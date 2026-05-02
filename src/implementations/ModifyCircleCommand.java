package implementations;

import interfaces.Command;
import shapes.Circle;

public class ModifyCircleCommand implements Command {

	private Circle circle;
	private Circle modificator;
	Circle prototype;
	
	public void execute() {
		circle.getCenter().setX(modificator.getCenter().getX());
		circle.getCenter().setY(modificator.getCenter().getY());
		circle.setRadius(modificator.getRadius());
		
		circle.setOutline(modificator.getOutline());
		circle.setFill(modificator.getFill());
	}
	
	public void unexecute() {
		circle.getCenter().setX(prototype.getCenter().getX());
		circle.getCenter().setY(prototype.getCenter().getY());
		circle.setRadius(prototype.getRadius());
		
		circle.setOutline(prototype.getOutline());
		circle.setFill(prototype.getFill());
	}


	// Getters & setters
	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		prototype = circle.getProto();
		this.circle = circle;
	}

	public Circle getModificator() {
		return modificator;
	}

	public void setModificator(Circle modificator) {
		this.modificator = modificator;
	}
	
	
}
