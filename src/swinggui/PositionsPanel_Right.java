package swinggui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import libraryClasses.Player;

public class PositionsPanel_Right extends JPanel {
	
	private String panelTitle;
	
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
		panel.add(new Box.Filler(new Dimension(1,5), new Dimension(1,5), new Dimension(1,5)));
				
		//panel title
		JPanel titlepanel = new JPanel();
		
		JLabel title = new JLabel(panelTitle);
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 30));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(145,143,143)));
		titlepanel.add(title);
		panel.add(titlepanel);
				
		//content begins here
		
		JPanel Test2 = new JPanel();
		Test2.add(new JLabel("drop shit hier"));
		Test2.setOpaque(true); Test2.setBackground(new Color(50,50,50));
		Test2.setPreferredSize(new Dimension(200,50));Test2.setMaximumSize(new Dimension(200,50));
		Test2.setFocusable(false);
		new MyDropTargetListener(Test2);
		panel.add(Test2);
		
		//content ends here
				
		//add panel
		panel.setMinimumSize(new Dimension(100,500));
		panel.setPreferredSize(new Dimension(800,500));
		panel.setMaximumSize(new Dimension(900,500));
		this.add(panel);
	}
	
	
	public static void main(String[] args) {
		Panel_Example2 thing = new Panel_Example2("Insert title here");
		thing.setVisible(true);
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
		          this.panel.add(new JLabel(Integer.toString(player.getNumber()) + " " + player.getName()));
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
