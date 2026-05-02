package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shapes.Circle;
import shapes.Shape;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgCircle extends JDialog {

	private final JPanel pnlCenter = new JPanel();
	public boolean isOk;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JTextField txtRadius;
	private Color outline = Color.black;
	private Color area = Color.white;
	private boolean outlineBool;
	private boolean areaBool;
	private Circle circleToEdit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setBackground(new Color(245, 255, 250));
		setTitle("Ana Zivkucin");
		setModal(true);
		setBounds(100, 100, 358, 307);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 128, 0));
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		JLabel lblXCoordinate = new JLabel("X coordinate:");
		
		JLabel lblYCoordinate = new JLabel("Y coordinate:");
		
		JLabel lblRadius = new JLabel("Radius:");
		
		txtXCoord = new JTextField();
		txtXCoord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c>='0') && (c<='9')) || (c == KeyEvent.VK_BACK_SPACE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});		
		txtXCoord.setColumns(10);
		
		txtYCoord = new JTextField();
		txtYCoord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c>='0') && (c<='9')) || (c == KeyEvent.VK_BACK_SPACE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});		
		txtYCoord.setColumns(10);
		
		txtRadius = new JTextField();
		txtRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c>='0') && (c<='9')) || (c == KeyEvent.VK_BACK_SPACE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});		
		txtRadius.setColumns(10);
		
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
					.addGap(24)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXCoordinate)
						.addComponent(lblYCoordinate)
						.addComponent(lblRadius)
						.addComponent(btnOutlineColor))
					.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAreaColor)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtYCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtXCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXCoordinate)
						.addComponent(txtXCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYCoordinate)
						.addComponent(txtYCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOutlineColor)
						.addComponent(btnAreaColor))
					.addContainerGap(149, Short.MAX_VALUE))
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
							   txtRadius.getText().trim().isEmpty()) {
								isOk = false;
						JOptionPane.showMessageDialog(null, "Enter in all the values", "Error", JOptionPane.ERROR_MESSAGE);
						getToolkit().beep();
							}else {
								if (circleToEdit != null) {
									int x = Integer.parseInt(txtXCoord.getText());
									int y = Integer.parseInt(txtYCoord.getText());
									int r = Integer.parseInt(txtRadius.getText());
									circleToEdit.getCenter().setX(x);
									circleToEdit.getCenter().setY(y);
									circleToEdit.setRadius(r);
									circleToEdit.setOutline(outline);
									circleToEdit.setFill(area);
								}
								
								isOk = true;
								dispose();
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
	
	public void setCircleToEdit(Circle c) {
		this.circleToEdit = c;
		txtXCoord.setText(String.valueOf(c.getCenter().getX()));
		txtYCoord.setText(String.valueOf(c.getCenter().getY()));
		txtRadius.setText(String.valueOf(c.getRadius()));
		outline = c.getOutline();
		area = c.getFill();
	}
	
	
}
