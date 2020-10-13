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
	
	public mainMenu() {
		panel = new JPanel();
		picLabel = new JLabel();
		background = new ImageIcon("src/main/bob.jpeg");
		Image img = background.getImage();
		System.out.println(panel.getWidth() +","+ panel.getHeight());
		Image newImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon finalImage = new ImageIcon(newImg);
		picLabel.setIcon(finalImage);
		
		this.add(picLabel);
		this.setVisible(true);
	
		
	
		/*
			try {
				bi = ImageIO.read(new File("bob.jpeg"));
				JPanel p = new mainMenu();
				p.setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
	}
}
