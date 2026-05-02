package implementations;

import interfaces.Command;
import mvc.Model;
import shapes.Shape;

public class NewShapeCommand implements Command {
	
	private Model model;
	private Shape shape;
	
	public void execute() {
		model.getShapes().add(shape);
	}
	
	public void unexecute() {
		model.getShapes().remove(shape);
	}


	
	//Getters & setters
	
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
