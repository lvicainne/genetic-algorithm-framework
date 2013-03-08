package fr.isen.cir56.group3_genetic;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Person implements PersonInterface{
    
    private double value;

    public void computeEvalutation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int compareTo(Object o) {
      double nombre1 = ((Person)o).getValue(); 
      double nombre2 = this.getValue(); 
      if (nombre1 > nombre2)  return -1; 
      else if(nombre1 == nombre2) return 0; 
      else return 1; 
    }

}
