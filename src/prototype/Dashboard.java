package prototype;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Dashboard extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	//Initial position of insect
	private int posX = 0;
	private int posY = 0;
	
	// Defines action when repaint is called
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawOval(5,5,posX, posY);
		g.setColor(Color.RED);
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	

}
