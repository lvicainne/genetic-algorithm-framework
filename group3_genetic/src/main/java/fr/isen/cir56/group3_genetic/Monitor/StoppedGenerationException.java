package fr.isen.cir56.group3_genetic.Monitor;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class StoppedGenerationException extends RuntimeException {

	public StoppedGenerationException() {
	}

	@Override
	public String toString() {
		return "La génération des popoulations est déjà stoppée.";
	}
}
