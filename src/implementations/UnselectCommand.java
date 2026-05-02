package implementations;

import interfaces.Command;
import shapes.Shape;

public class UnselectCommand implements Command {

	private Shape shape;
	
	public void execute() {
		shape.setSelected(false);
	}

	public void unexecute() {
		shape.setSelected(true);
	}
	
	// Getters & setters

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

}
