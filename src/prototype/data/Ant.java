package prototype.data;

import prototype.process.InsectVisitor;

public class Ant extends Insect {

	public Ant(Integer id, Coordinate coordinates, int maxHealth, int maxHunger, int maxThirst, int speed) {
		super(id, coordinates, maxHealth, maxHunger, maxThirst, speed);
	}

	@Override
	<T> T accept(InsectVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}