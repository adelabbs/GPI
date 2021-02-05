package prototype.data;

import java.util.ArrayList;

import prototype.process.InsectVisitor;

/**
 * The Insect model
 *
 */
public abstract class Insect {

	private Integer id;
	
	private Coordinate currentPosition;
	private Coordinate destinationPosition;
	private ArrayList<NaturalResource> poi;
	
	private String type;

	private int maxHealth;
	private int currentHealth;
	private int currentHunger;
	private int maxHunger;
	private int currentThirst;
	private int maxThirst;
	private int speed;
	private int maxSpeed;

	public Insect(Integer id, Coordinate destinationPosition, int maxHealth, int maxHunger, int maxThirst, 
			int maxSpeed, String type) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public void add (NaturalResource naturalResource) {
		poi.add(naturalResource);
	}
	public void remove (NaturalResource naturalResource) {
		poi.remove(naturalResource);
	}
	public ArrayList<NaturalResource> getPoi() {
		return poi;
	}

	public void setPoi(ArrayList<NaturalResource> poi) {
		this.poi = poi;
	}
}
