/**
 * 
 */
package fr.tactik.game;

import java.util.Vector;

/**
 * This is the Entity abstract Class. All objects extends this class.
 *
 */
public abstract class Entity {
	float posX;
	float posY;
	int sizeX;
	int sizeY;
	Vector<String> textures;
	boolean isWalkable;
	
	int id;

	public Entity(float posX, float posY, int sizeX, int sizeY, boolean isWalkable, Vector<String> textures,
			int id) {
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.isWalkable = isWalkable;
		this.textures = textures;
		this.id = id;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public boolean getIsWalkable() {
		return isWalkable;
	}
	

	public Vector<String> getTextures() {
		return textures;
	}
	
	public void setTextures(Vector<String> textures) {
		this.textures = textures;
	}

	public void setIsWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
