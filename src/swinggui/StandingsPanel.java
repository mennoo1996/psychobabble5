/**
 * GUI class that displays the current season's standings
 * @version 0.0.1
 */
package swinggui;

import game.Competition;

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

import libraryClasses.Standings;

@SuppressWarnings("serial")
public class StandingsPanel extends JPanel {
	
	private Competition currentCompetition;
	private boolean detailedLayout;
	
	/**
	 * Create and initialize StandingsPanel object
	 * @param curComp current competition
	 * @param hasDetail show detailed statistics or just the points
	 */
	public StandingsPanel(Competition curComp, boolean hasDetail) {
		currentCompetition = curComp;
		detailedLayout = hasDetail;
		
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
		
		int detail = detailedLayout ? 8 : 2;
		Object teamData[][] = new Object[numTeams][detail];
		
		// Fetch standings and then populate table data
		ArrayList<Standings> sortedRes = currentCompetition.getSortedStandings();
		for (int i = 0; i < numTeams; i++) {
			Standings standing = sortedRes.get(i);
			
			// Detailed or simple layout
			if (detailedLayout) { 
				Object teamRowComp[] = {
						standing.getTeamName(),
						standing.getPoints(),
						standing.getWon(),
						standing.getDraw(),
						standing.getLost(),
						standing.getGoalDifference(),
						standing.getGoalsFor(),
						standing.getGoalsAgainst()
				};
				
				teamData[i] = teamRowComp;
			} else {
				Object teamRowSimp[] = {
						standing.getTeamName(),
						standing.getPoints()
				};
				teamData[i] = teamRowSimp;
			}
		}
		
		Object columnNamesComplex[] =  { 
				"Team",
				"Points",
				"Won",
				"Draw",
				"Lost",
				"Goal Diff",
				"Goals for",
				"Goals against"
		};
		
		Object columnNamesSimp[] = {
				"Team",
				"Points"
		};
		
		// Initialize table
		JTable resultsTable = new JTable(teamData, (detailedLayout) ? columnNamesComplex : columnNamesSimp) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// center table items
		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		for(int x=1; x < detail; x++){
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
