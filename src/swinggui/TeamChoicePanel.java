package swinggui;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;

import libraryClasses.Competition;

public class TeamChoicePanel extends JPanel implements MouseListener {
	
	private Competition currentComp;
	private ActionListener chooseTeamListener;
	private int selectedIndex;
	
	public TeamChoicePanel(Competition cComp, ActionListener teamChoiceListener) {
		currentComp = cComp;
		chooseTeamListener = teamChoiceListener;
		selectedIndex = 0;
		
		initUI();
	}
	
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(new TeamListPanel(currentComp.getLibrary().getLibrary(), this));
	}
	
	/**
	 * Upon selecting a new team, the team detail panel is refreshed to reflect said selection
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof TeamScrollPanel) {
			TeamScrollPanel selectedTeam = (TeamScrollPanel)e.getSource();
			// First unselect previous team, then toggle the new one
			
			selectedIndex = selectedTeam.getTeamIndex();
			System.out.println("Team at index " + selectedTeam.getTeamIndex() + " was selected");
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
