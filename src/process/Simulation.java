package process;

import java.util.ArrayList;

import data.Ant;
import data.Bee;
import data.Coordinate;
import data.Environment;
import data.Insect;
import process.manager.AntManager;
import process.manager.BeeManager;
import process.manager.BugManager;

public class Simulation {
	private SimulationEntry simulationEntry;
	private Environment environment;
	private SimulationState state;

	private ArrayList<BugManager> bugManagers = new ArrayList<BugManager>();
	private ArrayList<Insect> insects = new ArrayList<Insect>();

	// private ArrayList<Integer> deadInsectsIds = new ArrayList<Integer>();

	// Using a HashMap will allow us to access specific managers quicker
	// private HashMap<Integer, BugManager> bugManagersByIds = new HashMap<Integer,
	// BugManager>();

	public Simulation(SimulationEntry simulationEntry) {
		this.simulationEntry = simulationEntry;
		buildSimulation();
	}

	private void buildSimulation() {
		int size = simulationEntry.getMapSize();
		float[][] map = new float[size][size];

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				map[y][x] = 0;
			}
		}
		environment = new Environment(map);
		int insectCount = simulationEntry.getInsectCount();
		createInsects(insectCount);
	}

	private void createInsects(int insectCount) {
		for (int i = 1; i < insectCount + 1; i++) {
			Ant ant = new Ant(i, new Coordinate(15 * i, 15 * i), i, i, i, i);
			Bee bee = new Bee(i, new Coordinate(40 * i, 60 * i), i, i, i, i);
			AntManager antManager = new AntManager("1", "peaceful", ant);
			BeeManager beeManager = new BeeManager("2", "peaceful", bee);
			insects.add(bee);
			insects.add(ant);
			bugManagers.add(beeManager);
			bugManagers.add(antManager);
		}
	}

	public void simulate() {
		for (BugManager bugManager : bugManagers) {
			bugManager.update();
		}
	}

	public void add(Insect insect) {
		insects.add(insect);
	}

	public void remove(Insect insect) {
		insects.remove(insect);
	}

	public void add(BugManager bugManager) {
		bugManagers.add(bugManager);
	}

	public void remove(BugManager bugManager) {
		bugManagers.remove(bugManager);
	}

	public void removeAllDeadInsects() {

	}

	public ArrayList<BugManager> getExplorerManagers() {
		return bugManagers;
	}

	public ArrayList<Insect> getInsects() {
		return insects;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setState(SimulationState state) {
		this.state = state;
	}

	public boolean isRunning() {
		return state == SimulationState.RUNNING;
	}

	public SimulationState getState() {
		return state;
	}
}