package process;

import java.util.ArrayList;
import java.util.HashMap;

import data.Environment;
import data.Insect;
import data.Nest;
import process.manager.BugManager;
import process.manager.NestManager;

/**
 * The Simulation processing class.
 * 
 *
 */
public class Simulation {
	// private static final boolean DEBUG = false;
	private Environment environment;
	private SimulationState state;

	private HashMap<Integer, BugManager> bugManagersByIds = new HashMap<Integer, BugManager>();
	private ArrayList<Insect> newInsects = new ArrayList<Insect>();
	private ArrayList<Integer> deadInsectsIds = new ArrayList<Integer>();

	private ArrayList<NestManager> nestManagers = new ArrayList<NestManager>();
	private ArrayList<NestManager> deadNestManagers = new ArrayList<NestManager>();

	public Simulation(SimulationEntry simulationEntry) {
		SimulationBuilder builder = new SimulationBuilder(simulationEntry, this);
		builder.buildSimulation();
	}

	public void simulate() {
		for (BugManager bugManager : bugManagersByIds.values()) {
			bugManager.update();
			if (bugManager.isDead()) {
				addDeadInsect(bugManager.getInsectId());
			}
		}

		for (NestManager nestManager : nestManagers) {
			nestManager.update();
			if (nestManager.isDead()) {
				addDeadNestManager(nestManager);
			}
		}
		removeDeadNests();
		removeAllDeadInsects();
		addAllNewInsects();
		if (getInsects().isEmpty()) {
			setState(SimulationState.STOP);
		}
	}

	public ArrayList<Insect> getInsects() {
		return environment.getInsects();
	}

	public void add(Insect insect) {
		environment.add(insect);
	}

	public void remove(Insect insect) {
		environment.remove(insect);
	}

	public HashMap<Integer, BugManager> getBugManagersByIds() {
		return bugManagersByIds;
	}

	public void setBugManagersByIds(HashMap<Integer, BugManager> bugManagersByIds) {
		this.bugManagersByIds = bugManagersByIds;
	}

	public synchronized void createAndAddBugManager(Insect insect) {
		if (insect != null) {
			InsectFactory factory = InsectFactory.getInstance();
			BugManager bugManager = factory.createBugManager(insect.getType(), insect);
			bugManagersByIds.put(insect.getId(), bugManager);
		}
	}

	public synchronized void remove(Integer bugManagerId) {
		bugManagersByIds.remove(bugManagerId);
	}

	public ArrayList<NestManager> getNestManagers() {
		return nestManagers;
	}

	public void setNestManagers(ArrayList<NestManager> nestManagers) {
		this.nestManagers = nestManagers;
	}

	public void add(NestManager manager) {
		if (manager != null) {
			nestManagers.add(manager);
		}
	}

	public void remove(NestManager manager) {
		nestManagers.remove(manager);
	}

	public ArrayList<NestManager> getDeadNestManagers() {
		return deadNestManagers;
	}

	private void removeDeadNests() {
		for (NestManager deadNestManager : deadNestManagers) {
			Nest deadNest = deadNestManager.getNest();
			environment.remove(deadNest);
			nestManagers.remove(deadNestManager);
		}
		deadNestManagers.clear();
	}

	public ArrayList<Integer> getDeadInsectsIds() {
		return deadInsectsIds;
	}

	public void addDeadNestManager(NestManager manager) {
		if (manager != null) {
			deadNestManagers.add(manager);
		}
	}

	public void addDeadInsect(Integer id) {
		deadInsectsIds.add(id);
	}

	public void removeAllDeadInsects() {
		for (Integer id : deadInsectsIds) {
			Insect insect = bugManagersByIds.get(id).getInsect();
			environment.getInsects().remove(insect);
			remove(id);
		}
		deadInsectsIds.clear();
	}

	public ArrayList<Insect> getNewInsects() {
		return newInsects;
	}

	public void addNewInsect(Insect insect) throws IllegalArgumentException {
		if (insect != null) {
			newInsects.add(insect);
		} else {
			throw new IllegalArgumentException("Insect is null");
		}
	}

	public void addAllNewInsects() {
		for (Insect insect : newInsects) {
			environment.add(insect);
			createAndAddBugManager(insect);
		}
		newInsects.clear();
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
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