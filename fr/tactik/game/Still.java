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
public abstract class Still extends Entity {

	/**
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 * @param isWalkable
	 * @param id
	 */
	public Still(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, String path,
			int current, int id) {
		super(posX, posY, sizeX, sizeY, isWalkable, path, current, id);
		// TODO Auto-generated constructor stub
	}
}
