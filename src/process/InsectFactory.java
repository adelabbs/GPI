package process;

import data.Ant;
import data.Bee;
import data.Centipede;
import data.Constants;
import data.Coordinate;
import data.Environment;
import data.Insect;
import data.Spider;
import process.manager.AntManager;
import process.manager.BeeManager;
import process.manager.BugManager;
import process.manager.CentipedeManager;
import process.manager.SpiderManager;
import test.manual.SimuPara;

/**
 * {@link InsectFactory} uses a Singleton design pattern to make sure
 * {@link Insect } ids are unique.
 * 
 *
 */
public class InsectFactory {

	private static InsectFactory instance = new InsectFactory();

	private int nextId = 0;

	private InsectFactory() {

	}

	public static InsectFactory getInstance() {
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
	
	public Centipede createCentipede(Coordinate position) {
		return new Centipede(getNextId(), position, SimuPara.MAX_HEALTH, SimuPara.MAX_HUNGER, SimuPara.MAX_THIRST,
				SimuPara.CENTIPEDE_SPEED);
	}

	public Insect createInsect(String type, Coordinate position) throws IllegalArgumentException {
		switch (type) {
		case Constants.ANT:
			return (Ant) createAnt(position);
		case Constants.BEE:
			return (Bee) createBee(position);
		case Constants.SPIDER:
			return (Spider) createSpider(position);
		case Constants.CENTIPEDE:
			return (Centipede) createCentipede(position);
			
		default:
			throw new IllegalArgumentException("Unknown insect type : " + type);
		}
	}

	public BugManager createBugManager(String group, Insect insect) throws IllegalArgumentException {
		if (insect == null)
			throw new IllegalArgumentException("Insect is null");
		String type = insect.getType();
		switch (type) {
		case Constants.ANT:
			return new AntManager(group, "peaceful", (Ant) insect, Environment.getInstance());
		case Constants.BEE:
			return new BeeManager(group, "peaceful", (Bee) insect, Environment.getInstance());
		case Constants.SPIDER:
			return new SpiderManager(group, "peaceful", (Spider) insect, Environment.getInstance());
		case Constants.CENTIPEDE:
			return new CentipedeManager(group, "peaceful", (Centipede) insect, Environment.getInstance());

		default:
			throw new IllegalArgumentException("Unknown insect type : " + type);
		}

	}

	private int getNextId() {
		return ++nextId;
	}
}