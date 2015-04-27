package fr.tactik.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class ControlerPlayer implements KeyListener {
	Player player;
	public ControlerPlayer(Player player) {
		this.player = player;
	}
	
	boolean left = false;
	boolean right = false;
	
	@Override
	public void keyPressed(KeyEvent e){

		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_Q){
			left = true;
	    }
		if(key == KeyEvent.VK_D){
			right = true;
	    }
		if(key == KeyEvent.VK_SPACE && player.jumping > 0){
			player.jump = 5;
			player.jumping--;
	    }
		if(key == KeyEvent.VK_CAPS_LOCK){
			player.running ^= true;
	    }	
	}
	
int i = 0;
	public void control(){
		if (left == true && player.running == false) player.moveLeft(4);
		if (left == true && player.running == true) player.moveLeft(8);
		if (right == true && player.running == false) player.moveRight(4);
		if (right == true && player.running == true) player.moveRight(8);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_Q){
			left = false;
	    }
		if(key == KeyEvent.VK_D){
			right = false;
	    }
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}