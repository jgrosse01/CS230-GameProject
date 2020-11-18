package tiles;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Player;

public class DownwardSpikes extends Tile {
	
	static BufferedImage downSpikeTexture;
	static {
		try { 
			downSpikeTexture = ImageIO.read(new File("src/sprites/Spike2.png")); 
		}
		catch(java.io.IOException e) { 
			e.printStackTrace();
		}
	}
	
	//THERE IS NO NULL CHECK, WE MUST DETERMINE BEFORE HAND WHERE THE IMAGE IS
	public DownwardSpikes(int x, int y, JPanel jpane) {
		super(x, y, downSpikeTexture, jpane, true);
	}
	
	public String toString() {
		return "Downward Spikes";
	}
}
