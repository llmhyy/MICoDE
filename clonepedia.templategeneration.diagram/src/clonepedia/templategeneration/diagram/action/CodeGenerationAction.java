package clonepedia.templategeneration.diagram.action;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.jdt.internal.corext.util.CodeFormatterUtil;
import org.eclipse.jdt.internal.corext.util.Strings;
import org.eclipse.jdt.internal.ui.javaeditor.ASTProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.PlatformUI;

import template_model.Class;
import template_model.Element;
import template_model.Field;
import template_model.Interface;
import template_model.Method;
import template_model.TemplateGraph;
import template_model.Type;
import template_model.diagram.edit.parts.TemplateGraphEditPart;
import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.codegeneration.diagram.bean.FieldWrapper;
import clonepedia.codegeneration.diagram.bean.MethodWrapper;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.templategeneration.diagram.codegeneration.CodeSkeletonGenerationUtil;

public class CodeGenerationAction extends AbstractActionDelegate implements IObjectActionDelegate{

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		IStructuredSelection selection = getStructuredSelection();
		Object selEditPart = selection.getFirstElement();

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		if(new ValidateGraphAction().checkGraph(selEditPart) == null){
			if (selEditPart instanceof TemplateGraphEditPart) {
				TemplateGraphEditPart graph = (TemplateGraphEditPart)selEditPart;
				TemplateGraph graphModel = (TemplateGraph)graph.resolveSemanticElement();
				
				for(Element elementModel: graphModel.getElements()){
					
					if(elementModel instanceof Type){
						Type typeModel = (Type)elementModel;
						try {
							if(typeModel.getMethods().size() != 0){
								createType(progressMonitor, typeModel);							
							}
						} catch (CoreException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}
		}
		else{
			MessageDialog.openError(shell, "Sorry", "The graph has not been completely configured.");
		}
		
	}

	private void createType(IProgressMonitor progressMonitor, Type typeModel) 
			throws CoreException, InterruptedException {
		String typeFullName = typeModel.getFullName();
		String packageName = typeFullName.substring(0, typeFullName.lastIndexOf("."));
		
		IPackageFragment pack = createPackage(packageName);
		ICompilationUnit cu = pack.getCompilationUnit(typeModel.getName() + ".java");
		
		try{
			cu.becomeWorkingCopy(new NullProgressMonitor());
			
			cu.createPackageDeclaration(packageName, new NullProgressMonitor());
			IType createdType = null;
			
			if(typeModel instanceof Class){
				Class classModel = (Class)typeModel;
				createdType = createClassContent(classModel, cu, null);
			}
			else if(typeModel instanceof Interface){
				
			}
			
			if(createdType != null){
				formatCode(createdType, cu, pack);
			}
			
			cu.commitWorkingCopy(true, new NullProgressMonitor());
			cu.discardWorkingCopy();
		}
		finally{
			cu.discardWorkingCopy();
		}
	}
	
	@SuppressWarnings("restriction")
	private void formatCode(IType createdType, ICompilationUnit cu, IPackageFragment pack) throws JavaModelException{
		int indent = 0;
		String lineDelimiter = StubUtility.getLineDelimiterUsed(pack.getJavaProject());
		
		ISourceRange range = createdType.getSourceRange();
		
		IBuffer buf = cu.getBuffer();
		String originalContent = buf.getText(range.getOffset(), range.getLength());
		
		String formattedContent = CodeFormatterUtil.format(
				CodeFormatter.K_CLASS_BODY_DECLARATIONS, originalContent,
				indent, lineDelimiter, pack.getJavaProject());
		formattedContent = Strings.trimLeadingTabsAndSpaces(formattedContent);
		buf.replace(range.getOffset(), range.getLength(), formattedContent);
	}
	
	private IType createClassContent(Class classModel, ICompilationUnit cu, IType ownerType) throws JavaModelException{
		
		String typeStub = constructTypeStub(classModel);
		
		IType type = null;
		if(cu != null){
			type = cu.createType(typeStub, null, false, new NullProgressMonitor());
		}
		else if(ownerType != null){
			type = ownerType.createType(typeStub, null, false, new NullProgressMonitor());
		} 
		
		for(Field fieldModel: classModel.getFields()){
			String fieldString = mergeFieldCode(fieldModel);
			type.createField(fieldString, null, false, new NullProgressMonitor());
		}
		
		for(Method methodModel: classModel.getMethods()){
			String methodString = mergeMethodCode(methodModel);
			type.createMethod(methodString, null, false, new NullProgressMonitor());
		}
		
		for(Class innerClassModel: classModel.getInnerClasses()){
			createClassContent(innerClassModel, null, type);
		}
		
		return type;
	}
	
	private String mergeMethodCode(Method methodModel) {
		//TODO
		if(AutoGenCTSettings.designs.size() != 0){
			Multiset fieldSet = AutoGenCTSettings.designs.
					findMultiset(methodModel.getDescription());
			MethodWrapper methodWrapper = (MethodWrapper)(fieldSet.getCorrespondingSet().get(0));
			return methodWrapper.getMethodDeclaration().toString();
		}
		else {
			StringBuffer paramsBuffer = new StringBuffer();
			for(String param: methodModel.getParameters()){
				String[] p = param.split(":");
				
				String type = p[0];
				String name = p[1];
				
				paramsBuffer.append(type + " " + name + ", ");
			}
			if(paramsBuffer.length() != 0){
				paramsBuffer.delete(paramsBuffer.length()-3, paramsBuffer.length()-1);
			}
			return "public " + methodModel.getReturnType() + " " + methodModel.getName() + "("
					+ paramsBuffer.toString() + "){}";
		}
	}

	private String mergeFieldCode(Field fieldModel){
		//TODO
		if(AutoGenCTSettings.designs.size() != 0){
			Multiset fieldSet = AutoGenCTSettings.designs.
					findMultiset(fieldModel.getDescription());
			FieldWrapper fieldWrapper = (FieldWrapper)(fieldSet.getCorrespondingSet().get(0));
			return fieldWrapper.getFieldDeclaration().toString();
		}
		else {
			return "private " + fieldModel.getType() + " " + fieldModel.getName() + ";";
		}
	}
	
	private IType createInterfaceContent(Interface interfaceModel){
		//TODO
		
		return null;
	}
	
	private String constructTypeStub(Type typeModel) {
		StringBuffer buf = new StringBuffer();
		if(typeModel instanceof Class){
			Class classModel = (Class) typeModel;
			buf.append("public class " + classModel.getName() + " ");
			if(classModel.getSuperClass() != null){
				Class superClass = classModel.getSuperClass();
				buf.append("extends " + superClass.getName() + " ");
			}
			
			if(classModel.getInterfaces().size() != 0){
				buf.append("implements ");
				for(Interface interfaceModel: classModel.getInterfaces()){
					buf.append(interfaceModel.getName() + ", ");
				}
				buf.delete(buf.length()-2, buf.length()-1);
			}
		}
		else if(typeModel instanceof Interface){
			Interface interfaceModel = (Interface) typeModel;
			buf.append("public interface " + interfaceModel.getName() + " ");
			
			if(interfaceModel.getSuperInterfaces().size() != 0){
				buf.append("extends ");
				for(Interface superInterface: interfaceModel.getSuperInterfaces()){
					buf.append(superInterface.getName() + ", ");
				}
				buf.delete(buf.length()-2, buf.length()-1);
			}
		}
		
		buf.append("{ }"); 
		return buf.toString();
	}
	
	private CompilationUnit createASTForImports(ICompilationUnit cu) {
		ASTParser parser = ASTParser.newParser(ASTProvider.SHARED_AST_LEVEL);
		parser.setSource(cu);
		parser.setResolveBindings(true);
		parser.setFocalPosition(0);
		return (CompilationUnit) parser.createAST(null);
	}
	
	private ICompilationUnit createCompilationUnit(IPackageFragment pack, String typeName) throws JavaModelException{
		ICompilationUnit cu = pack.getCompilationUnit(typeName);
		if (!cu.exists()) {
			cu = pack.createCompilationUnit(typeName, "", false, new NullProgressMonitor());
		}
		
		return cu;
	}
	
	private IPackageFragment createPackage(String packageName) throws JavaModelException{
		IPackageFragmentRoot root = CodeSkeletonGenerationUtil.getPackageFragmentRoot();
		IPackageFragment pack = root.getPackageFragment(packageName);
		if (pack == null) {
			pack = root.getPackageFragment(""); //$NON-NLS-1$
		}

		if (!pack.exists()) {
			String packName = pack.getElementName();
			pack = root.createPackageFragment(packName, true, new NullProgressMonitor());
		} 
		
		return pack;
	}

}
