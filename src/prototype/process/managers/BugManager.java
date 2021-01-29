package prototype.process.managers;

import prototype.data.Coordinate;
import prototype.data.Insect;

public abstract class BugManager {

	private String type = "none";
	private String groupID = "0";
	private String agressivity = "peaceful";

	public BugManager(String type, String groupID, String agressivity) {

		this.type = type;
		this.groupID = groupID;
		this.agressivity = agressivity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getAgressivity() {
		return agressivity;
	}

	public void setAgressivity(String agressivity) {
		this.agressivity = agressivity;
	}

	public abstract void update();

	public abstract Insect getInsect();

	public void moveInsect(Insect insect) {

		Coordinate nextPosition = nextPos(insect);

		insect.setCurrentPosition(nextPosition);
	}

	public Coordinate nextPos(Insect insect) {

		Coordinate origin = insect.getCurrentPosition();
		Coordinate destination = insect.getDestinationPosition();

		double deltaX = destination.getAbscissa() - origin.getAbscissa();
		double deltaY = destination.getOrdinate() - origin.getOrdinate();

		double length = distance(origin, destination);

		if (insect.getSpeed() < length) {

			float newX = Math.round((origin.getAbscissa() + (insect.getSpeed() / length * deltaX)));
			float newY = Math.round((origin.getOrdinate() + (insect.getSpeed() / length * deltaY)));

			return new Coordinate(newX, newY);

		} else

			return destination;
	}

	public boolean isDead() {
		return getInsect().getCurrentHealth() <= 0;
	}

	public double distance(Coordinate position1, Coordinate position2) {

		return Math.sqrt((Math.pow(position1.getAbscissa() - position2.getAbscissa(), 2)
				+ (Math.pow(position1.getOrdinate() - position2.getOrdinate(), 2))));
	}
}