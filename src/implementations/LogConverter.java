package implementations;

import java.awt.Color;
import java.util.ArrayList;

import interfaces.Command;
import mvc.Model;
import shapes.Circle;
import shapes.Donut;
import shapes.Hexagon;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class LogConverter {
	
	private Model model;
	
	public Command convert(String log) {
		if (log.startsWith("New point is drawn")) {
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int rgb = Integer.parseInt(data[3]);
			Point p = new Point(x, y);
			p.setOutline(new Color(rgb));
			NewShapeCommand newShapeCommand = new NewShapeCommand();
			newShapeCommand.setModel(model);
			newShapeCommand.setShape(p);
			return newShapeCommand;
		}else if (log.startsWith("New line is drawn")) {
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int xE = Integer.parseInt(data[3]);
			int yE = Integer.parseInt(data[4]);
			int rgb = Integer.parseInt(data[5]);
			Point p = new Point(x, y);
			Point pE = new Point(xE, yE);
			Line line = new Line(p, pE);
			line.setOutline(new Color(rgb));
			NewShapeCommand newShapeCommand = new NewShapeCommand();
			newShapeCommand.setModel(model);
			newShapeCommand.setShape(line);
			return newShapeCommand;
		} else if (log.startsWith("New circle is drawn")) {
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int r = Integer.parseInt(data[3]);
			int rgb = Integer.parseInt(data[4]);
			int rgbF = Integer.parseInt(data[5]);
			Point p = new Point(x, y);
			Circle circle = new Circle(p, r);
			circle.setOutline(new Color(rgb));
			circle.setFill(new Color(rgbF));
			NewShapeCommand newShapeCommand = new NewShapeCommand();
			newShapeCommand.setModel(model);
			newShapeCommand.setShape(circle);
			return newShapeCommand;
		}else if (log.startsWith("New donut is drawn")) {
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int r = Integer.parseInt(data[3]);
			int rI = Integer.parseInt(data[4]);
			int rgb = Integer.parseInt(data[5]);
			int rgbF = Integer.parseInt(data[6]);
			Point p = new Point(x, y);
			Donut donut = new Donut(p, r, rI);
			donut.setOutline(new Color(rgb));
			donut.setFill(new Color(rgbF));
			NewShapeCommand newShapeCommand = new NewShapeCommand();
			newShapeCommand.setModel(model);
			newShapeCommand.setShape(donut);
			return newShapeCommand;
		}else if (log.startsWith("New rectangle is drawn")) {
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int w = Integer.parseInt(data[3]);
			int h = Integer.parseInt(data[4]);
			int rgb = Integer.parseInt(data[5]);
			int rgbF = Integer.parseInt(data[6]);
			Point p = new Point(x, y);
			Rectangle rectangle = new Rectangle(p, w, h);
			rectangle.setOutline(new Color(rgb));
			rectangle.setFill(new Color(rgbF));
			NewShapeCommand newShapeCommand = new NewShapeCommand();
			newShapeCommand.setModel(model);
			newShapeCommand.setShape(rectangle);
			return newShapeCommand;
		}else if (log.startsWith("New hexagon is drawn")) {
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int r = Integer.parseInt(data[3]);
			int rgb = Integer.parseInt(data[4]);
			int rgbF = Integer.parseInt(data[5]);
			Hexagon hexagon = new Hexagon(x, y, r);
			hexagon.setOutline(new Color(rgb));
			hexagon.setFill(new Color(rgbF));
			NewShapeCommand newShapeCommand = new NewShapeCommand();
			newShapeCommand.setModel(model);
			newShapeCommand.setShape(hexagon);
			return newShapeCommand;
		} else if (log.startsWith("Shape is selected")) {
			String[] data = log.split(" ");
			int position = Integer.parseInt(data[5]);
			int index = position - 1;
			Shape shape = model.getShapes().get(index);
			SelectCommand selectCommand = new SelectCommand();
			selectCommand.setShape(shape);
			return selectCommand;
		} else if (log.startsWith("Selected shape is unselected")) {
			String[] data = log.split(" ");
			int position = Integer.parseInt(data[6]);
			int index = position - 1;
			Shape shape = model.getShapes().get(index);
			UnselectCommand unselectCommand = new UnselectCommand();
			unselectCommand.setShape(shape);
			return unselectCommand;
		} else if (log.equals("Selected shapes are unselected")) {
			int max = model.getShapes().size() - 1;
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			for (int k=max; k>=0; k--) {
				Shape s=model.getShapes().get(k);
				if(s.isSelected()) {
					shapes.add(s);
				}
			}
			
			UnselectGroupCommand unselectGroupCommand = new UnselectGroupCommand();
			unselectGroupCommand.setShapes(shapes);
			return unselectGroupCommand;
		} else if (log.equals("Selected shape is moved to bottom")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
		
			BringToBottomCommand bringToBottomCommand = new BringToBottomCommand();
			bringToBottomCommand.setModel(model);
			bringToBottomCommand.setShape(selected);
			return bringToBottomCommand;
		} else if (log.equals("Selected shape is moved to top")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			BringToTopCommand bringToTopCommand = new BringToTopCommand();
			bringToTopCommand.setModel(model);
			bringToTopCommand.setShape(selected);
			return bringToTopCommand;
		}else if (log.equals("Selected shape is moved down")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			DownCommand downCommand = new DownCommand();
			downCommand.setModel(model);
			downCommand.setShape(selected);
			return downCommand;
		} else if (log.equals("Selected shape is moved up")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			UpCommand upCommand = new UpCommand();
			upCommand.setModel(model);
			upCommand.setShape(selected);
			return upCommand;
		}else if (log.startsWith("Selected hexagon is modified")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int r = Integer.parseInt(data[3]);
			int rgb = Integer.parseInt(data[4]);
			int rgbF = Integer.parseInt(data[5]);
			Hexagon hexagon = new Hexagon(x, y, r);
			hexagon.setOutline(new Color(rgb));
			hexagon.setFill(new Color(rgbF));
			
			ModifyHexagonCommand modifyHexagonCommand = new ModifyHexagonCommand();
			modifyHexagonCommand.setHexagon((Hexagon)selected);
			modifyHexagonCommand.setModificator(hexagon);
			return modifyHexagonCommand;
		} else if (log.startsWith("Selected rectangle is modified")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int w = Integer.parseInt(data[3]);
			int h = Integer.parseInt(data[4]);
			int rgb = Integer.parseInt(data[5]);
			int rgbF = Integer.parseInt(data[6]);
			Point p = new Point(x, y);
			Rectangle rectangle = new Rectangle(p, w, h);
			rectangle.setOutline(new Color(rgb));
			rectangle.setFill(new Color(rgbF));
			
			ModifyRectangleCommand modifyRectangleCommand = new ModifyRectangleCommand();
			modifyRectangleCommand.setRectangle((Rectangle) selected);
			modifyRectangleCommand.setModificator(rectangle);
			return modifyRectangleCommand;
		}  else if (log.startsWith("Selected circle is modified")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int r = Integer.parseInt(data[3]);
			int rgb = Integer.parseInt(data[4]);
			int rgbF = Integer.parseInt(data[5]);
			Point p = new Point(x, y);
			Circle circle = new Circle(p, r);
			circle.setOutline(new Color(rgb));
			circle.setFill(new Color(rgbF));
			
			ModifyCircleCommand modifyCircleCommand = new ModifyCircleCommand();
			modifyCircleCommand.setCircle((Circle)selected);
			modifyCircleCommand.setModificator(circle);
			return modifyCircleCommand;
		} else if (log.startsWith("Selected line is modified")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int xE = Integer.parseInt(data[3]);
			int yE = Integer.parseInt(data[4]);
			int rgb = Integer.parseInt(data[5]);
			Point p = new Point(x, y);
			Point pE = new Point(xE, yE);
			Line line = new Line(p, pE);
			line.setOutline(new Color(rgb));
			
			ModifyLineCommand modifyLineCommand = new ModifyLineCommand();
			modifyLineCommand.setLine((Line)selected);
			modifyLineCommand.setModificator(line);
			return modifyLineCommand;
			
		} else if (log.startsWith("Selected point is modified")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int rgb = Integer.parseInt(data[3]);
			Point p = new Point(x, y);
			p.setOutline(new Color(rgb));
			
			ModifyPointCommand modifyPointCommand = new ModifyPointCommand();
			modifyPointCommand.setPoint((Point)selected);
			modifyPointCommand.setModificator(p);
			return modifyPointCommand;
		} else if (log.startsWith("Selected donut is modified")) {
			int max= model.getShapes().size() - 1;
			Shape selected=null;
			for (int k=0;k<=max;k++) {
				Shape s=model.getShapes().get(k);
				if (s.isSelected()) {
					selected = s;
				}
			}
			
			String[] data = log.split("/");
			int x = Integer.parseInt(data[1]);
			int y = Integer.parseInt(data[2]);
			int r = Integer.parseInt(data[3]);
			int r2 = Integer.parseInt(data[4]);
			int rgb = Integer.parseInt(data[5]);
			int rgbF = Integer.parseInt(data[6]);
			Point p = new Point(x, y);
			Donut donut = new Donut(p, r, r2);
			donut.setOutline(new Color(rgb));
			donut.setFill(new Color(rgbF));
			
			ModifyDonutCommand modifyDonutCommand = new ModifyDonutCommand();
			modifyDonutCommand.setDonut((Donut)selected);
			modifyDonutCommand.setModificator(donut);
			return modifyDonutCommand;
		} else if (log.startsWith("Selected shapes are deleted")) {
			int max = model.getShapes().size() - 1;
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			for (int k=0; k<max; k++) {
				Shape s=model.getShapes().get(k);
				if(s.isSelected()) {
					shapes.add(s);
				}
			}
			
			DeleteCommand deleteCommand = new DeleteCommand();
			deleteCommand.setModel(model);
			deleteCommand.setShapes(shapes);
			return deleteCommand;
		}
		
		return null;
	}
	
	// Getters & setters

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	
}
