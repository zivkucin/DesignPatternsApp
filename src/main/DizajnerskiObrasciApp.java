 package main;

import java.awt.EventQueue;

import mvc.Controller;
import mvc.Model;
import mvc.ViewDrawingFrm;
import mvc.ViewPnlDrawing;

public class DizajnerskiObrasciApp {
	
	public static void main(String[] args) {
		Model model = new Model();
		ViewPnlDrawing pnlDrawing = new ViewPnlDrawing(model);
		ViewDrawingFrm frm = new ViewDrawingFrm(pnlDrawing);
		Controller c = new Controller(model, frm);
		
		frm.setVisible(true);
	
	}
	
}
