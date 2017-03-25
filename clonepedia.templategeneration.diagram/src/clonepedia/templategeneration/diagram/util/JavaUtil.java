package clonepedia.templategeneration.diagram.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;

import template_model.diagram.util.AutoGenCTSettings;

@SuppressWarnings("restriction")
public class JavaUtil {
	/**
	 * The following two map is used to trade space for time.
	 */
	public static HashMap<String, CompilationUnit> compilationUnitMap = new HashMap<>();
	public static HashMap<String, ICompilationUnit> iCompilationUnitMap = new HashMap<>();
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static CompilationUnit parseCompilationUnit(String file){
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_7, options);
		parser.setCompilerOptions(options);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(false);
		
		try {
			String text = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
			
			parser.setSource(text.toCharArray());
			
			CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			return cu;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	public static String getFullNameOfCompilationUnit(CompilationUnit cu){
		
		String packageName = "";
		if(cu.getPackage() != null){
			packageName = cu.getPackage().getName().toString();
		}
		AbstractTypeDeclaration typeDeclaration = (AbstractTypeDeclaration) cu.types().get(0);
		String typeName = typeDeclaration.getName().getIdentifier();
		
		if(packageName.length() == 0){
			return typeName;
		}
		else{
			return packageName + "." + typeName; 			
		}
		
	}
	
	
	public static CompilationUnit findCompilationUnitInProject(String qualifiedName){
		CompilationUnit cu = compilationUnitMap.get(qualifiedName);
		if(null == cu){
			try{
				ICompilationUnit icu = findICompilationUnitInProject(qualifiedName);
				cu = convertICompilationUnitToASTNode(icu);	
				compilationUnitMap.put(qualifiedName, cu);
				return cu;
			}
			catch(IllegalStateException e){
				e.printStackTrace();
			} 
		}
		
		return cu;
	} 
	
	public static ICompilationUnit findICompilationUnitInProject(String qualifiedName){
		ICompilationUnit icu = iCompilationUnitMap.get(qualifiedName);
		if(null == icu){
			IJavaProject project = JavaCore.create(getSpecificJavaProjectInWorkspace());
			try {
				IType type = project.findType(qualifiedName);
				if(type == null){
					type = project.findType(qualifiedName, new NullProgressMonitor());
				}
				
				if(type != null){
					icu = type.getCompilationUnit();
					iCompilationUnitMap.put(qualifiedName, icu);
				}
				
			} catch (JavaModelException e1) {
				e1.printStackTrace();
			}
		}
		
		return icu;
	}
	
	public static CompilationUnit findNonCacheCompilationUnitInProject(String qualifiedName){
		ICompilationUnit icu = findNonCacheICompilationUnitInProject(qualifiedName);
		CompilationUnit cu = convertICompilationUnitToASTNode(icu);
		
		return cu;
	}
	
	
	public static ICompilationUnit findNonCacheICompilationUnitInProject(String qualifiedName) {
		IJavaProject project = JavaCore.create(getSpecificJavaProjectInWorkspace());
		try {
			IType type = project.findType(qualifiedName);
			if(type != null){
				return  type.getCompilationUnit();
			}
			
		} catch (JavaModelException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public static IPackageFragmentRoot findTestPackageRootInProject(){
		IJavaProject project = JavaCore.create(getSpecificJavaProjectInWorkspace());
		try {
			for(IPackageFragmentRoot packageFragmentRoot: project.getPackageFragmentRoots()){
				if(!(packageFragmentRoot instanceof JarPackageFragmentRoot) && packageFragmentRoot.getResource().toString().contains("test")){
					
					return packageFragmentRoot;
//					IPackageFragment packageFrag = packageFragmentRoot.getPackageFragment(packageName);
//					
//					String fragName = packageFrag.getElementName();
//					if(packageFrag.exists() && fragName.equals(packageName)){
//						return packageFrag;
//					}
					
				}
			}
			
		} catch (JavaModelException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static CompilationUnit convertICompilationUnitToASTNode(ICompilationUnit iunit){
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_7, options);
		parser.setCompilerOptions(options);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setSource(iunit);
		
		CompilationUnit cu = null;
		try{
			cu = (CompilationUnit) parser.createAST(null);		
			return cu;
		}
		catch(java.lang.IllegalStateException e){
			return null;
		}
	}
	
	public static IProject getSpecificJavaProjectInWorkspace(){
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		
		for(int i=0; i<projects.length; i++){
			if(AutoGenCTSettings.retrieveTargetProject().equals(projects[i].getName())){
				return projects[i];
				//return JavaCore.create(projects[i]);
			}
		}
		
		return null;
	}

	public static boolean isTheLocationHeadOfClass(String sourceName, int lineNumber) {
		CompilationUnit cu = findCompilationUnitInProject(sourceName);
		AbstractTypeDeclaration type = (AbstractTypeDeclaration) cu.types().get(0);
		int headLine = cu.getLineNumber(type.getName().getStartPosition());
		
		return headLine==lineNumber;
	}

	public static boolean isCompatibleMethodSignature(String thisSig, String thatSig) {
		if(thatSig.equals(thisSig)){
			return true;
		}
		
		String thisClassName = thisSig.substring(0, thisSig.indexOf("#"));
		String thisMethodSig = thisSig.substring(thisSig.indexOf("#")+1, thisSig.length());
		
		String thatClassName = thatSig.substring(0, thatSig.indexOf("#"));
		String thatMethodSig = thatSig.substring(thatSig.indexOf("#")+1, thatSig.length());
		
		if(thisMethodSig.equals(thatMethodSig)){
			CompilationUnit thisCU = JavaUtil.findCompilationUnitInProject(thisClassName);
			CompilationUnit thatCU = JavaUtil.findCompilationUnitInProject(thatClassName);
			
			if(thisCU==null || thatCU==null){
				return true;
			}
			
			AbstractTypeDeclaration thisType = (AbstractTypeDeclaration) thisCU.types().get(0);
			AbstractTypeDeclaration thatType = (AbstractTypeDeclaration) thatCU.types().get(0);
			
			ITypeBinding thisTypeBinding = thisType.resolveBinding();
			ITypeBinding thatTypeBinding = thatType.resolveBinding();
			
			boolean isSame = thisTypeBinding.getQualifiedName().equals(thatTypeBinding.getQualifiedName());
			
			if(isSame){
				return true;
			}
			else{
				boolean isCom1 = thisTypeBinding.isSubTypeCompatible(thatTypeBinding);
				boolean isCom2 = thatTypeBinding.isSubTypeCompatible(thisTypeBinding);
				
				return isCom1 || isCom2;				
			}
		}
		
		return false;
	}
	
}
