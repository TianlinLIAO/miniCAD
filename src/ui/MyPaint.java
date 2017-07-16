package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import listener.PaintListener;

public class MyPaint extends JFrame{

	private JPanel paintPanel=null;
	private PaintListener paintListener=null;
	
	public MyPaint(String title){
		this.setTitle(title);
		
		Container container=this.getContentPane();
		
		paintPanel=new PaintPanel();
		paintListener=new PaintListener(paintPanel);
		paintPanel.addMouseListener(paintListener);
		paintPanel.addMouseMotionListener(paintListener);
		
//		keyBinding=new KeyBindings();
//		container.add(keyBinding);
		container.setLayout(new BorderLayout());
		container.add(createShapeToolBar(), BorderLayout.NORTH);
		container.add(paintPanel, BorderLayout.CENTER);
		this.pack();
//		this.setJMenuBar(createMenuBar());
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.setSize(1200, 800);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth=screenSize.width;
		int screenHeight=screenSize.height;
		this.setLocation((screenWidth-this.getWidth())/2, (screenHeight-this.getHeight())/2);
	}

	private Component createShapeToolBar() {
		String[] paintTitle={"直线","圆","矩形","颜色"};
		JToolBar toolBar=new JToolBar();
		JButton button=null;
		for(int i=0;i<paintTitle.length;i++){
			button=new JButton(paintTitle[i]);
			button.addActionListener(paintListener);
			toolBar.add(button);
		}
		toolBar.setVisible(true);
		return toolBar;
	}

	private JMenuBar createMenuBar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args){
		MyPaint paint=new MyPaint("未命名-画图");
	}
}
