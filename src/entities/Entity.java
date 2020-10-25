package entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

public class Entity {
	public static final int DIR_LEFT = 0;
	public static final int DIR_RIGHT = 1;
	//integer location in pixels of top left corner
	private int x;
	private int y;
	//buffered images for left and right directional
	private BufferedImage lSprite;
	private BufferedImage rSprite;
	//entity width and height by image width and height
	private int width;
	private int height;
	//boolean is this entity visible
	private boolean  visible = false;
	//current JPanel to paint to
	private JPanel currentPane;
	private int currentDir;
	
	/**
	 * 
	 * @param x upper left corner (pixel)
	 * @param y upper right corner (pixel)
	 * @param left LEFT SPRITE (same size as right)
	 * @param right RIGHT SPRITE (same size as left)
	 * @param pane current pane
	 * @throws IOException IMAGE DIMENSIONS ARE NOT THE SAME
	 */
	public Entity(int x, int y, BufferedImage left, BufferedImage right, JPanel pane) throws IOException {
		if (left.getWidth() != right.getWidth() || left.getHeight() != right.getHeight())
			throw new java.io.IOException("Ensure image dimensions are the same");
		//set start location
		this.x = x;
		this.y = y;
		//set left and right sprite image
		this.lSprite = left;
		this.rSprite = right;
		this.width = lSprite.getWidth();
		this.height = lSprite.getHeight();
		currentDir = DIR_LEFT;
		toggleVisible();
	}
	
	/**
	 * 
	 * @param x upper left corner (pixel)
	 * @param y upper right corner (pixel)
	 * @param left LEFT SPRITE (same size as right)
	 * @param right RIGHT SPRITE (same size as left)
	 * @param pane current pane
	 * @param dir integer denoting direction; 0=Left, 1=Right
	 * @throws IOException IMAGE DIMENSIONS ARE NOT THE SAME
	 */
	public Entity(int x, int y, BufferedImage left, BufferedImage right, JPanel pane, int dir) throws IOException {
		this(x,y,left,right,pane);
		currentDir = dir;
	}
	
	//draws based on direction
	public void draw() {
		if (visible) {
			switch (currentDir) {
				case DIR_LEFT:
					currentPane.getGraphics().drawImage(lSprite, x, y, null);
				case DIR_RIGHT:
					currentPane.getGraphics().drawImage(rSprite, x, y, null);
			}
		}
	}
	
	//erases entity
	public void erase() {
		currentPane.getGraphics().clearRect(x, y, width, height);
	}
	
	//toggles whether the entity should be visible
	public void toggleVisible() {
		visible = !visible;
	}
	
	//changes the left and right images of the entity
	public void updateImages(BufferedImage left, BufferedImage right) throws IOException {
		if (left.getWidth() != right.getWidth() || left.getHeight() != right.getHeight())
			throw new java.io.IOException("Ensure image dimensions are the same");
		this.lSprite = left;
		this.rSprite = right;
		erase();
		draw();
	}
	
	//UNIMPLEMENTED //Basically if hits or hit by hazard then return true, need collision checker
	public boolean checkDie() {
		return false;
	}
	
	//Called when changing directions by control handler
	public void toggleDir() {
		if (currentDir == DIR_LEFT)
			currentDir = DIR_RIGHT;
		else
			currentDir = DIR_LEFT;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
