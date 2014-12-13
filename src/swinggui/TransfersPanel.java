package swinggui;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;

public class TransfersPanel extends JPanel {
	
	public Dimension minSize = new Dimension(20,20);
	public Dimension prefSize = new Dimension(40,20);
	
	public TransfersPanel() {
		initUI();
	}
	
	public final void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new Box.Filler(minSize, prefSize, null));
		
		// add the overview panels
		add(new Panel_Example2("Transfers"));
		add(new Panel_Example1());
		
		add(new Box.Filler(minSize, prefSize, null));
	}
}
