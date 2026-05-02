package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shapes.Donut;
import shapes.Shape;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDonut extends JDialog {

	private final JPanel pnlCenter = new JPanel();
	public boolean isOk;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private Color outline = Color.black;
	private Color area = Color.white;
	private boolean outlineBool;
	private boolean areaBool;
	private Donut donutToEdit;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setBackground(new Color(255, 222, 173));
		setTitle("Ana Zivkucin");
		setModal(true);
		setBounds(100, 100, 374, 287);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 128, 0));
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		JLabel lblXCoordinate = new JLabel("X coordinate:");
		
		JLabel lblYCoordinate = new JLabel("Y Coordinate:");
		JLabel lblRadius = new JLabel("Radius:");
		JLabel lblInnerRadius = new JLabel("Inner radius:");
		txtXCoord = new JTextField();
		txtXCoord.setColumns(10);
		txtYCoord = new JTextField();
		txtYCoord.setColumns(10);
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		
		JButton btnOutlineColor = new JButton("Outline color");
		btnOutlineColor.setBackground(new Color(245, 255, 250));
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outline = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
				outlineBool = true;
			}
		});
		
		JButton btnAreaColor = new JButton("Area color");
		btnAreaColor.setBackground(new Color(245, 255, 250));
		btnAreaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				area = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
				areaBool = true;
			}
		});
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXCoordinate)
						.addComponent(lblRadius)
						.addComponent(lblYCoordinate)
						.addComponent(lblInnerRadius)
						.addComponent(btnOutlineColor))
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAreaColor)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtYCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtXCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXCoordinate)
						.addComponent(txtXCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtYCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYCoordinate))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInnerRadius)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOutlineColor)
						.addComponent(btnAreaColor))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel btnPanel = new JPanel();
			btnPanel.setBackground(new Color(255, 128, 0));
			btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnPanel, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(245, 255, 250));
				okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txtXCoord.getText().trim().isEmpty() ||
					   txtYCoord.getText().trim().isEmpty() ||
					   txtRadius.getText().trim().isEmpty() ||
					   txtInnerRadius.getText().trim().isEmpty()) {
								isOk = false;
						JOptionPane.showMessageDialog(null, "Enter in all the values", "Error", JOptionPane.ERROR_MESSAGE);
						getToolkit().beep();
							}else {
								if(Integer.parseInt(txtRadius.getText()) >
								   Integer.parseInt(txtInnerRadius.getText())) {
									if (donutToEdit != null) {
										int x = Integer.parseInt(txtXCoord.getText());
										int y = Integer.parseInt(txtYCoord.getText());
										int bigRadius = Integer.parseInt(txtRadius.getText());
										int smallRadius = Integer.parseInt(txtInnerRadius.getText());
										donutToEdit.getCenter().setX(x);
										donutToEdit.getCenter().setY(y);
										donutToEdit.setRadius(bigRadius);
										donutToEdit.setInnerRadius(smallRadius);
										donutToEdit.setOutline(outline);
										donutToEdit.setFill(area);
									}
									
									isOk = true;
									dispose();
								}else {
							    isOk = false;
							    JOptionPane.showMessageDialog(null, "The inner radius must be smaller than the outer radius!", 
							    		"Error", JOptionPane.ERROR_MESSAGE);
								}
							}
				}
			});
				okButton.setActionCommand("OK");
				btnPanel.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(245, 255, 250));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				btnPanel.add(cancelButton);
			}
		}
	}

	public JTextField getTxtXCoord() {
		return txtXCoord;
	}

	public void setTxtXCoord(JTextField txtXCoord) {
		this.txtXCoord = txtXCoord;
	}

	public JTextField getTxtYCoord() {
		return txtYCoord;
	}

	public void setTxtYCoord(JTextField txtYCoord) {
		this.txtYCoord = txtYCoord;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}

	public Color getOutline() {
		return outline;
	}

	public void setOutline(Color outline) {
		this.outline = outline;
	}

	public Color getArea() {
		return area;
	}

	public void setArea(Color area) {
		this.area = area;
	}

	public boolean isOutlineBool() {
		return outlineBool;
	}

	public void setOutlineBool(boolean outlineBool) {
		this.outlineBool = outlineBool;
	}

	public boolean isAreaBool() {
		return areaBool;
	}

	public void setAreaBool(boolean areaBool) {
		this.areaBool = areaBool;
	}

	public boolean isOk() {
		return isOk;
	}
	public void setDonutToEdit(Donut d) {
		this.donutToEdit = d;
		txtXCoord.setText(String.valueOf(d.getCenter().getX()));
		txtYCoord.setText(String.valueOf(d.getCenter().getY()));
		txtRadius.setText(String.valueOf(d.getRadius()));
		txtInnerRadius.setText(String.valueOf(d.getInnerRadius()));
		outline = d.getOutline();
		area = d.getFill();
	}
}

