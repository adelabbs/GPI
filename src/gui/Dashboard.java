package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import data.Insect;
import data.NaturalResource;
import process.Simulation;
import test.manual.SimuPara;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final String FILE_PATH = "resources/tiles/Tile";
	public static final String FILE_EXTENSION = ".png";

	private HashMap<Integer, BufferedImage> tiles = new HashMap<Integer, BufferedImage>();
	private HashMap<String, BufferedImage> resourceTiles = new HashMap<String, BufferedImage>();
	ArrayList<NaturalResource> resources = null;

	private Simulation simulation;

	public Dashboard(Simulation simulation) {
		this.simulation = simulation;
		loadTiles();
		loadRessources();
	}

	// Defines action when repaint is called
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Use Graphics2D to have more drawing options later
		Graphics2D g2 = (Graphics2D) g;

		printMap(g2);
		printTiles(g2);
		printResources(g2);
		printInsects(g2);
		// printDebugGrid(g2);
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

	private void loadRessources(){
		resources = simulation.getEnvironment().getResources();
		BufferedImage bufferImage = null;
		String filename = null;

		for(NaturalResource nr : resources) {
			switch(nr.getType()) {
			case "flower":
				filename = FILE_PATH + 3 + FILE_EXTENSION;
				try {
					bufferImage = ImageIO.read(new File(filename));
				} catch (Exception e) {
					e.printStackTrace();
				}
				resourceTiles.put(nr.getId(), bufferImage);
				break;
			case "water":
				filename = FILE_PATH + 4 + FILE_EXTENSION;
				try {
					bufferImage = ImageIO.read(new File(filename));
				} catch (Exception e) {
					e.printStackTrace();
				}
				resourceTiles.put(nr.getId(), bufferImage);
				break;
			}
		}
		
	}

	private void loadTiles() {
		for (int i = 0; i < 5; i++) {
			String filename = FILE_PATH + i + FILE_EXTENSION;
			BufferedImage bufferImage = null;
			try {
				bufferImage = ImageIO.read(new File(filename));

			} catch (Exception e) {
				e.printStackTrace();
			}
			tiles.put(i, bufferImage);
		}
	}

	private void printTiles(Graphics2D g2) {
		BufferedImage bufferImage = null;
		Integer[][] map = simulation.getMap();

		for (int y = 0; y < SimuPara.SIMULATION_TILES; y++) {
			for (int x = 0; x < SimuPara.SIMULATION_TILES; x++) {
				bufferImage = tiles.get(map[y][x]);
				g2.drawImage(bufferImage, x * SimuPara.SCALE, y * SimuPara.SCALE, null);
			}
		}
	}
	
	private void printResources(Graphics2D g2) {
		for (NaturalResource nr : resources) {
			g2.drawImage(resourceTiles.get(nr.getId()), (int) nr.getCoordinates().getAbscissa() * SimuPara.SCALE, (int) nr.getCoordinates().getOrdinate() * SimuPara.SCALE, null);
		}
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
