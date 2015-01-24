/**
 * GUI Class that displays the splash screen for the game, with logo and choice of game type (i.e. new or saved)
 */
package swinggui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	
	/**
	 * Create and initialize a SplashPanel
	 * @param frame Event listener (the frame) which processes the game type choice
	 * @param hasSaveGames Whether or not there is/are saved game(s)
	 */
	public SplashPanel(ActionListener frame, boolean hasSaveGames) {
		gameChoice = frame;
		showLoadButton = hasSaveGames;
		
		initUI();
	}
	
	/**
	 * Initialize GUI elements contained in the SplashPanel
	 */
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(new Box.Filler(minSize, prefSize, null));
		
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		selectionPanel.setName("Panel");
		
		// MARK: LOGO HERE? YES LOGO HERE ALEX, LOGO HERE
		ImageIcon logo = createImageIcon("images/logo.png");
		JLabel logolabel = new JLabel (logo);
		logolabel.setOpaque(true);
		logolabel.setBackground(new Color(245,245,245));
		logolabel.setAlignmentX(CENTER_ALIGNMENT);
		logolabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		logolabel.setMaximumSize(new Dimension(2000,160));
		selectionPanel.add(logolabel);
		
		// Add title
		JLabel titleLabel = new JLabel("Football Manager", JLabel.CENTER);
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//selectionPanel.add(titleLabel);
		
		selectionPanel.add(new Box.Filler(new Dimension(0,4), null, null));
		
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
		
		selectionPanel.setMinimumSize(new Dimension(550,445));
		selectionPanel.setPreferredSize(new Dimension(550,445));
		selectionPanel.setMaximumSize(new Dimension(550,445));
		
		add(selectionPanel);
		
		add(new Box.Filler(minSize, prefSize, null));		
	}
	
	/**
	 * @param path the path to the image
	 * @return the located image, null if invalid file path
	 */
	public ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, null);
		}
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
}
