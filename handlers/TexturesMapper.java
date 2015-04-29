package handlers;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class TexturesMapper {
	
	static ImageIcon player_texturesImages;
	static ImageIcon still_1_texturesImages;
	static ImageIcon still_2_texturesImages;
	static ImageIcon mobile_1_texturesImages;
	static ImageIcon mobile_2_texturesImages;
	static String rootdir = System.getProperty("user.dir");
	static Map<ImageIcon, Integer> textureMap = new HashMap<ImageIcon, Integer>();
	
	private TexturesMapper() {}
	
	public static boolean init(){
		player_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/player/", 1).elementAt(0);
		still_1_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/still1/", 1).elementAt(0);
		still_2_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/still2/", 1).elementAt(0);
		mobile_1_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/mobile1/", 1).elementAt(0);
		mobile_2_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/mobile2/", 1).elementAt(0);
	
		textureMap.put(player_texturesImages, 1);
		textureMap.put(still_1_texturesImages, 2);
		textureMap.put(still_2_texturesImages, 3);
		textureMap.put(mobile_1_texturesImages, 4);
		textureMap.put(mobile_2_texturesImages, 5);
		
		return true;
	}
	
	public static ImageIcon getImageById(int id){
		return getKey(id);
	}
	
	private static ImageIcon getKey(Integer value){
		
	    for(ImageIcon key : textureMap.keySet()){
	        if(textureMap.get(key).equals(value)){
	            return key;
	        }
	    }
	    return null;
	}

}
