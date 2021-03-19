package data;

import java.util.ArrayList;

import process.InsectVisitor;

public class Spider extends Insect {

	private ArrayList<Insect> prey = new ArrayList<Insect>();

	public Spider(Integer id, Coordinate destinationPosition, int maxHealth, int maxHunger, int maxThirst,
			int maxSpeed) {
		super(id, destinationPosition, maxHealth, maxHunger, maxThirst, maxSpeed, Constants.SPIDER);
	}

	@Override
	public <T> T accept(InsectVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public void addPrey(Insect insect) {
		if (!prey.contains(insect)) {
			prey.add(insect);
		}
	}

	public void remove(Insect insect) {
		if (!prey.contains(insect)) {
			prey.remove(insect);
		}
	}

	public ArrayList<Insect> getPrey() {
		return prey;
	}

	public void setPrey(ArrayList<Insect> prey) {
		this.prey = prey;
	}

}