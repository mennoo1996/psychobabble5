package swinggui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;

public class HeaderBar extends JPanel {
	private ActionListener buttonListener;
	
	public HeaderBar(ActionListener listener) {
		buttonListener = listener;
		initUI();
	}
	
	/**
	 * Initialize the header bar 
	 */
	public final void initUI() {
		
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setSize(300, 50);
		
		// Add the buttons
		String[] buttons = { 
				"Overview",
				"Statistics",
				"Play",
				"Roster",
				"Transfers"
		};
		
		// Shortcut keys
		Character[] menuKey = { 
				KeyEvent.VK_O,
				KeyEvent.VK_S,
				KeyEvent.VK_P,
				KeyEvent.VK_R,
				KeyEvent.VK_T
		};
		
		for (int i = 0; i < buttons.length; i++) {
			JButton menuItem = new JButton(buttons[i]);
			
			menuItem.setMnemonic(menuKey[i]);
			
			final String name = menuItem.getText();
			
			menuItem.addActionListener(buttonListener);
			
			add(menuItem);
		}
		
	}
}
