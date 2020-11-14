package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tiles.Tile;

public class gameDisplay extends JPanel {
	
	
	
	

public void scalePlayer() {
	ImageIcon imageIcon = new ImageIcon("images"); // load the image to a imageIcon
	Image image = imageIcon.getImage(); // transform it 
	Image newimg = image.getScaledInstance(120, 240,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	imageIcon = new ImageIcon(newimg);  // transform it back
}





}