package prototype.process.managers;

import prototype.data.Ant;
import prototype.data.Coordinate;
import prototype.data.Insect;

public class AntManager extends BugManager {

	private Ant insect;
	private boolean thirsty;
	private boolean hungry;

	public AntManager(String groupID, String agressivity, Ant insect) {
		super(groupID, agressivity);
		this.insect = insect;
	}

	@Override
	public void update() {

		if ((insect.getCurrentThirst() / insect.getMaxThirst()) <= 0.30) {
			this.goDrink();
			thirsty = true;
		}

		else if ((insect.getCurrentHunger() / insect.getMaxHunger()) <= 0.30) {
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

			insect.setDestinationPosition(new Coordinate(Math.random() * 100, Math.random() * 100));
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

	private void drink() {
		insect.setCurrentThirst(insect.getMaxThirst());
		// TODO décrémenter la ressource

	}

	private void goEat() {
		// TODO Auto-generated method stub

	}

	private void goDrink() {
		// TODO Auto-generated method stub

	}

	@Override
	public Insect getInsect() {
		return insect;
	}

}