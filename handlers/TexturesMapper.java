package handlers;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class TexturesMapper {
	
	static ImageIcon player_texturesImages;
	static ImageIcon mobile_1_texturesImages;
	static ImageIcon mobile_2_texturesImages;
	static ImageIcon still_0_texturesImages;
	static ImageIcon still_1_texturesImages;
	static ImageIcon still_2_texturesImages;
	static ImageIcon still_3_texturesImages;
	static ImageIcon still_4_texturesImages;
	static ImageIcon life_bonus_texturesImages;
	static ImageIcon foe_0_texturesImages;
	static ImageIcon foe_1_texturesImages;
	static ImageIcon key_texturesImages;
	static ImageIcon door_texturesImages;
	static ImageIcon questionGuy_texturesImages;
	static String rootdir = System.getProperty("user.dir");
	static Map<ImageIcon, Integer> textureMap = new HashMap<ImageIcon, Integer>();
	
	private TexturesMapper() {}
	
	public static boolean init(){
		player_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/player/", 1).elementAt(0);
		mobile_1_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/mobile/", 1).elementAt(0);
		mobile_2_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/mobile/", 2).elementAt(1);
		still_0_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/stillPlat/", 1).elementAt(0);
		still_1_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/stillPlat/", 2).elementAt(1);
		still_2_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/stillPlat/", 3).elementAt(2);
		still_3_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/stillPlat/", 4).elementAt(3);
		still_4_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/stillPlat/", 5).elementAt(4);
		life_bonus_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/lifeBonus/", 1).elementAt(0);
		foe_0_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/foe/", 1).elementAt(0);
		foe_1_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/foe/", 2).elementAt(1);
		key_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/key/", 1).elementAt(0);
		door_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/door/", 1).elementAt(0);
		questionGuy_texturesImages = TextureLoader.getImageIconFromPath(rootdir + "/images/game/questionGuy/", 1).elementAt(0);
		
	
		textureMap.put(player_texturesImages, 1);
		textureMap.put(mobile_1_texturesImages, 2);
		textureMap.put(mobile_2_texturesImages, 3);
		textureMap.put(still_0_texturesImages, 4);
		textureMap.put(still_1_texturesImages, 5);
		textureMap.put(still_2_texturesImages, 6);
		textureMap.put(still_3_texturesImages, 7);
		textureMap.put(still_4_texturesImages, 8);
		textureMap.put(life_bonus_texturesImages, 9);
		textureMap.put(foe_0_texturesImages, 10);
		textureMap.put(foe_1_texturesImages, 11);
		textureMap.put(key_texturesImages, 12);
		textureMap.put(door_texturesImages, 13);
		textureMap.put(questionGuy_texturesImages, 14);
		
		
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
