package ui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import shapes.Shape;

public class PaintPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> listShape = new ArrayList<Shape>();
	private Shape shape=null;
	
	public PaintPanel(){
		listShape=new ArrayList<Shape>();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for ( Shape s : listShape ){
			s.draw(g);
		}	
		if(shape!=null){
			shape.draw(g);
		}
	}
	
	public void addShape(Shape shape){
		listShape.add(shape);
	}

	public ArrayList<Shape> getListShape() {
		return listShape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
}
