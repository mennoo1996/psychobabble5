package swinggui;

import game.Competition;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import schemeClasses.CompetitionScheme;
import schemeClasses.Match;
import libraryClasses.Team;
import libraryClasses.Player;
import libraryClasses.FieldPlayer;

public class MatchPanel extends JPanel {
	
	private Competition cComp;
	
	public Dimension minSize = new Dimension(20,20);
	public Dimension prefSize = new Dimension(40,20);
	
	private Team cTeam;
	
	//fonts
	Font fontSmall = new Font("Avenir", Font.ROMAN_BASELINE, 14);
	Font fontTitle = new Font("Avenir", Font.ROMAN_BASELINE, 18);
	
	public MatchPanel(Competition currentCompetition, Team currentTeam) {
		cComp = currentCompetition;
		cTeam = currentTeam;
		
		initUI();
	}
	
	public final void initUI() {
		//setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				
		add(new Box.Filler(minSize, prefSize, null));
		
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
		
		JPanel scrollContent1 = new JPanel();
		scrollContent1.setLayout(new BoxLayout(scrollContent1, BoxLayout.Y_AXIS));
		
		JPanel namePanel1 = new JPanel();
		JLabel teamLabel1 = new JLabel(curMatch.getTeam1());
		teamLabel1.setMinimumSize(new Dimension(0,40));
		teamLabel1.setPreferredSize(new Dimension(teamLabel1.getPreferredSize().width, 40));
		namePanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		namePanel1.add(teamLabel1);
		namePanel1.setMaximumSize(new Dimension(2000, 40));
				
		leftPanel.add(namePanel1);
		
		
		JPanel scorePanel1 = new JPanel();
		scorePanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		scorePanel1.setBackground(new Color(240,240,240)); scorePanel1.setOpaque(true);
		JLabel scoreLabel1 = new JLabel("Goals: " + String.valueOf(curMatch.getScoreTeam1()));
		scoreLabel1.setFont(fontTitle);
		scoreLabel1.setMinimumSize(new Dimension(0,30));
		scoreLabel1.setMaximumSize(new Dimension(2000,30));
		scorePanel1.add(scoreLabel1);
		scorePanel1.setMinimumSize(new Dimension(0,30));
		scorePanel1.setMaximumSize(new Dimension(2000, 30));
		
		scrollContent1.add(scorePanel1);
		
		
		
		// Goal makers and assists
		Font scorerFont = new Font("Avenir", Font.ROMAN_BASELINE, 16);
		Font assistFont = new Font("Avenir", Font.ITALIC, 14);
		
		// Each goal maker + assist in own content pane
		if (curMatch.getScoreTeam1() > 0) {		
			
			for(int i = 0; i < curMatch.getScoreTeam1(); i++) {
				JPanel scoreStats = new JPanel();
				scoreStats.setLayout(new BorderLayout());

				// The goal scorer
				System.out.println("Scorer: " + curMatch.getGoalMakerst1().get(i).getName());
				JLabel goalScorerLabel = new JLabel("Scorer: " + curMatch.getGoalMakerst1().get(i).getName(), JLabel.CENTER);

				goalScorerLabel.setMinimumSize(new Dimension(0,40));
				goalScorerLabel.setPreferredSize(new Dimension(goalScorerLabel.getPreferredSize().width, 40));
				goalScorerLabel.setMaximumSize(new Dimension(550, 50));
				goalScorerLabel.setFont(scorerFont);
				goalScorerLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				
				scoreStats.add(goalScorerLabel, BorderLayout.NORTH);
				
				// The assist credit
				System.out.println("Assist: " + curMatch.getAssistMakerst1().get(i).getName());
				JLabel assistLabel = new JLabel("Assist: " + curMatch.getAssistMakerst1().get(i).getName(), JLabel.CENTER);
				assistLabel.setMinimumSize(new Dimension(0,40));
				assistLabel.setPreferredSize(new Dimension(assistLabel.getPreferredSize().width, 40));
				assistLabel.setMaximumSize(new Dimension(550, 50));
				assistLabel.setFont(assistFont);
				assistLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				
				scoreStats.add(assistLabel, BorderLayout.CENTER);
				
				scoreStats.setMaximumSize(new Dimension(900, 80));
				
				scrollContent1.add(scoreStats);
				
			}
		}
		
		leftPanel.setMinimumSize(new Dimension(100,600));
		leftPanel.setPreferredSize(new Dimension(450,600));
		leftPanel.setMaximumSize(new Dimension(900,600));
		
		// Cards
		if (curMatch.getYellowCardGetterst1().size() > 0 || curMatch.getRedCardGetterst1().size() > 0) {
			
			JPanel cardsDiv1 = new JPanel();
			cardsDiv1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(180,180,180)));
			cardsDiv1.setBackground(new Color(240,240,240)); cardsDiv1.setOpaque(true);
			JLabel cardsLabel1 = new JLabel("Cards");
			cardsLabel1.setMinimumSize(new Dimension(0,30));
			cardsLabel1.setMaximumSize(new Dimension(100,30));
			cardsLabel1.setFont(fontTitle);
			cardsDiv1.add(cardsLabel1);
			cardsDiv1.setMinimumSize(new Dimension(0, 30));
			cardsDiv1.setMaximumSize(new Dimension(2000, 30));
			scrollContent1.add(cardsDiv1);
			
			for(int i = 0; i < curMatch.getRedCardGetterst1().size(); i++) {
				JPanel cards1 = new JPanel();
				cards1.setLayout(new BorderLayout());
				System.out.println("At least one red");
				JLabel redLabel = new JLabel(curMatch.getRedCardGetterst1().get(i).getName() + " (Red)", JLabel.CENTER);
				redLabel.setMinimumSize(new Dimension(100,40));
				redLabel.setPreferredSize(new Dimension(redLabel.getPreferredSize().width, 40));
				redLabel.setMaximumSize(new Dimension(550, 50));
				redLabel.setFont(scorerFont);
				redLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				cards1.add(redLabel, BorderLayout.CENTER);
				cards1.setMaximumSize(new Dimension(550, 50));
				
				scrollContent1.add(cards1);
			}
			
			for(int i = 0; i < curMatch.getYellowCardGetterst1().size(); i++) {
				JPanel cards1 = new JPanel();
				cards1.setLayout(new BorderLayout());
				JLabel yellowLabel = new JLabel(curMatch.getYellowCardGetterst1().get(i).getName() + " (Yellow)", JLabel.CENTER);
				yellowLabel.setMinimumSize(new Dimension(100,40));
				yellowLabel.setPreferredSize(new Dimension(yellowLabel.getPreferredSize().width, 40));
				yellowLabel.setMaximumSize(new Dimension(550, 50));
				yellowLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				yellowLabel.setFont(scorerFont);
				yellowLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				
				cards1.add(yellowLabel, BorderLayout.CENTER);
				cards1.setMaximumSize(new Dimension(550, 50));
				
				scrollContent1.add(cards1);
			}
		}
		
		// Injuries
		if (curMatch.getInjuredPlayerst1().size() > 0) {
			
			JPanel injuryDiv1 = new JPanel();
			injuryDiv1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(180,180,180)));
			injuryDiv1.setBackground(new Color(240,240,240)); injuryDiv1.setOpaque(true);
			JLabel injuryLabel1 = new JLabel("Injuries");
			injuryLabel1.setFont(fontTitle);
			injuryLabel1.setMinimumSize(new Dimension(0,30));
			injuryLabel1.setMaximumSize(new Dimension(100,30));
			injuryDiv1.add(injuryLabel1);
			injuryDiv1.setMinimumSize(new Dimension(0,30));
			injuryDiv1.setMaximumSize(new Dimension(2000, 30));
			
			scrollContent1.add(injuryDiv1);
			
			for(int i = 0; i < curMatch.getInjuredPlayerst1().size(); i++) {
				JPanel injury1 = new JPanel();
				injury1.setLayout(new BorderLayout());
				
				Player injuredP = curMatch.getInjuredPlayerst1().get(i);
				int duration = curMatch.getInjuriesLengthst1()[i];
				String injuryWeek = duration == 1 ? " week)" : " weeks)";
				JLabel injuredLabel = new JLabel(injuredP.getName() + " (" + duration + injuryWeek, JLabel.CENTER);
				injuredLabel.setMinimumSize(new Dimension(100,40));
				injuredLabel.setPreferredSize(new Dimension(injuredLabel.getPreferredSize().width, 40));
				injuredLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
				injuredLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				injuredLabel.setFont(scorerFont);
				
				injury1.add(injuredLabel, BorderLayout.CENTER);
				injury1.setMaximumSize(new Dimension(550, 50));
				
				scrollContent1.add(injury1);
			}
		}
		
		JScrollPane scrollPaneLeft = new JScrollPane(scrollContent1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		leftPanel.add(scrollPaneLeft);
		
		add(leftPanel);
		
		
		// Right team
		JPanel rightPanel = new JPanel();
		rightPanel.setName("Panel");
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JPanel namePanel2 = new JPanel();
		JLabel teamLabel2 = new JLabel(curMatch.getTeam2());
		teamLabel2.setMinimumSize(new Dimension(0,40));
		teamLabel2.setPreferredSize(new Dimension(teamLabel2.getPreferredSize().width, 40));
		namePanel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		namePanel2.add(teamLabel2);
		namePanel2.setMaximumSize(new Dimension(2000, 40));
		
		rightPanel.add(namePanel2);
		
		JPanel scrollContent2 = new JPanel();
		scrollContent2.setLayout(new BoxLayout(scrollContent2, BoxLayout.Y_AXIS));
		
		JPanel scorePanel2 = new JPanel();
		scorePanel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		scorePanel2.setBackground(new Color(240,240,240)); scorePanel2.setOpaque(true);
		JLabel scoreLabel2 = new JLabel("Goals: " + String.valueOf(curMatch.getScoreTeam2()));
		scoreLabel2.setFont(fontTitle);
		scoreLabel2.setMinimumSize(new Dimension(0,30));
		scoreLabel2.setMaximumSize(new Dimension(2000,30));
		scorePanel2.add(scoreLabel2);
		scorePanel2.setMinimumSize(new Dimension(0,30));
		scorePanel2.setMaximumSize(new Dimension(2000, 30));
		
		scrollContent2.add(scorePanel2);
		
		
		
		// Each goal maker + assist in own content pane
		if (curMatch.getScoreTeam2() > 0) {
			
			for(int i = 0; i < curMatch.getScoreTeam2(); i++) {
				JPanel scoreStats = new JPanel();
				scoreStats.setLayout(new BorderLayout());
				
				// The goal scorer
				JLabel goalScorerLabel = new JLabel("Scorer: " + curMatch.getGoalMakerst2().get(i).getName(), JLabel.CENTER);
				goalScorerLabel.setMinimumSize(new Dimension(0,40));
				goalScorerLabel.setPreferredSize(new Dimension(goalScorerLabel.getPreferredSize().width, 40));
				goalScorerLabel.setMaximumSize(new Dimension(550, 50));
				goalScorerLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				goalScorerLabel.setFont(scorerFont);
				
				scoreStats.add(goalScorerLabel, BorderLayout.NORTH);
							
				// The assist credit
				JLabel assistLabel = new JLabel("Assist: " + curMatch.getAssistMakerst2().get(i).getName(), JLabel.CENTER);
				assistLabel.setMinimumSize(new Dimension(0,40));
				assistLabel.setPreferredSize(new Dimension(assistLabel.getPreferredSize().width, 40));
				assistLabel.setMaximumSize(new Dimension(550, 50));
				assistLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				assistLabel.setFont(assistFont);
				
				scoreStats.add(assistLabel, BorderLayout.CENTER);
				
				scoreStats.setMaximumSize(new Dimension(900, 80));
				
				scrollContent2.add(scoreStats);
			}
		}
		
		rightPanel.setMinimumSize(new Dimension(100,600));
		rightPanel.setPreferredSize(new Dimension(450,600));
		rightPanel.setMaximumSize(new Dimension(900,600));
		
		// Cards
		if (curMatch.getYellowCardGetterst2().size() > 0 || curMatch.getRedCardGetterst2().size() > 0) {

			JPanel cardsDiv2 = new JPanel();
			cardsDiv2.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(180,180,180)));
			cardsDiv2.setBackground(new Color(240,240,240)); cardsDiv2.setOpaque(true);
			JLabel cardsLabe2 = new JLabel("Cards");
			cardsLabe2.setMinimumSize(new Dimension(0,30));
			cardsLabe2.setMaximumSize(new Dimension(2000,30));
			cardsLabe2.setFont(fontTitle);
			cardsDiv2.add(cardsLabe2);
			cardsDiv2.setMinimumSize(new Dimension(0, 30));
			cardsDiv2.setMaximumSize(new Dimension(2000, 30));
			scrollContent2.add(cardsDiv2);
			
			for(int i = 0; i < curMatch.getRedCardGetterst2().size(); i++) {
				JPanel cards2 = new JPanel();
				cards2.setLayout(new BorderLayout());
				JLabel redLabel = new JLabel(curMatch.getRedCardGetterst2().get(i).getName() + " (Red)", JLabel.CENTER);
				redLabel.setMinimumSize(new Dimension(0,40));
				redLabel.setPreferredSize(new Dimension(redLabel.getPreferredSize().width, 40));
				redLabel.setMaximumSize(new Dimension(550, 40));
				redLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				redLabel.setFont(scorerFont);
				redLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				cards2.add(redLabel, BorderLayout.CENTER);
				cards2.setMaximumSize(new Dimension(550, 50));
				
				scrollContent2.add(cards2);
			}
			
			for(int i = 0; i < curMatch.getYellowCardGetterst2().size(); i++) {
				JPanel cards2 = new JPanel();
				cards2.setLayout(new BorderLayout());
				JLabel yellowLabel = new JLabel(curMatch.getYellowCardGetterst2().get(i).getName() + " (Yellow)", JLabel.CENTER);
				yellowLabel.setMinimumSize(new Dimension(0,40));
				yellowLabel.setPreferredSize(new Dimension(yellowLabel.getPreferredSize().width, 40));
				yellowLabel.setMaximumSize(new Dimension(550, 40));
				yellowLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				yellowLabel.setFont(scorerFont);
				yellowLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				cards2.add(yellowLabel, BorderLayout.CENTER);
				cards2.setMaximumSize(new Dimension(550, 50));
				
				scrollContent2.add(cards2);
			}			
		}
		
		// Injuries
		if (curMatch.getInjuredPlayerst2().size() > 0) {
			
			JPanel injuryDiv2 = new JPanel();
			injuryDiv2.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(180,180,180)));
			injuryDiv2.setBackground(new Color(240,240,240)); injuryDiv2.setOpaque(true);
			JLabel injuryLabel2 = new JLabel("Injuries");
			injuryLabel2.setFont(fontTitle);
			injuryLabel2.setMinimumSize(new Dimension(0,30));
			injuryLabel2.setMaximumSize(new Dimension(100,30));
			injuryDiv2.add(injuryLabel2);
			injuryDiv2.setMinimumSize(new Dimension(0,30));
			injuryDiv2.setMaximumSize(new Dimension(2000, 30));
			
			scrollContent2.add(injuryDiv2);
			
			for(int i = 0; i < curMatch.getInjuredPlayerst2().size(); i++) {
				JPanel injury2 = new JPanel();
				injury2.setLayout(new BorderLayout());
				
				Player injuredP = curMatch.getInjuredPlayerst2().get(i);
				int duration = curMatch.getInjuriesLengthst2()[i];
				String injuryWeek = duration == 1 ? " week)" : " weeks)";
				JLabel injuredLabel = new JLabel(injuredP.getName() + " (" + duration + injuryWeek, JLabel.CENTER);
				injuredLabel.setMinimumSize(new Dimension(0,40));
				injuredLabel.setPreferredSize(new Dimension(injuredLabel.getPreferredSize().width, 40));
				injuredLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				injuredLabel.setFont(scorerFont);
				injuredLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
				injury2.add(injuredLabel, BorderLayout.CENTER);
				injury2.setMaximumSize(new Dimension(550, 50));
				
				scrollContent2.add(injury2);
			}
		}
		
		JScrollPane scrollPaneRight = new JScrollPane(scrollContent2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		rightPanel.add(scrollPaneRight);
		
		add(rightPanel);
		
		add(new Box.Filler(minSize, prefSize, null));
		
	}
}
