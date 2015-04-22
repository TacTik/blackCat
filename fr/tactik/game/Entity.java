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
 */
public abstract class Entity {
	float posX;
	float posY;
	int sizeX;
	int sizeY;
	
	boolean isWalkable;
	
	String path;
	Vector<Image> texturesImages;
	
	
	int current;
	int id;

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
	

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
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