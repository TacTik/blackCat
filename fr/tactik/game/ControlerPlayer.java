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
		if(key == KeyEvent.VK_SPACE && player.jumpsAvailable > 0){
			player.jump = 13;
			player.jumpsAvailable--;
	    }
		if(key == KeyEvent.VK_CAPS_LOCK){
			player.running ^= true;
	    }	
	}
	
	
	public void control(){
		if (left == true && player.running == false) player.moveLeft(4);
		if (left == true && player.running == true) player.moveLeft(8);
		if (right == true && player.running == false) player.moveRight(4);
		if (right == true && player.running == true) player.moveRight(8);
	}
	
	int deltaX = 1;
	
	public void playerSprite(){
		// going left
		System.out.println(left);
		System.out.println(player.jumpsAvailable);
		if (left == true && player.jumpsAvailable == 2){
			// running
			if (player.running == true) {
				player.current = 1;
				System.out.println("running left");
			}
			// walking
			else {
				player.current = 0;
				System.out.println("walking left");
			}
		}
		
		// going right
		if (right == true && player.jumpsAvailable == 2){
			// running
			if (player.running == true) {
				player.current = 2;
				//System.out.println("running right");
			}
			// walking
			else {
				player.current = 4;
				//System.out.println("walking right");
			}
		}
		
		// on the ground, not moving
		else if ((right == false && left == false) && player.jumpsAvailable == 2) {
			player.current = 6;
			//System.out.println("sitting");
		}
		// in the air
		else if (player.jumpsAvailable != 2) {
			// looking left
			if (player.xspeed < 0){
				player.current = 3;
				deltaX = -1;
			}
			// looking right
			else if (player.xspeed > 0) {
				player.current = 5;
				deltaX = 1;
			}
			//System.out.println("jumping");
			
			else if (player.xspeed == 0) {
				if (deltaX == -1) player.current = 3;
				if (deltaX == 1) player.current = 5;
			}
		}
		//player.current = 6;
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