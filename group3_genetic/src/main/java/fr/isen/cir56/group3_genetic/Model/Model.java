package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Event.Event;
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
		this.views = new LinkedList<>();
	}

	@Override
	public void addView(ViewInterface view) {
		if(view == null) {
			throw new NullPointerException();
		}
		this.views.add(view);
	}

	@Override
	public void removeView(ViewInterface view) {
		this.views.remove(view);
	}

	@Override
	public void refreshViews(Event event) {
		for (ViewInterface view : views) {
			view.refresh(event);
		}
	}
	
}
