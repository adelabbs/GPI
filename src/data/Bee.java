package data;

import process.InsectVisitor;

public class Bee extends Insect {

	public Bee(Integer id, Coordinate destinationPosition, int maxHealth, int maxHunger, int maxThirst,
			int maxSpeed) {
		super(id, destinationPosition, maxHealth, maxHunger, maxThirst, maxSpeed, Constants.BEE);
	}

	@Override
	public <T> T accept(InsectVisitor<T> visitor) {
		return visitor.visit(this);
	}
}