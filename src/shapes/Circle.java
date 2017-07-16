package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shape{
	
	private int x;
	private int y;
	private int radius;
	private Color strokeColor=Color.BLACK;
	private float strokeWidth=1.0f;

	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setStroke(new BasicStroke(strokeWidth));
		g2d.setColor(strokeColor);
		g2d.drawOval(x-radius, y-radius, 2*radius, 2*radius);
	}

	@Override
	public boolean containsPoint(int xCoordinate, int yCoordinate) {
		return Math.pow(xCoordinate-x, 2)+Math.pow(yCoordinate-y, 2)<=Math.pow(radius, 2);
	}

	@Override
	public void setLocation(int deltaX, int deltaY) {
		x+=deltaX;
		y+=deltaY;
	}

	@Override
	public boolean containsPoint(Point p) {
		return Math.pow(p.x-x, 2)+Math.pow(p.y-y, 2)<=Math.pow(radius, 2);
	}

	@Override
	public void getBigger() {
		radius+=10;
	}

	@Override
	public void getThicker() {
		strokeWidth+=1.0;
	}

	@Override
	public void getThinner() {
		if(strokeWidth>1) strokeWidth-=1.0;
	}
	
	@Override
	public void getSmaller() {
		if(radius>10)
			radius-=10;
	}
	
	@Override
	public void setStrokeColor(Color color) {
		strokeColor = color;
	}

}
