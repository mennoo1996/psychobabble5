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
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
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
	
	public PositionsPanel(Team cTeam) {
		currentTeam = cTeam;
		initUI();
	}
	
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
	
}

//Transferable object (containing player and/or position data)
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

//JPanel with attached Player Back Number
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

