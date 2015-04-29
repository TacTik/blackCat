package fr.tactik.editor;

import handlers.TexturesMapper;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Vector;

public class TilesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int nbTexturesSet = 6; // BEGIN AT 1 ! so, there is 5 texture in it.
	static String rootdir = System.getProperty("user.dir");
	
	public TilesPanel() {
		boolean t = TexturesMapper.init();

		Vector <JButton> buttons = new Vector<JButton>();
		setBackground(Color.white);
		FlowLayout layout = new FlowLayout(0, 0, 0);
		setLayout(layout);
		
		for (int i = 1; i <= nbTexturesSet; i++)
		buttons.add(new JButton (TexturesMapper.getImageById(i)));
		

		for (JButton b : buttons) {
			b.setPreferredSize(new Dimension(50, 50));
			b.setBackground(null);
	        add(b);
		  }

        
       // b.addActionListener(new ButtonEvent(b, system, row, col));
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setPreferredSize(new Dimension (100, 200));
	}
}
