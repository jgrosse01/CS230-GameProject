package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
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
import java.util.TimerTask;

import levelBuilder.LevelLoader;

public class gameDisplay extends JPanel{

	private static final long serialVersionUID = -3506813690426695567L;
	
	private boolean gameIsReady = false;
	
	private Player player;
	private Timer timer = new Timer();
	private static int gravityTimer = 0;
	private Time timerThing;
	private static final int MOVE_TIME = 17;
	private static final int MAX_NO_GRAVITY_TIMER = 17000;
	private Point levelLoc;
	private levelInfo currentLevel;
	private SpawnPoint currentSpawn;
	//private int timerInterval = 17; //Unneeded due to change in timer use

	public gameDisplay(gameController frame) {
		frame.setLayout(null);
		this.setLayout(null);
		this.setBackground(Color.gray);
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
		
		boolean choosing = true;
		while(choosing) {
			String levelFilename = JOptionPane.showInputDialog("Level Filename");
			try {
				currentLevel = LevelLoader.load(levelFilename + ".txt",this);
				choosing = false;
			} catch (FileNotFoundException err) {
				
			}
		}
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
		timerThing = new Time();
		timer.schedule(timerThing, 0, MOVE_TIME);
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
	
	class Time extends TimerTask {
		public void run() {
			if (gameIsReady) {
				if (!player.isGravity())
					gravityTimer += MOVE_TIME;
				if (gravityTimer >= MAX_NO_GRAVITY_TIMER) {
					player.smackdown();
					gravityTimer = 0;
				}					
				if (player.isGravity() && player.canMoveDown()) {
					player.setDY(10);
				} else if (player.isGravity()) {
					player.setDY(0);
				}
				if (!player.canMoveDown())
					player.canJumpTrue();
				else
					player.canJumpFalse();
				collisionCheck();
				player.move();
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
	
	public void collisionCheck() {
		Rectangle checkBox;
		Tile[][] level = currentLevel.getLevel();
		int x = player.getX();
		int y = player.getY();
		int dx = player.getDX();
		int dy = player.getDY();
		int dim = gameController.getBlockDimension();
		int xIA = x/dim;
		int yIA = y/dim;
		//player going left
		if(dx < 0) {
			if(xIA == 0 && x <= 0) {
				player.setCanMoveLeft(false);
			} else {
				if(level[xIA][yIA] != null) {
					if(level[xIA][yIA].isCollideable()) {
						player.setCanMoveLeft(false);
						if(player.getHitBox().intersects(level[xIA][yIA].getHitBox())) {
							player.setX((xIA*dim)+dim);
						}
					}
				}
				if(level[xIA][yIA+1] != null) {
					if(level[xIA][yIA+1].isCollideable()) {
						player.setCanMoveLeft(false);
						if(player.getHitBox().intersects(level[xIA][yIA+1].getHitBox())) {
							player.setX((xIA*dim)+dim);
						}
					}
				}
				if(level[xIA][yIA+2] != null) {
					if(level[xIA][yIA+2].isCollideable() && y > level[xIA][yIA+2].getY()-(dim*2)) {
						player.setCanMoveLeft(false);
						if(player.getHitBox().intersects(level[xIA][yIA+2].getHitBox())) {
							player.setX((xIA*dim)+dim);
						}
					}
				}
			}
		}
		//player going right
		if(dx > 0) {
			if(xIA == level.length-1) {
				player.setCanMoveRight(false);
			} else {
				if(level[xIA+1][yIA] != null) {
					if(level[xIA+1][yIA].isCollideable()) {
						player.setCanMoveRight(false);
						if(player.getHitBox().intersects(level[xIA+1][yIA].getHitBox())) {
							player.setX(xIA*dim);
						}
					}
				}
				if(level[xIA+1][yIA+1] != null) {
					if(level[xIA+1][yIA+1].isCollideable()) {
						player.setCanMoveRight(false);
						if(player.getHitBox().intersects(level[xIA+1][yIA+1].getHitBox())) {
							player.setX(xIA*dim);
						}
					}
				}
				if(level[xIA+1][yIA+2] != null) {
					if(level[xIA+1][yIA+2].isCollideable() && y > level[xIA+1][yIA+2].getY()-(dim*2)) {
						player.setCanMoveRight(false);
						if(player.getHitBox().intersects(level[xIA+1][yIA+2].getHitBox())) {
							player.setX(xIA*dim);
						}
					}
				}
			}
		}
		//player going up
		if(dy < 0) {

		}
		//player going down
		if(dy > 0) {
			if(yIA >= level[0].length-2) {
				player.setCanMoveDown(false);
			} else {
				if(level[xIA][yIA+2] != null) {
					if(level[xIA][yIA+2].isCollideable()) {
						player.setCanMoveDown(false);
						if(player.getHitBox().intersects(level[xIA][yIA+2].getHitBox())) {
							player.setY(yIA*dim);
						}
					}
				}
				if(xIA > 0) {
					if(level[xIA+1][yIA+2] != null) {
						if(level[xIA+1][yIA+2].isCollideable() && x >= level[xIA+1][yIA+2].getX()-dim+4) {
							player.setCanMoveDown(false);
							if(player.getHitBox().intersects(level[xIA+1][yIA+2].getHitBox())) {
								player.setY(yIA*dim);
							}
						}
					}
				}
			}
		}
	}
}