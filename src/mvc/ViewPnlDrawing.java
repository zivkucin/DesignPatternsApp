package mvc;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import modals.DlgCircle;
import modals.DlgDonut;
import modals.DlgRectangle;
import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

import java.util.Iterator;

public class ViewPnlDrawing extends JPanel {
	
	private Model model;
	
	public ViewPnlDrawing (Model model) {
		super();
		this.model = model;
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (Shape shape : model.getShapes()) {
			shape.draw(g);
		}
	}
	
}

