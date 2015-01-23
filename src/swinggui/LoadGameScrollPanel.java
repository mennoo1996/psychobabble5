/**
 * GUI class that displays a single save game's information in a JPanel
 */
package swinggui;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import game.Game;

@SuppressWarnings("serial")
public class LoadGameScrollPanel extends JPanel {
	
	private int gameIndex;
	private Game loadGame;
	private MouseListener clickTrigger;
	private boolean isSelected;
	
	/**
	 * Create and initialize a LoadGameScrollPanel JPanel
	 * @param index Index of the save game in the current GameList
	 * @param gameToLoad The save game to display
	 * @param changeGameListener MouseListener for a click event
	 */
	public LoadGameScrollPanel(int index, Game gameToLoad, MouseListener changeGameListener) {
		gameIndex = index;
		loadGame = gameToLoad;
		clickTrigger = changeGameListener;
		isSelected = false;
		
		addMouseListener(clickTrigger);
		
		setOpaque(true);
		
		initUI();
	}
	
	/**
	 * @return The saved game's index in the current GameList
	 */
	public int getGameIndex() {
		return gameIndex;
	}
	
	/**
	 * Toggle whether or not this instance is selected. When selected the LoadGameScrollPanel is light blue instead of white
	 */
	public void toggleSelected() {
		if (isSelected) {
			setBackground(Color.WHITE);
		} else {
			setBackground(new Color(221,244,255));
		}
		revalidate();
		repaint();
		isSelected = !isSelected;
	}
	
	/**
	 * Initialize the GUI elements contained with the LoadGameScrollPanel
	 */
	public final void initUI() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
		
		// Add game name label
		JLabel gameLabel = new JLabel((gameIndex+1) + ": " + loadGame.getName() + ", " + loadGame.getTeam().getTeamName() + ", round: " + loadGame.getCompetition().getRoundsPlayed(), JLabel.CENTER);
		gameLabel.setFont(new Font("Avenir", Font.ROMAN_BASELINE, 16));
		gameLabel.setMinimumSize(new Dimension(20, 50));
		gameLabel.setPreferredSize(new Dimension(90, 50));
		gameLabel.setMaximumSize(new Dimension(100, 50));
		
		add(gameLabel, BorderLayout.CENTER);
	}
}
