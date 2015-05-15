/**
 * 
 */
package fr.tactik.game;

import java.awt.Image;
import java.util.Vector;

/**
 * This is the mobile abstract Class. This class implements the mobile entity.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public abstract class Mobile extends Entity {

	/**
	 * This is the distance in x that the mobile can move.
	 */
	float moveFieldX;
	/**
	 * This is the distance in y that the mobile can move.
	 */
	float moveFieldY;
	/**
	 * This is the initial x position of the mobile.
	 */
	float initialPosX;
	/**
	 * This is the initial y position of the mobile.
	 */
	float initialPosY;

	/**
	 * This is the speed in x and y of the mobile.
	 */
	float xspeed, yspeed;
	/**
	 * This is the speed in x of the mobile before it change.
	 */
	float xspeedBefore = 0;
	/**
	 * This is the speed in y of the mobile before it change.
	 */
	float yspeedBefore = 0;
	
	/**
	 * This function is the mobile constructor.
	 * @param posX
	 * 				this is the x position of the mobile.
	 * @param posY 
	 * 				this is the y position of the mobile.
	 * @param sizeX
	 * 				this is the x size of the mobile.
	 * @param sizeY 
	 * 				this is the y size of the mobile.
	 * @param isWalkable 
	 * 				this parameter indicate if the mobile is walkable or not.
	 * @param path 
	 * 				this the path of the mobile.
	 * @param current 
	 * 				It indicate what is the current texture if the mobile have several textures.
	 * @param id 
	 * 				THis is the id of the mobile.
	 */
	public Mobile(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, String path,
			int current, int id) {
		super(posX, posY, sizeX, sizeY, isWalkable, path, current, id);
		// TODO Auto-generated constructor stub
		initialPosX = posX;
		initialPosY = posY;
		
	}

	/**
	 * This function update the position of the mobile.
	 */
	public void update(){
		this.posX += this.xspeed;
		this.posY += this.yspeed;
		this.xspeedBefore = this.xspeed;
		this.yspeedBefore = this.yspeed;
		this.xspeed = 0;
		this.yspeed = 0;
	}
	
	/**
	 * This function move the mobile to the left.
	 */
	public void moveLeft(float x){
		this.xspeed += -x;
	}
	
	/**
	 * This function move the mobile to the right.
	 */
	public void moveRight(float x){
		this.xspeed += x;
	}
	

	/**
	 * This function move up the mobile.
	 */
	public void moveUp(float y){
		this.yspeed += -y;
	}
	
	/**
	 * This function move down the mobile.
	 */
	public void moveDown(float y){ 
		this.yspeed += y;
	}
	
	
	/**
	 * This function move the mobile in his move field.
	 * @param moveFieldX
	 * 					This is the distance in x that the mobile can move.
	 * @param moveFieldY
	 * 					This is the distance in y that the mobile can move.
	 * @param level
	 * 					THis parameter help for the collision.
	 * @param id 
	 * 					This is the id of the mobile.
	 */
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
