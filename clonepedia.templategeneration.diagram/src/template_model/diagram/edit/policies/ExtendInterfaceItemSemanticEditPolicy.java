package template_model.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import template_model.diagram.edit.commands.RelationDestroyCommand;

/**
 * @generated
 */
public class ExtendInterfaceItemSemanticEditPolicy
		extends
		template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ExtendInterfaceItemSemanticEditPolicy() {
		super(
				template_model.diagram.providers.Template_generationElementTypes.ExtendInterface_4006);
	}

	/**
	 * @generated NOT
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		//return getGEFWrapper(new DestroyElementCommand(req));
		return getGEFWrapper(new RelationDestroyCommand(req));
	}

}
