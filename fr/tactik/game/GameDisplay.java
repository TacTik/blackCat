package fr.tactik.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This is the GameDisplay Class. This class permit to display all the entity of the game.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
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
    		g.drawImage(player.getCurrentTexture(),0,0,null);
	    	g.drawImage(background,0,0,null);

	}
}
