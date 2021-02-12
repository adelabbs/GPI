package test.manual;

import gui.InsectGUI;

public class TestGUI {

	public static void main(String[] args) {
		InsectGUI insectGUI = new InsectGUI();
		Thread thread = new Thread(insectGUI);
		thread.start();
	}

}
