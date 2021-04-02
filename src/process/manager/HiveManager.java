package process.manager;


import data.Bee;
import data.Environment;
import data.Hive;
import data.Nest;
import data.TileCoordinate;
import process.InsectFactory;
import process.Simulation;
import process.SimulationUtility;

public class HiveManager extends NestManager {

	private Hive nest;
	private int waitTime = 0;
	private NestManagerState state = NestManagerState.WANDERING;

	public HiveManager(Simulation simulation, Hive nest) {
		super(simulation);
		this.nest = nest;
	}

	@Override
	public void update() {

		switch (state) {
		case WANDERING:
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
		Environment e = Environment.getInstance();
		e.add(bee);
		Simulation simulation = getSimulation();
		if (simulation != null) {
			simulation.addNewInsect(bee);
		}
	}

	public void idle() {
		int currentInsects = getNest().getCurrentInsectsCount();
		if (currentInsects > 2) {
			setWaitTime(getNest().getReproductionTime());
			// TODO stop insects
			setState(NestManagerState.REPRODUCTION);
		}
	}

	public void enter(Bee bee) {
		nest.add(bee);
	}

	public void addInsectNest() {
		// Use getEnvironment method
		/*
		ArrayList<Insect> insects = Environment.getInstance().getInsects();
		Coordinate nestPosition = getNest().getPosition();

		double abscissa;
		double ordinate;

		for (Insect insect : insects) {
			abscissa = insect.getCurrentPosition().getAbscissa();
			ordinate = insect.getCurrentPosition().getOrdinate();

			if ((nestPosition.getAbscissa() == abscissa) && (nestPosition.getOrdinate() == ordinate)) {
				nest.add((Bee) insect);
			}
		}*/
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
