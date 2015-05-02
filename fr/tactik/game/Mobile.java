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
public abstract class Mobile extends Entity {

	/**
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 * @param isWalkable
	 * @param id
	 */

	float moveFieldX;
	float moveFieldY;
	float initialPosX;
	float initialPosY;

	float xspeed, yspeed;
	float xspeedBefore = 0;
	float yspeedBefore = 0;
	
	public Mobile(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, String path,
			int current, int id) {
		super(posX, posY, sizeX, sizeY, isWalkable, path, current, id);
		// TODO Auto-generated constructor stub
		initialPosX = posX;
		initialPosY = posY;
		
	}

	public void update(){
		this.posX += this.xspeed;
		this.posY += this.yspeed;
		this.xspeedBefore = this.xspeed;
		this.yspeedBefore = this.yspeed;
		this.xspeed = 0;
		this.yspeed = 0;
	}
	
	public void moveLeft(float x){
		this.xspeed += -x;
	}
	
	public void moveRight(float x){
		this.xspeed += x;
	}
	
	
	public void moveUp(float y){
		this.yspeed += -y;
	}
	
	public void moveDown(float y){ 
		this.yspeed += y;
	}
	
	
	
	public void moveInField(float moveFieldX, float moveFieldY, int[][] level, int id){
		//For moveFieldX
		if((posX < moveFieldX + initialPosX ) && (xspeedBefore >= 0)){
			this.moveRight(1);
		}
		else if(posX >= moveFieldX + initialPosX){
			this.moveLeft(1);
		}
		else if (posX <= initialPosX){
			this.moveRight(1);
		}
		else if((posX < moveFieldX + initialPosX ) && (xspeedBefore < 0)){
			this.moveLeft(1);
		}
		
		//For moveFieldY
		if((posY < moveFieldY + initialPosY ) && (yspeedBefore >= 0)){
			this.moveDown(1);
		}
		else if(posY >= moveFieldY + initialPosY){
			this.moveUp(1);
		}
		else if (posY <= initialPosY){
			this.moveDown(1);
		}
		else if((posY < moveFieldY + initialPosY ) && (yspeedBefore < 0)){
			this.moveUp(1);
		}

		/* test
		int x = (int)((posX + 25 - initialPosX)/50);
		int xMax = (int)(moveFieldX / 50);
		int y = (int)((posY +25 - initialPosY)/50);
		int yMax = (int)(moveFieldY / 50);
		System.out.println("x : " + x);
		System.out.println("xMax : " + xMax);
		System.out.println("y : " + y);
		System.out.println("yMax : " + yMax);
		for (int i = 0; i <= yMax; i++){
			for (int j = 0; j <= xMax; j++){
				if (i == y && j == x)
					level[(int)(initialPosY/50)+i][(int)(initialPosX/50)+j] = id;
				else
					level[(int)(initialPosY/50)+i][(int)(initialPosX/50)+j] = 0;
			}
		}
		*/
		
	}
}
