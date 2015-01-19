package swinggui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import libraryClasses.Player;
import libraryClasses.Positions;
import libraryClasses.Team;

@SuppressWarnings("serial")
public class PositionsPanel_Right extends JPanel implements DragGestureListener, Transferable{
	
	private String panelTitle;
	private Team currentTeam;
	private Font fontTitle = new Font("Avenir", Font.ROMAN_BASELINE, 15);
	PlayerPanel[] panels = new PlayerPanel[11];
	
	public PositionsPanel_Right(Team cTeam) {
		currentTeam = cTeam;
		initUI();
	}
	
	/**
	 * Initialize an example panel
	 */
	public final void initUI() {
		//new panel
		setOpaque(false);
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//dragsource
		DragSource ds = new DragSource();
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Positions");
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160,160,160)));
		titlepanel.add(title);
		add(titlepanel);
				
		//content begins here
		
		//New content pane using GridBagLayout
		JPanel ContentPanel = new JPanel(new GridBagLayout()){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon myImageIcon = createImageIcon("images/field.png");
				Image img = myImageIcon.getImage();
				int imgx = img.getWidth(null);
				int imgy = img.getHeight(null);
				g.drawImage(myImageIcon.getImage(), (getWidth() - imgx)/2, getHeight() - imgy,imgx,imgy, null);
			}	
		};
		
		//loop through team of 11 players
		
		for(int i = 11; i > 0; i--){
			//System.out.println(currentTeam.getPositions().getPositionArray()[i-1]);
			panels[i - 1] = new PlayerPanel(null, i);
			panels[i - 1].setName("playerPanel");
			panels[i - 1].setLayout(new BoxLayout(panels[i - 1], BoxLayout.X_AXIS));
			panels[i - 1].setPreferredSize(new Dimension(150,50));
			panels[i - 1].setMinimumSize(new Dimension(150,50));
			
			//panel content
			if(currentTeam.getPositions() == null || currentTeam.getPositions().getPositionArray()[i - 1] == null){
				setPlayerPanel(true,null,currentTeam,i, false);		
			}
			else{		
				setPlayerPanel(false, currentTeam.getPositions().getPositionArray()[i-1],currentTeam, i, false);	
			}
			
			new MyDropTargetListener(panels[i - 1]);
			
			//layout					insets go like this (y,x,y,x) or better (fromtop,fromleft,frombottom,fromrright)
			GridBagConstraints c = new GridBagConstraints();
			switch(i){
				//row 1
				case(11): //player 1
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(30,0,0,80);
					//c.weighty = 0.5;
					c.gridx = 0;
					c.gridy = 0;
					c.gridwidth = 2;
					break;
				case(10): //player 2
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,0,0,0);
					//c.weighty = 0.5;
					c.gridx = 1;
					c.gridy = 0;
					c.gridwidth = 2;
					break;
				case(9): //player 3
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(30,80,0,0);
					//c.weighty = 0.5;
					c.gridx = 2;
					c.gridy = 0;
					c.gridwidth = 2;
					break;
				//row 2
				case(8): //player 4
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(20,0,0,60);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 0;
					c.gridy = 1;
					c.gridwidth = 2;
					break;
				case(7): //player 5
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(50,0,0,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 1;
					c.gridy = 1;
					c.gridwidth = 2;
					break;
				case(6): //player 6
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(20,60,0,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 2;
					c.gridy = 1;
					c.gridwidth = 2;
					break;
				//row 3
				case(5): //player 7
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,0,30,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 0;
					c.gridy = 2;
					c.gridwidth = 1;
					break;
				case(4): //player 8
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(20,0,0,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 1;
					c.gridy = 2;
					c.gridwidth = 1;
					break;
				case(3): //player 9
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(20,0,0,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 2;
					c.gridy = 2;
					c.gridwidth = 1;
					break;
				case(2): //player 10
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,0,30,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 3;
					c.gridy = 2;
					c.gridwidth = 1;
					break;
				//row 4
				case(1): //player 11
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,0,20,0);
					c.ipady = 0;
					c.weightx = 0.5;
					c.weighty = 0.0;
					c.gridx = 1;
					c.gridy = 3;
					c.gridwidth = 2;
					break;
			}
			ds.createDefaultDragGestureRecognizer(panels[i - 1], DnDConstants.ACTION_COPY, this);
			ContentPanel.add(panels[i - 1], c);
		}
		
		add(ContentPanel);
		add(new Box.Filler(new Dimension(1,20), new Dimension(1,20), new Dimension(1,20)));
		
		//content ends here
				
		//add panel
		setMinimumSize(new Dimension(100,500));
		setPreferredSize(new Dimension(800,550));
		setMaximumSize(new Dimension(900,600));
		
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
	
	//drag gesture start
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
	
	//DropTarget Action
	class MyDropTargetListener extends DropTargetAdapter {

        private DropTarget dropTarget;
        private JPanel panel;

		public MyDropTargetListener(JPanel panel) {
			this.panel = panel;
		    dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
		}

		public void drop(DropTargetDropEvent event) {
		    try{
		      Transferable tr = event.getTransferable();
		      Player player = (Player) tr.getTransferData(TransferablePlayer.playerFlavor);
		      Player switchPlayer = ((PlayerPanel) panel).getPlayer();
		      int oldPosition = (int) tr.getTransferData(TransferablePlayer.intFlavor);
		      if (event.isDataFlavorSupported(TransferablePlayer.playerFlavor)) {
		          
		    	  event.acceptDrop(DnDConstants.ACTION_COPY);
		          
		          setPlayerPanel(false, player,currentTeam, ((PlayerPanel) panel).getPosition(), false);
		          
		          if(oldPosition != 0){
		        	 setPlayerPanel(false, switchPlayer, currentTeam, oldPosition, false);
		          }
		          
		          event.dropComplete(true);
		          
		          return;
		      }
		      event.rejectDrop();
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    	event.rejectDrop();
		    }
		}
    }
	
	
	public void setPlayerPanel(boolean empty, Player player, Team team, int position, boolean keeper){
		
		panels[position - 1].removeAll();
		
		JLabel label2 = new JLabel();
		ImageIcon myImageIcon;
		Component box;

		if(empty){
			//content
			((PlayerPanel) panels[position - 1]).setPlayer(player);
			myImageIcon = createImageIcon("images/Shirts/T-Shirt.png");
			label2 = new JLabel("Select player");
			box = Box.createRigidArea(new Dimension(0,0));
			setPosition(position, null, team);
		}
		else if(keeper){
			//content
			((PlayerPanel) panels[position - 1]).setPlayer(player);
			myImageIcon = createImageIcon("images/Shirts/T-Shirt-orange.png");
			label2 = new JLabel("Select keeper");
			box = Box.createRigidArea(new Dimension(0,0));
			setPosition(position, null, team);
		}
		else if(player == null){
			//content
			((PlayerPanel) panels[position - 1]).setPlayer(player);
			myImageIcon = createImageIcon("images/Shirts/T-Shirt.png");
			label2 = new JLabel("Select player");
			box = Box.createRigidArea(new Dimension(0,0));
			setPosition(position, null, team);
		}
		else{
			//loop through players to check for doubles
			for(int i = 0; i < 11; i++){
				if(
						i != (position - 1) &&
						panels[i] != null &&
						panels[i].getPlayer() != null &&
						((PlayerPanel) panels[i]).getPlayer().equals(player)
						){
					setPlayerPanel(true, null,team, i + 1, false);
				}
			}
			//content
			((PlayerPanel) panels[position - 1]).setPlayer(player);
			myImageIcon = createImageIcon("images/Shirts/" + Integer.toString(player.getNumber()) + ".png");
			label2 = new JLabel(player.getName());
			box = Box.createRigidArea(new Dimension(10,0));
		}
		
		//shirt override paint component to scale image
   	 	JLabel label1 = new JLabel ("  	   ") {
	  		    @Override
	  		    public void paintComponent (Graphics g) {
	  		        super.paintComponent (g);
	  		        g.drawImage (myImageIcon.getImage(), 0, 0, 38, 38, null);
	  		    }
	  	};
	  	
  		label2.setFont(fontTitle);
  		
  		//finish
	  	panels[position - 1].add(label1);
	  	panels[position - 1].add(box);
	  	panels[position - 1].add(label2);
  		
  		//redraw
  		panels[position - 1].revalidate();
  		panels[position - 1].repaint();
  		
  		//final check
  		if(player != null && position == 1 && player.getPlayerType().toString() != "Goalkeeper"){
  			setPlayerPanel(false, null,team, 1, true);
  		}
  		else if(player != null && position != 1 && player.getPlayerType().toString() == "Goalkeeper"){
  			setPlayerPanel(true, null,team, position, false);
  		}
  		
  		//set position
  		setPosition(position, player, team);
	}
	
	public static void setPosition(int position, Player player, Team team){
//		if(team.getPositions().getPositionArray() != null){
//			
//		}
		Player[] positions = team.getPositions().getPositionArray();
		positions[position - 1] = player;
		team.setPositions(new Positions(positions));
		
		//System.out.println(team.getPositions().getPositionArray()[10]);
		
	}
	
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		return null;
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
