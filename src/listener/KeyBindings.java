package listener;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class KeyBindings extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String Bigger = "Bigger";
    private Action bigger = new AbstractAction(Bigger) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(Bigger);
        }
    };
    private static final String RIGHT = "Right";
    private Action right = new AbstractAction(RIGHT) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(RIGHT);
        }
    };

	    public KeyBindings(){
	        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	            KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), Bigger);
	        this.getActionMap().put(Bigger, bigger);
	        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	            KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), RIGHT);
	        this.getActionMap().put(RIGHT, right);
	    }


}
