package process.manager;

import java.util.ArrayList;

import data.Coordinate;
import data.Environment;
import data.Insect;
import data.NaturalResource;
import data.TileCoordinate;
import process.SimulationUtility;
import test.manual.SimuPara;

public abstract class BugManager {

	private String groupID = "0";
	private String agressivity = "peaceful";
	private int tileX = 0;
	private int tileY = 0;

	private NaturalResource destinationResource = null;

	private int currentTick = 0;
	private int updateInterval = SimuPara.TIME_INTERVAL;

	private Environment environment;

	public BugManager(String groupID, String agressivity, Environment environment) {
		this.groupID = groupID;
		this.agressivity = agressivity;
		this.environment = environment;
	}

	public int getUpdateInterval() {
		return updateInterval;
	}

	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
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

		double direction = calculateDirection(insect.getCurrentPosition(), nextPosition);

		insect.setDirection(direction);

		insect.setCurrentPosition(nextPosition);

		int newTileX = calculateTileX(nextPosition);
		int newTileY = calculateTileY(nextPosition);

		setTileX(newTileX);
		setTileY(newTileY);

	}

	private int calculateTileX(Coordinate nextPosition) {
		int tileX = (int) (Math.round(nextPosition.getAbscissa() + (SimuPara.INSECT_DEFAULT_SIZE / 2)) / 50);
		return tileX;
	}

	private int calculateTileY(Coordinate nextPosition) {
		int tileY = (int) (Math.round(nextPosition.getOrdinate() + (SimuPara.INSECT_DEFAULT_SIZE / 2)) / 50);
		return tileY;
	}

	public double calculateDirection(Coordinate currentPosition, Coordinate nextPosition) {

		double angle = Math.toDegrees(Math.atan2(nextPosition.getOrdinate() - currentPosition.getOrdinate(),
				nextPosition.getAbscissa() - currentPosition.getAbscissa()));
		return angle - 90;
	}

	public Coordinate nextPos(Insect insect) {

		Coordinate origin = insect.getCurrentPosition();
		Coordinate destination = insect.getDestinationPosition();

		double deltaX = destination.getAbscissa() - origin.getAbscissa();
		double deltaY = destination.getOrdinate() - origin.getOrdinate();

		double length = SimulationUtility.distance(origin, destination);

		if (insect.getSpeed() < length) {

			double newX = (origin.getAbscissa() + (insect.getSpeed() / length * deltaX));
			double newY = (origin.getOrdinate() + (insect.getSpeed() / length * deltaY));

			return new Coordinate(newX, newY);

		} else

			return destination;
	}

	/**
	 * Stats are updated every TIME_INTERVAL
	 */
	public void updateStats() {
		currentTick++;
		if (currentTick % updateInterval == 0) {
			currentTick = 1;
			updateHunger();
			updateThirst();
			updateLifeSpan();
		}
	}

	private void updateHunger() {
		Insect insect = getInsect();
		insect.decreaseCurrentHunger();
		if (insect.getCurrentHunger() <= 0) {
			insect.decreaseCurrentHealth();
		}
	}

	private void updateThirst() {
		Insect insect = getInsect();
		insect.decreaseCurrentThirst();
		if (insect.getCurrentHunger() <= 0) {
			insect.decreaseCurrentHealth();
		}
	}

	private void updateLifeSpan() {
		Insect insect = getInsect();
		insect.decreaseLifeSpan();
		if (insect.getCurrentHunger() <= 0) {
			insect.decreaseCurrentHealth();
		}
	}

	/**
	 * The na�ve point of interest discovery
	 */
	public void discoverPOI() {
		ArrayList<NaturalResource> resources = environment.getResourcesList();
		Coordinate insectPosition = getInsect().getCurrentPosition();
		int convertedX = ((int) insectPosition.getAbscissa()) / SimuPara.SCALE;

		int convertedY = ((int) insectPosition.getOrdinate()) / SimuPara.SCALE;
		// System.out.println(getInsect().getId() + " : " + convertedX + "," + " " +
		// convertedY);
		for (NaturalResource resource : resources) {
			TileCoordinate resourcePosition = resource.getCoordinates();
			if (resourcePosition.getAbscissa() == convertedX && resourcePosition.getOrdinate() == convertedY
					&& resource.getQuantity() > 0) {
				getInsect().add(resource);
			}
		}
	}

	public boolean isDead() {
		return getInsect().getCurrentHealth() <= 0;
	}

	public NaturalResource findNewResource(String resourceType) {
		NaturalResource newResource = null;
		for (NaturalResource resource : getInsect().getPoi()) {
			if (resource.getType().equals(resourceType)) {
				return resource;
			}
		}
		return newResource;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public Integer getInsectId() {
		return getInsect().getId();
	}

	public int getCurrentTick() {
		return currentTick;
	}

	public void setCurrentTick(int currentTick) {
		this.currentTick = currentTick;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public NaturalResource getDestinationResource() {
		return destinationResource;
	}

	public void setDestinationResource(NaturalResource destinationResource) {
		this.destinationResource = destinationResource;
	}

	public boolean isAtDestination() {
		Coordinate destination = getInsect().getDestinationPosition();
		Coordinate currentPosition = getInsect().getCurrentPosition();
		return currentPosition.getAbscissa() == destination.getAbscissa()
				&& currentPosition.getOrdinate() == destination.getOrdinate();
	}

}