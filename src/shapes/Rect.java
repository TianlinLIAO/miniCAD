package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rect extends Shape{

	private int x;
	private int y;
	private int width;
	private int height;
	private Color strokeColor=Color.BLACK;
	
	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		g.setColor(strokeColor);
		g.drawRect(x, y, width, height);
	}

	@Override
	public boolean containsPoint(int xCoordinate, int yCoordinate) {
		return xCoordinate>=x&&xCoordinate<=(x+width)&&yCoordinate>=y&&yCoordinate<=(y+height);
	}

	@Override
	public void setLocation(int deltaX, int deltaY) {
		x+=deltaX;
		y+=deltaY;
	}

	@Override
	public boolean containsPoint(Point p) {
		return p.x>=x&&p.x<=(x+width)&&p.y>=y&&p.y<=(y+height);
	}

	@Override
	public void getBigger() {
		width+=10;
		height+=10;
	}

	@Override
	public void getSmaller() {
		if(width>10&&height>10){
			width-=10;
			height-=10;
		}
	} 
	
	@Override
	public void setStrokeColor(Color color) {
		strokeColor = color;
	}

}
