package implementations;

import interfaces.Command;
import shapes.Donut;

public class ModifyDonutCommand implements Command {

	private Donut donut;
	private Donut modificator;
	Donut prototype;
	
	public void execute() {
		donut.getCenter().setX(modificator.getCenter().getX());
		donut.getCenter().setY(modificator.getCenter().getY());
		donut.setRadius(modificator.getRadius());
		donut.setInnerRadius(modificator.getInnerRadius());
		donut.setOutline(modificator.getOutline());
		donut.setFill(modificator.getFill());
	}
	
	public void unexecute() {
		donut.getCenter().setX(prototype.getCenter().getX());
		donut.getCenter().setY(prototype.getCenter().getY());
		donut.setRadius(prototype.getRadius());
		donut.setInnerRadius(prototype.getInnerRadius());
		donut.setOutline(prototype.getOutline());
		donut.setFill(prototype.getFill());
	}


	// Getters & setters
	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		prototype = donut.getProto();
		this.donut = donut;
	}

	public Donut getModificator() {
		return modificator;
	}

	public void setModificator(Donut modificator) {
		this.modificator = modificator;
	}
	
	
}
