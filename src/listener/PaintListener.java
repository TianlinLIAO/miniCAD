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

public class PaintListener extends MouseAdapter implements ActionListener, MouseMotionListener, MouseWheelListener {

	private static final int LINE = 1;
	private static final int CIRCLE = 2;
	private static final int RECT = 3;

	private JPanel paintPanel = null;
	private ArrayList<Shape> shapes = null;

	private static int shapeToPaint = -1;
	private int x1, y1, x2, y2;
	private int lastX, lastY;
	private int newX1, newY1, newX2, newY2;
	private Shape currenShape;
	private Shape selected = null;
	private boolean drawingMode = false;

	public PaintListener(JPanel panel) {
		this.paintPanel = panel;
		this.shapes = ((PaintPanel) paintPanel).getListShape();
		setKeyBindings();
	}

	private void setKeyBindings() {
		InputMap inputMap = paintPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = paintPanel.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "���");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "��С");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "���");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "��ϸ");

		actionMap.put("���", new KeyAction("���"));
		actionMap.put("��С", new KeyAction("��С"));
		actionMap.put("���", new KeyAction("���"));
		actionMap.put("��ϸ", new KeyAction("��ϸ"));
	}

	public class KeyAction extends AbstractAction {

		private String cmd;

		public KeyAction(String cmd) {
			this.cmd = cmd;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (selected != null) {
				if (cmd.equals("���")) {
					selected.getBigger();
					((PaintPanel) paintPanel).repaint();
				} else if (cmd.equals("��С")) {
					selected.getSmaller();
					((PaintPanel) paintPanel).repaint();
				} else if (cmd.equals("���")) {

				} else if (cmd.equals("��ϸ")) {

				}
			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ֱ��")) {
			shapeToPaint = LINE;
			drawingMode = true;
		} else if (e.getActionCommand().equals("Բ")) {
			shapeToPaint = CIRCLE;
			drawingMode = true;
		} else if (e.getActionCommand().equals("����")) {
			shapeToPaint = RECT;
			drawingMode = true;
		} else if (e.getActionCommand().equals("��ɫ")) {
			Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
			if (color != null && selected != null) {
				selected.setStrokeColor(color);
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
					selected = shape;
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
			if(selected!=null){
				selected.setLocation(x2 - lastX, y2 - lastY);
				lastX = x2;
				lastY = y2;
				((PaintPanel) paintPanel).repaint();
			}
		}

		else {
			if (shapeToPaint == LINE) {
				currenShape = new Line(x1, y1, x2, y2);
			} else if (shapeToPaint == CIRCLE) {
				currenShape = new Circle(x1, y1, Math.abs(x2 - x1));
			} else if (shapeToPaint == RECT) {
				currenShape = new Rect(x1, y1, x2 - x1, y2 - y1);
			}

			((PaintPanel) paintPanel).setShape(currenShape);
			((PaintPanel) paintPanel).repaint();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (drawingMode) {
			if(currenShape!=null){
				((PaintPanel) paintPanel).addShape(currenShape);
				((PaintPanel) paintPanel).setShape(currenShape);
				((PaintPanel) paintPanel).repaint();
			}
			drawingMode=false;
		}
		else {
			
		}
		
		((PaintPanel) paintPanel).repaint();
		System.out.println("mouse released");
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		// if(selected!=null){
		if (notches < 0) {
			System.out.println("move up");
			// selected.getBigger();
			// ((PaintPanel) paintPanel).repaint();
		} else {
			System.out.println("move down");
			// selected.getSmaller();
			// ((PaintPanel) paintPanel).repaint();
		}
		// }

	}
}
