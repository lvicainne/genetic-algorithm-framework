import java.util.Vector;

public interface EvolutionOperator {

    public Vector  myConfiguration;

  protected abstract void evaluate();

}