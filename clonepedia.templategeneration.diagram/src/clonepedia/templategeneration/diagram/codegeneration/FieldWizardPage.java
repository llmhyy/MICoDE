package clonepedia.templategeneration.diagram.codegeneration;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import clonepedia.templategeneration.diagram.codegeneration.DetermineElementWizardPage.MethodParameterWrapper;
import template_model.Field;

public class FieldWizardPage extends DetermineElementWizardPage {
	
	private Field fieldModel;
	
	public FieldWizardPage(Field fieldModel, String pageName){
		super(true, pageName);
		this.fieldModel = fieldModel;
		
		setTitle("Determine the field");
	}
	
	protected void handleFieldChanged(String fieldName) {
		super.handleFieldChanged(fieldName);
		if(fieldName == FIELDS){
			fFieldNameStatus = fieldNameChanged();	
			fFieldTypeStatus = fieldTypeChanged();
		}
		doStatusUpdate();
	}
	
	private void doStatusUpdate() {
		// status of all used components
		IStatus[] status= new IStatus[] {
			fFieldTypeStatus,
			fFieldNameStatus
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

		createContainerControls(composite, nColumns);
		createFieldTypeControls(composite, nColumns);
		createFieldNameControls(composite, nColumns);
		
		init();
		
		setControl(composite);
	}
	
	private void init() {
		
		IPackageFragmentRoot initRoot = CodeSkeletonGenerationUtil.getPackageFragmentRoot();
		if(null != initRoot){
			setPackageFragmentRoot(initRoot, true);
			
			String fieldName = this.fieldModel.getName();
			String fieldType = this.fieldModel.getType();
			if(fieldName == null){
				fieldName = "";
			}
			if(fieldType == null){
				fieldType = "";
			}
			setFieldName(fieldName, true);
			setFieldType(fieldType, true);
		}
		
	}
}
