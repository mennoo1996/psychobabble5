/**
 * GUI class that displays the current season's previous and prior match results
 * @version 0.0.1
 */
package swinggui;

import game.Competition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Recent and Upcoming Fixtures");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setMaximumSize(new Dimension(2000, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		titlepanel.add(title);
		add(titlepanel);

		//fonts here
		Font fontSeparator = new Font("Avenir", Font.ROMAN_BASELINE, 12);
		Font fontHeader = new Font("Avenir", Font.ROMAN_BASELINE, 14);
		
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
			
			//table header
			JPanel headerPanel = new JPanel(); headerPanel.setLayout(new BorderLayout());
			DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
			centerRender.setHorizontalAlignment(JLabel.CENTER);
			Object headerData[][] = new Object[1][2]; headerData[0][0] = "Match"; headerData[0][1] = "Score";
			JTable headertable = new JTable(headerData, columnNamesPrev){
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			headertable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
			headertable.getColumnModel().getColumn(0).setMinWidth(120);
			headertable.getColumnModel().getColumn(1).setCellRenderer(centerRender);
			headertable.setGridColor(new Color(255,255,255,0));
			headertable.setRowHeight(25);
			//headertable.setMaximumSize(new Dimension(2000,25));
			headertable.setFont(fontHeader);
			headerPanel.add(headertable);
			headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
			headerPanel.setOpaque(true);
			//headerPanel.setMaximumSize(new Dimension(2000,20));
			headerPanel.setBackground(new Color(230,230,230));
			headertable.setBackground(new Color(230,230,230));
			add(headerPanel);
			
			
			JTable resultsTable = new JTable(previousMatches, columnNamesPrev) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			// center table items
	        resultsTable.getColumnModel().getColumn(1).setCellRenderer( centerRender );
		    resultsTable.setGridColor(new Color(200,200,200));
			
			// Always display team name properly
			resultsTable.getColumnModel().getColumn(0).setMinWidth(120);
			resultsTable.setRowHeight(20);
			// Disable editing
			resultsTable.getTableHeader().setReorderingAllowed(false);
			resultsTable.setTableHeader(null);
			//JScrollPane scrollPane = new JScrollPane(resultsTable);
			add(resultsTable);
			//add(scrollPane);
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
			
			//table header
			JPanel headerPanel = new JPanel(); //headerPanel.setLayout(new BorderLayout());
			DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
			centerRender.setHorizontalAlignment(JLabel.CENTER);
			Object headerData[][] = new Object[1][1]; headerData[0][0] = "Upcoming";
			JTable headertable = new JTable(headerData, columnNamesNext){
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			headertable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
			headertable.setGridColor(new Color(255,255,255,0));
			headertable.setRowHeight(15);
			headertable.setFont(fontHeader);
			headerPanel.add(headertable);
			headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
			headerPanel.setOpaque(true);
			headerPanel.setMaximumSize(new Dimension(2000,20));
			headerPanel.setBackground(new Color(230,230,230));
			headertable.setBackground(new Color(230,230,230));
			add(headerPanel);
			
			
			// Initialize tables
	
			
			JTable upcomingTable = new JTable(upcomingMatches, columnNamesNext) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			// Always display team name properly
			upcomingTable.getColumnModel().getColumn(0).setMinWidth(120);
			upcomingTable.getColumnModel().getColumn(0).setCellRenderer( centerRender );
			upcomingTable.setGridColor(new Color(200,200,200));
			upcomingTable.setRowHeight(20);
			// Disable editing
			upcomingTable.getTableHeader().setReorderingAllowed(false);
			upcomingTable.setTableHeader(null);
			JScrollPane scrollPaneUp = new JScrollPane(upcomingTable);
			
			add(scrollPaneUp);
		}
		
		// Adjust dimensions
		setMinimumSize(new Dimension(100,580));
		setPreferredSize(new Dimension(800,580));
		setMaximumSize(new Dimension(900,580));
	}
}
