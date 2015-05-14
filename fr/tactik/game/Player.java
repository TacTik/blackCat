/**
 * 
 */
package fr.tactik.game;

import java.awt.Image;
import java.util.Vector;

/**
 * @author juliette
 *
 */
public class Player extends Mobile{

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
	
	int lifePoints = 5;
	int state;
	Vector<String> inventory = new Vector<String>();

	
	public void jump(){
		this.moveUp(jump);
		if (this.jump > 0)
			this.jump -= 0.4;
		else this.jump =0;
	}
	
	public void gravity(){ 
		this.yspeed += 4;
	}
	
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
			if (level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] == 15){
				removeStillFromLevel(level,nbColumns,((int)(posY + this.yspeed - 2) / 50) + 1,((int)(posX) / 50) + 1,stills);
				this.lifePoints += 3;
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] = 0;
			}
			
			// Foe
			if (level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] == 16 ||
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] == 17){
				removeStillFromLevel(level,nbColumns,((int)(posY + this.yspeed - 2) / 50) + 1,((int)(posX) / 50) + 1,stills);
				level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] = 0;
			}
			
			// Key
			if (level[((int)(posY + this.yspeed - 2) / 50) + 1][((int)(posX) / 50) + 1] == 18){
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
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 15){
				this.lifePoints += 3;
				removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,((int)(posX + this.xspeed-8) / 50) + 2,stills);
				level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] = 0;
			}
			
			// Foe
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 16 ||
					level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 17){
				this.lifePoints --;
				this.posX -= 75;
			}
			
			// Key
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 18){
				removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,((int)(posX + this.xspeed-8) / 50) + 2,stills);
				level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] = 0;
				this.inventory.add("key");
			}
			
			// Door
			if (level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] == 19){
				if(this.inventory.contains("key")){
					removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,((int)(posX + this.xspeed-8) / 50) + 2,stills);
					level[(int)(posY + this.yspeed +25) / 50][((int)(posX + this.xspeed-8) / 50) + 2] = 0;
				}
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
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 15){
				removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,(int)(posX + this.xspeed+8) / 50,stills);
				this.lifePoints += 3;
				level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] = 0;
			}
			
			// Foe
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 16 ||
				level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 17){
				this.lifePoints --;
				this.posX += 75;
			}
			
			// Key
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 18){
				removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,(int)(posX + this.xspeed+8) / 50,stills);
				level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] = 0;
				this.inventory.add("key");
			}
			
			// Door
			if (level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] == 19){
				if(this.inventory.contains("key")){
					removeStillFromLevel(level,nbColumns,(int)(posY + this.yspeed +25) / 50,(int)(posX + this.xspeed+8) / 50,stills);
					level[(int)(posY + this.yspeed +25) / 50][(int)(posX + this.xspeed+8) / 50] = 0;
				}
			}
			
			
			
			this.xspeed = 0;
			if (jumpsAvailable != 2) jumpsAvailable = 1;
		}
			
	}
	
	public void removeStillFromLevel(int[][] level, int nbColumns, int x, int y, Vector<Still> stills){
		int index = 0;
		
		for (int j = 0; j < x; j++){
			for (int i = 0; i < nbColumns; i++){
				if (level[j][i] >= 10 && level[j][i] <= 20) index++;
			}
		}
		for (int i = 0; i < y; i++){
			if (level[x][i] >= 10 && level[x][i] <= 20) index++;
		}
		stills.remove(index);
		return;
	}
	
}
