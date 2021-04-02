package data;

import java.util.ArrayList;

public abstract class Nest {

	private String type;

	private TileCoordinate position;

	private ArrayList<Insect> insects = new ArrayList<Insect>();

	private int maxCapacity;
	private int currentInsectsCount = 0;

	private int maxHealth;
	private int currentHealth;

	private int reproductionTime;

	public Nest(String type, TileCoordinate position, int maxHealth, int maxCapacity, int reproductionTime) {
		this.position = position;
		this.maxHealth = maxHealth;
		currentHealth = maxHealth;
		this.type = type;
		this.reproductionTime = reproductionTime;
	}

	public String getType() {
		return type;
	}

	public TileCoordinate getPosition() {
		return position;
	}

	public void setPosition(TileCoordinate position) {
		this.position = position;
	}

	public ArrayList<Insect> getInsects() {
		return insects;
	}

	public void setInsects(ArrayList<Insect> insects) {
		this.insects = insects;
	}

	public void remove(Insect insect) {
		if (insects.contains(insect)) {
			insects.remove(insect);
			decrementCurrentInsectsCount();
		}
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public int getCurrentInsectsCount() {
		return currentInsectsCount;
	}

	public void incrementCurrentInsectsCount() {
		if (currentInsectsCount < maxCapacity) {
			currentInsectsCount++;
		}
	}

	public void decrementCurrentInsectsCount() {
		if (currentInsectsCount > 0) {
			currentInsectsCount--;
		}
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getReproductionTime() {
		return reproductionTime;
	}

	public void setReproductionTime(int reproductionTime) {
		this.reproductionTime = reproductionTime;
	}
}
