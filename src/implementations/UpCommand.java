package implementations;

import java.util.Collections;

import interfaces.Command;
import mvc.Model;
import shapes.Shape;

public class UpCommand implements Command {

	private Model model;
	private Shape shape;
	
	public void execute() {
		int pos = model.getShapes().indexOf(shape);
		Collections.swap(model.getShapes(), pos, pos + 1);
	}

	public void unexecute() {
		int pos = model.getShapes().indexOf(shape);
		Collections.swap(model.getShapes(), pos, pos - 1);
	}
	
	// Getters & setters

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
