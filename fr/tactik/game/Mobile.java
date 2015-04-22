/**
 * 
 */
package fr.tactik.game;

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
	public Mobile(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, Vector<String> textures,
			int id) {
		super(posX, posY, sizeX, sizeY, isWalkable, textures, id);
		// TODO Auto-generated constructor stub
	}

	
	public void moveLeft(){
		
	}
	
	public void moveRight(){
		
	}
	
	
	public void moveUp(){
		
	}
	
	public void moveDown(){
		
	}
}
