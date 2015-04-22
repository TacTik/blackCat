package fr.tactik.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class LevelPanel extends JScrollPane{
	private static final long serialVersionUID = 1L;
	private static JPanel levelView;
	//TODO : afficher tiles / plateform / objects delimitations ?
	
	public LevelPanel(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
	}

	
	public void initView() {
		JPanel outer = new JPanel(); //permet d'avoir le JPanel à la bonne taille, même si plus petit que le scrollPanel
		levelView = new JPanel();
		outer.add(levelView);
		
		//TODO think about define a level model to get width heigh etc
		levelView.setPreferredSize(new Dimension((int)800, (int)500));
		getViewport().add(outer, BorderLayout.NORTH);
		revalidate();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
