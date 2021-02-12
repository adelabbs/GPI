package process;

import data.Ant;
import data.Bee;

public interface InsectVisitor<T> {
	T visit(Bee insect);

	T visit(Ant insect);

}