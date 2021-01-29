package prototype.process;

import java.util.ArrayList;

import prototype.data.Environment;
import prototype.process.managers.BugManager;

public class Simulation {
	private SimulationEntry simulationEntry;
	private Environment environment;
	private SimulationState state;

	private ArrayList<BugManager> bugManagers = new ArrayList<BugManager>();

	public Simulation(SimulationEntry simulationEntry) {
		this.simulationEntry = simulationEntry;
		buildSimulation();
	}

	private void buildSimulation() {
		environment = new Environment();
	}

	public void simulate() {
		for (BugManager bugManager : bugManagers) {
			bugManager.update();
		}
	}

	public void add(BugManager bugManager) {
		bugManagers.add(bugManager);
	}

	public ArrayList<BugManager> getExplorerManagers() {
		return bugManagers;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setState(SimulationState state) {
		this.state = state;
	}

}