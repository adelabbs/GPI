package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Ant;
import data.Coordinate;
import data.Environment;
import data.Insect;
import data.Spider;
import process.SimulationUtility;
import process.manager.AntManager;
import process.manager.SpiderManager;

public class TestSpiderManager {
	
	@Test
	public void testDetection() {
		Ant ant = new Ant(1, new Coordinate(15, 15), 10, 10, 10, 1);
		AntManager manager = new AntManager(null, null, ant, null);
		
		Spider spider = new Spider(1, new Coordinate(150, 170), 10, 10, 10, 1);
		SpiderManager spidermanager = new SpiderManager(null, null, null, spider);
		
		Environment e = Environment.getInstance();
		
		
		double rangeC = (double) spidermanager.getRange()*50;
		
		e.add(ant);
		e.add(spider);
		
		double distance = SimulationUtility.distance(spider.getCurrentPosition(),ant.getCurrentPosition());
		Boolean detection = false;
		
		if(distance < rangeC) {
			detection = true;
		}
		
		assertTrue(detection);
	}
	
	@Test
	public void testSelectPrey() {
		Ant ant = new Ant(1, new Coordinate(15, 15), 10, 10, 10, 1);
		AntManager manager = new AntManager(null, null, ant, null);
		
		Ant ant2 = new Ant(1, new Coordinate(28, 30), 10, 10, 10, 1);
		AntManager manager2 = new AntManager(null, null, ant2, null);
		
		Spider spider = new Spider(1, new Coordinate(150, 170), 10, 10, 10, 1);
		SpiderManager spidermanager = new SpiderManager(null, null, null, spider);
		
		Environment e = Environment.getInstance();
		
		
		
		
		e.add(ant);
		e.add(ant2);
		e.add(spider);
		
		double dmin = 1000;
		double dEachInsect;
		Insect insect=null;
		
		for (Insect i : e.getInsects()) {
			dEachInsect = SimulationUtility.distance(spider.getCurrentPosition(),i.getCurrentPosition());
			if(dEachInsect< dmin && dEachInsect>1.0) {
				insect= i;
				dmin = dEachInsect;
			}
		}
		assertEquals(ant2,insect);
	}
	
	@Test
	public void testmoveInsect() {
		Environment e = Environment.getInstance();
		Ant ant = new Ant(1, new Coordinate(15, 15), 10, 10, 10, 1);
		AntManager manager = new AntManager(null, null, ant, e);
		
		Spider spider = new Spider(1, new Coordinate(150, 170), 10, 10, 10, 1);
		SpiderManager spidermanager = new SpiderManager(null, null, e, spider);
		
		int range = 5;
		
		Boolean closer = false;
		
		e.add(ant);
		e.add(spider);
		
		spider.setDestinationPosition(ant.getCurrentPosition());
		double distance = SimulationUtility.distance(spider.getCurrentPosition(),ant.getCurrentPosition());
		
		spidermanager.update();
		
		double distance2 = SimulationUtility.distance(spider.getCurrentPosition(),ant.getCurrentPosition());
		
		if(distance> distance2) {
			closer = true;
		}

		assertTrue(closer);
	}
	
	@Test
	public void testAttack() {
		Ant ant = new Ant(1, new Coordinate(15, 15), 10, 10, 10, 1);
		AntManager manager = new AntManager(null, null, ant, null);
		
		Spider spider = new Spider(1, new Coordinate(150, 170), 10, 10, 10, 1);
		SpiderManager spidermanager = new SpiderManager(null, null, null, spider);
		
		Environment e = Environment.getInstance();
		
		int range = 5;
		double rangeC = (double)range*50;
		
		e.add(ant);
		e.add(spider);
		
		int antHealth = ant.getCurrentHealth() -1;
	    spidermanager.attackPrey(ant);
	
		
		assertEquals(antHealth,ant.getCurrentHealth());
	}

}
