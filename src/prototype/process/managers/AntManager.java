package prototype.process.managers;

import prototype.data.Ant;
import prototype.data.Constants;
import prototype.data.Coordinate;
import prototype.data.Insect;
import prototype.data.NaturalResource;
import test.SimuPara;

public class AntManager extends BugManager {

	private Ant insect;
	private boolean thirsty;
	private boolean hungry;
	private boolean wandering;

	public AntManager(String groupID, String agressivity, Ant insect) {
		super(groupID, agressivity);
		this.insect = insect;
	}

	@Override
	public void update() {

		if (((insect.getCurrentThirst() / insect.getMaxThirst()) <= 0.30) && !thirsty) {
			this.goDrink();
			thirsty = true;
		}

		else if (((insect.getCurrentHunger() / insect.getMaxHunger()) <= 0.30) && !hungry) {
			this.goEat();
			hungry = true;
		}

		if (insect.getDestinationPosition() == insect.getCurrentPosition()) {
			if (thirsty) {
				drink();

			}
			if (hungry) {
				eat();
			}
			if (wandering) {
				// test si l'insecte à trouver un POI valide

				insect.setDestinationPosition(new Coordinate(Math.random() * SimuPara.SIMULATION_MAP_SIZE,
						Math.random() * SimuPara.SIMULATION_MAP_SIZE));
			}
		}
		super.moveInsect(insect);

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

}