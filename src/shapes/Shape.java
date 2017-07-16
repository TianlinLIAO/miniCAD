package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {

	public abstract void draw(Graphics g);
	
	public abstract boolean containsPoint(int xCoordinate,int yCoordinate);
	
	public abstract boolean containsPoint(Point p);
	
	public abstract void setLocation(int deltaX,int deltaY);
	
	public abstract void getBigger();
	
	public abstract void getSmaller();
	
	public abstract void getThicker();
	
	public abstract void getThinner();
	
	public abstract void setStrokeColor(Color color);
	
}
