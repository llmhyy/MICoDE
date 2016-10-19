package template_model.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class ExtendInterfaceReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public ExtendInterfaceReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof template_model.ExtendInterface) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof template_model.Interface && newEnd instanceof template_model.Interface)) {
			return false;
		}
		if (getLink().getSuperInterface().size() != 1) {
			return false;
		}
		template_model.Interface target = (template_model.Interface) getLink()
				.getSuperInterface().get(0);
		if (!(getLink().eContainer() instanceof template_model.TemplateGraph)) {
			return false;
		}
		template_model.TemplateGraph container = (template_model.TemplateGraph) getLink()
				.eContainer();
		return template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy
				.getLinkConstraints().canExistExtendInterface_4006(container,
						getLink(), getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof template_model.Interface && newEnd instanceof template_model.Interface)) {
			return false;
		}
		template_model.Interface source = getLink().getSubInterface();
		if (!(getLink().eContainer() instanceof template_model.TemplateGraph)) {
			return false;
		}
		template_model.TemplateGraph container = (template_model.TemplateGraph) getLink()
				.eContainer();
		return template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy
				.getLinkConstraints().canExistExtendInterface_4006(container,
						getLink(), source, getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setSubInterface(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().getSuperInterface().remove(getOldTarget());
		getLink().getSuperInterface().add(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected template_model.ExtendInterface getLink() {
		return (template_model.ExtendInterface) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected template_model.Interface getOldSource() {
		return (template_model.Interface) oldEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Interface getNewSource() {
		return (template_model.Interface) newEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Interface getOldTarget() {
		return (template_model.Interface) oldEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Interface getNewTarget() {
		return (template_model.Interface) newEnd;
	}
}
