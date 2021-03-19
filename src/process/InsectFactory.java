package process;

import data.Ant;
import data.Bee;
import data.Constants;
import data.Coordinate;
import data.Insect;
import data.Spider;
import test.manual.SimuPara;

/**
 * {@link InsectFactory} uses a Singleton design pattern to make sure
 * {@link Insect } ids are unique.
 * 
 * @author Adel
 *
 */
public class InsectFactory {

	private InsectFactory instance;

	private int nextId = 0;

	public InsectFactory() {

	}

	public InsectFactory getInstance() {
		return instance;
	}

	public Ant createAnt(Coordinate position) {
		return new Ant(getNextId(), position, SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST,
				SimuPara.ANT_SPEED);
	}

	public Bee createBee(Coordinate position) {
		return new Bee(getNextId(), position, SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST,
				SimuPara.BEE_SPEED);
	}

	public Spider createSpider(Coordinate position) {
		return new Spider(getNextId(), position, SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST,
				SimuPara.SPIDER_SPEED);
	}

	public Insect createInsect(String type, Coordinate position) throws IllegalArgumentException {
		switch (type) {
		case Constants.ANT:
			return createAnt(position);
		case Constants.BEE:
			return createBee(position);
		case Constants.SPIDER:
			return createSpider(position);
		default:
			throw new IllegalArgumentException("Unknown insect type : " + type);
		}
	}

	private int getNextId() {
		return ++nextId;
	}
}