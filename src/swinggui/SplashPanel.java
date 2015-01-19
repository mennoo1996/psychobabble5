package swinggui;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class SplashPanel extends JPanel {
	private ActionListener gameChoice;
	private Dimension minSize = new Dimension(20, 20);
	private Dimension prefSize = new Dimension(40, 20);
	private boolean showLoadButton;
	
	public SplashPanel(ActionListener frame, boolean hasSaveGames) {
		gameChoice = frame;
		showLoadButton = hasSaveGames;
		
		initUI();
	}
	
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(new Box.Filler(minSize, prefSize, null));
		
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		selectionPanel.setName("Panel");
		
		// MARK: LOGO HERE?
		
		// Add title
		JLabel titleLabel = new JLabel("Football Manager", JLabel.CENTER);
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(titleLabel);
		
		// Load game button 
		if (showLoadButton) {
			JButton loadGameButton = new JButton("Load Game");
			loadGameButton.setName("Test");
			loadGameButton.addActionListener(gameChoice);
			loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			selectionPanel.add(loadGameButton);
		}
		
		// New game button
		JButton newGameButton = new JButton("New Game");
		newGameButton.setName("Test");
		newGameButton.addActionListener(gameChoice);
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(newGameButton);
		
		selectionPanel.setMinimumSize(new Dimension(200,450));
		selectionPanel.setPreferredSize(new Dimension(450,550));
		selectionPanel.setMaximumSize(new Dimension(900,600));
		
		add(selectionPanel);
		
		add(new Box.Filler(minSize, prefSize, null));		
	}
}
