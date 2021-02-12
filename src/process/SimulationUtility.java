package process;

import test.manual.SimuPara;

public class SimulationUtility {
	
	/**
	 * Simulates the unit time (for an iteration) defined for the simulation. 
	 */
	public static void unitTime() {
		try {
			Thread.sleep(SimuPara.SIMULATION_SPEED);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}