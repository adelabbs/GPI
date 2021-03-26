package data;

import java.util.ArrayList;

public abstract class Nest {

	private Integer id;
	private String type;

	private Coordinate position;

	private int size;
	private ArrayList<Insect> insects = new ArrayList<Insect>();
	
	private int capacity;
	private int currentInsects;

	private int maxHealth;
	private int currentHealth;
	
	private int timeReproduction;
	
	public Nest(Integer id, String type, Coordinate position, int maxHealth,
			int capacity, int timeReproduction) {
		this.id=id;
		this.position=position;
		this.maxHealth=maxHealth;
		this.type=type;
		this.timeReproduction = timeReproduction;
	}
	
	public void incrementCurrentInsects() {
		if (currentInsects < capacity) {
			currentInsects++;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCurrentInsects() {
		return currentInsects;
	}

	public void setCurrentInsects(int currentInsects) {
		this.currentInsects = currentInsects;
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

	public ArrayList<Insect> getInsects() {
		return insects;
	}

	public void setInsects(ArrayList<Insect> insects) {
		this.insects = insects;
	}

	public int getTimeReproduction() {
		return timeReproduction;
	}

	public void setTimeReproduction(int timeReproduction) {
		this.timeReproduction = timeReproduction;
	}
}
