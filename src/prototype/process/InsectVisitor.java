package prototype.process;

import prototype.data.Ant;
import prototype.data.Bee;

public interface InsectVisitor<T> {
	T visit(Bee insect);

	T visit(Ant insect);

}