package prototype.process.managers;

import prototype.data.Ant;
import prototype.data.Insect;

public class AntManager extends BugManager {

	private Ant insect;

	public AntManager(String groupID, String agressivity, Ant insect) {
		super(groupID, agressivity);
		this.insect = insect;
	}

	@Override
	public void update() {
		super.moveInsect(insect);

	}

	@Override
	public Insect getInsect() {
		return insect;
	}

}