package swinggui;

import java.awt.BorderLayout;
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
import libraryClasses.Team;

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
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setName("Panel");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//dragsource
		DragSource ds = new DragSource();
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Positions");
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(145,143,143)));
		titlepanel.add(title);
		panel.add(titlepanel);
				
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
		
		ContentPanel.setPreferredSize(new Dimension(ContentPanel.getWidth(), 500));
		
		//GridBagConstraints c = new GridBagConstraints();
		
		PlayerPanel Test;
		//TOP ROW
		
		//PlayerPanel[] panels = new PlayerPanel[11];
		
		for(int i = 1; i < 12; i++){
			
			panels[i - 1] = new PlayerPanel(null, i);
			
			//PlayerPanel playerPanel = new PlayerPanel(null, i);
			panels[i - 1].setName("playerPanel");
			panels[i - 1].setLayout(new BoxLayout(panels[i - 1], BoxLayout.X_AXIS));
			panels[i - 1].setPreferredSize(new Dimension(150,50));
			panels[i - 1].setMinimumSize(new Dimension(150,50));
			
			JLabel label2 = new JLabel();
			ImageIcon myImageIcon;
			Component box;
			//shirt
			if(currentTeam.getPositions().getPositionArray()[i - 1]== null){
				System.out.println(currentTeam.getPositions().getPositionArray()[i-1]);
				myImageIcon = createImageIcon("images/Shirts/T-Shirt.png");
				label2 = new JLabel("Select player");
				box = Box.createRigidArea(new Dimension(0,0));
			}
			else{
				panels[i - 1].setPlayer(currentTeam.getPositions().getPositionArray()[i-1]);
				myImageIcon = createImageIcon("images/Shirts/" + i + ".png");
				label2 = new JLabel(currentTeam.getPositions().getPositionArray()[i-1].getName());
				box = Box.createRigidArea(new Dimension(10,0));
			}
			JLabel label1 = new JLabel ("  	   ") {
			    @Override
			    public void paintComponent (Graphics g) {
			        super.paintComponent (g);
			        g.drawImage (myImageIcon.getImage(), 0, 0, 38, 38, null);
			    }
			};
			
			//name
			label2.setFont(fontTitle);
			
			//finish up
			panels[i - 1].add(label1);
			panels[i - 1].add(box);
			panels[i - 1].add(label2);
			new MyDropTargetListener(panels[i - 1]);
			
			//layout
			GridBagConstraints c = new GridBagConstraints();
			switch(i){
				//row 1
				case(1): //player 1
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,0,0,80);
					c.weighty = 0.5;
					c.gridx = 0;
					c.gridy = 0;
					c.gridwidth = 2;
					break;
				case(2): //player 2
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(80,0,0,0);
					c.weighty = 0.5;
					c.gridx = 1;
					c.gridy = 0;
					c.gridwidth = 2;
					break;
				case(3): //player 3
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,30,0,0);
					c.weighty = 0.5;
					c.gridx = 2;
					c.gridy = 0;
					c.gridwidth = 2;
					break;
				//row 2
				case(4): //player 4
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,30,0,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 0;
					c.gridy = 1;
					c.gridwidth = 2;
					break;
				case(5): //player 5
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(30,0,0,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 1;
					c.gridy = 1;
					c.gridwidth = 2;
					break;
				case(6): //player 6
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,0,0,30);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 2;
					c.gridy = 1;
					c.gridwidth = 2;
					break;
				//row 3
				case(7): //player 7
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,0,30,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 0;
					c.gridy = 2;
					c.gridwidth = 1;
					break;
				case(8): //player 8
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(10,0,0,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 1;
					c.gridy = 2;
					c.gridwidth = 1;
					break;
				case(9): //player 9
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(10,0,0,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 2;
					c.gridy = 2;
					c.gridwidth = 1;
					break;
				case(10): //player 10
					c.fill = GridBagConstraints.NONE;
					c.insets = new Insets(0,0,30,0);
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = 3;
					c.gridy = 2;
					c.gridwidth = 1;
					break;
				//row 4
				case(11): //player 11
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
		
		panel.add(ContentPanel);
		
		//content ends here
				
		//add panel
		panel.setMinimumSize(new Dimension(100,500));
		panel.setPreferredSize(new Dimension(800,550));
		panel.setMaximumSize(new Dimension(900,600));
		
		this.add(panel);
	}
	
	public static void main(String[] args) {
		Panel_Example2 thing = new Panel_Example2("Insert title here");
		thing.setVisible(true);
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
		          this.panel.removeAll();
			        //shirt
			  		ImageIcon myImageIcon = createImageIcon("images/Shirts/" +	Integer.toString(player.getNumber()) + ".png");
			  		JLabel label1 = new JLabel ("  	   ") {
			  		    @Override
			  		    public void paintComponent (Graphics g) {
			  		        super.paintComponent (g);
			  		        g.drawImage (myImageIcon.getImage(), 0, 0, 38, 38, null);
			  		    }
			  		};
			  		
			  		//name
			  		JLabel label2 = new JLabel(player.getName());
			  		label2.setFont(fontTitle);
			  		
			  		//finish up
			  		panel.add(label1);
			  		panel.add(Box.createRigidArea(new Dimension(10,0)));
			  		panel.add(label2);
			  		((PlayerPanel) panel).setPlayer(player);
			  		this.panel.revalidate();
		          	this.panel.repaint();
		          
		          if(oldPosition != 0){
		        	  
		        	 System.out.println("Origin: " +  panels[oldPosition - 1].player.toString());
		        	 panels[oldPosition - 1].removeAll();
		        	 //shirt
		        	 ImageIcon myImageIcon2 = createImageIcon("images/Shirts/" +	Integer.toString(switchPlayer.getNumber()) + ".png");
		        	 JLabel label3 = new JLabel ("  	   ") {
				  		    @Override
				  		    public void paintComponent (Graphics g) {
				  		        super.paintComponent (g);
				  		        g.drawImage (myImageIcon.getImage(), 0, 0, 38, 38, null);
				  		    }
				  	};
				  	//name
			  		JLabel label4 = new JLabel(switchPlayer.getName());
			  		label4.setFont(fontTitle);
			  		//finish up
			  		panels[oldPosition - 1].add(label3);
			  		panels[oldPosition - 1].add(Box.createRigidArea(new Dimension(10,0)));
			  		panels[oldPosition - 1].add(label4);
			  		((PlayerPanel) panels[oldPosition - 1]).setPlayer(switchPlayer);
			  		panels[oldPosition - 1].revalidate();
			  		panels[oldPosition - 1].repaint();
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
