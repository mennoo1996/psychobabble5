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
	
	public LoadGameScrollPanel(int index, Game gameToLoad, MouseListener changeGameListener) {
		gameIndex = index;
		loadGame = gameToLoad;
		clickTrigger = changeGameListener;
		isSelected = false;
		
		addMouseListener(clickTrigger);
		
		setOpaque(true);
		
		initUI();
	}
	
	public int getGameIndex() {
		return gameIndex;
	}
	
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
	
	public final void initUI() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
		
		// Add game name label
		JLabel gameLabel = new JLabel((gameIndex+1) + ": " + loadGame.getName() + ", " + loadGame.getTeam().getTeamName(), JLabel.CENTER);
		gameLabel.setFont(new Font("Avenir", Font.ROMAN_BASELINE, 16));
		gameLabel.setMinimumSize(new Dimension(20, 50));
		gameLabel.setPreferredSize(new Dimension(90, 50));
		gameLabel.setMaximumSize(new Dimension(100, 50));
		
		add(gameLabel, BorderLayout.CENTER);
	}
}
