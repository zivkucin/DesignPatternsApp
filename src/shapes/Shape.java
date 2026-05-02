package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import drawing.Moveable;

public abstract class Shape implements Moveable, Comparable, Serializable {
	private boolean selected;
	private Color outline = Color.black;
	private Color fill = Color.white;

	public abstract void draw(Graphics g);  
	
	public abstract boolean contains(int x, int y);
	
	public Shape() {
	}
	
	public Shape(boolean selected) {
		super();
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}	
	
	public Color getOutline() {
		return outline;
	}

	public void setOutline(Color outline) {
		this.outline = outline;
	}

	public Color getFill() {
		return fill;
	}

	public void setFill(Color fill) {
		this.fill = fill;
	}


}

