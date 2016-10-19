package clonepedia.templategeneration.diagram.codegeneration;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import template_model.Class;


public class ClassWizardPage extends DetermineElementWizardPage {
	
	//private final static String PAGE_NAME= "DetermineClassWizardPage";
	private Class classModel;
	
	public ClassWizardPage(Class classModel, String pageName) {
		super(true, pageName);
		
		this.classModel = classModel;
		
		setTitle("Determine the class");
	}
	
	protected void handleFieldChanged(String fieldName) {
		super.handleFieldChanged(fieldName);

		doStatusUpdate();
	}

	private void doStatusUpdate() {
		// status of all used components
		IStatus[] status= new IStatus[] {
			fContainerStatus,
			isEnclosingTypeSelected() ? fEnclosingTypeStatus : fPackageStatus,
			fTypeNameStatus,
			fModifierStatus,
			fSuperClassStatus,
			fSuperInterfacesStatus
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
		createPackageControls(composite, nColumns);
		//createEnclosingTypeControls(composite, nColumns);

		createSeparator(composite, nColumns);

		createTypeNameControls(composite, nColumns);
		createModifierControls(composite, nColumns);

		//createSuperClassControls(composite, nColumns);
		//createSuperInterfacesControls(composite, nColumns);

		setControl(composite);
		
		init(this.classModel);
	}

	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			setFocus();
	}

	
	private void init(Class classModel) {
		
		IPackageFragmentRoot initRoot = CodeSkeletonGenerationUtil.getPackageFragmentRoot();
		if(null != initRoot){
			
			setPackageFragmentRoot(initRoot, true);
			
			String fullName = classModel.getFullName();
			String packageName = (fullName == null)? "" : fullName.substring(0, fullName.lastIndexOf("."));
			
			String name = classModel.getName();
			name = ( name == null)? "" : name;
			
			fPackageDialogField.setText(packageName);
			fTypeNameDialogField.setText(name);
			/*if(clazz.getSuperClass() != null){
				setSuperClass(clazz.getSuperClass().getFullName(), true);				
			}
			
			ArrayList<String> interfaceStringList = new ArrayList<>();
			for(clonepedia.model.ontology.Interface interf: clazz.getImplementedInterfaces()){
				interfaceStringList.add(interf.getFullName());
			}
			
			setSuperInterfaces(interfaceStringList, true);*/
			
			doStatusUpdate();
		}
	}
	
}
