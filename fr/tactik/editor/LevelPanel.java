package fr.tactik.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import handlers.TextureLoader;
import handlers.TexturesMapper;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LevelPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	Image backgroundImage;
	String bg;
	int width, height;
	int tileDimention = 50;
	int[][] tiles;
	
	public LevelPanel(int width, int height, String background){
		this.bg = background;
		this.width = width;
		this.height = height;
		tiles = new int[width][height];
		
		addMouseListener(this);
		addMouseMotionListener(this);
		this.requestFocus();
		
		//boolean t = TexturesMapper.init();

	}
	
	public int init(){
		backgroundImage = TextureLoader.getImageFromPath(this.bg);
		
		setPreferredSize(new Dimension((int)width * tileDimention, (int)height * tileDimention));
		this.requestFocus();
		if (null == backgroundImage)
			JOptionPane.showMessageDialog(null, "Background is set to white.");
			setBackground(Color.white);
		return 0;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0,0,null);
		
		g.setColor(new Color(51,51,51,40));
		
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				if(tiles[i][j] == -8){
					g.fillRect(i * 50, j * 50, 50, 50);
				}
				if(tiles[i][j] == 2){
					//g.setColor(new Color(2,51,5,40));
					//g.fillRect(i * 50, j * 50, 50, 50);
					g.drawImage(TexturesMapper.getImageById(2).getImage(), i*50, j*50, 50 , 50, null);
				}
			}
		}

		
		g.setColor(Color.black);
		for(int a = 0; a <= tileDimention; a++){
			g.drawLine(0, a * tileDimention, width* tileDimention, a * tileDimention);
			g.drawLine(a * tileDimention, 0 , a * tileDimention, height* tileDimention);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		tiles[ e.getX() / 50][e.getY() / 50] = 2; // TODO place an id and display image and go make a coffe.
		repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				if(tiles[i][j] == -8 && (i !=  e.getX() && j != e.getY() )){
					tiles[i][j] = 0;
				}
			}
		}
		
		if(tiles[ e.getX() / 50][e.getY() / 50] == 0)
			tiles[ e.getX() / 50][e.getY() / 50] = -8;
		repaint();
	}
}
