package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Text extends Shape {

	private int x;
	private int y;
	private String text;
	private Color strokeColor = Color.BLACK;
	private Font font = new Font("TimesRoman", Font.PLAIN, 50);
	private Rectangle2D stringBounds;

	public Text(int x, int y, String text) {
		super();
		this.x = x;
		this.y = y;
		this.text = text;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(strokeColor);
		g2d.setFont(font);
		g2d.drawString(text, x, y);
		stringBounds = g2d.getFontMetrics().getStringBounds(text, g2d);
		// g2d.drawRect(x, y, (int)stringBounds.getWidth(),
		// (int)stringBounds.getHeight());
	}

	@Override
	public boolean containsPoint(int xCoordinate, int yCoordinate) {
		return xCoordinate >= x && xCoordinate <= (x + stringBounds.getWidth()) && yCoordinate <= y
				&& yCoordinate >= (y - stringBounds.getHeight());
	}

	@Override
	public boolean containsPoint(Point p) {
		return p.x >= x && p.x <= (x + stringBounds.getWidth()) && p.y <= y && p.y >= (y - stringBounds.getHeight());
	}

	@Override
	public void setLocation(int deltaX, int deltaY) {
		x += deltaX;
		y += deltaY;
	}

	@Override
	public void getBigger() {
		Font newFont = font.deriveFont(font.getSize() * 1.2f);
		font = newFont;
	}

	@Override
	public void getSmaller() {
		Font newFont = font.deriveFont(font.getSize() * 0.9f);
		font = newFont;
	}

	@Override
	public void getThicker() {}

	@Override
	public void getThinner() {}

	@Override
	public void setStrokeColor(Color color) {
		strokeColor = color;
	}

}
