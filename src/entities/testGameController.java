package entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testGameController {

	private BufferedImage testSprite;
    static JFrame testFrame = new JFrame();
    static JPanel testPane = new JPanel();
	
	public testGameController() {
		 try {
             testSprite = ImageIO.read(new File("fastDolphinRight.jpg"));
       } catch(Exception e) {       e.printStackTrace();  }
       try {
             Player player = new Player(50, 50, testSprite, testSprite, testPane);
              player.draw();
        } catch (IOException e) {  e.printStackTrace(); } 
	}

	public static void main(String[] args) {
		testFrame.add(testPane);
		testGameController gc = new testGameController();

	}

}
