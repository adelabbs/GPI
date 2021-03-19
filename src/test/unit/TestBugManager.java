package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Ant;
import data.Constants;
import data.Coordinate;
import data.Environment;
import data.NaturalResource;
import data.TileCoordinate;
import process.manager.AntManager;
import process.manager.BugManager;
import test.manual.SimuPara;

public class TestBugManager {

	@Test
	public void testMoveInsect() {
		fail("Not yet implemented");
	}

	@Test
	public void testNextPos() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDead() {
		fail("Not yet implemented");
	}

	@Test
	public void testDistance() {
		fail("Not yet implemented");
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
		NaturalResource flower = new NaturalResource(Constants.FLOWER, 1, 300, new TileCoordinate(0, 0));
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);

		Environment e = Environment.getInstance();
		e.addResource(flower);
		BugManager antManager = new AntManager("1", "peaceful", ant, e);

		antManager.discoverPOI();

		assertTrue(ant.getPoi().contains(flower));
	}


}
