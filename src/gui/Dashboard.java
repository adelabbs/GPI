package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import data.Insect;
import data.NaturalResource;
import process.Simulation;
import process.SimulationState;
import test.manual.SimuPara;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final String FILE_PATH = "resources/tiles/Tile";
	public static final String FILE_EXTENSION = ".png";

	private HashMap<Integer, BufferedImage> tiles = new HashMap<Integer, BufferedImage>();
	private HashMap<Integer, BufferedImage> resourceTiles = new HashMap<Integer, BufferedImage>();
	ArrayList<NaturalResource> resources = null;

	private Simulation simulation;
	Graphics2D g2;
	private boolean end = false;
	
	//Lateral bar
	//Insects count
	private int nbIAnts = 0;
	private int nbIBees = 0;
	private int nbISpiders = 0;
	private boolean setMax = false;
	//Resources count
	private int maxWater = 0;
	private int maxFlower = 0;

	
	private static boolean DEBUG = false;

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
		g2 = (Graphics2D) g;

		printMap(g2);
		printTiles(g2);
		printResources(g2);
		printInsects(g2);
		printLateralBar(g2);

		if (DEBUG) {
			printDebugGrid(g2);
		}
		if(end) {
			printEndMessage(g2);
		}
	}

	private void printMap(Graphics2D g2) {
	}
	
	
	private void getMaxInsectsOnField() {
		for(Insect i : simulation.getInsects()) {
			if(i.getType().equals("Ant")) {
				nbIAnts++;
			} else if(i.getType().equals("Bee")) {
				nbIBees++;
			} else if(i.getType().equals("Spider")) {
				nbISpiders++;
			}
		}
	}
	
	private void getMaxResourcesOnField() {
		for(NaturalResource re : simulation.getEnvironment().getResources()) {
			if(re.getType().equals("flower")) {
				maxFlower += re.getQuantity();
			} else {
				maxWater += re.getQuantity();
			}
		}
	}
	
	private void printLateralBar(Graphics2D g2) {
		int height = getHeight();
		
		int nbAnts = 0;
		int nbBees = 0;
		int nbSpiders = 0;
		int waterQuantity = 0;
		int flowerQuantity = 0;
		
		
		
		if(!setMax) {
			getMaxInsectsOnField();
			getMaxResourcesOnField();
			setMax = true;
		}
				
		for(Insect i : simulation.getInsects()) {
			if(i.getType().equals("Ant")) {
				nbAnts++;
			} else if(i.getType().equals("Bee")) {
				nbBees++;
			} else if(i.getType().equals("Spider")) {
				nbSpiders++;
			}
		}
			
		//Lateral Bar Border
		g2.fillRect(1000,1,1000,height);
		//Scoreboard panel
		g2.setFont(new Font("Lancer", Font.BOLD, 20));
		g2.setColor(Color.RED);
		g2.drawString("Scoreboard", 1040, 50);
		g2.drawRect(1030, 30, 130, 25);
		//Print insects number
		g2.drawString("Ant(s) : " + String.valueOf(nbAnts) + "/" + String.valueOf(nbIAnts), 1040, 100);
		g2.drawString("Bee(s) : " + String.valueOf(nbBees) + "/" + String.valueOf(nbIBees), 1040, 140);
		g2.drawString("Spider(s) : " + String.valueOf(nbSpiders) + "/" + String.valueOf(nbISpiders), 1040, 180);
		
		//Resources on field
		for(NaturalResource re : simulation.getEnvironment().getResources()) {
			if(re.getType().equals("flower")) {
				flowerQuantity += re.getQuantity();
			} else {
				waterQuantity += re.getQuantity();
			}
		}
		g2.drawString("Resources", 1040, 260);
		g2.drawRect(1030, 240, 130, 25);
		//Print resources bar count & bar
		
		waterQuantity = 300;
		
		double ratioFd = flowerQuantity / (double) maxFlower;
		int ratioF = (int) (ratioFd * 120);
		
		double ratioWd = waterQuantity / (double) maxWater;
		int ratioW = (int) (ratioWd * 120);
		
		
		
		//Food
		g2.drawString("Food : " + String.valueOf(flowerQuantity) + "/" + String.valueOf(maxFlower), 1010, 300);
		g2.drawRect(1030, 320, 120, 20);
		g2.fillRect(1030, 320, ratioF, 20);
		
		//Water
		g2.drawString("Water : " + String.valueOf(waterQuantity) + "/" + String.valueOf(maxWater), 1010, 370);
		g2.drawRect(1030, 380, 120, 20);
		g2.fillRect(1030, 380, ratioW, 20);
		System.out.println(ratioF);
		
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
	
	public void printEnd() {
		end = true;
	}

	private void loadRessources() {
		resources = simulation.getEnvironment().getResources();
		BufferedImage bufferImage = null;
		String filename = null;

		for (NaturalResource nr : resources) {
			switch (nr.getType()) {
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

	private void printEndMessage(Graphics2D g2) {
		g2.setFont(new Font("Lancer", Font.BOLD, 40));
		g2.setPaint(Color.RED);
		g2.drawString("FIN DE LA SIMULATION !",300,300);
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
			g2.drawImage(resourceTiles.get(nr.getId()), (int) nr.getCoordinates().getAbscissa() * SimuPara.SCALE,
					(int) nr.getCoordinates().getOrdinate() * SimuPara.SCALE, null);
		}
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
