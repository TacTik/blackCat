package fr.tactik.editor;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;


public class TilesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		setPreferredSize(new Dimension (200, 800)); // fixed !
	}
}
