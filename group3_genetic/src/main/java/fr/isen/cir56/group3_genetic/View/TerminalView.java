/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View;

import java.io.PrintStream;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TerminalView extends AbstractGeneticView {
	private PrintStream stream;

	public TerminalView(PrintStream stream) {
		this.setStream(stream);
	}
	
	public final PrintStream getStream() {
		return stream;
	}

	public final void setStream(PrintStream stream) {
		this.stream = stream;
	}
	
	public void refresh(Event event) {
		if(event.hasException()) {
			stream.println("Exception : " + event.getException().toString());
		}
		
		stream.println(event.toString());
	}

}
