package prototype.process;

import java.util.ArrayList;

import prototype.data.Environment;
import prototype.data.Insect;
import prototype.process.managers.BugManager;

public class Simulation {
	private SimulationEntry simulationEntry;
	private Environment environment;
	private SimulationState state;

	private ArrayList<BugManager> bugManagers = new ArrayList<BugManager>();
	private ArrayList<Insect> insects = new ArrayList<Insect>();

	public Simulation(SimulationEntry simulationEntry) {
		this.simulationEntry = simulationEntry;
		buildSimulation();
	}

	private void buildSimulation() {
		int size = simulationEntry.getSize();
		float[][] map = new float[size][size];
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				map[y][x] = 0;
			}
		}
		environment = new Environment(map);
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