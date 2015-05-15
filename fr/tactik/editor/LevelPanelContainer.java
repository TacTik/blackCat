package fr.tactik.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This is the level panel container Class. This class This class create a panel which contains the level displayer.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class LevelPanelContainer extends JScrollPane{
	private static final long serialVersionUID = 1L;
	private static LevelPanel levelView;
	int width, height;
		
	/**
	 * This function is the constructor of the level panel container.
	 */
	public LevelPanelContainer(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
	}

	/**
	 * This function init the view of the container .
	 */
	public int initView(int width, int height, String background) {
		levelView = new LevelPanel(width, height, background);
		if (-1 == levelView.init()){
			return -1;
		}
		
		JPanel outer = new JPanel();
		outer.add(levelView);
	
		outer.setBackground(Color.gray);
		getViewport().add(outer, BorderLayout.NORTH);
		revalidate();
		return 1;
	}

	/**
	 * This function addd the possibility to change the background.
	 */
	public void changeBg(String levelBackground) {
		levelView.setBg(levelBackground);
		
	}

}
