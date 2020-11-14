package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entities.Player;
import tiles.Tile;

public class gameDisplay extends JPanel {
	
	JPanel level;
	Player player;
	
	public gameDisplay() {
		level = new JPanel();
		level.setLayout(null);
		level.setVisible(true);
		
		
	}

	public void scalePlayer() {
		ImageIcon imageIcon = new ImageIcon("images"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 240,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
	}
	
	public void loadLevel() {
		
	}
	
	public void camera() {
		
	}

}