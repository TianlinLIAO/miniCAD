package ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class HelpMessageDialog extends JDialog{
	
	private static final String helpMessage = "Welcome to miniCAD!\n"
			+ "You could draw lines, circles and rectangles.\n"
			+ "For selected shape object, you could move it by mouse dragging,\n" + "change the stroke color,\n"
			+ "and change the size and stroke width by:\n" + "keyboard 'W' - make it bigger;\n"
			+ "keyboard 'S' - make it smaller;\n" + "keyboard 'A' - make the stroke thicker;\n"
			+ "keyboard 'D' - make the stroke thinner;\n";

	public HelpMessageDialog(MyPaint paint){
		super(paint, "Help Message", true);
		Container container=getContentPane();
		JTextArea textArea=new JTextArea();
		textArea.setText(helpMessage);
		container.add(textArea, BorderLayout.CENTER);
		setBounds(600,600,500,500);
	}
}
