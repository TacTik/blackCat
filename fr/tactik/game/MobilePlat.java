/**
 * 
 */
package fr.tactik.game;

import java.awt.Image;
import java.util.Vector;

/**
 * This is the mobile plateform Class.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class MobilePlat extends Mobile{

	/**
	 * This function is the mobile plateform constructor.
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
	public MobilePlat(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, String path,
		int current, int id) {
		super(posX, posY, sizeX, sizeY, isWalkable, path, current, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */

}
