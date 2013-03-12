/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Utils.Collections;

import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Collections {
	
	/**
	 * Reverse a part of the list
	 * @param list
	 * @param index1
	 * @param index2 
	 */
	public static void reverse(List list, int index1, int index2) {
		if(index1 > index2) {
			reverse(list, index2, index1);
		}
		int curIndex2 = index2;
		int curIndex1 = index1;
		
		while((curIndex2-curIndex1) > 1) {
			
			java.util.Collections.swap(list, curIndex1, curIndex2);
			curIndex1++;
			curIndex2--;
		}
		
	}
}
