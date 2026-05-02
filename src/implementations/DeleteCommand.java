package implementations;

import java.util.ArrayList;

import interfaces.Command;
import mvc.Model;
import shapes.Shape;

public class DeleteCommand implements Command {

	private ArrayList<Shape> shapes;
	private Model model;
	ArrayList<Integer> indices;
	
	public void execute() {
		indices = new ArrayList<Integer>();
		for (int k = 0; k < shapes.size(); k++)
			indices.add(model.getShapes().indexOf(shapes.get(k)));
		for (int k = 0; k < shapes.size(); k++)
			model.getShapes().remove(shapes.get(k));
	}

	public void unexecute() {
		for (int k = 0; k < indices.size(); k++) {
			model.getShapes().add(indices.get(k), shapes.get(k));
		}
	}
	
	// Getters & setters

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
