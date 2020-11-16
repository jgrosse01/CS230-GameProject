package tiles;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Player;

public class Acid extends Tile {

	static BufferedImage acidTexture;
	static {
		try { 
			acidTexture = ImageIO.read(new File("src/sprites/Acid (1).png")); 
		}
		catch(java.io.IOException e) { 
			e.printStackTrace();
		}
	}
	
	//THERE IS NO NULL CHECK, WE MUST DETERMINE BEFORE HAND WHERE THE IMAGE IS
	public Acid(int x, int y, JPanel jpane) {
		super(x, y, acidTexture, jpane, true);
	}
	
	public String toString() {
		return "Acid";
	}

}
