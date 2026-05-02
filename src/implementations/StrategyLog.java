package implementations;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import interfaces.Strategy;

public class StrategyLog implements Strategy {

	private String text;
	
	public void save(String file) {
		try (PrintWriter out = new PrintWriter(file)) {
		    out.println(text);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error");
			//Debug
			e.printStackTrace();
		}
	}
	
	// Getters & setters
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
