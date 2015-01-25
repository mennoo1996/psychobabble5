/**
 * GUI Class that displays a team's rating, players, and budget, and allows the player to select said team
 */
package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import libraryClasses.FieldPlayer;
import libraryClasses.Goalkeeper;
import libraryClasses.Player;
import libraryClasses.Team;
import gameLogic.TeamRating;

@SuppressWarnings("serial")
public class TeamDetailPanel extends JPanel {
	
	Team detailTeam;
	ActionListener playListener;
	ArrayList<Player> players;
	
	/**
	 * Create and initialize a TeamDetailPanel
	 * @param selTeam the selected team
	 * @param getPlayingListener Event listener for if the player decides to create a new game with this team
	 */
	public TeamDetailPanel(Team selTeam, ActionListener getPlayingListener) {
		detailTeam = selTeam;
		playListener = getPlayingListener;
		players = detailTeam.getTeam();
		
		initUI();
	}
	
	/**
	 * Initialize GUI elements contained in the TeamDetailPanel
	 */
	public final void initUI() {
		
		setBackground(Color.WHITE);
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Panel name is team name
		JPanel title = new JPanel();
		JLabel titleLabel = new JLabel(detailTeam.getTeamName());
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		title.add(titleLabel);
		add(title);
		
		//
		// Details panel
		//
		JPanel detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
		
		// Statistics view
		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		
		//budget calculation
		BigDecimal bd = new BigDecimal(detailTeam.getBudget(), MathContext.DECIMAL64);
		//BigDecimal bd = detailTeam.getBudget();
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(false);		
		String priceString = formatter.format(bd.longValue());
		
		// List budget and team rating
		JLabel ratingLabel = new JLabel("Rating:", JLabel.CENTER);
		ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel actualRating = new JLabel("" + TeamRating.calculateTeamRating(detailTeam).getTotal(), JLabel.CENTER);
		actualRating.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel budgetLabel = new JLabel("Budget:", JLabel.CENTER);
		budgetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel actualBudget = new JLabel("$" + priceString, JLabel.CENTER);
		actualBudget.setAlignmentX(Component.CENTER_ALIGNMENT);
		statsPanel.add(ratingLabel);
		statsPanel.add(actualRating);
		statsPanel.add(budgetLabel);
		statsPanel.add(actualBudget);
		
		statsPanel.setMinimumSize(new Dimension(150, 50));
		statsPanel.setPreferredSize(new Dimension(150, 50));
		detailsPanel.add(new Box.Filler(new Dimension(10, 20), null, null));
		detailsPanel.add(statsPanel);
		detailsPanel.add(new Box.Filler(new Dimension(10, 20), null, null));
		
		// Player list view
		Font fontSeparator = new Font("Avenir", Font.ROMAN_BASELINE, 12);
		Font fontPlayername = new Font("Avenir", Font.ROMAN_BASELINE, 16);
		Font fontPlayerattr = new Font("Avenir", Font.ROMAN_BASELINE, 11);
		
		//top level JPanel holds all content for the scrollable.
		JPanel ScrollPaneContent = new JPanel();
		ScrollPaneContent.setLayout(new BoxLayout(ScrollPaneContent, BoxLayout.Y_AXIS));
		
		//player types
		String[] PlayerTypes = {
				"Attacker",
				"Midfielder",
				"Defender",
				"Goalkeeper"
		};
		
		//loop through player types
		for(int q = 0; q < 4; q++){
			
			//add a separator
			JPanel separator = new JPanel(); 
			separator.setMinimumSize(new Dimension(0,25));
			//separator.setPreferredSize(new Dimension(separator.getWidth(), 25));
			separator.setOpaque(true);
			separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
			separator.setBackground(new Color(230,230,230));
			
			//separator title
			JLabel separatortitle = new JLabel(PlayerTypes[q] + "s");
			separatortitle.setFont(fontSeparator);
			separator.add(separatortitle);
			ScrollPaneContent.add(separator);
			
			//Grid panel to hold all players of current type
			JPanel ScrollPaneGrid = new JPanel();
			ScrollPaneGrid.setLayout(new GridLayout(0,1));
			
			//add all players of current type to Grid panel
			for(int w = 0; w < players.size(); w++){
				if(players.get(w).getPlayerType().toString() == PlayerTypes[q]){
					
					//panel to hold player information. Also draggable element
					ListPanel Playerpanel = new ListPanel(players.get(w));
					Playerpanel.setLayout(new BorderLayout());
					Playerpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200,200,200)));
					
					//name label
					JLabel label2 = new JLabel("  " + players.get(w).getName());
					label2.setFont(fontPlayername);
					label2.setMinimumSize(new Dimension(100,50));
					label2.setPreferredSize(new Dimension(100, 50));
					label2.setMaximumSize(new Dimension(100,50));
					//attributes
					JPanel label3 = new JPanel();
					label3.setMinimumSize(new Dimension(100, label3.getSize().height));
					label3.setPreferredSize(new Dimension(100, label3.getSize().height));
					label3.setMaximumSize(new Dimension(100, label3.getSize().height));
					if(PlayerTypes[q] != "Goalkeeper"){
						FieldPlayer player = (FieldPlayer) players.get(w);
						label3.setLayout(new GridLayout(2,2));
						
						int[] Attr = {	player.getDefenseValue(),
										player.getDribblingValue(),
										player.getFinishingValue(),
										player.getStaminaValue()
						};
						JLabel[] AttrLabel = {	new JLabel("Def: " + Integer.toString(Attr[0])),
												new JLabel("Dri: " + Integer.toString(Attr[1])),
												new JLabel("Fin: " + Integer.toString(Attr[2])),
												new JLabel("Sta: " + Integer.toString(Attr[3]))		
						};	
						
						for(int e = 0; e < 4; e++){
							AttrLabel[e].setFont(fontPlayerattr);
							label3.add(AttrLabel[e]);
						}
					}
					else{
						Goalkeeper player = (Goalkeeper) players.get(w);
						label3.setLayout(new BoxLayout(label3, BoxLayout.X_AXIS));
						JLabel attr = new JLabel("Stat: " + player.getGoalkeeperValue());
						attr.setFont(fontPlayerattr);
						label3.add(attr);
					}
					
					//finish up
					Playerpanel.add(label2, BorderLayout.CENTER);
					Playerpanel.add(label3, BorderLayout.EAST);
					ScrollPaneGrid.add(Playerpanel);
					
				}
			}
			
			ScrollPaneContent.add(ScrollPaneGrid);
			
		}
		
		JScrollPane ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		ScrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(180,180,180)));
		//ScrollPane.setMinimumSize(new Dimension(100,300));
		//ScrollPane.setPreferredSize(new Dimension(400,500));
		
		detailsPanel.add(ScrollPane);
		setMinimumSize(new Dimension(400,580));
		setPreferredSize(new Dimension(600,580));
		setMaximumSize(new Dimension(900,580));
		
		add(detailsPanel);
		
		//
		// Finalize selection panel
		//
		JPanel finishPanel = new JPanel();
		finishPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180,180,180)));
		JButton finalizeSelection = new JButton("Play as this team");
		finalizeSelection.setName("Test");
		finalizeSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
		finalizeSelection.putClientProperty("teamName", detailTeam.getTeamName());
		finalizeSelection.addActionListener(playListener);
		
		finishPanel.add(finalizeSelection, BorderLayout.CENTER);
		
		add(finishPanel);
	}
}
