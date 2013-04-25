package fr.isen.cir56.group3_genetic.Wizard.Configurator.ParameterPanel;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;
import fr.isen.cir56.group3_genetic.Model.ModelInterface;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.Selector.SelectorPanel;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ParameterPanel<ClassType extends Object> extends JPanel implements ControllerInterface {

	public static double MINIMUM_PROBABILITY = 0;
	public static double MAXIMUM_PROBABILITY = 100;
	private JCheckBox checkbox;
	private SelectorPanel selectorPanel;
	private boolean selected = false;
	private double currentProbability;
	private final EventListenerList listeners = new EventListenerList();
	private final ClassType parameterClass;

	public ParameterPanel(ClassType parameterClass, String text, double defaultPercentageProbability) {
		this.checkbox = new JCheckBox();
		this.selectorPanel = new SelectorPanel(text, (int) MINIMUM_PROBABILITY, (int) MAXIMUM_PROBABILITY, (int) defaultPercentageProbability);
		this.parameterClass = parameterClass;


		this.checkbox.addActionListener(new ParameterCheckboxChangedListener(this));
		this.selectorPanel.addValueChangedListener(new ParameterProbabilityChangedListener(this));

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(this.checkbox);
		this.add(this.selectorPanel);

		this.setSelected(this.selected);
		this.setProbability(this.currentProbability);
	}

	public void setProbability(double p) {
		this.currentProbability = p;
		this.fireParameterChanged(selected, this.currentProbability);
	}

	public double getProbability() {
		return this.currentProbability;
	}

	public void addParameterPanelChangedListener(ParameterPanelChangedListener listener) {
		listeners.add(ParameterPanelChangedListener.class, listener);
	}

	public void removeParameterPanelChangedListener(ParameterPanelChangedListener listener) {
		listeners.remove(ParameterPanelChangedListener.class, listener);
	}

	public ParameterPanelChangedListener[] getParameterChangedListener() {
		return listeners.getListeners(ParameterPanelChangedListener.class);
	}

	protected void fireParameterChanged(boolean isSelected, double newProbability) {
		for (ParameterPanelChangedListener listener : getParameterChangedListener()) {
			listener.parameterChanged(isSelected, newProbability);
		}
	}

	/**
	 * If true, the class is selected and the checkbox too
	 *
	 * @param s
	 */
	public void setSelected(boolean s) {
		this.checkbox.setSelected(s); //the checkbox has to be always ENABLE to (dis)enable the other compoents
		this.selectorPanel.setEnabled(s);
		this.selected = s;
		this.fireParameterChanged(s, this.currentProbability);
	}

	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setEnabled(boolean e) {
		super.setEnabled(e);
		this.checkbox.setEnabled(e); //the checkbox has to be always ENABLE to (dis)enable the other compoents

		this.selectorPanel.setEnabled(e);
		if (e) {
			if (this.isSelected()) {
				this.selectorPanel.setEnabled(true);
			} else {
				this.selectorPanel.setEnabled(false);
			}
		}
	}

	public JCheckBox getCheckbox() {
		return checkbox;
	}

	public SelectorPanel getSelectorPanel() {
		return selectorPanel;
	}

	public ClassType getParameterClass() {
		return parameterClass;
	}

	@Override
	public void setModel(ModelInterface model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ModelInterface getModel() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
