package main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainMenu extends JLayeredPane implements ActionListener{
	private static final long serialVersionUID = 7962642755482788779L;
	ImageIcon background;
	
	JPanel panel;
	
	JLabel picLabel;
	JLabel picTitle;
	
	JButton play;
	JButton levelBuilder;
	JButton exit;
	
	public mainMenu() {
		this.setLayout(null);
		picLabel = new JLabel(); //create jlabel
		background = new ImageIcon("src/main/bob.jpeg"); //init image
		Image img = background.getImage(); //transform to img
		Image newImg = img.getScaledInstance(gameController.getWindowWidth(), gameController.getWindowHeight(), Image.SCALE_SMOOTH); //create scaled version
		ImageIcon finalImage = new ImageIcon(newImg); //transform back to image icon
		picLabel.setIcon(finalImage); //set image to label
		picLabel.setBounds(0, 0, gameController.getWindowWidth(), gameController.getWindowHeight()); //set bounds of label on the layered panel. (has to be done with layered panel.
		
		this.add(picLabel, 1); //add to position 1 on the layered panel
		
		play = new JButton("Play"); //init play button
		levelBuilder = new JButton("Levels"); //init levels button
		exit = new JButton("Exit"); //init exit button
		
		play.setBounds(500, 200, 50, 50); //set bounds/placement
		levelBuilder.setBounds(500, 300, 50, 50); //set bounds/placement
		exit.setBounds(500, 400, 50, 50); //set bounds/placement
		
		this.add(play, 0); //add play button to 0 position (outermost position)
		this.add(levelBuilder, 0); //add levels button
		this.add(exit, 0); //add exit button
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Action Listener for buttons.
		
	}

}
