package mvc;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import implementations.BringToBottomCommand;
import implementations.BringToTopCommand;
import implementations.DeleteCommand;
import implementations.DownCommand;
import implementations.LogConverter;
import implementations.ModifyCircleCommand;
import implementations.ModifyDonutCommand;
import implementations.ModifyHexagonCommand;
import implementations.ModifyLineCommand;
import implementations.ModifyPointCommand;
import implementations.ModifyRectangleCommand;
import implementations.NewShapeCommand;
import implementations.SelectCommand;
import implementations.StrategySerialization;
import implementations.UnselectCommand;
import implementations.UnselectGroupCommand;
import implementations.UpCommand;
import interfaces.Command;
import interfaces.Strategy;
import modals.DlgCircle;
import modals.DlgDonut;
import modals.DlgHexagon;
import modals.DlgLine;
import modals.DlgPoint;
import modals.DlgRectangle;
import shapes.Circle;
import shapes.Donut;
import shapes.Hexagon;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class Controller extends Observable {

	private Model model;
	private ViewDrawingFrm frm;
	
	ArrayList<String> logsBuffer = new ArrayList<String>();
	ArrayList<Command> undoBuffer = new ArrayList<Command>();
	ArrayList<Command> redoBuffer = new ArrayList<Command>();
	
	public Controller(Model model, ViewDrawingFrm frm) {
		super();
		this.model = model;
		this.frm = frm;
		
		frm.setController(this);
		addObserver(frm);
		notifyObserversCustom();
	}

	public void delete() {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for (int k=0; k<model.getShapes().size();k++) {
			if (model.getShapes().get(k).isSelected()) {
				shapes.add(model.getShapes().get(k));
			}
		}
		
		DeleteCommand deleteCommand = new DeleteCommand();
		undoBuffer.add(deleteCommand);
		redoBuffer.clear();
		deleteCommand.setModel(model);
		deleteCommand.setShapes(shapes);
		deleteCommand.execute();
		frm.addLog("Selected shapes are deleted");
		notifyObserversCustom();
	}
	
	public void pnlDrawingMouseClick(MouseEvent event, String shapeToDraw,
			Color outline, Color area, Point firstLinePoint) {
		int x=event.getX();
		int y=event.getY();
		
		NewShapeCommand newShapeCommand = new NewShapeCommand();
		newShapeCommand.setModel(model);
		
		switch(shapeToDraw) {
			case "point":
			Point p = new Point(x,y,false);
			p.setOutline(outline);
			
			newShapeCommand.setShape(p);
			newShapeCommand.execute();
			undoBuffer.add(newShapeCommand);
			redoBuffer.clear();
			frm.addLog("New point is drawn/" + p.getX() + "/" + p.getY() + "/" + p.getOutline().getRGB());
			
			break;
	
			case "line":
			if (firstLinePoint.getX() != -1) {
				Point startLine = new Point(firstLinePoint.getX(), firstLinePoint.getY());
				Point endLine = new Point(x,y,false);
				Line line = new Line (startLine, endLine, false);
				line.setOutline(outline);
				newShapeCommand.setShape(line);
				newShapeCommand.execute();
				undoBuffer.add(newShapeCommand);
				redoBuffer.clear();
				frm.addLog("New line is drawn/" + line.getStart().getX() + "/" + line.getStart().getY() + "/" + line.getEnd().getX() + "/" + line.getEnd().getY() + "/"+ line.getOutline().getRGB());
				firstLinePoint.setX(-1);
				firstLinePoint.setY(-1);
			} else {
				firstLinePoint.setX(x);
				firstLinePoint.setY(y);
			}
			break; 
		
			case "circle":
				DlgCircle dlgCircle = new DlgCircle();
				dlgCircle.getTxtXCoord().setText(String.valueOf(x));
				dlgCircle.getTxtYCoord().setText(String.valueOf(y));
				dlgCircle.getTxtYCoord().setEditable(false);
				dlgCircle.getTxtXCoord().setEditable(false);
				dlgCircle.setVisible(true);
					if(dlgCircle.isOk == true) {
						Point center=new Point(x, y,false);
						int r=Integer.parseInt(dlgCircle.getTxtRadius().getText());
						Circle c = new Circle(center,r,false);
						c.setOutline(outline);
						c.setFill(area);
						if(dlgCircle.isOutlineBool()) {
							c.setOutline(dlgCircle.getOutline());
						}
						if(dlgCircle.isAreaBool()) {
							c.setFill(dlgCircle.getArea());
						}
						
						
						newShapeCommand.setShape(c);
						newShapeCommand.execute();
						undoBuffer.add(newShapeCommand);
						redoBuffer.clear();
						frm.addLog("New circle is drawn/" + c.getCenter().getX() + "/" + c.getCenter().getY() + "/" + c.getRadius() + "/" + c.getOutline().getRGB() + "/"+ c.getFill().getRGB());
					}
				break;
			
			case "donut":
				DlgDonut dlgDonut = new DlgDonut();
				dlgDonut.getTxtXCoord().setText(String.valueOf(x));
				dlgDonut.getTxtYCoord().setText(String.valueOf(y));
				dlgDonut.getTxtXCoord().setEditable(false);
				dlgDonut.getTxtYCoord().setEditable(false);
				dlgDonut.setVisible(true);
				if(dlgDonut.isOk == true) {
					Point c=new Point(x, y,false);
					int bigRadius=Integer.parseInt(dlgDonut.getTxtRadius().getText());
					int smallRadius=Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
					Donut d = new Donut(c, bigRadius, smallRadius, false);
					d.setOutline(outline);
					d.setFill(area);
					
					if(dlgDonut.isOutlineBool()) {
						d.setOutline(dlgDonut.getOutline());
					}
					if(dlgDonut.isAreaBool()) {
						d.setFill(dlgDonut.getArea());
					}
					
					newShapeCommand.setShape(d);
					newShapeCommand.execute();
					undoBuffer.add(newShapeCommand);
					redoBuffer.clear();
					frm.addLog("New donut is drawn/" + d.getCenter().getX() + "/" + d.getCenter().getY() + "/" + d.getRadius() + "/" + d.getInnerRadius() + "/" + d.getOutline().getRGB() + "/"+ d.getFill().getRGB());
				}
				break;
				
		case "rectangle":
			DlgRectangle dlgRectangle = new DlgRectangle();
			dlgRectangle.getTxtXCoord().setText(String.valueOf(x));
			dlgRectangle.getTxtYCoord().setText(String.valueOf(y));
			dlgRectangle.getTxtYCoord().setEditable(false);
			dlgRectangle.getTxtXCoord().setEditable(false);
			dlgRectangle.setVisible(true);
				if(dlgRectangle.isOk == true) {
					Point upperLeft = new Point(x,y,false);
					int width=Integer.parseInt(dlgRectangle.getTxtWidth().getText());
					int height=Integer.parseInt(dlgRectangle.getTxtHeight().getText());
					Rectangle r = new Rectangle(upperLeft,width,height, false);
					r.setOutline(outline);
					r.setFill(area);
					
					if(dlgRectangle.isOutlineBool()) {
						r.setOutline(dlgRectangle.getOutline());
					}
					if(dlgRectangle.isAreaBool()) {
						r.setFill(dlgRectangle.getArea());
					}
					
					newShapeCommand.setShape(r);
					newShapeCommand.execute();
					undoBuffer.add(newShapeCommand);
					redoBuffer.clear();
					frm.addLog("New rectangle is drawn/" + r.getUpperLeftPoint().getX() + "/" + r.getUpperLeftPoint().getY() + "/" + r.getWidth() + "/" + r.getHeight() + "/" + r.getOutline().getRGB() + "/" + r.getFill().getRGB());
				}
			break; 
			
		case "hexagon":
			DlgHexagon dlgHexagon = new DlgHexagon();
			dlgHexagon.getTxtXCoord().setText(String.valueOf(x));
			dlgHexagon.getTxtYCoord().setText(String.valueOf(y));
			dlgHexagon.getTxtYCoord().setEditable(false);
			dlgHexagon.getTxtXCoord().setEditable(false);
			dlgHexagon.setVisible(true);
				if(dlgHexagon.isOk == true) {
					int r=Integer.parseInt(dlgHexagon.getTxtRadius().getText());
					Hexagon h = new Hexagon(x,y,r);
					h.setOutline(outline);
					h.setFill(area);
					
					if(dlgHexagon.isOutlineBool()) {
						h.setOutline(dlgHexagon.getOutline());
					}
					if(dlgHexagon.isAreaBool()) {
						h.setFill(dlgHexagon.getArea());
					}
					
					newShapeCommand.setShape(h);
					newShapeCommand.execute();
					undoBuffer.add(newShapeCommand);
					redoBuffer.clear();
					frm.addLog("New hexagon is drawn/" + h.getCenterX() + "/" + h.getCenterY() + "/" + h.getR() + "/" + h.getOutline().getRGB() + "/"+ h.getFill().getRGB());
				}
			break; 
		
		default:
			int max= model.getShapes().size() - 1;
			
			Shape shapeContains = null;
			for (int k=max; k>=0; k--) {
				Shape s=model.getShapes().get(k);
				if(s.contains(x, y)) {
					shapeContains = s;
					break;
				}
			}
			
			if (shapeContains == null) {
				ArrayList<Shape> shapes = new ArrayList<Shape>();
				for (int k=max; k>=0; k--) {
					Shape s=model.getShapes().get(k);
					if(s.isSelected()) {
						shapes.add(s);
					}
				}
				
				if (shapes.size() > 0) {
					UnselectGroupCommand unselectGroupCommand = new UnselectGroupCommand();
					undoBuffer.add(unselectGroupCommand);
					redoBuffer.clear();
					unselectGroupCommand.setShapes(shapes);
					unselectGroupCommand.execute();
					frm.addLog("Selected shapes are unselected");
				}
				
				//unselect
			} else {
				if (shapeContains.isSelected()) {
					UnselectCommand unselectCommand = new UnselectCommand();
					undoBuffer.add(unselectCommand);
					redoBuffer.clear();
					unselectCommand.setShape(shapeContains);
					unselectCommand.execute();
					frm.addLog("Selected shape is unselected on position " + ((model.getShapes().indexOf(shapeContains)) + 1));
				} else {
					SelectCommand selectCommand = new SelectCommand();
					undoBuffer.add(selectCommand);
					redoBuffer.clear();
					selectCommand.setShape(shapeContains);
					selectCommand.execute();
					frm.addLog("Shape is selected on position " + ((model.getShapes().indexOf(shapeContains)) + 1));
				}
			}
			
			break;
		}
		
		notifyObserversCustom();
	}
	
	private void notifyObserversCustom() {
		int count = 0;
		for (int k=model.getShapes().size() - 1; k>=0; k--) {
			Shape s=model.getShapes().get(k);
			if(s.isSelected()) {
				count++;
			}
		}
		
		ArrayList<Boolean> switches = new ArrayList<Boolean>();
		switches.add(count == 1);
		switches.add(count > 0);
		switches.add(undoBuffer.size() != 0);
		switches.add(redoBuffer.size() != 0);
		switches.add(logsBuffer.size() != 0);
		
		setChanged();
		notifyObservers(switches);
		
	}
	
	public void modifyShape() {
		int max= model.getShapes().size() - 1;
		Shape selected=null;
		for (int k=0;k<=max;k++) {
			Shape s=model.getShapes().get(k);
			if (s.isSelected()) {
				selected = s;
			}
		}
		
		if (selected == null) return;
		
		if (selected instanceof Point) {
			Point p = (Point)selected;
			DlgPoint dlg = new DlgPoint();
			Point modificator = new Point(p.getX(), p.getY());
			modificator.setOutline(p.getOutline());
			dlg.setPointToEdit(modificator);
			dlg.setVisible(true);
			ModifyPointCommand modifyPointCommand = new ModifyPointCommand();
			undoBuffer.add(modifyPointCommand);
			redoBuffer.clear();
			modifyPointCommand.setPoint(p);
			modifyPointCommand.setModificator(modificator);
			modifyPointCommand.execute();
			frm.addLog("Selected point is modified/" + p.getX() + "/" + p.getY() + "/" + p.getOutline().getRGB());
		} else if (selected instanceof Line) {
			Line l = (Line)selected;
			DlgLine dlg = new DlgLine();
			
			Line modificator = new Line(new Point(l.getStart().getX(), l.getStart().getY()), new Point(l.getEnd().getX(), l.getEnd().getY()));
			modificator.setOutline(l.getOutline());
			
			dlg.setLineToEdit(modificator);
			dlg.setVisible(true);
			
			ModifyLineCommand modifyLineCommand = new ModifyLineCommand();
			undoBuffer.add(modifyLineCommand);
			redoBuffer.clear();
			modifyLineCommand.setLine(l);
			modifyLineCommand.setModificator(modificator);
			modifyLineCommand.execute();
			frm.addLog("Selected line is modified/" + l.getStart().getX() + "/" + l.getStart().getY() + "/" + l.getEnd().getX() + "/" + l.getEnd().getY() + "/"+ l.getOutline().getRGB());
		} else if (selected instanceof Donut) {
			Donut d = (Donut)selected;
			DlgDonut dlg = new DlgDonut();
			Donut modificator = new Donut(new Point(d.getCenter().getX(), d.getCenter().getY()),d.getRadius(), d.getInnerRadius());
			modificator.setOutline(d.getOutline());
			modificator.setFill(d.getFill());
			dlg.setDonutToEdit(modificator);
			dlg.setVisible(true);
			ModifyDonutCommand modifyDonutCommand = new ModifyDonutCommand();
			undoBuffer.add(modifyDonutCommand);
			redoBuffer.clear();
			modifyDonutCommand.setDonut(d);
			modifyDonutCommand.setModificator(modificator);
			modifyDonutCommand.execute();
			frm.addLog("Selected donut is modified/" + d.getCenter().getX() + "/" + d.getCenter().getY() + "/" + d.getRadius() + "/" + d.getInnerRadius() + "/" + d.getOutline().getRGB() + "/"+ d.getFill().getRGB());
		} else if (selected instanceof Circle) {
			Circle c = (Circle)selected;
			DlgCircle dlg = new DlgCircle();
			
			Circle modificator = new Circle(new Point(c.getCenter().getX(), c.getCenter().getY()),c.getRadius());
			modificator.setOutline(c.getOutline());
			modificator.setFill(c.getFill());
			
			dlg.setCircleToEdit(modificator);
			dlg.setVisible(true);
			
			ModifyCircleCommand modifyCircleCommand = new ModifyCircleCommand();
			undoBuffer.add(modifyCircleCommand);
			redoBuffer.clear();
			modifyCircleCommand.setCircle(c);
			modifyCircleCommand.setModificator(modificator);
			modifyCircleCommand.execute();
			frm.addLog("Selected circle is modified/" + c.getCenter().getX() + "/" + c.getCenter().getY() + "/" + c.getRadius() + "/" + c.getOutline().getRGB() + "/"+ c.getFill().getRGB());
		} else if (selected instanceof Rectangle) {
			Rectangle r = (Rectangle)selected;
			DlgRectangle dlg = new DlgRectangle();
			
			Rectangle modificator = new Rectangle(new Point(r.getUpperLeftPoint().getX(), r.getUpperLeftPoint().getY()),r.getWidth(), r.getHeight());
			modificator.setOutline(r.getOutline());
			
			modificator.setFill(r.getFill());
			
			dlg.setRectangleToEdit(modificator);
			dlg.setVisible(true);
			
			ModifyRectangleCommand modifyRectangleCommand = new ModifyRectangleCommand();
			undoBuffer.add(modifyRectangleCommand);
			redoBuffer.clear();
			modifyRectangleCommand.setRectangle(r);
			modifyRectangleCommand.setModificator(modificator);
			modifyRectangleCommand.execute();
			frm.addLog("Selected rectangle is modified/" + r.getUpperLeftPoint().getX() + "/" + r.getUpperLeftPoint().getY() + "/" + r.getWidth() + "/" + r.getHeight() + "/" + r.getOutline().getRGB() + "/" + r.getFill().getRGB());
		} else if (selected instanceof Hexagon) {
			Hexagon  h = (Hexagon)selected;
			DlgHexagon dlg = new DlgHexagon();
			Hexagon modificator = new Hexagon(h.getCenterX(), h.getCenterY(),h.getR());
			modificator.setOutline(h.getOutline());
			modificator.setFill(h.getFill());
			
			dlg.setHexagonToEdit(modificator);
			dlg.setVisible(true);
			
			ModifyHexagonCommand modifyHexagonCommand = new ModifyHexagonCommand();
			undoBuffer.add(modifyHexagonCommand);
			redoBuffer.clear();
			modifyHexagonCommand.setHexagon(h);
			modifyHexagonCommand.setModificator(modificator);
			modifyHexagonCommand.execute();
			
			frm.addLog("Selected hexagon is modified/" +  + h.getCenterX() + "/" + h.getCenterY() + "/" + h.getR() + "/" + h.getOutline().getRGB() + "/"+ h.getFill().getRGB());
		}
		
		notifyObserversCustom();
	}

	public void save(Strategy strategy) {
		
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("C:\\Users\\Public"));
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        strategy.save(chooser.getSelectedFile().getAbsolutePath());
	    }
		
	}

	public ArrayList<Shape> getShapesForSave() {
		return model.getShapes();
	}

	public void deserialize() {
		
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("C:\\Users\\Public"));
	    int retrival = chooser.showOpenDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        
	    	FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
				ObjectInputStream in = new ObjectInputStream(fileIn);
		    	ArrayList<Shape> o = (ArrayList<Shape>) in.readObject();
		    	model.getShapes().clear();
		    	for (int i = 0; i < o.size(); i++) {
		    		model.getShapes().add(o.get(i));
		    	}
		    	
		    	frm.resetLogs();
		    	notifyObserversCustom();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(chooser, "Error");
				//Debug
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(chooser, "Content is wrong");
				//Debug
				e.printStackTrace();
			}
	    	
	    	
	    }
	}
	
	public void readTextFile() {
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("C:\\Users\\Public"));
	    int retrival = chooser.showOpenDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        
	    	BufferedReader reader;

			try {
				reader = new BufferedReader(new FileReader(chooser.getSelectedFile()));
				String line = reader.readLine();

				while (line != null) {
					if (line.trim().equals("") == false) {
						logsBuffer.add(line);
					}
					line = reader.readLine();
				}

				reader.close();
		    	
				model.getShapes().clear();
				frm.resetLogs();
		    	notifyObserversCustom();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(chooser, "Error");
				//Debug
				e.printStackTrace();
			} 
	    	
	    	
	    }
	}

	public void down() {
		int max= model.getShapes().size() - 1;
		Shape selected=null;
		int index = 0;
		for (int k=0;k<=max;k++) {
			Shape s=model.getShapes().get(k);
			if (s.isSelected()) {
				selected = s;
				index = k;
			}
		}
		
		if (selected == null) return;
		if (index == 0) {
			JOptionPane.showMessageDialog(null, "Shape is already on the bottom");
			return;
		}
		
		DownCommand downCommand = new DownCommand();
		undoBuffer.add(downCommand);
		redoBuffer.clear();
		downCommand.setModel(model);
		downCommand.setShape(selected);
		downCommand.execute();
		
		frm.addLog("Selected shape is moved down");
	}

	public void up() {
		int max= model.getShapes().size() - 1;
		Shape selected=null;
		int index = 0;
		for (int k=0;k<=max;k++) {
			Shape s=model.getShapes().get(k);
			if (s.isSelected()) {
				selected = s;
				index = k;
			}
		}
		
		if (selected == null) return;
		if (index == max) {
			JOptionPane.showMessageDialog(null, "Shape is already on the top");
			return;
		}
		
		UpCommand upCommand = new UpCommand();
		undoBuffer.add(upCommand);
		redoBuffer.clear();
		upCommand.setModel(model);
		upCommand.setShape(selected);
		upCommand.execute();
		
		frm.addLog("Selected shape is moved up");
	}

	public void bringToTop() {
		int max= model.getShapes().size() - 1;
		Shape selected=null;
		int index = 0;
		for (int k=0;k<=max;k++) {
			Shape s=model.getShapes().get(k);
			if (s.isSelected()) {
				selected = s;
				index = k;
			}
		}
		
		if (selected == null) return;
		if (index == max) {
			JOptionPane.showMessageDialog(null, "Shape is already on the top");
			return;
		}
		
		BringToTopCommand bringToTopCommand = new BringToTopCommand();
		undoBuffer.add(bringToTopCommand);
		redoBuffer.clear();
		bringToTopCommand.setModel(model);
		bringToTopCommand.setShape(selected);
		bringToTopCommand.execute();
		
		frm.addLog("Selected shape is moved to top");
	}

	public void bringToBottom() {
		int max= model.getShapes().size() - 1;
		Shape selected=null;
		int index = 0;;
		for (int k=0;k<=max;k++) {
			Shape s=model.getShapes().get(k);
			if (s.isSelected()) {
				selected = s;
				index = k;
			}
		}
		
		if (selected == null) return;
		if (index == 0) {
			JOptionPane.showMessageDialog(null, "Shape is already on the bottom");
			return;
		}
	
		BringToBottomCommand bringToBottomCommand = new BringToBottomCommand();
		undoBuffer.add(bringToBottomCommand);
		redoBuffer.clear();
		bringToBottomCommand.setModel(model);
		bringToBottomCommand.setShape(selected);
		bringToBottomCommand.execute();
		
		frm.addLog("Selected shape is moved to bottom");
	}

	public void nextLog() {
		if (logsBuffer.isEmpty()) {
			return;
		}
		
		LogConverter converter = new LogConverter();
		converter.setModel(model);
		if (logsBuffer.get(0).equals("Undone command")) {
			undo();
		} else if (logsBuffer.get(0).equals("Redone command")) {
			redo();
		} else {
			Command c = converter.convert(logsBuffer.get(0));
			c.execute();
			undoBuffer.add(c);
			redoBuffer.clear();
			frm.addLog(logsBuffer.get(0));
		}
		
		logsBuffer.remove(0);
		notifyObserversCustom();
	}

	public void undo() {
		int undoBuffLast = undoBuffer.size();
		undoBuffer.get(undoBuffLast - 1).unexecute();
		redoBuffer.add(undoBuffer.get(undoBuffLast - 1));
		undoBuffer.remove(undoBuffLast -1);
		frm.addLog("Undone command");
		notifyObserversCustom();
	}

	public void redo() {
		int redoBuffLast = redoBuffer.size();
		redoBuffer.get(redoBuffLast - 1).execute();
		undoBuffer.add(redoBuffer.get(redoBuffLast - 1));
		redoBuffer.remove(redoBuffLast -1);
		frm.addLog("Redone command");
		notifyObserversCustom();
	}
	
}
