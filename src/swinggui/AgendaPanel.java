/**
 * GUI class that displays the current season's previous and prior match results
 * @version 0.0.1
 */
package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import libraryClasses.Competition;
import libraryClasses.CompetitionScheme;
import libraryClasses.Round;
import libraryClasses.Match;

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
		headertable.getColumnModel().getColumn(1).setCellRenderer( centRender );
		headertable.getColumnModel().getColumn(0).setCellRenderer(centRender);
		//headertable.getColumnModel().getColumn(0).setMinWidth(250);
		headertable.getColumnModel().getColumn(1).setMaxWidth(150);
		headertable.setGridColor(new Color(255,255,255,0));
		headertable.setRowHeight(25);
		headertable.setFont(fontHeader);
		headerPanel.add(headertable);
		JPanel headerPanel3 = new JPanel(); headerPanel3.setMinimumSize(new Dimension(30,14));
		headerPanel.add(headerPanel3, BorderLayout.EAST);
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		headerPanel.setBackground(new Color(230,230,230));
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
					curMatch.getTeam1() + " vs " + curMatch.getTeam2(),
					scoreString
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
			//table.getColumnModel().getColumn(0).setMinWidth(250);
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
		
		//resultsTable.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(160,160,160)));
		//resultsTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160,160,160)));
		resultsTable.getTableHeader().setFont(new Font("Avenir", Font.ROMAN_BASELINE, 15));
		resultsTable.setGridColor(new Color(180,180,180));
		resultsTable.setTableHeader(null);
		// Disable editing
		//resultsTable.getTableHeader().setReorderingAllowed(false);
		
		
		//scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(160,160,160)));
		JScrollPane ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(ScrollPane);
		//scrollPane.setPreferredSize(new Dimension(800,500));
		
		// Adjust dimensions
		setMinimumSize(new Dimension(100,500));
		setPreferredSize(new Dimension(800,550));
		setMaximumSize(new Dimension(900,600));
	}
}

class SpreadsheetCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        /* the following is the similar to DefaultTableCellRenderer */
        if (isSelected) {
            super.setForeground(Color.red);
        } else {
            super.setForeground(Color.red);
            super.setBackground(Color.black);
            System.out.println("LOOOOL");
        }
    setText(value.toString());
    return this;
    }
}


class HeaderRenderer implements TableCellRenderer {

    TableCellRenderer renderer;

    public HeaderRenderer(JTable table) {
        renderer = table.getTableHeader().getDefaultRenderer();
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int col) {
        return renderer.getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, col);
    }
}

