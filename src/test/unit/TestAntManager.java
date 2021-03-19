package test.unit;

import static org.junit.Assert.*;

import org.junit.Before;
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

public class TestAntManager {

	@Before
	public void prepareAnt() {
		//
	}
	
	@Test
	public void testGoEat() {
		NaturalResource water = new NaturalResource(Constants.WATER, 1, 300, new TileCoordinate(0, 0));
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
	public void testConsume() {
		NaturalResource flower = new NaturalResource(Constants.FLOWER, 1, 300, new TileCoordinate(0, 0));
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER-1, SimuPara.MAX_THIRST, 1);

		Environment e = Environment.getInstance();
		e.addResource(flower);
		AntManager antManager = new AntManager("1", "peaceful", ant, e);
		
		ant.setCurrentHunger(10);
		ant.add(flower);
		int currentThirst=ant.getCurrentHunger();
		int currentResource= flower.getQuantity();
		
		antManager.setDestinationResource(flower);
		antManager.eat(SimuPara.INSECT_DEFAULT_EAT_QTT);
		
		
		assertEquals(currentThirst+SimuPara.INSECT_DEFAULT_EAT_QTT,ant.getCurrentHunger());
		assertEquals(currentResource-SimuPara.INSECT_DEFAULT_EAT_QTT,flower.getQuantity());
	}

}
