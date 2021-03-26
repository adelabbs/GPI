package data;

public class Hive extends Nest{

	public Hive(Integer id, String type, Coordinate position, int maxHealth, int capacity) {
		super(id, Constants.HIVE, position, maxHealth, capacity);
	}

	public void addBee(Bee bee) {
		getInsects().add(bee);
	}

	public void removeAnt(Bee bee) {
		if (getInsects().contains(bee)) {
			getInsects().remove(bee);
		}
	}

}
