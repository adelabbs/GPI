package test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({TestAntManager.class, TestSimulation.class, TestBugManager.class})
public class SimulationTestSuite {

}