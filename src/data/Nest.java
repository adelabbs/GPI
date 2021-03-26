package data;

import java.util.ArrayList;

import test.manual.SimuPara;

public class Nest {
	
	public static final int MIN_LIFESPAN = 10000;
	public static final int MAX_LIFESPAN = 20000;

	private Integer id;
	private String type;

	private Coordinate Position;
	private ArrayList<Insect> insects = new ArrayList<Insect>();

	private int size;
	
	private int capacity;
	private int currentInsects;

	private int maxHealth;
	private int currentHealth;
	private int lifeSpan;
	
	public Nest(Integer id, Coordinate destinationPosition, int maxHealth, int maxHunger, int maxThirst, int maxSpeed,
			String type) {
		
	}
}
