package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import data.Insect;
import process.Simulation;
import test.manual.SimuPara;

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
		printTiles(g2);
		printInsects(g2);
		printDebugGrid(g2);
	}

	private void printMap(Graphics2D g2) {

	}

	private void printInsects(Graphics2D g2) {
		for (Insect insect : simulation.getInsects()) {
			PaintVisitor paintVisitor = new PaintVisitor(g2);
			insect.accept(paintVisitor);
		}
	}

	private void printDebugGrid(Graphics2D g2) {
		int width = getWidth();
		int height = getHeight();
		g2.setColor(Color.GRAY);
		for (int i = SimuPara.SCALE; i <= width; i += SimuPara.SCALE) {
			g2.drawLine(i, 1, i, height);
		}

		for (int i = SimuPara.SCALE; i <= height; i += SimuPara.SCALE) {
			g2.drawLine(1, i, width, i);
		}
	}
	
	private void printTiles(Graphics2D g2) {
		String filename = "resources/tiles/Tile0.png";
		BufferedImage bufferImage = null;
		int width = getWidth();
		int height = getHeight();
		
		try {
			bufferImage = ImageIO.read(new File(filename));
		} catch(Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i <= width; i += SimuPara.SCALE) {
			for (int j = 0; j <= height; j += SimuPara.SCALE) {
				g2.drawImage(bufferImage, i,j, null);
			}
		}
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
