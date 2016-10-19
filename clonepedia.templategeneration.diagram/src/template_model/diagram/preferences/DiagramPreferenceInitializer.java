package template_model.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		template_model.diagram.preferences.DiagramGeneralPreferencePage
				.initDefaults(store);
		template_model.diagram.preferences.DiagramAppearancePreferencePage
				.initDefaults(store);
		template_model.diagram.preferences.DiagramConnectionsPreferencePage
				.initDefaults(store);
		template_model.diagram.preferences.DiagramPrintingPreferencePage
				.initDefaults(store);
		template_model.diagram.preferences.DiagramRulersAndGridPreferencePage
				.initDefaults(store);

	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return template_model.diagram.part.Template_generationDiagramEditorPlugin
				.getInstance().getPreferenceStore();
	}
}
