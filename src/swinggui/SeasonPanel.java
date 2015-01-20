package swinggui;

import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;

import game.Competition;
import libraryClasses.Team;
import libraryClasses.Player;
import libraryClasses.Standings;

public class SeasonPanel extends JPanel{
	
	private Competition cComp;
	private Team cTeam;
	private ActionListener nextChoiceListener;
	private String playerName;
	
	private Dimension minSize = new Dimension(20,20);
	private Dimension prefSize = new Dimension(40, 20); 
	
	public SeasonPanel(Competition currentComp, Team currentTeam, ActionListener choiceListener, String yourName) {
		cComp = currentComp;
		cTeam = currentTeam;
		nextChoiceListener = choiceListener;
		playerName = yourName;
		
		initUI();
	}
	
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(new Box.Filler(minSize, prefSize, null));
		
		JPanel seasonOverviewPanel = new JPanel();
		seasonOverviewPanel.setName("Panel");
		seasonOverviewPanel.setLayout(new BoxLayout(seasonOverviewPanel, BoxLayout.Y_AXIS));
		
		// Add Congratulations Label
		JPanel title = new JPanel();
		JLabel titleLabel = new JLabel("Congratulations " + playerName + " on completing the season", JLabel.CENTER);
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160, 160, 160)));
		title.add(titleLabel);
		seasonOverviewPanel.add(title);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
		
		JPanel yourTeamData = new JPanel();
		yourTeamData.setLayout(new BorderLayout());
		
		// Add final position
		ArrayList<Standings> sortedStandings = cComp.getSortedStandings();
		int place = 1;
		for (int i = 0; i < sortedStandings.size(); i++) {
			if (sortedStandings.get(i).getTeamName().equals(cTeam.getTeamName())) {
				place = i + 1;
			}
		}
		
		String suffix = "th";
		
		if (place == 1) {
			suffix = "st";
		} else if (place == 2) {
			suffix = "nd";
		} else if (place == 3) {
			suffix = "rd";
		}
		
		JLabel finalPosition = new JLabel("As " + cTeam.getTeamName() + " you finished in " + place + suffix + " place", JLabel.CENTER);
		finalPosition.setMinimumSize(new Dimension(0, 40));
		finalPosition.setPreferredSize(new Dimension(finalPosition.getPreferredSize().width, 40));
		yourTeamData.add(finalPosition, BorderLayout.NORTH);
		
		// Add final points
		JLabel finalPoints = new JLabel("You finished the season with " + cTeam.getStandings().getPoints() + " points", JLabel.CENTER);
		finalPoints.setMinimumSize(new Dimension(0, 40));
		finalPoints.setPreferredSize(new Dimension(finalPoints.getPreferredSize().width, 40));
		yourTeamData.add(finalPoints, BorderLayout.CENTER);
		
		// Add current budget
		JLabel finalBudget = new JLabel("Your remaining budget is: $" + (int)cTeam.getBudget(), JLabel.CENTER);
		finalBudget.setMinimumSize(new Dimension(0, 40));
		finalBudget.setPreferredSize(new Dimension(finalBudget.getPreferredSize().width, 40));
		yourTeamData.add(finalBudget, BorderLayout.SOUTH);
		
		dataPanel.add(yourTeamData);
		
		JPanel seasonStats = new JPanel();
		seasonStats.setLayout(new BorderLayout());
		
		// Show the winning team
		JLabel winningTeam = new JLabel("This season's winning team was " + sortedStandings.get(0).getTeamName(), JLabel.CENTER);
		winningTeam.setMinimumSize(new Dimension(0, 40));
		winningTeam.setPreferredSize(new Dimension(winningTeam.getPreferredSize().width, 40));
		seasonStats.add(winningTeam, BorderLayout.NORTH);
		
		// Show the season's topscorers
		JPanel topScorersPanel = new JPanel();
		topScorersPanel.setLayout(new BoxLayout(topScorersPanel, BoxLayout.Y_AXIS));
		
		JLabel topScorersLabel = new JLabel("Top Scorers", JLabel.CENTER);
		topScorersLabel.setMinimumSize(new Dimension(0, 40));
		topScorersLabel.setPreferredSize(new Dimension(topScorersLabel.getPreferredSize().width, 40));
		topScorersPanel.add(topScorersLabel);
		
		// Only show top 3 scorers
		Player[] topScorers = cComp.getTopScorers();
		for (int i = 0; i < 3; i++) {
			JPanel nextScorer = new JPanel();
			nextScorer.setLayout(new BorderLayout());
			
			nextScorer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200, 200, 200)));
			
			Player beast = topScorers[i];
			
			// Add game name label
			JLabel scorerLabel = new JLabel(beast.getName() + ", " + beast.getTeam() + ": " + beast.getGoals() + " goals", JLabel.CENTER);
			scorerLabel.setFont(new Font("Avenir", Font.ROMAN_BASELINE, 16));
			scorerLabel.setMinimumSize(new Dimension(20, 50));
			scorerLabel.setPreferredSize(new Dimension(90, 50));
			scorerLabel.setMaximumSize(new Dimension(100, 50));
			
			nextScorer.add(scorerLabel, BorderLayout.CENTER);
			topScorersPanel.add(nextScorer);
		}
		
		seasonStats.add(topScorersPanel, BorderLayout.CENTER);
		
		dataPanel.add(seasonStats);
		seasonOverviewPanel.add(dataPanel);
		
		// Add buttons for new season and quit
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.X_AXIS));
		
		JButton newSeasonButton = new JButton("New Season");
		newSeasonButton.setName("Test");
		newSeasonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newSeasonButton.addActionListener(nextChoiceListener);
		
		choicePanel.add(newSeasonButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setName("Test");
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.addActionListener(nextChoiceListener);
		
		choicePanel.add(quitButton);
		
		seasonOverviewPanel.add(choicePanel);
		
		add(seasonOverviewPanel);
		
		add(new Box.Filler(minSize, prefSize, null));
	}
}
