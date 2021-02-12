package process.manager;

import data.Ant;
import data.Constants;
import data.Coordinate;
import data.Insect;
import data.NaturalResource;
import test.manual.SimuPara;

public class AntManager extends BugManager {

	private Ant insect;
	private boolean thirsty;
	private boolean hungry;
	private boolean wandering;
	private boolean waiting;
	private int waitTime = 0;

	public AntManager(String groupID, String agressivity, Ant insect) {
		super(groupID, agressivity);
		this.insect = insect;
	}

	@Override
	public void update() {
		System.out.println(waitTime);
		
		if (waiting) {
			if (waitTime > 0) {
				waitTime--;
			}
			else {
				waiting = false;
			}
		} else {
			
			if (((insect.getCurrentThirst() / insect.getMaxThirst()) <= 0.30) && !thirsty) {
				this.goDrink();
				thirsty = true;
			}

			else if (((insect.getCurrentHunger() / insect.getMaxHunger()) <= 0.30) && !hungry) {
				this.goEat();
				hungry = true;
			} else {
				wandering = true;

			}
			
			if (insect.getDestinationPosition() == insect.getCurrentPosition())

			{
				if (!thirsty && !hungry && !waiting) {
					waiting = true;
					waitTime = (int) (Math.random() * 200 );
				}
				if (wandering) {
					// test si l'insecte a trouvé un POI valide et met le wandering a false

					insect.setDestinationPosition(new Coordinate(Math.random() * SimuPara.SIMULATION_MAP_SIZE,
							Math.random() * SimuPara.SIMULATION_MAP_SIZE));
				} else if (thirsty) {
					drink();

				} else if (hungry) {
					eat();
				}

			}
			super.moveInsect(insect);

		}

		
	}

	public void eat() {
		// eat qui rempli totalement la faim de l'insecte
		int currentHunger = insect.getCurrentHunger();
		int maxHunger = insect.getMaxHunger();
		int delta = maxHunger - currentHunger;
		insect.setCurrentHunger(insect.getMaxHunger());
		// TODO décrémenter la ressource de delta

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

	}

	public void drink() {
		// eat qui rempli totalement la soif de l'insecte
		int currentThirst = insect.getCurrentThirst();
		int maxThirst = insect.getMaxThirst();
		int delta = maxThirst - currentThirst;
		insect.setCurrentThirst(insect.getMaxThirst());
		// TODO décrémenter la ressource de delta

	}

	public void drink(int quantity) {
		// eat qui rempli de quantity la soif de l'insecte
		int currentThirst = insect.getCurrentThirst();
		int maxThirst = insect.getMaxThirst();
		int calculatedThirst = currentThirst + quantity;

		if (calculatedThirst > maxThirst) {
			insect.setCurrentThirst(insect.getMaxThirst());
			quantity = calculatedThirst - maxThirst;
		} else {
			insect.setCurrentThirst(currentThirst + quantity);
		}

		// TODO décrémenter la ressource de quantity

	}

	private void goEat() {
		boolean nofood = true;
		for (NaturalResource ressource : insect.getPoi()) {
			if (ressource.getType() == Constants.FOOD) {
				nofood = false;
				insect.setDestinationPosition(ressource.getCoordinates());
			}
			if (nofood) {
				wandering = true;

			}

		}

	}

	private void goDrink() {
		boolean noWater = true;
		for (NaturalResource ressource : insect.getPoi()) {
			if (ressource.getType() == Constants.WATER) {
				noWater = false;
				insect.setDestinationPosition(ressource.getCoordinates());
			}
			if (noWater) {
				wandering = true;

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

	public boolean isWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

}