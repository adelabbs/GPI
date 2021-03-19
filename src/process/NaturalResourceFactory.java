package process;

import data.Constants;
import data.NaturalResource;
import data.TileCoordinate;
import test.manual.SimuPara;

public class NaturalResourceFactory {

	private static Integer currentResourceId = 0;

	public static NaturalResource createResource(String type, TileCoordinate position) throws IllegalArgumentException {
		switch (type) {
		case Constants.FOOD:
			return new NaturalResource(type, getNextResourceId(), SimuPara.FOOD_DEFAULT_QUANTITY, position);
		case Constants.FLOWER:
			return new NaturalResource(type, getNextResourceId(), SimuPara.FLOWER_DEFAULT_QUANTITY, position);
		case Constants.WATER:
			return new NaturalResource(type, getNextResourceId(), SimuPara.WATER_DEFAULT_QUANTITY, position);
		default:
			throw new IllegalArgumentException("Unknown resource type : " + type);
		}
	}

	private static Integer getNextResourceId() {
		return ++currentResourceId;
	}
}
