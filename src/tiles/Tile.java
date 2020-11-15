package tiles;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import levelBuilder.levelInfo;
import main.gameController;


public class Tile {

	//start pixel x and y
	private int x;
	private int y;
	//width and height in pixels
	private int width;
	private int height;
	//is the tile visible
	private boolean visible = false;
	//current image
	private BufferedImage sprite;
	//panel tile will be on
	private JPanel currentPane;
	//determines whether tile is breakable
	boolean breakable;
	private JLabel label;
	
	//Constructor
	public Tile(int x, int y, BufferedImage sprite, JPanel pane) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		//get tile width and height from image size
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		
		//passed in the panel this is being added to
		this.currentPane = pane;
		breakable = false;
		toggleVisible();
	}
	
	public Tile(int x, int y, BufferedImage sprite, JPanel pane, boolean breakable) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		//get tile width and height from image size
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		
		//passed in the panel this is being added to
		this.currentPane = pane;
		this.breakable = breakable;
		toggleVisible();
	}
	
	//Paint to screen with graphics element from content pane
	public void draw() {
		//draw: Image, start x, start y, no observer
//		currentPane.getGraphics().drawImage(sprite, x, y, null);
		label = new JLabel();
		label.setBounds(x,y,gameController.getBlockDimension(),gameController.getBlockDimension());
		currentPane.add(label);
		Image scaleImage = sprite.getScaledInstance(gameController.getBlockDimension(), gameController.getBlockDimension(), Image.SCALE_SMOOTH);
		ImageIcon finalImage = new ImageIcon(scaleImage);
		label.setIcon(finalImage);
		label.setVisible(true);
	}
	
	//Erase from screen with graphics element from content pane
	public void erase() {
		//erase: start x, start y, image width, image height
		currentPane.getGraphics().clearRect(x, y, width, height);
	}
	
	public void toggleVisible() {
		visible = !visible;
	}
	
	public void updateImage(BufferedImage sprite) {
		this.sprite = sprite;
		this.erase();
		this.draw();
	}
	
	//breaks tile, erases and adds item to player inventory slot
	public boolean breakTile() {
		if (breakable) {
			this.erase();
			return true;
		}
		return false;
	}
	
	public void placeTile(levelInfo level) {
		int mousex = MouseInfo.getPointerInfo().getLocation().x;
		int mousey = MouseInfo.getPointerInfo().getLocation().y;
		level.place(mousex, mousey, this);
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public BufferedImage getImage() {return sprite;}
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
