package entities;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
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

import main.gameController;

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
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private boolean isGravity = false; //to utilize timer to set limit on jump
    private boolean canJump = true; //to tell the player if they can jump
    private Tile inventorySlot = null;
    private SpawnPoint sp;
    private boolean canMoveDown = true;
    private boolean canMoveRight = true;
    private boolean canMoveLeft = true;
    private boolean canMoveUp = true;
    //put timer and keep track in the controlling display class to set dy to -2 for time equivalent to time moving up (Super Mario Feel)

    //Hitbox for collision detecting
    private Rectangle hitBox;
    
    private int currentIconNumber = 1;
    private String currentIconType = "Run";
    private BufferedImage currentImage;
    private ImageIcon currentIcon;
    private JPanel panel;
   
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
    
    public Player(int x, int y, BufferedImage imageLeft, BufferedImage imageRight, JPanel pane, SpawnPoint sp) throws IOException {
    	super(x,y,imageLeft,imageRight,pane);
    	this.sp = sp;
    	panel = pane;
    	pane.setFocusable(true);
    	pane.addKeyListener(this);
    	System.out.println(pane);
    	imageLeft = ImageIO.read(new File("src/sprites/leftIdle (0).png"));
    	imageRight = ImageIO.read(new File("src/sprites/Idle (0).png"));
    	//hitBox slightly bigger than player to make collision detection easier
    	hitBox = new Rectangle(x-1,y-1,gameController.getBlockDimension()+2,(gameController.getBlockDimension()*2)+2);
    }
    
    public void loadImage(String fileName) {
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
    	if(dy > 0 && !canMoveDown) {setDY(0);}
    	if(dy < 0 && !canMoveUp) {setDY(0);}
    	if(dx > 0 && !canMoveRight) {setDX(0);}
    	if(dx < 0 && !canMoveLeft) {setDX(0);}
		label.setBounds(label.getX()+dx, label.getY()+dy, gameController.getBlockDimension(), gameController.getBlockDimension()*2);
		hitBox.setBounds(label.getX(),label.getY(),gameController.getBlockDimension(), gameController.getBlockDimension()*2);
		panel.setLocation((panel.getX()-dx), (panel.getY()-dy));
		
		if (currentIconType == "Idle" || currentIconType == "leftIdle") {
			currentIconNumber = 0;
			File file = new File("src/sprites/" + currentIconType + " " + "(" + currentIconNumber + ")" + ".png");
			try {
				currentImage = ImageIO.read(file);
			} catch (IOException e) {e.printStackTrace();}
			currentIcon = new ImageIcon(currentImage); //load the image to a imageIcon
			Image img = currentIcon.getImage(); // transform it 
			Image newimg = img.getScaledInstance(gameController.getBlockDimension(), gameController.getBlockDimension()*2, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			currentIcon = new ImageIcon(newimg);
			label.setIcon(currentIcon);
		}		
		else {
			currentIconNumber = (currentIconNumber>=15) ? 0 : (currentIconNumber+1) %15;
			File file = new File("src/sprites/" + currentIconType + " " + "(" + currentIconNumber + ")" + ".png");
			
			try {
				currentImage = ImageIO.read(file);
			} catch (IOException e) {e.printStackTrace();}
			currentIcon = new ImageIcon(currentImage); //load the image to a imageIcon
			Image img = currentIcon.getImage(); // transform it 
			Image newimg = img.getScaledInstance(gameController.getBlockDimension(), gameController.getBlockDimension()*2, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			currentIcon = new ImageIcon(newimg);  // transform it back
			label.setIcon(currentIcon);
		}
		canMoveDown = true;
		canMoveUp = true;
		canMoveRight = true;
		canMoveLeft = true;
	}
    
    public void respawn() {
    	int x = sp.getX();
    	int y = sp.getY() - gameController.getBlockDimension();
    	this.setX(x);
    	this.setY(y);
    	label.setBounds(x, y, gameController.getBlockDimension(), gameController.getBlockDimension()*2);
		hitBox.setBounds(label.getX(),label.getY(),gameController.getBlockDimension(), gameController.getBlockDimension()*2);
    	
    	panel.setLocation((int)(screenSize.getWidth()/2)-x-(int)(gameController.getBlockDimension()/2),(int)(screenSize.getHeight()/2)-y-gameController.getBlockDimension());
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
        	dx = -10;
        	dy = 0;
        	break;
        case (KeyEvent.VK_RIGHT):
        	System.out.println("RIGHT MOVE");
        	setCurrentDir(DIR_RIGHT);
        	currentIconType = "Run";
        	dy = 0;
        	dx = 10;
        	break;
        case (KeyEvent.VK_UP):
        	if (canJump) {
        		if (getCurrentDir() == DIR_RIGHT) {
    				System.out.println("UP RIGHT MOVE");
        			currentIconType = "Jump";
        			isGravity = false;
        			canJump = false;
        			dy = -10;
        			break;
    			}
    			else {
    				System.out.println("UP LEFT MOVE");
        			currentIconType = "leftJump";
        			isGravity = false;
        			canJump = false;
        			dy = -10;
        			break;
    			}
        	}
        }
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
            	isGravity = true;
        	}
        	else {
        		currentIconType = "leftIdle";
            	currentIconNumber = 0;
            	isGravity = true;
        	}
        }
	}
	
	public SpawnPoint getSpawnPoint() {return sp;}
	public int getDX() { return dx; }
    public int getDY() { return dy; }
    public int getX() {return label.getX();}
    public int getY() {return label.getY();}
    public boolean isAirbourne() { return isAirbourne; }
    //FOR USE IN THE CONTROLLING DISPLAY CLASS WITH TIMER
    public void setDY(int dY) { this.dy = dY; }
    public void setDX(int dX) {this.dx = dX;}
    public Tile getInventory() {return inventorySlot;}
	public void setSpawnPoint(SpawnPoint sp) {this.sp = sp;}
	public void setInventory(Tile t) {inventorySlot = t;}
	public void clearInventory() {inventorySlot = null;}
	public void invertDY() {dy = -1*dy;}
	public boolean isGravity() {return isGravity;}
	//sets didJump to false and allows gravity to work
	public void smackdown() {isGravity = true;}
	public void canJumpFalse() {canJump = false;}
	public void canJumpTrue() {canJump = true;}
	
	/*
	 * THIS IS WHERE WE ARE HANDLING COLLISIONS
	 */
	
	public boolean canMoveDown() {return canMoveDown;}
	public void setCanMoveDown(boolean b) {canMoveDown = b;}
	public void setCanMoveUp(boolean b) {canMoveUp = b;}
	public void setCanMoveLeft(boolean b) {canMoveLeft = b;}
	public void setCanMoveRight(boolean b) {canMoveRight = b;}
	
	
	public Rectangle getHitBox() {
		return hitBox;
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
