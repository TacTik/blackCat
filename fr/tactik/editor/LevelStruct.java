package fr.tactik.editor;

public class LevelStruct {
	
	private final int width;
	private final int height;
	private final String backgroundPath;
	
	public LevelStruct(int width, int height, String bgColor) {
		this.width = width;
		this.height = height;
		this.backgroundPath = bgColor;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
