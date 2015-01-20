package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

class PlayerScrollPanel_Left extends JPanel{
	
	private int playerIndex;
	private Player player;
	private MouseListener listener;
	private String playerType;
	private boolean isSelected;
	private Font fontPlayername = new Font("Avenir", Font.ROMAN_BASELINE, 16);
	private Font fontPlayerattr = new Font("Avenir", Font.ROMAN_BASELINE, 11);
	
	
	public PlayerScrollPanel_Left(Player player, String playerType, MouseListener mouseListener, int index){
		playerIndex = index;
		this.player = player;
		listener = mouseListener;
		this.playerType = playerType;
		isSelected = false;
		this.addMouseListener(listener);
		initUI();
		
	}

	public final void initUI(){
		setLayout(new BorderLayout());
		setOpaque(true);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200,200,200)));
		
		//shirt
		ImageIcon myImageIcon;
		if(player.getDaysInjured() > 0) {
			//injury sticker
			myImageIcon = createImageIcon("images/red-cross.png");
		} else if(player.getDaysSuspended() > 0) {
			//suspension sticker
			myImageIcon = createImageIcon("images/red-card.png");
		} else {
			//t-shirt
			myImageIcon = createImageIcon("images/Shirts/" +	Integer.toString(player.getNumber()) + ".png");
		}
		JLabel label1 = new JLabel ("") {
		    @Override
		    public void paintComponent (Graphics g) {
		        super.paintComponent (g);
		        g.drawImage (myImageIcon.getImage(), 0, 0, 55, 55, null);
		    }
		};
		label1.setPreferredSize(new Dimension(65,55));
		label1.setOpaque(false);
		//name label
		JLabel label2 = new JLabel(player.getName());
		label2.setFont(fontPlayername);
		label2.setMinimumSize(new Dimension(20,50));
		label2.setPreferredSize(new Dimension(90, 50));
		label2.setMaximumSize(new Dimension(100,50));
		label2.setOpaque(false);
		//attributes
		JPanel label3 = new JPanel();
		label3.setMinimumSize(new Dimension(75, label3.getSize().height));
		label3.setPreferredSize(new Dimension(80, label3.getSize().height));
		label3.setMaximumSize(new Dimension(100, label3.getSize().height));
		label3.setOpaque(false);
		if(playerType != "Goalkeeper"){
			
			FieldPlayer player1 = (FieldPlayer) player;
			label3.setLayout(new GridLayout(2,2));
			
			int[] Attr = {	player1.getDefenseValue(),
							player1.getDribblingValue(),
							player1.getFinishingValue(),
							player1.getStaminaValue()
			};
			JLabel[] AttrLabel = {	new JLabel("Def: " + Integer.toString(Attr[0])),
									new JLabel("Dri: " + Integer.toString(Attr[1])),
									new JLabel("Fin: " + Integer.toString(Attr[2])),
									new JLabel("Sta: " + Integer.toString(Attr[3]))		
			};	
			
			for(int e = 0; e < 4; e++){
				AttrLabel[e].setFont(fontPlayerattr);
				label3.add(AttrLabel[e]);
			}
		}
		else{
			Goalkeeper player1 = (Goalkeeper) player;
			label3.setLayout(new BoxLayout(label3, BoxLayout.X_AXIS));
			JLabel attr = new JLabel("Stat: " + player1.getGoalkeeperValue());
			attr.setFont(fontPlayerattr);
			label3.add(attr);
		}
		
		//finish up
		add(label1, BorderLayout.WEST);
		add(label2, BorderLayout.CENTER);
		add(label3, BorderLayout.EAST);
	}
	
	public void toggleSelected() {
		if (isSelected) {
			setBackground(Color.WHITE);
		} else {
			setBackground(new Color(164, 225, 255));
		}
		revalidate();
		repaint();
		isSelected = !isSelected;
	}
	
	public void deselect(){
		if (isSelected) {
			setBackground(Color.WHITE);
		}
		revalidate();
		repaint();
		isSelected = false;
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