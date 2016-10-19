package template_model.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class ClassClassDeclaredMethodComparment2ItemSemanticEditPolicy
		extends
		template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ClassClassDeclaredMethodComparment2ItemSemanticEditPolicy() {
		super(
				template_model.diagram.providers.Template_generationElementTypes.Class_3004);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (template_model.diagram.providers.Template_generationElementTypes.Method_3005 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.Method5CreateCommand(
					req));
		}
		if (template_model.diagram.providers.Template_generationElementTypes.Field_3007 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.Field2CreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

}