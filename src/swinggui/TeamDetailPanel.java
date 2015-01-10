package swinggui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.Box;

import java.util.ArrayList;

import libraryClasses.FieldPlayer;
import libraryClasses.Goalkeeper;
import libraryClasses.Team;
import libraryClasses.Player;

public class TeamDetailPanel extends JPanel {
	
	Team detailTeam;
	ActionListener playListener;
	ArrayList<Player> players;
	
	public TeamDetailPanel(Team selTeam, ActionListener getPlayingListener) {
		detailTeam = selTeam;
		playListener = getPlayingListener;
		players = detailTeam.getTeam();
		
		initUI();
	}
	
	public final void initUI() {
		
		setBackground(Color.WHITE);
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Panel name is team name
		JPanel title = new JPanel();
		JLabel titleLabel = new JLabel(detailTeam.getTeamName());
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
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
		
		// List budget
		JLabel budgetLabel = new JLabel("Budget:", JLabel.CENTER);
		JLabel actualBudget = new JLabel("£" + (int)detailTeam.getBudget(), JLabel.CENTER);
		statsPanel.add(budgetLabel);
		statsPanel.add(actualBudget);
		
		detailsPanel.add(statsPanel);
		detailsPanel.add(new Box.Filler(new Dimension(20, 20), null, null));
		
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
			separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(180,180,180)));
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
					Playerpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200,200,200)));
					
					//name label
					JLabel label2 = new JLabel(players.get(w).getName());
					label2.setFont(fontPlayername);
					label2.setMinimumSize(new Dimension(20,50));
					label2.setPreferredSize(new Dimension(90, 50));
					label2.setMaximumSize(new Dimension(100,50));
					//attributes
					JPanel label3 = new JPanel();
					label3.setMinimumSize(new Dimension(75, label3.getSize().height));
					label3.setPreferredSize(new Dimension(80, label3.getSize().height));
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
		//ScrollPane.setMinimumSize(new Dimension(100,300));
		//ScrollPane.setPreferredSize(new Dimension(400,500));
		
		detailsPanel.add(ScrollPane);
		detailsPanel.setMinimumSize(new Dimension(100,450));
		detailsPanel.setPreferredSize(new Dimension(100,550));
		detailsPanel.setMaximumSize(new Dimension(900,600));
		
		add(detailsPanel);
		
		//
		// Finalize selection panel
		//
		JPanel finishPanel = new JPanel();
		
		JButton finalizeSelection = new JButton("Play as this team");
		finalizeSelection.setName("Test");
		finalizeSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
		finalizeSelection.putClientProperty("teamName", detailTeam.getTeamName());
		finalizeSelection.addActionListener(playListener);
		
		finishPanel.add(finalizeSelection, BorderLayout.CENTER);
		
		add(finishPanel);
	}
}
