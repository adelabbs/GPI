package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Ant;
import data.Coordinate;
import data.Environment;
import data.NaturalResource;
import data.TileCoordinate;
import gui.InsectGUI;
import process.Simulation;
import process.SimulationEntry;
import process.SimulationState;
import process.manager.AntManager;
import process.manager.BugManager;
import test.manual.SimuPara;

public class SimulationTest {

	@Test
	public void testSimulate() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInsect() {
		Coordinate coordinate = new Coordinate(1, 1);
		Ant a = new Ant(345555, coordinate, 10, 10, 10, 10);
		SimulationEntry SE = new SimulationEntry(50, 0);
		Simulation S = new Simulation(SE);
		int taille = S.getInsects().size();
		S.add(a);
		assertEquals(taille + 1, S.getInsects().size());

	}

	@Test
	public void testRemoveInsect() {
		Coordinate coordinate = new Coordinate(1, 1);
		Ant a = new Ant(345555, coordinate, 10, 10, 10, 10);
		Ant b = new Ant(345555, coordinate, 10, 10, 10, 10);
		SimulationEntry SE = new SimulationEntry(50, 0);
		Simulation S = new Simulation(SE);

		S.add(a);
		S.add(b);
		int taille = S.getInsects().size();
		S.remove(b);
		assertEquals(taille - 1, S.getInsects().size());
	}

	@Test
	public void testRemoveBugManager() {
		Coordinate coordinate = new Coordinate(1, 1);
		Ant a = new Ant(345555, coordinate, 10, 10, 10, 10);
		Ant b = new Ant(345555, coordinate, 10, 10, 10, 10);
		SimulationEntry SE = new SimulationEntry(50, 0);
		Simulation S = new Simulation(SE);
		S.add(a);
		S.add(b);
		S.simulate();
		int id = a.getId();
		S.remove(id);
		assertEquals(false, S.getBugManagersByIds().containsKey(id));
	}

	@Test
	public void testIsRunning() {

		SimulationEntry SE = new SimulationEntry(50, 0);
		Simulation S = new Simulation(SE);
		S.simulate();
		assertEquals(S.getState() == SimulationState.RUNNING, S.isRunning());

	}

	@Test
	public void testUpdateHunger() {
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);
		BugManager antManager = new AntManager("1", "peaceful", ant, null);

		int CurrentHunger = ant.getCurrentHunger();
		antManager.setUpdateInterval(1);
		antManager.updateStats();
		assertEquals(CurrentHunger - 1, ant.getCurrentHunger());

	}

	@Test
	public void testUpdateThirst() {
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);
		BugManager antManager = new AntManager("1", "peaceful", ant, null);

		int CurrentThirst = ant.getCurrentThirst();
		antManager.setUpdateInterval(1);
		antManager.updateStats();
		assertEquals(CurrentThirst - 1, ant.getCurrentThirst());

	}

	@Test
	public void testUpdateLifeSpan() {
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);
		BugManager antManager = new AntManager("1", "peaceful", ant, null);

		int LifeSpan = ant.getLifeSpan();
		antManager.setUpdateInterval(1);
		antManager.updateStats();
		assertEquals(LifeSpan - 1, ant.getLifeSpan());

	}

	@Test
	public void testDiscoverPOI() {
		NaturalResource flower = new NaturalResource(NaturalResource.FLOWER, 1, 300, new TileCoordinate(0, 0));
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);

		Environment e = Environment.getInstance();
		e.addResource(flower);
		BugManager antManager = new AntManager("1", "peaceful", ant, e);

		antManager.discoverPOI();

		assertTrue(ant.getPoi().contains(flower));
	}

	@Test
	public void testGoEat() {
		NaturalResource water = new NaturalResource(NaturalResource.WATER, 1, 300, new TileCoordinate(0, 0));
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);
		BugManager antManager = new AntManager("1", "peaceful", ant, Environment.getInstance());

		ant.add(water);
		ant.setCurrentThirst(1);
		antManager.update();

		Coordinate coordinate = ant.getDestinationPosition();
		int convertedX = ((int) coordinate.getAbscissa()) / SimuPara.SCALE;
		int convertedY = ((int) coordinate.getOrdinate()) / SimuPara.SCALE;

		assertEquals(0, convertedY);
		assertEquals(0, convertedX);

	}

	@Test
	public void testEat() {
		
	}

}