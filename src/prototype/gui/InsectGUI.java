package prototype.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import prototype.process.Simulation;
import prototype.process.SimulationEntry;

/*
 * Main GUI class for simulation
 * 
 */
public class InsectGUI extends JFrame implements Runnable {
	private static final Dimension IDEAL_MAIN_DIMENSION = new Dimension(800, 400);
	private static final Dimension IDEAL_DASHBOARD_DIMENSION = new Dimension(800, 300);
	
		
	private Dashboard dashboard = new Dashboard();
	
	private SimulationEntry simEntry = new SimulationEntry();
	private Simulation simulation = new Simulation(simEntry);
	
	private boolean stop = true;
	
	public InsectGUI(String title) {
		super(title);
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
		dashboard.setPosX(DISPOSE_ON_CLOSE);
		dashboard.setPosY(DISPOSE_ON_CLOSE);
		
		
		dashboard.repaint();
	}
	
	
	@Override
	public void run() {
		while(!stop) {
			
			if(!stop) {
				updateValues();
			}
		}
	}
	
	public static void main(String[] args) {
		new InsectGUI("BugsStudio");
	}
}
