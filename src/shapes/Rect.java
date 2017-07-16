package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

public class Rect extends Shape implements Serializable{

	private int x;
	private int y;
	private int width;
	private int height;
	private Color strokeColor=Color.BLACK;
	private float strokeWidth=1.0f;
	
	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setStroke(new BasicStroke(strokeWidth));
		g2d.setColor(strokeColor);
		g2d.drawRect(x, y, width, height);
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
