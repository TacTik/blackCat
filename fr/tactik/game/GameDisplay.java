package fr.tactik.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This is the GameDisplay Class. This class permit to display all the entity of the game.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */

public class GameDisplay extends JPanel implements Runnable {
	//hero
	//monde
	//controler de jeu
	//controler de menu

	static String rootdir = System.getProperty("user.dir");
	
	Image background;
	Vector<Entity> entities;
	int offsetX=0;
	int offsetY=0;
	
	long lastLoopTime = System.nanoTime();
	final int TARGET_FPS = 60;
	final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	int lastFpsTime =0;
    int fps = 0;
	
	public void initGame() {
		
		entities = new Vector<Entity>();
		
		Player player1 = new Player(10,10,50,50,false, rootdir + "/images/game/player/",1, 1);
		Player player2 = new Player(600,600,50,50,false, rootdir + "/images/game/player/",2, 1);
		
		entities.add (player1);
		entities.add (player2);
	}
	
	public void gameUpdate() {
		entities.get(0).setPosX((entities.get(0).getPosX() + 1)%800 );
	}
	
	public void render() {
		repaint();
	}
	
	
	public GameDisplay() {
		// TODO Auto-generated constructor stub
		initGame();	
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
	    	g.drawImage(background,0,0,null);
    		//g.drawImage(player.getCurrentTexture(),(int)player.getPosX(),(int)player.getPosX(),null);
	    	for (int i = 0; i < entities.size(); i++){
	    		g.drawImage(entities.get(i).getCurrentTexture(),(int)entities.get(i).getPosX()+offsetX,(int)entities.get(i).getPosY()+offsetY,null);
	    	}

	}

	@Override
	public void run() {
		
		while (true){
			 long now = System.nanoTime();
		     long updateLength = now - lastLoopTime;
		     lastLoopTime = now;
		     double delta = updateLength / ((double)OPTIMAL_TIME);

		      // update the frame counter
		      lastFpsTime += updateLength;
		      fps++;
		      
		      // update our FPS counter if a second has passed since
		      // we last recorded
		      if (lastFpsTime >= 1000000000)
		      {
		         System.out.println("(FPS: "+fps+")");
		         lastFpsTime = 0;
		         fps = 0;
		      }
		      
		      if (lastFpsTime >= 1000000000)
		      {
		         System.out.println("(FPS: "+fps+")");
		         lastFpsTime = 0;
		         fps = 0;
		      }
		      
			gameUpdate();
			render();
			
			try {
				Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
