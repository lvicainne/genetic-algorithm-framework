package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Utils.Reflection.ClassFilter;
import fr.isen.cir56.group3_genetic.Utils.Reflection.PackageHelper;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class UniqueSelectableParameterPanel<ClassType> extends JComboBox<ClassType> {

	public UniqueSelectableParameterPanel(List<String> packageNames, ClassFilter filter) {
		List<Class> foundClasses = new LinkedList<>();
		
		for (String packageName : packageNames) {
			try {
				// Find classes accepted by the filter in the package "some.package" but not in its sub-package.
				Collection<Class<?>> classes = PackageHelper.getInstance().getClasses(packageName, true, filter);
				foundClasses.addAll(classes);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(UniqueSelectableParameterPanel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		for (Class myClass : foundClasses) {
			ClassType myClass2 = (ClassType) myClass;
			this.addItem(myClass2);
			
		}

	}
}
