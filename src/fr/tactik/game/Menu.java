package fr.tactik.game;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * 
 * The main menu. It allows the user to start a new game, load or save a game and quit the game.
 *
 */
public class Menu extends JMenuBar {
	
	GameWindow mainWindow;
	
	Menu(){
		
		// game
		JMenu game = new JMenu ("Game");
		game.setMnemonic(KeyEvent.VK_J);
		add(game);	
		
			// new
		JMenuItem newGame= new JMenuItem ("new Game");
		game.add(newGame);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		newGame.setMnemonic(KeyEvent.VK_N);
		newGame.setActionCommand("New game");
		
		game.addSeparator();
		
			// save
		JMenuItem save = new JMenuItem ("Save");
		game.add(save);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		save.setMnemonic(KeyEvent.VK_S);
		game.addSeparator();
		
			// load
		JMenuItem load = new JMenuItem ("Load");
		game.add(load);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		load.setMnemonic(KeyEvent.VK_C);
		game.addSeparator();
		
			// Quit
		JMenuItem quit = new JMenuItem ("Quit :'(");
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		quit.setMnemonic(KeyEvent.VK_Q);
		quit.setActionCommand("Quitter :'(");
		game.add(quit);
		
		// Editor <-- dunno if this is the right place for it. Anyway.
		JMenu editor = new JMenu ("Editor");
		editor.setMnemonic(KeyEvent.VK_J);
		add(editor);
		
			// about
		JMenu about = new JMenu ("About");
		add(about);

	}
}