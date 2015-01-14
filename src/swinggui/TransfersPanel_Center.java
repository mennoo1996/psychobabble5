package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import libraryClasses.FieldPlayer;
import libraryClasses.Goalkeeper;
import libraryClasses.Player;

public class TransfersPanel_Center extends JPanel{

	private JPanel playerPanel;
	private JPanel buttonPanel;
	private JPanel statPanel;
	private JTextField textField;
	private Font fontPlayername = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	private Font fontPlayerattr = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	
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
		titlepanel.setMaximumSize(new Dimension(2000, 100));
		add(titlepanel);

		//content begins here
		playerPanel = new JPanel();
		JLabel nothing = new JLabel("No player selected");
		playerPanel.add(nothing);
		statPanel = new JPanel();
		buttonPanel = new JPanel(new BorderLayout());
		JButton transferButton = new JButton("nothing");
		buttonPanel.add(transferButton);

		add(playerPanel);
		add(statPanel);
		add(buttonPanel);
		
		//content ends here
		
		setMinimumSize(new Dimension(100,500));
		setPreferredSize(new Dimension(350,550));
		setMaximumSize(new Dimension(900,600));
		
	}
	
	
	
	public void showPlayer(boolean isLeft, Player player){
		

		remove(buttonPanel);
		remove(statPanel);
		remove(playerPanel);
		playerPanel = new JPanel(new BorderLayout());
		
		
		//shirt
		ImageIcon myImageIcon = createImageIcon("images/Shirts/T-Shirt.png");
		JLabel label1 = new JLabel ("") {
		    @Override
		    public void paintComponent (Graphics g) {
		        super.paintComponent (g);
		        g.drawImage (myImageIcon.getImage(), 0, 0, 75, 75, null);
		    }
		};
		label1.setPreferredSize(new Dimension(75,75));
		label1.setOpaque(false);
		playerPanel.add(label1, BorderLayout.WEST);
		
		String name = player.getName();
		String team = player.getTeam();
		JPanel namePanel = new JPanel(); namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
		namePanel.add(new Box.Filler(new Dimension(0,8), new Dimension(0,8), new Dimension(0,8)));
		namePanel.add(new JLabel(name));
		
		JLabel Team = new JLabel(team); Team.setFont(new Font("Avenir", Font.ROMAN_BASELINE, 16));
		namePanel.add(Team);
		playerPanel.add(namePanel, BorderLayout.CENTER); 
		playerPanel.setMaximumSize(new Dimension(2000, 250));
		
		statPanel = new JPanel();
		statPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160,160,160)));
		statPanel.setMinimumSize(new Dimension(0, 30));
		statPanel.setPreferredSize(new Dimension(50, 30));
		statPanel.setMaximumSize(new Dimension(2000, 30));
		if(player.getPlayerType() != "Goalkeeper"){
			//statPanel.setLayout(new GridLayout(1,4));
			FieldPlayer player1 = (FieldPlayer) player;
			
			int[] Attr = {	player1.getDefenseValue(),
							player1.getDribblingValue(),
							player1.getFinishingValue(),
							player1.getStaminaValue()
			};
			JLabel[] AttrLabel = {	new JLabel("	Def: " + Integer.toString(Attr[0])),
									new JLabel("	Dri: " + Integer.toString(Attr[1])),
									new JLabel("	Fin: " + Integer.toString(Attr[2])),
									new JLabel("	Sta: " + Integer.toString(Attr[3]))		
			};	
			
			for(int e = 0; e < 4; e++){
				AttrLabel[e].setFont(fontPlayerattr);
				statPanel.add(AttrLabel[e]);
			}
		}
		else{
			Goalkeeper player1 = (Goalkeeper) player;
			JLabel attr = new JLabel("Goalkeepervalue: " + player1.getGoalkeeperValue());
			attr.setFont(fontPlayerattr);
			statPanel.add(attr);
		}
		
		JButton transferButton = new JButton("Transfer");
		transferButton.setName("Test");

		textField = new JTextField(10);
		textField.setName("textField");
		buttonPanel = new JPanel(new FlowLayout());


		buttonPanel.add(textField);
		buttonPanel.add(transferButton);
		
		add(playerPanel);
		add(statPanel);
		add(buttonPanel);
		
		revalidate();
		repaint();
		
		
	}
	
	public void noPlayer(){
		
	}
	
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
