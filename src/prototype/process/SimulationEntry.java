package prototype.process;

public class SimulationEntry {
	int mapSize;
	int insectCount;

	public SimulationEntry(int mapSize, int insectCount) {
		this.mapSize = mapSize;
		this.insectCount = insectCount;
	}

	public int getInsectCount() {
		return insectCount;
	}

	public int getMapSize() {
		return mapSize;
	}

}