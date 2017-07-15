package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line extends Shape{

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color strokeColor=Color.BLACK;
	
	public Line(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(strokeColor);
		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public boolean containsPoint(int xCoordinate, int yCoordinate) {
		return xCoordinate>=x1&&xCoordinate<=x2&&yCoordinate>=y1&&yCoordinate<=y2;
	}
	
	@Override
	public void setLocation(int deltaX, int deltaY) {
		x1+=deltaX;
		y1+=deltaY;
		x2+=deltaX;
		y2+=deltaY;
	}

	@Override
	public boolean containsPoint(Point p) {
		return p.x>=x1&&p.x<=x2&&p.y>=y1&&p.y<=y2;
	}

	@Override
	public void getBigger() {
		x1--;
		y1--;
		x2++;
		y2++;
	}

	@Override
	public void getSmaller() {
		x1++;
		y1++;
		x2--;
		y2--;
	}
	
	@Override
	public void setStrokeColor(Color color) {
		strokeColor = color;
	}
	
}
