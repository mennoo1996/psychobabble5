package swinggui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class Panel_Example1 extends JPanel {
	
	public Panel_Example1() {
		initUI();
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
		JLabel title = new JLabel("Panel title here");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(title);
		
		//content begins here, I just added two meaningless buttons
		
		//draggable image
		ImageIcon icon1 = createImageIcon("images/draggableimage.png");
		
		//dragger label
		JLabel Label1 = new JLabel(icon1);
		
		panel.add(Label1);
		
		//dropper button
		JButton Button1 = new JButton("Drag here");
		Button1.setName("Test");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button1.setFocusable(false);
		panel.add(Button1);
		
		MouseListener listener = new DragMouseAdapter();
		Label1.addMouseListener(listener);
		Label1.setTransferHandler(new TransferHandler("icon"));
		Button1.setTransferHandler(new TransferHandler("icon"));
		
		
		JTextField field = new JTextField();
		panel.add(field);
		
		Button1.setTransferHandler(new TransferHandler("Text"));
		field.setDragEnabled(true);
		
		
		JButton Button2 = new JButton("Another button");
		Button2.setName("Test");
		Button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Button2);
		
		//content ends here
		
		//add panel
		panel.setMinimumSize(new Dimension(100,500));
		panel.setPreferredSize(new Dimension(450,500));
		panel.setMaximumSize(new Dimension(900,500));
		this.add(panel);
	}
	
	class DragMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }	
	
	public static void main(String[] args) {
		Panel_Example1 thing = new Panel_Example1();
		thing.setVisible(true);
	}
}
