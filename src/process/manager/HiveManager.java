package process.manager;

import data.Hive;
import data.Nest;
import process.Simulation;

public class HiveManager extends NestManager {

	private Hive nest;

	public HiveManager(Simulation simulation, Hive nest) {
		super(simulation);
		this.nest = nest;
	}

	@Override
	public void update() {
		// TODO

	}

	@Override
	public void reproduce() {
		
	}

	@Override
	public Nest getNest() {
		return nest;
	}

}
