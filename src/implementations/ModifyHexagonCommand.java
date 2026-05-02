package implementations;

import interfaces.Command;
import shapes.Hexagon;

public class ModifyHexagonCommand implements Command {
	
	private Hexagon hexagon;
	private Hexagon modificator;
	Hexagon prototype;
	
	public void execute() {
		hexagon.setCenterX(modificator.getCenterX());
		hexagon.setCenterY(modificator.getCenterY());
		hexagon.setR(modificator.getR());
		hexagon.setOutline(modificator.getOutline());
		hexagon.setFill(modificator.getFill());
	}
	
	public void unexecute() {
		hexagon.setCenterX(prototype.getCenterX());
		hexagon.setCenterY(prototype.getCenterY());
		hexagon.setR(prototype.getR());
		hexagon.setOutline(prototype.getOutline());
		hexagon.setFill(prototype.getFill());
	}


	// Getters & setters
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		prototype = hexagon.getProto();
		this.hexagon = hexagon;
	}

	public Hexagon getModificator() {
		return modificator;
	}

	public void setModificator(Hexagon modificator) {
		this.modificator = modificator;
	}
}
