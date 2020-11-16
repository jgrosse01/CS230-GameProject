package tiles;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EndPoint extends Tile {
	
	static BufferedImage texture;
	static {
		try { 
			texture = ImageIO.read(new File("src/sprites/DoorOpen.png")); 
		}
		catch(java.io.IOException e) { 
			e.printStackTrace();
		}
	}

	public EndPoint(int x, int y, JPanel pane) {
		super(x, y, texture, pane);
	}
	
	public String toString() {
		return "EndPoint";
	}

}
