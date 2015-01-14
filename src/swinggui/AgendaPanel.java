/**
 * GUI class that displays the current season's previous and prior match results
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import schemeClasses.CompetitionScheme;
import schemeClasses.Match;

@SuppressWarnings("serial")
public class AgendaPanel extends JPanel {
	
	private Competition currentCompetition;
	
	/**
	 * Create and initialize AgendaPanel object
	 * @param curComp current competition
	 */
	public AgendaPanel(Competition curComp) {
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
		
		JLabel agendaTitle = new JLabel("Fixtures");
		agendaTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(agendaTitle);
		
		
		// Preallocate row array
		Object teamData[][] = new Object[417][2];
		
		// Fetch scheme and then populate table data
		CompetitionScheme curScheme = currentCompetition.getScheme();
		
		// Loop through 38 rounds
		for (int i = 1; i <= 38; i++) {
			ArrayList<Match> roundsMatches = curScheme.getRound(i).getMatches();

			// Each match
			for (int j = 0; j < 10; j++) {
				Match curMatch = roundsMatches.get(j);
				
				String scoreString = "-";
				if (curMatch.getScoreTeam1() != -1) {
					scoreString = curMatch.getScoreTeam1() + " - " + curMatch.getScoreTeam2();
				}
				
				Object matchRow[] = {
						curMatch.getTeam1() + " vs " + curMatch.getTeam2(),
						scoreString
				};
				teamData[(i-1)*11 + j] = matchRow;
			}
			if (i > 1 && i < 38) {
				Object spacing[] = { " ", " " };
				teamData[(i-1)*11 + 10] = spacing;
			}
		}
		
		Object columnNames[] = { 
				"Match",
				"Score"
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
        resultsTable.getColumnModel().getColumn(1).setCellRenderer( centerRender );
	    
		
		// Always display team name properly
		resultsTable.getColumnModel().getColumn(0).setMinWidth(120);

		// Disable editing
		resultsTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(resultsTable);
		
		add(scrollPane);
		
		// Adjust dimensions
		setMinimumSize(new Dimension(100,500));
		setPreferredSize(new Dimension(800,500));
		setMaximumSize(new Dimension(900,500));
	}
}
