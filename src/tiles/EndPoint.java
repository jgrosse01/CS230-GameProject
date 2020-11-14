package tiles;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EndPoint extends Tile {

	public EndPoint(int x, int y, BufferedImage sprite, JPanel pane) {
		super(x, y, sprite, pane);
		
	}

	static BufferedImage texture;
	static {
		try { 
			texture = ImageIO.read(new File("src/sprites/leftRun (9).png")); 
		}
		catch(java.io.IOException e) { 
			e.printStackTrace();
		}
	}

}
