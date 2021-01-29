package prototype.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import prototype.data.Insect;
import prototype.process.Simulation;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;

	// Initial position of insect
	private float posX = 0;
	private float posY = 0;

	private Simulation simulation;

	public Dashboard(Simulation simulation) {
		this.simulation = simulation;
	}

	// Defines action when repaint is called
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Insect i : simulation.getInsects()) {
			g.drawOval((int) i.getCurrentPosition().getAbscissa(), (int) i.getCurrentPosition().getOrdinate(), 50, 50);

			if (i.getType().equals("Ant")) {
				g.setColor(Color.BLUE);
			}

			else {
				g.setColor(Color.RED);
			}
		}
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
