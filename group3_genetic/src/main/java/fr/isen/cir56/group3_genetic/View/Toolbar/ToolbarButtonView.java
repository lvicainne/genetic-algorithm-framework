package fr.isen.cir56.group3_genetic.View.Toolbar;

import javax.swing.JButton;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ToolbarButtonView extends JButton {
	private ToolbarButtonEnum buttonEnum;
	
	public ToolbarButtonView(ToolbarButtonEnum buttonEnum) {
		super();
		this.setText(buttonEnum.getText());
		this.setIcon(buttonEnum.getIcon());
		this.buttonEnum = buttonEnum;
	}

	public ToolbarButtonEnum getButtonEnum() {
		return buttonEnum;
	}
	
}
