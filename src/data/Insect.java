package data;

import java.util.ArrayList;

import process.InsectVisitor;
import test.manual.SimuPara;

/**
 * The Insect model
 *
 */
public abstract class Insect {

	public static final int MIN_LIFESPAN = 10000;
	public static final int MAX_LIFESPAN = 20000;

	private Integer id;
	private double direction = 0.0;

	private Coordinate currentPosition;
	private Coordinate destinationPosition;
	private ArrayList<NaturalResource> poi = new ArrayList<NaturalResource>();

	private int size = SimuPara.INSECT_DEFAULT_SIZE;

	private String type;

	private int maxHealth;
	private int currentHealth;
	private int currentHunger;
	private int maxHunger;
	private int currentThirst;
	private int maxThirst;
	private int speed;
	private int maxSpeed;
	private int lifeSpan;

	public Insect(Integer id, Coordinate destinationPosition, int maxHealth, int maxHunger, int maxThirst, int maxSpeed,
			String type) {
		this.id = id;
		this.destinationPosition = destinationPosition;
		currentPosition = destinationPosition;
		this.maxHealth = maxHealth;
		currentHealth = maxHealth;
		this.maxHunger = maxHunger;
		currentHunger = maxHunger;
		this.maxThirst = maxThirst;
		currentThirst = maxThirst;
		this.maxSpeed = maxSpeed;
		speed = maxSpeed;
		this.type = type;
		lifeSpan = (int) (MIN_LIFESPAN + Math.random() * ((MAX_LIFESPAN - MIN_LIFESPAN) + 1));
	}

	public abstract <T> T accept(InsectVisitor<T> visitor);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public int getCurrentHunger() {
		return currentHunger;
	}

	public void setCurrentHunger(int currentHunger) {
		this.currentHunger = currentHunger;
	}

	public void decreaseCurrentHunger() {
		if (currentHunger > 0) {
			currentHunger--;
		}
	}

	public void decreaseCurrentHealth() {
		if (currentHealth > 0) {
			currentHealth--;
		}
	}

	public int getMaxHunger() {
		return maxHunger;
	}

	public int getCurrentThirst() {
		return currentThirst;
	}

	public void setCurrentThirst(int currentThirst) {
		this.currentThirst = currentThirst;
	}

	public void decreaseCurrentThirst() {
		if (currentThirst > 0) {
			currentThirst--;
		}
	}

	public int getMaxThirst() {
		return maxThirst;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public Coordinate getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Coordinate currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Coordinate getDestinationPosition() {
		return destinationPosition;
	}

	public void setDestinationPosition(Coordinate destinationPosition) {
		this.destinationPosition = destinationPosition;
	}

	public void setDestinationPosition(TileCoordinate destinationTile) {
		Coordinate destinationPosition = new Coordinate(
				destinationTile.getAbscissa() * SimuPara.SCALE + SimuPara.INSECT_DEFAULT_SIZE / 2,
				destinationTile.getAbscissa() * SimuPara.SCALE + SimuPara.INSECT_DEFAULT_SIZE / 2);
		this.destinationPosition = destinationPosition;
	}
	
	public boolean isThirsty() {
		return (currentThirst / maxThirst) <= SimuPara.INSECT_THIRST_THRESHOLD;
	}
	
	public boolean isHungry() {
		return (currentHunger / maxHunger) <= SimuPara.INSECT_HUNGER_THRESHOLD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void add(NaturalResource naturalResource) {
		if (!poi.contains(naturalResource)) {
			poi.add(naturalResource);
		}
	}

	public void remove(NaturalResource naturalResource) {
		poi.remove(naturalResource);
	}

	public ArrayList<NaturalResource> getPoi() {
		return poi;
	}

	public void setPoi(ArrayList<NaturalResource> poi) {
		this.poi = poi;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getLifeSpan() {
		return lifeSpan;
	}

	public void decreaseLifeSpan() {
		if (lifeSpan > 0) {
			lifeSpan--;
		}
	}

	public void setLifeSpan(int lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

}
