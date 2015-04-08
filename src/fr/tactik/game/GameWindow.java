package fr.tactik.game;

import javax.swing.JFrame;

/**
 * 
 * @author juliette
 * The main window. Contains the whole universe.
 *
 */
public class GameWindow extends JFrame{

	//menu
	//hero
	//monde
	//controler de jeu
	//controler de menu

	GameWindow(){
	setTitle("Un super Titre de Jeu OH YEAH");
	setSize(800,800);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible (true);		
	}
}
