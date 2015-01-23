/**
 * GUI Class that displays the last match's result & details for the player's team
 */
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
	private Font fontSmall = new Font("Avenir", Font.ROMAN_BASELINE, 16);
	private Font fontTitle = new Font("Avenir", Font.ROMAN_BASELINE, 18);
	private Font scorerFont = new Font("Avenir", Font.ROMAN_BASELINE, 16);
	private Font assistFont = new Font("Avenir", Font.ITALIC, 14);
	
	/**
	 * Create and initialize a MatchPanel
	 * @param currentCompetition Current Competition
	 * @param currentTeam Player's team
	 */
	public MatchPanel(Competition currentCompetition, Team currentTeam) {
		cComp = currentCompetition;
		cTeam = currentTeam;
		
		initUI();
	}
	
	/**
	 * Initialize GUI elements contained in the MatchPanel
	 */
	public final void initUI() {

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
		
		//LOOP TWICE FOR TWO PANELS
		for(int i=0; i<2; i++){
			
			//Panel
			JPanel panel = new JPanel();
			JPanel scrollContent = new JPanel();
			panel.setName("Panel");
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setMinimumSize(new Dimension(100,580));
			panel.setPreferredSize(new Dimension(450,580));
			panel.setMaximumSize(new Dimension(900,580));
			scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));
			
			//double things initialized here, unless I need to loop them
			JLabel teamLabel;
			JLabel scoreLabel;
			int Score;
			ArrayList<FieldPlayer> goalMakers;
			ArrayList<FieldPlayer> assistMakers;
			ArrayList<Player> redCardGetters;
			ArrayList<Player> yellowCardGetters;
			ArrayList<Player> injuredPlayers;
			int[] injuredLength;
			if(i==0) {
				teamLabel = new JLabel(curMatch.getTeam1());
				scoreLabel = new JLabel("Goals: " + String.valueOf(curMatch.getScoreTeam1()));
				Score = curMatch.getScoreTeam1();
				goalMakers = curMatch.getGoalMakerst1();
				assistMakers = curMatch.getAssistMakerst1();
				redCardGetters = curMatch.getRedCardGetterst1();
				yellowCardGetters = curMatch.getYellowCardGetterst1();
				injuredPlayers = curMatch.getInjuredPlayerst1();
				injuredLength = curMatch.getInjuriesLengthst1();
			} else {
				teamLabel = new JLabel(curMatch.getTeam2());
				scoreLabel = new JLabel("Goals: " + String.valueOf(curMatch.getScoreTeam2()));
				Score = curMatch.getScoreTeam2();
				goalMakers = curMatch.getGoalMakerst2();
				assistMakers = curMatch.getAssistMakerst2();
				redCardGetters = curMatch.getRedCardGetterst2();
				yellowCardGetters = curMatch.getYellowCardGetterst2();
				injuredPlayers = curMatch.getInjuredPlayerst2();
				injuredLength = curMatch.getInjuriesLengthst2();
			}
			
			//Team Name panel
			JPanel namePanel = new JPanel();
			teamLabel.setMinimumSize(new Dimension(0,40));
			teamLabel.setPreferredSize(new Dimension(teamLabel.getPreferredSize().width, 40));
			namePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
			namePanel.add(teamLabel);
			namePanel.setMaximumSize(new Dimension(2000, 40));
			panel.add(namePanel);
			
			//Team Score panel
			JPanel scorePanel = new JPanel();
			scorePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
			scorePanel.setBackground(new Color(240,240,240));
			scorePanel.setOpaque(true);
			scoreLabel.setFont(fontSmall);
			scoreLabel.setMinimumSize(new Dimension(0,30));
			scoreLabel.setMaximumSize(new Dimension(2000,30));
			scorePanel.add(scoreLabel);
			scorePanel.setMinimumSize(new Dimension(0,30));
			scorePanel.setPreferredSize(new Dimension(200,30));
			scorePanel.setMaximumSize(new Dimension(2000, 30));
			scrollContent.add(scorePanel);
			
			//Goal makers and assists
			if( (i==0 && curMatch.getScoreTeam1()>0) || (i==1 && curMatch.getScoreTeam2()>0) ) {
				
				//loop and create all players
				for(int j=0; j<Score; j++) {
					//initialize panel
					JPanel scoreStats = new JPanel();
					scoreStats.setLayout(new BorderLayout());
					
					// The goal scorer
					JLabel goalScorerLabel = new JLabel("Scorer: " + goalMakers.get(j).getName(), JLabel.CENTER);
					goalScorerLabel.setMinimumSize(new Dimension(0,40));
					goalScorerLabel.setPreferredSize(new Dimension(goalScorerLabel.getPreferredSize().width, 40));
					goalScorerLabel.setMaximumSize(new Dimension(550, 50));
					goalScorerLabel.setFont(scorerFont);
					goalScorerLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
					scoreStats.add(goalScorerLabel, BorderLayout.NORTH);
					
					// The assist credit
					JLabel assistLabel = new JLabel("Assist: " + assistMakers.get(j).getName(), JLabel.CENTER);
					assistLabel.setMinimumSize(new Dimension(0,40));
					assistLabel.setPreferredSize(new Dimension(assistLabel.getPreferredSize().width, 40));
					assistLabel.setMaximumSize(new Dimension(550, 50));
					assistLabel.setFont(assistFont);
					assistLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
					scoreStats.add(assistLabel, BorderLayout.CENTER);
					
					scoreStats.setMaximumSize(new Dimension(900, 80));
					scrollContent.add(scoreStats);
				}
				
			}
			
			//yellow and red cards
			if((i==0 && (curMatch.getYellowCardGetterst1().size()>0 || curMatch.getRedCardGetterst1().size() > 0) ) || 
			   (i==1 && (curMatch.getYellowCardGetterst2().size()>0 || curMatch.getRedCardGetterst2().size() > 0) ) ) {
				
				//Team cards panel
				JPanel cardsDiv = new JPanel();
				cardsDiv.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(180,180,180)));
				cardsDiv.setBackground(new Color(240,240,240)); 
				cardsDiv.setOpaque(true);
				JLabel cardsLabel = new JLabel("Cards");
				cardsLabel.setMinimumSize(new Dimension(0,30));
				cardsLabel.setMaximumSize(new Dimension(100,30));
				cardsLabel.setFont(fontSmall);
				cardsDiv.add(cardsLabel);
				cardsDiv.setMinimumSize(new Dimension(0, 30));
				cardsDiv.setPreferredSize(new Dimension(200,30));
				cardsDiv.setMaximumSize(new Dimension(2000, 30));
				scrollContent.add(cardsDiv);
				
				//red cards
				for(int j = 0; j < redCardGetters.size(); j++) {
					JPanel cards = new JPanel();
					cards.setLayout(new BorderLayout());
					System.out.println("At least one red");
					JLabel redLabel = new JLabel(redCardGetters.get(j).getName() + " (Red)", JLabel.CENTER);
					redLabel.setMinimumSize(new Dimension(100,40));
					redLabel.setPreferredSize(new Dimension(redLabel.getPreferredSize().width, 40));
					redLabel.setMaximumSize(new Dimension(550, 50));
					redLabel.setFont(scorerFont);
					redLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
					cards.add(redLabel, BorderLayout.CENTER);
					cards.setMaximumSize(new Dimension(550, 50));
					scrollContent.add(cards);
				}
				
				//yellow cards
				for(int j = 0; j < yellowCardGetters.size(); j++) {
					JPanel cards = new JPanel();
					cards.setLayout(new BorderLayout());
					JLabel yellowLabel = new JLabel(yellowCardGetters.get(j).getName() + " (Yellow)", JLabel.CENTER);
					yellowLabel.setMinimumSize(new Dimension(100,40));
					yellowLabel.setPreferredSize(new Dimension(yellowLabel.getPreferredSize().width, 40));
					yellowLabel.setMaximumSize(new Dimension(550, 50));
					yellowLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
					yellowLabel.setFont(scorerFont);
					yellowLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
					cards.add(yellowLabel, BorderLayout.CENTER);
					cards.setMaximumSize(new Dimension(550, 50));
					scrollContent.add(cards);
				}
				
			}
			
			//injured players
			if( (i==0 && curMatch.getInjuredPlayerst1().size()>0) || (i==1 && curMatch.getInjuredPlayerst2().size()>0) ) {
				
				//team injuries panel
				JPanel injuryDiv = new JPanel();
				injuryDiv.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(180,180,180)));
				injuryDiv.setBackground(new Color(240,240,240));
				injuryDiv.setOpaque(true);
				JLabel injuryLabel = new JLabel("Injuries");
				injuryLabel.setFont(fontSmall);
				injuryLabel.setMinimumSize(new Dimension(0,30));
				injuryLabel.setMaximumSize(new Dimension(100,30));
				injuryDiv.add(injuryLabel);
				injuryDiv.setMinimumSize(new Dimension(0,30));
				injuryDiv.setPreferredSize(new Dimension(200,30));
				injuryDiv.setMaximumSize(new Dimension(2000, 30));
				scrollContent.add(injuryDiv);
				
				//injuries
				for(int j = 0; j < injuredPlayers.size(); j++) {
					JPanel injury1 = new JPanel();
					injury1.setLayout(new BorderLayout());
					Player injuredP = injuredPlayers.get(j);
					int duration = injuredLength[j];
					String injuryWeek = duration == 1 ? " week)" : " weeks)";
					JLabel injuredLabel = new JLabel(injuredP.getName() + " (" + duration + injuryWeek, JLabel.CENTER);
					injuredLabel.setMinimumSize(new Dimension(100,40));
					injuredLabel.setPreferredSize(new Dimension(injuredLabel.getPreferredSize().width, 40));
					injuredLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
					injuredLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240,240,240)));
					injuredLabel.setFont(scorerFont);
					injury1.add(injuredLabel, BorderLayout.CENTER);
					injury1.setMaximumSize(new Dimension(550, 50));
					scrollContent.add(injury1);
				}
				
			}
			
			JScrollPane scrollPane = new JScrollPane(scrollContent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane);
			add(panel);
			
		}
		
		add(new Box.Filler(minSize, prefSize, null));
		
	}
}
