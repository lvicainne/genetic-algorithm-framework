/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Operator.InvalidCrossoverOperatorException;
import fr.isen.cir56.group3_genetic.Operator.InvalidMutationOperatorException;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
interface GeneticAlgorithmInterface {
	
	public void selection();
	public void crossover() throws InvalidCrossoverOperatorException;
	public void mutation() throws InvalidMutationOperatorException;
	
	
}
