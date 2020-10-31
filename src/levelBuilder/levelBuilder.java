package levelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class levelBuilder extends JPanel{
	//JPanel wanted this
	private static final long serialVersionUID = 2370201629065856700L;
	
	private JPanel tileSelect; //For holding all the swing objects that select the tile to place
	private JPanel levelPanel;
	private JComboBox tileCB; //For selecting specific tile in your tile group
	private ButtonGroup radioButtons; //For selecting a tile group i.e. tiles, entities, puzzles
	private String[][] levelArray;
	private JFrame thisFrame;
	private Point levelLoc;
	
	public levelBuilder(JFrame frame) {
		//Creating the class variables
		tileSelect = new JPanel();
		levelPanel = new JPanel();
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
		
		this.setBackground(Color.red); //CHANGE THIS EVENTUALLY
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
		
		//Making and adding buttons
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
		levelPanel.setBackground(Color.black);
		levelPanel.setSize(new Dimension(2000,2000));
		
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
		
		int response = JOptionPane.showConfirmDialog(null, panel, "Enter width and height for the level", 
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
		//just using this for testing now
		//drawing on level panel to test draggable
				for(int i = 0; i < 100; i ++)
				{
					JButton hey = new JButton("hello");
					hey.setBounds(10+i*40,10+i*40,30,30);
					levelPanel.add(hey);
				}
	}
	
	private void paintLevel(Graphics g)
	{
		int width = levelArray.length;
		int height = levelArray[0].length;
		g.setColor(Color.blue);
		g.fillRect(200, 200, width, height);
}
	
	/**
	 * This stuff is just for testing, can be removed once the main menu
	 * has functionality for accessing the level builder.
	 * @param args
	 */
	public static void main(String [] args) {
		JFrame frame = new JFrame();
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Testing Frame");
        levelBuilder build = new levelBuilder(frame);
        frame.add(build);
        build.setSize(frame.getSize());
        frame.revalidate();
        frame.repaint();
	}
	
}
