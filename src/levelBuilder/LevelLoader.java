package levelBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import main.gameController;

import javax.swing.JPanel;

import tiles.Dirt;
import tiles.Tile;

public class LevelLoader {

	public static levelInfo load(String levelString, JPanel pane) { 
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
		} catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	//stringToTile for "normal" tiles
	private static Tile stringToTile(String string, int x, int y, JPanel pane) {
		if(string == "Dirt") {return new Dirt(x,y,pane);};

		return null;
	}
	
	//stringToTile for tiles that have extra options that we need to know
	private static Tile stringToTile(String string, int x, int y, JPanel pane, String options) {
		if(string == "SpawnPoint") {
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("yo");
	}
} 
