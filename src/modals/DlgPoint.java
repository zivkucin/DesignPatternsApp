package modals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shapes.Point;
import shapes.Shape;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private final JPanel pnlCenter = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private Color outline = Color.black;
	private boolean outlineBool;
	private boolean isOk;
	private Point pointToEdit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setBackground(new Color(255, 228, 181));
		setTitle("Ana Zivkucin");
		setModal(true);
		setBounds(100, 100, 379, 243);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 128, 0));
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		JLabel lblXCoord = new JLabel("X coordinate:");
		
		JLabel lblYCoord = new JLabel("Y coordinate:");
		
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
		
		JButton btnOutlineColor = new JButton("Outline color");
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
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
								.addComponent(lblXCoord)
								.addComponent(lblYCoord))
							.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
							.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
								.addComponent(txtYCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtXCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(61, Short.MAX_VALUE))))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXCoord)
						.addComponent(txtXCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYCoord)
						.addComponent(txtYCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnOutlineColor)
					.addContainerGap(159, Short.MAX_VALUE))
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
						if(txtXCoord.getText().trim().isEmpty() ||
						   txtYCoord.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Enter in all the values!", "Error", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}else {
							int x = Integer.parseInt(txtXCoord.getText());
							int y = Integer.parseInt(txtYCoord.getText());
							if (pointToEdit != null) {
								pointToEdit.setX(x);
								pointToEdit.setY(y);
								pointToEdit.setOutline(outline);
							}
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
						.addGap(240)
						.addComponent(okButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cancelButton)
						.addGap(5))
			);
			gl_btnPanel.setVerticalGroup(
				gl_btnPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_btnPanel.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton))
						.addContainerGap())
			);
			btnPanel.setLayout(gl_btnPanel);
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

	public boolean isOk() {
		return isOk;
	}

	public void setPointToEdit(Point p) {
		this.pointToEdit = p;
		txtXCoord.setText(String.valueOf(p.getX()));
		txtYCoord.setText(String.valueOf(p.getY()));
		outline = p.getOutline();
	}
	
}

