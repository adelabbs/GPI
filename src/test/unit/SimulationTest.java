package test.unit;

import static org.junit.Assert.*;


import org.junit.Test;

import data.Ant;
import data.Coordinate;
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
		assertEquals(SimulationState.RUNNING,S.isRunning());
		
	}
	
	@Test
	public void testUpdateHunger() {
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);
		BugManager antManager = new AntManager("1", "peaceful", ant);
		
		int CurrentHunger = ant.getCurrentHunger();
		
		for(int i=0; i<51;i++) {
			antManager.updateStats();
		}
		
		assertEquals(CurrentHunger-1, ant.getCurrentHealth());
		
	}
	
	@Test
	public void testUpdateThirst() {
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);
		BugManager antManager = new AntManager("1", "peaceful", ant);
		
		ant.setCurrentThirst(0);;
		int CurrentHealth = ant.getCurrentHealth();
		for(int i=0; i<51;i++) {
			antManager.updateStats();
		}
		
		assertEquals(CurrentHealth-1, ant.getCurrentHealth());
		
	}
	
	@Test
	public void testUpdateLifeSpan() {
		Ant ant = new Ant(1, new Coordinate(15, 15), SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST, 1);
		BugManager antManager = new AntManager("1", "peaceful", ant);
		
		int LifeSpan = ant.getLifeSpan();
		for(int i=0; i<51;i++) {
			antManager.updateStats();
		}
		
		assertEquals(LifeSpan-1, ant.getLifeSpan());
		
	}

}