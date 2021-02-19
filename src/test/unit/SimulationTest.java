package test.unit;

import static org.junit.Assert.*;


import org.junit.Test;

import data.Ant;
import data.Coordinate;

import process.Simulation;
import process.SimulationEntry;
import process.SimulationState;
public class SimulationTest {

	@Test
	public void testSimulate() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInsect() {
		Coordinate coordinate = new Coordinate(1, 1);
		Ant a = new Ant(345555,coordinate,10,10,10,10); 
		SimulationEntry SE = new SimulationEntry(50,0);
		Simulation S = new Simulation(SE);
		int taille = S.getInsects().size();
		S.add(a);
		assertEquals(taille+1,S.getInsects().size());
		
	}

	@Test
	public void testRemoveInsect() {
		Coordinate coordinate = new Coordinate(1, 1);
		Ant a = new Ant(345555,coordinate,10,10,10,10);
		Ant b = new Ant(345555,coordinate,10,10,10,10);
		SimulationEntry SE = new SimulationEntry(50,0);
		Simulation S = new Simulation(SE);
		
		S.add(a);
		S.add(b);
		int taille = S.getInsects().size();
		S.remove(b);
		assertEquals(taille-1,S.getInsects().size());
	}

	@Test
	public void testAddBugManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBugManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRunning() {
		
		SimulationEntry SE = new SimulationEntry(50,0);
		Simulation S = new Simulation(SE);
		S.simulate();
		assertEquals(S.getState() == SimulationState.RUNNING,S.isRunning());
		
	}

}
