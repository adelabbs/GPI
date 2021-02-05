package prototype.data;

public class NaturalResource {
	private String type;
	private String id;
	private int quantity;
	private float xPos;
	private float yPos;


	public NaturalResource (String type, String id, int quantity, float x, float y) {
		this.type = type;
		this.id = id;
		this.quantity = quantity;
		this.xPos = x;
		this.yPos = y;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
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
