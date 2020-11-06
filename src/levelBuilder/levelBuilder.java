package levelBuilder;

import javax.swing.*;

import main.gameController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import tiles.*;

public class levelBuilder extends JPanel{
	//JPanel wanted this
	private static final long serialVersionUID = 2370201629065856700L;
	
	private JPanel tileSelect; //For holding all the swing objects that select the tile to place
	private JPanel levelPanel;
	private JComboBox tileCB; //For selecting specific tile in your tile group
	private ButtonGroup radioButtons; //For selecting a tile group i.e. tiles, entities, puzzles
	private String[][] levelArray;
	private gameController thisFrame;
	private Point levelLoc;
	
	public levelBuilder(gameController frame) {
		//Creating the class variables
		tileSelect = new JPanel();
		tileSelect.setLayout(new GridBagLayout());
		levelPanel = new JPanel();
		levelPanel.setLayout(null);
		//making level panel clickable
		levelPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				levelLoc = e.getPoint();
			}
		});
		//making level panel draggable
		levelPanel.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point currentScreenLoc = e.getLocationOnScreen();
				levelPanel.setLocation(currentScreenLoc.x - levelLoc.x, currentScreenLoc.y - levelLoc.y);
			}
		});
		
		thisFrame = frame;
		
		this.setBackground(Color.black);
		this.setLayout(null);
		this.setVisible(true);
		
		//Radio buttons for selecting tile groups
		JRadioButton radioTile = new JRadioButton("Tiles");
		JRadioButton radioPuzzle = new JRadioButton("Puzzles");
		JRadioButton radioEntity = new JRadioButton("Entities");
		radioButtons = new ButtonGroup();
		radioButtons.add(radioTile);
		radioButtons.add(radioPuzzle);
		radioButtons.add(radioEntity);
		
		//Making and buttons and adding listeners
		JButton exitButton = new JButton("Main Menu");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.builderToMain();
			}
		});
		JButton newButton = new JButton("New Level");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					makeNewLevel();
			}		
		});
		JButton saveButton = new JButton("Save Level");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveLevel();
			}
		});
		
		//combobox for selecting the tiles
		String[] tiles = {"1", "2", "3", "4"}; //using strings for testing
		tileCB = new JComboBox(tiles);
		tileCB.setSelectedIndex(0);
		
		//formatting tileSelect and then adding it to the Panel
		//tileSelect.setLayout(new FlowLayout());
		tileSelect.setBorder(BorderFactory.createTitledBorder("Tile Select"));
		tileSelect.setVisible(true);
		//tileSelect.setBounds(0,0,thisFrame.getWidth(), (int)(thisFrame.getHeight()*.15));
		this.add(tileSelect, BorderLayout.NORTH);
		tileSelect.setBounds(0,0,frame.getWidth(),(int)(frame.getHeight()*.15));
		//Adding combobox and buttons to the tileSelect
		tileSelect.add(exitButton);
		tileSelect.add(newButton);
		tileSelect.add(saveButton);
		tileSelect.add(tileCB);
		tileSelect.add(radioTile);
		tileSelect.add(radioPuzzle);
		tileSelect.add(radioEntity);
		radioTile.setSelected(true);
		tileCB.setVisible(true);
		
		//^^ but for level object
		//level.setLayout(new FlowLayout());
		this.add(levelPanel, BorderLayout.CENTER);
		levelPanel.setBackground(Color.red);
		levelPanel.setVisible(true);
		
		//Creating combo boxes for tileSelect. Needs to be integrated with
		//all the different tiles when we have them made.
		tileCB = new JComboBox();
	}
	
	//this will populate the levelArray for use in making a level.
	//It is called when the "new level" button is pushed.
	private void makeNewLevel()
	{
		//Creating a panel with two text fields for the popup message
		JTextField widthField = new JTextField(3);
		JTextField heightField = new JTextField(3);
		int width;
		int height;
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("Width: "));
		panel.add(widthField);
		panel.add(new JLabel("Height: "));
		panel.add(heightField);
		
		int response = JOptionPane.showConfirmDialog(null, panel, "Width and height in tiles", 
				JOptionPane.OK_CANCEL_OPTION);
		if(response == JOptionPane.OK_OPTION)
		{
			width = Integer.parseInt(widthField.getText());
			height = Integer.parseInt(heightField.getText());
			levelArray = new String[width][height];
			paintLevel(levelPanel.getGraphics());
		}
	}
	
	private void saveLevel() {
		
	}
	
	private void paintLevel(Graphics g)
	{
		//The 5's and 50's are arbitrary, we need to decide
		//what size we wan't tiles to be
		int tileSpacing = 5;
		int tileWidth = 50;
		levelPanel.setVisible(false);
		int width = levelArray.length;
		int height = levelArray[0].length;
		levelPanel.setBounds(0,tileSelect.getHeight(),(tileSpacing+tileWidth)*width+tileSpacing,(tileSpacing+tileWidth)*height+tileSpacing);
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				JLabel label = new JLabel();
				label.setBackground(Color.white);
				label.setOpaque(true);
				label.setBounds(tileSpacing+(j*(tileWidth+tileSpacing)),tileSpacing+(i*(tileWidth+tileSpacing)),tileWidth,tileWidth);
				levelPanel.add(label);
			}
		}
		levelPanel.setVisible(true);
}
}
