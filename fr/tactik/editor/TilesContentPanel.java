package fr.tactik.editor;

import java.awt.LayoutManager;
import java.awt.ScrollPane;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This is the tiles content panel Class. This class .
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class TilesContentPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private TilesPanel tileSetView;
	private ScrollPane scrollTileSetPanel;
	
	/**
	 * This is the constructor of the tiles content panel.
	 */
	public TilesContentPanel(int vsbPolicy, int hsbPolicy) {
		super();
		LayoutManager layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);
		scrollTileSetPanel = new ScrollPane(vsbPolicy, hsbPolicy);
		add(scrollTileSetPanel);
	}
	
	/**
	 * This inner class create a scroll panel.
	 */	
	public class ScrollPane extends JScrollPane{
				
				private static final long serialVersionUID = 1L;
				
				public ScrollPane(int vsbPolicy, int hsbPolicy) {
					super(vsbPolicy, hsbPolicy);
					JPanel outer = new JPanel();
					tileSetView = new TilesPanel();
					outer.add(tileSetView);
					getViewport().add(tileSetView);
				}
	
	
	}
}