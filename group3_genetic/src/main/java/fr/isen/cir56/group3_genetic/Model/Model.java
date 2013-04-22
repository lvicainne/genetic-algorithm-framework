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
	private List<ListenerInterface> views;
	
	public Model() {
		this.views = new LinkedList<>();
	}

	@Override
	public void addListener(ListenerInterface view) {
		if(view == null) {
			throw new NullPointerException();
		}
		this.views.add(view);
	}
	
	public void addView(ViewInterface view) {
		this.addListener(view);
	}

	@Override
	public void removeListener(ListenerInterface view) {
		this.views.remove(view);
	}

	public void removeView(ViewInterface view) {
		this.removeListener(view);
	}

	
	@Override
	public void refreshListener(Event event) {
		for (ListenerInterface view : views) {
			view.refresh(event);
		}
	}
	
	public void refreshViews(Event event) {
		this.refreshListener(event);
	}
	
}
