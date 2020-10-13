package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame; // for JFrame
import javax.swing.JOptionPane; // messages are displayed using JOptionPane
import javax.swing.JPanel;

@SuppressWarnings("unused")
class gameController {
	private JFrame gameJFrame;
	boolean initialized;
	String gameState;
	mainMenu menu;
	Container mainPane;
	
	JButton button;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

	public gameController() {
		
		JFrame gameJFrame = new JFrame();
		JPanel menu = new mainMenu();
		gameJFrame.setTitle("Escape");
		gameJFrame.setSize((int) width, (int) height);
        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameJFrame.add(menu);
       
        gameJFrame.setVisible(true);
        
	}

	public static void main(String[] args) {
        gameController myController = new gameController();

	}

}
