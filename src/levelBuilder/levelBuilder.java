package levelBuilder;

import javax.swing.*;
import java.awt.*;

public class levelBuilder extends JPanel{
	//JPanel wanted this
	private static final long serialVersionUID = 2370201629065856700L;
	
	private JPanel tileSelect; //For holding all the swing objects that select the tile to place
	private JPanel level; //For holding all the slots for actually placing a tile in the level
	private JComboBox tileCB;
	private JComboBox entityCB;
	private JComboBox puzzleCB;
	
	public levelBuilder() {
		//Creating the class variables
		tileSelect = new JPanel();
		level = new JPanel();
		
		this.setBackground(Color.red); //CHANGE THIS EVENTUALLY
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		//formatting tileSelect and then adding it to the Panel
		tileSelect.setLayout(new FlowLayout());
		tileSelect.setBorder(BorderFactory.createTitledBorder("Tile Select"));
		tileSelect.setVisible(true);
		this.add(tileSelect, BorderLayout.WEST);
		
		//^^ but for level object
		level.setLayout(new FlowLayout());
		this.add(level, BorderLayout.EAST);
		
		//Creating combo boxes for tileSelect. Needs to be integrated with
		//all the different tiles when we have them made.
		tileCB = new JComboBox();
	}
	
	/**
	 * This stuff is just for testing, can be removed once the main menu
	 * has functionality for accessing the level builder.
	 * @param args
	 */
	public static void main(String [] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(600,600);
        levelBuilder build = new levelBuilder();
        frame.add(build);
        build.setSize(frame.getSize());
	}
	
}
