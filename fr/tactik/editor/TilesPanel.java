package fr.tactik.editor;

import handlers.TextureHandler;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.Vector;

public class TilesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static String rootdir = System.getProperty("user.dir");
	
	ImageIcon player_texturesImages;
	ImageIcon still_1_texturesImages;
	ImageIcon still_2_texturesImages;
	ImageIcon mobile_1_texturesImages;
	ImageIcon mobile_2_texturesImages;
	

	
	public TilesPanel() {
		Vector <JButton> buttons = new Vector<JButton>();
		setBackground(Color.white);
		FlowLayout layout = new FlowLayout(0, 0, 0);
		setLayout(layout);

		player_texturesImages = TextureHandler.getImageIconFromPath(rootdir + "/images/game/player/", 1).elementAt(0);
		still_1_texturesImages = TextureHandler.getImageIconFromPath(rootdir + "/images/game/still1/", 1).elementAt(0);
		still_2_texturesImages = TextureHandler.getImageIconFromPath(rootdir + "/images/game/still2/", 1).elementAt(0);
		mobile_1_texturesImages = TextureHandler.getImageIconFromPath(rootdir + "/images/game/mobile1/", 1).elementAt(0);
		mobile_2_texturesImages = TextureHandler.getImageIconFromPath(rootdir + "/images/game/mobile2/", 1).elementAt(0);
		
		buttons.add(new JButton (player_texturesImages));
		buttons.add(new JButton (still_1_texturesImages));
		buttons.add(new JButton (still_1_texturesImages));
		buttons.add(new JButton (mobile_1_texturesImages));
		buttons.add(new JButton (mobile_2_texturesImages));
		

		for (JButton b : buttons) {
			b.setPreferredSize(new Dimension(50, 50));
	        add(b);
		  }

        
       // b.addActionListener(new ButtonEvent(b, system, row, col));

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setPreferredSize(new Dimension (100, 200));
   	
	}
}
