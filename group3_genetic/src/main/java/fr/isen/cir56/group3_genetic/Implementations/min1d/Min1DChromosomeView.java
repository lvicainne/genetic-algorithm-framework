/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewListener;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

	
	
	
/**
 *
 * @author Wasp
 */
public class Min1DChromosomeView extends JPanel implements ChromosomeViewListener{

	private ChromosomeInterface chromosome;
	
	
	@Override
	public void chromosomeChanged(ChromosomeInterface chromosome) {
		this.chromosome = chromosome;

		// exécution dans l'EDT vu que c'est une commande Swing
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
		});

	}

	@Override
	public void resetView() {
		//We reset the view with a null chromosome
		this.chromosomeChanged(null);
	}
	
	@Override
	public void paintComponent(Graphics graphic){ // mettre un point sur le graphique
		List<GeneInterface> genes = chromosome.getGenes();
		Min1DValue min1DValue = (Min1DValue) genes.get(0);
		
		min1DValue.getData();
		
	}

}
