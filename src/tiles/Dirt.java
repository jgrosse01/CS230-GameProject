package tiles;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Dirt extends Tile{
	
	static BufferedImage dirtTexture;
	static {
		try { 
			dirtTexture = ImageIO.read(new File("sprites/dirt2.png")); 
		}
		catch(java.io.IOException e) { 
			e.printStackTrace();
		}
	}
	
	//THERE IS NO NULL CHECK, WE MUST DETERMINE BEFORE HAND WHERE THE IMAGE IS
	public Dirt(int x, int y, JPanel jpane) {
		super(x, y, dirtTexture, jpane, true);
	}
	
}
