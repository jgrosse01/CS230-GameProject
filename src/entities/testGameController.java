package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tiles.SpawnPoint;

public class testGameController {

	private BufferedImage testSprite;
    static JFrame testFrame;
    static JPanel testPane;
    static JLabel label;
    SpawnPoint sp = new SpawnPoint(50, 50, testPane);
	
	public testGameController() {
		testFrame = new JFrame();
		testPane = new JPanel();
		sp = new SpawnPoint(50, 50, testPane);
		 try {
             //testSprite = ImageIO.read(new File("/fastDolphinRight.jpg"));
			 File file = new File("src/sprites/Run (1).png");
			 testSprite = ImageIO.read(file);
       } catch(Exception e) {       e.printStackTrace();  }
       try {
             Player player = new Player(50, 50, testSprite, testSprite, testPane, sp);
              player.draw();
        } catch (IOException e) {  e.printStackTrace(); } 
	}

	public static void main(String[] args) {
		testGameController gc = new testGameController();
		testFrame.add(testPane);
		testFrame.setSize(500,500);
		testFrame.setVisible(false);
		testFrame.setVisible(true);

	}

}
