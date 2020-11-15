package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import levelBuilder.levelInfo;
import javax.swing.JOptionPane;

import entities.Player;
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
		this.setBounds(0,0,currentLevel.getLevelLayout().length*gameController.getBlockDimension(),currentLevel.getLevelLayout()[0].length*gameController.getBlockDimension());
		currentLevel.drawLevel();
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
}