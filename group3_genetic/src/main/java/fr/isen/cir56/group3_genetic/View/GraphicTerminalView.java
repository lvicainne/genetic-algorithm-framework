/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View;

import javax.swing.JTextArea;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class GraphicTerminalView extends AbstractGeneticView {
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
