package data;

public class Anthill extends Nest {

	public Anthill(TileCoordinate position, int maxHealth, int capacity, int reproductionTime) {
		super(Constants.ANTHILL, position, maxHealth, capacity, reproductionTime);
	}

	public void add(Ant ant) {
		if (ant != null) {
			getInsects().add(ant);
			incrementCurrentInsectsCount();
		}
	}
}