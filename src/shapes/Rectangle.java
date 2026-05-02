package shapes;

import java.awt.Color;
import java.awt.Graphics;

import modals.DlgRectangle;

public class Rectangle extends Shape{
	private Point upperLeftPoint;
	private int width;
	private int height;
	private boolean selected;

	
	public Rectangle() {
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
		setSelected(selected);
	}

	public int area() {
		return width*height;
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return (int)(this.area()-((Rectangle)o).area());
		}
		return 0;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getFill());
		g.fillRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.getHeight());
		g.setColor(getOutline());
		g.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.getHeight());
		if(isSelected()==true) {
			g.setColor(Color.black);
			g.drawRect(this.getUpperLeftPoint().getX()-3, this.getUpperLeftPoint().getY()-3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX()+this.getWidth()-3, this.getUpperLeftPoint().getY()-3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX()-3, this.getUpperLeftPoint().getY()+this.getHeight()-3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX()+this.getWidth()-3, this.getUpperLeftPoint().getY()+this.getHeight()-3, 6, 6);
		}
	}
	@Override
	public boolean contains(int x, int y) {
		return (this.upperLeftPoint.getX() <= x && x <= this.upperLeftPoint.getX() + width && this.upperLeftPoint.getY() <= y && y <= this.upperLeftPoint.getY() + height);
	}
	@Override
	public void move(int newX, int newY) {
		upperLeftPoint.move(newX, newY);	
	}
	
	public void paintArea(Graphics g) {
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String toString() {
		return "Rectangle - UpperLeft= " + this.upperLeftPoint + " , width= " + this.width + 
				" , height= " + this.height + " , selected= " + isSelected();
	}
	
	public Rectangle getProto() {
		Rectangle r = new Rectangle(upperLeftPoint.getProto(), width, height, selected);
		r.setOutline(getOutline());
		r.setFill(getFill());
		return r;
	}
	
}

