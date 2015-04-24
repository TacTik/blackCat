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




    
	public static int[][] readLevel(int a, int b){
		int [][] tab = new int [a][b];
		int i = 0;
    	try{
    		FileInputStream fstream = new FileInputStream("level1.txt");
    		DataInputStream in = new DataInputStream(fstream);
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    		String strLine;
    		while ((strLine = br.readLine()) != null)   {
	    	   String[] tokens = strLine.split(" ");
	    	   for (int j = 0; j < b ; j++){
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
	
	public static int readLevelLines(){
		int res = -1;
    	try{
    		FileInputStream fstream = new FileInputStream("level1_size.txt");
    		DataInputStream in = new DataInputStream(fstream);
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    		String strLine;
    		while ((strLine = br.readLine()) != null)   {
 	    		String[] tokens = strLine.split(" ");
 	    		res = Integer.parseInt(tokens[0]);
 	    	}
     		in.close();
    	}
    	catch (Exception e){
    	     System.err.println("Error: " + e.getMessage());
    	}
    	return res;
	}
	
	public static int readLevelColumns(){
		int res = -1;
    	try{
    		FileInputStream fstream = new FileInputStream("level1_size.txt");
    		DataInputStream in = new DataInputStream(fstream);
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    		String strLine;
    		while ((strLine = br.readLine()) != null)   {
 	    		String[] tokens = strLine.split(" ");
 	    		res = Integer.parseInt(tokens[1]);
 	    	}
     		in.close();
    	}
    	catch (Exception e){
    	     System.err.println("Error: " + e.getMessage());
    	}
    	return res;
	}
    
    
	public void initGame() {
			
		mobiles = new Vector<Mobile>();		
		stills = new Vector<Still>();
		
		int nbLines = readLevelLines();
		int nbColumns = readLevelColumns();
		int[][] level = readLevel(nbLines,nbColumns);

		
		controlPlayer = new ControlerPlayer(player);
		this.addKeyListener(controlPlayer);
		this.setFocusable(true);

		for (int i = 0; i < nbLines; i++){
			for (int j = 0; j < nbColumns ; j++){
				int id = level[i][j];
				switch (id) {
		            case 1:  player = new Player(50*j,50*i,50,50,false, rootdir + "/images/game/player/",1, i*nbColumns+j);
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
	}
	
	public void gameUpdate() {

		mobiles.get(0).moveLeft(1);
		mobiles.get(1).updateCurrentText();
		mobiles.get(2).moveInField(100, 0);

		// mobiles.get(0).moveLeft(1);
		// mobiles.get(1).updateCurrentText();
		player.update();
		mobiles.get(2).update();
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
	    	g.drawImage(player.getCurrentTexture(),(int)player.getPosX()+offsetX,(int)player.getPosY()+offsetY,null);
	    	
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
