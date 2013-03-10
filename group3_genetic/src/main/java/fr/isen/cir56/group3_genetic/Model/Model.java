package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.View.Event;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Model implements ModelInterface {
	private List<ViewInterface> views;
	
	public Model() {
		this.views = new LinkedList<ViewInterface>();
	}

	public void addView(ViewInterface view) {
		if(view == null) {
			throw new NullPointerException();
		}
		this.views.add(view);
	}

	public void removeView(ViewInterface view) {
		this.views.remove(view);
	}

	public void refreshViews(Event event) {
		for (ViewInterface view : views) {
			view.refresh(event);
		}
	}
	
}
