package implementations;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.Strategy;
import shapes.Shape;

public class StrategySerialization implements Strategy {

	private ArrayList<Shape> shapesOnDrawing;
	
	public void save(String file) {
		try  {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(shapesOnDrawing);
			fos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error");
			//Debug
			e.printStackTrace();
		}
	}
	
	// Getters & setters
	
	public ArrayList<Shape> getShapesOnDrawing() {
		return shapesOnDrawing;
	}

	public void setShapesOnDrawing(ArrayList<Shape> shapesOnDrawing) {
		this.shapesOnDrawing = shapesOnDrawing;
	}
	

}
