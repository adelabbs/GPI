package test.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.Ant;
import data.Coordinate;
import process.manager.AntManager;

public class AntManagerTest {

	@Before 
	public void prepareAnt() {
		//
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void shouldEatOnAResource() {
		Ant ant = new Ant(1, new Coordinate(15, 15), 10, 10, 10, 1);
		AntManager manager = new AntManager(null, null, ant);
		//
		fail("Not yet implemented");
	}

}
