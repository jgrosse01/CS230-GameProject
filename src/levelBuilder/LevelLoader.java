package levelBuilder;

public class LevelLoader {

	static levelInfo level;
	
	public static levelInfo load(String[] levelList) { 
		
		level = new levelInfo(width, height, fileLoaded);
		return level;
	}
}
