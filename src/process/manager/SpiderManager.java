package process.manager;

import data.Coordinate;
import data.Environment;
import data.Insect;
import data.Spider;
import test.manual.SimuPara;

public class SpiderManager extends BugManager {

	private Spider insect;

	public SpiderManager(String groupID, String agressivity, Environment environment, Spider spider) {
		super(groupID, agressivity, environment);
		this.insect = spider;
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

	@Override
	public Insect getInsect() {
		return insect;
	}

}
