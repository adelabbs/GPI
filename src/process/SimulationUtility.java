package process;

import data.Coordinate;
import data.TileCoordinate;
import test.manual.SimuPara;

public class SimulationUtility {

	public static void unitTime() {
		try {
			Thread.sleep(SimuPara.SIMULATION_SPEED);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean equals(TileCoordinate tilePosition, Coordinate position) {
		int convertedX = ((int) position.getAbscissa()) / SimuPara.SCALE;
		int convertedY = ((int) position.getOrdinate()) / SimuPara.SCALE;
		return convertedX == tilePosition.getAbscissa() && convertedY == tilePosition.getOrdinate();
	}
	
	public static Coordinate getRandomCoordinate() {
		return new Coordinate(Math.random()*SimuPara.SIMULATION_MAP_SIZE, Math.random()*SimuPara.SIMULATION_MAP_SIZE);
	}

}
