package process.manager;

import data.Environment;
import data.Insect;
import data.Spider;

public class SpiderManager extends BugManager {

	private Spider spider;

	public SpiderManager(String groupID, String agressivity, Environment environment, Spider spider) {
		super(groupID, agressivity, environment);
		this.spider = spider;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Insect getInsect() {
		return spider;

	}

}
