package fr.tactik.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
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

public class GameDisplay extends JPanel implements Runnable{
	//hero
	//monde
	//controler de jeu
	//controler de menu

	static String rootdir = System.getProperty("user.dir");
	
	Image background;
	Player player;
	Vector<Mobile> mobiles;
	Vector<Still> stills;
	int offsetX=0;
	int offsetY=0;
	
	long lastLoopTime = System.nanoTime();
	final int TARGET_FPS = 60;
	final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	int lastFpsTime =0;
    int fps = 0;
    ControlerPlayer controlPlayer;

    int[][] level;
    static int nbLines = 0;
	static int nbColumns = 0;


    
	public static int[][] readLevel(){
		
		int i = 0;
		int[][] tab = null;
    	
		try{
    		FileInputStream fstream = new FileInputStream("level1.txt");
    		DataInputStream in = new DataInputStream(fstream);
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    		String strLine;
    		
    		strLine = br.readLine();  
			String[] tokens = strLine.split(" ");
			nbLines = Integer.parseInt(tokens[0]);
			nbColumns = Integer.parseInt(tokens[1]);
			tab = new int [nbLines][nbColumns];
    		
    		while ((strLine = br.readLine()) != null)   {
    			tokens = strLine.split(" ");
	    	    for (int j = 0; j < nbColumns ; j++){
	    		    tab[i][j] = Integer.parseInt(tokens[j]);
    			}
	    	    i++;
    		}
    		in.close();
    	}
    	catch (Exception e){
    	     System.err.println("Error: " + e.getMessage());
    	}
    	return tab;
	}
    
    
	public void initGame() {
			
		mobiles = new Vector<Mobile>();		
		stills = new Vector<Still>();
		
		
		 level = readLevel();

		for (int i = 0; i < nbLines; i++){
			for (int j = 0; j < nbColumns ; j++){
				int id = level[i][j];
				switch (id) {
		            case 1:  player = new Player(50*j,50*i,50,50,false, rootdir + "/images/game/player/",1, i*nbColumns+j);
		            		 level[i][j] = 0;
		                     break;
		            case 2:  MobilePlat mobile1 = new MobilePlat(50*j,50*i,50,50,false, rootdir + "/images/game/mobile1/",2, i*nbColumns+j);
		            		 mobiles.add (mobile1);
		            		 break;
		            case 3:  MobilePlat mobile2 = new MobilePlat(50*j,50*i,50,50,false, rootdir + "/images/game/mobile2/",2, i*nbColumns+j);
		            		 mobiles.add (mobile2);
		            		 break;
		            case 4:  StillPlat still1 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/still1/",0, i*nbColumns+j);
	       		 			 stills.add (still1);
	       		 			 break;
		            case 5:  StillPlat still2 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/still2/",0, i*nbColumns+j);
			 			 	 stills.add (still2);
			 			 	 break;
		            case 6:  MobilePlat foe1 = new MobilePlat(50*j,50*i,50,50,false, rootdir + "/images/game/mobile2/",0, i*nbColumns+j);
	 			 	 		 mobiles.add (foe1);
	 			 	 		 break;
		            default: 
		                     break;
				}
			}
		}
		
		controlPlayer = new ControlerPlayer(player);
		this.addKeyListener(controlPlayer);
		this.setFocusable(true);
	}
	
	public void gameUpdate() {

		//mobiles.get(2).moveInField(100, 0);

		// mobiles.get(0).moveLeft(1);
		//player.updateCurrentText();

		controlPlayer.control();
		controlPlayer.playerSprite();
		player.jump();
		player.gravity();
		player.collision(level,nbLines,nbColumns);
		player.update();
		
		//mobiles.get(2).update();
	}
	
	public void render() {		
		repaint();
	}
	
	public GameDisplay() {	
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
	    	// Background
	    	g.drawImage(background,0,0,null);
    		
	    	// Player
	    	g.drawImage(player.getCurrentTexture(),(int)player.getPosX()+offsetX,(int)player.getPosY()+offsetY,this);
	    	
	    	// Mobile elements
	    	for (int i = 0; i < mobiles.size(); i++){
	    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX()+offsetX,(int)mobiles.get(i).getPosY()+offsetY,null);
	    	}
	    	
	    	// Still elements
	    	for (int i = 0; i < stills.size(); i++){
	    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX()+offsetX,(int)stills.get(i).getPosY()+offsetY,null);
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
		         //System.out.println("(FPS: "+fps+")");
		         lastFpsTime = 0;
		         fps = 0;
		      }
		      
		      if (lastFpsTime >= 1000000000)
		      {
		         // System.out.println("(FPS: "+fps+")");
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
