/**
 * GUI Class that shows the team name
 */
package swinggui;

import java.awt.event.MouseListener;
import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TeamScrollPanel extends JPanel {
	private int teamIndex;
	private MouseListener clickTrigger;
	private boolean isSelected;
	
	/**
	 * Create and initialize a TeamScrollPanel
	 * @param index Index of the selected team
	 * @param changeTeamListener Event listener to notify if the TeamScrollPanel has been clicked
	 */
	public TeamScrollPanel(int index, MouseListener changeTeamListener) {
		teamIndex = index;
		clickTrigger = changeTeamListener;
		isSelected = false;
		
		setOpaque(true);
		
		this.addMouseListener(clickTrigger);
	}
	
	/**
	 * @return the index of the displayed team
	 */
	public int getTeamIndex() {
		return teamIndex;
	}
	
	/**
	 * Toggles whether or not the current TeamScrollPanel is selected (changes the background color)
	 */
	public void toggleSelected() {
		if (isSelected) {
			setBackground(Color.WHITE);
		} else {
			// Make this a nicer color how about this? :)
			setBackground(new Color(221,244,255));
		}
		revalidate();
		repaint();
		isSelected = !isSelected;
	}
}