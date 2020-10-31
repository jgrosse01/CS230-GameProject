package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import tiles.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends Entity implements KeyListener, MouseListener {

	private int dx; //Direction x (change)
    private int dy; //Direction y (change)
    private boolean isAirbourne = false; //to utilize timer to set limit on jump
    private BufferedImage[] playerImages;
    private Tile inventorySlot = null;
    //put timer and keep track in the controlling display class to set dy to -2 for time equivalent to time moving up (Super Mario Feel)
	
    public Player(int x, int y, BufferedImage imageLeft, BufferedImage imageRight, JPanel pane) throws IOException{
    	super(x,y,imageLeft,imageRight,pane);
    }
    
    public int getDX() { return dx; }
    public int getDY() { return dy; }
    
    //FOR USE IN THE CONTROLLING DISPLAY CLASS WITH TIMER
    public void setDY(int dY) { this.dy = dY; }
    
    public boolean isAirbourne() { return isAirbourne; }
    
    public void loadImage(String fileName)
	{
		if (fileName != null)
			try
			{
				for (int i = 0; i<83; i++) {
					playerImages[i] = ImageIO.read(new File("sprites/" + fileName));
				}
			}
			catch (IOException e)
			{
				System.out.println("Image not found.");
			}
		
	}

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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
