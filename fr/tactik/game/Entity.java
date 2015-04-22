/**
 * 
 */
package fr.tactik.game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * This is the Entity abstract Class. All objects extends this class.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public abstract class Entity {
	/**
	 * This is the x position of the entity.
	 */
	float posX;
	/**
	 * This is the y position of the entity.
	 */
	float posY;
	/**
	 * This is the x size of the entity.
	 */
	int sizeX;
	/**
	 * This is the y size of the entity.
	 */
	int sizeY;

	/**
	 * This is the texture path of the entity. 
	 * 	 
	 */
	String path;
	Vector<Image> texturesImages;
	
	int current;

	/**
	 * Indicate if the player can walk on the entity.
	 */
	boolean isWalkable;

	/**
	 * This is the id of the entity.
	 */
	int id;
	
	/**
	 * This is the constructor of entity.
	 * @param posX
	 * 				This is the x position of the entity.
	 * @param posY
	 * 				This is the y position of the entity.
	 * @param sizeX
	 * 				This is the x size of the entity.
	 * @param sizeY
	 * 				This is the y size of the entity.
	 * @param isWalkable 
	 * 				Indicate if the player can walk of the entity.
	 * @param textures
	 * 				This is the texture of the entity. This is a vector because an entity can have several textures like the player.
	 */
	public Entity(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, String path,
			int current, int id) {
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.isWalkable = isWalkable;
		this.path = path;
		this.current = current;
		this.id = id;
		
		this.texturesImages = new Vector<Image>();
		this.setTexturesImages();
	}

	/**
	 * Get the x position of the entity.
	 * @return posX
	 * 				the x position of the entity.
	 */	
	public float getPosX() {
		return posX;
	}

	/**
	* Set the x position to the entity.
	*/	
	public void setPosX(float posX) {
		this.posX = posX;
	}

	/**
	* Get the y position of the entity.
	* @return posY
	* 				the y position of the entity.
	*/	
	public float getPosY() {
		return posY;
	}

	/**
	* Set the y position to the entity.
	*/	
	public void setPosY(float posY) {
		this.posY = posY;
	}

	/**
	* Get the x size of the entity.
	* @return sizeX
	* 				the x size of the entity.
	*/	
	public int getSizeX() {
		return sizeX;
	}

	/**
	* Set the x size to the entity.
	* @param sizeX
	* 			the new x size of the entity
	*/
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	/**
	* Get the y size of the entity.
	* @return sizeY
	* 				the y size of the entity.
	*/	
	public int getSizeY() {
		return sizeY;
	}

	/**
	* Set the y size to the entity.
	* @param sizeY
	* 			the new y size of the entity
	*/
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	/**
	* Show if an entity is walkable or not.
	* @return isWalkable
	* 			It is a boolean which shox if the entity is walkable or not.
	*/
	public boolean getIsWalkable() {
		return isWalkable;
	}
	
	/**
	* Get the textures of the entity.
	* @return textures
	* 			A vector of all the textures of the entity.
	*/
	public String getPath() {
		return path;
	}

	/**
	* Set the textures to the entity.
	* @param textures 
	* 			the new textures of the entity.
	*/
	public void setPath(String path) {
		this.path = path;
	}

	/**
	* Set if the entity is walkable or not.
	* @param isWalkable
	* 			The entity is now walkable or not.
	*/
	public void setIsWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}

	/**
	* Get the id of the entity.
	* @return id
	* 			The id of the entity.
	*/
	public int getId() {
		return id;
	}

	/**
	* Set an id to the entity.
	* @param id
	* 			The new id of the entity.
	*/
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTexturesImages(){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for (int i =0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				try {
					texturesImages.add (ImageIO.read(new File(path + listOfFiles[i].getName())));
				} catch (IOException e) {
					System.out.println("expect");
					e.printStackTrace();
				}
			}
		} 
	}
	
	public Image getCurrentTexture(){
		return texturesImages.get(current);
	}
	
	public void updateCurrentText(){
		current = texturesImages.size() -1; // FIX ME ! must be a loop in the vector  
	}
}
