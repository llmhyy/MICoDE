package clonepedia.templategeneration.diagram.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import template_model.Class;
import template_model.Field;
import template_model.Method;
import template_model.Template_modelPackage;
import template_model.diagram.edit.parts.Class2EditPart;
import template_model.diagram.edit.parts.ClassEditPart;
import template_model.diagram.edit.parts.Field2EditPart;
import template_model.diagram.edit.parts.Field3EditPart;
import template_model.diagram.edit.parts.FieldEditPart;
import template_model.diagram.edit.parts.InterfaceEditPart;
import template_model.diagram.edit.parts.Method3EditPart;
import template_model.diagram.edit.parts.Method4EditPart;
import template_model.diagram.edit.parts.Method5EditPart;
import clonepedia.templategeneration.diagram.codegeneration.ClassConfigureWizard;
import clonepedia.templategeneration.diagram.codegeneration.DetermineElementWizardPage.MethodParameterWrapper;
import clonepedia.templategeneration.diagram.codegeneration.FieldConfigureWizard;
import clonepedia.templategeneration.diagram.codegeneration.MethodConfigureWizard;

public class ConfigureAction  extends AbstractActionDelegate implements IObjectActionDelegate{

	
	
	public ConfigureAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		super.setActivePart(action, targetPart);
		
	}

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		
		IStructuredSelection selection = getStructuredSelection();
		Object selEditPart = selection.getFirstElement();

		if (selEditPart instanceof ClassEditPart || selEditPart instanceof Class2EditPart) {
			ShapeNodeEditPart classEditPart = (ShapeNodeEditPart)selEditPart;
			Class classModel = (Class)classEditPart.resolveSemanticElement();
			
			ClassConfigureWizard wizard = new ClassConfigureWizard(classModel);
			
			WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().getShell(), wizard);
			
			
			if(dialog.open() == Window.OK){
				
				TransactionalEditingDomain domain = classEditPart.getEditingDomain();
				
				String fullName = wizard.getPackageName() + "." + wizard.getResidingClass();
				
				SetRequest setClassFullNameRequest = new SetRequest(domain, classModel, 
						Template_modelPackage.eINSTANCE.getElement_FullName(), fullName);
				SetValueCommand setClassFullNameCommand = new SetValueCommand(setClassFullNameRequest);
				
				SetRequest setClassNameRequest = new SetRequest(domain, classModel, 
						Template_modelPackage.eINSTANCE.getElement_Name(), wizard.getResidingClass());
				SetValueCommand setClassNameCommand = new SetValueCommand(setClassNameRequest);
				
				SetRequest setCompleteRequest = new SetRequest(domain, classModel, 
						Template_modelPackage.eINSTANCE.getElement_IsComplete(), new Boolean(true));
				SetValueCommand seCompleteCommand = new SetValueCommand(setCompleteRequest);
				
				
				classEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setClassFullNameCommand));
				classEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setClassNameCommand));
				classEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(seCompleteCommand));
				
				if(classEditPart instanceof Class2EditPart){
					((Class2EditPart)classEditPart).getPrimaryShape().repaint();
				}
				else if(classEditPart instanceof ClassEditPart){
					((ClassEditPart)classEditPart).getPrimaryShape().repaint();
				}
				
			}
		}
		else if(selEditPart instanceof InterfaceEditPart){
			InterfaceEditPart interfaceEditPart = (InterfaceEditPart)selEditPart;
			
			//TODO
		}
		else if(selEditPart instanceof Method4EditPart || selEditPart instanceof Method3EditPart
				|| selEditPart instanceof Method5EditPart){
			ShapeNodeEditPart methodEditPart = (ShapeNodeEditPart)selEditPart;
			Method methodModel = (Method)methodEditPart.resolveSemanticElement();
			
			MethodConfigureWizard wizard = new MethodConfigureWizard(methodModel);
			WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().getShell(), wizard);
			
			
			if(dialog.open() == Window.OK){
				
				TransactionalEditingDomain domain = methodEditPart.getEditingDomain();
				
				SetRequest setMethodNameRequest = new SetRequest(domain, methodModel, 
						Template_modelPackage.eINSTANCE.getElement_Name(), wizard.getMethodName());
				SetValueCommand setMethodNameCommand = new SetValueCommand(setMethodNameRequest);
				
				SetRequest setMethodReturnTypeRequest = new SetRequest(methodModel, 
						Template_modelPackage.eINSTANCE.getMethod_ReturnType(), wizard.getMethodReturnType());
				SetValueCommand setMethodReturnTypeCommand = new SetValueCommand(setMethodReturnTypeRequest);
				
				List<MethodParameterWrapper> list = wizard.getMethodParameters();
				List<String> paramList = new ArrayList<String>();
				for(MethodParameterWrapper param: list){
					String paramString = param.toString();
					paramList.add(paramString);
					
				}
				
				SetRequest setMethodParametersRequest = new SetRequest(methodModel, 
						Template_modelPackage.eINSTANCE.getMethod_Parameters(), paramList);
				SetValueCommand setParametersCommnad = new SetValueCommand(setMethodParametersRequest);
				
				SetRequest setCompleteRequest = new SetRequest(domain, methodModel, 
						Template_modelPackage.eINSTANCE.getElement_IsComplete(), new Boolean(true));
				SetValueCommand setCompleteCommand = new SetValueCommand(setCompleteRequest);
				
				methodEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setMethodNameCommand));
				methodEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setMethodReturnTypeCommand));
				methodEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setParametersCommnad));
				methodEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setCompleteCommand));
				
				if(methodEditPart instanceof Method3EditPart){
					((Method3EditPart)methodEditPart).getPrimaryShape().repaint();					
				}
				else if(methodEditPart instanceof Method4EditPart){
					((Method4EditPart)methodEditPart).getPrimaryShape().repaint();	
				}
				else if(methodEditPart instanceof Method5EditPart){
					((Method5EditPart)methodEditPart).getPrimaryShape().repaint();	
				}
				//classModel.setName(wizard.getResidingClass());
			}						
		}
		else if(selEditPart instanceof FieldEditPart || selEditPart instanceof Field2EditPart){
			ShapeNodeEditPart fieldEditPart = (ShapeNodeEditPart)selEditPart;
			Field fieldModel = (Field)fieldEditPart.resolveSemanticElement();			
			
			FieldConfigureWizard wizard = new FieldConfigureWizard(fieldModel);
			WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().getShell(), wizard);
			if(dialog.open() == Window.OK){
				TransactionalEditingDomain domain = fieldEditPart.getEditingDomain();
				
				SetRequest setMethodNameRequest = new SetRequest(domain, fieldModel, 
						Template_modelPackage.eINSTANCE.getElement_Name(), wizard.getFieldName());
				SetValueCommand setFieldNameCommand = new SetValueCommand(setMethodNameRequest);
				
				SetRequest setMethodReturnTypeRequest = new SetRequest(fieldModel, 
						Template_modelPackage.eINSTANCE.getField_Type(), wizard.getFieldType());
				SetValueCommand setFieldTypeCommand = new SetValueCommand(setMethodReturnTypeRequest);
				
				SetRequest setCompleteRequest = new SetRequest(domain, fieldModel, 
						Template_modelPackage.eINSTANCE.getElement_IsComplete(), new Boolean(true));
				SetValueCommand setCompleteCommand = new SetValueCommand(setCompleteRequest);
				
				fieldEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setFieldNameCommand));
				fieldEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setFieldTypeCommand));
				fieldEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setCompleteCommand));
				
				if(fieldEditPart instanceof FieldEditPart){
					((FieldEditPart) fieldEditPart).getPrimaryShape().repaint();					
				}
				else if(fieldEditPart instanceof Field2EditPart){
					((Field2EditPart) fieldEditPart).getPrimaryShape().repaint();	
				}
				else if(fieldEditPart instanceof Field3EditPart){
					((Field3EditPart) fieldEditPart).getPrimaryShape().repaint();	
				}
			}
		}
	}

}
