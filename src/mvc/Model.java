package mvc;

import java.util.ArrayList;

import shapes.Shape;

public class Model {

	private ArrayList<Shape> shapes = new ArrayList<Shape>(); 
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
}
