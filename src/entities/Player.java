package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import tiles.Tile;
import tiles.SpawnPoint;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends Entity implements KeyListener, MouseListener {

	private int dx; //Direction x (change)
    private int dy; //Direction y (change)
    private boolean isAirbourne = false; //to utilize timer to set limit on jump
    private static BufferedImage[] run;
    private static BufferedImage[] leftRun;
    private static BufferedImage[] idle;
    private static BufferedImage[] leftIdle;
    private static BufferedImage[] jump;
    private static BufferedImage[] leftJump;
    private static BufferedImage[] dead;
    private static BufferedImage[] leftDead;
    private boolean didJump = false; //to utilize timer to set limit on jump
    private BufferedImage[] playerImages;
    private Tile inventorySlot = null;
    private SpawnPoint sp;
    //put timer and keep track in the controlling display class to set dy to -2 for time equivalent to time moving up (Super Mario Feel)

    private int currentIconNumber = 1;
    private String currentIconType = "Run";
    private BufferedImage currentImage;
    private ImageIcon currentIcon;
   
    /**
     * 
     * @param x starting x
     * @param y starting y
     * @param imageLeft left facing image
     * @param imageRight right facing image
     * @param pane pane to be painted to
     * @param sp spawnpoint of player
     * @throws IOException cannot find images
     **/
    
    public Player(int x, int y, BufferedImage imageLeft, BufferedImage imageRight, JPanel pane, SpawnPoint sp) throws IOException{
    	super(x,y,imageLeft,imageRight,pane);
    	this.sp = sp;
    	pane.setFocusable(true);
    	pane.addKeyListener(this);
    	System.out.println(pane);
    }
    
    public int getDX() { return dx; }
    public int getDY() { return dy; }
    
    //FOR USE IN THE CONTROLLING DISPLAY CLASS WITH TIMER
    public void setDY(int dY) { this.dy = dY; }
    
    public boolean isAirbourne() { return isAirbourne; }
    
    public void loadImage(String fileName)
	{
		try {
			idle = new BufferedImage[1];
			leftIdle = new BufferedImage[1];
			run = new BufferedImage[16];
			leftRun = new BufferedImage[16];
			jump = new BufferedImage[16];
			leftJump = new BufferedImage[16];
			dead = new BufferedImage[16];
			leftDead = new BufferedImage[16];
			
			idle[0] = ImageIO.read(new File("src/sprites/Idle (0).png"));
			leftIdle[0] = ImageIO.read(new File("src/sprites/leftIdle (0).png"));
			for (int i = 0; i<15; i++) {
				run[i] = ImageIO.read(new File("src/sprites/Run ("+ (i+1) +").png"));
			}
			for (int i = 0; i<15; i++) {
				leftRun[i] = ImageIO.read(new File("src/sprites/leftRun ("+ (i+1) +").png"));
			}
			for (int i = 0; i<15; i++) {
				jump[i] = ImageIO.read(new File("src/sprites/Jump ("+ (i+1) +").png"));
			}
			for (int i = 0; i<15; i++) {
				leftJump[i] = ImageIO.read(new File("src/sprites/leftJump ("+ (i+1) +").png"));
			}
			for (int i = 0; i<15; i++) {
				dead[i] = ImageIO.read(new File("src/sprites/Dead ("+ (i+1) +").png"));
			}
			for (int i = 0; i<15; i++) {
				leftDead[i] = ImageIO.read(new File("src/sprites/leftDead ("+ (i+1) +").png"));
			}
		} catch (IOException e) {e.printStackTrace();}
	}
    
    public void move() {
		label.setLocation(label.getX()+dx, label.getY()+dy);
		//draw();
		
		if (currentIconType == "Idle" || currentIconType == "leftIdle") {
			currentIconNumber = 0;
			File file = new File("src/sprites/" + currentIconType + " " + "(" + currentIconNumber + ")" + ".png");
			try {
				currentImage = ImageIO.read(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentIcon = new ImageIcon(currentImage);
			label.setIcon(currentIcon);
		}
		
		/*
		switch (currentIconType) {
		case "Idle":
			currentIcon = new ImageIcon(idle[0]);
			label.setIcon(currentIcon);
		case "leftIdle":
			currentIcon = new ImageIcon(leftIdle[0]);
			label.setIcon(currentIcon);
		case "Run":
			for (int i = 0; i<15; i++) {
				currentIcon = new ImageIcon(run[i+1]);
				label.setIcon(currentIcon);
			}
		case "leftRun":
			for (int i = 0; i<15; i++) {
				currentIcon = new ImageIcon(leftRun[i+1]);
			}
			label.setIcon(currentIcon);
		case "Jump":
			for (int i = 0; i<15; i++) {
				currentIcon = new ImageIcon(jump[i+1]);
			}
			label.setIcon(currentIcon);
		case "leftJump":
			for (int i = 0; i<15; i++) {
				currentIcon = new ImageIcon(leftJump[i+1]);
			}
			label.setIcon(currentIcon);
		case "Dead":
			for (int i = 0; i<15; i++) {
				currentIcon = new ImageIcon(dead[i+1]);
			}
			label.setIcon(currentIcon);
		case "leftDead":
			for (int i = 0; i<15; i++) {
				currentIcon = new ImageIcon(leftDead[i+1]);
			}
			label.setIcon(currentIcon);
		}
		*/
		
		
		else {
			currentIconNumber = (currentIconNumber>=15) ? 0 : (currentIconNumber+1) %15;
			File file = new File("src/sprites/" + currentIconType + " " + "(" + currentIconNumber + ")" + ".png");
			try {
				currentImage = ImageIO.read(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentIcon = new ImageIcon(currentImage);
			label.setIcon(currentIcon);
		}
		
	}
    
    public void respawn() {
    	this.setX(sp.getX());
    	this.setY(sp.getY());
    }
    
    public void gravity() {
    	int terminalVelocity = -10;
    	int gravity = -1;
    	int verticalSpeed = 0;
    	
    	verticalSpeed = verticalSpeed + gravity;
        if(verticalSpeed > terminalVelocity){
        	verticalSpeed = terminalVelocity;
        }
        this.setY(this.getY() + verticalSpeed);
    	
    }

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

        switch (key) {
        case (KeyEvent.VK_LEFT):
        	System.out.println("LEFT MOVE");
        	setCurrentDir(DIR_LEFT);
        	currentIconType = "leftRun";
        	//label.setLocation((int) (getX()-(label.getWidth()*0.5)), getY());
        	dx = -10;
        	dy = 0;
        	break;
        case (KeyEvent.VK_RIGHT):
        	System.out.println("RIGHT MOVE");
        	setCurrentDir(DIR_RIGHT);
        	currentIconType = "Run";
        	//label.setLocation((int) (getX()+(label.getWidth()*0.5)), getY());
        	dy = 0;
        	dx = 10;
        	break;
        case (KeyEvent.VK_UP):
        	if (getCurrentDir() == DIR_RIGHT) {
        		System.out.println("UP RIGHT MOVE");
            	currentIconType = "Jump";
            	dy = 10;
            	break;
        	}
        	else {
        		System.out.println("UP LEFT MOVE");
            	currentIconType = "leftJump";
            	dy = 10;
            	break;
        	}
        }
        move();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
        case (KeyEvent.VK_LEFT):
        	currentIconType = "leftIdle";
        	currentIconNumber = 0;
        	dx = 0;
        case (KeyEvent.VK_RIGHT):
        	currentIconType = "Idle";
        	currentIconNumber = 0;
        	dx = 0;
        case (KeyEvent.VK_UP):
        	if (getCurrentDir() == DIR_RIGHT)
        	{
        		currentIconType = "Idle";
            	currentIconNumber = 0;
            	dy = 0;
        	}
        	else {
        		currentIconType = "leftIdle";
            	currentIconNumber = 0;
            	dy = 0;
        	}
        }
		move();
	}
	
	public SpawnPoint getSpawnPoint() {
		return sp;
	}
	
	public void setSpawnPoint(SpawnPoint sp) {
		this.sp = sp;
	}
	
	public Tile getInventory() {
		return inventorySlot;
	}
	
	public void setInventory(Tile t) {
		inventorySlot = t;
	}
	
	public void clearInventory() {
		inventorySlot = null;
	}
	
	public void invertDY() {
		dy = -1*dy;
	}
	
	public boolean didJump() {
		return didJump;
	}
	
	//sets didJump to false and allows gravity to work
	public void smackdown() {
		didJump = false;
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
