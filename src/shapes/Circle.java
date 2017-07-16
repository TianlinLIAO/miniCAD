package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle extends Shape{
	
	private int x;
	private int y;
	private int radius;
	private Color strokeColor=Color.BLACK;

	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(strokeColor);
		g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
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
	public void getSmaller() {
		if(radius>10)
			radius-=10;
	}
	
	@Override
	public void setStrokeColor(Color color) {
		strokeColor = color;
	}

}
