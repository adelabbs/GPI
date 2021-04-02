package process.manager;

import data.Bee;
import data.Constants;
import data.Coordinate;
import data.Environment;
import data.Hive;
import data.Insect;
import data.NaturalResource;
import data.TileCoordinate;
import process.SimulationUtility;
import test.manual.SimuPara;

public class BeeManager extends BugManager {

	private Bee insect;
	private int waitTime = 0;
	private AntManagerState state = AntManagerState.WANDERING;
	private Hive hive;

	public BeeManager(String groupID, String agressivity, Bee insect, Environment environment) {
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
		
		case NEST:
			goNest();

		default:
			throw new IllegalArgumentException("Unexpected value: " + state);
		}
		
		if (insect.getDestinationPosition() == insect.getCurrentPosition()) {
			insect.setDestinationPosition(new Coordinate(Math.random() * SimuPara.SIMULATION_MAP_SIZE,
					Math.random() * SimuPara.SIMULATION_MAP_SIZE));
		}
		super.moveInsect(insect);

	}
	
	private void wander() {
		AntManagerState newState = AntManagerState.WANDERING;

		if (insect.getDestinationPosition() == null) {
			insect.setDestinationPosition(SimulationUtility.getRandomCoordinate());
		} else if (isAtDestination()) {
			waitTime = (int) Math.random() * 100 + 50;
			newState = AntManagerState.IDLE;

			if (insect.isThirsty()) {
				newState = AntManagerState.THIRSTY;
			}

			else if (insect.isHungry()) {
				newState = AntManagerState.HUNGRY;
			}
			
			else if( !insect.isHungry() && !insect.isThirsty() ) {
				newState = AntManagerState.NEST;
			}
			
			else {
				insect.setDestinationPosition(SimulationUtility.getRandomCoordinate());
			}

		}

		else {
			moveInsect(insect);
		}

		setState(newState);

	}

	private void idle() {
		AntManagerState newState = AntManagerState.IDLE;

		if (waitTime > 0) {
			waitTime--;
		}else {
			newState = AntManagerState.WANDERING;
		}
		setState(newState);
	}

	private void goDrink() {

		AntManagerState newState = AntManagerState.THIRSTY;

		if (insect.getCurrentThirst()
				/ (double) insect.getMaxThirst() >= SimuPara.INSECT_DEFAULT_DRINK_UPPER_THRESHOLD) {
			newState = AntManagerState.WANDERING;
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
				newState = AntManagerState.WANDERING;
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

		AntManagerState newState = AntManagerState.HUNGRY;

		if (insect.getCurrentHunger() / (double) insect.getMaxHunger() >= SimuPara.INSECT_DEFAULT_EAT_UPPER_THRESHOLD) {
			newState = AntManagerState.WANDERING;
			setDestinationResource(null);
			insect.setDestinationPosition(SimulationUtility.getRandomCoordinate());

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
				newState = AntManagerState.WANDERING;
				insect.setDestinationPosition(SimulationUtility.getRandomCoordinate());

			}

		} else if (!isAtDestination()) {
			moveInsect(insect);

		} else {

		}

		setState(newState);
	}
	
	private void goNest() {
		AntManagerState newState = AntManagerState.NEST;
		
		double abscissa = insect.getCurrentPosition().getAbscissa();
		double ordinate = insect.getCurrentPosition().getOrdinate();
		
		double abscissaNest = getHive().getPosition().getAbscissa();
		double ordinateNest = getHive().getPosition().getOrdinate();

		if (insect.isHungry() || insect.isThirsty()) {
			newState = AntManagerState.THIRSTY;
		} 
		
		else if ((abscissaNest == abscissa) && (ordinateNest == ordinate)) {
			if (waitTime <= 0) {
				waitTime = SimuPara.REPRODUCTION_TIME_INTERVAL;
			} else {
				waitTime--;
			}
		}
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

	public AntManagerState getState() {
		return state;
	}

	public void setState(AntManagerState state) {
		this.state = state;
	}

	public Hive getHive() {
		return hive;
	}

	public void setHive(Hive hive) {
		this.hive = hive;
	}

}