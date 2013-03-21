/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Configurator.Selector;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;
import fr.isen.cir56.group3_genetic.Model.ModelInterface;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

/**
 * A selector panel for getting from a JLabel, a Slide and a TextFiel 
 * an integer value.
 * This class is the controller for the other JPanel components (which are the views)
 * A small MVC.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class SelectorPanel extends JPanel implements ControllerInterface  {

	private JLabel label;
	private JSlider slider;
	private JTextField editor;
	private int maxValue;
	private int minValue;
	private int currentValue;
	private final EventListenerList listeners = new EventListenerList();

	public SelectorPanel(String label, int min, int max, int defaultValue) {
		this.label = new JLabel(label);

		this.slider = new JSlider(min, max, defaultValue);
		this.slider.setMajorTickSpacing(max / 3);
		this.slider.setMinorTickSpacing(max / 6);
		this.slider.setPaintTicks(true);
		this.slider.setPaintLabels(true);

		this.editor = new JTextField(defaultValue);
		this.editor.setColumns(5);
		this.editor.setSize(new Dimension(30, 20));

		this.currentValue = defaultValue;
		this.maxValue = max;
		this.minValue = min;

		this.slider.addChangeListener(new SelectorSliderChangedEvent(this));
		this.editor.addActionListener(new SelectorEditorChangedEvent(this));

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(this.label);
		this.add(this.slider);
		this.add(this.editor);

		this.update(); //initialize well the editor

	}

	public final void update() {
		this.editor.setText(String.valueOf(currentValue));
		this.slider.setValue(currentValue);
	}

	public void setCurrentValue(int value) {
		if (value < this.minValue || value > this.maxValue) {
			//We DO NO change the value if the ne one does NOT fit
			return;
		}
		int oldValue = this.currentValue;
		this.currentValue = value;
		this.fireValueChanged(oldValue, value);
		this.update();
	}

	public int getCurrentValue() {
		return this.currentValue;
	}

	protected JSlider getSlider() {
		return slider;
	}

	protected JTextField getEditor() {
		return editor;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
		this.slider.setMaximum(maxValue);
		this.slider.setMajorTickSpacing(maxValue / 3);
		this.slider.setMinorTickSpacing(maxValue / 6);
		if(this.currentValue > this.maxValue) {
			this.setCurrentValue(maxValue);
		}
	}

	public void addValueChangedListener(ValueChangedListener listener) {
		listeners.add(ValueChangedListener.class, listener);
	}

	public void removeValueChangedListener(ValueChangedListener listener) {
		listeners.remove(ValueChangedListener.class, listener);
	}

	public ValueChangedListener[] getSelectorChangedListener() {
		return listeners.getListeners(ValueChangedListener.class);
	}

	protected void fireValueChanged(int oldValue, int newValue) {
		for (ValueChangedListener listener : getSelectorChangedListener()) {
			listener.valueChanged(oldValue, newValue);
		}
	}
	
	@Override
	public void setEnabled(boolean e) {
		super.setEnabled(e);
		this.editor.setEnabled(e);
		this.slider.setEnabled(e);
		this.label.setEnabled(e);
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
