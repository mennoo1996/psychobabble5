package swinggui;

// Import player here
import libraryClasses.*;
import xmlIO.XMLParser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GenTable extends JPanel {
	private JTable table;
	
	public GenTable() {
		initUI();
	}
	
	/**
	 * Initialize the table within a scroll pane
	 */
	public final void initUI() {
		
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v2.xml", "files/competition-scheme.xml");
		
		Team team = competition.getLibrary().getLibrary().get(0);
		
		Object rowDate[][] = new Object[team.getTeam().size()][3];
		
		for (int i = 0; i < team.getTeam().size(); i++) {
			Player guy = team.getTeam().get(i);
			Object newRow[] = { guy.getName(), guy.getAge(), guy.getNumber() };
		    rowDate[i] = newRow;
		}
		
//		Object rowDate[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
//				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
//		
		Object columnNames[] = { "Column 1", "Column2", "Column3" };
		
		table = new JTable(rowDate,columnNames);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setPreferredSize(new Dimension(300,350));
		this.add(scrollPane,BorderLayout.CENTER);
		this.setSize(300, 150);
	}
	
	public void addPlayers(ArrayList<String> players) {
		// Adjust the player model here
	}
	
	public static void main(String[] args) {
		GenTable thing = new GenTable();
		thing.setVisible(true);
	}
}
