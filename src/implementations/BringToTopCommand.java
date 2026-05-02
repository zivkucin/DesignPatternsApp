package implementations;

import interfaces.Command;
import mvc.Model;
import shapes.Shape;

public class BringToTopCommand implements Command {

		private Model model;
		private Shape shape;
		int pos;
		
		public void execute() {
			pos = model.getShapes().indexOf(shape);
			model.getShapes().remove(shape);
			model.getShapes().add(shape);
		}

		public void unexecute() {
			model.getShapes().remove(shape);
			model.getShapes().add(pos, shape);
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
