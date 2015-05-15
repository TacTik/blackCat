package handlers;

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
