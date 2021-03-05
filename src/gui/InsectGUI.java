package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import process.Simulation;
import process.SimulationEntry;
import process.SimulationState;
import process.SimulationUtility;
import test.manual.SimuPara;

/*
 * Main GUI class for simulation
 * 
 */
public class InsectGUI extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	private static final Dimension IDEAL_MAIN_DIMENSION = new Dimension(1100, 1100);
	private static final Dimension IDEAL_DASHBOARD_DIMENSION = new Dimension(1000, 1000);

	private Dashboard dashboard;

	private Simulation simulation;

	private boolean stop = false;

	public InsectGUI() {
		super("BugStudio");
		SimulationEntry simEntry = new SimulationEntry(SimuPara.SIMULATION_TILES,
				SimuPara.SIMULATION_INSECT_COUNT_PER_TYPE);
		simulation = new Simulation(simEntry);
		dashboard = new Dashboard(simulation);
		init();
	}

	private void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		dashboard.setPreferredSize(IDEAL_DASHBOARD_DIMENSION);
		contentPane.add(BorderLayout.CENTER, dashboard);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(IDEAL_MAIN_DIMENSION);
		setResizable(false);
	}

	public void updateValues() {
		dashboard.setSimulation(simulation);
		dashboard.revalidate();
		dashboard.repaint();
		if(simulation.getInsects().isEmpty()) {
			simulation.setState(SimulationState.STOP);
		}
	}

	@Override
	public void run() {
		while (!stop) {
			

			if (simulation.isReady()) {
				simulation.launch();
			}
			if (simulation.isRunning()) {
				SimulationUtility.unitTime();
				simulation.simulate();
				updateValues();
			}
			if (simulation.getState() == SimulationState.STOP) {
				dashboard.printEnd();
				stop = true;
			}
		}
	}

}
