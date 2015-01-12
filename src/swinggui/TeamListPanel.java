package swinggui;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import libraryClasses.Team;

@SuppressWarnings("serial")
public class TeamListPanel extends JPanel {
	
	private ArrayList<Team> theTeams;
	private MouseListener changeTeamListener;
	private ArrayList<TeamScrollPanel> selections;
	private int oldSelection;
	
	public TeamListPanel(ArrayList<Team> teamList, MouseListener detailRefresher) {
		theTeams = teamList;
		changeTeamListener = detailRefresher;
		oldSelection = 0;
		selections = new ArrayList<TeamScrollPanel>();
		
		initUI();
	}
	
	public final void initUI() {
		
		setOpaque(false);
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Teams Title Panel
		JPanel title = new JPanel();
		JLabel titleLabel = new JLabel("Teams");
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160,160,160)));
		title.add(titleLabel);
		add(title);
		
		// Team name font
		Font fontTeam = new Font("Avenir", Font.ROMAN_BASELINE, 16);
		
		JPanel teamPaneContent = new JPanel();
		teamPaneContent.setLayout((new BoxLayout(teamPaneContent, BoxLayout.Y_AXIS)));
		
		
		// Iterate through the teams
		for(int i = 0; i < theTeams.size(); i++) {
			// Index to keep track of team (for refresh)
			TeamScrollPanel nextTeam = new TeamScrollPanel(i, changeTeamListener);
			selections.add(nextTeam);
			nextTeam.setLayout(new BorderLayout());
			nextTeam.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200, 200, 200)));
			
			// Team name
			JLabel teamNameLabel = new JLabel(theTeams.get(i).getTeamName(), JLabel.CENTER);
			teamNameLabel.setFont(fontTeam);
			teamNameLabel.setMinimumSize(new Dimension(20,50));
			teamNameLabel.setPreferredSize(new Dimension(90, 50));
			teamNameLabel.setMaximumSize(new Dimension(100,50));
			
			nextTeam.add(teamNameLabel, BorderLayout.CENTER);
			
			// Add to table content
			teamPaneContent.add(nextTeam);
		}
		
		// Select first team
		selections.get(0).toggleSelected();
		
		// Add content to scroll pane and add to view
		JScrollPane scrollPane = new JScrollPane(teamPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(17);
		
		add(scrollPane);
		
		// Specify panel dimensions
		setMinimumSize(new Dimension(100,500));
		setPreferredSize(new Dimension(450,550));
		setMaximumSize(new Dimension(900,612));
	}
	
	public void newSelection(int newIndex) {
		selections.get(oldSelection).toggleSelected();
		selections.get(newIndex).toggleSelected();
		oldSelection = newIndex;
	}
}