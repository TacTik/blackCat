package fr.tactik.game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * The main window. Contains the whole universe.
 *
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class GameWindow extends JFrame{

	/**
	* It create a new menu for the game.
	*/
	Menu gameMenu;
	
	/**
	* It is the table which contains all our entity.
	*/
	int[] gameGrid;
	
	/**
	* It is the place where the game is launch.
	*/
	static String rootDir = System.getProperty("user.dir");
	
	/**
	* Create a new window.
	*/
	GameWindow(){
		setTitle("Un super Titre de Jeu OH YEAH");
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("images/icon.png").getImage());
		
		Menu gameMenu = new Menu();
		setJMenuBar(gameMenu);
		
		gameGrid = GameLoader.load("monFichier.bct");
		
		GameDisplay gamePanel = new GameDisplay(); 		

		getContentPane().add(gamePanel);
		
		setVisible (true);
		gamePanel.run();
	}
}
