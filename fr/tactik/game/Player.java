/**
 * 
 */
package fr.tactik.game;

import java.awt.Image;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is the player Class.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class Player extends Mobile{

	/**
	 * This function is the player constructor.
	 * @param posX
	 * 				this is the x position of the player.
	 * @param posY 
	 * 				this is the y position of the player.
	 * @param sizeX
	 * 				this is the x size of the player.
	 * @param sizeY 
	 * 				this is the y size of the player.
	 * @param isWalkable 
	 * 				this parameter indicate if the mobile is walkable or not.
	 * @param path 
	 * 				this the path of the player.
	 * @param current 
	 * 				It indicate what is the current texture if the mobile have several textures.
	 * @param id 
	 * 				This is the id of the player.
	 */
	public Player(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, String path,
		int current, int id) {
		super(posX, posY, sizeX, sizeY, isWalkable, path, current, id);
		// TODO Auto-generated constructor stub
		
		
	}

	/**
	 * 
	 */
	
	float jump = 0;
	int jumpsAvailable = 0;
	boolean running = false;
	
	int lifePoints = 7;
	int state;
	Vector<String> inventory = new Vector<String>();
	boolean triggerNextLevel = false;
	/**
	 * This function make the player jumping.
	 */
	public void jump(){
		this.moveUp(jump);
		if (this.jump > 0)
			this.jump -= 0.4;
		else this.jump =0;
	}
	
	/**
	 * This the gravity function for the player.
	 */
	public void gravity(){ 
		this.yspeed += 4;
	}
	
	/**
	 * This function manage the collision with the player and the other items in the level.
	 */
	public void collision(int[][] level, int nbLines, int nbColumns, Vector<Still> stills){	
		
		// Bottom
		if ((int)(posY + this.yspeed - 2) + 50 >= nbLines * 50){
			this.yspeed = 0;
			jumpsAvailable = 2;
		}
		else if (level[((int)(posY + this.yspeed - 2) / 50) + 1][(int)(posX + 8) / 50] != 0 ||
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] != 0 ||
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX - 8) / 50) + 2] != 0){
			
			// Life bonus
			if (level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] == 9){
				removeStillFromLevel(level,nbColumns,((int)(posY + this.yspeed - 2) / 50) + 1,((int)(posX) / 50) + 1,stills);
				this.lifePoints += 3;
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] = 0;
			}
			
			// Foe
			if (level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] == 10 ||
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] == 11){
				removeStillFromLevel(level,nbColumns,((int)(posY + this.yspeed - 2) / 50) + 1,((int)(posX) / 50) + 1,stills);
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] = 0;
			}
			
			// Key
			if (level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] == 12){
				removeStillFromLevel(level,nbColumns,((int)(posY + this.yspeed - 2) / 50) + 1,((int)(posX) / 50) + 1,stills);
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] = 0;
				this.inventory.add("key");
			}
			
			this.yspeed = 0;
			jumpsAvailable = 2;
		}
		else if (jumpsAvailable == 2) jumpsAvailable = 1;
		
		// Top
		if ((int)(posY + this.yspeed) < 0)
			this.yspeed = 0;
		else if (level[(int)(posY + this.yspeed - 1) / 50][(int)(posX + 8) / 50] != 0 ||
				level[(int)(posY + this.yspeed - 1) / 50][((int)(posX) / 50) + 1] != 0 ||	
				level[(int)(posY + this.yspeed - 1) / 50][((int)(posX - 8) / 50) + 2] != 0)
			this.yspeed = 0;
		

		/// Right
		if ((int)(posX + this.xspeed) + 100 >= nbColumns * 50)
			this.xspeed = 0;
		else if (level[(int)(posY + this.yspeed + 3) / 50][((int)(posX + this.xspeed-8) / 50) + 2] != 0 ||
				level[((int)(posY + this.yspeed - 3) / 50) + 1][((int)(posX + this.xspeed-8) / 50) + 2] != 0){
			
			// Life bonus
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 9){
				this.lifePoints += 3;
				removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,((int)(posX + this.xspeed-8) / 50) + 2,stills);
				level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] = 0;
			}
			
			// Foe
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 10 ||
					level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 11){
				this.lifePoints --;
				this.posX -= 75;
			}
			
			// Key
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 12){
				removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,((int)(posX + this.xspeed-8) / 50) + 2,stills);
				level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] = 0;
				this.inventory.add("key");
			}
			
			// Door
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 13){
				if(this.inventory.contains("key")){
					removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,((int)(posX + this.xspeed-8) / 50) + 2,stills);
					level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] = 0;
					this.triggerNextLevel = true;
				}
			}
			
			// QuestionGuy
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 14){
				if (questionGuy(controlPlayer)==true){
					removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,((int)(posX + this.xspeed-8) / 50) + 2,stills);
					level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] = 0;
				}
				else this.posX -= 10;
			}
			
			
			this.xspeed = 0;
			if (jumpsAvailable != 2) jumpsAvailable = 1;
		}
		
			
		
		// Left
		if ((int)(posX + this.xspeed) < 0)
			this.xspeed = 0;
		else if (level[(int)(posY + this.yspeed + 3) / 50][(int)(posX + this.xspeed+8) / 50] != 0 ||
				level[((int)(posY + this.yspeed - 3) / 50) + 1][(int)(posX + this.xspeed+8) / 50] != 0){
			
			// Life bonus
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 9){
				removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,(int)(posX + this.xspeed+8) / 50,stills);
				this.lifePoints += 3;
				level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] = 0;
			}
			
			// Foe
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 10 ||
				level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 11){
				this.lifePoints --;
				this.posX += 75;
			}
			
			// Key
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 12){
				removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,(int)(posX + this.xspeed+8) / 50,stills);
				level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] = 0;
				this.inventory.add("key");
			}
			
			// Door
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 13){
				if(this.inventory.contains("key")){
					removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,(int)(posX + this.xspeed+8) / 50,stills);
					level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] = 0;
					this.triggerNextLevel = true;
				}
			}
			
			// QuestionGuy
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 14){
				if (questionGuy(controlPlayer)==true){
					removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,(int)(posX + this.xspeed+8) / 50,stills);
					level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] = 0;
				}
				else this.posX += 10;
			}
			
			
			this.xspeed = 0;
			if (jumpsAvailable != 2) jumpsAvailable = 1;
		}
			
	}
	
	/**
	 * This function remove the still which is breakable if the player go on them.
	 */
	public void removeStillFromLevel(int[][] level, int nbColumns, int x, int y, Vector<Still> stills){
		int index = 0;
		
		for (int j = 0; j < x; j++){
			for (int i = 0; i < nbColumns; i++){
				if (level[j][i] >= 4 && level[j][i] <= 13) index++;
			}
		}
		for (int i = 0; i < y; i++){
			if (level[x][i] >= 4 && level[x][i] <= 13) index++;
		}
		stills.remove(index);
		return;
	}
	
	public void cleanInventory(){
		inventory.clear();
	}
	
	public boolean questionGuy(ControlerPlayer controlPlayer){
		String question = "";
		String answer = "";
		Random random = new Random();
		int foo = random.nextInt(10);
		
		switch (foo){
		case 0: question = "Quelle est la couleur du cheval blanc d'Henry IV ?";
				answer = "blanc";
				break;
		case 1: question = "Pourquoi ne font-ils pas de nourriture pour chat à saveur de souris ?";
				answer = "parce que";
				break;
		case 2: question = "Quelle est le meilleur langage de programmation ?";
				answer = "java";
				break;
		case 3: question = "C'est en sciant que Léonard devint...";
				answer = "scie";
				break;
		case 4: question = "Qui était là en premier: l'oeuf ou la poule ?";
				answer = "les deux";
				break;
		case 5: question = "Quel est le synonyme de synonyme ?";
				answer = "synonyme";
				break;
		case 6: question = "Question6";
				answer = "answer6";
				break;
		case 7: question = "Question7";
				answer = "answer7";
				break;
		case 8: question = "Question8";
				answer = "answer8";
				break;
		case 9: question = "Question9";
				answer = "answer9";
				break;
		default: break;
		}
		
		String test = JOptionPane.showInputDialog(null, question,"Le vieux sage",JOptionPane.QUESTION_MESSAGE);
		controlPlayer.right = false;
		controlPlayer.running = false;
		controlPlayer.left = false;
		this.running = false;
		if (test == null)
			return false;
		if (test.equals(answer)){
			return true;
		}
		else{
			return false;
		}
	}
}
