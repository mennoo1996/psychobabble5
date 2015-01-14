package swinggui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import libraryClasses.Player;

public class TransfersPanel_Center extends JPanel{

	private JPanel playerPanel;
	
	public TransfersPanel_Center() {
		initUI();
	}
	
	public final void initUI(){

		setOpaque(false);
		setName("Panel");
		//setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(145,143,143)));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Selected");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160,160,160)));
		titlepanel.add(title);
		add(titlepanel);

		//content begins here
		playerPanel = new JPanel();
		JLabel nothing = new JLabel("No player selected");
		playerPanel.add(nothing);
		add(playerPanel);
		
		//content ends here
		
		setMinimumSize(new Dimension(100,500));
		setPreferredSize(new Dimension(350,550));
		setMaximumSize(new Dimension(900,600));
		
	}
	
	
	
	public void showPlayer(boolean isLeft, Player player){
		
		remove(playerPanel);
		playerPanel = new JPanel();
		playerPanel.add(new JLabel(player.getName()));
		add(playerPanel);
		revalidate();
		repaint();
		
		
	}
	
	public void noPlayer(){
		
	}
	
}
