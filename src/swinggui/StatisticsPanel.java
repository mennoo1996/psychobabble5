/**
 * GUI Class that displays the Statistics Window
 */
package swinggui;

import game.Competition;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import libraryClasses.Team;

@SuppressWarnings("serial")
public class StatisticsPanel extends JPanel {
	
	private Competition currentCompetition;
	private Team currentTeam;
	
	public Dimension minSize = new Dimension(20,20);
	public Dimension prefSize = new Dimension(40,20);
	
	/**
	 * Create and initialize a StatisticsPanel
	 * @param curComp - Current competition object
	 * @param curTeam - Current team object
	 */
	public StatisticsPanel(Competition curComp, Team curTeam) {
		currentCompetition = curComp;
		currentTeam = curTeam;
		initUI();
	}

	/**
	 * Initialize the GUI elements contained in the NamePanel
	 */
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new Box.Filler(minSize, prefSize, null));
		
		// add the overview panels
		add(new StandingsPanel(currentCompetition, currentTeam, true));
		add(new AgendaPanel(currentCompetition));
		
		add(new Box.Filler(minSize, prefSize, null));
	}
}
