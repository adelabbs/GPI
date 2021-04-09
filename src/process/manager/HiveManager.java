package process.manager;

import data.Bee;
import data.Hive;
import data.Nest;
import data.TileCoordinate;
import process.InsectFactory;
import process.Simulation;
import process.SimulationUtility;
import test.manual.SimuPara;

public class HiveManager extends NestManager {

	private Hive nest;
	private int waitTime = SimuPara.HIVE_REPRODUCTION_TIME;
	private NestManagerState state = NestManagerState.IDLE;

	public HiveManager(Simulation simulation, Hive nest) {
		super(simulation);
		this.nest = nest;
	}

	@Override
	public void update() {
		switch (state) {
		case IDLE:
			idle();
			break;

		case REPRODUCTION:
			reproduce();
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + state);
		}
	}

	@Override
	public void reproduce() {
		InsectFactory factory = InsectFactory.getInstance();
		TileCoordinate position = nest.getPosition();
		Bee bee = factory.createBee(SimulationUtility.convertTileCoordinates(position));
		Simulation simulation = getSimulation();
		if (simulation != null) {
			simulation.addNewInsect(bee);
		}
		setState(NestManagerState.IDLE);
	}

	public void idle() {
		if (waitTime > 0) {
			waitTime--;
		} else {
			setState(NestManagerState.REPRODUCTION);
			waitTime = SimuPara.NEST_REPRODUCTION_TIME;
		}
	}

	public void enter(Bee bee) {
		nest.add(bee);
	}

	@Override
	public Nest getNest() {
		return nest;
	}

	public NestManagerState getState() {
		return state;
	}

	public void setState(NestManagerState state) {
		this.state = state;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

}