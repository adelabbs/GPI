package process;

import java.util.ArrayList;
import java.util.HashMap;

import data.Ant;
import data.Bee;
import data.Coordinate;
import data.Environment;
import data.Insect;
import data.NaturalResource;
import data.Spider;
import data.TileCoordinate;
import process.manager.AntManager;
import process.manager.BeeManager;
import process.manager.BugManager;
import process.manager.SpiderManager;
import test.manual.SimuPara;

/**
 * The Simulation processing class.
 * 
 * @author Adel
 *
 */
public class Simulation {
	private static final boolean DEBUG = true;
	private SimulationEntry simulationEntry;
	private Environment environment;
	private SimulationState state;
	private Integer currentInsectId = 0;
	private Integer currentResourceId = 0;

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
		int insectCount = simulationEntry.getInsectCount();
		createInsects(insectCount);
		createResources();
		setState(SimulationState.READY);
	}

	public HashMap<Integer, BugManager> getBugManagersByIds() {
		return bugManagersByIds;
	}

	private void createInsects(int insectCount) {
		ArrayList<Insect> insects = new ArrayList<Insect>();
		for (int i = 1; i < insectCount + 1; i++) {
			Ant ant = new Ant(getNextInsectId(), new Coordinate(15 * i, 15 * i), SimuPara.MAX_HEALTH,
					SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, i);
			Bee bee = new Bee(getNextInsectId(), new Coordinate(40 * i, 60 * i), SimuPara.MAX_HEALTH,
					SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, i);
			BugManager antManager = new AntManager("1", "peaceful", ant, environment);
			BugManager beeManager = new BeeManager("2", "peaceful", bee, environment);
			insects.add(bee);
			insects.add(ant);
			bugManagersByIds.put(bee.getId(), beeManager);
			bugManagersByIds.put(ant.getId(), antManager);
		}
		Spider spider = new Spider(getNextInsectId(), new Coordinate(2 * SimuPara.SCALE, 16 * SimuPara.SCALE),
				SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);
		BugManager spiderManager = new SpiderManager("3", "agressive", environment, spider);
		insects.add(spider);
		bugManagersByIds.put(spider.getId(), spiderManager);
		environment.setInsects(insects);
	}

	private void createResources() {
		NaturalResource flower = new NaturalResource(NaturalResource.FLOWER, getNextResourceId(), 300,
				new TileCoordinate(0, 0));
		environment.addResource(flower);

		NaturalResource water = new NaturalResource(NaturalResource.WATER, getNextResourceId(), 300,
				new TileCoordinate(10, 10));
		environment.addResource(water);

		if (DEBUG) {
			createTestResources();
		}
	}

	public void createTestResources() {
		int i = 0;
		while (i < 10) {
			int x = (int) (0 + Math.random() * ((19 - 0)));
			int y = (int) (0 + Math.random() * ((19 - 0)));
			NaturalResource water = new NaturalResource(NaturalResource.WATER, getNextResourceId(), 300,
					new TileCoordinate(x, y));
			if (!environment.getResources().contains(water)) {
				environment.addResource(water);
				i++;
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

	public SimulationState getState() {
		return state;
	}

	private Integer getNextInsectId() {
		return ++currentInsectId;
	}

	private Integer getNextResourceId() {
		return ++currentResourceId;
	}
}