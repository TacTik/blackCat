package fr.tactik.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import handlers.TextureHandler;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LevelPanel extends JPanel  {

	private static final long serialVersionUID = 1L;
	Image backgroundImage;
	String bg;
	int width, height;
	int tileDimention = 50;
	
	public LevelPanel(int width, int height, String background){
		this.bg = background;
		this.width = width;
		this.height = height;
	}
	
	public int init(){
		backgroundImage = TextureHandler.getImageFromPath(this.bg);
		
		setPreferredSize(new Dimension((int)width * tileDimention, (int)height * tileDimention));

		if (null == backgroundImage)
			JOptionPane.showMessageDialog(null, "Background is set to white.");
			setBackground(Color.white);
		return 0;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int a = 0; a <= tileDimention; a++){
			//tracé des lignes du damier
			g.drawLine(0, a * tileDimention, width* tileDimention, a * tileDimention);
			g.drawLine(a * tileDimention, 0 , a * tileDimention, height* tileDimention);
		}
	}
}
