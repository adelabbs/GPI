package test.manual;

public class SimuPara {
	public static final int SIMULATION_SPEED = 16; // lower values = higher speeds
	public static final int SIMULATION_MAP_SIZE = 1000;

	public static final int SCALE = 50;

	public static final int SIMULATION_TILES = SIMULATION_MAP_SIZE / SCALE;

	public static final int INSECT_DEFAULT_SIZE = 48;

	public static final int INSECT_DEFAULT_EAT_QTT = 3;
	public static final int INSECT_DEFAULT_DRINK_QTT = 2;

	public static final int TILESET_SIZE = 3;

	public static final int MAX_HEALTH = 500;
	public static final int MAX_THIRST = 100;
	public static final int MAX_HUNGER = 100;

	public static final double INSECT_THIRST_THRESHOLD = 0.50; // in decimal (30% = 0.3)
	public static final double INSECT_HUNGER_THRESHOLD = 0.30;
	public static final int TIME_INTERVAL = 50;
	public static final int CONSUMING_TIME_INTERVAL = 15;
	public static final int REPRODUCTION_TIME_INTERVAL = 50;
	public static final int INSECT_ATTACK_TIME_INTERVAL = 10;
	
	public static final int INSECT_DAMAGE = 100;

	public static final double INSECT_DEFAULT_DRINK_UPPER_THRESHOLD = 0.75; // in decimal (30% = 0.3
	public static final double INSECT_DEFAULT_EAT_UPPER_THRESHOLD = 0.75; // in decimal (30% = 0.3

	public static final int ANT_SPEED = 1;
	public static final int BEE_SPEED = 2;
	public static final int SPIDER_SPEED = 1;
	public static final int CENTIPEDE_SPEED = 1;
	public static final int CENTIPEDE_CHASING_SPEED = 2;

	public static final int FLOWER_DEFAULT_QUANTITY = 300;
	public static final int WATER_DEFAULT_QUANTITY = 100;
	public static final int FOOD_DEFAULT_QUANTITY = 100;

	public static final int WATER_COUNT = 10;
	public static final int FLOWER_COUNT = 10;
	public static final int FOOD_COUNT = 0; // TODO Add images, before keep FOOD_COUNT at 0

	public static final int ANT_COUNT = 3;
	public static final int BEE_COUNT = 2;
	public static final int SPIDER_COUNT = 1; // TODO Add images, before keep FOOD_COUNT at 0
	public static final int CENTIPEDE_COUNT = 1;

	public static final int NEST_MAX_HEALTH = 1000;
	public static final int NEST_MAX_CAPACITY = 4;

	public static final int NEST_REPRODUCTION_TIME = 1500;
	public static final int HIVE_REPRODUCTION_TIME = 1500;
	public static final int ANTHILL_REPRODUCTION_TIME = 2000;

}
