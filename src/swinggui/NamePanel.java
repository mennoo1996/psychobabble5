/**
 * GUI Class that allows the player to enter his name for a new game
 */
package swinggui;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

@SuppressWarnings("serial")
public class NamePanel extends JPanel implements DocumentListener {
	
	private JTextField nameField;
	private JLabel instructionLabel;
	private JButton timeToPick;
	private ActionListener nameChoiceListener;
	
	private Dimension minSize = new Dimension(20,20);
	private Dimension prefSize = new Dimension(40, 20);
	
	/**
	 * Create and initialize a NamePanel
	 * @param namePickListener Event listener to pass on the player's name 
	 */
	public NamePanel(ActionListener namePickListener) {
		nameChoiceListener = namePickListener;
		
		initUI();
	}
	
	/**
	 * Initialize the GUI elements contained in the NamePanel
	 */
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new Box.Filler(minSize, prefSize, null));
		
		JPanel nameChoicePanel = new JPanel();
		nameChoicePanel.setLayout(new BoxLayout(nameChoicePanel, BoxLayout.Y_AXIS));
		nameChoicePanel.setName("Panel");
		
		// Add instructions for what to do:
		instructionLabel = new JLabel("Enter your name here:", JLabel.CENTER);
		instructionLabel.setMinimumSize(new Dimension(0, 40));
		instructionLabel.setPreferredSize(new Dimension(instructionLabel.getPreferredSize().width, 40));
		instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameChoicePanel.add(instructionLabel);
		
		// Add textfield for user's name
		nameField = new JTextField(10);
		nameField.setName("textField");
		nameField.getDocument().addDocumentListener(this);
		nameField.setMinimumSize(new Dimension(nameField.getWidth(), 41));
		nameField.setMaximumSize(new Dimension(250, 41));
		
		nameChoicePanel.add(nameField);
		
		// Add button
		timeToPick = new JButton("Pick your team");
		timeToPick.setName("Test");
		timeToPick.addActionListener(nameChoiceListener);
		timeToPick.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		timeToPick.setEnabled(false);
		nameChoicePanel.add(timeToPick);
		
		// Add name choice panel dimensions
		add(nameChoicePanel);
		add(new Box.Filler(minSize, prefSize, null));
		nameChoicePanel.setMinimumSize(new Dimension(400,240));
		nameChoicePanel.setPreferredSize(new Dimension(400,240));
	}
	
	/**
	 * Validates the name field and limits the player's name to 24 characters
	 */
	public void validateNameField() {
		if (nameField.getText().length() > 0) {
			timeToPick.setEnabled(true);
			if (nameField.getText().length() > 24) {
				nameField.setText(nameField.getText().substring(0, 24));
			}
			timeToPick.putClientProperty("playerName", nameField.getText());
		} else {
			timeToPick.setEnabled(false);
		}
	}
	
	/**
	 * Event listener for change in name entry field (addition of characters)
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		validateNameField();
	}
	
	/**
	 * Event listener for change in name entry field (deletion of characters)
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		validateNameField();
	}
	
	/**
	 * Event listener for change in name entry field
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		validateNameField();
	}
}
