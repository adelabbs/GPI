package process.manager;

import data.Nest;
import process.Simulation;

public abstract class NestManager {

	private Simulation simulation;

	public NestManager(Simulation simulation) {
		this.simulation = simulation;
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public abstract void update();

	public abstract void reproduce();

	public abstract Nest getNest();

	public boolean isDead() {
		return getNest().getCurrentHealth() <= 0;
	}

}