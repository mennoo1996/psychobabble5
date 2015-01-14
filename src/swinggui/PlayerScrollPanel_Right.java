package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import libraryClasses.FieldPlayer;
import libraryClasses.Goalkeeper;
import libraryClasses.Player;

class PlayerScrollPanel_Right extends JPanel{
	
	private int playerIndex;
	private Player player;
	private MouseListener listener;
	private boolean isSelected;
	private Font fontPlayername = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	private Font fontPlayerattr = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	
	
	public PlayerScrollPanel_Right(Player player, MouseListener mouseListener, int index){
		playerIndex = index;
		this.player = player;
		isSelected = false;
		listener = mouseListener;
		this.addMouseListener(listener);
		initUI();
		
	}

	public final void initUI(){
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(new BorderLayout(0,0));
		setOpaque(true);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200,200,200)));
		

		//name label
		JLabel label1 = new JLabel("	" + player.getName());
		label1.setFont(fontPlayername);
		label1.setOpaque(false);
		//attributes
		JPanel label2 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		label2.setOpaque(false);
		if(player.getPlayerType() != "Goalkeeper"){
			
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
				label2.add(AttrLabel[e]);
			}
		}
		else{
			Goalkeeper player1 = (Goalkeeper) player;
			JLabel attr = new JLabel("Stat: " + player1.getGoalkeeperValue());
			attr.setFont(fontPlayerattr);
			label2.add(attr);
		}
		
		JLabel label3 = new JLabel("	" + player.getTeam());
		label3.setFont(fontPlayername);
		JLabel label4 = new JLabel("	" + player.getPlayerType());
		label4.setFont(fontPlayerattr);
		
		//splitters
		JPanel panel1 = new JPanel(new BorderLayout(0,0));
		JPanel panel2 = new JPanel(new BorderLayout(0,0));
		
		setMaximumSize(new Dimension(2000,35));
		
		//finish up
		panel1.add(label1, BorderLayout.WEST);
		panel2.add(label2, BorderLayout.WEST);
		panel1.add(label3, BorderLayout.EAST);
		panel2.add(label4, BorderLayout.EAST);
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
	}
	
	public void toggleSelected() {
		if (isSelected) {
			setBackground(Color.WHITE);
			System.out.println("coloring...");
		} else {
			// Make this a nicer color
			
			System.out.println("coloring...");
			setBackground(new Color(135, 206, 250));
		}
		revalidate();
		repaint();
		isSelected = !isSelected;
	}
	
	public int getPlayerIndex(){
		return playerIndex;
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
	
	public Player getPlayer(){
		return player;
	}
	
}