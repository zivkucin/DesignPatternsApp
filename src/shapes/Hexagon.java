package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Hexagon extends Shape {

	private hexagon.Hexagon h;
	
	public Hexagon(int x, int y, int r) {
		h = new hexagon.Hexagon(x, y, r);
	}
	
	public boolean contains(int x, int y) {
		boolean contains = h.doesContain(x, y);
		return contains;
	}
	
	public void setCenterX(int x) {
		h.setX(x);
	}
	
	public void setCenterY(int y) {
		h.setY(y);
	}
	
	public void setR(int r) {
		h.setR(r);
	}

	public void draw(Graphics g) {
		h.paint(g);
	}
	
	public int getCenterX() {
		return h.getX();
	}
	
	public int getCenterY() {
		return h.getY();
	}
	
	public int getR() {
		return h.getR();
	}
	
	// Overrides
	
	@Override
	public void setOutline(Color outline) {
		h.setBorderColor(outline);
	}
	
	@Override
	public void setFill(Color fill) {
		h.setAreaColor(fill);
	}
	
	@Override
	public Color getOutline() {
		return h.getBorderColor();
	}
	
	@Override
	public Color getFill() {
		return h.getAreaColor();
	}
	
	@Override
	public boolean isSelected() {
		return h.isSelected();
	}
	
	@Override
	public void setSelected(boolean selected) {
		h.setSelected(selected);
	}
	
	// Later
	public void move(int newX, int newY) {
	}

	public int compareTo(Object o) {
		return 0;
	}
	
	public Hexagon getProto() {
		Hexagon h = new Hexagon(getCenterX(), getCenterY(), getR());
		h.setOutline(getOutline());
		h.setFill(getFill());
		return h;
	}
}
