package process;

import data.Anthill;
import data.Constants;
import data.Hive;
import data.Nest;
import data.TileCoordinate;
import test.manual.SimuPara;

public class NestFactory {
	public static Hive createHive(TileCoordinate position) {
		return new Hive(position, SimuPara.NEST_MAX_HEALTH, SimuPara.NEST_MAX_CAPACITY,
				SimuPara.NEST_REPRODUCTION_TIME);
	}

	public static Anthill createAnthill(TileCoordinate position) {
		return new Anthill(position, SimuPara.NEST_MAX_HEALTH, SimuPara.NEST_MAX_CAPACITY,
				SimuPara.NEST_REPRODUCTION_TIME);
	}

	public static Nest createNest(String type, TileCoordinate position) throws IllegalArgumentException {
		switch (type) {
		case Constants.ANTHILL:
			return createAnthill(position);
		case Constants.HIVE:
			return createHive(position);
		default:
			throw new IllegalArgumentException("Unknown nest type : " + type);
		}
	}
}