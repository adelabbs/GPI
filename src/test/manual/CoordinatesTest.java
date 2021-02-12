package test.manual;

import data.Bee;
import data.Coordinate;
import process.manager.BeeManager;

public class CoordinatesTest {

	public static void main(String[] args) {
		Bee bee = new Bee(1, new Coordinate(25.0, 25.0), 10, 10, 10, 10);
		BeeManager beeman = new BeeManager("0", "peaceful", bee);
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		
		beeman.update();
		System.out.println(beeman.getInsect().getCurrentPosition().toString());
		

	}

}
