package template_model.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class ClassClassDeclaredMethodComparmentItemSemanticEditPolicy
		extends
		template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ClassClassDeclaredMethodComparmentItemSemanticEditPolicy() {
		super(
				template_model.diagram.providers.Template_generationElementTypes.Class_2005);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (template_model.diagram.providers.Template_generationElementTypes.Method_3003 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.Method4CreateCommand(
					req));
		}
		if (template_model.diagram.providers.Template_generationElementTypes.Field_3006 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.FieldCreateCommand(
					req));
		}
		if (template_model.diagram.providers.Template_generationElementTypes.Class_3004 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.Class2CreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

}
