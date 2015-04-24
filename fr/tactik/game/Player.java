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
	
	int lifePoints;
	float speed;
	int state;
	Vector<String> inventory;

	public void crouch(){
		
	}
	
	public void run(){
		
	}
}
