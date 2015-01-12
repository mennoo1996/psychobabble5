package swinggui;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;

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
		//setName("Panel");
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
		leftPanel.setName("Panel");
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
		
		// Goal makers and assists
		
		// Each goal maker + assist in own content pane
		JPanel goalPeeps = new JPanel();
		goalPeeps.setLayout(new BoxLayout(goalPeeps, BoxLayout.Y_AXIS));
		goalPeeps.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel goalTitlePanel1 = new JPanel();
		JLabel goalTitleLabel = new JLabel("Goals");
		goalTitleLabel.setPreferredSize(new Dimension(goalTitleLabel.getPreferredSize().width, 40));
		goalTitleLabel.setMaximumSize(new Dimension(goalTitleLabel.getPreferredSize().width, 40));
		goalTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		goalTitlePanel1.add(goalTitleLabel);
		
		goalTitlePanel1.setMinimumSize(new Dimension(100, 80));
		goalTitlePanel1.setPreferredSize(new Dimension(100, 80));
		goalTitlePanel1.setMaximumSize(new Dimension(550, 80));
		
		goalPeeps.add(goalTitlePanel1);
				
		Font scorerFont = new Font("Avenir", Font.ROMAN_BASELINE, 16);
		Font assistFont = new Font("Avenir", Font.ITALIC, 14);
		
		for(int i = 0; i < curMatch.getScoreTeam1(); i++) {
			JLabel scoreStats = new JLabel();
			scoreStats.setLayout(new BoxLayout(scoreStats, BoxLayout.Y_AXIS));
			
			// The goal scorer
			System.out.println("Scorer: " + curMatch.getGoalMakerst1().get(i).getName());
			JLabel goalScorerLabel = new JLabel(curMatch.getGoalMakerst1().get(i).getName());
			goalScorerLabel.setMinimumSize(new Dimension(100,40));
			goalScorerLabel.setPreferredSize(new Dimension(100, 40));
			goalScorerLabel.setMaximumSize(new Dimension(550, 40));
			goalScorerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			goalScorerLabel.setFont(scorerFont);
			
			scoreStats.add(goalScorerLabel);
			
			// The assist credit
			System.out.println("Assist: " + curMatch.getAssistMakerst1().get(i).getName());
			JLabel assistLabel = new JLabel(curMatch.getAssistMakerst1().get(i).getName());
			assistLabel.setMinimumSize(new Dimension(100,40));
			assistLabel.setPreferredSize(new Dimension(100, 40));
			assistLabel.setMaximumSize(new Dimension(550, 100));
			assistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			assistLabel.setFont(assistFont);
			
			scoreStats.setMinimumSize(new Dimension(100, 80));
			scoreStats.setPreferredSize(new Dimension(100, 80));
			scoreStats.setMaximumSize(new Dimension(550, 80));
			
			scoreStats.add(assistLabel);
			goalPeeps.add(scoreStats);
			
		}
		
		goalPeeps.setMinimumSize(new Dimension(200,200));
		goalPeeps.setPreferredSize(new Dimension(200,500));
		goalPeeps.setMaximumSize(new Dimension(200,500));
				
		leftPanel.add(goalPeeps);
		
		// Cards
		JPanel cards1 = new JPanel();
		cards1.setLayout(new BoxLayout(cards1, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < curMatch.getRedCardGetterst1().size(); i++) {
			JLabel redLabel = new JLabel(curMatch.getRedCardGetterst1().get(i).getName() + " (R)");
			redLabel.setMinimumSize(new Dimension(0,40));
			redLabel.setPreferredSize(new Dimension(redLabel.getPreferredSize().width, 40));
			redLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			redLabel.setFont(scorerFont);
			
			cards1.add(redLabel);
		}
		
		for(int i = 0; i < curMatch.getYellowCardGetterst1().size(); i++) {
			JLabel yellowLabel = new JLabel(curMatch.getYellowCardGetterst1().get(i).getName() + " (Y)");
			yellowLabel.setMinimumSize(new Dimension(0,40));
			yellowLabel.setPreferredSize(new Dimension(yellowLabel.getPreferredSize().width, 40));
			yellowLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			yellowLabel.setFont(scorerFont);
			
			cards1.add(yellowLabel);
		}
		
		leftPanel.add(cards1);
		
		// Injuries
		JPanel injury1 = new JPanel();
		injury1.setLayout(new BoxLayout(injury1, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < curMatch.getInjuredPlayerst1().size(); i++) {
			Player injuredP = curMatch.getInjuredPlayerst1().get(i);
			int duration = curMatch.getInjuriesLengthst1()[i];
			String injuryWeek = duration == 1 ? " week)" : " weeks)";
			JLabel injuredLabel = new JLabel(injuredP.getName() + " (" + duration + injuryWeek);
			injuredLabel.setMinimumSize(new Dimension(0,40));
			injuredLabel.setPreferredSize(new Dimension(injuredLabel.getPreferredSize().width, 40));
			injuredLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			injuredLabel.setFont(scorerFont);
			
			injury1.add(injuredLabel);
		}
		
		leftPanel.add(injury1);
		
		add(leftPanel);
		
		
		// Center labels panel
		
		JPanel centerPanel = new JPanel();
		centerPanel.setName("Panel");
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
		
		JPanel cardsDiv = new JPanel();
		JLabel cardsLabel = new JLabel("Cards");
		cardsLabel.setMinimumSize(new Dimension(0,40));
		cardsLabel.setPreferredSize(new Dimension(cardsLabel.getPreferredSize().width, 40));
		cardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cardsDiv.add(cardsLabel);
		
		centerPanel.add(cardsDiv);
		
		JPanel injuryDiv = new JPanel();
		JLabel injuryLabel = new JLabel("Injuries");
		injuryLabel.setMinimumSize(new Dimension(0,40));
		injuryLabel.setPreferredSize(new Dimension(injuryLabel.getPreferredSize().width, 40));
		injuryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		injuryDiv.add(injuryLabel);
		
		centerPanel.add(injuryDiv);
		
		add(centerPanel);
		
		// Right team
		JPanel rightPanel = new JPanel();
		rightPanel.setName("Panel");
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
			goalScorerLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			goalScorerLabel.setFont(scorerFont);
			
			scoreStats.add(goalScorerLabel);
						
			// The assist credit
			JLabel assistLabel = new JLabel(curMatch.getAssistMakerst2().get(i).getName());
			assistLabel.setMinimumSize(new Dimension(0,40));
			assistLabel.setPreferredSize(new Dimension(assistLabel.getPreferredSize().width, 40));
			assistLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			assistLabel.setFont(assistFont);
			
			scoreStats.add(assistLabel);
			
			goalPeeps2.add(scoreStats);
		}
		
		rightPanel.add(goalPeeps2);
		
		// Cards
		JPanel cards2 = new JPanel();
		cards2.setLayout(new BoxLayout(cards2, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < curMatch.getRedCardGetterst2().size(); i++) {
			JLabel redLabel = new JLabel(curMatch.getRedCardGetterst2().get(i).getName() + " (R)");
			redLabel.setMinimumSize(new Dimension(0,40));
			redLabel.setPreferredSize(new Dimension(redLabel.getPreferredSize().width, 40));
			redLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			redLabel.setFont(scorerFont);
			
			cards2.add(redLabel);
		}
		
		for(int i = 0; i < curMatch.getYellowCardGetterst2().size(); i++) {
			JLabel yellowLabel = new JLabel(curMatch.getYellowCardGetterst2().get(i).getName() + " (Y)");
			yellowLabel.setMinimumSize(new Dimension(0,40));
			yellowLabel.setPreferredSize(new Dimension(yellowLabel.getPreferredSize().width, 40));
			yellowLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			yellowLabel.setFont(scorerFont);
			
			cards2.add(yellowLabel);
		}
		
		rightPanel.add(cards2);
		
		// Injuries
		JPanel injury2 = new JPanel();
		injury2.setLayout(new BoxLayout(injury2, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < curMatch.getInjuredPlayerst2().size(); i++) {
			Player injuredP = curMatch.getInjuredPlayerst2().get(i);
			int duration = curMatch.getInjuriesLengthst2()[i];
			String injuryWeek = duration == 1 ? " week)" : " weeks)";
			JLabel injuredLabel = new JLabel(injuredP.getName() + " (" + duration + injuryWeek);
			injuredLabel.setMinimumSize(new Dimension(0,40));
			injuredLabel.setPreferredSize(new Dimension(injuredLabel.getPreferredSize().width, 40));
			injuredLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			injuredLabel.setFont(scorerFont);
			
			injury2.add(injuredLabel);
		}
		
		rightPanel.add(injury2);
		
		add(rightPanel);
	}
}
