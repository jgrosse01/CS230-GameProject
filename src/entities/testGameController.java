package entities;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.gameController;
import tiles.SpawnPoint;
import tiles.Tile;

public class testGameController {

	private BufferedImage testSprite;
    static JFrame testFrame;
    static JPanel testPane;
    static JLabel label;
    SpawnPoint sp = new SpawnPoint(50, 50, testPane);
    
    
    private JPanel tileSelect; //For holding all the swing objects that select the tile to place
	private JPanel levelPanel;
	private JComboBox tileCB; //For selecting specific tile in your tile group
	private ButtonGroup radioButtons; //For selecting a tile group i.e. tiles, entities, puzzles
	private String[][] levelArray;
	private gameController thisFrame;
	private Point levelLoc; //location of level, used for dragging frame around
	
	public Player player;
	public Tile tile;
	public BufferedImage dirtTexture;
    
    
	
	public testGameController() {
		testFrame = new JFrame();
//		testFrame.setLayout(null);
		testPane = new JPanel();
//		testPane.setLayout(null);
		sp = new SpawnPoint(50, 10, testPane);
		//Player.loadImage();
		try {
			 File file = new File("src/sprites/Idle (0).png");
			 testSprite = ImageIO.read(file);
		} catch(Exception e) {e.printStackTrace();}
		
       try {
             Player player = new Player(50, 50, testSprite, testSprite, testPane, sp);
              player.draw();
        } catch (IOException e) {e.printStackTrace();} 
       
       
	}
	
	public void scalePlayer() {
		ImageIcon imageIcon = new ImageIcon("images"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(gameController.getBlockDimension(), gameController.getBlockDimension()*2, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
	}
	
	public static void main(String[] args) {
		testGameController gc = new testGameController();
		testFrame.add(testPane);
		testFrame.setSize(1500,1500);
		testFrame.setVisible(false);
		testFrame.setVisible(true);

	}

}
