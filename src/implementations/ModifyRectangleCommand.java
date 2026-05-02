package implementations;

import interfaces.Command;
import shapes.Rectangle;

public class ModifyRectangleCommand implements Command {

	private Rectangle rectangle;
	private Rectangle modificator;
	Rectangle prototype;
	
	public void execute() {
		rectangle.getUpperLeftPoint().setX(modificator.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(modificator.getUpperLeftPoint().getY());
		rectangle.setHeight(modificator.getHeight());
		rectangle.setWidth(modificator.getWidth());
		rectangle.setOutline(modificator.getOutline());
		rectangle.setFill(modificator.getFill());
	}
	
	public void unexecute() {
		rectangle.getUpperLeftPoint().setX(prototype.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(prototype.getUpperLeftPoint().getY());
		rectangle.setHeight(prototype.getHeight());
		rectangle.setWidth(prototype.getWidth());
		rectangle.setOutline(prototype.getOutline());
		rectangle.setFill(prototype.getFill());
	}


	// Getters & setters
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		prototype = rectangle.getProto();
		this.rectangle = rectangle;
	}

	public Rectangle getModificator() {
		return modificator;
	}

	public void setModificator(Rectangle modificator) {
		this.modificator = modificator;
	}
	
}
