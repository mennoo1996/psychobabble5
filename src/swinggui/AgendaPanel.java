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
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

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
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Fixtures");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160,160,160)));
		titlepanel.add(title);
		add(titlepanel);
		
		//fonts here
		Font fontSeparator = new Font("Avenir", Font.ROMAN_BASELINE, 12);
		Font fontHeader = new Font("Avenir", Font.ROMAN_BASELINE, 14);
		
		// Preallocate row array
		Object teamData[][] = new Object[417][2];
		
		// Fetch scheme and then populate table data
		CompetitionScheme curScheme = currentCompetition.getScheme();
		DefaultTableCellRenderer centRender = new DefaultTableCellRenderer();
		centRender.setHorizontalAlignment(JLabel.CENTER);
		
		// column names
		Object columnNames[] = { 
				"Match",
				"Score",
		};
		
		JPanel ScrollPaneContent = new JPanel(); ScrollPaneContent.setLayout(new BoxLayout(ScrollPaneContent, BoxLayout.Y_AXIS));
		
		//table header
		JPanel headerPanel = new JPanel(); headerPanel.setLayout(new BorderLayout());
		
		Object headerData[][] = new Object[1][2]; headerData[0][0] = "Match"; headerData[0][1] = "Score";
		JTable headertable = new JTable(headerData, columnNames){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		headertable.getColumnModel().getColumn(1).setCellRenderer(centRender);
		headertable.getColumnModel().getColumn(0).setCellRenderer(centRender);
		headertable.getColumnModel().getColumn(1).setMaxWidth(150);
		headertable.setGridColor(new Color(255,255,255,0));
		headertable.setRowHeight(25);
		headertable.setFont(fontHeader);
		headerPanel.add(headertable);
		JPanel headerPanel3 = new JPanel(); headerPanel3.setMinimumSize(new Dimension(30,14));
		headerPanel3.setBackground(new Color(230,230,230)); headerPanel3.setOpaque(true);
		headerPanel.add(headerPanel3, BorderLayout.EAST);
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		headerPanel.setOpaque(true);
		headertable.setBackground(new Color(230,230,230));
		
		add(headerPanel);
		
		// Loop through 38 rounds
		for (int i = 1; i <= 38; i++) {
			ArrayList<Match> roundsMatches = curScheme.getRound(i).getMatches();
			//each match
			Object Data[][] = new Object[10][2];
			for (int j = 0; j < 10; j++) {
				Match curMatch = roundsMatches.get(j);
				String scoreString = "-";
				if (curMatch.getScoreTeam1() != -1) {
					scoreString = curMatch.getScoreTeam1() + " - " + curMatch.getScoreTeam2();
				}
				Object matchRow[] = {
					curMatch.getTeam1() + " vs " + curMatch.getTeam2(), scoreString
				};
				Data[j] = matchRow;
			}
			
			//define table
			JTable table = new JTable(Data, columnNames){
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			table.getColumnModel().getColumn(1).setCellRenderer(centRender);
			table.getColumnModel().getColumn(0).setCellRenderer(centRender);
			table.getColumnModel().getColumn(1).setMaxWidth(150);
			table.setGridColor(new Color(150,150,150));

			//add a separator
			JPanel separator = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
			separator.setOpaque(true);
			separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(180,180,180)));
			separator.setBackground(new Color(240,240,240));

			//separator title
			JLabel separatortitle = new JLabel("Round " + i); separatortitle.setMinimumSize(new Dimension(0,10));
			separatortitle.setFont(fontSeparator);
			separatortitle.setPreferredSize(new Dimension(60,20));
			separator.add(separatortitle);
			ScrollPaneContent.add(separator);
			
			//finish up
			ScrollPaneContent.add(separator);
			ScrollPaneContent.add(table);
		}
		
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
		resultsTable.getTableHeader().setFont(new Font("Avenir", Font.ROMAN_BASELINE, 15));
		resultsTable.setGridColor(new Color(180,180,180));
		resultsTable.setTableHeader(null);

		JScrollPane ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(ScrollPane);
		
		// Adjust dimensions
		setMinimumSize(new Dimension(100,600));
		setPreferredSize(new Dimension(700,600));
		setMaximumSize(new Dimension(800,600));
	}
}

