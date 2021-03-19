package process.manager;

import java.util.ArrayList;

import data.Constants;
import data.Coordinate;
import data.Environment;
import data.Insect;
import data.NaturalResource;
import data.Spider;
import data.TileCoordinate;
import process.SimulationUtility;
import test.manual.SimuPara;

public class SpiderManager extends BugManager {

	private Spider insect;
	private int waitTime = 0;
	private SpiderManagerState state = SpiderManagerState.WANDERING;
	private int range = 5; // 5 tiles
	public static final int SPIDER_DAMAGE = SimuPara.MAX_HEALTH / 2;

	public SpiderManager(String groupID, String agressivity, Spider spider, Environment environment) {
		super(groupID, agressivity, environment);
		this.insect = spider;
	}

	@Override
	public void update() {
		updateStats();
		discoverPOI();
		discoverPrey();

		// TODO case where the spider attack
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

		default:
			throw new IllegalArgumentException("Unexpected value: " + state);
		}
	}

	private void discoverPrey() {
		ArrayList<Insect> insects = getEnvironment().getInsects();
		Coordinate insectPosition = getInsect().getCurrentPosition();

		double rangeC = (double) range * 50;
		double distance;

		for (Insect insect : insects) {
			distance = SimulationUtility.distance(insectPosition, insect.getCurrentPosition());
			if (distance < rangeC) {

				this.insect.addPrey(insect);
			} else {
				// this function remove the insect only if he exist in
				// arrayList
				this.insect.remove(insect);
			}
		}
	}

	private Insect selectedPrey() {
		ArrayList<Insect> insects = this.insect.getPrey();
		Coordinate insectPosition = getInsect().getCurrentPosition();
		Insect prey = null;

		double dmin = 1000; // initialize the minimum
		double distance;

		for (Insect insect : insects) {

			distance = SimulationUtility.distance(insectPosition, insect.getCurrentPosition());
			if (distance < dmin) {

				dmin = distance;
				prey = insect;
			}
		}
		return prey;
	}

	private void movetoPrey(Insect prey) {
		Coordinate preyPosition = prey.getCurrentPosition();
		getInsect().setDestinationPosition(preyPosition);
	}

	public void attackPrey(Insect prey) {
		// if the insect is arrived t destination
		if (getInsect().getCurrentPosition() == getInsect().getDestinationPosition()) {
			prey.setCurrentHealth(prey.getCurrentHealth() - 1);

			// the spider eat the prey
			if (getInsect().getCurrentHunger() < getInsect().getMaxHunger()) {
				getInsect().setCurrentHunger(getInsect().getCurrentHunger() + 1);
			}
		}
	}

	private void wander() {
		SpiderManagerState newState = SpiderManagerState.WANDERING;

		if (insect.getDestinationPosition() == null) {
			insect.setDestinationPosition(SimulationUtility.getRandomCoordinate());
		} else if (isAtDestination()) {
			waitTime = (int) Math.random() * 100 + 50;
			newState = SpiderManagerState.IDLE;

			if (insect.isThirsty()) {
				newState = SpiderManagerState.THIRSTY;
			}

			else if (insect.isHungry()) {
				newState = SpiderManagerState.HUNGRY;
			} else {
				insect.setDestinationPosition(SimulationUtility.getRandomCoordinate());
			}

		}

		else {
			moveInsect(insect);
		}

		setState(newState);

	}

	private void idle() {
		SpiderManagerState newState = SpiderManagerState.IDLE;

		if (waitTime > 0) {
			waitTime--;
		}

		else {
			newState = SpiderManagerState.WANDERING;
		}
		setState(newState);
	}

	private void goDrink() {

		SpiderManagerState newState = SpiderManagerState.THIRSTY;
		if (insect.getCurrentThirst()
				/ (double) insect.getMaxThirst() >= SimuPara.INSECT_DEFAULT_DRINK_UPPER_THRESHOLD) {
			newState = SpiderManagerState.WANDERING;
			setDestinationResource(null);
			insect.setDestinationPosition(SimulationUtility.getRandomCoordinate());

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
				newState = SpiderManagerState.WANDERING;
				insect.setDestinationPosition(SimulationUtility.getRandomCoordinate());

			}

		} else if (!isAtDestination()) {
			moveInsect(insect);

		} else {
			// ?
		}

		setState(newState);
	}

	private void goEat() {
		SpiderManagerState newState = SpiderManagerState.HUNGRY;

		Insect prey = null;

		if (insect.getCurrentHunger() / (double) insect.getMaxHunger() >= SimuPara.INSECT_DEFAULT_EAT_UPPER_THRESHOLD) {
			newState = SpiderManagerState.WANDERING;
		} else {
			ArrayList<Insect> insects = Environment.getInstance().getInsects();
			// Time to hunt
			if (insect.isHungry()) {
				Coordinate pos = null;

				for (Insect i : insects) {
					double distance = SimuPara.SIMULATION_MAP_SIZE;

					if (!(i.equals(insect))) {
						if (distance > SimulationUtility.distance(insect.getCurrentPosition(),
								i.getCurrentPosition())) {
							distance = SimulationUtility.distance(insect.getCurrentPosition(), i.getCurrentPosition());
							pos = i.getCurrentPosition();
							prey = i;
						}
					}
				}
				insect.setDestinationPosition(pos);
				moveInsect(insect);
			}

		}
		if (prey != null) {
			if (SimulationUtility.distance(insect.getCurrentPosition(), prey.getCurrentPosition()) < 1) {
				eat(prey);
			}
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

	public void eat(Insect prey) {
		prey.setCurrentHealth(prey.getCurrentHealth() - SPIDER_DAMAGE);
		insect.setCurrentHunger(insect.getCurrentHunger() + SPIDER_DAMAGE);
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

	public SpiderManagerState getState() {
		return state;
	}

	public void setState(SpiderManagerState state) {
		this.state = state;
	}

	public int getRange() {
		return range;
	}

}
