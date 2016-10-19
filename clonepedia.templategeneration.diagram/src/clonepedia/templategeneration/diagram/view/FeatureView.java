package clonepedia.templategeneration.diagram.view;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;


import clonepedia.Activator;
import clonepedia.model.ontology.ComplexType;
import clonepedia.model.ontology.Method;
import clonepedia.model.ontology.Variable;
import clonepedia.model.template.CandidateTemplate;
import clonepedia.model.template.Template;
import clonepedia.model.template.SubCandidateTemplate;
import clonepedia.model.template.TemplateMethodGroup;
import clonepedia.model.template.CandidateTemplateList;
import clonepedia.templategeneration.diagram.dialog.RenameDialog;
import clonepedia.templategeneration.diagram.view.model.MethodWrapper;
import clonepedia.templategeneration.diagram.view.model.TMGWrapper;
import clonepedia.templategeneration.diagram.view.model.TemplateList;
import clonepedia.templategeneration.diagram.view.model.TemplateWrapper;
import clonepedia.util.ImageUI;
import clonepedia.util.MinerProperties;
import clonepedia.util.MinerUtil;
import clonepedia.util.Settings;

public class FeatureView extends ViewPart {

	public final String diagramDirtory = "template_model";
	
	private TreeViewer viewer;
	private Resource diagram;
	
	public FeatureView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		Tree featureTree = createTree(parent);
		this.viewer = new TreeViewer(featureTree);
		this.viewer.addDoubleClickListener(new FeatureSelectionListener());
		
		System.out.println("start loading totalTFGs file");
		CandidateTemplateList totalTFGs = (CandidateTemplateList) MinerUtil.deserialize("totalTFGs", false);
		System.out.println("finish loading totalTFGs file");
		
		TemplateList templateList = new TemplateList(totalTFGs);
		
		this.viewer.setContentProvider(new FeatureContentProvider());
		this.viewer.setLabelProvider(new FeatureLabelProvider());
		this.viewer.setInput(templateList);
		
		getSite().setSelectionProvider(viewer);
		
		hookActionsOnMenu();
		hookActionsOnToolBar();
	}
	
	private void hookActionsOnToolBar(){
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		
		Action loadAction = new Action("load data"){
			public void run(){
				CandidateTemplateList totalTFGs = (CandidateTemplateList) MinerUtil.deserialize("totalTFGs", false);
				viewer.setInput(new TemplateList(totalTFGs));
				viewer.refresh();
			}
		};
		loadAction.setImageDescriptor(Activator.getDefault().getImageRegistry().getDescriptor(ImageUI.LOAD_DATA));
		
		Action saveAction = new Action("save data"){
			public void run(){
				CandidateTemplateList totalTFGs = ((TemplateList)viewer.getInput()).getTotalTFGs();
				try {
					MinerUtil.serialize(totalTFGs, "totalTFGs", false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		saveAction.setImageDescriptor(Activator.getDefault().getImageRegistry().getDescriptor(ImageUI.SAVE_DATA));
		
		toolBar.add(loadAction);
		toolBar.add(saveAction);
	}
	
	private void hookActionsOnMenu(){
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(viewer.getTree());
		
		viewer.getTree().setMenu(menu);
		
		Action renameAction = new Action("Rename") {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				
				if(selection.getFirstElement() instanceof TemplateWrapper){
					
					RenameDialog dialog = new RenameDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
					if(dialog.open() == Window.OK){
						String name = dialog.getTemplateName();
						String description = dialog.getTemplateDescription();
						CandidateTemplate candidateTemplate = ((TemplateWrapper)selection.getFirstElement()).getCandidateTemplate();
						
						Template template = candidateTemplate.getTemplate();
						template.setName(name);
						template.setDescription(description);
						
						viewer.refresh();
					}
					
					
				}
				
				viewer.refresh();
			}
		};
		
		Action deleteAction = new Action("Delete") {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				
				if(selection.getFirstElement() instanceof TemplateWrapper){
					
					TemplateWrapper templateWrapper = (TemplateWrapper)selection.getFirstElement();
					
					CandidateTemplate candidateTemplate = templateWrapper.getCandidateTemplate();
					
					//Template template = candidateTemplate.getTemplate();
					
					TemplateList list = (TemplateList)viewer.getInput();
					list.getTemplateWrapperList().remove(templateWrapper);
					list.getTotalTFGs().remove(candidateTemplate);
					
					viewer.refresh();
					
					
				}
				
				viewer.refresh();
			}
		};
		
		menuManager.add(renameAction);
		menuManager.add(deleteAction);
		//getSite().registerContextMenu(menuManager, viewer);
	}
	
	private Tree createTree(Composite parent){
		Tree featureTree = new Tree(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		featureTree.setHeaderVisible(true);
		featureTree.setLinesVisible(true);
		
		TreeColumn featureColumn = new TreeColumn(featureTree, SWT.LEFT);
		featureColumn.setAlignment(SWT.LEFT);
		featureColumn.setText("feature template");
		featureColumn.setWidth(220);
		
		TreeColumn declaringTypeColumn = new TreeColumn(featureTree, SWT.LEFT);
		declaringTypeColumn.setAlignment(SWT.LEFT);
		declaringTypeColumn.setText("declaring type");
		declaringTypeColumn.setWidth(100);
		
		TreeColumn returnTypeColumn = new TreeColumn(featureTree, SWT.LEFT);
		returnTypeColumn.setAlignment(SWT.LEFT);
		returnTypeColumn.setText("return type");
		returnTypeColumn.setWidth(100);
		
		TreeColumn parametersColumn = new TreeColumn(featureTree, SWT.LEFT);
		parametersColumn.setAlignment(SWT.LEFT);
		parametersColumn.setText("parameters");
		parametersColumn.setWidth(100);
		
		return featureTree;
	}
	
	public TreeViewer getViewer(){
		return this.viewer;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public URI getURI(String path) {
		return URI.createPlatformResourceURI(path, false);
	}
	
	private class FeatureSelectionListener implements IDoubleClickListener{

		@Override
		public void doubleClick(DoubleClickEvent event) {
			//TreeViewer viewer = (TreeViewer) event.getViewer();
		    IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection(); 
		    Object selectedNode = thisSelection.getFirstElement(); 
		    
		    if(selectedNode instanceof TemplateWrapper){
		    	final CandidateTemplate tfgList = ((TemplateWrapper)selectedNode).getCandidateTemplate(); 
		    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				final IProject project = root.getProject(Settings.projectName);
				try {
					if (project.isNatureEnabled(MinerProperties.javaNatureName)) {
						
						IFolder folder = project.getFolder(diagramDirtory);
						if(!folder.exists()){
							try {
								folder.create(false, true, new NullProgressMonitor());
							} catch (CoreException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						/*TemplateBuilder builder = new TemplateBuilder(tfgList);
						Template template = builder.buildTemplate();*/
						Template template = tfgList.getTemplate();
						
						IPath diagramFilePath = folder.getFullPath().append(template.getTemplateID() + ".template_model_diagram");
						IPath modelFilePath = folder.getFullPath().append(template.getTemplateID() + ".template_model");
						
						//IJavaProject javaProject = JavaCore.create(project);
						//IPath diagramFilePath = Path.fromPortableString(diagramFile);
						//IPath modelFilePath = Path.fromPortableString(modelFile);
						//IJavaElement element = javaProject.findElement(filePath);
						/*diagram = template_model.diagram.part.Template_generationDiagramEditorUtil
								.createDiagram(getURI(diagramFilePath.toString()), getURI(modelFilePath.toString()), 
										monitor, tfgList);	*/	
						
						if(!folder.getRawLocation().append(template.getTemplateID() + ".template_model_diagram").toFile().exists()){
							diagram = template_model.diagram.part.Template_generationDiagramEditorUtil
									.createDiagram(getURI(diagramFilePath.toString()), getURI(modelFilePath.toString()), 
											new NullProgressMonitor(), template);	
							template_model.diagram.part.Template_generationDiagramEditorUtil.openDiagram(diagram);							
						}
						else{
							TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
							Resource diagramResource = editingDomain.getResourceSet().createResource(getURI(diagramFilePath.toString()));
							template_model.diagram.part.Template_generationDiagramEditorUtil.openDiagram(diagramResource);	
						}
						
						/*if(project.findMember(diagramFilePath) == null){
						}*/
						
						/*Job job = new Job("Creating or opening diagram..."){
							@Override
							protected IStatus run(IProgressMonitor monitor) {
								// TODO Auto-generated method stub
								
								
								
								
								return Status.OK_STATUS;
							}
						};
						job.schedule();
						if(diagram == null){
							Thread.sleep(1000);								
						}*/
					}
				} catch (JavaModelException e) {					
					e.printStackTrace();
				} catch (CoreException e) {					
					e.printStackTrace();
				}/* catch (InterruptedException e) {
					e.printStackTrace();
				}*/
		    }
		    
		    //viewer.setExpandedState(selectedNode, !viewer.getExpandedState(selectedNode));
		}

	}
	
	public class FeatureContentProvider implements ITreeContentProvider{

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof TemplateList){
				TemplateList templateList = (TemplateList)parentElement;
				
				ArrayList<TemplateWrapper> templateWrapperList = new ArrayList<TemplateWrapper>();
				for(TemplateWrapper feature: templateList.getTemplateWrapperList()){
					templateWrapperList.add(feature);
					/*if(feature.getTfgList().size() > 2){
						templateWrapperList.add(feature);
					}*/
				}
				
				return templateWrapperList.toArray(new TemplateWrapper[0]);
			}
			else if(parentElement instanceof TemplateWrapper){
				TemplateWrapper feature = (TemplateWrapper)parentElement;
				
				return feature.getTmgWrapperList().toArray(new TMGWrapper[0]);
			}
			else if(parentElement instanceof TMGWrapper){
				TMGWrapper tmg = (TMGWrapper)parentElement;
				
				return tmg.getMethodWrapperList().toArray(new MethodWrapper[0]);
			}
			
			return null;
		}

		@Override
		public Object getParent(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if(element instanceof TemplateList){

				return ((TemplateList)element).getTemplateWrapperList().size() != 0;
			}
			else if(element instanceof TemplateWrapper){
				return ((TemplateWrapper)element).getTmgWrapperList().size() != 0;
			}
			else if(element instanceof TMGWrapper){
				return ((TMGWrapper)element).getMethodWrapperList().size() != 0;
			}
			
			return false;
		}
		
	}
	public class FeatureLabelProvider implements ITableLabelProvider{

		@Override
		public void addListener(ILabelProviderListener listener) {
			
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			
			 switch (columnIndex){
	            case 0: 
	            	if(element instanceof TemplateWrapper)
	            		return ((TemplateWrapper)element).getCandidateTemplate().getTemplate().getName();
	            	else if(element instanceof TMGWrapper){
	     
	            		TMGWrapper wrapper = (TMGWrapper)element;
	            		return wrapper.getTmg().getName();
	            		
	            	}
	            	else if(element instanceof MethodWrapper){
	            		return ((MethodWrapper)element).getMethod().getSimpleElementName();
	            	}
	            case 1:
	               if (element instanceof MethodWrapper){
	            	   MethodWrapper m = (MethodWrapper)element; 
	            	   ComplexType type = m.getMethod().getOwner();
	            	   return type.getSimpleElementName();
	               }
	            case 2: 
	               if (element instanceof MethodWrapper){
	            	   MethodWrapper m = (MethodWrapper)element; 
	            	   return m.getMethod().getReturnType().getFullName();
	               }
	            case 3:
	            	if(element instanceof MethodWrapper){
	            		MethodWrapper m = (MethodWrapper)element;
	            		StringBuffer buffer = new StringBuffer();
	            		
	            		boolean flag = false;
	            		for(Variable variable: m.getMethod().getParameters()){
	            			buffer.append(variable.getVariableType() + ", ");
	            			flag = true;
	            		}
	            		String paramString = buffer.toString();
	            		return flag ? paramString.substring(0, paramString.length()-2) : paramString;
	            	}
	         }
	         return null;
		}
		
	}
}
