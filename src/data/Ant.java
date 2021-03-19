package data;

import process.InsectVisitor;

public class Ant extends Insect {

	public Ant(Integer id, Coordinate destinationPosition, int maxHealth, int maxHunger, int maxThirst,
			int maxSpeed) {
		super(id, destinationPosition, maxHealth, maxHunger, maxThirst, maxSpeed, Constants.ANT);
	}

	@Override
	public <T> T accept(InsectVisitor<T> visitor) {
		return visitor.visit(this);
	}

	

}