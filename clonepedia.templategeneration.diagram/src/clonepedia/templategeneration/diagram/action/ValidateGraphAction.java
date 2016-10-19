package clonepedia.templategeneration.diagram.action;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.PlatformUI;

import template_model.Class;
import template_model.Element;
import template_model.Field;
import template_model.Method;
import template_model.TemplateGraph;
import template_model.Type;
import template_model.diagram.edit.parts.TemplateGraphEditPart;

public class ValidateGraphAction extends AbstractActionDelegate implements IObjectActionDelegate{

	public ValidateGraphAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		IStructuredSelection selection = getStructuredSelection();
		Object selEditPart = selection.getFirstElement();
		
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		Element element = checkGraph(selEditPart);
		if(null == element){
			MessageDialog.openInformation(shell, "Congratulations", "The graph is validated for generate code.");
		}
		else{
			MessageDialog.openError(shell, "Sorry", "The graph has not been completely configured. Please check " + element.getName());
		}
	}
	
	/**
	 * return element which is not complete
	 * @param selEditPart
	 * @return
	 */
	public Element checkGraph(Object selEditPart){
		

		if (selEditPart instanceof TemplateGraphEditPart) {
			TemplateGraphEditPart graph = (TemplateGraphEditPart)selEditPart;
			
			TemplateGraph graphModel = (TemplateGraph)graph.resolveSemanticElement();
			
			for(Element elementModel: graphModel.getElements()){
				if(elementModel instanceof Type){
					Type typeModel = (Type)elementModel;
					
					Element ringleaderElement = checkTypeCompleteness(typeModel);
					if(null != ringleaderElement){
						return ringleaderElement;
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * return the element which is not complete.
	 * @param typeModel
	 * @return
	 */
	private Element checkTypeCompleteness(Type typeModel){
		if(!typeModel.isIsComplete()){
			return typeModel;
		}
		else{
			for(Field fieldModel: typeModel.getFields()){
				if(!fieldModel.isIsComplete()){
					return fieldModel;
				}
			}
			for(Method methodMethod: typeModel.getMethods()){
				if(!methodMethod.isIsComplete()){
					return methodMethod;
				}
			}
			
			if(typeModel instanceof Class){
				Class classModel = (Class)typeModel;
				for(Class innerClass: classModel.getInnerClasses()){
					Element ele = checkTypeCompleteness(innerClass);
					if(ele != null){
						return ele;
					}
				}
			}
		}
		
		return null;
	}

}
