package prototype.data;

/**
 * The current environment is represented by a grid.
 * 
 *
 */
public class Environment {

	private float[][] map;

	public Environment(float[][] map) {
		this.map = map;
	}

	public float[][] getMap() {
		return map;
	}

	public void setMap(float[][] map) {
		this.map = map;
	}

}