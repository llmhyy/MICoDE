package template_model.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import template_model.Class;
import template_model.ExtendClass;
import template_model.ExtendInterface;
import template_model.Implement;
import template_model.Interface;

public class RelationDestroyCommand extends DestroyElementCommand {

	public RelationDestroyCommand(DestroyElementRequest request) {
		super(request);
		
		//removeCorrespondingProperty();
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {

		removeCorrespondingProperty();
		
		EObject destructee = getElementToDestroy();

		// only destroy attached elements
		if ((destructee != null) && (destructee.eResource() != null)) {
			// tear down incoming references
			tearDownIncomingReferences(destructee);

			// also tear down outgoing references, because we don't want
			// reverse-reference lookups to find destroyed objects
			tearDownOutgoingReferences(destructee);

			// remove the object from its container
			EcoreUtil.remove(destructee);

			// in case it was cross-resource-contained
			Resource res = destructee.eResource();
			if (res != null) {
				res.getContents().remove(destructee);
			}
		}


		return CommandResult.newOKCommandResult();
	}
	
	private void removeCorrespondingProperty(){
		if(getElementToDestroy() instanceof ExtendClass){
			ExtendClass extend = (ExtendClass)getElementToDestroy();
			Class subClass = extend.getSubClass();
			
			subClass.setSuperClass(null);
		}
		else if(getElementToDestroy() instanceof Implement){
			Implement implement = (Implement)getElementToDestroy();
			
			Class clazz = implement.getClass_();
			Interface interf = implement.getInterface().get(0);
			
			clazz.getInterfaces().remove(interf);
		}
		else if(getElementToDestroy() instanceof ExtendInterface){
			ExtendInterface extendInterface = (ExtendInterface)getElementToDestroy();
			
			Interface subInterface = extendInterface.getSubInterface();
			Interface superInterface = extendInterface.getSuperInterface().get(0);
			
			subInterface.getSuperInterfaces().remove(superInterface);
		}
	}
}
