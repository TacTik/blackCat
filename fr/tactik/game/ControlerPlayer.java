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
		
		if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_Q) && player.running == false){
			player.moveLeft(4);
	    }
		if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_Q) && player.running == true){
			player.moveLeft(8);
	    }
		if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && player.running == false){
			player.moveRight(4);
	    }
		if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && player.running == true){
			player.moveRight(8);
	    }
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_Z){
			player.moveUp(4);
		}
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
			player.moveDown(4);
		}
		if(key == KeyEvent.VK_SPACE && player.jumping > 0){
			player.jump = 5;
			player.jumping--;
	    }
		if(key == KeyEvent.VK_CAPS_LOCK){
			player.running ^= true;
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