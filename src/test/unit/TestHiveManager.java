package test.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import data.Coordinate;
import data.Environment;
import data.Hive;
import data.Insect;
import process.InsectFactory;
import process.manager.HiveManager;
import test.manual.SimuPara;

public class TestHiveManager {
	@Test
	public void shouldCreateNewInsect() {
		// Given
		Environment e = Environment.getInstance();
		ArrayList<Insect> insects = new ArrayList<Insect>();
		InsectFactory f = InsectFactory.getInstance();
		insects.add(f.createBee(new Coordinate(0, 0)));
		insects.add(f.createBee(new Coordinate(1, 1)));
		e.setInsects(insects);

		Hive nest = new Hive(new Coordinate(0, 0), SimuPara.NEST_MAX_HEALTH, SimuPara.NEST_MAX_CAPACITY, 0);
		nest.setInsects(insects);

		HiveManager manager = new HiveManager(null, nest);
		int initialInsectCount = e.getInsects().size();

		// Then
		manager.reproduce();
		int newInsectCount = e.getInsects().size();
		assertEquals(initialInsectCount + 1, newInsectCount);

	}

}
