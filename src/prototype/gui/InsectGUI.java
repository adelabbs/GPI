package prototype.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import prototype.process.Simulation;
import prototype.process.SimulationEntry;
import test.SimuPara;

/*
 * Main GUI class for simulation
 * 
 */
public class InsectGUI extends JFrame implements Runnable {

	private static final Dimension IDEAL_MAIN_DIMENSION = new Dimension(1100, 1100);
	private static final Dimension IDEAL_DASHBOARD_DIMENSION = new Dimension(1000, 1000);

	private Dashboard dashboard;

	private Simulation simulation;

	private boolean stop = false;

	public InsectGUI() {
		super("BugStudio");
		SimulationEntry simEntry = new SimulationEntry(SimuPara.SIMULATION_MAP_SIZE,
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
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(SimuPara.SIMULATION_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			simulation.simulate();

			if (!stop) {
				updateValues();
			}
		}
	}

}
