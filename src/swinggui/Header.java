package swinggui;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Header extends JPanel {
	
	private ActionListener buttonListener;	
	
	public Header(ActionListener listener) {
		buttonListener = listener;
		initUI();
	}
	
	/**
	 * Initialize the header in a jpanel
	 */
	public final void initUI() {
		//new panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		//button text
		String[] ButtonText = {
				"overview ",
				"statistics",
				"",
				"positions ",
				"transfers"
		};
		
		//button names to reference proper style
		String[] ButtonName = {
				"HeaderOuter",
				"HeaderMiddle",
				"HeaderInner",
				"HeaderMiddle",
				"HeaderOuter2"
		};
		
		// Shortcut keys
		Character[] menuKey = { 
				KeyEvent.VK_O,
				KeyEvent.VK_S,
				KeyEvent.VK_R,
				KeyEvent.VK_P,
				KeyEvent.VK_T
		};
		
		//create buttons
		for(int i = 0 ; i < 5 ; i++){
			JButton button = new JButton(ButtonText[i]);
			button.setMnemonic(menuKey[i]);
			button.setDisplayedMnemonicIndex(-1);
			button.setName(ButtonName[i]);
			if (i == 2) {
				button.setToolTipText("play");
			}
			button.addActionListener(buttonListener);
			
			panel.add(button);
		}
		
		//add panel
		this.add(panel);
	}
	
}
