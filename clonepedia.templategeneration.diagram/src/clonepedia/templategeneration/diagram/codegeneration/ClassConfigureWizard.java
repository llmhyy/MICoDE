package clonepedia.templategeneration.diagram.codegeneration;


import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.wizard.Wizard;

import template_model.Class;


@SuppressWarnings("restriction")
public class ClassConfigureWizard extends Wizard {
	
	private ClassWizardPage determineClassPage;
	private Class classModel;
	
	public ClassConfigureWizard(Class classModel){
		super();
		
		this.classModel = classModel;
		
	    setNeedsProgressMonitor(true);
		setWindowTitle("Configure Class");
	}
	
	@Override
	public void addPages(){
		determineClassPage = new ClassWizardPage(this.classModel, "Determine the class");
		
		addPage(determineClassPage);
	}
	
	@Override
	public boolean performFinish() {
		
		//warnAboutTypeCommentDeprecation();
		//boolean res= super.performFinish();
		/*if (res) {
			IResource resource= fPage.getModifiedResource();
			if (resource != null) {
				selectAndReveal(resource);
				openResource((IFile) resource);
			}
		}*/
		return true;
	}
	
	public String getPackageName(){
		return this.determineClassPage.getPackageText();
	}

	public String getResidingClass(){
		return this.determineClassPage.getTypeName();
	}
	
	public String getSuperClass(){
		return this.determineClassPage.getSuperClass();
	}
	
	public List<String> getInterfaces(){
		return this.determineClassPage.getSuperInterfaces();
	}
	
	

	
}
