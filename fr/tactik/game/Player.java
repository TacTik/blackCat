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
	Vector<String> inventory;

	
	public void jump(){
		this.moveUp(jump);
		if (this.jump > 0)
			this.jump -= 0.4;
		else this.jump =0;
	}
	
	public void gravity(){ 
		this.yspeed += 4;
	}
	
	public void collision(int[][] level, int nbLines, int nbColumns){	
		
		// Bottom
		if ((int)(posY + this.yspeed - 1) + 50 >= nbLines * 50){
			this.yspeed = 0;
			jumpsAvailable = 2;
		}
		else if (level[((int)(posY + this.yspeed - 1) / 50) + 1][(int)(posX) / 50] != 0 ||
				level[((int)(posY + this.yspeed - 1) / 50) + 1][((int)(posX) / 50) + 2] != 0){
			this.yspeed = 0;
			jumpsAvailable = 2;
		}
		else if (jumpsAvailable == 2) jumpsAvailable = 1;
		
		// Top
		if ((int)(posY + this.yspeed) < 0)
			this.yspeed = 0;
		else if (level[(int)(posY + this.yspeed - 1) / 50][(int)(posX) / 50] != 0 ||
				level[(int)(posY + this.yspeed - 1) / 50][((int)(posX) / 50) + 2] != 0)
			this.yspeed = 0;
		

		/// Right
		if ((int)(posX + this.xspeed) + 100 >= nbColumns * 50)
			this.xspeed = 0;
		else if (level[(int)(posY + this.yspeed) / 50][((int)(posX + this.xspeed) / 50) + 2] != 0 ||
				level[((int)(posY + this.yspeed - 1) / 50) + 1][((int)(posX + this.xspeed) / 50) + 2] != 0)
			this.xspeed = 0;
		
		// Left
		if ((int)(posX + this.xspeed) < 0)
			this.xspeed = 0;
		else if (level[(int)(posY + this.yspeed) / 50][(int)(posX + this.xspeed) / 50] != 0 ||
				level[((int)(posY + this.yspeed - 1) / 50) + 1][(int)(posX + this.xspeed) / 50] != 0)
			this.xspeed = 0;
	}
	
}
