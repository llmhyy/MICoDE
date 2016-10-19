package clonepedia.templategeneration.routine;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.codegeneration.diagram.bean.Application;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;

public class StructureExtractor {
	public ArrayList<Application> extractPrgroamStructure(String[] excludeLocations){
		ArrayList<Application> applications = initialApplications();
		
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		
		for(Application application: applications){
			
			File file = new File(application.getApplicationLocation());
			IContainer[] containers = root.findContainersForLocationURI(file.toURI());
			
			long t1 = System.currentTimeMillis();
			int javaFileNumber = parseProgram(application, containers[0], excludeLocations);
			long t2 = System.currentTimeMillis();
			System.out.println("parsed " + javaFileNumber + " Java files, which takes " + (t2-t1)/1000 + " seconds");
			
			long t3 = System.currentTimeMillis();
			application.buildCallingRelation();
			long t4 = System.currentTimeMillis();
			System.out.println("finshed building calling relations of these " + javaFileNumber + " Java files, which takes " + (t4-t3)/1000 + " seconds");
			
			System.currentTimeMillis();
		}
		
		return applications;
	}
	
	private int parseProgram(Application application, IResource resource, String[] excludeLocations){
		int count = 0;
		if(resource instanceof IFile){
			IFile file = (IFile)resource;
			if(file.getFileExtension().equals("java")){
				ICompilationUnit iunit = JavaCore.createCompilationUnitFrom(file);
				TypeWrapper typeWrapper = parseType(iunit);
				if(typeWrapper != null){
					typeWrapper.setApplication(application);
					application.addType(typeWrapper);					
				}
				
				count = 1;
			}
		}
		else if(resource instanceof IContainer){
			IContainer folder = (IContainer)resource;
			try {
				if(!isFolderInExcludedPaths(folder, excludeLocations)){
					for(IResource res: folder.members()){
						count += parseProgram(application, res, excludeLocations);
					}					
				}
				
				
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	private boolean isFolderInExcludedPaths(IContainer folder, String[] excludeLocations){
		for(String p: excludeLocations){
			
			
			try {
				String folderPath = folder.getLocationURI().toURL().getFile();
				if(null != folderPath && folderPath.startsWith("/")){
					folderPath = folderPath.substring(1, folderPath.length());
					folderPath = folderPath.replace('/', '\\');
				}
				
				
				if(folderPath.startsWith(p)){
					return true;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
//			if(path.isPrefixOf(folderPath)){
//				return 0;
//			}
			
		}
		
		return false;
	}
	
	private TypeWrapper parseType(ICompilationUnit iunit){
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(iunit);
		parser.setResolveBindings(true);
		
		final ArrayList<TypeWrapper> list = new ArrayList<>();
		
		try{
			CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			cu.accept(new ASTVisitor() {
				public boolean visit(TypeDeclaration typeDeclaration){
					TypeWrapper typeWrapper = new TypeWrapper(typeDeclaration);
					list.add(typeWrapper);
					return false;
				}
			});
		}
		catch(java.lang.IndexOutOfBoundsException excep){
			excep.printStackTrace();
			System.out.println(iunit);
		}
		
		if(list.size() != 0){
			return list.get(0);
		}
		else{
			return null;
		}
	}
	
	
	private ArrayList<Application> initialApplications(){
		ArrayList<Application> applications = new ArrayList<>();
		String[] applicationPaths = new String[]{AutoGenCTSettings.retrieveScopePath()};
		for(String applicationLocation: applicationPaths){
			Application application = new Application(applicationLocation);
			applications.add(application);
		}
		
		return applications;
	}
}
