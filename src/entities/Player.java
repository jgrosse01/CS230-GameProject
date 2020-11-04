package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends Entity implements KeyListener {

	private int dx; //Direction x (change)
    private int dy; //Direction y (change)
    private boolean isAirbourne = false; //to utilize timer to set limit on jump
    private BufferedImage[] playerImages;
    //put timer and keep track in the controlling display class to set dy to -2 for time equivalent to time moving up (Super Mario Feel)
	
    public Player(int x, int y, BufferedImage imageLeft, BufferedImage imageRight, JPanel pane) throws IOException{
    	super(x,y,imageLeft,imageRight,pane);
    }
    
    public int getDX() { return dx; }
    public int getDY() { return dy; }
    
    //FOR USE IN THE CONTROLLING DISPLAY CLASS WITH TIMER
    public void setDY(int dY) { this.dy = dY; }
    
    public boolean isAirbourne() { return isAirbourne; }

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

        switch (key) {
        case (KeyEvent.VK_LEFT):
        	dx = -2;
        case (KeyEvent.VK_RIGHT):
        	dx = 2;
        case (KeyEvent.VK_UP):
        	dy = 2;
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
        case (KeyEvent.VK_LEFT):
        	dx = 0;
        case (KeyEvent.VK_RIGHT):
        	dx = 0;
        case (KeyEvent.VK_UP):
        	dy = 0;
        }
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//Unneeded
	}


}
