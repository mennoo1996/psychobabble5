/**
 * GUI class that displays the current season's standings
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
import libraryClasses.Standings;

public class StandingsPanel extends JPanel {
	
	private Competition currentCompetition;
	
	/**
	 * Create and initialize StandingsPanel object
	 * @param curComp current competition
	 */
	public StandingsPanel(Competition curComp) {
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
		
		JLabel standingsTitle = new JLabel("Current Standings (Round " + currentCompetition.getRoundsPlayed() + ")");
		standingsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(standingsTitle);
		
		// Preallocate row array
		int numTeams = currentCompetition.getLibrary().getLibrary().size();
		Object teamData[][] = new Object[numTeams][8];
		
		// Fetch standings and then populate table data
		ArrayList<Standings> sortedRes = currentCompetition.getSortedStandings();
		for (int i = 0; i < numTeams; i++) {
			Standings standing = sortedRes.get(i);
			Object teamRow[] = {
				standing.getTeamName(),
				standing.getPoints(),
				standing.getWon(),
				standing.getDraw(),
				standing.getLost(),
				standing.getGoalDifference(),
				standing.getGoalsFor(),
				standing.getGoalsAgainst()
			};
			teamData[i] = teamRow;
		}
		
		Object columnNames[] = { 
				"Team",
				"Points",
				"Won",
				"Draw",
				"Lost",
				"Goal Diff",
				"Goals for",
				"Goals against"
		};
		
		// Initialize table
		JTable resultsTable = new JTable(teamData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// center table items
		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		for(int x=1; x < 8; x++){
	         resultsTable.getColumnModel().getColumn(x).setCellRenderer( centerRender );
	    }
		
		// Always display team name properly
		resultsTable.getColumnModel().getColumn(0).setMinWidth(120);

		// Disable editing
		resultsTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(resultsTable);
		
		add(scrollPane);
		
		// Adjust dimensions
		setMinimumSize(new Dimension(100, 500));
		setPreferredSize(new Dimension(800, 500));
		setMaximumSize(new Dimension(900, 500));
	}
}
