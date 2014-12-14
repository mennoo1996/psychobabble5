/**
 * GUI class that displays the current season's previous and prior match results
 * @version 0.0.1
 */
package swinggui;

import java.awt.Component;
import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;

import libraryClasses.Competition;
import libraryClasses.CompetitionScheme;
import libraryClasses.Round;
import libraryClasses.Match;

public class RecentMatchesPanel extends JPanel {
	
	private Competition currentCompetition;
	
	/**
	 * Create and initialize RecentMatchesPanel object
	 * @param curComp current competition
	 */
	public RecentMatchesPanel(Competition curComp) {
		currentCompetition = curComp;
		
		initUI();
	}
	
	/**
	 * Initialize the GUI elements
	 */
	public final void initUI() {
		
		setOpaque(false);
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new Box.Filler(new Dimension(1,5), new Dimension(1,5), new Dimension(1,5)));
		
		JLabel agendaTitle = new JLabel("Recent and Upcoming Fixtures");
		agendaTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(agendaTitle);
		
		// Fetch scheme and then populate table data
		CompetitionScheme curScheme = currentCompetition.getScheme();
		
		int curRound = currentCompetition.getRoundsPlayed();
		
		if (curRound > 0) {
			// Recent matches table array
			Object previousMatches[][] = new Object[10][2];
			
			
			// Get recent matches
			ArrayList<Match> roundsMatches = curScheme.getRound(curRound).getMatches();
			for (int i = 0; i < 10; i++) {
				Match curMatch = roundsMatches.get(i);
				
				Object matchRow[] = {
					curMatch.getTeam1() + " vs " + curMatch.getTeam2(),
					curMatch.getScoreTeam1() + " - " + curMatch.getScoreTeam2()
				};
				previousMatches[i] = matchRow;
			}
			
			Object columnNamesPrev[] = { 
					"Match",
					"Score"
			};
			
			JTable resultsTable = new JTable(previousMatches, columnNamesPrev) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			// center table items
			DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
			centerRender.setHorizontalAlignment(JLabel.CENTER);
	        resultsTable.getColumnModel().getColumn(1).setCellRenderer( centerRender );
		    
			
			// Always display team name properly
			resultsTable.getColumnModel().getColumn(0).setMinWidth(120);

			// Disable editing
			resultsTable.getTableHeader().setReorderingAllowed(false);
			JScrollPane scrollPane = new JScrollPane(resultsTable);
			
			add(scrollPane);
		}
		if (curRound < 38) {
			// Upcoming matches table array
			Object upcomingMatches[][] = new Object[10][1];
			
			// Get upcoming matches
			ArrayList<Match> roundsMatches = curScheme.getRound(curRound + 1).getMatches();
			for (int i = 0; i < 10; i++) {
				Match curMatch = roundsMatches.get(i);
				
				Object matchRow[] = {
					curMatch.getTeam1() + " vs " + curMatch.getTeam2()
				};
				upcomingMatches[i] = matchRow;
			}
			
			Object columnNamesNext[] = {
					"Upcoming"
			};
			
			// Initialize tables
	
			
			JTable upcomingTable = new JTable(upcomingMatches, columnNamesNext) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			// Always display team name properly
			upcomingTable.getColumnModel().getColumn(0).setMinWidth(120);
	
			// Disable editing
			upcomingTable.getTableHeader().setReorderingAllowed(false);
			JScrollPane scrollPaneUp = new JScrollPane(upcomingTable);
			
			add(scrollPaneUp);
		}
		
		// Adjust dimensions
		setMinimumSize(new Dimension(100,500));
		setPreferredSize(new Dimension(800,500));
		setMaximumSize(new Dimension(900,500));
	}
}
