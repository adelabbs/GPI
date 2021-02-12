package test.manual;

import data.Coordinate;

public class CoordinatesTest {

	public static void main(String[] args) {

		Coordinate coordiante1 = new Coordinate(10, 10);

		Coordinate coordiante2 = new Coordinate(5, 10);

		System.out.println(calculateDirection(coordiante1, coordiante2));

	}

	public static double calculateDirection(Coordinate currentPosition, Coordinate nextPosition) {
		double angle = Math.toDegrees(Math.atan2(nextPosition.getOrdinate() - currentPosition.getOrdinate(),
				nextPosition.getAbscissa() - currentPosition.getAbscissa()));
		return angle;
	}

}
