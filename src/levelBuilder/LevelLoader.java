package levelBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import main.gameController;

import javax.swing.JPanel;

import tiles.*;

public class LevelLoader {

	public static levelInfo load(String levelString, JPanel pane) { 
		
		//This splits first line by colon for width and height
		Tile[][] levelArray;
		levelInfo level;
		int width;
		int height;
		String line;
		File file = new File(levelString);
		try {
		Scanner reader = new Scanner(file);
		line = reader.nextLine();
		String[] lineSplit = line.split(":");
		width = Integer.parseInt(lineSplit[0]);
		height = Integer.parseInt(lineSplit[1]);
		
		//Split by spaces to get tiles
		
		} catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	//stringToTile for "normal" tiles
	private static Tile stringToTile(String string, int x, int y, JPanel pane) {
		switch (string) {
		case "Dirt": return new Dirt(x,y,pane);
		case "Metal": return new Metal(x,y,pane);
		}

		return null;
	}
	
	//stringToTile for tiles that have extra options that we need to know
	private static Tile stringToTile(String string, int x, int y, JPanel pane, String options) {
		if (options.contains(":")) {
			String[] splitOptions = options.split(":");
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("Comment Ya Code");
	}
} 
