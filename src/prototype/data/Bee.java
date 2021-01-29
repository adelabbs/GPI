package prototype.data;

import prototype.process.InsectVisitor;

public class Bee extends Insect {

	public Bee(Integer id, Coordinate destinationPosition, int maxHealth, int maxHunger, int maxThirst, int speed,
			int maxSpeed) {
		super(id, destinationPosition, maxHealth, maxHunger, maxThirst, speed, maxSpeed);
	}

	@Override
	<T> T accept(InsectVisitor<T> visitor) {
		return visitor.visit(this);
	}
}