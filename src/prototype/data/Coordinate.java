package prototype.data;

public class Coordinate {
	
	private float abscissa;
	private float ordinate;
	
	public Coordinate(float abscissa, float ordinate) {
		super();
		this.abscissa = abscissa;
		this.ordinate = ordinate;
	}
	public float getOrdinate() {
		return ordinate;
	}
	public void setOrdinate(float ordinate) {
		this.ordinate = ordinate;
	}
	public float getAbscissa() {
		return abscissa;
	}
	public void setAbscissa(float abscissa) {
		this.abscissa = abscissa;
	}
	
}
