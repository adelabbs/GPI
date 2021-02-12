package data;

public class NaturalResource {
	private String type;
	private String id;
	private int quantity;
	private Coordinate coordinates;


	public NaturalResource (String type, String id, int quantity, Coordinate coordinates) {
		this.type = type;
		this.id = id;
		this.quantity = quantity;
		this.coordinates = coordinates;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate coordinates) {
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
