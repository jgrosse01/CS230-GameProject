package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import levelBuilder.levelInfo;
import javax.swing.JOptionPane;

import entities.Player;
import tiles.SpawnPoint;
import tiles.Tile;
import java.util.Timer;
import main.gameController;
import levelBuilder.LevelLoader;

public class gameDisplay extends JPanel{

	private static final long serialVersionUID = -3506813690426695567L;
	
	private boolean gameIsReady = false;
	
	private Player player;
	private static Timer timer = new Timer();
	private static int gravityTimer = 0;
	private static final int MOVE_TIME = 17;
	private static final int MAX_NO_GRAVITY_TIMER = 1000;
	private Point levelLoc;
	private levelInfo currentLevel;
	private SpawnPoint currentSpawn;
	//private int timerInterval = 17; //Unneeded due to change in timer use

	public gameDisplay(gameController frame) {
		frame.setLayout(null);
		this.setLayout(null);
		this.setBackground(Color.black);
		
		//temp code for draggin and clicking
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				levelLoc = e.getPoint();
			}
		});
		//making level panel draggable
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point currentScreenLoc = e.getLocationOnScreen();
				setLocation(currentScreenLoc.x - levelLoc.x, currentScreenLoc.y - levelLoc.y);
			}
		});
		
		String levelFilename = JOptionPane.showInputDialog("Level Filename");
		currentLevel = LevelLoader.load(levelFilename + ".txt",this);
		this.setBounds(0,0,currentLevel.getLevel().length*gameController.getBlockDimension(),currentLevel.getLevel()[0].length*gameController.getBlockDimension());
		currentLevel.drawLevel();
		setCurrentSpawn();
		try {
			BufferedImage tempSprite = ImageIO.read(new File("src/sprites/Idle (0).png"));
			player = new Player(0,0,tempSprite,tempSprite,this,currentSpawn);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		player.respawn();
		player.draw();
		gameIsReady = true;
	}

	public void scalePlayer() {
		ImageIcon imageIcon = new ImageIcon("images"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(gameController.getBlockDimension(), gameController.getBlockDimension()*2, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
	}
	
	public void run() {
		if (gameIsReady) {
			if (player.didJump()) {
				player.invertDY();
				gravityTimer += MOVE_TIME;
			}
			if (gravityTimer >= MAX_NO_GRAVITY_TIMER) {
				player.invertDY();
				player.smackdown();
				gravityTimer = 0;
			}
		}
	}
	
	public void setCurrentSpawn() {
		Tile[][] levelLayout = currentLevel.getLevel();
		for(int y = 0; y < levelLayout[0].length; y++) {
			for(int x = 0; x < levelLayout.length; x++) {
				if(levelLayout[x][y] instanceof SpawnPoint) {
					SpawnPoint sp = ((SpawnPoint) levelLayout[x][y]);
					if(sp.isCurrent()) {
						currentSpawn = sp;
					}
				}
			}
		}
	}
}