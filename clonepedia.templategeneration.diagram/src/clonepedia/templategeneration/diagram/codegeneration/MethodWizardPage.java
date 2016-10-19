package clonepedia.templategeneration.diagram.codegeneration;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import template_model.Class;
import template_model.Method;


public class MethodWizardPage extends DetermineElementWizardPage {

	private Method methodModel;
	
	public MethodWizardPage(Method methodModel, String pageName) {
		super(true, pageName);
		
		this.methodModel = methodModel;
		
		setTitle("Determine the method");
	}
	
	protected void handleFieldChanged(String fieldName) {
		if (fieldName == CONTAINER) {
			fMethodReturnTypeStatus = methodReturnTypeChanged();
			fMethodNameStatus = methodNameChanged();
			fMethodParametersStatus = methodParametersChanged();
		}
		else if(fieldName == METHODS){
			fMethodNameStatus = methodNameChanged();	
			fMethodParametersStatus = methodParametersChanged();
		}

		doStatusUpdate();
	}

	private void doStatusUpdate() {
		// status of all used components
		IStatus[] status= new IStatus[] {
			fMethodReturnTypeStatus,
			fMethodNameStatus,
			fMethodParametersStatus
		};

		// the mode severe status will be displayed and the OK button enabled/disabled.
		updateStatus(status);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite composite= new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());

		int nColumns= 4;

		GridLayout layout= new GridLayout();
		layout.numColumns= nColumns;
		composite.setLayout(layout);

		// pick & choose the wanted UI components
		
		createContainerControls(composite, nColumns);
		
		//createPackageControls(composite, nColumns);
		
		createMethodReturnTypeControls(composite, nColumns);
		createMethodNameControls(composite, nColumns);
		createMethodParametersControls(composite, nColumns);
		
		init();
		
		setControl(composite);
	}

	private void init() {
		
		IPackageFragmentRoot initRoot = CodeSkeletonGenerationUtil.getPackageFragmentRoot();
		if(null != initRoot){
			setPackageFragmentRoot(initRoot, true);
			
			String methodName = this.methodModel.getName();
			String returnTypeName = this.methodModel.getReturnType();
			if(methodName == null){
				methodName = "";
			}
			if(returnTypeName == null){
				returnTypeName = "";
			}
			setMethodName(methodName, true);
			setMethodReturnType(returnTypeName, true);
			
			ArrayList<MethodParameterWrapper> paramList = new ArrayList<MethodParameterWrapper>();
			for(String p: this.methodModel.getParameters()){
				String[] param = p.split(":");
				String type = param[0];
				String name = param[1];
				paramList.add(new MethodParameterWrapper(type, name));
			}
			
			setMethodParameters(paramList, true);
		}
		
	}

}
