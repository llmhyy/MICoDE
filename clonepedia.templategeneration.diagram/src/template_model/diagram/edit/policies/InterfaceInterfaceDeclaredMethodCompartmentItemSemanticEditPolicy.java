package template_model.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class InterfaceInterfaceDeclaredMethodCompartmentItemSemanticEditPolicy
		extends
		template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public InterfaceInterfaceDeclaredMethodCompartmentItemSemanticEditPolicy() {
		super(
				template_model.diagram.providers.Template_generationElementTypes.Interface_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (template_model.diagram.providers.Template_generationElementTypes.Method_3002 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.Method3CreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

}
