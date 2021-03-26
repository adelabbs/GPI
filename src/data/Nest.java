package data;

import java.util.ArrayList;

import test.manual.SimuPara;

public class Nest {
	
	public static final int MIN_LIFESPAN = 10000;
	public static final int MAX_LIFESPAN = 20000;

	private Integer id;
	private String type;

	private Coordinate position;
	private ArrayList<Insect> insects = new ArrayList<Insect>();

	private int size;
	
	private int capacity;
	private int currentInsects;

	private int maxHealth;
	private int currentHealth;
	private int lifeSpan;
	
	public Nest(Integer id, String type, Coordinate position, int maxHealth,
			int capacity) {
		this.id=id;
		this.position=position;
		this.maxHealth=maxHealth;
		this.type=type;
		lifeSpan = (int) (MIN_LIFESPAN + Math.random() * ((MAX_LIFESPAN - MIN_LIFESPAN) + 1));
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

	public ArrayList<Insect> getInsects() {
		return insects;
	}

	public void setInsects(ArrayList<Insect> insects) {
		this.insects = insects;
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

	public int getLifeSpan() {
		return lifeSpan;
	}

	public void setLifeSpan(int lifeSpan) {
		this.lifeSpan = lifeSpan;
	}
}
