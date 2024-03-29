package process;

import java.util.ArrayList;

import data.Anthill;
import data.Constants;
import data.Coordinate;
import data.Environment;
import data.Hive;
import data.Insect;
import data.NaturalResource;
import data.TileCoordinate;
import process.manager.AntHillManager;
import process.manager.HiveManager;
import test.manual.SimuPara;

public class SimulationBuilder {
	private SimulationEntry simulationEntry;
	private Simulation simulation;
	private Environment environment = Environment.getInstance();

	public SimulationBuilder(SimulationEntry simulationEntry, Simulation simulation) {
		this.simulationEntry = simulationEntry;
		this.simulation = simulation;
	}

	public void buildSimulation() {
		simulation.setEnvironment(environment);
		buildMap();

		createInsects(simulationEntry.getAntCount(), simulationEntry.getBeeCount(), simulationEntry.getSpiderCount(),
				simulationEntry.getCentipedeCount());

		createResources(simulationEntry.getFlowerCount(), simulationEntry.getWaterCount(),
				simulationEntry.getFoodCount());

		createNests();

		simulation.setState(SimulationState.READY);
	}

	private void buildMap() {
		int size = simulationEntry.getMapSize();
		Integer[][] map = new Integer[size][size];

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				map[y][x] = Integer.valueOf((int) (Math.random() * (SimuPara.TILESET_SIZE)));
			}
		}

		environment.setMap(map);
	}

	private void createInsects(int antCount, int beeCount, int spiderCount, int centipedeCount) {
		ArrayList<Insect> insects = new ArrayList<Insect>();
		try {
			createInsects(Constants.ANT, antCount, insects);
			createInsects(Constants.BEE, beeCount, insects);
			createInsects(Constants.SPIDER, spiderCount, insects);
			createInsects(Constants.CENTIPEDE, centipedeCount, insects);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		environment.setInsects(insects);
	}

	private void createInsects(String type, int count, ArrayList<Insect> insects) throws IllegalArgumentException {
		if (count < 0)
			throw new IllegalArgumentException("Negative resource count");

		InsectFactory factory = InsectFactory.getInstance();
		int i = 0;
		int size = simulationEntry.getMapSize();
		while (i < count) {
			int x = (int) (0 + Math.random() * ((size - 1)));
			int y = (int) (0 + Math.random() * ((size - 1)));

			Coordinate position = new Coordinate(x * SimuPara.SCALE, y * SimuPara.SCALE);
			try {
				Insect insect = factory.createInsect(type, position);
				insects.add(insect);
				simulation.createAndAddBugManager(insect);
				i++;
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private void createResources(int flowerCount, int waterCount, int foodCount) throws IllegalArgumentException {
		int size = simulationEntry.getMapSize();
		if (waterCount + foodCount + flowerCount > size * size)
			throw new IllegalArgumentException("Resources count exceeds map capacity");
		try {
			createResources(Constants.FLOWER, flowerCount);
			createResources(Constants.WATER, waterCount);
			createResources(Constants.FOOD, foodCount);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	private void createResources(String type, int count) throws IllegalArgumentException {
		if (count < 0)
			throw new IllegalArgumentException("Negative resource count");
		int i = 0;
		while (i < count) {
			int x = (int) (0 + Math.random() * ((19 - 0)));
			int y = (int) (0 + Math.random() * ((19 - 0)));
			TileCoordinate position = new TileCoordinate(x, y);
			if (!environment.getResources().containsKey(position)) {
				try {
					NaturalResource resource = NaturalResourceFactory.createResource(type, position);
					environment.addResource(resource);
					i++;
				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	private void createNests() {
		boolean createdHive = false;
		boolean createdAntHill = false;
		while (!((createdHive) && (createdAntHill))) {
			if(!createdHive) {
				createdHive = createHive();
			}
			if(!createdAntHill) {
				createdAntHill = createAnthill();
			}
		}
	}
	
	private boolean createHive() {
		boolean created = false;
		TileCoordinate hivePosition = generateRandomPosition();
		if (!environment.getResources().containsKey(hivePosition)) {
			Hive hive = NestFactory.createHive(hivePosition);
			environment.add(hive);
			simulation.add(new HiveManager(simulation, hive));
			created = true;
		}
		return created;
	}
	
	private boolean createAnthill() {
		boolean created = false;
		TileCoordinate antHillPosition = generateRandomPosition();
		if (!environment.getResources().containsKey(antHillPosition)) {
			Anthill antHill = NestFactory.createAnthill(antHillPosition);
			environment.add(antHill);
			simulation.add(new AntHillManager(simulation, antHill));
			created = true;
		}
		return created;
	}

	private TileCoordinate generateRandomPosition() {
		int size = simulationEntry.getMapSize();
		int x = (int) (0 + Math.random() * ((size - 1)));
		int y = (int) (0 + Math.random() * ((size - 1)));
		return new TileCoordinate(x, y);
	}

	public SimulationEntry getSimulationEntry() {
		return simulationEntry;
	}

	public Simulation getSimulation() {
		return simulation;
	}
}