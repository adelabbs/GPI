package process.manager;

import java.util.ArrayList;

import data.Centipede;
import data.Constants;
import data.Coordinate;
import data.Environment;
import data.Insect;
import data.NaturalResource;
import data.TileCoordinate;
import process.SimulationUtility;
import test.manual.SimuPara;

public class CentipedeManager extends BugManager {
	private Centipede insect;
	private int waitTime = 0;
	private CentipedeManagerState state = CentipedeManagerState.WANDERING;
	private int range = 5;
	private int attackCoolDown = 0;

	public static final Coordinate TERRITORY_RADIUS = new Coordinate(300, 300);
	public static final Coordinate TERRITORY_ORIGIN = new Coordinate(0, 0);

	public CentipedeManager(String groupID, String agressivity, Centipede insect, Environment environment) {
		super(groupID, agressivity, environment);
		this.insect = insect;
	}

	@Override
	public void update() {
		updateStats();
		discoverPOI();
		switch (state) {
		case WANDERING:
			wander();
			break;

		case IDLE:
			idle();
			break;

		case HUNGRY:
			goEat();
			break;

		case THIRSTY:
			goDrink();
			break;

		case DEFEND:
			defend();
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + state);
		}
	}

	public Coordinate getInRangeCoordinate() {
		if (insect.isHungry() || insect.isThirsty()) {
			return SimulationUtility.getRandomCoordinate();
		}

		else if (!isHome()) {
			return TERRITORY_ORIGIN;
		}
		return new Coordinate(Math.random() * TERRITORY_RADIUS.getAbscissa(),
				Math.random() * TERRITORY_RADIUS.getOrdinate());
	}

	private void defend() {
		CentipedeManagerState newState = CentipedeManagerState.DEFEND;
		Insect openent = findInsectInTerritory();
		if (openent != null) {
			Coordinate insectCoordinate = insect.getCurrentPosition();
			Coordinate opponentInsectCoordinate = openent.getCurrentPosition();

			insect.setDestinationPosition(opponentInsectCoordinate);

			if (SimulationUtility.distance(opponentInsectCoordinate, insectCoordinate) <= 10) {
				if (attackCoolDown <= 0) {
					openent.setCurrentHealth(openent.getCurrentHealth() - SimuPara.INSECT_DAMAGE);
					attackCoolDown = SimuPara.INSECT_ATTACK_TIME_INTERVAL;
					insect.setSpeed(SimuPara.CENTIPEDE_CHASING_SPEED);

				} else {
					attackCoolDown--;
				}
			}

			newState = CentipedeManagerState.DEFEND;
			moveInsect(insect);

		} else {
			newState = CentipedeManagerState.WANDERING;
			insect.setSpeed(SimuPara.CENTIPEDE_SPEED);
		}

		setState(newState);
	}

	private Insect findInsectInTerritory() {
		Coordinate insectPosition = insect.getCurrentPosition();
		Coordinate preyPosition;
		Insect prey = null;
		int distance = SimuPara.SIMULATION_MAP_SIZE;
		ArrayList<Insect> insects = Environment.getInstance().getInsects();
		for (Insect i : insects) {
			if (i != insect) {
				preyPosition = i.getCurrentPosition();
				if (SimulationUtility.distance(TERRITORY_ORIGIN, i.getCurrentPosition()) < TERRITORY_RADIUS
						.getAbscissa()) {
					if (SimulationUtility.distance(insectPosition, preyPosition) < distance) {
						prey = i;
					}

				}
			}
		}
		return prey;
	}

	private boolean isHome() {
		return (SimulationUtility.distance(insect.getCurrentPosition(), TERRITORY_ORIGIN) < TERRITORY_RADIUS
				.getAbscissa());
	}

	private void wander() {
		CentipedeManagerState newState = CentipedeManagerState.WANDERING;

		if (insect.getDestinationPosition() == null) {
			insect.setDestinationPosition(getInRangeCoordinate());
		} else if (isAtDestination()) {
			// TODO defend territory
			waitTime = (int) Math.random() * 100 + 50;
			newState = CentipedeManagerState.IDLE;

			if (insect.isThirsty()) {
				newState = CentipedeManagerState.THIRSTY;
			}

			else if (insect.isHungry()) {
				newState = CentipedeManagerState.HUNGRY;
			} else {
				// Random movement in insect's territory
				insect.setDestinationPosition(getInRangeCoordinate());
			}
		}

		else if (findInsectInTerritory() != null && isHome()) {
			newState = CentipedeManagerState.DEFEND;
		}

		else {
			moveInsect(insect);
		}

		setState(newState);

	}

	private void idle() {
		CentipedeManagerState newState = CentipedeManagerState.IDLE;

		if (waitTime > 0) {
			waitTime--;
		}

		else {
			newState = CentipedeManagerState.WANDERING;
		}
		setState(newState);
	}

	private void goDrink() {

		CentipedeManagerState newState = CentipedeManagerState.THIRSTY;
		if (insect.getCurrentThirst()
				/ (double) insect.getMaxThirst() >= SimuPara.INSECT_DEFAULT_DRINK_UPPER_THRESHOLD) {
			newState = CentipedeManagerState.WANDERING;
			setDestinationResource(null);
			insect.setDestinationPosition(getInRangeCoordinate());

		} else if (canConsume(Constants.WATER)) {
			if (waitTime <= 0) {
				waitTime = SimuPara.CONSUMING_TIME_INTERVAL;
				drink(SimuPara.INSECT_DEFAULT_DRINK_QTT);
			} else {
				waitTime--;
			}

		} else if (getDestinationResource() == null) {
			NaturalResource newResource = findNewResource(Constants.WATER);
			if (newResource != null) {

				insect.setDestinationPosition(newResource.getCoordinates());

				setDestinationResource(newResource);
			} else {
				newState = CentipedeManagerState.WANDERING;
				insect.setDestinationPosition(getInRangeCoordinate());

			}

		} else if (!isAtDestination()) {
			moveInsect(insect);

		}

		setState(newState);
	}

	private void goEat() {

		CentipedeManagerState newState = CentipedeManagerState.HUNGRY;

		if (insect.getCurrentHunger() / (double) insect.getMaxHunger() >= SimuPara.INSECT_DEFAULT_EAT_UPPER_THRESHOLD) {
			newState = CentipedeManagerState.WANDERING;
			setDestinationResource(null);
			insect.setDestinationPosition(getInRangeCoordinate());

		} else if (canConsume(Constants.FLOWER)) {
			// TODO change to Constants.FOOD when implemented
			if (waitTime <= 0) {
				waitTime = SimuPara.CONSUMING_TIME_INTERVAL;
				eat(SimuPara.INSECT_DEFAULT_EAT_QTT);
			} else {
				waitTime--;
			}

		} else if (getDestinationResource() == null) {
			NaturalResource newResource = findNewResource(Constants.FLOWER);
			// TODO change to Constants.FOOD when implemented
			if (newResource != null) {

				insect.setDestinationPosition(newResource.getCoordinates());

				setDestinationResource(newResource);
			} else {
				newState = CentipedeManagerState.WANDERING;
				insect.setDestinationPosition(getInRangeCoordinate());

			}

		} else if (!isAtDestination()) {
			moveInsect(insect);

		}

		setState(newState);
	}

	private boolean canConsume(String type) {

		NaturalResource resource = getDestinationResource();
		if (resource != null && resource.getType().equals(type)) {

			int x = (int) ((insect.getCurrentPosition().getAbscissa()) / SimuPara.SCALE);
			int y = (int) ((insect.getCurrentPosition().getOrdinate()) / SimuPara.SCALE);

			TileCoordinate resourcePosition = resource.getCoordinates();

			if (resourcePosition.getAbscissa() == x && resourcePosition.getOrdinate() == y) {
				return true;
			}
		}
		return false;
	}

	public void eat(int quantity) {
		int ressourceQuantity = getDestinationResource().getQuantity();
		if (ressourceQuantity < quantity) {
			quantity = ressourceQuantity;
		}

		int currentHunger = insect.getCurrentHunger();
		int maxHunger = insect.getMaxHunger();
		int calculatedHunger = currentHunger + quantity;

		if (calculatedHunger > maxHunger) {
			insect.setCurrentHunger(insect.getMaxHunger());
			quantity = calculatedHunger - maxHunger;
		} else {
			insect.setCurrentHunger(currentHunger + quantity);
		}

		getDestinationResource().setQuantity(ressourceQuantity - quantity);
		if (getDestinationResource().getQuantity() <= 0) {
			insect.remove(getDestinationResource());
			setDestinationResource(null);
		}
	}

	public void drink(int quantity) {
		int ressourceQuantity = getDestinationResource().getQuantity();
		if (ressourceQuantity < quantity) {
			quantity = ressourceQuantity;
		}

		int currentThirst = insect.getCurrentThirst();
		int maxThirst = insect.getMaxHunger();
		int calculatedThirst = currentThirst + quantity;

		if (calculatedThirst > maxThirst) {
			insect.setCurrentThirst(insect.getMaxThirst());
			quantity = calculatedThirst - maxThirst;
		} else {
			insect.setCurrentThirst(currentThirst + quantity);
		}

		getDestinationResource().setQuantity(ressourceQuantity - quantity);
		if (getDestinationResource().getQuantity() <= 0) {
			insect.remove(getDestinationResource());
			setDestinationResource(null);
		}
	}

	@Override
	public Insect getInsect() {
		return insect;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public CentipedeManagerState getState() {
		return state;
	}

	public void setState(CentipedeManagerState state) {
		this.state = state;
	}

	public int getRange() {
		return range;
	}

}