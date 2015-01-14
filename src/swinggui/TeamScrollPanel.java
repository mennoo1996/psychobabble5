package swinggui;

import java.awt.event.MouseListener;
import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TeamScrollPanel extends JPanel {
	private int teamIndex;
	private MouseListener clickTrigger;
	private boolean isSelected;
	
	public TeamScrollPanel(int index, MouseListener changeTeamListener) {
		teamIndex = index;
		clickTrigger = changeTeamListener;
		isSelected = false;
		
		setOpaque(true);
		
		this.addMouseListener(clickTrigger);
	}
	
	public int getTeamIndex() {
		return teamIndex;
	}
	
	public void toggleSelected() {
		if (isSelected) {
			setBackground(Color.WHITE);
		} else {
			// Make this a nicer color how about this? :)
			setBackground(new Color(135, 206, 250));
		}
		revalidate();
		repaint();
		isSelected = !isSelected;
	}
}