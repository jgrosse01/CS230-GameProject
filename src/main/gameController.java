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

import levelBuilder.levelBuilder;
import levelBuilder.levelInfo;

@SuppressWarnings("unused")
public class gameController extends JFrame{
	private static final long serialVersionUID = 6581184009482946096L;
	
	//private JFrame gameJFrame;
	private boolean initialized;
	private String gameState;
	private Container mainPane;
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int) screenSize.getWidth();
    private static int height = (int) screenSize.getHeight();
    private static int blockDim = width/15;
    
    //maybe put into a separate jpanel to actually run the levels?
    public levelInfo level;

	public gameController() {
	
		mainMenu menu = new mainMenu(this);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setTitle("Escape From Casa Bob");
		setSize(width, height);
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
	public static int getBlockDimension() { return blockDim; }

	public static void main(String[] args) {
        gameController myController = new gameController();
	}
	
	public void mainToBuilder() {
		mainPane.setVisible(false);
		mainPane.remove(0);
		levelBuilder builder = new levelBuilder(this);
		mainPane.add(builder);
		mainPane.setVisible(true);
	}
	
	public void builderToMain() {
		mainPane.setVisible(false);
		mainPane.remove(0);
		mainMenu menu = new mainMenu(this);
		mainPane.add(menu);
		mainPane.setVisible(true);
	}

}
