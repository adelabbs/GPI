package process.manager;

import data.Nest;

public interface NestManager {

	void update();

	void reproduce();
	
	void idle();

	Nest getNest();

}