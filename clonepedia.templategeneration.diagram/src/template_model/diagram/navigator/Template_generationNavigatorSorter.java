package template_model.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class Template_generationNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7006;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof template_model.diagram.navigator.Template_generationNavigatorItem) {
			template_model.diagram.navigator.Template_generationNavigatorItem item = (template_model.diagram.navigator.Template_generationNavigatorItem) element;
			return template_model.diagram.part.Template_generationVisualIDRegistry
					.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
