/**
 * 
 */
package fr.tactik.game;

import java.awt.Image;
import java.util.Vector;

/**
 * This is the still plateform Class.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class StillPlat extends Still{

	/**
	 * This function is the still plateform constructor.
	 * @param posX
	 * 				this is the x position of the still.
	 * @param posY 
	 * 				this is the y position of the still.
	 * @param sizeX
	 * 				this is the x size of the still.
	 * @param sizeY 
	 * 				this is the y size of the still.
	 * @param isWalkable 
	 * 				this parameter indicate if the still is walkable or not.
	 * @param path 
	 * 				this the path of the still.
	 * @param current 
	 * 				It indicate what is the current texture if the still have several textures.
	 * @param id 
	 * 				THis is the id of the still.
	 */
	public StillPlat(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, String path,
		int current, int id) {
		super(posX, posY, sizeX, sizeY, isWalkable, path, current, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */

}
