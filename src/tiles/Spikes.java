package tiles;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Player;

public class Spikes extends Tile {
	
	static BufferedImage spikeTexture;
	static {
		try { 
			spikeTexture = ImageIO.read(new File("src/sprites/Spike.png")); 
		}
		catch(java.io.IOException e) { 
			e.printStackTrace();
		}
	}
	
	//THERE IS NO NULL CHECK, WE MUST DETERMINE BEFORE HAND WHERE THE IMAGE IS
	public Spikes(int x, int y, JPanel jpane) {
		super(x, y, spikeTexture, jpane, true);
	}
	
	public String toString() {
		return "Spikes";
	}
}
