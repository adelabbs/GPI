package prototype.process.managers;

import prototype.data.Ant;
import prototype.data.Insect;

public class AntManager extends BugManager {

	private Ant insect;

	public AntManager(String type, String groupID, String agressivity, Ant insect) {
		super(type, groupID, agressivity);
		this.insect = insect;
	}

	@Override
	public void update() {

	}

	@Override
	public Insect getInsect() {
		return insect;
	}

}