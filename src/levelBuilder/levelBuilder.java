package levelBuilder;

import javax.swing.*;
import entities.Entity;
import main.gameController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import tiles.*;

public class levelBuilder extends JPanel{
	//JPanel wanted this
	private static final long serialVersionUID = 2370201629065856700L;
	
	private JPanel tileSelect; //For holding all the swing objects that select the tile to place
	private JPanel levelPanel; //for displaying level
	private JComboBox tileCB; //For selecting specific tile in your tile group
	private ButtonGroup radioButtons; //For selecting a tile group i.e. tiles, entities, puzzles
	private Tile[][] levelArray;
	private levelInfo level;
	private gameController thisFrame;
	private Point levelLoc; //location of level, used for dragging frame around
	
	private Tile[] tileArray;
	private Tile tileSelected;
	private JRadioButton radioAdd;
	private JRadioButton radioDelete;
	
	private boolean isDefaultPlaced = false;
	
	public levelBuilder(gameController frame) {
		//Creating the class variables
		tileSelect = new JPanel();
		tileSelect.setLayout(null);
		levelPanel = new JPanel();
		levelPanel.setLayout(null);
		makeTileArray();
		
		//Using blockdimension for consistent spacing when making buttons
		//and frames
		int Sp = frame.getBlockDimension();
		int Hsp = (int)(Sp/2);
		
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
		
		this.setLayout(null);
		this.setVisible(true);
		
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
				try {
				saveLevel();
				}
				catch (IOException err) {
					err.printStackTrace();
				}
			}
		});
		JButton loadButton = new JButton("Load Level");
		
		//combobox for selecting the tiles and label for combobox
		//the string arrays need to be manually edited when we
		//add new tiles.
		JLabel cbLabel = new JLabel("Tile Select");
		tileCB = new JComboBox(tileArray);
		

		radioAdd = new JRadioButton("Add");
		radioDelete = new JRadioButton("Delete");

		radioButtons = new ButtonGroup();
		radioButtons.add(radioAdd);
		radioButtons.add(radioDelete);
		radioAdd.setSelected(true);
		
		
		//formatting tileSelect and then adding it to the Panel
		tileSelect.setBorder(BorderFactory.createTitledBorder("Tile Select"));
		tileSelect.setVisible(true);
		this.add(tileSelect);
		tileSelect.setBounds(0,0,frame.getWidth(),(int)(frame.getHeight()*.15));
		//Adding combobox and buttons to the tileSelect and making bounds
		tileSelect.add(exitButton); exitButton.setBounds(Hsp,Hsp,Hsp*3,Hsp);
		tileSelect.add(newButton); newButton.setBounds(Hsp*5,Hsp,Hsp*3,Hsp);
		tileSelect.add(saveButton); saveButton.setBounds(Hsp*8,Hsp,Hsp*3,Hsp);
		tileSelect.add(loadButton); loadButton.setBounds(Hsp*11,Hsp,Hsp*3,Hsp);
		tileSelect.add(tileCB); tileCB.setBounds(Hsp*15,Hsp,Sp*2,Hsp);
		tileSelect.add(cbLabel); cbLabel.setBounds((Hsp*19)+10,Hsp,Sp,Hsp);
		tileSelect.add(radioAdd); radioAdd.setBounds(Hsp*21,Hsp,Sp,Hsp);
		tileSelect.add(radioDelete); radioDelete.setBounds(Hsp*23,Hsp,Sp,Hsp);
		tileCB.setVisible(true);
		
		//^^ but for level object
		//level.setLayout(new FlowLayout());
		this.add(levelPanel, BorderLayout.CENTER);
		levelPanel.setBackground(Color.black);
		levelPanel.setVisible(true);
		
		//Creating combo boxes for tileSelect. Needs to be integrated with
		//all the different tiles when we have them made.
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
			levelArray = new Tile[width][height];
			paintLevel(levelPanel.getGraphics());
		}
	}
	
	private void saveLevel() throws java.io.IOException{
		String levelName = JOptionPane.showInputDialog("Level Name");
		
		File newLevel = new File(levelName + ".txt");
		if(newLevel.createNewFile()) {
			FileWriter writer = new FileWriter(levelName + ".txt");
			writer.write(Integer.toString(levelArray.length)+":"+levelArray[0].length+"\n");
			for(int i = 0; i < levelArray[0].length; i++) {
				for(int j = 0; j < levelArray.length; j++) {
					if(levelArray[j][i]==null) {
						writer.write("null ");
					} else if (levelArray[j][i] instanceof SpawnPoint) {
						writer.write("SpawnPoint:" + ((SpawnPoint) levelArray[j][i]).isCurrent()+" ");
					}
					else {
					writer.write(levelArray[j][i].toString() + " ");
					}
				}
				writer.write("\n");
			}
			writer.close();
		} else {
			System.out.println("There is already a level with that name");
		}
	}
	
	private void paintLevel(Graphics g)
	{
		int tileSpacing = 1;
		int tileWidth = thisFrame.getBlockDimension();
		levelPanel.setVisible(false);
		int width = levelArray.length;
		int height = levelArray[0].length;
		levelPanel.setBounds(0,tileSelect.getHeight(),(tileWidth+tileSpacing)*width+tileSpacing,(tileWidth+tileSpacing)*height+tileSpacing);
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				JButton tileIcon = new JButton();
				tileIcon.setActionCommand(null);
				tileIcon.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tilePressed(e.getSource());
					}
				});
				tileIcon.setBorderPainted(false);
				if(levelArray[j][i]==null) {
					tileIcon.setBackground(Color.white);
					tileIcon.setOpaque(true);
				}
				tileIcon.setBounds(tileSpacing+(j*(tileWidth+tileSpacing)),tileSpacing+(i*(tileWidth+tileSpacing)),tileWidth,tileWidth);
				levelPanel.add(tileIcon);
			}
		}
		levelPanel.setVisible(true);
	}
	
	//What happens when one of the tiles is pressed.
	private void tilePressed(Object e) {
		if(e instanceof JButton) {
			JButton button = (JButton)e;
			int x = button.getX();
			int y = button.getY(); 
			Object item = tileCB.getSelectedItem();
			if(item instanceof Tile) {
				Tile tile = (Tile)item;
				if(radioAdd.isSelected()) {
					if(tile instanceof SpawnPoint) {
						if(!isDefaultPlaced) {
							((SpawnPoint) tile).toggleIsCurrent();
							isDefaultPlaced=true;
						}
					}
					levelArray[x/thisFrame.getBlockDimension()][y/thisFrame.getBlockDimension()] = tile;
					ImageIcon icon = resizeTile(tile.getImage());
					button.setIcon(icon);
					button.repaint();
					button.setActionCommand(tile.toString());
				}
				if(radioDelete.isSelected()) {
					button.setIcon(null);
					button.repaint();
					button.setActionCommand(null);
				}
			}
		}
	}
	
	//Tile array for selecting tiles to place,
	//needs to be manually updated for every new tile.
	//we can set coords to 0 for all because we are only
	//using these for reference.
	private void makeTileArray() {
		tileArray = new Tile[2];
		tileArray[0] = new Dirt(0,0,levelPanel);
		tileArray[1] = new SpawnPoint(0,0,levelPanel);
	}
	
	private ImageIcon resizeTile(BufferedImage img) {
		Image imgResize = img.getScaledInstance(thisFrame.getBlockDimension(), thisFrame.getBlockDimension(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(imgResize);
		return icon;
	}
	
}
