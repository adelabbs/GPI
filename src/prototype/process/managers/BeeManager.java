package prototype.process.managers;

import prototype.data.Bee;
import prototype.data.Insect;

public class BeeManager extends BugManager {

	private Bee insect;

	public BeeManager(String type, String groupID, String agressivity, Bee insect) {
		super(type, groupID, agressivity);
		this.insect = insect;
	}

	@Override
	public Insect getInsect() {
		return insect;
	}

	@Override
	public void update() {

	}

}