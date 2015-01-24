/**
 * GUI Class for creating the Positions Panel
 */
package swinggui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import libraryClasses.Player;
import libraryClasses.Team;

@SuppressWarnings("serial")
public class PositionsPanel extends JPanel implements DragGestureListener, Transferable{
	private Team currentTeam;
	private Dimension minSize = new Dimension(20,20);
	private Dimension prefSize = new Dimension(40,20);
	private PositionsPanel_Left Left;
	private PositionsPanel_Right Right;
	
	/**
	 * Create and initialize a PositionsPanel
	 * @param cteam - Attached Team object
	 */
	public PositionsPanel(Team cTeam) {
		currentTeam = cTeam;
		initUI();
	}
	
	/**
	 * Initialize the GUI elements contained in the PositionsPanel_Right
	 */
	public final void initUI() {
		Left = new PositionsPanel_Left(currentTeam, this);
		Right = new PositionsPanel_Right(currentTeam, this, Left);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(new Box.Filler(minSize, prefSize, null));
		add(Left);
		add(Right);
		add(new Box.Filler(minSize, prefSize, null));
	}

	@Override
	/**
	 * function to create Transferable when Drag Gesture is recognized
	 */
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
        PlayerPanel panel = (PlayerPanel) event.getComponent();
        Player player = panel.getPlayer();
        int position = panel.getPosition();
        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }
        event.startDrag(cursor, new TransferablePlayer(player, position));
	}

	// The rest of the (unnecessary) DragGestureListener methods
	/**
	 * nothing happens (required interface method)
	 */
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return null;
	}

	/**
	 * nothing happens (required interface method)
	 */
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return false;
	}

	/**
	 * nothing happens (required interface method)
	 */
	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return null;
	}
	
}

/**
 * Transferable Class containing all necessary data for a Drag and Drop
 */
class TransferablePlayer implements Transferable {
	  protected static DataFlavor playerFlavor = new DataFlavor(Player.class, "A Player Object");
	  protected static DataFlavor intFlavor = new DataFlavor(int.class, "An Integer Object");
	  protected static DataFlavor[] supportedFlavors = {
	      playerFlavor,
	      intFlavor,
	  };
	  Player player;
	  int position;
	  
	 /**
	 * Create and initialize a TransferablePlayer
	 * @param player - Player object to attach
	 * @param position - int position to attach
	 */
	  public TransferablePlayer(Player player, int position) {
	  	this.player = player;
	  	this.position = position;
	  }
	  
	 /**
	 * returns Array of supported flavors
	 * @return supportedFlavors[]
	 */
	  public DataFlavor[] getTransferDataFlavors() {
	  	return supportedFlavors;
	  }
	  
	 /**
	 * checks if DataFlavor is supported
	 * @return boolean if supported
	 */
	  public boolean isDataFlavorSupported(DataFlavor flavor) {
		    if (flavor.equals(playerFlavor)) {
		    	return true;
		    }
		    return false;
	  }
	  
	 /**
	 * returns Transferrable data for given DataFlavor
	 * @param flavor - DataFlavor asked for
	 * @return transferData, depending on Flavor
	 */
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

/**
 * PlayerPanel Class extending JPanel with Player and Position object
 */
@SuppressWarnings("serial")
class PlayerPanel extends JPanel {
	Player player;
	int position;
	
	public PlayerPanel(Player player, int position){
		this.player = player;
		this.position = position;
	}	
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getPosition(){
		return position;
	}
}

