package prototype.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import prototype.data.Insect;
import prototype.process.Simulation;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;

	private Simulation simulation;

	public Dashboard(Simulation simulation) {
		this.simulation = simulation;
	}

	// Defines action when repaint is called
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Use Graphics2D to have more drawing options later
		Graphics2D g2 = (Graphics2D) g;

		printMap(g2);
		printInsects(g2);
	}

	private void printMap(Graphics2D g2) {

	}

	private void printInsects(Graphics2D g2) {
		for (Insect insect : simulation.getInsects()) {
			PaintVisitor paintVisitor = new PaintVisitor(g2);
			insect.accept(paintVisitor);
		}
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
