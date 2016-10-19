package clonepedia.templategeneration.diagram.action;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eposoft.jccd.data.ASourceUnit;
import org.eposoft.jccd.data.JCCDFile;
import org.eposoft.jccd.data.SimilarityGroup;
import org.eposoft.jccd.data.SimilarityGroupManager;
import org.eposoft.jccd.data.SourceUnitPosition;
import org.eposoft.jccd.data.ast.ANode;
import org.eposoft.jccd.data.ast.NodeTypes;
import org.eposoft.jccd.detectors.APipeline;
import org.eposoft.jccd.detectors.ASTDetector;
import org.eposoft.jccd.preprocessors.java.GeneralizeArrayInitializers;
import org.eposoft.jccd.preprocessors.java.GeneralizeClassDeclarationNames;
import org.eposoft.jccd.preprocessors.java.GeneralizeMethodArgumentTypes;
import org.eposoft.jccd.preprocessors.java.GeneralizeMethodCallNames;
import org.eposoft.jccd.preprocessors.java.GeneralizeMethodReturnTypes;
import org.eposoft.jccd.preprocessors.java.GeneralizeVariableDeclarationTypes;
import org.eposoft.jccd.preprocessors.java.GeneralizeVariableNames;

import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.filepraser.CloneDetectionFileWriter;
import clonepedia.model.ontology.CloneInstance;
import clonepedia.model.ontology.CloneSet;
import clonepedia.model.ontology.CloneSets;
import clonepedia.util.MinerProperties;
import clonepedia.util.Settings;

public class CloneDetectionAction implements IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		Job job = new Job("detecting code clones"){

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				detectMultiPackageClone();
				//detectMultiProjectClone();
				return Status.OK_STATUS;
			}
			
		};
		job.schedule();

	}
	
	private CloneSets clearCloneSets(CloneSets sets){
		Iterator<CloneSet> setIterator = sets.getCloneList().iterator();
		while(setIterator.hasNext()){
			CloneSet set = setIterator.next();
			Iterator<CloneInstance> instanceIterator = set.iterator();
			while(instanceIterator.hasNext()){
				CloneInstance instance = instanceIterator.next();
				
				if(!isAnInsterestingInstance(instance)){
					instanceIterator.remove();
				}
			}
			
			if(set.size() < 2 || isALocalCloneSet(set)){
				setIterator.remove();
			}
		}
		
		return sets;
	}
	
	private boolean isALocalCloneSet(CloneSet set){
		String[] projectLocation = new String[]{AutoGenCTSettings.retrieveScopePath()};
		for(String filePath: projectLocation){
			if(isAllCloneInstanceInThisProject(set, filePath)){
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isAllCloneInstanceInThisProject(CloneSet set, String filePath){
		for(CloneInstance instance: set){
			if(!instance.getFileLocation().contains(filePath)){	
				return false;
			}
		}
		return true;
	}
	
	private boolean isAnInsterestingInstance(CloneInstance instance){
		String[] projectLocation = new String[]{AutoGenCTSettings.retrieveScopePath()};
		for(String filePath: projectLocation){
			if(instance.getFileLocation().contains(filePath)){
				return true;
			}
		}
		return false;
	}
	
	private void detectMultiPackageClone(){
		try {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject proj = root.getProject(Settings.projectName);
			if (proj.isNatureEnabled(MinerProperties.javaNatureName)) {
				IJavaProject javaProject = JavaCore.create(proj);
				IPackageFragment[] packages = javaProject.getPackageFragments();
				
				ArrayList<JCCDFile> fileList = new ArrayList<JCCDFile>();
				
				/*fileList.add(new JCCDFile(("F:\\git_space\\JHotDraw7\\jhotdraw7\\src\\main\\java\\"
						+ "org\\jhotdraw\\gui\\plaf\\palette\\PaletteToolBarBorder.java")));*/
				
				for(IPackageFragment pack: packages){
					if(pack.getKind() == IPackageFragmentRoot.K_SOURCE 
							/*&& pack.getHandleIdentifier().contains("sample")*/){
						for(ICompilationUnit iunit: pack.getCompilationUnits()){
							IResource resource = iunit.getResource();
							
							fileList.add(new JCCDFile(resource.getRawLocation().toFile()));
							
							System.currentTimeMillis();
						}
					}
				}
				
				APipeline detector = new ASTDetector();
				JCCDFile[] files = fileList.toArray(new JCCDFile[0]);
				detector.setSourceFiles(files);
				
				detector.addOperator(new GeneralizeArrayInitializers());
				detector.addOperator(new GeneralizeClassDeclarationNames());
				detector.addOperator(new GeneralizeMethodArgumentTypes());
				detector.addOperator(new GeneralizeMethodReturnTypes());
				detector.addOperator(new GeneralizeVariableDeclarationTypes());
				detector.addOperator(new GeneralizeMethodCallNames());
				detector.addOperator(new GeneralizeVariableDeclarationTypes());
				detector.addOperator(new GeneralizeVariableNames());
				
				SimilarityGroupManager manager = detector.process();
				SimilarityGroup[] simGroups = manager.getSimilarityGroups();
				
				CloneSets sets = convertToCloneSets(simGroups);
				
				//sets = clearCloneSets(sets);
				
				CloneDetectionFileWriter writer = new CloneDetectionFileWriter();
				writer.writeToXML(sets);
				
				System.out.println("clone information has been written in " + Settings.inputCloneFile);
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	private CloneSets convertToCloneSets(SimilarityGroup[] simGroups){
		CloneSets sets = new CloneSets();
		
		for (int i = 0; i < simGroups.length; i++) {
			final ASourceUnit[] nodes = simGroups[i].getNodes();
			
			CloneSet set = new CloneSet(String.valueOf(simGroups[i].getGroupId()));
			
			
			for (int j = 0; j < nodes.length; j++) {
				
				final SourceUnitPosition minPos = APipeline.getFirstNodePosition((ANode) nodes[j]);
				final SourceUnitPosition maxPos = APipeline.getLastNodePosition((ANode) nodes[j]);

				ANode fileNode = (ANode) nodes[j];
				while (fileNode.getType() != NodeTypes.FILE.getType()) {
					fileNode = fileNode.getParent();
				}
				
				CloneInstance cloneInstance = new CloneInstance(set, fileNode.getText(), 
						minPos.getLine(), maxPos.getLine());
				
				if(cloneInstance.getLength() >= 5){
					set.add(cloneInstance);					
				}
				
			}
			
			if(set.size() >= 2){
				sets.add(set);				
			}
		}
		
		return sets;
	}
	
	private String[] getProjectNames(){
		String[] projectLocation = new String[]{AutoGenCTSettings.retrieveScopePath()};
		String[] projectNames = new String[projectLocation.length];
		for(int i=0; i<projectLocation.length; i++){
			String location = projectLocation[i];
			String projectName = location.substring(location.lastIndexOf("\\")+1, location.length());
			projectNames[i] = projectName;
		}
		
		return projectNames;
	}
	
	private void detectMultiProjectClone(){
		String[] projects = getProjectNames();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		ArrayList<JCCDFile> fileList = new ArrayList<JCCDFile>();
		for(int i=0; i<projects.length; i++){
			IProject proj = root.getProject(projects[i]);
			try {
				if (proj.isNatureEnabled(MinerProperties.javaNatureName)) {
					IJavaProject javaProject = JavaCore.create(proj);
					IPackageFragment[] packages = javaProject.getPackageFragments();
					
					
					for(IPackageFragment pack: packages){
						if(pack.getKind() == IPackageFragmentRoot.K_SOURCE 
								/*&& pack.getHandleIdentifier().contains("sample")*/){
							for(ICompilationUnit iunit: pack.getCompilationUnits()){
								IResource resource = iunit.getResource();
								
								fileList.add(new JCCDFile(resource.getRawLocation().toFile()));
							}
						}
					}
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		APipeline detector = new ASTDetector();
		JCCDFile[] files = fileList.toArray(new JCCDFile[0]);
		detector.setSourceFiles(files);
		
		detector.addOperator(new GeneralizeArrayInitializers());
		detector.addOperator(new GeneralizeClassDeclarationNames());
		detector.addOperator(new GeneralizeMethodArgumentTypes());
		detector.addOperator(new GeneralizeMethodReturnTypes());
		detector.addOperator(new GeneralizeVariableDeclarationTypes());
		detector.addOperator(new GeneralizeMethodCallNames());
		detector.addOperator(new GeneralizeVariableDeclarationTypes());
		detector.addOperator(new GeneralizeVariableNames());
		
		SimilarityGroupManager manager = detector.process();
		SimilarityGroup[] simGroups = manager.getSimilarityGroups();
		
		CloneSets sets = convertToCloneSets0(simGroups);
		sets = clearCloneSets(sets);
		
		CloneDetectionFileWriter writer = new CloneDetectionFileWriter();
		writer.writeToXML(sets);
	}
	
	private CloneSets convertToCloneSets0(SimilarityGroup[] simGroups){
		CloneSets sets = new CloneSets();
		
		for (int i = 0; i < simGroups.length; i++) {
			final ASourceUnit[] nodes = simGroups[i].getNodes();
			
			CloneSet set = new CloneSet(String.valueOf(simGroups[i].getGroupId()));
			
			
			for (int j = 0; j < nodes.length; j++) {
				
				final SourceUnitPosition minPos = APipeline.getFirstNodePosition((ANode) nodes[j]);
				final SourceUnitPosition maxPos = APipeline.getLastNodePosition((ANode) nodes[j]);

				ANode fileNode = (ANode) nodes[j];
				while (fileNode.getType() != NodeTypes.FILE.getType()) {
					fileNode = fileNode.getParent();
				}
				
				CloneInstance cloneInstance = new CloneInstance(set, fileNode.getText(), 
						minPos.getLine(), maxPos.getLine());
				
				if(cloneInstance.getLength() >= 5){
					set.add(cloneInstance);					
				}	
				
			}
			
			if(set.size() >= 2){
				sets.add(set);				
			}
		}
		
		return sets;
	}
	
	private boolean containsCloneInstanceInSameProject(CloneSet set, CloneInstance instance){
		if(set.size() == 0){
			return false;
		}
		else{
			for(CloneInstance ins: set){
				if(ins.getFileLocation().contains("jarp") && instance.getFileLocation().contains("joone")){
					return false;
				}
				else if(ins.getFileLocation().contains("joone") && instance.getFileLocation().contains("jarp")){
					return false;
				}
				else{
					return true;					
				}
			}
			
		}
		
		return true;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow window) {

	}

}
