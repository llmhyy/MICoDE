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
public class CallReorientCommand extends EditElementCommand {

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
	public CallReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof template_model.Call) {
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
		if (!(oldEnd instanceof template_model.Method && newEnd instanceof template_model.Method)) {
			return false;
		}
		template_model.Method target = getLink().getCalleeMethod();
		if (!(getLink().eContainer() instanceof template_model.TemplateGraph)) {
			return false;
		}
		template_model.TemplateGraph container = (template_model.TemplateGraph) getLink()
				.eContainer();
		return template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy
				.getLinkConstraints().canExistCall_4007(container, getLink(),
						getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof template_model.Method && newEnd instanceof template_model.Method)) {
			return false;
		}
		template_model.Method source = getLink().getCallerMethod();
		if (!(getLink().eContainer() instanceof template_model.TemplateGraph)) {
			return false;
		}
		template_model.TemplateGraph container = (template_model.TemplateGraph) getLink()
				.eContainer();
		return template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy
				.getLinkConstraints().canExistCall_4007(container, getLink(),
						source, getNewTarget());
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
		getLink().setCallerMethod(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setCalleeMethod(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected template_model.Call getLink() {
		return (template_model.Call) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected template_model.Method getOldSource() {
		return (template_model.Method) oldEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Method getNewSource() {
		return (template_model.Method) newEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Method getOldTarget() {
		return (template_model.Method) oldEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Method getNewTarget() {
		return (template_model.Method) newEnd;
	}
}
