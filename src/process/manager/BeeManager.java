package process.manager;

import data.Bee;
import data.Coordinate;
import data.Environment;
import data.Insect;
import test.manual.SimuPara;

public class BeeManager extends BugManager {

	private Bee insect;

	public BeeManager(String groupID, String agressivity, Bee insect, Environment environment) {
		super(groupID, agressivity, environment);
		this.insect = insect;
	}

	@Override
	public Insect getInsect() {
		return insect;
	}

	@Override
	public void update() {
		updateStats();
		discoverPOI();
		if (insect.getDestinationPosition() == insect.getCurrentPosition()) {
			insect.setDestinationPosition(new Coordinate(Math.random() * SimuPara.SIMULATION_MAP_SIZE,
					Math.random() * SimuPara.SIMULATION_MAP_SIZE));
		}
		super.moveInsect(insect);

	}

}