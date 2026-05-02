package shapes;

import java.awt.Color;
import java.awt.Graphics;

import modals.DlgLine;

public class Line extends Shape{
	private Point start;
	private Point end;
	private boolean selected;
	
	public Point getStart() {
		return start;
	}
	
	public void setStart(Point start) {
		this.start = start;
	}
	public Point getEnd() {
		return end;
	}
	public void setEnd(Point end) {
		this.end = end;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Point middleOfLine() {
		int middleX = (this.getStart().getX() + this.getEnd().getX())/2;
		int middleY = (this.getStart().getY() + this.getEnd().getY())/2;
		Point middle = new Point(middleX, middleY);
		return middle;
	}
	
	public Line() {
	}
	
	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public Line(Point start, Point end, boolean selected) {
		this(start, end);
		setSelected(selected);
	}
	
	public double lenght() {
		return start.distance(end.getX(), end.getY());
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(getOutline());
		g.drawLine(this.getStart().getX(), this.getStart().getY(), this.getEnd().getX(), this.getEnd().getY());
		if(isSelected()==true) {
			g.setColor(Color.black);
			g.drawRect(this.getStart().getX()-3, this.getStart().getY()-3, 6, 6);
			g.drawRect(this.getEnd().getX()-3, this.getEnd().getY()-3,6,6);
			g.drawRect(this.middleOfLine().getX()-3, this.middleOfLine().getY()-3,6,6);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return (start.distance(x,y) + end.distance(x, y)) - lenght() < 0.1;
	}

	@Override
	public void move(int newX, int newY) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "Line - start= " + start + " , end= " + end + " , selected= " + isSelected();
	}
	
	public Line getProto() {
		Line l = new Line(start.getProto(), end.getProto(), isSelected());
		l.setOutline(getOutline());
		return l;
	}
}
