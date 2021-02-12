package data;

public class NaturalResource {
	private String type;
	private Integer id;
	private int quantity;
	private TileCoordinate coordinates;

	public static final String FLOWER = "flower";
	public static final String WATER = "water";

	public NaturalResource(String type, Integer integer, int quantity, TileCoordinate coordinates) {
		this.type = type;
		this.id = integer;
		this.quantity = quantity;
		this.coordinates = coordinates;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TileCoordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(TileCoordinate coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
