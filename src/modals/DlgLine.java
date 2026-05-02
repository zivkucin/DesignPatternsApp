package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shapes.Line;
import shapes.Point;
import shapes.Shape;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgLine extends JDialog {

	private final JPanel pnlCenter = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField txtXStart;
	private JTextField txtYStart;
	private JTextField txtXEnd;
	private JTextField txtYEnd;
	private Color outline = Color.black;
	private boolean outlineBool;
	private JButton btnOutlineColor;
	private Line lineToEdit;
	private boolean isOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBackground(new Color(255, 222, 173));
		setTitle("Ana Zivkucin");
		setModal(true);
		setBounds(100, 100, 324, 308);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 128, 0));
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		JLabel lblXStart = new JLabel("X start:");
		JLabel lblYStart = new JLabel("Y start:");
		JLabel lblXEnd = new JLabel("X end:");
		JLabel lblYEnd = new JLabel("Y end:");
		
		txtXStart = new JTextField();
		txtXStart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c>='0') && (c<='9')) || (c == KeyEvent.VK_BACK_SPACE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtXStart.setColumns(10);
		
		txtYStart = new JTextField();
		txtYStart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c>='0') && (c<='9')) || (c == KeyEvent.VK_BACK_SPACE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtYStart.setColumns(10);
		
		txtXEnd = new JTextField();
		txtXEnd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c>='0') && (c<='9')) || (c == KeyEvent.VK_BACK_SPACE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtXEnd.setColumns(10);
		
		txtYEnd = new JTextField();
		txtYEnd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c>='0') && (c<='9')) || (c == KeyEvent.VK_BACK_SPACE))) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtYEnd.setColumns(10);
		
		btnOutlineColor = new JButton("Outline color");
		btnOutlineColor.setBackground(new Color(245, 255, 250));
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outline = JColorChooser.showDialog(null, "Choose a color", Color.black);
				outlineBool = true;
			}
		});
		
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addComponent(btnOutlineColor)
							.addContainerGap())
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlCenter.createSequentialGroup()
								.addComponent(lblYEnd)
								.addContainerGap())
							.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCenter.createSequentialGroup()
									.addComponent(lblXEnd)
									.addContainerGap())
								.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_pnlCenter.createSequentialGroup()
										.addComponent(lblYStart)
										.addContainerGap())
									.addGroup(gl_pnlCenter.createSequentialGroup()
										.addComponent(lblXStart)
										.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
										.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
											.addComponent(txtXStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtYStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtXEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtYEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addContainerGap(67, Short.MAX_VALUE)))))))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXStart)
						.addComponent(txtXStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYStart)
						.addComponent(txtYStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXEnd)
						.addComponent(txtXEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYEnd)
						.addComponent(txtYEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnOutlineColor)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel btnPanel = new JPanel();
			btnPanel.setBackground(new Color(255, 128, 0));
			getContentPane().add(btnPanel, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setBackground(new Color(245, 255, 250));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtXStart.getText().trim().isEmpty() ||
						   txtYStart.getText().trim().isEmpty() ||
						   txtXEnd.getText().trim().isEmpty() ||
						   txtYEnd.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Enter in all the values!", "Error", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}else {
							if (lineToEdit != null) {
								int xStart = Integer.parseInt(txtXStart.getText());
								int yStart = Integer.parseInt(txtYStart.getText());
								int xEnd = Integer.parseInt(txtXEnd.getText());
								int yEnd = Integer.parseInt(txtYEnd.getText());
								lineToEdit.getStart().setX(xStart);
								lineToEdit.getStart().setY(yStart);
								lineToEdit.getEnd().setX(xEnd);
								lineToEdit.getEnd().setY(yEnd);
								lineToEdit.setOutline(outline);
							}
							
							
							isOk = true;
						}
						dispose();
						return;
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(245, 255, 250));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
			gl_btnPanel.setHorizontalGroup(
				gl_btnPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_btnPanel.createSequentialGroup()
						.addGap(185)
						.addComponent(okButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cancelButton))
			);
			gl_btnPanel.setVerticalGroup(
				gl_btnPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_btnPanel.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(cancelButton)
							.addComponent(okButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			btnPanel.setLayout(gl_btnPanel);
		}
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JTextField getTxtXStart() {
		return txtXStart;
	}

	public void setTxtXStart(JTextField txtXStart) {
		this.txtXStart = txtXStart;
	}

	public JTextField getTxtYStart() {
		return txtYStart;
	}

	public void setTxtYStart(JTextField txtYStart) {
		this.txtYStart = txtYStart;
	}

	public JTextField getTxtXEnd() {
		return txtXEnd;
	}

	public void setTxtXEnd(JTextField txtXEnd) {
		this.txtXEnd = txtXEnd;
	}

	public JTextField getTxtYEnd() {
		return txtYEnd;
	}

	public void setTxtYEnd(JTextField txtYEnd) {
		this.txtYEnd = txtYEnd;
	}

	public JPanel getPnlCenter() {
		return pnlCenter;
	}
	
	public  void setLineToEdit(Line l) {
		this.lineToEdit = l;
		txtXStart.setText(String.valueOf(l.getStart().getX()));
		txtYStart.setText(String.valueOf(l.getStart().getY()));
		txtXEnd.setText(String.valueOf(l.getEnd().getX()));
		txtYEnd.setText(String.valueOf(l.getEnd().getY()));
		outline = l.getOutline();
	}

	public boolean isOk() {
		return isOk;
	}
	
	
}

