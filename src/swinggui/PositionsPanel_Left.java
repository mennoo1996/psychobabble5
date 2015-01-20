package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.io.IOException;
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
public class PositionsPanel_Left extends JPanel implements DragGestureListener, Transferable {
	
	private Team currentTeam;
	private ArrayList<Player> Team;
	
	public PositionsPanel_Left(Team cTeam) {
		currentTeam = cTeam;
		Team = cTeam.getTeam();
		initUI();
	}
	
	
	/**
	 * Initialize an example panel
	 */
	public final void initUI() {
		//new panel
		//JPanel panel = new JPanel();
		setOpaque(false);
		setName("Panel");
		//setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(145,143,143)));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//dragsource
		DragSource ds = new DragSource();
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Players");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160,160,160)));
		titlepanel.add(title);
		add(titlepanel);

		//content begins here
		
		//Fonts here
		Font fontSeparator = new Font("Avenir", Font.ROMAN_BASELINE, 12);
		Font fontPlayername = new Font("Avenir", Font.ROMAN_BASELINE, 16);
		Font fontPlayerattr = new Font("Avenir", Font.ROMAN_BASELINE, 11);
		
		//top level JPanel holds all content for the scrollable.
		JPanel ScrollPaneContent = new JPanel(); ScrollPaneContent.setLayout(new BoxLayout(ScrollPaneContent, BoxLayout.Y_AXIS));
		
		//player types
		String[] PlayerTypes = {
				"Attacker",
				"Midfielder",
				"Defender",
				"Goalkeeper"
		};
		
		//loop through player types
		for(int q = 0; q < 4; q++){
			
			//add a separator
			JPanel separator = new JPanel(); 
			separator.setMinimumSize(new Dimension(0,25));
			//separator.setPreferredSize(new Dimension(separator.getWidth(), 25));
			separator.setOpaque(true);
			separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(180,180,180)));
			separator.setBackground(new Color(240,240,240));
			
			//separator title
			JLabel separatortitle = new JLabel(PlayerTypes[q] + "s");
			separatortitle.setFont(fontSeparator);
			separator.add(separatortitle);
			ScrollPaneContent.add(separator);
			
			//Grid panel to hold all players of current type
			JPanel ScrollPaneGrid = new JPanel();
			ScrollPaneGrid.setLayout(new GridLayout(0,1));
			
			//add all players of current type to Grid panel
			for(int w = 0; w < Team.size(); w++){
				if(Team.get(w).getPlayerType().toString() == PlayerTypes[q]){
					
					//panel to hold player information. Also draggable element
					ListPanel Playerpanel = new ListPanel(Team.get(w));
					Playerpanel.setLayout(new BorderLayout());
					Playerpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200,200,200)));
					
					//shirt
					ImageIcon myImageIcon;
					if(Team.get(w).getDaysInjured() > 0) {
						//injury sticker
						myImageIcon = createImageIcon("images/red-cross.png");
					} else if(Team.get(w).getDaysSuspended() > 0 || Team.get(w).getRedcards() > 0) {
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
					
					//name label
					JLabel label2 = new JLabel(Team.get(w).getName());
					label2.setFont(fontPlayername);
					label2.setMinimumSize(new Dimension(20,50));
					label2.setPreferredSize(new Dimension(90, 50));
					label2.setMaximumSize(new Dimension(100,50));
					
					
					//attributes
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
						}
					}
					else{
						Goalkeeper player = (Goalkeeper) Team.get(w);
						label3.setLayout(new BoxLayout(label3, BoxLayout.X_AXIS));
						JLabel attr = new JLabel("Stat: " + player.getGoalkeeperValue());
						attr.setFont(fontPlayerattr);
						label3.add(attr);
					}
					
					
					//finish up
					Playerpanel.add(label1, BorderLayout.WEST);
					JPanel fill = new JPanel();
					Playerpanel.add(label2, BorderLayout.CENTER);
					Playerpanel.add(label3, BorderLayout.EAST);
					ScrollPaneGrid.add(Playerpanel);
					
					
					ds.createDefaultDragGestureRecognizer(Playerpanel, DnDConstants.ACTION_COPY, this);
					
				}
			}
			
			ScrollPaneContent.add(ScrollPaneGrid);
			
		}
		
		JScrollPane ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		//ScrollPane.setMinimumSize(new Dimension(100,300));
		//ScrollPane.setPreferredSize(new Dimension(400,500));
		
		add(ScrollPane);
		//content ends here
		
		//add panel
		setMinimumSize(new Dimension(100,600));
		setPreferredSize(new Dimension(450,600));
		setMaximumSize(new Dimension(900,600));
		//this.add(panel);
	}
	
	
	
	//drag gesture start
	public void dragGestureRecognized(DragGestureEvent event) {
        Cursor cursor = null;
        ListPanel panel = (ListPanel) event.getComponent();
        Player player = panel.getPlayer();
        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }
        event.startDrag(cursor, new TransferablePlayer(player, 0));
    }

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return null;
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

////JPanel with attached Player Back Number
//@SuppressWarnings("serial")
//class ListPanel extends JPanel {
//	Player player;
//	public ListPanel(Player player){
//		this.player = player;
//	}	
//	public Player getPlayer(){
//		return player;
//	}
//}

//Transferable	
class TransferablePlayer implements Transferable {
    protected static DataFlavor playerFlavor = new DataFlavor(Player.class, "A Player Object");
    protected static DataFlavor intFlavor = new DataFlavor(int.class, "An Integer Object");
    protected static DataFlavor[] supportedFlavors = {
        playerFlavor,
        intFlavor,
    };
    
    Player player;
    int position;
    public TransferablePlayer(Player player, int position) {
    	this.player = player;
    	this.position = position;
    }
    public DataFlavor[] getTransferDataFlavors() {
    	return supportedFlavors;
    }
    public boolean isDataFlavorSupported(DataFlavor flavor) {
	    if (flavor.equals(playerFlavor)) {
	    	return true;
	    }
	    return false;
    }
   public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
     if (flavor.equals(playerFlavor)){
         return player;
     }
     else if(flavor.equals(intFlavor)){
    	 return position;
     }
     else{
    	 throw new UnsupportedFlavorException(flavor);
     }  
   }  
}
