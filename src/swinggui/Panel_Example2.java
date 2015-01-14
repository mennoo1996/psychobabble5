package swinggui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel_Example2 extends JPanel {
	
	private String panelTitle;
	
	public Panel_Example2(String panTitle) {
		panelTitle = panTitle;
		initUI();
	}
	
	/**
	 * Initialize an example panel
	 */
	public final void initUI() {
		//new panel
		JPanel panel = new JPanel();
				
		panel.setOpaque(false);
		panel.setName("Panel");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new Box.Filler(new Dimension(1,5), new Dimension(1,5), new Dimension(1,5)));
				
		//panel title
		JLabel title = new JLabel(panelTitle);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(title);
				
		//content begins here, I just added the table thingy
		panel.add(new GenTable());
		//content ends here
				
		//add panel
		panel.setMinimumSize(new Dimension(100,500));
		panel.setPreferredSize(new Dimension(800,500));
		panel.setMaximumSize(new Dimension(900,500));
		this.add(panel);
	}
	
	public static void main(String[] args) {
		Panel_Example2 thing = new Panel_Example2("Insert title here");
		thing.setVisible(true);
	}
}
