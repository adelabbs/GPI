package data;

import java.util.ArrayList;

/**
 * The current environment is represented by a grid.
 * 
 *
 */
public class Environment {
	/**
	 * The unique instance of the class prepared
	 */
	private static Environment instance = new Environment();
	private Integer[][] map;
	private ArrayList<NaturalResource> resources = new ArrayList<NaturalResource>();
	private ArrayList<Insect> insects = new ArrayList<Insect>();

	/**
	 * Private constructor ensuring no access from outside of the class.
	 */
	private Environment() {
	}

	public static Environment getInstance() {
		return instance;
	}

	public Integer[][] getMap() {
		return map;
	}

	public void setMap(Integer[][] map) {
		this.map = map;
	}

	public ArrayList<NaturalResource> getResources() {
		return resources;
	}

	public void setResources(ArrayList<NaturalResource> resources) {
		this.resources = resources;
	}

	public ArrayList<Insect> getInsects() {
		return insects;
	}

	public void setInsects(ArrayList<Insect> insects) {
		this.insects = insects;
	}

	public void add(Insect insect) {
		if (insect != null) {
			insects.add(insect);
		}
	}

	public void remove(Insect insect) {
		insects.remove(insect);
	}

	public String printMap() {
		String tmp = "Map : width=" + map.length + ", height=" + map.length + "\n";
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map.length; x++) {
				tmp += map[y][x].toString() + " ";
			}
			tmp += "\n";
		}
		return tmp;
	}

}