package fr.tactik.game;

import javax.swing.JFrame;

/**
 * 
 * The main window. Contains the whole universe.
 *
 */
public class GameWindow extends JFrame{

	Menu gameMenu = new Menu();
	//hero
	//monde
	//controler de jeu
	//controler de menu

	GameWindow(){
		setTitle("Un super Titre de Jeu OH YEAH");
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Menu gameMenu = new Menu();
		setJMenuBar(gameMenu);
		
		setVisible (true);		
	}
}
