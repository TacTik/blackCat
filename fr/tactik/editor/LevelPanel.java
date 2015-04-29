package fr.tactik.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import handlers.SelectTileHelper;
import handlers.TextureLoader;
import handlers.TexturesMapper;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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

	}
	
	public void setBg(String background){
		this.bg = background;
		backgroundImage = TextureLoader.getImageFromPath(this.bg);
		repaint();
	}
	
	public int init(){
		backgroundImage = TextureLoader.getImageFromPath(this.bg);
		
		setPreferredSize(new Dimension((int)width * tileDimention, (int)height * tileDimention));
		this.requestFocus();
		if (null == backgroundImage)
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
				else if(tiles[i][j] != 0){
					g.drawImage(TexturesMapper.getImageById(tiles[i][j]).getImage(), i*50, j*50, 50 , 50, null);
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
	public void mouseDragged(MouseEvent e) {
		if(e.getX() < 0 || e.getY() < 0)
			return;
		
		if (SwingUtilities.isRightMouseButton(e)){
			tiles[ e.getX() / 50][e.getY() / 50] = 0;
		}
		else 
			tiles[ e.getX() / 50][e.getY() / 50] = SelectTileHelper.getSelectedTile();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX() < 0 || e.getY() < 0)
			return;
		
		if (SwingUtilities.isRightMouseButton(e)){
			tiles[ e.getX() / 50][e.getY() / 50] = 0;
		}
		else 
			tiles[ e.getX() / 50][e.getY() / 50] = SelectTileHelper.getSelectedTile();
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
