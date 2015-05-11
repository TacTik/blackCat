package fr.tactik.game;

import java.io.FileNotFoundException;

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
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		setTitle("BraveCat");
		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("images/icons/icon.png").getImage());
		
		Menu gameMenu = new Menu();
		setJMenuBar(gameMenu);
	
		
		GameDisplay gamePanel = new GameDisplay(); 		

		getContentPane().add(gamePanel);
		
		setVisible (true);
		gamePanel.run();
	}
}
