package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Shape{

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color strokeColor=Color.BLACK;
	private float strokeWidth=1.0f;
	
	public Line(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setStroke(new BasicStroke(strokeWidth));
		g2d.setColor(strokeColor);
		g2d.drawLine(x1, y1, x2, y2);
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
		x1-=10;
		y1-=10;
		x2+=10;
		y2+=10;
	}

	@Override
	public void getSmaller() {
		x1+=10;
		y1+=10;
		x2-=10;
		y2-=10;
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
	public void setStrokeColor(Color color) {
		strokeColor = color;
	}

}
