package data;

public class Anthill extends Nest {

	public Anthill(Coordinate position, int maxHealth, int capacity, int reproductionTime) {
		super(Constants.ANTILL, position, maxHealth, capacity, reproductionTime);
	}

	public void add(Ant ant) {
		if (ant != null) {
			getInsects().add(ant);
			incrementCurrentInsectsCount();
		}
	}
}