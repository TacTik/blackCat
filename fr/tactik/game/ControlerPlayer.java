package fr.tactik.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class ControlerPlayer implements KeyListener {
	Player player;
	HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

	
	public ControlerPlayer(Player player) {
		this.player = player;
		keys.put(KeyEvent.VK_UP, false);
		keys.put(KeyEvent.VK_RIGHT, false);
		keys.put(KeyEvent.VK_DOWN, false);
		keys.put(KeyEvent.VK_LEFT, false);
	}

	@Override
	public void keyPressed(KeyEvent e){

		keys.put(e.getKeyCode(), true);
		
		if(keys.get(KeyEvent.VK_LEFT)){
			player.moveLeft(3);
	    }
		if(keys.get(KeyEvent.VK_RIGHT)){
			player.moveRight(3);
	    }
		if(keys.get(KeyEvent.VK_UP)){
			player.moveUp(3);
	    }
		if(keys.get(KeyEvent.VK_DOWN)){
			player.moveDown(3);
	    }
	    
	}


	@Override
	public void keyReleased(KeyEvent e) {
		keys.put(e.getKeyCode(), false);
		
		if(false == keys.get(KeyEvent.VK_LEFT)){
			player.xspeed = 0;
			System.out.println("LEfT");

	    }
		if(!keys.get(KeyEvent.VK_RIGHT)){
			player.xspeed = 0;
			System.out.println("right");

	    }
		if(!keys.get(KeyEvent.VK_UP)){
			player.yspeed = 0;
			System.out.println("up");

	    }
		if(!keys.get(KeyEvent.VK_DOWN)){
			player.yspeed = 0;
			System.out.println("Down");

	    }
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}