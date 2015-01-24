/**
 * GUI Class that displays the leftmost Transfers panel
 */
package swinggui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import libraryClasses.Player;
import libraryClasses.Team;

@SuppressWarnings("serial")
public class TransfersPanel_Left extends JPanel {
	
	@SuppressWarnings("unused")
	private Team currentTeam;
	private int oldSelection;
	private ArrayList<PlayerScrollPanel_Left> selections;
	private ArrayList<Player> Team;
	private MouseListener changeTeamListener;
	private Font fontSeparator = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	
	/**
	 * Create and initialize a TransfersPanel_Left
	 * @param cTeam - current Team
	 * @param detailRefresher - MouseListener object to detect clicks
	 */
	public TransfersPanel_Left(Team cTeam, MouseListener detailRefresher) {
		currentTeam = cTeam;
		Team = cTeam.getTeam();
		changeTeamListener = detailRefresher;
		oldSelection = 0;
		selections = new ArrayList<PlayerScrollPanel_Left>();
		initUI();
	}
	
	
	/**
	 * Initialize the GUI elements contained in the NamePanel
	 */
	public final void initUI() {

		setOpaque(false);
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Team");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		titlepanel.add(title);
		add(titlepanel);

		//content begins here
		
		//top level JPanel holds all content for the scrollable.
		JPanel ScrollPaneContent = new JPanel(); ScrollPaneContent.setLayout(new BoxLayout(ScrollPaneContent, BoxLayout.Y_AXIS));
		
		//player types
		String[] PlayerTypes = {
				"Attacker",
				"Midfielder",
				"Defender",
				"Goalkeeper"
		};
		
		int index = 0;
		
		//loop through player types
		for(int q = 0; q < 4; q++){
			
			//add a separator
			JPanel separator = new JPanel(); 
			separator.setMinimumSize(new Dimension(0,25));
			separator.setOpaque(true);
			separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
			separator.setBackground(new Color(240,240,240));
			
			//separator title
			JLabel separatortitle = new JLabel(PlayerTypes[q] + "s");
			separatortitle.setFont(fontSeparator);
			separator.add(separatortitle);
			ScrollPaneContent.add(separator);
			
			//Grid panel to hold all players of current type
			JPanel ScrollPaneGrid = new JPanel();
			ScrollPaneGrid.setLayout(new GridLayout(0,1));
			
			//add all players of current type to Grid panel
			for(int w = 0; w < Team.size(); w++){
				if(Team.get(w).getPlayerType().toString().equals(PlayerTypes[q]) ){
					
					PlayerScrollPanel_Left panel = new PlayerScrollPanel_Left(Team.get(w), PlayerTypes[q], changeTeamListener,index);
					selections.add(panel);
					ScrollPaneGrid.add(panel);
					index++;
				}
			}
			ScrollPaneContent.add(ScrollPaneGrid);
		}
		
		selections.get(0).toggleSelected();
		
		JScrollPane ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(ScrollPane);
		
		//content ends here
		
		setMinimumSize(new Dimension(100,580));
		setPreferredSize(new Dimension(450,580));
		setMaximumSize(new Dimension(900,580));
	}
	
	/**
	 * Return Player at index
	 * @param index - index at which to find Player
	 * @return Player object at index
	 */
	public Player getPlayer(int index){
		return selections.get(index).getPlayer();
	}
	
	/**
	 * Set new selection at index, if an old one already exists
	 * @param newIndex - index at which to set selection
	 */
	public void newSelection(int newIndex) {
		selections.get(oldSelection).toggleSelected();
		selections.get(newIndex).toggleSelected();
		oldSelection = newIndex;
	}
	
	/**
	 * Turn off all selections
	 */
	public void noSelection(){
		selections.get(oldSelection).deselect();
		oldSelection = 0;
	}
	
	/**
	 * Set new selection at index, if an old one does not yet exist
	 * @param newIndex - index at which to set selection
	 */
	public void firstSelection(int newIndex){
		selections.get(newIndex).toggleSelected();
		oldSelection = newIndex;
	}
	
	/**Function to create ImageIcons, just in case I have trouble finding them again
	 * @param path			- ImageIcon file location
	 * @return				- new ImageIcon object
	 */
	public ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, null);
		}
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	
	
}
