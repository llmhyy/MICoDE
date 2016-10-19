package clonepedia.templategeneration.diagram.codegeneration;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

import template_model.Method;
import clonepedia.templategeneration.diagram.codegeneration.DetermineElementWizardPage.MethodParameterWrapper;

public class MethodConfigureWizard extends Wizard {

	private MethodWizardPage page;
	private Method methodModel;
	
	public MethodConfigureWizard(Method methodModel){
		
		super();
		
		this.methodModel = methodModel;
		
		setNeedsProgressMonitor(true);
		setWindowTitle("Configure Method");
	}
	
	@Override
	public void addPages(){
		page = new MethodWizardPage(this.methodModel, "Determine the method");
		
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getMethodName(){
		return page.getMethodName();
	}
	
	public String getMethodReturnType(){
		return page.getMethodReturnType();
	}
	
	public List<MethodParameterWrapper> getMethodParameters(){
		return page.getMethodParameters();
	}

}
