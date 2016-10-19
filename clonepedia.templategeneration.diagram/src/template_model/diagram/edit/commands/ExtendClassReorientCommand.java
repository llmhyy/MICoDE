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
public class ExtendClassReorientCommand extends EditElementCommand {

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
	public ExtendClassReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof template_model.ExtendClass) {
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
		if (!(oldEnd instanceof template_model.Class && newEnd instanceof template_model.Class)) {
			return false;
		}
		template_model.Class target = getLink().getSuperClass();
		if (!(getLink().eContainer() instanceof template_model.TemplateGraph)) {
			return false;
		}
		template_model.TemplateGraph container = (template_model.TemplateGraph) getLink()
				.eContainer();
		return template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy
				.getLinkConstraints().canExistExtendClass_4010(container,
						getLink(), getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof template_model.Class && newEnd instanceof template_model.Class)) {
			return false;
		}
		template_model.Class source = getLink().getSubClass();
		if (!(getLink().eContainer() instanceof template_model.TemplateGraph)) {
			return false;
		}
		template_model.TemplateGraph container = (template_model.TemplateGraph) getLink()
				.eContainer();
		return template_model.diagram.edit.policies.Template_generationBaseItemSemanticEditPolicy
				.getLinkConstraints().canExistExtendClass_4010(container,
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
		getLink().setSubClass(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setSuperClass(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected template_model.ExtendClass getLink() {
		return (template_model.ExtendClass) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected template_model.Class getOldSource() {
		return (template_model.Class) oldEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Class getNewSource() {
		return (template_model.Class) newEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Class getOldTarget() {
		return (template_model.Class) oldEnd;
	}

	/**
	 * @generated
	 */
	protected template_model.Class getNewTarget() {
		return (template_model.Class) newEnd;
	}
}
