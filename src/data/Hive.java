package data;

public class Hive extends Nest {

	public Hive(TileCoordinate position, int maxHealth, int capacity, int reproductionTime) {
		super(Constants.HIVE, position, maxHealth, capacity, reproductionTime);
	}

	public void add(Bee bee) {
		if (bee != null) {
			getInsects().add(bee);
			incrementCurrentInsectsCount();
		}
	}
}
