package implementations;


import interfaces.Command;
import shapes.Point;

public class ModifyPointCommand implements Command {

	
	private Point point;
	private Point modificator;
	Point prototype;
	
	public void execute() {
		point.setX(modificator.getX());
		point.setY(modificator.getY());
		point.setOutline(modificator.getOutline());
	}
	
	public void unexecute() {
		point.setX(prototype.getX());
		point.setY(prototype.getY());
		point.setOutline(prototype.getOutline());
	}


	// Getters & setters
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		prototype = point.getProto();
		this.point = point;
	}

	public Point getModificator() {
		return modificator;
	}

	public void setModificator(Point modificator) {
		this.modificator = modificator;
	}
		

}
