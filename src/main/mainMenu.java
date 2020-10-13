package main;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class mainMenu extends JPanel{
	private static final long serialVersionUID = 7962642755482788779L;
	ImageIcon background;
	JPanel panel;
	JLabel picLabel;
	
	JButton play;
	JButton levelBuilder;
	JButton exit;
	
	public mainMenu() {
		panel = new JPanel();
		picLabel = new JLabel();
		background = new ImageIcon("src/main/bob.jpeg");
		Image img = background.getImage();
		Image newImg = img.getScaledInstance(gameController.getWindowWidth(), gameController.getWindowHeight(), Image.SCALE_SMOOTH);
		ImageIcon finalImage = new ImageIcon(newImg);
		picLabel.setIcon(finalImage);
		
		this.add(picLabel);
		this.setVisible(true);
	
		play = new JButton();
		levelBuilder = new JButton();
		exit = new JButton();
	}

}
