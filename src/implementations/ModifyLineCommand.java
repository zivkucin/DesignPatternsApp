package implementations;

import interfaces.Command;
import shapes.Line;

public class ModifyLineCommand implements Command {

	private Line line;
	private Line modificator;
	Line prototype;
	
	public void execute() {
		line.getStart().setX(modificator.getStart().getX());
		line.getStart().setY(modificator.getStart().getY());
		line.getEnd().setX(modificator.getEnd().getX());
		line.getEnd().setY(modificator.getEnd().getY());
		line.setOutline(modificator.getOutline());
	}
	
	public void unexecute() {
		line.getStart().setX(prototype.getStart().getX());
		line.getStart().setY(prototype.getStart().getY());
		line.getEnd().setX(prototype.getEnd().getX());
		line.getEnd().setY(prototype.getEnd().getY());
		line.setOutline(prototype.getOutline());
	}


	// Getters & setters
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		prototype = line.getProto();
		this.line = line;
	}

	public Line getModificator() {
		return modificator;
	}

	public void setModificator(Line modificator) {
		this.modificator = modificator;
	}
	
}
