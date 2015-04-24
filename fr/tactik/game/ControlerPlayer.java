package fr.tactik.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class ControlerPlayer implements KeyListener {
	Player player;
	public ControlerPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e){

		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT){
			player.moveLeft(4);
	    }
		if(key == KeyEvent.VK_RIGHT){
			player.moveRight(4);
	    }
		if(key == KeyEvent.VK_UP){
			player.moveUp(4);
	    }
		if(key == KeyEvent.VK_DOWN){
			player.moveDown(4);
	    }
	    
	}


	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT){
			player.xspeed = 0;
	    }
		if(key == KeyEvent.VK_RIGHT){
			player.xspeed = 0;
	    }
		if(key == KeyEvent.VK_UP){
			player.yspeed = 0;
	    }
		if(key == KeyEvent.VK_DOWN){
			player.yspeed = 0;
	    }

	}

	@Override
	public void keyTyped(KeyEvent e) {}
}