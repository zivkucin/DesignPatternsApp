package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import implementations.StrategyLog;
import implementations.StrategySerialization;
import modals.DlgCircle;
import modals.DlgDonut;
import modals.DlgLine;
import modals.DlgPoint;
import modals.DlgRectangle;
import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class ViewDrawingFrm extends JFrame implements Observer {

	private Controller controller;

	private JPanel contentPane;
	private Color outline = Color.black;
	private Color area = Color.white;
	private ViewPnlDrawing pnlDrawing;
	private Point firstLinePoint = new Point(-1, -1);
	private DefaultListModel<String> lm = new DefaultListModel<String>();
	private JButton btnNewButton_8;
	private JButton btnNewButton_9;
	private JButton btnNewButton_7;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	
	JButton btnModify;
	JButton btnDelete;

	String shapeToDraw = "";
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public ViewDrawingFrm(ViewPnlDrawing pnl) {
		setTitle("Ana Zivkucin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 564);
		contentPane = new JPanel();
		contentPane.setToolTipText("dsadsa");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pnlDrawing = pnl;
		pnlDrawing.setBackground(Color.WHITE);

		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Color.ORANGE);
		contentPane.add(pnlButton, BorderLayout.NORTH);

		pnlDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.pnlDrawingMouseClick(e, shapeToDraw, outline, area, firstLinePoint);
				pnlDrawing.repaint();
			}
		});

		JToggleButton btnPoint = new JToggleButton("Point");
		buttonGroup.add(btnPoint);
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapeToDraw = "point";
			}
		});
		pnlButton.setLayout(new GridLayout(1, 8, 0, 0));
		pnlButton.add(btnPoint);

		JToggleButton btnDonut = new JToggleButton("Donut");
		buttonGroup.add(btnDonut);
		btnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapeToDraw = "donut";
			}
		});

		JToggleButton btnCircle = new JToggleButton("Circle");
		buttonGroup.add(btnCircle);
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapeToDraw = "circle";

			}
		});

		JToggleButton btnLine = new JToggleButton("Line");
		buttonGroup.add(btnLine);
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapeToDraw = "line";

			}
		});
		pnlButton.add(btnLine);

		JToggleButton btnRectangle = new JToggleButton("Rectangle");
		buttonGroup.add(btnRectangle);
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapeToDraw = "rectangle";
			}
		});
		pnlButton.add(btnRectangle);

		JToggleButton btnHexagon = new JToggleButton("Hexagon");
		buttonGroup.add(btnHexagon);
		btnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapeToDraw = "hexagon";
			}
		});

		pnlButton.add(btnHexagon);
		pnlButton.add(btnCircle);
		pnlButton.add(btnDonut);

		JButton btnOutlineColor = new JButton("Outline Color");
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outline = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
				if (outline == null) {
					outline = Color.BLACK;
				}
			}
		});
		pnlButton.add(btnOutlineColor);

		JButton btnAreaColor = new JButton("Area Color");
		btnAreaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area = JColorChooser.showDialog(null, "Choose a color", Color.WHITE);
				if (area == null) {
					area = Color.WHITE;
				}
			}
		});
		pnlButton.add(btnAreaColor);

		JPanel actions = new JPanel();
		contentPane.add(actions, BorderLayout.EAST);
		GridBagLayout gbl_actions = new GridBagLayout();
		gbl_actions.columnWidths = new int[] { 65, 0 };
		gbl_actions.rowHeights = new int[] { 50, 50, 50, 0, 0, 0, 0, 0, 0, 0 };
		gbl_actions.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_actions.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		actions.setLayout(gbl_actions);

		JToggleButton btnSelect = new JToggleButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapeToDraw = "select";

			}
		});
		buttonGroup.add(btnSelect);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.fill = GridBagConstraints.BOTH;
		gbc_btnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 0;
		actions.add(btnSelect, gbc_btnSelect);

		btnModify = new JButton("Modify");
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.fill = GridBagConstraints.BOTH;
		gbc_btnModify.insets = new Insets(0, 0, 5, 0);
		gbc_btnModify.gridx = 0;
		gbc_btnModify.gridy = 1;
		actions.add(btnModify, gbc_btnModify);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
				pnlDrawing.repaint();
			}
		});
		btnDelete.setBackground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 2;
		actions.add(btnDelete, gbc_btnDelete);

		JButton btnSaveLog = new JButton("Save log");
		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StrategyLog strategy = new StrategyLog();
				String text = "";
				for (int i = lm.getSize() - 1; i >= 0; i--) {
					text += lm.getElementAt(i) + "\n";
				}

				strategy.setText(text);
				controller.save(strategy);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("File operations");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		actions.add(lblNewLabel_2, gbc_lblNewLabel_2);
		GridBagConstraints gbc_btnSaveLog = new GridBagConstraints();
		gbc_btnSaveLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveLog.gridx = 0;
		gbc_btnSaveLog.gridy = 4;
		actions.add(btnSaveLog, gbc_btnSaveLog);

		JButton btnNewButton = new JButton("Serialize");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StrategySerialization strategy = new StrategySerialization();
				strategy.setShapesOnDrawing(controller.getShapesForSave());
				controller.save(strategy);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		actions.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Read log");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.readTextFile();
				pnlDrawing.repaint();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 6;
		actions.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Deserialize");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deserialize();
				pnlDrawing.repaint();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 7;
		actions.add(btnNewButton_2, gbc_btnNewButton_2);
		
		btnNewButton_7 = new JButton("Next Log");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.nextLog();
				pnlDrawing.repaint();
			}
		});
		btnNewButton_7.setVisible(false);
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.gridx = 0;
		gbc_btnNewButton_7.gridy = 8;
		actions.add(btnNewButton_7, gbc_btnNewButton_7);

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modifyShape();
				pnlDrawing.repaint();
			}
		});

		contentPane.add(pnlDrawing, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);
		pnlDrawing.setLayout(new BorderLayout(0, 0));

		list.setModel(lm);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		btnNewButton_3 = new JButton("Up");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.up();
				pnlDrawing.repaint();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Z-Axis operations");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 1;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);

		btnNewButton_4 = new JButton("Down");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.down();
				pnlDrawing.repaint();
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 2;
		panel_1.add(btnNewButton_4, gbc_btnNewButton_4);

		btnNewButton_5 = new JButton("Bring to top");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToTop();
				pnlDrawing.repaint();
			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_5.gridx = 0;
		gbc_btnNewButton_5.gridy = 3;
		panel_1.add(btnNewButton_5, gbc_btnNewButton_5);

		btnNewButton_6 = new JButton("Bring to bottom");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBottom();
				pnlDrawing.repaint();
			}
		});
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_6.gridx = 0;
		gbc_btnNewButton_6.gridy = 4;
		panel_1.add(btnNewButton_6, gbc_btnNewButton_6);
		
		btnNewButton_8 = new JButton("UNDO");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
				pnlDrawing.repaint();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Undo/Redo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 5;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_8.gridx = 0;
		gbc_btnNewButton_8.gridy = 6;
		panel_1.add(btnNewButton_8, gbc_btnNewButton_8);
		
		btnNewButton_9 = new JButton("REDO");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
				pnlDrawing.repaint();
			}
		});
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.gridx = 0;
		gbc_btnNewButton_9.gridy = 7;
		panel_1.add(btnNewButton_9, gbc_btnNewButton_9);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void addLog(String log) {
		lm.add(0, log);
	}

	public void resetLogs() {
		lm.clear();
	}

	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Boolean> switches = (ArrayList<Boolean>)arg;
		btnModify.setEnabled(switches.get(0));
		btnDelete.setEnabled(switches.get(1));
		btnNewButton_8.setEnabled(switches.get(2));
		btnNewButton_9.setEnabled(switches.get(3));
		btnNewButton_7.setVisible(switches.get(4));
		
		if (switches.get(0) == false) {
			btnNewButton_3.setEnabled(false);
			btnNewButton_4.setEnabled(false);
			btnNewButton_5.setEnabled(false);
			btnNewButton_6.setEnabled(false);
		} else {
			btnNewButton_3.setEnabled(true);
			btnNewButton_4.setEnabled(true);
			btnNewButton_5.setEnabled(true);
			btnNewButton_6.setEnabled(true);
		}
	}

}
