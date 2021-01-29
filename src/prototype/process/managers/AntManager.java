package prototype.process.managers;

import prototype.data.Ant;
import prototype.data.Coordinate;
import prototype.data.Insect;

public class AntManager extends BugManager {

	private Ant insect;

	public AntManager(String groupID, String agressivity, Ant insect) {
		super(groupID, agressivity);
		this.insect = insect;
	}

	@Override
	public void update() {
		if (insect.getDestinationPosition().getOrdinate() == insect.getCurrentPosition().getOrdinate() &&
				insect.getDestinationPosition().getAbscissa() == insect.getCurrentPosition().getAbscissa()) {
			insect.setDestinationPosition(new Coordinate(Math.random()*100, Math.random()*100));
		}
		super.moveInsect(insect);

	}

	@Override
	public Insect getInsect() {
		return insect;
	}

}