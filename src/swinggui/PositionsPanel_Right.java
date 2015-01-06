package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import libraryClasses.Player;

public class PositionsPanel_Right extends JPanel {
	
	private String panelTitle;
	private Font fontTitle = new Font("Avenir", Font.ROMAN_BASELINE, 15);
	
	public PositionsPanel_Right(String panTitle) {
		panelTitle = panTitle;
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
		
		GridBagConstraints c = new GridBagConstraints();
//		if (shouldFill) {
//            //natural height, maximum width
//            c.fill = GridBagConstraints.HORIZONTAL;
//		}
		
		
		
		JPanel Test;
		//TOP ROW
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,80);  //top padding
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;   //2 columns wide
		ContentPanel.add(Test, c);
		
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(80,0,0,0);  //top padding
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;   //2 columns wide
		ContentPanel.add(Test, c);
		
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,30,0,0);  //top padding
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;   //2 columns wide
		ContentPanel.add(Test, c);
		
		//SECOND ROW
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,30,0,0);  //top padding
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		ContentPanel.add(Test, c);
		
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(30,0,0,0);  //top padding
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		ContentPanel.add(Test, c);
		
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,30);  //top padding
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		ContentPanel.add(Test, c);

		//THIRD ROW
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,30,0);  //top padding
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		ContentPanel.add(Test, c);
		
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(10,0,0,0);  //top padding
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		ContentPanel.add(Test, c);
		
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(10,0,0,0);  //top padding
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		ContentPanel.add(Test, c);
		
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,30,0);  //top padding
		c.weighty = 0.5;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		ContentPanel.add(Test, c);
		
		//lAST ROW
		
		Test = PlayerPanel();
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,20,0);  //top padding
		c.ipady = 0;
		c.weighty = 0.0;   //request any extra vertical space
		c.gridx = 1;       //aligned with button 2
		c.gridy = 3;       //third row
		c.gridwidth = 2;   //2 columns wide
		
		ContentPanel.add(Test, c);
		
		
		
		panel.add(ContentPanel);
		
//		ImageIcon myImageIcon = createImageIcon("images/field.png");
//		Dimension size = ContentPanel.getPreferredSize();
//		JLabel test2 = new JLabel ("") {
//		    @Override
//		    public void paintComponent (Graphics g) {
//		        super.paintComponent (g);
//		        System.out.println("override");
//		        g.drawImage (myImageIcon.getImage(), 0, 0, 500, 396, null);
//		    }
//		};
//		test2.setPreferredSize(new Dimension(500,396));
//		test2.setOpaque(true);
//		test2.setBackground(new Color(0,0,0,255));
		//ContentPanel.add(test2);
		
//		JPanel Test2 = new JPanel();
//		Test2.add(new JLabel("drop shit hier"));
//		Test2.setOpaque(true); Test2.setBackground(new Color(50,50,50));
//		Test2.setPreferredSize(new Dimension(200,50));Test2.setMaximumSize(new Dimension(200,50));
//		Test2.setFocusable(false);
//		new MyDropTargetListener(Test2);
//		panel.add(Test2);
		
		//content ends here
				
		//add panel
		panel.setMinimumSize(new Dimension(100,500));
		panel.setPreferredSize(new Dimension(800,550));
		panel.setMaximumSize(new Dimension(900,600));
		this.add(panel);
	}
	
	private JPanel newTest(){
		JPanel Test = new JPanel();
		Test.setLayout(new BoxLayout(Test, BoxLayout.Y_AXIS));
		JLabel Test2 = new JLabel("Drop shit hier"); 
		Test2.setOpaque(true); Test2.setBackground(new Color(100,100,100));
		Test.add(Test2);
		Test.setOpaque(true); Test.setBackground(new Color(50,50,50));
		//Test.setPreferredSize(new Dimension(200,50)); Test.setMaximumSize(new Dimension(200,50));
		Test.setFocusable(false);
		new MyDropTargetListener(Test);
		return Test;
	}
	
	private JPanel PlayerPanel(){
		JPanel panel = new JPanel();
		panel.setName("playerPanel");
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(150,50));
		panel.setMinimumSize(new Dimension(150,50));
		
		//shirt
		ImageIcon myImageIcon = createImageIcon("images/Shirts/" +	Integer.toString(11) + ".png");
		JLabel label1 = new JLabel ("  	   ") {
		    @Override
		    public void paintComponent (Graphics g) {
		        super.paintComponent (g);
		        System.out.println("override");
		        g.drawImage (myImageIcon.getImage(), 0, 0, 38, 38, null);
		    }
		};
		
		//name
		JLabel label2 = new JLabel("Naam hier");
		label2.setFont(fontTitle);
		
		//finish up
		panel.add(label1);
		panel.add(Box.createRigidArea(new Dimension(10,0)));
		panel.add(label2);
		new MyDropTargetListener(panel);
		return panel;
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
		          
		          
		          //this.panel.add(new JLabel(Integer.toString(player.getNumber()) + " " + player.getName()));
		          
		          
		          
		          this.panel.revalidate();
		          this.panel.repaint();
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
	
}

//JPanel with attached Player Back Number
class PlayerPanel extends JPanel {
	Player player;
	int position;
	
	public PlayerPanel(int position){
		this.position = position;
	}	
	public Player getPlayer(){
		return player;
	}
}
