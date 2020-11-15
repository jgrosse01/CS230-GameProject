package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import levelBuilder.levelInfo;

import entities.Player;
import tiles.Tile;
import java.util.Timer;
import main.gameController;

public class gameDisplay extends JPanel{

	private static final long serialVersionUID = -3506813690426695567L;
	
	JPanel level;
	Player player;
	
	private levelInfo currentLevel;
	private int timerInterval = 17;

	public gameDisplay(gameController frame) {
		level = new JPanel();
		level.setLayout(null);
		level.setVisible(true);
	}

	public void scalePlayer() {
		ImageIcon imageIcon = new ImageIcon("images"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(gameController.getBlockDimension(), gameController.getBlockDimension()*2, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
	}
	
	public void camera() {
		
	}
	
	public void run() {
		
	}
	
	private void paint() {
		
	}
}