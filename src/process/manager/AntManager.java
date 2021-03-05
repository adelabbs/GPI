package process.manager;

import data.Ant;
import data.Constants;
import data.Coordinate;
import data.Environment;
import data.Insect;
import data.NaturalResource;
import data.TileCoordinate;
import test.manual.SimuPara;

public class AntManager extends BugManager {

	private Ant insect;
	private int waitTime = 0;
	private AntManagerState state = AntManagerState.WANDERING;

	public AntManager(String groupID, String agressivity, Ant insect, Environment environment) {
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

		default:
			throw new IllegalArgumentException("Unexpected value: " + state);
		}
	}



	private void wander() {
		AntManagerState newState = AntManagerState.WANDERING;

		if (isAtDestination()) {

			waitTime = (int) Math.random() * 100 + 50;
			newState = AntManagerState.IDLE;

		}

		else {
			super.moveInsect(insect);
		}

		if (insect.isThirsty()) {
			newState = AntManagerState.THIRSTY;
		}

		else if (insect.isHungry()) {
			newState = AntManagerState.HUNGRY;
		}

		setState(newState);

	}

	private void idle() {
		AntManagerState newState = AntManagerState.IDLE;

		if (waitTime > 0) {
			waitTime--;
		}

	private void goDrink() {
		AntManagerState newState = AntManagerState.THIRSTY;
		if (canDrink()) {
			drink(10);
			// TODO 10 -> const
		} else if (waterAvailable()) {
			// TODO
		} else if (insect.getCurrentThirst() / insect.getMaxThirst() <= 0.9) {
			// TODO 0,9 -> constant
			newState = AntManagerState.WANDERING;
		}

		setState(newState);
	}
	
	private void goEat() {
		// TODO Auto-generated method stub
		
	}

	private boolean waterAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean canDrink() {
		// TODO Auto-generated method stub
		return false;
	}

	public void eat(int quantity) {
		// eat qui rempli de quantity la faim de l'insecte
		int currentHunger = insect.getCurrentHunger();
		int maxHunger = insect.getMaxHunger();
		int calculatedHunger = currentHunger + quantity;

		if (calculatedHunger > maxHunger) {
			insect.setCurrentHunger(insect.getMaxHunger());
			quantity = calculatedHunger - maxHunger;
		} else {
			insect.setCurrentHunger(currentHunger + quantity);
		}

		// TODO décrémenter la ressource de quantity
		int x = (int) ((insect.getCurrentPosition().getAbscissa()) / SimuPara.SCALE);
		int y = (int) ((insect.getCurrentPosition().getOrdinate()) / SimuPara.SCALE);

		TileCoordinate tileCoordinate = new TileCoordinate(x, y);

		for (NaturalResource resource : Environment.getInstance().getResources()) {
			if (resource.getCoordinates() == tileCoordinate) {
				resource.setQuantity(resource.getQuantity() - quantity);
			}
		}

	}

	public void drink(int quantity) {
		// eat qui rempli de quantity la soif de l'insecte
		int currentThirst = insect.getCurrentThirst();
		int maxThirst = insect.getMaxThirst();
		int calculatedThirst = currentThirst + quantity;

		if (calculatedThirst > maxThirst) {
			insect.setCurrentThirst(insect.getMaxThirst());
			quantity -= calculatedThirst - maxThirst;
		} else {
			insect.setCurrentThirst(currentThirst + quantity);
		}

		// TODO décrémenter la ressource de quantity
		int x = (int) ((insect.getCurrentPosition().getAbscissa()) / SimuPara.SCALE);
		int y = (int) ((insect.getCurrentPosition().getOrdinate()) / SimuPara.SCALE);

		for (NaturalResource resource : Environment.getInstance().getResources()) {
			TileCoordinate resourcePosition = resource.getCoordinates();
			int resourceX = resourcePosition.getAbscissa();
			int resourceY = resourcePosition.getOrdinate();
			if (resourceX == x && resourceY == y) {
				resource.setQuantity(resource.getQuantity() - quantity);
			}
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

	public boolean isAtDestination() {

		if (insect.getDestinationPosition() == insect.getCurrentPosition()) {
			return true;
		} else {
			return false;
		}
	}

}