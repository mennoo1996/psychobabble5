package swinggui;

import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class TeamScrollPanel extends JPanel {
	private int teamIndex;
	private MouseListener clickTrigger;
	
	public TeamScrollPanel(int index, MouseListener changeTeamListener) {
		teamIndex = index;
		clickTrigger = changeTeamListener;
		
		this.addMouseListener(clickTrigger);
	}
	
	public int getTeamIndex() {
		return teamIndex;
	}
}