package template_model.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

/**
 * @generated
 */
public class Template_generationEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (template_model.diagram.part.Template_generationVisualIDRegistry
					.getVisualID(view)) {

			case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.TemplateGraphEditPart(
						view);

			case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.Field3EditPart(
						view);

			case template_model.diagram.edit.parts.FieldName3EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.FieldName3EditPart(
						view);

			case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.MethodEditPart(
						view);

			case template_model.diagram.edit.parts.MethodNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.MethodNameEditPart(
						view);

			case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.InterfaceEditPart(
						view);

			case template_model.diagram.edit.parts.InterfaceNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.InterfaceNameEditPart(
						view);

			case template_model.diagram.edit.parts.InterfaceFullNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.InterfaceFullNameEditPart(
						view);

			case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ClassEditPart(view);

			case template_model.diagram.edit.parts.ClassNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ClassNameEditPart(
						view);

			case template_model.diagram.edit.parts.ClassFullNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ClassFullNameEditPart(
						view);

			case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.Method3EditPart(
						view);

			case template_model.diagram.edit.parts.MethodName3EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.MethodName3EditPart(
						view);

			case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.Method4EditPart(
						view);

			case template_model.diagram.edit.parts.MethodName4EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.MethodName4EditPart(
						view);

			case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.FieldEditPart(view);

			case template_model.diagram.edit.parts.FieldNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.FieldNameEditPart(
						view);

			case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.Class2EditPart(
						view);

			case template_model.diagram.edit.parts.ClassName2EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ClassName2EditPart(
						view);

			case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.Method5EditPart(
						view);

			case template_model.diagram.edit.parts.MethodName5EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.MethodName5EditPart(
						view);

			case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.Field2EditPart(
						view);

			case template_model.diagram.edit.parts.FieldName2EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.FieldName2EditPart(
						view);

			case template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart(
						view);

			case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart(
						view);

			case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart(
						view);

			case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ExtendInterfaceEditPart(
						view);

			case template_model.diagram.edit.parts.ExtendInterfaceNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ExtendInterfaceNameEditPart(
						view);

			case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.CallEditPart(view);

			case template_model.diagram.edit.parts.CallNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.CallNameEditPart(
						view);

			case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ImplementEditPart(
						view);

			case template_model.diagram.edit.parts.ImplementNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ImplementNameEditPart(
						view);

			case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ExtendClassEditPart(
						view);

			case template_model.diagram.edit.parts.ExtendClassNameEditPart.VISUAL_ID:
				return new template_model.diagram.edit.parts.ExtendClassNameEditPart(
						view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE
				.getTextCellEditorLocator(source);
	}

}