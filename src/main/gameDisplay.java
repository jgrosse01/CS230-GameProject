package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FilenameFilter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import levelBuilder.levelInfo;
import javax.swing.JOptionPane;

import entities.Player;
import tiles.Acid;
import tiles.Box;
import tiles.Dirt;
import tiles.EndPoint;
import tiles.Floor;
import tiles.SpawnPoint;
import tiles.Spikes;
import tiles.Tile;
import java.util.Timer;
import java.util.TimerTask;

import levelBuilder.LevelLoader;

public class gameDisplay extends JPanel{

	private static final long serialVersionUID = -3506813690426695567L;
	
	private boolean gameIsReady = false;
	
	private Player player;
	private gameController frame;
	private Timer timer = new Timer();
	private static int gravityTimer = 0;
	private Time timerThing;
	private static final int MOVE_TIME = 17;
	private static final int MAX_NO_GRAVITY_TIMER = 17000;
	private Point levelLoc;
	private levelInfo currentLevel;
	private SpawnPoint currentSpawn;
	private boolean exit = false;
	//private int timerInterval = 17; //Unneeded due to change in timer use

	public gameDisplay(gameController frame) {
		this.frame = frame;
		frame.setLayout(null);
		this.setLayout(null);
		this.setBackground(Color.gray);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();
				if(key == KeyEvent.VK_ESCAPE) {
					frame.gameToMain();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
		File filePath = new File("..\\game");
		if(!filePath.exists()) {
			filePath = new File("../game");
		}
		File[] fileList = filePath.listFiles();
		String[] fileNames = new String[fileList.length];
		int levelCount = 0;
		for(int i = 0; i < fileList.length; i ++) {
			if(fileList[i].getName().length() > 4) {
				if(fileList[i].getName().substring(fileList[i].getName().length() -4).equals(".txt")) {
					levelCount++;
					fileNames[i] = fileList[i].getName();
				}
			}
		}
		String[] levelNames = new String[levelCount];
		int inc = 0;
		for(int i = 0; i < fileNames.length; i++) {
			if(fileNames[i] != null) {
				levelNames[inc] = fileNames[i];
				inc++;
			}
		}
		JComboBox levelComboBox = new JComboBox(levelNames);
		int response = JOptionPane.showConfirmDialog(null, levelComboBox, "Select a level", 
				JOptionPane.OK_CANCEL_OPTION);
		if(response == JOptionPane.OK_OPTION) {
			try {
				currentLevel = LevelLoader.load((String)levelComboBox.getSelectedItem(),this);
			} catch (FileNotFoundException err) {
				
			}
		} else {
			exit = true;
		}
		if(!exit) {
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
			} else {
				timerThing = new Time();
				timer.schedule(timerThing, 500);
			}
		}
	
		/*
		private void makeLevelArray() {
			levelArray = new levelInfo[10];
			try {
				level1 = LevelLoader.load("1" + ".txt",this);
			} catch (FileNotFoundException e) {e.printStackTrace();}
		}
		*/
	
		public void scalePlayer() {
			ImageIcon imageIcon = new ImageIcon("images"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(gameController.getBlockDimension(), gameController.getBlockDimension()*2, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
		}
		
		class Time extends TimerTask {
			public void run() {
				if (gameIsReady) {
					if(player.getDY() < 20 && player.canMoveDown()) {
						player.setDY(player.getDY()+1);
					}
					collisionCheck();
					player.move();
				} else if(exit){
					frame.gameToMain();
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
			if((xIA == 0 && x <= 0)) {
				player.setCanMoveLeft(false);
			} else if(x > dim){
				if(level[xIA-1][yIA] != null) {
					if(level[xIA-1][yIA].isCollideable() && x < level[xIA-1][yIA].getX()+dim+10) {
						player.setCanMoveLeft(false);
						if(player.getHitBox().intersects(level[xIA-1][yIA].getHitBox())) {
							player.setX((xIA*dim)+dim);
						}
					} 
				}
				if(level[xIA-1][yIA+1] != null) {
					if(level[xIA-1][yIA+1].isCollideable() && x < level[xIA-1][yIA+1].getX()+dim+10) {
						player.setCanMoveLeft(false);
						if(player.getHitBox().intersects(level[xIA-1][yIA+1].getHitBox())) {
							player.setX((xIA*dim)+dim);
						}
					} 
				}
				if(level[xIA-1][yIA+2] != null) {
					if(level[xIA-1][yIA+2].isCollideable() && y > level[xIA-1][yIA+2].getY()-(dim*2) && x > level[xIA-1][yIA+2].getX()+dim+10) {
						player.setCanMoveLeft(false);
//						if(player.getHitBox().intersects(level[xIA-1][yIA+2].getHitBox())) {
//							player.setX((xIA*dim)+dim);
//						}
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
					if(level[xIA+1][yIA+2].isCollideable() && y > level[xIA+1][yIA+2].getY()-(dim*2)+10) {
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
			if(yIA <= 0 && y <= 0) {
				player.setCanMoveUp(false);
			} else if(y > dim){
				if(level[xIA][yIA-1] != null && y <= level[xIA][yIA-1].getY()+dim+5) {
					if(level[xIA][yIA-1].isCollideable()) {
						player.setCanMoveUp(false);
						if(player.getHitBox().intersects(level[xIA][yIA-1].getHitBox())) {
							player.setY((yIA+1)*dim);
						}
					}
				}
				if(xIA > 0) {
					if(level[xIA+1][yIA-1] != null) {
						if(level[xIA+1][yIA-1].isCollideable() && x >= level[xIA+1][yIA-1].getX()-dim+20 && y < level[xIA+1][yIA-1].getY()+dim+5) {
							player.setCanMoveUp(false);
							if(player.getHitBox().intersects(level[xIA+1][yIA-1].getHitBox())) {
								player.setY((yIA+1)*dim);
							}
						}
					}
				}
			}
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
					} else {
						if(level[xIA][yIA+2] instanceof Acid) {
							if(player.getHitBox().intersects(level[xIA][yIA+2].getHitBox())) {
								player.respawn();
							}
						}
					}
				}
				if(xIA > 0) {
					if(level[xIA+1][yIA+2] != null) {
						if(level[xIA+1][yIA+2].isCollideable() && x >= level[xIA+1][yIA+2].getX()-dim+20) {
							player.setCanMoveDown(false);
							if(player.getHitBox().intersects(level[xIA+1][yIA+2].getHitBox())) {
								player.setY(yIA*dim);
							}
						} else {
							if(level[xIA+1][yIA+2] instanceof Acid && x >= level[xIA+1][yIA+2].getX()-dim+20) {
								if(player.getHitBox().intersects(level[xIA+1][yIA+2].getHitBox())) {
									player.respawn();
								}
							}
						}
					}
				}
			}
		}
	}
}