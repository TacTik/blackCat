package fr.tactik.editor;

import handlers.SelectTileHelper;
import handlers.TexturesMapper;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * This is the tiles panel Class. This class .
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class TilesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int nbTexturesSet = 15; // BEGIN AT 1 ! so, there is 5 texture in it.
	static String rootdir = System.getProperty("user.dir");
	
	/**
	 * This is the constructor of the tiles panel.
	 */
	public TilesPanel() {
		boolean t = TexturesMapper.init();

		Vector <JButton> buttons = new Vector<JButton>();
		setBackground(Color.white);
		FlowLayout layout = new FlowLayout(0, 0, 0);
		setLayout(layout);
		
		for (int i = 1; i < nbTexturesSet; i++){
			JButton b = new JButton (TexturesMapper.getImageById(i));
			b.setName(Integer.toString(i));
			buttons.add(b);
		}

		for (final JButton b : buttons) {
			b.setPreferredSize(new Dimension(50, 50));
			b.setBackground(new Color(225, 225, 225));
	        b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					 SelectTileHelper.setSelectedTile(Integer.parseInt(b.getName()));
					
				}
			});
	        add(b);
		  }
	}

	/**
	 * This function draw the tiles.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setPreferredSize(new Dimension (100, 200));
	}
}
