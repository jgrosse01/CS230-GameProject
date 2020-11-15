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
		levelArray = new Tile[width][height];
		//Split by spaces to get tiles
		for (int i = 0; i < height; i++) {
			lineSplit = reader.nextLine().split(" ");
			for (int j = 0; j < lineSplit.length; j++) {
				if (lineSplit[j].contains(":")) {
					int temp = lineSplit[j].indexOf(":");
					String temp1 = lineSplit[j].substring(0,temp); //String with Tile name
					String temp2 = lineSplit[j].substring(temp+1); //String with Options
					levelArray[i][j] = stringToTile(temp1,i,j,pane,temp2);
				}
				else {
					levelArray[i][j] = stringToTile(lineSplit[j],i,j,pane);
				}
			}
		}
		reader.close();
		return new levelInfo(levelArray, pane);
		
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
		case "EndPoint": return new EndPoint(x,y,pane);
		}

		return null;
	}
	
	//stringToTile for tiles that have extra options that we need to know
	private static Tile stringToTile(String string, int x, int y, JPanel pane, String options) {
		String[] splitOptions = new String[3];
		//for puzzle {name, typeID, specialID}
		//for spawnpoint options = boolean T/F
		if (options.contains(":")) {
			splitOptions = options.split(":");
		}
		switch(string) {
		case "SpawnPoint": return new SpawnPoint(x,y,pane,(options.equals("true") ? true : false));
		/*case "PuzzleItem":
			switch(splitOptions[0]) {
			case "Key": return new Key(x,y,pane);
			case "PressurePlate": return new PressurePlate(x,y,pane);
			case "PedestalObject": return new PedestalObject(x,y,pane);
			}
			*/
			
		}
		
		return null;
	}
	
	public static void main(String[] args) {

		System.out.println("Comment Ya Code, Matt... I'm lookin' at you!");
	}
} 