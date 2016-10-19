package clonepedia.templategeneration.diagram.codegeneration;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

import clonepedia.templategeneration.diagram.codegeneration.DetermineElementWizardPage.MethodParameterWrapper;
import template_model.Field;

public class FieldConfigureWizard extends Wizard {

	private FieldWizardPage determineFieldPage;
	private Field fieldModel;
	
	public FieldConfigureWizard(Field fieldModel){
		super();
		this.fieldModel = fieldModel;
		
		setNeedsProgressMonitor(true);
		setWindowTitle("Configure Field");
	}
	
	public void addPages(){
		determineFieldPage = new FieldWizardPage(fieldModel, "Determine the field");
		addPage(determineFieldPage);
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getFieldName(){
		return determineFieldPage.getFieldName();
	}
	
	public String getFieldType(){
		return determineFieldPage.getFieldType();
	}

}
