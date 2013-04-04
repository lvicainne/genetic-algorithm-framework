package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Event.Event;
import javax.swing.JTextArea;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class GraphicTerminalView implements ViewInterface {
	private JTextArea textArea;
	
	public GraphicTerminalView() {
		this.textArea = new JTextArea();
	}
	
	@Override
	public void refresh(Event event) {
		String newLine = System.getProperty("line.separator");
		String message = this.textArea.getText();
		if(event.hasException()) {
			message += "Exception : " + event.getException().toString() + newLine;
		}
		
		message += event.toString() + newLine;
		
		this.textArea.setText(message);
	}
	
}
