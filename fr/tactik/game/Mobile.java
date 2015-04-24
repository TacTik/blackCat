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
	float xspeed, yspeed;
	
	public Mobile(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, String path,
			int current, int id) {
		super(posX, posY, sizeX, sizeY, isWalkable, path, current, id);
		// TODO Auto-generated constructor stub
	}

	public void update(){
		this.posX += this.xspeed;
		this.posY += this.yspeed;

	}
	
	public void moveLeft(float x){
		this.xspeed = -x;
	}
	
	public void moveRight(float x){
		this.xspeed = x;
	}
	
	
	public void moveUp(float y){
		this.yspeed = -y;
	}
	
	public void moveDown(float y){
		this.yspeed = y;
	}
}
