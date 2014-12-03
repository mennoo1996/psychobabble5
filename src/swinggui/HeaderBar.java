package swinggui;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;

public class HeaderBar extends JFrame {
	public HeaderBar() {
		initUI();
	}
	
	/**
	 * Initialize the header bar 
	 */
	public final void initUI() {
		
		// Add table to main view
		GenTable firstTable = new GenTable();
		
		// Create header bar
		JPanel header = new JPanel();
		
		header.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		header.setSize(300, 50);
		
		// Add the buttons
		String[] buttons = { 
				"Overview",
				"Statistics",
				"Play",
				"Roster",
				"Transfers"
		};
		
		for (int i = 0; i < buttons.length; i++) {
			header.add(new JButton(buttons[i]));
		}
		
		add(header, BorderLayout.NORTH);
		
		add(firstTable, BorderLayout.SOUTH);
		
		pack();
		
		setLayout(new BorderLayout());
		
		// Make fullscreen
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int) tk.getScreenSize().getWidth();
		int ySize = (int) tk.getScreenSize().getHeight();
		setSize(xSize, ySize);
		setTitle("Football Manager 2015");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				HeaderBar ex = new HeaderBar();
				ex.setVisible(true);
			}
		});
	}
}
