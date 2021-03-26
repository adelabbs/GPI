package data;

import process.InsectVisitor;

public class Centipede extends Insect {

	public Centipede(Integer id, Coordinate destinationPosition, int maxHealth, int maxHunger, int maxThirst,
			int maxSpeed) {
		super(id, destinationPosition, maxHealth, maxHunger, maxThirst, maxSpeed, Constants.CENTIPEDE);
	}
	
	@Override
	public <T> T accept(InsectVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
