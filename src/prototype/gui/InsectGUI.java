package prototype.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import prototype.process.Simulation;
import prototype.process.SimulationEntry;


public class InsectGUI extends JFrame implements Runnable {

	
	
	
	
	
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
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	public void updateValues() {
		
		
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
		new InsectGUI("Prototype bete");
	}
}
