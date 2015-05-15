package fr.tactik.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import fr.tactik.editor.EditorWindow;

/**
 * 
 * The main menu. It allows the user to start a new game, load or save a game and quit the game.
 *
 */
public class Menu extends JMenuBar {
	
	GameWindow mainWindow;
	GameDisplay gamePanel;
	
	Menu(GameDisplay gamePanel){
		
		this.gamePanel = gamePanel;
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
		JMenuItem load = new JMenuItem ("Load a level");
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
		
		// Editor 
		JMenu editor = new JMenu ("Editor");
		editor.setMnemonic(KeyEvent.VK_J);
		add(editor);

		
		load.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		          initGame(false);
		    }
		});
		

		
		editor.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent arg0) {
				EditorWindow.createWindow(1000,700);
			}

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
			// about
		JMenu about = new JMenu ("About");
		add(about);

	}
	
	public void initGame(boolean isFirst){
		gamePanel.initGame(isFirst);
	}
}