/**
 * GUI Class for creating a selectable JPanel showing Player data
 */
package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import libraryClasses.FieldPlayer;
import libraryClasses.Goalkeeper;
import libraryClasses.Player;

@SuppressWarnings("serial")
class PlayerScrollPanel_Right extends JPanel{
	
	private int playerIndex;
	private Player player;
	private MouseListener listener;
	private boolean isSelected;
	private Font fontPlayername = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	private Font fontPlayerattr = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	
	/**
	 * Create and initialize a PlayerScrollPanel_Right
	 * @param player - Attached Player object
	 * @param mouseListener - mouseListener for clickability
	 * @param index - index to use if player is in a list
	 */
	public PlayerScrollPanel_Right(Player player, MouseListener mouseListener, int index){
		playerIndex = index;
		this.player = player;
		isSelected = false;
		listener = mouseListener;
		this.addMouseListener(listener);
		initUI();
		
	}

	/**
	 * Initialize the GUI elements contained in the PlayerScrollPanel_Left
	 */
	public final void initUI(){
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(new BorderLayout(0,0));
		setOpaque(true);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200,200,200)));

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
		
		JLabel label3 = new JLabel("	" + player.getTeam() + "    ");
		label3.setFont(fontPlayername);
		JLabel label4 = new JLabel("	" + player.getPlayerType() + "    ");
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
	
	/**
	 * Toggles PlayerScrollPanel_Right selection state
	 */
	public void toggleSelected() {
		if (isSelected) {
			setBackground(Color.WHITE);
		} else {
			setBackground(new Color(221,244,255));
		}
		revalidate();
		repaint();
		isSelected = !isSelected;
	}
	
	/**
	 * returns player index
	 * @return - int playerIndex
	 */
	public int getPlayerIndex(){
		return playerIndex;
	}

	/**
	 * returns player object
	 * @return - Player player
	 */
	public Player getPlayer(){
		return player;
	}
	
}