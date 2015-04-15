package fr.tactik.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameDisplay extends JPanel {
	//hero
	//monde
	//controler de jeu
	//controler de menu

	Player player;
	Image background;
	
	public GameDisplay() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void setBackground(String path){
		  // load the background image
        try {
        	background = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    	g.drawImage(background,0,0, 50,50,null);
	}
}
