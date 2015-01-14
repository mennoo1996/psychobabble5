package swinggui;

import java.awt.event.MouseListener;

import javax.swing.JPanel;

import libraryClasses.Player;

//JPanel with attached Player Back Number
public class ListPanel extends JPanel {
	
	private Player player;
	
	public ListPanel(Player player){
		this.player = player;
		
	}	
	public Player getPlayer(){
		return player;
	}
	
}
