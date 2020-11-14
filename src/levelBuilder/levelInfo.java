package levelBuilder;

import javax.swing.JPanel;

import tiles.Tile;

public class levelInfo {
	//pixel width and height of each tile space
	private int tileDimension;
	
	private Tile endPoint;
	
	/**
	 * Commented out because we don't have these classes
	 * at the time of writing.
	 */
	//public PuzzleInteractable[] allPuzzles
	public Tile[][] levelLayout;
	
	/**
	 * Will probably need to call level loader and need a filename
	 */
	public levelInfo(Tile[][] layout, JPanel pane) {
		this.levelLayout = layout;
	}
	
	/**
	 * Calculates the array location in which a tile needs to be placed and places the tile
	 * @param x mouse x location
	 * @param y mouse y location
	 */
	public void place(int x, int y, Tile tile) {
		levelLayout[x/tileDimension][y/tileDimension] = tile;
	}
	
	/**
	 * Checks the allPuzzles variable and returns if
	 * all have been solved or not
	 * @return
	 */
	public boolean didPass() {
		//commented out because we don't have these classes/variables yet
//		boolean flag = true;
//		for(int i = 0; i < allPuzzles.length; i++)
//		{
			/**The "isPassed" is a placeholder name
			 * we need to check if the puzzle object has been passed */
//			if(allPuzzles[i].isPassed == false)
//			{
//				flag = false;
//				break;
//			}
//		}
//		return flag;
		//This is just here to avoid spitting errors right now
		return true;
	}
	
	public void setLevel(Tile[][] levelArray) {
		levelLayout = levelArray;
	}
	
	public void drawLevel() {
		
	}
}
