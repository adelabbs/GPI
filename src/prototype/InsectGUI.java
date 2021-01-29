package prototype;

import javax.swing.JFrame;


public class InsectGUI extends JFrame implements Runnable {

	
	
	
	
	private JPanel 
	
	private Dashboard dashboard = new Dashboard();
	private Simulation simulation = new Simulation;
	
	public InsectGUI(String title) {
		super(title);
		init();
	}
	
	
	private void init() {
		
	}
	
	public static void main(String[] args) {
		new InsectGUI("Prototype bete");
	}
}
