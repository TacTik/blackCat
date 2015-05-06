package fr.tactik.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This is the GameDisplay Class. This class permit to display all the entity of the game.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */

public class GameDisplay extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;

	//hero
	//monde
	//controler de jeu
	//controler de menu

	static String rootdir = System.getProperty("user.dir");
	
	Image background;
	Image footprint;
	Player player;
	Vector<Mobile> mobiles;
	Vector<Still> stills;
	
	static int windowSizeX = 1000;
	static int windowSizeY = 600;
	static int worldSizeX = 0;
	static int worldSizeY = 0;
	int offsetX = 0;
	int offsetY = 0;
	
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
    		FileInputStream fstream = new FileInputStream("level2.txt");
    		DataInputStream in = new DataInputStream(fstream);
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    		String strLine;
    		
    		strLine = br.readLine();  
			String[] tokens = strLine.split(" ");
			nbLines = Integer.parseInt(tokens[0]);
			nbColumns = Integer.parseInt(tokens[1]);
			tab = new int [nbLines][nbColumns];
			
			worldSizeX = nbColumns * 50;
			worldSizeY = nbLines * 50;
			
    		
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
		
		try {
        	background = ImageIO.read(new File(rootdir + "/images/game/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		try {
        	footprint = ImageIO.read(new File(rootdir + "/images/game/footprint.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		mobiles = new Vector<Mobile>();		
		stills = new Vector<Still>();
		
		
		level = readLevel();

		for (int i = 0; i < nbLines; i++){
			for (int j = 0; j < nbColumns ; j++){
				int id = level[i][j];
				switch (id) {
		            case 1:  player = new Player(50*j,50*i,50,50,false, rootdir + "/images/game/player/",1, 1);
		            		 level[i][j] = 0;
		                     break;
		            case 2:  MobilePlat mobile1 = new MobilePlat(50*j,50*i,50,50,false, rootdir + "/images/game/mobile1/",0, 2);
		            		 mobiles.add (mobile1);
		            		 break;
		            case 3:  MobilePlat mobile2 = new MobilePlat(50*j,50*i,50,50,false, rootdir + "/images/game/mobile2/",2, 3);
		            		 mobiles.add (mobile2);
		            		 break;
		            case 10: StillPlat stillPlat1 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/stillPlat1/",0, 10);
	       		 			 stills.add (stillPlat1);
	       		 			 break;
		            case 11: StillPlat lifeBonus = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/lifeBonus/",0, 11);
			 			 	 stills.add (lifeBonus);
			 			 	 break;
		            case 12: StillPlat foe1 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/foe1/",0, 12);
	 			 	 		 stills.add (foe1);
	 			 	 		 break;
		            case 13: StillPlat key = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/key/",0, 13);
				 	 		 stills.add (key);
				 	 		 break;
		            case 14: StillPlat door = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/door/",0, 14);
		 	 		 		 stills.add (door);
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
		
		for (int i = 0; i < mobiles.size(); i++){
			int x = 0;
			int y = 0;
			switch (mobiles.get(i).id) {
	            case 1:  x = 0;
	            		 y = 100;
	                     break;
	            case 2:  x = 0;
       		 			 y = 100;
	            		 break;
	            case 3:  x = 100;
       		 			 y = 100;
	            		 break;
	            default: x = 0;
	            		 y = 0;
	                     break;
			}
			mobiles.get(i).moveInField(x, y, level,mobiles.get(i).id);
			mobiles.get(i).update();
		}
		

		controlPlayer.control();
		controlPlayer.playerSprite();
		player.jump();
		player.gravity();
		player.collision(level,nbLines,nbColumns,stills);
		player.update();
		//System.out.println(level[8][5]);
		
		offsetX = (int) player.posX - (windowSizeX/2 - 50);
		offsetY = (int) player.posY - (windowSizeY/2 - 25);
		
	}
	
	public void render() {		
		repaint();
	}
	
	public GameDisplay() {	
		initGame();	
	}	

	
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    	// Background
	    	g.drawImage(background,0,0,null);
	    	//System.out.println("worldSizeY "+worldSizeY);
    		//System.out.println("windowSizeY/2 "+windowSizeY/2);
	    	//System.out.println(player.posX);
	    	//System.out.println(player.posY);
	    	// -------------------------------- Center -----------------------------	    	
	    	if (player.posX > windowSizeX/2 - 50 && player.posX < worldSizeX - windowSizeX/2 &&
	    		player.posY > windowSizeY/2 && player.posY < worldSizeY - windowSizeY/2){
	    		
	    		// Player
		    	g.drawImage(player.getCurrentTexture(),windowSizeX/2 - 50,windowSizeY/2 - 25,this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX() - offsetX,(int)mobiles.get(i).getPosY() - offsetY,null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX() - offsetX,(int)stills.get(i).getPosY() - offsetY,null);
		    	}
	    	}
	    	
	    	// -------------------------------- Top/Left -----------------------------
	    	else if (player.posX <= windowSizeX/2 - 50 &&
		    		player.posY <= windowSizeY/2){
	    		
	    		// Player
			    g.drawImage(player.getCurrentTexture(),(int)player.getPosX(),(int)player.getPosY(),this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX(),(int)mobiles.get(i).getPosY(),null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX(),(int)stills.get(i).getPosY(),null);
		    	}
	    	}
	    	
	    	// -------------------------------- Left -----------------------------
	    	else if (player.posX <= windowSizeX/2 - 50 &&
	    			player.posY > windowSizeY/2 && player.posY < worldSizeY - windowSizeY/2){

	    		// Player
			    g.drawImage(player.getCurrentTexture(),(int)player.getPosX(),windowSizeY/2 - 25,this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX(),(int)mobiles.get(i).getPosY() - offsetY,null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX(),(int)stills.get(i).getPosY() - offsetY,null);
		    	}
	    	}
	    	
	    	// -------------------------------- Bot/Left -----------------------------
	    	else if (player.posX <= windowSizeX/2 - 50 &&
		    		player.posY >= worldSizeY - windowSizeY/2){
	    		
	    		// Player
			    g.drawImage(player.getCurrentTexture(),(int)player.getPosX(),(int)player.getPosY() - (worldSizeY - windowSizeY + 50),this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX(),(int)mobiles.get(i).getPosY() - (worldSizeY - windowSizeY + 50),null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX(),(int)stills.get(i).getPosY() - (worldSizeY - windowSizeY + 50),null);
		    	}
	    	}
	    	
	    	// -------------------------------- Bot -----------------------------
	    	else if (player.posX > windowSizeX/2 - 50 && player.posX < worldSizeX - windowSizeX/2 &&
		    		player.posY >= worldSizeY - windowSizeY/2){

	    		// Player
			    g.drawImage(player.getCurrentTexture(),windowSizeX/2 - 50,(int)player.getPosY() - (worldSizeY - windowSizeY + 50),this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX() - offsetX,(int)mobiles.get(i).getPosY() - (worldSizeY - windowSizeY + 50),null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX() - offsetX,(int)stills.get(i).getPosY() - (worldSizeY - windowSizeY + 50),null);
		    	}
	    	}
	    	
	    	// -------------------------------- Bot/Right -----------------------------
	    	else if (player.posX >= worldSizeX - windowSizeX/2 &&
		    		player.posY >= worldSizeY - windowSizeY/2){
	    		
	    		// Player
			    g.drawImage(player.getCurrentTexture(),(int)player.getPosX()- (worldSizeX - windowSizeX + 20),(int)player.getPosY() - (worldSizeY - windowSizeY + 50),this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX()- (worldSizeX - windowSizeX + 20),(int)mobiles.get(i).getPosY() - (worldSizeY - windowSizeY + 50),null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX()- (worldSizeX - windowSizeX + 20),(int)stills.get(i).getPosY() - (worldSizeY - windowSizeY + 50),null);
		    	}
	    	}
	    	
	    	// -------------------------------- Right -----------------------------
	    	else if (player.posX >= worldSizeX - windowSizeX/2 &&
	    			player.posY > windowSizeY/2 && player.posY < worldSizeY - windowSizeY/2){
	    		
	    		// Player
			    g.drawImage(player.getCurrentTexture(),(int)player.getPosX()- (worldSizeX - windowSizeX + 20),windowSizeY/2 - 25,this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX()- (worldSizeX - windowSizeX + 20),(int)mobiles.get(i).getPosY() - offsetY,null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX()- (worldSizeX - windowSizeX + 20),(int)stills.get(i).getPosY() - offsetY,null);
		    	}
	    	}
	    	
	    	// -------------------------------- Top/Right -----------------------------
	    	else if (player.posX >= worldSizeX - windowSizeX/2 &&
	    			player.posY <= windowSizeY/2){
	    		
	    		// Player
			    g.drawImage(player.getCurrentTexture(),(int)player.getPosX()- (worldSizeX - windowSizeX + 20),(int)player.getPosY(),this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX()- (worldSizeX - windowSizeX + 20),(int)mobiles.get(i).getPosY(),null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX()- (worldSizeX - windowSizeX + 20),(int)stills.get(i).getPosY(),null);
		    	}
	    	}
	    	
	    	// -------------------------------- Top -----------------------------
	    	else if (player.posX > windowSizeX/2 - 50 && player.posX < worldSizeX - windowSizeX/2 &&
	    			player.posY <= windowSizeY/2){
	    		
	    		// Player
			    g.drawImage(player.getCurrentTexture(),windowSizeX/2 - 50,(int)player.getPosY(),this);
		    	
		    	// Mobile elements
		    	for (int i = 0; i < mobiles.size(); i++){
		    		g.drawImage(mobiles.get(i).getCurrentTexture(),(int)mobiles.get(i).getPosX() - offsetX,(int)mobiles.get(i).getPosY(),null);
		    	}
		    	
		    	// Still elements
		    	for (int i = 0; i < stills.size(); i++){
		    		g.drawImage(stills.get(i).getCurrentTexture(),(int)stills.get(i).getPosX() - offsetX,(int)stills.get(i).getPosY(),null);
		    	}
	    	}
	    	
	    	
	    	
	    	// Life points
	    	for (int i = 0; i < player.lifePoints; i++){
	    		g.drawImage(footprint,30*i +10,10 ,null);
	    	}

	}

	@Override
	public void run() {
		
		while (true){
			 long now = System.nanoTime();
		     long updateLength = now - lastLoopTime;
		     lastLoopTime = now;

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
