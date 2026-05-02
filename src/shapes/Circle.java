package shapes;

import java.awt.Color;
import java.awt.Graphics;

import modals.DlgCircle;

public class Circle extends Shape {
	private Point center;
	private int radius;
	private boolean selected;
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Circle() {
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this.center = center;
		this.radius = radius;
		setSelected(selected);
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getFill());
		g.fillOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius() * 2, this.getRadius() *2);
		g.setColor(getOutline());
		
		g.drawOval(this.getCenter().getX()-this.getRadius(), this.getCenter().getY()-this.getRadius(), this.getRadius()*2, this.getRadius()*2);
		if(isSelected()==true) {
			g.setColor(Color.black);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()-3,6,6);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()-this.getRadius()-3,6,6);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()+this.getRadius()-3, 6, 6);
			g.drawRect(this.getCenter().getX()-this.getRadius()-3, this.getCenter().getY()-3,6,6);
			g.drawRect(this.getCenter().getX()+this.getRadius()-3, this.getCenter().getY()-3,6,6);
		}
	}
	@Override
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= this.getRadius();
	}
	@Override
	public void move(int newX, int newY) {
		center.move(newX, newY);		
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString() {
		return "Circle - center = " + center + " , radius= " + radius + " , selected = " + isSelected();
	}
	
	public Circle getProto() {
		Circle c = new Circle(center.getProto(), radius, isSelected());
		c.setFill(getFill());
		c.setOutline(getOutline());
		return c;
	}
}
