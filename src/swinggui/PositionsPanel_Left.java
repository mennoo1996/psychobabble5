package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import libraryClasses.Competition;
import libraryClasses.FieldPlayer;
import libraryClasses.Player;

public class PositionsPanel_Left extends JPanel implements DragGestureListener, Transferable {
	
	private Competition currentComp;
	private ArrayList<Player> Team;
	
	public PositionsPanel_Left(Competition cComp) {
		currentComp = cComp;
		Team = cComp.getLibrary().getTeamForName("Manchester United").getTeam();
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
		
		//dragsource
		DragSource ds = new DragSource();
		
		//panel title
		JPanel titlepanel = new JPanel();
		
		JLabel title = new JLabel("Players");
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 30));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(145,143,143)));
		titlepanel.add(title);
		panel.add(titlepanel);

		//content begins here
		
		//top level jpane for holding all players and separators (Goalkeepers, attackers etc)
		JPanel ScrollPaneContent = new JPanel();
		ScrollPaneContent.setLayout(new BoxLayout(ScrollPaneContent, BoxLayout.Y_AXIS));
		
		//example separator (Goalkeepers)
		JPanel separator1 = new JPanel(); separator1.setPreferredSize(new Dimension(separator1.getPreferredSize().width, 30));
		Font fontseparator = new Font("Avenir", Font.ROMAN_BASELINE, 16);
		separator1.setOpaque(true); //separator1.setLayout(new BorderLayout());
		separator1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(180,180,180)));
		separator1.setBackground(new Color(230,230,230));
		JLabel goalkeepers = new JLabel("Goalkeepers"); goalkeepers.setFont(fontseparator);
		separator1.add(goalkeepers);
		ScrollPaneContent.add(separator1);
		
		//Grid pane to hold all players
		JPanel ScrollPaneGrid = new JPanel();
		ScrollPaneGrid.setLayout(new GridLayout(0,1));
		
		for(int i = 0 ; i < Team.size() ; i++){
			//panel
			ListPanel panel1 = new ListPanel(Team.get(i));
			panel1.setLayout(new BorderLayout());
			panel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200,200,200)));
			
			//name label
			JLabel label1 = new JLabel(Team.get(i).getName());
			Font fonttitle = new Font("Avenir", Font.ROMAN_BASELINE, 22);
			label1.setFont(fonttitle);
			label1.setPreferredSize(new Dimension(100, 50));
			
			//attributes
			Font fontattr = new Font("Avenir", Font.ROMAN_BASELINE, 12);
			JPanel label3 = new JPanel();
			label3.setPreferredSize(new Dimension(100, label3.getPreferredSize().height));
			switch(Team.get(i).getPlayerType()){
				case("Goalkeeper"):	
					
					break;
				default: 
					FieldPlayer player = (FieldPlayer) Team.get(i);
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
					
					for(int a = 0; a < 4; a++){
						AttrLabel[a].setFont(fontattr);
						label3.add(AttrLabel[a]);
					}
					break;
			}	
			
			//number shirt
			ImageIcon myImageIcon = createImageIcon("images/Shirts/" +	Integer.toString(Team.get(i).getNumber()) + ".png");
			JLabel label2 = new JLabel ("") {
			    @Override
			    public void paintComponent (Graphics g) {
			        super.paintComponent (g);
			        //System.out.println("override");
			        g.drawImage (myImageIcon.getImage(), 0, 0, 60, 60, null);
			    }
			};
			label2.setPreferredSize(new Dimension(80,60));
			
			//finish up
			panel1.add(label2, BorderLayout.WEST);
			panel1.add(label1);
			panel1.add(label3, BorderLayout.EAST);
			
			ScrollPaneGrid.add(panel1);
			ds.createDefaultDragGestureRecognizer(panel1, DnDConstants.ACTION_COPY, this);
		}
		
		ScrollPaneContent.add(ScrollPaneGrid);
		JScrollPane ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		ScrollPane.setPreferredSize(new Dimension(400,500));
		
		panel.add(ScrollPane);
		//content ends here
		
		//add panel
		panel.setMinimumSize(new Dimension(100,500));
		panel.setPreferredSize(new Dimension(450,500));
		panel.setMaximumSize(new Dimension(900,500));
		this.add(panel);
	}
	
	public static void main(String[] args) {
		Panel_Example1 thing = new Panel_Example1();
		thing.setVisible(true);
	}
	
	//drag gesture start
	public void dragGestureRecognized(DragGestureEvent event) {
        Cursor cursor = null;
        ListPanel panel = (ListPanel) event.getComponent();
        Player player = panel.getPlayer();
        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }
        event.startDrag(cursor, new TransferablePlayer(player));
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



//JPanel with attached Player Back Number
class ListPanel extends JPanel {
	Player player;
	public ListPanel(Player player){
		this.player = player;
	}	
	public Player getPlayer(){
		return player;
	}
}


//Transferable	
class TransferablePlayer implements Transferable {
    protected static DataFlavor playerFlavor = new DataFlavor(Player.class, "A Player Object");
    protected static DataFlavor[] supportedFlavors = {
        playerFlavor,
    };
    Player player;
    public TransferablePlayer(Player player) {
    	this.player = player;
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
     else{
    	 throw new UnsupportedFlavorException(flavor);
     }  
   }  
}
