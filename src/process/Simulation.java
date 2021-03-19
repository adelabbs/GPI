package process;

import java.util.ArrayList;
import java.util.HashMap;

import data.Constants;
import data.Coordinate;
import data.Environment;
import data.Insect;
import data.NaturalResource;
import data.TileCoordinate;

import process.manager.BugManager;
import test.manual.SimuPara;

/**
 * The Simulation processing class.
 * 
 * @author Adel
 *
 */
public class Simulation {
	// private static final boolean DEBUG = false;
	private SimulationEntry simulationEntry;
	private Environment environment;
	private SimulationState state;

	private HashMap<Integer, BugManager> bugManagersByIds = new HashMap<Integer, BugManager>();
	private ArrayList<Integer> deadInsectsIds = new ArrayList<Integer>();

	public Simulation(SimulationEntry simulationEntry) {
		this.simulationEntry = simulationEntry;
		buildSimulation();
	}

	private void buildSimulation() {
		int size = simulationEntry.getMapSize();
		Integer[][] map = new Integer[size][size];

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				map[y][x] = Integer.valueOf((int) (Math.random() * (SimuPara.TILESET_SIZE)));
			}
		}

		environment = Environment.getInstance();
		environment.setMap(map);
		createInsects(simulationEntry.getAntCount(), simulationEntry.getBeeCount(), simulationEntry.getSpiderCount());
		createResources(simulationEntry.getFlowerCount(), simulationEntry.getWaterCount(),
				simulationEntry.getFoodCount());
		setState(SimulationState.READY);
	}

	public HashMap<Integer, BugManager> getBugManagersByIds() {
		return bugManagersByIds;
	}

	private void createInsects(int antCount, int beeCount, int spiderCount) {
		ArrayList<Insect> insects = new ArrayList<Insect>();
		createInsects(Constants.ANT, antCount, insects);
		createInsects(Constants.BEE, beeCount, insects);
		createInsects(Constants.SPIDER, spiderCount, insects);
		environment.setInsects(insects);
	}

	private void createInsects(String type, int count, ArrayList<Insect> insects) {
		if (count < 0)
			throw new IllegalArgumentException("Negative resource count");

		InsectFactory factory = InsectFactory.getInstance();
		int i = 0;
		while (i < count) {
			int x = (int) (0 + Math.random() * ((SimuPara.SIMULATION_TILES - 0)));
			int y = (int) (0 + Math.random() * ((SimuPara.SIMULATION_TILES - 0)));

			Coordinate position = new Coordinate(x * SimuPara.SCALE, y * SimuPara.SCALE);
			try {
				Insect insect = factory.createInsect(type, position);
				BugManager bugManager = factory.createBugManager(type, insect); // TODO replace type with specific group
																				// number
				insects.add(insect);
				bugManagersByIds.put(insect.getId(), bugManager);
				i++;
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private void createResources(int flowerCount, int waterCount, int foodCount) throws IllegalArgumentException {
		if (waterCount + foodCount + flowerCount > SimuPara.SIMULATION_TILES * SimuPara.SIMULATION_TILES)
			throw new IllegalArgumentException("Resources count exceeds map capacity");
		try {
			createResources(Constants.FLOWER, flowerCount);
			createResources(Constants.WATER, waterCount);
			createResources(Constants.FOOD, foodCount);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	private void createResources(String type, int count) throws IllegalArgumentException {
		if (count < 0)
			throw new IllegalArgumentException("Negative resource count");
		int i = 0;
		while (i < count) {
			int x = (int) (0 + Math.random() * ((19 - 0)));
			int y = (int) (0 + Math.random() * ((19 - 0)));
			TileCoordinate position = new TileCoordinate(x, y);
			if (!environment.getResources().containsKey(position)) {
				try {
					NaturalResource resource = NaturalResourceFactory.createResource(type, position);
					environment.addResource(resource);
					i++;
				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
				}

			}

		}
	}

	public void simulate() {
		for (BugManager bugManager : bugManagersByIds.values()) {
			bugManager.update();
			if (bugManager.isDead()) {
				addDeadInsect(bugManager.getInsectId());
			}
		}
		removeAllDeadInsects();

		if (getInsects().isEmpty()) {
			setState(SimulationState.STOP);
		}
	}

	public void add(Insect insect) {
		ArrayList<Insect> insects = environment.getInsects();
		insects.add(insect);
	}

	public void remove(Insect insect) {
		ArrayList<Insect> insects = environment.getInsects();
		insects.remove(insect);
	}

	public void remove(Integer bugManagerId) {
		bugManagersByIds.remove(bugManagerId);
	}

	public void removeAllDeadInsects() {
		for (Integer id : deadInsectsIds) {
			Insect insect = bugManagersByIds.get(id).getInsect();
			environment.getInsects().remove(insect);
			remove(id);
		}
		deadInsectsIds.clear();
	}

	public void addDeadInsect(Integer id) {
		deadInsectsIds.add(id);
	}

	public ArrayList<Insect> getInsects() {
		return environment.getInsects();
	}

	public Environment getEnvironment() {
		return environment;
	}

	public Integer[][] getMap() {
		return environment.getMap();
	}

	public void setState(SimulationState state) {
		this.state = state;
	}

	public void launch() {
		state = SimulationState.RUNNING;
	}

	public boolean isRunning() {
		return state == SimulationState.RUNNING;
	}

	public boolean isReady() {
		return state == SimulationState.READY;
	}

	public boolean isOver() {
		return state == SimulationState.STOP;
	}

	public SimulationState getState() {
		return state;
	}

}