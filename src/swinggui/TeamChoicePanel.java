package swinggui;

import game.Competition;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TeamChoicePanel extends JPanel implements MouseListener {
	
	private Competition currentComp;
	private ActionListener chooseTeamListener;
	private int selectedIndex;
	private TeamListPanel teamPanel;
	private TeamDetailPanel deets;
	private Dimension minSize = new Dimension(20,20);
	private Dimension prefSize = new Dimension(40,20);
	
	public TeamChoicePanel(Competition cComp, ActionListener teamChoiceListener) {
		currentComp = cComp;
		chooseTeamListener = teamChoiceListener;
		selectedIndex = 0;
		
		initUI();
	}
	
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(new Box.Filler(minSize, prefSize, null));
		
		teamPanel = new TeamListPanel(currentComp.getLibrary().getLibrary(), this);
		add(teamPanel);
		
		deets = new TeamDetailPanel(currentComp.getLibrary().getLibrary().get(selectedIndex), chooseTeamListener);
		add(deets);
		
		add(new Box.Filler(minSize, prefSize, null));
	}
	
	/**
	 * Upon selecting a new team, the team detail panel is refreshed to reflect said selection
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof TeamScrollPanel) {
			TeamScrollPanel selectedTeam = (TeamScrollPanel)e.getSource();
			
			selectedIndex = selectedTeam.getTeamIndex();
			// First unselect previous team, then toggle the new one
			teamPanel.newSelection(selectedIndex);
			
			// Replace detail panel with the newly selected team
			remove(deets);
			TeamDetailPanel refresh = new TeamDetailPanel(currentComp.getLibrary().getLibrary().get(selectedIndex), chooseTeamListener);
			add(refresh, 2);
			deets = refresh;
			revalidate();
			repaint();
		}
	}
	
	// The rest of the (unnecessary) mouselistener methods
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	

}
