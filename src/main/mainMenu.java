package main;


import javax.swing.*;
import java.awt.*;

public class mainMenu extends JPanel{
	JPanel gameContentPane;
	ImageIcon image;
	JButton button;
	
	public mainMenu() {
		gameContentPane = new JPanel();
		gameContentPane.setBorder(BorderFactory.createTitledBorder("TITTTLE"));
        gameContentPane.setLayout(null);
        this.setBackground(Color.green);
		button = new JButton("FHIOEHSFOIJSOIFJE");
		this.add(button, BorderLayout.CENTER);
		this.setVisible(true);
	}
}