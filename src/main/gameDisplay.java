package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tiles.Tile;
import java.util.Timer;

import levelBuilder.levelInfo;

public class gameDisplay extends JPanel{

	private static final long serialVersionUID = -3506813690426695567L;
	
	private levelInfo currentLevel;
	private int timerInterval = 17;

	public gameDisplay(gameController frame) {
		
	}
	
	public void run() {
		
	}
	
	private void paint() {
		
	}

	public void scalePlayer() {
		ImageIcon imageIcon = new ImageIcon("images"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 240,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
}





}