package implementations;

import java.util.ArrayList;

import interfaces.Command;
import shapes.Shape;

public class UnselectGroupCommand implements Command {

	private ArrayList<Shape> shapes;
	
	public void execute() {
		for (int k = 0; k < shapes.size(); k++)
			shapes.get(k).setSelected(false);
	}

	public void unexecute() {
		for (int k = 0; k < shapes.size(); k++)
			shapes.get(k).setSelected(true);
	}
	
	// Getters & setters

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	
}
