package listener;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import shapes.Shape;
import ui.MyPaint;
import ui.PaintPanel;

public class MenuListener implements ActionListener {

	private MyPaint frame = null;
	private PaintPanel paintPanel = null;
	private String path = null;

	public MenuListener(MyPaint frame, PaintPanel paintPanel) {
		this.frame = frame;
		this.paintPanel = paintPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("New")) {
			frame.setTitle("Untitled - miniCAD");
			paintPanel.clearPaint();
			paintPanel.repaint();
			path = null;
		} else if (e.getActionCommand().equals("Open File...")) {
			FileDialog fileDialog = new FileDialog(frame, "Open File", FileDialog.LOAD);
			fileDialog.setVisible(true);
			String directory = fileDialog.getDirectory();
			String fileName = fileDialog.getFile();

			if (directory != null && fileName != null) {
				path = directory + fileName;
			}
			if (path != null) {
				frame.setTitle(fileName + " - miniCAD");
				openFile(path);
				paintPanel.repaint();
			}
		} else if (e.getActionCommand().equals("Save")) {
			saveFile();
		} else if (e.getActionCommand().equals("Save As...")) {
			saveAsFile();
		} else if (e.getActionCommand().equals("Quit")) {
			System.exit(0);
		}
	}

	private void openFile(String path) {
		ObjectInputStream ois = null;
		Shape shape = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(path));
			int size = ois.readInt();
			for (int i = 0; i < size; i++) {
				shape = (Shape) ois.readObject();
				paintPanel.addShape(shape);
			}
			ois.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
	}

	private void saveFile() {
		if (path != null) {
			writeFile(path);
		} else {
			saveAsFile();
		}
	}

	private void saveAsFile() {
		FileDialog fileDialog=new FileDialog(frame, "Save", FileDialog.SAVE);
		fileDialog.setVisible(true);
		String directory=fileDialog.getDirectory();
		String fileName=fileDialog.getFile();
		
		if(directory!=null&&fileName!=null){
			path=directory+fileName;
			writeFile(path);
			frame.setTitle(fileName+" - miniCAD");
		}
	}

	private void writeFile(String path) {
		ObjectOutputStream oos=null;
		ArrayList<Shape> listShape=null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream(path));
			listShape=paintPanel.getListShape();
			oos.writeInt(listShape.size());
			for(int i=0;i<listShape.size();i++){
				oos.writeObject(listShape.get(i));
			}
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
