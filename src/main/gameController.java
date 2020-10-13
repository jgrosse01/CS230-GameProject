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
public class gameController extends JFrame{
	private static final long serialVersionUID = 6581184009482946096L;
	
	//private JFrame gameJFrame;
	private boolean initialized;
	private String gameState;
	private mainMenu menu;
	private Container mainPane;
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int) screenSize.getWidth();
    private static int height = (int) screenSize.getHeight();

	public gameController() {
	
		menu = new mainMenu();
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setTitle("Escape From Casa Bob");
		setSize((int) width, (int) height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        mainPane = getContentPane();
        //IF SCREEN IS CYAN THEN WE DID SOMETHING WRONG
        setBackground(Color.CYAN);
        mainPane.add(menu);
        mainPane.setVisible(true);

	}
	
	public void setContainer(Container container) { this.mainPane = container; }
	public String gameState() { return gameState; }
	public boolean getInit() { return initialized; }
	public static int getWindowWidth() { return width; }
	public static int getWindowHeight() { return height; }

	public static void main(String[] args) {
        gameController myController = new gameController();
	}

}
