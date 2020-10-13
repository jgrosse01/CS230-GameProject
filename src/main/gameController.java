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
public class gameController {
	private JFrame gameJFrame;
	private boolean initialized;
	private String gameState;
	private mainMenu menu;
	private Container mainPane;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();

	public gameController() {
	
		menu = new mainMenu();
		gameJFrame = new JFrame();
		gameJFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		gameJFrame.setTitle("Escape From Casa Bob");
		gameJFrame.setSize((int) width, (int) height);
        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameJFrame.add(menu);
       
        gameJFrame.setVisible(true);
        
        mainPane = gameJFrame.getContentPane();
        //IF SCREEN IS CYAN THEN WE DID SOMETHING WRONG
        gameJFrame.setBackground(Color.CYAN);
        mainPane.add(menu);
        mainPane.setVisible(true);

	}
	
	public void setContainer(Container container) { this.mainPane = container; }
	public String getState() { return gameState; }
	public boolean getInit() { return initialized; }
	public int getWindowWidth() { return width; }
	public int getWindowHeight() { return height; }

	public static void main(String[] args) {
        gameController myController = new gameController();
	}

}
