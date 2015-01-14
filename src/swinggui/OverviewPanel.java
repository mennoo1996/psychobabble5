package swinggui;

import game.Competition;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OverviewPanel extends JPanel {
	
	private Competition currentComp;
	
	public Dimension minSize = new Dimension(20,20);
	public Dimension prefSize = new Dimension(40,20);
	
	public OverviewPanel(Competition cComp) {
		currentComp = cComp;
		
		initUI();
	}
	
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new Box.Filler(minSize, prefSize, null));
		
		// add the overview panels
		add(new StandingsPanel(currentComp, false));
		add(new RecentMatchesPanel(currentComp));
		
		add(new Box.Filler(minSize, prefSize, null));
	}
}
