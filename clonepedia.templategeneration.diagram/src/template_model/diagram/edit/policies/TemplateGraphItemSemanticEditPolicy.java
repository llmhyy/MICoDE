package template_model.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class TemplateGraphItemSemanticEditPolicy
		extends
		template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TemplateGraphItemSemanticEditPolicy() {
		super(
				template_model.diagram.providers.Template_generationElementTypes.TemplateGraph_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (template_model.diagram.providers.Template_generationElementTypes.Field_2006 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.Field3CreateCommand(
					req));
		}
		if (template_model.diagram.providers.Template_generationElementTypes.Method_2003 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.MethodCreateCommand(
					req));
		}
		if (template_model.diagram.providers.Template_generationElementTypes.Interface_2001 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.InterfaceCreateCommand(
					req));
		}
		if (template_model.diagram.providers.Template_generationElementTypes.Class_2005 == req
				.getElementType()) {
			return getGEFWrapper(new template_model.diagram.edit.commands.ClassCreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
