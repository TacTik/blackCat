package fr.tactik.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.io.FilenameUtils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import fr.tactik.editor.LevelPanel;

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
	
	static String backgroundPath;
	Image background;
	Image footprint;
	Player player;
	Vector<Mobile> mobiles;
	Vector<Still> stills;
	Vector<String> listLevels;
	static int windowSizeX = 1000;
	static int windowSizeY = 600;
	static int worldSizeX = 0;
	static int worldSizeY = 0;
	int offsetX = 0;
	int offsetY = 0;
	int currentLevel = 0;
	
	long lastLoopTime = System.nanoTime();
	final int TARGET_FPS = 60;
	final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	int lastFpsTime =0;
    int fps = 0;
    boolean isRunning = false;
    ControlerPlayer controlPlayer;

    int[][] level;
    static int nbLines = 0;
	static int nbColumns = 0;


    
	public static int[][] readLevel(String levelPath){
		
		int i = 0;
		int[][] tab = null;
    	
		try{
			
			FileInputStream fstream = new FileInputStream(levelPath);
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
			
    		
    		while (i != nbLines && (strLine = br.readLine()) != null)   {
    			tokens = strLine.split(" ");
	    	    for (int j = 0; j < nbColumns ; j++){
	    		    tab[i][j] = Integer.parseInt(tokens[j]);
    			}
	    	    i++;
    		}
    		backgroundPath = br.readLine();
    		in.close();
    	}
    	catch (Exception e){
    	     System.err.println("Error: " + e.getMessage());
    	}
    	return tab;
	}
	
	
    
	
    public void createListOfLevels(){
    	listLevels = new Vector<String>();
		final String path = "./levels";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  listLevels.add("./levels/" + listOfFiles[i].getName());
		      }
		    }
		Arrays.sort(listOfFiles);
		if (listLevels.isEmpty()){
			JOptionPane.showMessageDialog(null, "There is no level to load ! ");
			System.exit(0);
		}
    }
    
	public void initGame(boolean isFirst) {
		readSound(rootdir + "/sounds/theme.wav");
		if(isFirst){
			level = readLevel(listLevels.elementAt(currentLevel));
		}
		else {
			final JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("./levels"));
			chooser.setDialogTitle("Choose a Level");
			chooser.setMultiSelectionEnabled(false);
			if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				level = readLevel(chooser.getSelectedFile().toString());
			} else System.exit(-1);
		}
		
		try {
			if (backgroundPath == null){
				backgroundPath = "/images/game/backgrounds/01_background.jpg";
			}
        	background = ImageIO.read(new File(rootdir + "/" + backgroundPath));
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

		
		for (int i = 0; i < nbLines; i++){
			for (int j = 0; j < nbColumns ; j++){
				int id = level[i][j];
				switch (id) {
		            case 1:  player = new Player(50*j,50*i,50,50,false, rootdir + "/images/game/player/",1, 1);
		            		 level[i][j] = 0;
		                     break;
		            case 2:  MobilePlat mobile0 = new MobilePlat(50*j,50*i,50,50,false, rootdir + "/images/game/mobile/",0, 2);
		            		 mobiles.add (mobile0);
		            		 break;
		            case 3:  MobilePlat mobile1 = new MobilePlat(50*j,50*i,50,50,false, rootdir + "/images/game/mobile/",1, 3);
		            		 mobiles.add (mobile1);
		            		 break;
		            case 4: StillPlat stillPlat0 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/stillPlat/",0, 4);
	       		 			 stills.add (stillPlat0);
	       		 			 break;
		            case 5: StillPlat stillPlat1 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/stillPlat/",1, 5);
  		 			 		 stills.add (stillPlat1);
  		 			 		 break;
		            case 6: StillPlat stillPlat2 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/stillPlat/",2, 6);
  		 			 		 stills.add (stillPlat2);
  		 			 		 break;
		            case 7: StillPlat stillPlat3 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/stillPlat/",3, 7);
  		 			 		 stills.add (stillPlat3);
  		 			 		 break;
		            case 8: StillPlat stillPlat4 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/stillPlat/",4, 8);
  		 			 		 stills.add (stillPlat4);
  		 			 		 break;
		            case 9: StillPlat lifeBonus = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/lifeBonus/",0, 9);
			 			 	 stills.add (lifeBonus);
			 			 	 break;
		            case 10: StillPlat foe0 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/foe/",0, 10);
	 			 	 		 stills.add (foe0);
	 			 	 		 break;
		            case 11: StillPlat foe1 = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/foe/",1, 11);
		 	 		 		 stills.add (foe1);
		 	 		 		 break;
		            case 12: StillPlat key = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/key/",0, 12);
				 	 		 stills.add (key);
				 	 		 break;
		            case 13: StillPlat door = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/door/",0, 13);
		 	 		 		 stills.add (door);
		 	 		 		 break;
		            case 14: StillPlat questionGuy = new StillPlat(50*j,50*i,50,50,false, rootdir + "/images/game/questionGuy/",0, 14);
	 		 		 		 stills.add (questionGuy);
	 		 		 		 break;
		            default: 
		                     break;
				}
			}
		}
		
		if (null == player){
			player = new Player(50,50,50,50,false, rootdir + "/images/game/player/",1, 0);
   		 	level[0][0] = 0;
		}
		
		controlPlayer = new ControlerPlayer(player);
		this.addKeyListener(controlPlayer);
		this.setFocusable(true);
	}
	
	public void gameUpdate() {
		checkGameOver();
		for (int i = 0; i < mobiles.size(); i++){
			int x = 0;
			int y = 0;
			switch (mobiles.get(i).id) {
	            case 1:  x = 100;
	            		 y = 0;
	                     break;
	            case 2:  x = 0;
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
		player.collision(level,nbLines,nbColumns,stills,controlPlayer);
		player.update();
		
		//System.out.println(level[8][5]);
		
		offsetX = (int) player.posX - (windowSizeX/2 - 50);
		offsetY = (int) player.posY - (windowSizeY/2 - 25);
		
		if(player.triggerNextLevel){
			player.triggerNextLevel = false;
			nextLevel();
		}
		
	}
	
	public void render() {		
		repaint();
	}
	
	public GameDisplay() {
		isRunning = true;
		createListOfLevels();
		initGame(true);	
	}	

	
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    	// Background
	    	g.drawImage(background,0,0,null);
	    	if(null == player)
	    		return;

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
		
		while (isRunning){
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
				if((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000>0)
					Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void nextLevel(){
		isRunning = false;
		player.cleanInventory();
		if(currentLevel +1 < listLevels.size()){
			currentLevel ++;
			initGame(true);
		}
		else{
			JOptionPane.showMessageDialog(null, "CONGRATULATIONS !! You win the game !! ");
			System.exit(0);
		}
		isRunning = true;
		this.run();
	}
	
	public void checkGameOver(){
		if (player.lifePoints != 0)
			return;
		isRunning = false;
		player.cleanInventory();
		JOptionPane.showMessageDialog(null, "You loose the game ... ");
		System.exit(0);
	}
	
	public void readSound(String path){
	    InputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    AudioStream audioStream = null;
		try {
			audioStream = new AudioStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    AudioPlayer.player.start(audioStream);
	}
	
}
