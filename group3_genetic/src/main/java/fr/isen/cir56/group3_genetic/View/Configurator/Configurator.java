/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Configurator;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Configurator extends JPanel {
	
	public Configurator() {
		List<String> liste = new LinkedList<>();
		liste.add("fr.isen.cir56");
		
		this.add(new ComboBoxSelector(liste, new SelectorClassFilter()));
		this.add(new ComboBoxSelector(liste, new MutationClassFilter()));
		this.add(new ComboBoxSelector(liste, new CrossoverClassFilter()));
	}
	
}
