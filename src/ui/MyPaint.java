package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import listener.MenuListener;
import listener.PaintListener;

public class MyPaint extends JFrame{

	private static final long serialVersionUID = 1L;
	private PaintPanel paintPanel=null;
	private PaintListener paintListener=null;
	private MenuListener menuListener=null;
	
	public MyPaint(String title){
		this.setTitle(title);
		
		Container container=this.getContentPane();
		
		paintPanel=new PaintPanel();
		paintListener=new PaintListener(paintPanel);
		paintPanel.addMouseListener(paintListener);
		paintPanel.addMouseMotionListener(paintListener);
		
		container.setLayout(new BorderLayout());
		container.add(createShapeToolBar(), BorderLayout.NORTH);
		container.add(paintPanel, BorderLayout.CENTER);
		menuListener=new MenuListener(this, paintPanel);
		this.setJMenuBar(createMenuBar());
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.setSize(1200, 800);
		//Get the width and height of screen
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth=screenSize.width;
		int screenHeight=screenSize.height;
		//Set the MyPaint window in the middle of the screen
		this.setLocation((screenWidth-this.getWidth())/2, (screenHeight-this.getHeight())/2);
	}

	private JMenuBar createMenuBar() {
		String[] fileMenuTitle={"New","Open File...","Save","Save As..","Quit"};
		String[] helpMenuTitle={"About"};
		
		JMenuBar menuBar=new JMenuBar();
		JMenu menu=null;
		
		menu=createMenu(fileMenuTitle,"File");
		menuBar.add(menu);
		menu=createMenu(helpMenuTitle, "Help");
		menuBar.add(menu);
		return menuBar;
	}

	private JMenu createMenu(String[] menuItems, String menuTitle) {
		JMenu menu=new JMenu();
		JMenuItem menuItem=null;
		for(int i=0;i<menuItems.length;i++){
			if(menuItems[i].equals("Separator")){
				menu.addSeparator();
			}
			else{
				menuItem=new JMenuItem();
				menuItem.setText(menuItems[i]);
				menuItem.addActionListener(menuListener);
				menu.add(menuItem);
			}
		}
		menu.setText(menuTitle);
		return menu;
	}

	private Component createShapeToolBar() {
		String[] paintTitle={"Line","Circle","Rectangle","Text","Color"};
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

	public static void main(String[] args){
		MyPaint paint=new MyPaint("Untitled - miniCAD");
	}
}
