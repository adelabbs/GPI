package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Ant;
import data.Coordinate;
import process.Simulation;
import process.SimulationEntry;
import process.SimulationState;


public class TestSimulation {

	@Test
	public void testAddInsect() {
		Coordinate coordinate = new Coordinate(1, 1);
		Ant a = new Ant(345555, coordinate, 10, 10, 10, 10);
		SimulationEntry SE = new SimulationEntry();
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
		SimulationEntry SE = new SimulationEntry();
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
		SimulationEntry SE = new SimulationEntry();
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

		SimulationEntry SE = new SimulationEntry();
		Simulation S = new Simulation(SE);
		S.simulate();
		assertEquals(S.getState() == SimulationState.RUNNING, S.isRunning());

	}

}