package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame; // for JFrame
import javax.swing.JOptionPane; // messages are displayed using JOptionPane

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
		
		menu = new mainMenu();
		gameJFrame = new JFrame();
		gameJFrame.setTitle("GEHSUGHSEOISI");
		gameJFrame.setSize((int) width, (int) height);
        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameJFrame.setVisible(true);
        
        mainPane = gameJFrame.getContentPane();
        gameJFrame.setBackground(Color.red);
        //menu.setBackground(Color.red);
        mainPane.add(menu);
        mainPane.setVisible(true);

	}

	public static void main(String[] args) {
        gameController myController = new gameController();

	}

}
