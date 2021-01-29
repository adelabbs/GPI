package prototype.data;

import prototype.process.InsectVisitor;

public class Bee extends Insect {

	public Bee(Integer id, Coordinate coordinates, int maxHealth, int maxHunger, int maxThirst, int speed) {
		super(id, coordinates, maxHealth, maxHunger, maxThirst, speed);
	}

	@Override
	<T> T accept(InsectVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	
}