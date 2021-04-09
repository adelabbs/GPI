package process.manager;

import data.Ant;
import data.Anthill;
import data.Nest;
import data.TileCoordinate;
import process.InsectFactory;
import process.Simulation;
import process.SimulationUtility;
import test.manual.SimuPara;

public class AntHillManager extends NestManager {

	private Anthill nest;
	private int waitTime = SimuPara.ANTHILL_REPRODUCTION_TIME;

	public AntHillManager(Simulation simulation, Anthill nest) {
		super(simulation);
		this.nest = nest;
	}

	@Override
	public void update() {
		if (waitTime > 0) {
			waitTime--;
		} else {
			reproduce();
			waitTime = SimuPara.NEST_REPRODUCTION_TIME;
		}
	}

	@Override
	public void reproduce() {
		InsectFactory factory = InsectFactory.getInstance();
		TileCoordinate position = nest.getPosition();
		Ant ant = factory.createAnt(SimulationUtility.convertTileCoordinates(position));
		Simulation simulation = getSimulation();
		if (simulation != null) {
			simulation.addNewInsect(ant);
		}
	}

	@Override
	public Nest getNest() {
		return nest;
	}

}