package implementations;

import interfaces.Command;
import shapes.Shape;

public class SelectCommand implements Command {

	private Shape shape;
	
	public void execute() {
		shape.setSelected(true);
	}

	public void unexecute() {
		shape.setSelected(false);
	}

	// Getters & setters
	
	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
