package prototype.data;

import prototype.process.InsectVisitor;

/**
 * The Insect model
 *
 */
public abstract class Insect {

	private Integer id;
	private Coordinate coordinates;

	private int maxHealth;
	private int currentHealth;
	private int currentHunger;
	private int maxHunger;
	private int currentThirst;
	private int maxThirst;
	private int speed;
	private int maxSpeed;
	private int currentSpeed;

	public Insect(Integer id, Coordinate coordinates, int maxHealth, int maxHunger, int maxThirst, int speed) {
		this.id = id;
		this.coordinates = coordinates;
		this.maxHealth = maxHealth;
		currentHealth = maxHealth;
		this.maxHunger = maxHunger;
		currentHunger = maxHunger;
		this.maxThirst = maxThirst;
		currentThirst = maxThirst;
		this.maxSpeed = speed;
		currentSpeed = maxSpeed;
	}
	
	abstract <T>  T accept(InsectVisitor<T> visitor);

	public Integer getId() {
		return id;
	}


	public Coordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate coordinates) {
		this.coordinates = coordinates;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
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

	public int getMaxHunger() {
		return maxHunger;
	}

	public void setMaxHunger(int maxHunger) {
		this.maxHunger = maxHunger;
	}

	public int getCurrentThirst() {
		return currentThirst;
	}

	public void setCurrentThirst(int currentThirst) {
		this.currentThirst = currentThirst;
	}

	public int getMaxThirst() {
		return maxThirst;
	}

	public void setMaxThirst(int maxThirst) {
		this.maxThirst = maxThirst;
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

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

}
