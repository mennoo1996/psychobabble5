package swinggui;

import java.awt.Dimension;
import java.awt.Component;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import libraryClasses.Competition;
import libraryClasses.CompetitionScheme;
import libraryClasses.Match;
import libraryClasses.Team;

public class MatchPanel extends JPanel {
	
	private Competition cComp;
	private Team cTeam;
	
	public MatchPanel(Competition currentCompetition, Team currentTeam) {
		cComp = currentCompetition;
		cTeam = currentTeam;
		
		initUI();
	}
	
	public final void initUI() {
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// Fetch match information
		CompetitionScheme curScheme = cComp.getScheme();
		ArrayList<Match> roundMatches = curScheme.getRound(cComp.getRoundsPlayed()).getMatches();
		
		Match curMatch = null;
		for(Match aMatch: roundMatches) {
			if (aMatch.getTeam1().equals(cTeam.getTeamName()) || aMatch.getTeam2().equals(cTeam.getTeamName())) {
				curMatch = aMatch;
			}
		}
		
		// Left team
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		JPanel namePanel1 = new JPanel();
		JLabel teamLabel1 = new JLabel(curMatch.getTeam1());
		teamLabel1.setMinimumSize(new Dimension(0,40));
		teamLabel1.setPreferredSize(new Dimension(teamLabel1.getPreferredSize().width, 40));
		teamLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		namePanel1.add(teamLabel1);
		
		leftPanel.add(namePanel1);
		
		JPanel scorePanel1 = new JPanel();
		JLabel scoreLabel1 = new JLabel(String.valueOf(curMatch.getScoreTeam1()));
		scoreLabel1.setMinimumSize(new Dimension(0,40));
		scoreLabel1.setPreferredSize(new Dimension(scoreLabel1.getPreferredSize().width, 40));
		scoreLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		scorePanel1.add(scoreLabel1);
		
		leftPanel.add(scorePanel1);
		
		add(leftPanel);
		
		// Center labels panel
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		JPanel teamDivider = new JPanel();
		JLabel versus = new JLabel("vs");
		versus.setMinimumSize(new Dimension(0,40));
		versus.setPreferredSize(new Dimension(versus.getPreferredSize().width, 40));
		versus.setAlignmentX(Component.CENTER_ALIGNMENT);
		teamDivider.add(versus);
		
		centerPanel.add(teamDivider);
		
		JPanel scoreDivider = new JPanel();
		JLabel scoreDash = new JLabel("-");
		scoreDash.setMinimumSize(new Dimension(0,40));
		scoreDash.setPreferredSize(new Dimension(scoreDash.getPreferredSize().width, 40));
		scoreDash.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreDivider.add(scoreDash);
		
		centerPanel.add(scoreDivider);
		
		add(centerPanel);
		
		// Right team
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JPanel namePanel2 = new JPanel();
		JLabel teamLabel2 = new JLabel(curMatch.getTeam2());
		teamLabel2.setMinimumSize(new Dimension(0,40));
		teamLabel2.setPreferredSize(new Dimension(teamLabel2.getPreferredSize().width, 40));
		teamLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		namePanel2.add(teamLabel2);
		
		rightPanel.add(namePanel2);
		
		JPanel scorePanel2 = new JPanel();
		JLabel scoreLabel2 = new JLabel(String.valueOf(curMatch.getScoreTeam2()));
		scoreLabel2.setMinimumSize(new Dimension(0,40));
		scoreLabel2.setPreferredSize(new Dimension(scoreLabel2.getPreferredSize().width, 40));
		scoreLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		scorePanel2.add(scoreLabel2);
		
		rightPanel.add(scorePanel2);
		
		add(rightPanel);
	}
}
