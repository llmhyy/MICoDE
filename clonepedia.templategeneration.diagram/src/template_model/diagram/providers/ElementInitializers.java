package template_model.diagram.providers;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = template_model.diagram.part.Template_generationDiagramEditorPlugin
				.getInstance().getElementInitializers();
		if (cached == null) {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().setElementInitializers(
							cached = new ElementInitializers());
		}
		return cached;
	}
}
