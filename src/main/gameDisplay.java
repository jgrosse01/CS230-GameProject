package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import levelBuilder.levelInfo;
import javax.swing.JOptionPane;

import entities.Player;
import tiles.Tile;
import java.util.Timer;
import levelBuilder.LevelLoader;

public class gameDisplay extends JPanel{

	private static final long serialVersionUID = -3506813690426695567L;
	
	private boolean gameIsReady = false;
	
	private JPanel level;
	private Player player;
	private static Timer timer = new Timer();
	private static int gravityTimer = 0;
	private static final int MOVE_TIME = 17;
	private static final int MAX_NO_GRAVITY_TIMER = 1000;
	
	private levelInfo currentLevel;
	//private int timerInterval = 17; //Unneeded due to change in timer use

	public gameDisplay(gameController frame) {
		level = new JPanel();
		level.setLayout(null);
		level.setVisible(true);
		this.setLayout(null);
		
		String levelFilename = JOptionPane.showInputDialog("Level Filename");
		currentLevel = LevelLoader.load(levelFilename + ".txt",this);
		currentLevel.drawLevel();
		gameIsReady = true;
	}

	public void scalePlayer() {
		ImageIcon imageIcon = new ImageIcon("images"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 240,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
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