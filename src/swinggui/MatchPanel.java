package swinggui;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import libraryClasses.Competition;
import libraryClasses.CompetitionScheme;
import libraryClasses.Match;
import libraryClasses.Team;
import libraryClasses.Player;
import libraryClasses.FieldPlayer;

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
		
		setAlignmentY(Component.TOP_ALIGNMENT);
		
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
		leftPanel.setOpaque(true);
		leftPanel.setBackground(Color.CYAN);
		
		JPanel namePanel1 = new JPanel();
		namePanel1.setOpaque(true);
		namePanel1.setBackground(Color.YELLOW);
		JLabel teamLabel1 = new JLabel(curMatch.getTeam1());
		teamLabel1.setMinimumSize(new Dimension(0,40));
		teamLabel1.setPreferredSize(new Dimension(teamLabel1.getPreferredSize().width, 40));
		teamLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		namePanel1.add(teamLabel1);
				
		leftPanel.add(namePanel1);
		
		
		JPanel scorePanel1 = new JPanel();
		scorePanel1.setOpaque(true);
		scorePanel1.setBackground(Color.GREEN);
		JLabel scoreLabel1 = new JLabel(String.valueOf(curMatch.getScoreTeam1()));
		scoreLabel1.setMinimumSize(new Dimension(0,40));
		scoreLabel1.setPreferredSize(new Dimension(scoreLabel1.getPreferredSize().width, 40));
		scoreLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		scorePanel1.add(scoreLabel1);
		
		leftPanel.add(scorePanel1);
		
		// Goal makers and assists
		
		// Each goal maker + assist in own content pane
		JPanel goalPeeps = new JPanel();
		goalPeeps.setLayout(new BoxLayout(goalPeeps, BoxLayout.Y_AXIS));
		
		goalPeeps.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200, 200, 200)));
		
		goalPeeps.setOpaque(true);
		goalPeeps.setBackground(Color.RED);
		
		Font scorerFont = new Font("Avenir", Font.ROMAN_BASELINE, 16);
		Font assistFont = new Font("Avenir", Font.ROMAN_BASELINE, 16);
		
		for(int i = 0; i < curMatch.getScoreTeam1(); i++) {
			JLabel scoreStats = new JLabel();
			scoreStats.setLayout(new BoxLayout(scoreStats, BoxLayout.Y_AXIS));
			
			// The goal scorer
			System.out.println("Scorer: " + curMatch.getGoalMakerst1().get(i).getName());
			JLabel goalScorerLabel = new JLabel(curMatch.getGoalMakerst1().get(i).getName());
			goalScorerLabel.setMinimumSize(new Dimension(0,40));
			goalScorerLabel.setPreferredSize(new Dimension(goalScorerLabel.getPreferredSize().width, 40));
			goalScorerLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			goalScorerLabel.setFont(scorerFont);
			
			scoreStats.add(goalScorerLabel);
			
			// The assist credit
			System.out.println("Assist: " + curMatch.getAssistMakerst1().get(i).getName());
			JLabel assistLabel = new JLabel(curMatch.getAssistMakerst1().get(i).getName());
			assistLabel.setMinimumSize(new Dimension(0,40));
			assistLabel.setPreferredSize(new Dimension(assistLabel.getPreferredSize().width, 40));
			goalScorerLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			assistLabel.setFont(assistFont);
			
			scoreStats.add(assistLabel);
						
			goalPeeps.add(scoreStats);
		}
				
		leftPanel.add(goalPeeps);
		
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
		
		JPanel goalsDiv = new JPanel();
		JLabel goalLabel = new JLabel("Goals");
		goalLabel.setMinimumSize(new Dimension(0,40));
		goalLabel.setPreferredSize(new Dimension(goalLabel.getPreferredSize().width, 40));
		goalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		goalsDiv.add(goalLabel);
		
		centerPanel.add(goalsDiv);
		
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
		
		// Goal makers and assists
		
		// Each goal maker + assist in own content pane
		JPanel goalPeeps2 = new JPanel();
		goalPeeps2.setLayout(new BoxLayout(goalPeeps2, BoxLayout.Y_AXIS));

		for(int i = 0; i < curMatch.getScoreTeam2(); i++) {
			JPanel scoreStats = new JPanel();
			scoreStats.setLayout(new BoxLayout(scoreStats, BoxLayout.Y_AXIS));
			
			// The goal scorer
			JLabel goalScorerLabel = new JLabel(curMatch.getGoalMakerst2().get(i).getName());
			goalScorerLabel.setMinimumSize(new Dimension(0,40));
			goalScorerLabel.setPreferredSize(new Dimension(goalScorerLabel.getPreferredSize().width, 40));
			goalScorerLabel.setFont(scorerFont);
			
			scoreStats.add(goalScorerLabel);
						
			// The assist credit
			JLabel assistLabel = new JLabel(curMatch.getAssistMakerst2().get(i).getName());
			assistLabel.setMinimumSize(new Dimension(0,40));
			assistLabel.setPreferredSize(new Dimension(assistLabel.getPreferredSize().width, 40));
			assistLabel.setFont(assistFont);
			
			scoreStats.add(assistLabel);
			
			goalPeeps2.add(scoreStats);
		}
		
		rightPanel.add(goalPeeps2);
		
		add(rightPanel);
	}
}
