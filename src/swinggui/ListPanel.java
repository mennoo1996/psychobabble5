/**
 * GUI class to extend JPanel to hold player Objects
 * @version 0.0.1
 */
package swinggui;

import javax.swing.JPanel;

import libraryClasses.Player;

@SuppressWarnings("serial")
public class ListPanel extends JPanel {
	
	private Player player;
	
	/**
	 * Initialize the ListPanel
	 * @param player - Player object attached to panel
	 */
	public ListPanel(Player player){
		this.player = player;
	}	
	
	/**
	 * return Player variable
	 */
	public Player getPlayer(){
		return player;
	}
	
}
