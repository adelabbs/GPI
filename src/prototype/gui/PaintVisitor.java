package prototype.gui;

import java.awt.Color;
import java.awt.Graphics;

import prototype.data.Ant;
import prototype.data.Bee;
import prototype.process.InsectVisitor;

public class PaintVisitor implements InsectVisitor<Void> {

	private Graphics g;

	public PaintVisitor(Graphics graphics) {
		this.g = graphics;
	}

	@Override
	public Void visit(Bee insect) {

		g.drawOval((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate(), 50,
				50);

		g.setColor(Color.RED);

		return null;
	}

	@Override
	public Void visit(Ant insect) {
		g.drawOval((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate(), 50,
				50);

		g.setColor(Color.BLUE);
		return null;
	}

}