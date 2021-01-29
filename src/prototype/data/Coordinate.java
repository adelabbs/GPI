package prototype.data;

public class Coordinate {
	
	private float absyss;
	private float ordinate;
	
	public Coordinate(float absyss, float ordinate) {
		super();
		this.absyss = absyss;
		this.ordinate = ordinate;
	}
	public float getOrdinate() {
		return ordinate;
	}
	public void setOrdinate(float ordinate) {
		this.ordinate = ordinate;
	}
	public float getAbsyss() {
		return absyss;
	}
	public void setAbsyss(float absyss) {
		this.absyss = absyss;
	}
	
}
