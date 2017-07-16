package listener;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import shapes.Circle;
import shapes.Line;
import shapes.Rect;
import shapes.Shape;
import ui.PaintPanel;

public class PaintListener extends MouseAdapter implements ActionListener, MouseMotionListener {

	private static final int LINE = 1;
	private static final int CIRCLE = 2;
	private static final int RECT = 3;
	private static int shapeToPaint = -1;

	private JPanel paintPanel = null;
	private ArrayList<Shape> shapes = null;

	private int x1, y1, x2, y2;
	private int lastX, lastY;

	private Shape currentShape = null;
	private boolean drawingMode = false;

	public PaintListener(JPanel panel) {
		this.paintPanel = panel;
		this.shapes = ((PaintPanel) paintPanel).getListShape();
		setKeyBindings();
	}

	private void setKeyBindings() {
		InputMap inputMap = paintPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = paintPanel.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "Bigger");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "Smaller");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "Thicker");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "Thinner");

		actionMap.put("Bigger", new KeyAction("Bigger"));
		actionMap.put("Smaller", new KeyAction("Smaller"));
		actionMap.put("Thicker", new KeyAction("Thicker"));
		actionMap.put("Thinner", new KeyAction("Thinner"));
	}

	public class KeyAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private String cmd;

		public KeyAction(String cmd) {
			this.cmd = cmd;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (drawingMode)
				return;
			if (currentShape != null) {
				if (cmd.equals("Bigger")) {
					currentShape.getBigger();
					((PaintPanel) paintPanel).repaint();
				} else if (cmd.equals("Smaller")) {
					currentShape.getSmaller();
					((PaintPanel) paintPanel).repaint();
				} else if (cmd.equals("Thicker")) {
					currentShape.getThicker();
					((PaintPanel) paintPanel).repaint();
				} else if (cmd.equals("Thinner")) {
					currentShape.getThinner();
					((PaintPanel) paintPanel).repaint();
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Line")) {
			shapeToPaint = LINE;
			drawingMode = true;
		} else if (e.getActionCommand().equals("Circle")) {
			shapeToPaint = CIRCLE;
			drawingMode = true;
		} else if (e.getActionCommand().equals("Rectangle")) {
			shapeToPaint = RECT;
			drawingMode = true;
		} else if (e.getActionCommand().equals("Color")) {
			Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
			if (color != null && currentShape != null) {
				currentShape.setStrokeColor(color);
				System.out.println("color is:" + color.toString());
				((PaintPanel) paintPanel).repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();

		if (!drawingMode) {
			for (Shape shape : shapes) {
				if (shape.containsPoint(x1, y1)) {
					currentShape = shape;
					lastX = x1;
					lastY = y1;
					System.out.println("selected");
					break;
				}
			}
		}

		System.out.println("mouse pressed");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();

		if (!drawingMode) {
			if (currentShape != null) {
				currentShape.setLocation(x2 - lastX, y2 - lastY);
				lastX = x2;
				lastY = y2;
				((PaintPanel) paintPanel).repaint();
			}
		}

		else {
			if (shapeToPaint == LINE) {
				currentShape = new Line(x1, y1, x2, y2);
			} else if (shapeToPaint == CIRCLE) {
				currentShape = new Circle(x1, y1, Math.abs(x2 - x1));
			} else if (shapeToPaint == RECT) {
				currentShape = new Rect(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1));
			}

			((PaintPanel) paintPanel).setShape(currentShape);
			((PaintPanel) paintPanel).repaint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (drawingMode) {
			if (currentShape != null) {
				((PaintPanel) paintPanel).addShape(currentShape);
				((PaintPanel) paintPanel).setShape(currentShape);
				((PaintPanel) paintPanel).repaint();
			}
			drawingMode = false;
		}

		((PaintPanel) paintPanel).repaint();
		
		System.out.println("mouse released");
	}
	
}
