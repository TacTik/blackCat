package fr.tactik.editor;

import handlers.TextureHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class LevelPanel extends JScrollPane{
	private static final long serialVersionUID = 1L;
	private static JPanel levelView;
	int width, height;
	
	Image backgroundImage;
	//TODO : afficher tiles / plateform / objects delimitations ?
	
	public LevelPanel(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
	}

	
	public int initView(int width, int height, String background) {
		backgroundImage = TextureHandler.getImageFromPath(background);
		if (null == backgroundImage)
			return -1;
		
		JPanel outer = new JPanel();
		levelView = new JPanel();
		outer.add(levelView);
		
		this.width = width;
		this.height = height;
		levelView.setPreferredSize(new Dimension((int)width, (int)height));
		levelView.setBackground(Color.white);
		outer.setBackground(Color.gray);
		
		getViewport().add(outer, BorderLayout.NORTH);
		revalidate();
		return 1;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImage,0,0,null);
	}
}
