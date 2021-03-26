package process;

import data.Ant;
import data.Bee;
import data.Centipede;
import data.Spider;

public interface InsectVisitor<T> {
	T visit(Bee insect);

	T visit(Ant insect);

	T visit(Spider spider);
	
	T visit(Centipede centipede);
}