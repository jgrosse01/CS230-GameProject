package tiles;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Player;

public class SpawnPoint extends Tile implements InteractableTile{
	
	private boolean isCurrent;
	
	static BufferedImage texture;
	static {
		try { 
			texture = ImageIO.read(new File("sprites/NEEDIMAGES")); 
		}
		catch(java.io.IOException e) { 
			e.printStackTrace();
		}
	}

	public SpawnPoint(int x, int y,JPanel pane) {
		super(x, y, texture, pane, false);
		isCurrent = false;
	}
	
	public SpawnPoint(int x, int y,JPanel pane, boolean isDefault) {
		super(x, y, texture, pane, false);
		isCurrent = isDefault;
	}
	
	public void toggleIsCurrent() {
		isCurrent = !(isCurrent);
		this.swapImage();
	}
	
	//Have one activated image and one unactivated image
	public void swapImage() {
		//image is now other image or vice versa
	}

	@Override
	public void use(Player player) {
		player.getSpawnPoint().toggleIsCurrent();
		this.toggleIsCurrent();
		player.setSpawnPoint(this);
	}
	
}
