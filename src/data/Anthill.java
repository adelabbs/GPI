package data;

public class Anthill extends Nest{
			
	public Anthill(Integer id, String type, Coordinate position, int maxHealth, int capacity) {
		super(id, Constants.ANTILL, position, maxHealth, capacity);
	}

	public void addAnt(Ant ant) {
		getInsects().add(ant);
	}

	public void removeAnt(Ant ant) {
		if (getInsects().contains(ant)) {
			getInsects().remove(ant);
		}
	}

}
