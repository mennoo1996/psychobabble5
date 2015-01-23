/**
 * GUI Class that allows the player to choose which save game to load
 */
package swinggui;

import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import game.Game;
import game.GameList;

@SuppressWarnings("serial")
public class LoadPanel extends JPanel implements MouseListener {
	
	private GameList theList;
	private ActionListener gameChoiceListener;
	private ArrayList<LoadGameScrollPanel> gamePanels;
	private int oldSelection;
	
	private JButton loadButton;
	
	private Dimension minSize = new Dimension(20,20);
	private Dimension prefSize = new Dimension(40, 20);
	
	/**
	 * Create and initialize a LoadPanel
	 * @param yourGames List of save games
	 * @param choiceListener Event listener for the load game choice
	 */
	public LoadPanel(GameList yourGames, ActionListener choiceListener) {
		theList = yourGames;
		gameChoiceListener = choiceListener;
		oldSelection = 0;
		
		initUI();
	}
	
	/**
	 * Initialize the GUI elements contained within the LoadPanel
	 */
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(new Box.Filler(minSize, prefSize, null));
		
		JPanel gameChoicePanel = new JPanel();
		gameChoicePanel.setName("Panel");
		gameChoicePanel.setLayout(new BoxLayout(gameChoicePanel, BoxLayout.Y_AXIS));
		
		// Add Load Title Label
		JPanel title = new JPanel();
		JLabel titleLabel = new JLabel("Choose your saved game:");
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
		title.setMaximumSize(new Dimension(2000,40));
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
		title.add(titleLabel);
		gameChoicePanel.add(title);
		
		// For each save game create a jpanel, which has this as mouse listener
		JPanel saveGameContentPane = new JPanel();
		saveGameContentPane.setLayout(new BoxLayout(saveGameContentPane, BoxLayout.Y_AXIS));
		
		// Fetch the save games & set up indices
		ArrayList<Game> theGames = theList.getGames();
		gamePanels = new ArrayList<LoadGameScrollPanel>();
		
		// Iterate through the save games
		for (int i = 0; i < theGames.size(); i++) {
			LoadGameScrollPanel nextGame = new LoadGameScrollPanel(i, theGames.get(i), this);
			nextGame.setMaximumSize(new Dimension(2000,50));
			gamePanels.add(nextGame);
			
			saveGameContentPane.add(nextGame);
		}
		
		gamePanels.get(0).toggleSelected();
		
		// Create the scroll panel
		JScrollPane scrollPane = new JScrollPane(saveGameContentPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(17);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
		scrollPane.setMinimumSize(new Dimension(100,500));
		scrollPane.setPreferredSize(new Dimension(450,500));
		scrollPane.setMaximumSize(new Dimension(900,500));

		gameChoicePanel.add(scrollPane);
		
		// Add the load game button
		loadButton = new JButton("Load This Game");
		loadButton.setName("Test");
		loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loadButton.putClientProperty("gameIndex", 0);
		loadButton.addActionListener(gameChoiceListener);
		
		gameChoicePanel.add(loadButton);
		
		add(gameChoicePanel);
		
		add(new Box.Filler(minSize, prefSize, null));
	}
	
	/**
	 * Mouse click event which changes the selected save game
	 * @param e MouseEvent
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof LoadGameScrollPanel) {
			LoadGameScrollPanel selectedGame = (LoadGameScrollPanel)e.getSource();
			
			int newIndex = selectedGame.getGameIndex();
			gamePanels.get(oldSelection).toggleSelected();
			gamePanels.get(newIndex).toggleSelected();
			oldSelection = newIndex;
			
			loadButton.putClientProperty("gameIndex", oldSelection);
		}
	}
	
	
	// The rest of the "required" MouseListener interface methods
	/**
	 * nothing happens (required interface method)
	 * @param e Mouse event
	 */
	public void mouseDragged(MouseEvent e) {
		
	}
	
	/**
	 * nothing happens (required interface method)
	 * @param e Mouse event
	 */
	public void mouseEntered(MouseEvent e) {
		
	}
	
	/**
	 * nothing happens (required interface method)
	 * @param e Mouse event
	 */
	public void mouseExited(MouseEvent e) {
		
	}
	
	/**
	 * nothing happens (required interface method)
	 * @param e Mouse event
	 */
	public void mousePressed(MouseEvent e) {
		
	}
	
	/**
	 * nothing happens (required interface method)
	 * @param e Mouse event
	 */
	public void mouseReleased(MouseEvent e) {
		
	}
}
