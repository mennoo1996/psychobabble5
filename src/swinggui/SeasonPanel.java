/**
 * GUI Class that display's a completed season's results
 */
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
	
	private Font fontLarge = new Font("Avenir", Font.ROMAN_BASELINE, 20);
	private Font fontSmall = new Font("Avenir", Font.ROMAN_BASELINE, 16);
	
	/**
	 * Create and initialize a SeasonPanel
	 * @param currentComp Current (completed) competition
	 * @param currentTeam Player's team
	 * @param choiceListener Event listener for continuation choice
	 * @param yourName Player's name
	 */
	public SeasonPanel(Competition currentComp, Team currentTeam, ActionListener choiceListener, String yourName) {
		cComp = currentComp;
		cTeam = currentTeam;
		nextChoiceListener = choiceListener;
		playerName = yourName;
		
		initUI();
	}
	
	/**
	 * Initialize GUI elements contained in the SeasonPanel
	 */
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(new Box.Filler(minSize, prefSize, null));
		
		JPanel seasonOverviewPanel = new JPanel();
		seasonOverviewPanel.setName("Panel");
		seasonOverviewPanel.setLayout(new BoxLayout(seasonOverviewPanel, BoxLayout.Y_AXIS));
		
		// Add Congratulations Label
		JPanel title = new JPanel();
		JLabel titleLabel = new JLabel("Congratulations, " + playerName + ", on completing the season", JLabel.CENTER);
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160, 160, 160)));
		title.add(titleLabel);
		seasonOverviewPanel.add(title);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
		
		JPanel yourTeamData = new JPanel();
		yourTeamData.setLayout(new BorderLayout());

		yourTeamData.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(180,180,180)));
		
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
		finalPosition.setFont(fontSmall);
		finalPosition.setMinimumSize(new Dimension(0, 125));
		finalPosition.setPreferredSize(new Dimension(finalPosition.getPreferredSize().width, 125));
		finalPosition.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220,220)));
		yourTeamData.add(finalPosition, BorderLayout.NORTH);
		
		// Add final points
		JLabel finalPoints = new JLabel("You finished the season with " + cTeam.getStandings().getPoints() + " points", JLabel.CENTER);
		finalPoints.setFont(fontSmall);
		finalPoints.setMinimumSize(new Dimension(0, 40));
		finalPoints.setPreferredSize(new Dimension(finalPoints.getPreferredSize().width, 40));
		finalPoints.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220,220)));
		yourTeamData.add(finalPoints, BorderLayout.CENTER);
		
		// Add current budget
		JLabel finalBudget = new JLabel("Your remaining budget is: $" + (int)cTeam.getBudget(), JLabel.CENTER);
		finalBudget.setFont(fontSmall);
		finalBudget.setMinimumSize(new Dimension(0, 125));
		finalBudget.setPreferredSize(new Dimension(finalBudget.getPreferredSize().width, 125));
		yourTeamData.add(finalBudget, BorderLayout.SOUTH);
		
		dataPanel.add(yourTeamData);
		
		JPanel seasonStats = new JPanel();
		seasonStats.setLayout(new BorderLayout());
		seasonStats.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		
		// Show the winning team
		JLabel winningTeam = new JLabel("This season's winning team was " + sortedStandings.get(0).getTeamName(), JLabel.CENTER);
		winningTeam.setFont(fontSmall);
		winningTeam.setMinimumSize(new Dimension(0, 40));
		winningTeam.setPreferredSize(new Dimension(winningTeam.getPreferredSize().width, 40));
		winningTeam.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		seasonStats.add(winningTeam, BorderLayout.NORTH);
		
		// Show the season's topscorers
		JPanel topScorersPanel = new JPanel();
		topScorersPanel.setLayout(new BoxLayout(topScorersPanel, BoxLayout.Y_AXIS));
		
		JPanel topScorer = new JPanel(new BorderLayout());
		JLabel topScorersLabel = new JLabel("Top Scorers", JLabel.CENTER);
		topScorer.setMinimumSize(new Dimension(0,30));
		topScorer.setPreferredSize(new Dimension(200, 30));
		topScorer.setMaximumSize(new Dimension(2000,30));
		topScorersLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		topScorersLabel.setFont(fontSmall);
		topScorersLabel.setOpaque(true);
		topScorersLabel.setBackground(new Color(240,240,240));
		topScorer.add(topScorersLabel, BorderLayout.CENTER);
		topScorersPanel.add(topScorer);
		
		// Only show top 3 scorers
		Player[] topScorers = cComp.getTopScorers();
		for (int i = 0; i < 3; i++) {
			JPanel nextScorer = new JPanel();
			nextScorer.setLayout(new BorderLayout());
			
			nextScorer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));
			
			Player beast = topScorers[i];
			
			// Add game name label
			JLabel scorerLabel = new JLabel(beast.getName() + ", " + beast.getTeam() + ": " + beast.getGoals() + " goals", JLabel.CENTER);
			scorerLabel.setFont(new Font("Avenir", Font.ROMAN_BASELINE, 16));
			scorerLabel.setMinimumSize(new Dimension(20, 30));
			scorerLabel.setPreferredSize(new Dimension(90, 30));
			scorerLabel.setMaximumSize(new Dimension(100, 30));
			
			nextScorer.add(scorerLabel, BorderLayout.CENTER);
			topScorersPanel.add(nextScorer);
		}
		
		seasonStats.add(topScorersPanel, BorderLayout.CENTER);
		
		dataPanel.add(seasonStats);
		seasonOverviewPanel.add(dataPanel);
		
		// Add buttons for new season and quit
		JPanel choicePanel = new JPanel();
		//choicePanel.setPreferredSize(new Dimension(2000,choicePanel.getMaximumSize().height));
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
		
		seasonOverviewPanel.setMinimumSize(new Dimension(900,580));
		seasonOverviewPanel.setPreferredSize(new Dimension(900,580));
		seasonOverviewPanel.setMaximumSize(new Dimension(900,580));
		add(seasonOverviewPanel);
		
		add(new Box.Filler(minSize, prefSize, null));
	}
}
