package data;

import java.util.ArrayList;
import java.util.HashMap;

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
	private HashMap<TileCoordinate, NaturalResource> resources = new HashMap<TileCoordinate, NaturalResource>();
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

	public ArrayList<Insect> getInsects() {
		return insects;
	}

	public void setInsects(ArrayList<Insect> insects) {
		this.insects = insects;
	}

	public synchronized void add(Insect insect) {
		if (insect != null) {
			insects.add(insect);
		}
	}

	public synchronized void remove(Insect insect) {
		insects.remove(insect);
	}

	public synchronized void addResource(NaturalResource resource) throws IllegalArgumentException {
		boolean inserted = false;
		if (resource != null) {
			if (resource.getCoordinates() != null) {
				resources.put(resource.getCoordinates(), resource);
				inserted = true;
			}
		}
		if (!inserted) {
			throw new IllegalArgumentException("Empty resource");
		}
	}

	public synchronized void remove(NaturalResource resource) {
		resources.remove(resource.getCoordinates());
	}

	public HashMap<TileCoordinate, NaturalResource> getResources() {
		return resources;
	}

	public ArrayList<NaturalResource> getResourcesList() {
		return new ArrayList<NaturalResource>(resources.values());
	}

	public NaturalResource getResource(TileCoordinate position) {
		return resources.get(position);
	}

	public void setResources(HashMap<TileCoordinate, NaturalResource> resources) {
		this.resources = resources;
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