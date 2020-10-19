package entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Player implements KeyListener {

	private int dx; //Direction x
    private int dy; //Direction y
    private int x = 40; //Initial position X
    private int y = 60; //Initial position Y
    private int w; //width
    private int h; //height
    private BufferedImage image; //image
    private BufferedImage spriteDoubleBuffer;
	private AffineTransform spriteTransform;
	private Graphics2D spriteDoubleBufferG2D;
	private Graphics2D spriteImageG2D;
	
    public Player(int width, int height, BufferedImage doubleBuffer){
        /*Position is set with setters */
        this.x = 0;
        this.y = 0;
        this.w = width;
        this.h = height;
        this.spriteDoubleBufferG2D = (Graphics2D) doubleBuffer.getGraphics();
        this.spriteTransform = new AffineTransform();
        
    }
	
    public Graphics2D getSpriteDoubleBufferG2D() {
        return spriteDoubleBufferG2D;
    }
    
    public void transform(){
              
        this.spriteTransform.setToIdentity(); 
        this.spriteTransform.translate((int)this.getX(), (int)this.getY());

    }
    
    public void draw(){
        this.transform();
        this.spriteDoubleBufferG2D.drawImage(this.getSpriteImage(), spriteTransform, null);
        //this.spriteDoubleBufferG2D.drawImage(this.getSpriteImage(), spriteTransform,  Main.getInstance());
        //this.spriteDoubleBufferG2D.drawImage(spriteImage, 0, 0, null);
    }
    
    public void loadSpriteImage(String name) {
        this.setSpriteImage(Toolkit.getDefaultToolkit().getImage("src/sprites/"+name));
       
    }

    public Image getSpriteImage() {
        return image;
    }

    public void setSpriteImage(Image spriteImage) {
        this.image = (BufferedImage) spriteImage;
    }

    public Graphics2D getSpriteImageG2D() {
        return spriteImageG2D;
    }

    public void setSpriteImageG2D(Graphics2D spriteImageG2D) {
        this.spriteImageG2D = spriteImageG2D;
    }
    
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
		
	}

}
