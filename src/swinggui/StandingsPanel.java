/**
 * GUI class that displays the current season's standings
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
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import libraryClasses.Standings;
import libraryClasses.Team;

@SuppressWarnings("serial")
public class StandingsPanel extends JPanel {
	
	private Competition currentCompetition;
	private Team currentTeam;
	private int isTeam = 0;
	private boolean detailedLayout;
	
	/**
	 * Create and initialize StandingsPanel object
	 * @param curComp current competition
	 * @param hasDetail show detailed statistics or just the points
	 */
	public StandingsPanel(Competition curComp, Team curTeam, boolean hasDetail) {
		currentTeam = curTeam;
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
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Current Standings (Round " + currentCompetition.getRoundsPlayed() + ")");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setMaximumSize(new Dimension(2000, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		titlepanel.add(title);
		add(titlepanel);

		//fonts here
		Font fontHeader = new Font("Avenir", Font.ROMAN_BASELINE, 14);
		
		// Preallocate row array
		int numTeams = currentCompetition.getLibrary().getLibrary().size();
		int detail = detailedLayout ? 5 : 2;
		DefaultTableCellRenderer centRender = new DefaultTableCellRenderer();
		centRender.setHorizontalAlignment(JLabel.CENTER);
		Object teamData1[][] = new Object[numTeams][detail];
		Object teamData2[][] = new Object[numTeams][detail];
		Object columnNames1[] = new Object[1];
		Object columnNames2[] = new Object[1];
		Object headerData1[][] = new Object[1][detail];
		Object headerData2[][] = new Object[1][1];
		
		if(detailedLayout) {				// detailed
			columnNames1 = new Object[] { 
					"Team",
					"Points",
					"Won",
					"Draw",
					"Lost"
			};
			teamData2 = new Object[numTeams][4];
			headerData2 = new Object[1][4];
			columnNames2 = new Object[] {
					"Team",
					"Goal Diff",
					"Goals for",
					"Goals against"
			};
			for(int i=0;i<columnNames2.length;i++) {
				headerData2[0][i] = columnNames2[i];
			}
		} else {							// not detailed
			columnNames1 = new Object[] {
					"Team",
					"Points"
			};
		}
		
		for(int i=0;i<columnNames1.length;i++) {
			headerData1[0][i] = columnNames1[i];
		}

		//construct headers and tables in loop
		int detail2 = detailedLayout ? 2 : 1;
		for(int i=0;i<detail2;i++){
			
			// header
			JPanel headerPanel = new JPanel();
			headerPanel.setLayout(new BorderLayout());
			Object[][] headerData;
			Object[] columnNames;
			if(i==1){
				headerData =  headerData2;
				columnNames = columnNames2;
			} else{
				headerData = headerData1;
				columnNames = columnNames1;
			}
			
			JTable headertable = new JTable(headerData, columnNames){
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			for(int j=0;j<columnNames.length;j++) {
				headertable.getColumnModel().getColumn(j).setCellRenderer(centRender);
			}
			
			headertable.getColumnModel().getColumn(0).setMinWidth(120);
			headertable.setGridColor(new Color(255,255,255,0));
			headertable.setRowHeight(25);
			headertable.setFont(fontHeader);
			headerPanel.add(headertable);
			headerPanel.add(headertable);
			
			if(i == 1) {
				headerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(180,180,180)));
			} else {
				headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
			}
			
			headerPanel.setOpaque(true);
			headertable.setBackground(new Color(230,230,230));
			headerPanel.setMaximumSize(new Dimension(2000, 10));
			add(headerPanel);
			
			// Fetch standings and then populate table data
			ArrayList<Standings> sortedRes = currentCompetition.getSortedStandings();
			
			
			for (int k = 0; k < numTeams; k++) {
				Standings standing = sortedRes.get(k);
				if(standing.getTeamName().equals(currentTeam.getTeamName())) {
					isTeam = k;
				}
				// Detailed or simple layout
				if (detailedLayout) { 
					if(i==1) {
						Object teamRowComp[] = {
								standing.getTeamName(),
								standing.getGoalDifference(),
								standing.getGoalsFor(),
								standing.getGoalsAgainst()
						};
						
						teamData2[k] = teamRowComp;
					} else {
						Object teamRowComp[] = {
								standing.getTeamName(),
								standing.getPoints(),
								standing.getWon(),
								standing.getDraw(),
								standing.getLost()
						};
						
						teamData1[k] = teamRowComp;
					}
				} else {
					Object teamRowSimp[] = {
							standing.getTeamName(),
							standing.getPoints()
					};
					
					teamData1[k] = teamRowSimp;
				}
			}
			
			Object teamData[][];
			if(i==1){
				teamData =  teamData2;
			} else{
				teamData = teamData1;
			}
			
			
			
			// Initialize table
			JTable resultsTable = new JTable(teamData, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

				 public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			        Component c = super.prepareRenderer(renderer, row, column);
			        c.setBackground(row % 2 == 0 ? getBackground() : new Color(245,245,245));
			        JComponent jc = (JComponent)c;
			        
			        if (row==isTeam){
			        	jc.setBackground(new Color(221,244,255));
			        }

			        return c;
				 }
			};
			
			//resultsTable.changeSelection(0, 0, false, false);
			resultsTable.changeSelection(0, 0, false, false);
			
			// center table items
			DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
			centerRender.setHorizontalAlignment(JLabel.CENTER);
			
			if(i == 1){
				for(int x=1; x < 4; x++){
			         resultsTable.getColumnModel().getColumn(x).setCellRenderer( centerRender );
			    }
			} else if(detailedLayout) {
				for(int x=1; x < 5; x++){
			         resultsTable.getColumnModel().getColumn(x).setCellRenderer( centerRender );
			    }
			} else {
				for(int x=1; x < 2; x++){
			         resultsTable.getColumnModel().getColumn(x).setCellRenderer( centerRender );
			    }
			}
			
			
			// Always display team name properly
			resultsTable.getColumnModel().getColumn(0).setMinWidth(120);
			resultsTable.setGridColor(new Color(200,200,200));

			// Disable editing
			resultsTable.getTableHeader().setReorderingAllowed(false);
			resultsTable.setTableHeader(null);
			resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			
			if(detailedLayout) {
				JScrollPane scrollPane = new JScrollPane(resultsTable);
				resultsTable.setRowHeight(19);
				add(scrollPane);
			} else {
				JPanel scrollPane = new JPanel(new BorderLayout()); 
				resultsTable.setRowHeight(19);
				scrollPane.setMaximumSize(new Dimension(2000,2000));
				scrollPane.add(resultsTable);
				add(scrollPane);
				JPanel stretchPane = new JPanel();
				JLabel content = new JLabel();
				int roundsleft = 38 - currentCompetition.getRoundsPlayed();
				content = new JLabel("Rounds left: " + roundsleft);
				content.setFont(new Font("Avenir", Font.ROMAN_BASELINE, 20));
				stretchPane.add(content);
				stretchPane.setBackground(new Color(240,240,240));
				stretchPane.setOpaque(true);
				add(stretchPane);
			}
			
			
			
			if(i==0 && detailedLayout) {
				JPanel filler = new JPanel();
				filler.setBackground(new Color(230,230,230));
				filler.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180,180,180)));
				filler.setOpaque(true);
				add(filler);
			}
			
		}
		
		// Adjust dimensions
		setMinimumSize(new Dimension(100, 580));
		setPreferredSize(new Dimension(600, 580));
		setMaximumSize(new Dimension(800, 580));
	}
}
