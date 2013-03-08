package fr.isen.cir56.group3_genetic;

public interface PersonInterface extends Comparable{

	//public Vector myPopulation;
    public void computeEvalutation();
        
    public double getValue();
    public void setValue(double value);
        
}