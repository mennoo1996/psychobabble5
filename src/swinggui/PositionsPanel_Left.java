/**
 * GUI Class for creating the left panel in the Positions Window
 */
package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import libraryClasses.FieldPlayer;
import libraryClasses.Goalkeeper;
import libraryClasses.Player;
import libraryClasses.Team;

@SuppressWarnings("serial")
public class PositionsPanel_Left extends JPanel {
	
	private DragGestureListener dragGestureListener;
	private Team currentTeam;
	private ArrayList<Player> Team;
	private JPanel ScrollPaneContent;
	private String[] PlayerTypes = {
			"Attacker",
			"Midfielder",
			"Defender",
			"Goalkeeper"
	};
	private DragSource ds;
	private Font fontSeparator = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	private Font fontPlayername = new Font("Avenir", Font.ROMAN_BASELINE, 16);
	private Font fontPlayerattr = new Font("Avenir", Font.ROMAN_BASELINE, 11);
	
	/**
	 * Create and initialize a PositionsPanel_Left
	 * @param cteam - Attached Team object
	 * @param listener - DragGestureListener for Drag and Drop
	 */
	public PositionsPanel_Left(Team cTeam, DragGestureListener listener) {
		dragGestureListener = listener;
		currentTeam = cTeam;
		Team = cTeam.getTeam();
		initUI();
	}
	
	/**
	 * Initialize the GUI elements contained in the PlayerScrollPanel_Left
	 */
	public final void initUI() {

		setOpaque(false);
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Players");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		titlepanel.add(title);
		add(titlepanel);

		//content begins here

		ScrollPaneContent = new JPanel(); 
		ScrollPaneContent.setLayout(new BoxLayout(ScrollPaneContent, BoxLayout.Y_AXIS));
		loadContent();
		JScrollPane ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(ScrollPane);
		
		//content ends here

		//Panel Size Declarations
		setMinimumSize(new Dimension(100,580));
		setPreferredSize(new Dimension(450,580));
		setMaximumSize(new Dimension(900,580));
	}
	
	/**
	 * (Re)load all Team-Player content into the PositionsPanel_Left GUI
	 */
	public void loadContent(){
		ScrollPaneContent.removeAll();
		
		//dragsource
		ds = new DragSource();
		
		//loop through player types
		for(int q = 0; q < 4; q++){
			
			//add a separator
			JPanel separator = new JPanel(); 
			JLabel separatortitle = new JLabel(PlayerTypes[q] + "s");
			separator.setMinimumSize(new Dimension(0,25));
			separator.setOpaque(true);
			separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
			separator.setBackground(new Color(240,240,240));
			separatortitle.setFont(fontSeparator);
			separator.add(separatortitle);
			ScrollPaneContent.add(separator);
			
			//Grid panel to hold all players of current type
			JPanel ScrollPaneGrid = new JPanel();
			ScrollPaneGrid.setLayout(new GridLayout(0,1));
			
			//add all players of current type to Grid panel
			for(int w = 0; w < Team.size(); w++){
				if(Team.get(w).getPlayerType().toString() == PlayerTypes[q]){
					
					//panel to hold player information. Also the draggable element
					PlayerPanel Playerpanel = new PlayerPanel(Team.get(w), 0);
					Playerpanel.setLayout(new BorderLayout());
					Playerpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200,200,200)));
					
					//Player Image Label
					ImageIcon myImageIcon;
					if(Team.get(w).getDaysInjured() > 0) {
						//injury sticker
						myImageIcon = createImageIcon("images/red-cross.png");
					} else if(Team.get(w).getDaysSuspended() > 0) {
						//suspension sticker
						myImageIcon = createImageIcon("images/red-card.png");
					} else {
						//t-shirt
						myImageIcon = createImageIcon("images/Shirts/" +	Integer.toString(Team.get(w).getNumber()) + ".png");
					}
					JLabel label1 = new JLabel ("") {
					    @Override
					    public void paintComponent (Graphics g) {
					        super.paintComponent (g);
					        //System.out.println("override");
					        g.drawImage (myImageIcon.getImage(), 0, 0, 55, 55, null);
					    }
					};
					label1.setPreferredSize(new Dimension(65,55));
					
					//Player Name Label
					JLabel label2 = new JLabel(Team.get(w).getName());
					label2.setFont(fontPlayername);
					label2.setMinimumSize(new Dimension(20,50));
					label2.setPreferredSize(new Dimension(90, 50));
					label2.setMaximumSize(new Dimension(100,50));
					
					//Player Attributes Label
					JPanel label3 = new JPanel();
					label3.setMinimumSize(new Dimension(75, label3.getSize().height));
					label3.setPreferredSize(new Dimension(80, label3.getSize().height));
					label3.setMaximumSize(new Dimension(100, label3.getSize().height));
					if(PlayerTypes[q] != "Goalkeeper"){
						FieldPlayer player = (FieldPlayer) Team.get(w);
						label3.setLayout(new GridLayout(2,2));
						
						int[] Attr = {	player.getDefenseValue(),
										player.getDribblingValue(),
										player.getFinishingValue(),
										player.getStaminaValue()
						};
						JLabel[] AttrLabel = {	new JLabel("Def: " + Integer.toString(Attr[0])),
												new JLabel("Dri: " + Integer.toString(Attr[1])),
												new JLabel("Fin: " + Integer.toString(Attr[2])),
												new JLabel("Sta: " + Integer.toString(Attr[3]))		
						};	
						
						for(int e = 0; e < 4; e++){
							AttrLabel[e].setFont(fontPlayerattr);
							label3.add(AttrLabel[e]);
							if(Team.get(w).getDaysInjured()>0 || Team.get(w).getDaysSuspended()>0) {
								AttrLabel[e].setForeground(new Color(255,255,255));
							}
						}
					}
					else{
						Goalkeeper player = (Goalkeeper) Team.get(w);
						label3.setLayout(new BoxLayout(label3, BoxLayout.X_AXIS));
						JLabel attr = new JLabel("Stat: " + player.getGoalkeeperValue());
						attr.setFont(fontPlayerattr);
						label3.add(attr);
						if(Team.get(w).getDaysInjured()>0 || Team.get(w).getDaysSuspended()>0) {
							attr.setForeground(new Color(255,255,255));
						}
					}

					//finish up					
					Playerpanel.add(label1, BorderLayout.WEST);
					Playerpanel.add(label2, BorderLayout.CENTER);
					Playerpanel.add(label3, BorderLayout.EAST);
					ScrollPaneGrid.add(Playerpanel);
					if(Team.get(w).getDaysInjured()>0 || Team.get(w).getDaysSuspended()>0) {
						Playerpanel.setBackground(new Color(255,182,158)); Playerpanel.setOpaque(true);
						Playerpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255,255,255)));
						label2.setForeground(new Color(255,255,255));
					} else if(currentTeam.getPositions().contains(Team.get(w))) {
						Playerpanel.setBackground(new Color(221,244,255)); Playerpanel.setOpaque(true);
						ds.createDefaultDragGestureRecognizer(Playerpanel, DnDConstants.ACTION_COPY, dragGestureListener);
					} else {
						ds.createDefaultDragGestureRecognizer(Playerpanel, DnDConstants.ACTION_COPY, dragGestureListener);
					}

				}
			}
			ScrollPaneContent.add(ScrollPaneGrid);
		}
	}
	
	/**Function to create ImageIcons, just in case I have trouble finding them again
	 * @param path			- ImageIcon file location
	 * @return				- new ImageIcon object
	 */
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


