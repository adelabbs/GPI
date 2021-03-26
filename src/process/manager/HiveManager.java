package process.manager;

import data.Hive;
import data.Nest;

public class HiveManager implements NestManager {

	private Hive nest;

	public HiveManager(Hive nest) {
		this.nest = nest;
	}

	@Override
	public void update() {
		// TODO

	}

	@Override
	public void reproduce() {
		// TODO
	}

	@Override
	public Nest getNest() {
		return nest;
	}

}
