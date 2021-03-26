package process;

import test.manual.SimuPara;

public class SimulationEntry {
	private int mapSize;
	private int antCount;
	private int beeCount;
	private int spiderCount;
	private int centipedeCount;

	private int flowerCount;
	private int waterCount;
	private int foodCount;

	public SimulationEntry() {
		mapSize = SimuPara.SIMULATION_TILES;
		antCount = SimuPara.ANT_COUNT;
		beeCount = SimuPara.BEE_COUNT;
		spiderCount = SimuPara.SPIDER_COUNT;
		centipedeCount = SimuPara.CENTIPEDE_COUNT;
		flowerCount = SimuPara.FLOWER_COUNT;
		waterCount = SimuPara.WATER_COUNT;
		foodCount = SimuPara.FOOD_COUNT;
	}

	public SimulationEntry(int mapSize, int antCount, int beeCount, int spiderCount, int flowerCount, int waterCount,
			int foodCount) {
		this.mapSize = mapSize;
		this.antCount = antCount;
		this.beeCount = beeCount;
		this.spiderCount = spiderCount;
		this.flowerCount = flowerCount;
		this.waterCount = waterCount;
		this.foodCount = foodCount;
	}

	public int getAntCount() {
		return antCount;
	}

	public void setAntCount(int antCount) {
		this.antCount = antCount;
	}

	public int getBeeCount() {
		return beeCount;
	}

	public void setBeeCount(int beeCount) {
		this.beeCount = beeCount;
	}

	public int getSpiderCount() {
		return spiderCount;
	}
	
	public int getCentipedeCount() {
		return centipedeCount;
	}
	
	public void setCentipedeCount(int centipedeCount) {
		this.centipedeCount = centipedeCount;
	}

	public void setSpiderCount(int spiderCount) {
		this.spiderCount = spiderCount;
	}

	public int getMapSize() {
		return mapSize;
	}

	public int getFlowerCount() {
		return flowerCount;
	}

	public void setFlowerCount(int flowerCount) {
		this.flowerCount = flowerCount;
	}

	public int getWaterCount() {
		return waterCount;
	}

	public void setWaterCount(int waterCount) {
		this.waterCount = waterCount;
	}

	public int getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

}