package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import modals.DlgDonut;

public class Donut extends Circle {
	
	private int innerRadius;

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public Donut() {		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected); 
	}
	

	public boolean contains(int x, int y) {
		return super.contains(x, y) && this.getCenter().distance(x, y) >= this.getInnerRadius();
	}
	
	public String toString() {
		return "Donut - InnerRadius= " + innerRadius + " , Outer color " + super.toString();
	}
	
	public void draw(Graphics g) {
		//super.draw(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
        
		java.awt.Shape ell1 = new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), getRadius() + getRadius(),getRadius() +getRadius());
        java.awt.Shape ell2 = new Ellipse2D.Double(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, getInnerRadius() + getInnerRadius(),getInnerRadius() + getInnerRadius());
        Area mainArea = new Area( ell1 );
        Area inneArea = new Area(ell2);
        mainArea.subtract( inneArea );
        g2d.setColor(getFill());
        g2d.fill(mainArea);
        
        g2d.setColor(getOutline());
        g2d.draw(mainArea);
        
        g2d.dispose();
        
        if(isSelected()) {
        	drawBlueSquares(g);
        }
	}
	
	public void drawBlueSquares(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getCenter().getX() -3, getCenter().getY()-3, 6, 6);
		g.drawRect(getCenter().getX() -3, getCenter().getY()-getRadius()-3, 6, 6);
		g.drawRect(getCenter().getX() -3, getCenter().getY()+getRadius()-3, 6, 6);
		g.drawRect(getCenter().getX()+getRadius() -3, getCenter().getY()-3, 6, 6);
		g.drawRect(getCenter().getX()-getRadius() -3, getCenter().getY()-3, 6, 6);
    	
		g.drawRect(getCenter().getX() + getInnerRadius() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() - getInnerRadius() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() + getInnerRadius() - 3, 6, 6);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() - getInnerRadius() - 3, 6, 6);
	}
	
	public Donut getProto() {
		Donut c = new Donut(getCenter().getProto(), getRadius(), getInnerRadius(), isSelected());
		c.setFill(getFill());
		c.setOutline(getOutline());
		return c;
	}
}
