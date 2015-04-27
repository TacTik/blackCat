package fr.tactik.editor;

import handlers.TextureHandler;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.FileNotFoundException;
import java.util.Vector;


public class TilesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static String rootdir = System.getProperty("user.dir");
	
	Image player_texturesImages;
	Image still_1_texturesImages;
	Image still_2_texturesImages;
	Image mobile_1_texturesImages;
	Image mobile_2_texturesImages;
	

	
	public TilesPanel() {
		setBackground(Color.white);
		FlowLayout layout = new FlowLayout(0, 0, 0);
		setLayout(layout);

		player_texturesImages = TextureHandler.getTexturesFromFolder(rootdir + "/images/game/player/", 1).elementAt(0);
		still_1_texturesImages = TextureHandler.getTexturesFromFolder(rootdir + "/images/game/still1/", 1).elementAt(0);
		still_2_texturesImages = TextureHandler.getTexturesFromFolder(rootdir + "/images/game/still2/", 1).elementAt(0);
		mobile_1_texturesImages = TextureHandler.getTexturesFromFolder(rootdir + "/images/game/mobile1/", 1).elementAt(0);
		mobile_2_texturesImages = TextureHandler.getTexturesFromFolder(rootdir + "/images/game/mobile2/", 1).elementAt(0);
		
		for (int row = 0; row < 2; row++){
            for (int col = 0; col < 3; col++){
                JButton b = new JButton (new ImageIcon(player_texturesImages));
                b.setPreferredSize(new Dimension(50, 50));
                add(b);
               // b.addActionListener(new ButtonEvent(b, system, row, col));
            }
        }
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setPreferredSize(new Dimension (100, 200));
		
		//paint all images in each folder
		
		// Player
    	g.drawImage(player_texturesImages,0,0, 50, 50,null);
    	// still 1
    	g.drawImage(still_1_texturesImages,0,50, 50, 50, null);
    	// still 2
    	g.drawImage(still_2_texturesImages,0,100, 50, 50, null);
    	// mobile 1
    	g.drawImage(mobile_1_texturesImages,0,150, 50, 50, null);
    	// mobile 2
    	g.drawImage(mobile_2_texturesImages,0,200, 50, 50, null);
   	
	}
}
