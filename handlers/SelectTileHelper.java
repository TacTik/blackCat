package handlers;

/**
 * This is the select tile helper Class.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class SelectTileHelper {

	public SelectTileHelper() {}
	private static int selected = -8;
	
	public static int getSelectedTile(){
		return selected;
	}
	
	public static void setSelectedTile(int selectedTile){
		selected = selectedTile;
	}

}
