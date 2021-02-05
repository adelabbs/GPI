package prototype.data;

public class Coordinate {
	
	private double abscissa;
	private double ordinate;
	private int orientation; //0=north, 1=east, 2=south, 3=west
	
	public Coordinate(double newX, double newY, int orientation) {
		this.abscissa = newX;
		this.ordinate = newY;
		this.orientation = orientation;
	}
	public double getOrdinate() {
		return ordinate;
	}
	public void setOrdinate(double ordinate) {
		this.ordinate = ordinate;
	}
	public double getAbscissa() {
		return abscissa;
	}
	public void setAbscissa(double abscissa) {
		this.abscissa = abscissa;
	}
	
	@Override
	public String toString() {
		return "Coordinate [abscissa=" + abscissa + ", ordinate=" + ordinate + "]";
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	
}
