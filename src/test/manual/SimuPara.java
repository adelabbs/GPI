package test.manual;

public class SimuPara {
	public static final int SIMULATION_SPEED = 10; //lower values = higher speeds
	public static final int SIMULATION_MAP_SIZE = 1000;
	public static final int SCALE = 50;

	public static final int SIMULATION_TILES = SIMULATION_MAP_SIZE / SCALE;

	public static final int INSECT_DEFAULT_SIZE = 48;

	public static final int INSECT_DEFAULT_EAT_QTT = 3;
	public static final int INSECT_DEFAULT_DRINK_QTT = 2;

	public static final int SIMULATION_INSECT_COUNT_PER_TYPE = 1;
	public static final int TILESET_SIZE = 3;

	public static final int MAX_HEALTH = 500;
	public static final int MAX_THIRST = 100;
	public static final int MAX_HUNGER = 100;

	public static final double INSECT_THIRST_THRESHOLD = 0.50; // in decimal (30% = 0.3)
	public static final double INSECT_HUNGER_THRESHOLD = 0.30;
	public static final int TIME_INTERVAL = 100;
	public static final int CONSUMING_TIME_INTERVAL = 20;
	
	
	public static final double INSECT_DEFAULT_DRINK_UPPER_THRESHOLD = 0.75; // in decimal (30% = 0.3
	public static final double INSECT_DEFAULT_EAT_UPPER_THRESHOLD = 0.75; // in decimal (30% = 0.3
	
	public static final int ANT_SPEED = 1;
	public static final int BEE_SPEED = 1;
	public static final int SPIDER_SPEED = 1;

}
