package prototype.process.managers;

import prototype.data.Bee;
import prototype.data.Coordinate;
import prototype.data.Insect;

public class BeeManager extends BugManager {

	private Bee insect;

	public BeeManager (String groupID, String agressivity, Bee insect) {
		super(groupID, agressivity);
		this.insect = insect;
	}

	@Override
	public Insect getInsect() {
		return insect;
	}

	@Override
	public void update() {
		if (insect.getDestinationPosition().equals(insect.getCurrentPosition())) {
			insect.setDestinationPosition(new Coordinate(Math.random()*100, Math.random()*100));
		}
		super.moveInsect(insect);

	}

}