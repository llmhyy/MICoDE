package clonepedia.templategeneration.diagram.view;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import template_model.diagram.preferences.TemplateGenerationPreferencePage;
import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.Activator;
import clonepedia.codegeneration.diagram.bean.AbstractField;
import clonepedia.codegeneration.diagram.bean.AbstractMethod;
import clonepedia.codegeneration.diagram.bean.DesignList;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;
import clonepedia.templategeneration.diagram.dialog.RenameDialog;
import clonepedia.templategeneration.routine.DesignXMLReader;
import clonepedia.templategeneration.routine.DesignXMLWriter;
import clonepedia.util.ImageUI;
import clonepedia.util.MinerProperties;
import clonepedia.util.Settings;

public class ReusableDesignView extends ViewPart {

	public final String diagramDirtory = "template_model";

	private TreeViewer viewer;
	private Resource diagram;

	public ReusableDesignView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		Tree featureTree = createTree(parent);
		this.viewer = new TreeViewer(featureTree);
		this.viewer.addDoubleClickListener(new DesignSelectionListener());

		this.viewer.addDragSupport(DND.DROP_COPY | DND.DROP_MOVE,
				new Transfer[] { LocalSelectionTransfer.getTransfer() }, new DragListener(viewer));
		this.viewer.addDropSupport(DND.DROP_COPY | DND.DROP_MOVE,
				new Transfer[] { LocalSelectionTransfer.getTransfer() }, new DropListener(viewer));

		this.viewer.setContentProvider(new DesignContentProvider());
		this.viewer.setLabelProvider(new DesignLabelProvider());

		this.viewer.setInput(AutoGenCTSettings.designs);

		getSite().setSelectionProvider(viewer);

		hookActionsOnMenu();
		hookActionsOnToolBar();
	}
	
	private void printTemplateMetrics(DesignList designs){
		int sampleSize = 15;
		
		System.out.println("Total number of templates of " + AutoGenCTSettings.retrieveTargetProject() + ": " + designs.size());
		double averageTypeNum = 0;
		double averageMethodNum = 0;
		double averageFieldNum = 0;
		double averageInnerClass = 0;
		
		for(int i=0; i<sampleSize; i++){
			TemplateDesign design = designs.get(i);
			averageTypeNum += design.getAbstractTypeNumber();
			averageMethodNum += design.getAbstractMethodNumber();
			averageFieldNum += design.getAbstractFieldNumber();
			averageInnerClass += design.getAbstractInnerTypeNumber();
			
			System.out.println("No." + (i+1) + ", TT: " + design.getAbstractTypeNumber() + ", TM: " + design.getAbstractMethodNumber()
					+ ", TF: " + design.getAbstractFieldNumber());
			
		}
		
		int size = sampleSize;
		averageTypeNum /= size;
		averageMethodNum /= size;
		averageFieldNum /= size;
		averageInnerClass /= size;
		
		System.out.println("avg number of types: " + averageTypeNum);
		System.out.println("avg number of methods: " + averageMethodNum);
		System.out.println("avg number of fields: " + averageFieldNum);
		System.out.println("avg number of inner classes: " + averageInnerClass);
	}

	private void hookActionsOnToolBar() {
//		final String templateFileLocation = Activator
//				.getDefault()
//				.getPreferenceStore()
//				.getString(TemplateGenerationPreferencePage.TEMPLATE_FILE_LOCATION);
		
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();

		Action loadAction = new Action("load data") {
			public void run() {
				String templateFileLocation = AutoGenCTSettings.retrieveTemplateFileLocation();
				
				DesignList designs = DesignXMLReader
						.xml2design(templateFileLocation);
				AutoGenCTSettings.designs = designs;
				// viewer.setInput(designs);
				/*for(TemplateDesign d: designs){
					System.out.println(d.getName());
					System.currentTimeMillis();
				}*/
				printTemplateMetrics(designs);
				
				viewer.setInput(AutoGenCTSettings.designs);
				viewer.refresh();
			}
		};
		loadAction.setImageDescriptor(Activator.getDefault().getImageRegistry()
				.getDescriptor(ImageUI.LOAD_DATA));

		Action saveAction = new Action("save data") {
			public void run() {
				if(AutoGenCTSettings.designs.size() != 0){
					String templateFileLocation = AutoGenCTSettings.retrieveTemplateFileLocation();
					DesignXMLWriter.design2XML(AutoGenCTSettings.designs,
							templateFileLocation);					
				}
			}
		};
		saveAction.setImageDescriptor(Activator.getDefault().getImageRegistry()
				.getDescriptor(ImageUI.SAVE_DATA));

		toolBar.add(loadAction);
		toolBar.add(saveAction);
	}

	private void hookActionsOnMenu() {
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(viewer.getTree());

		viewer.getTree().setMenu(menu);

		Action renameAction = new Action("Rename") {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();

				if (selection.getFirstElement() instanceof TemplateDesign) {

					RenameDialog dialog = new RenameDialog(PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getShell());
					if (dialog.open() == Window.OK) {
						String name = dialog.getTemplateName();
						String description = dialog.getTemplateDescription();

						TemplateDesign design = (TemplateDesign) selection
								.getFirstElement();
						design.setName(name);
						design.setDescription(description);

						viewer.refresh();
					}

				}

				viewer.refresh();
			}
		};

		Action deleteAction = new Action("Delete") {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();

				if (selection.getFirstElement() instanceof TemplateDesign) {

					TemplateDesign design = (TemplateDesign) selection
							.getFirstElement();

					AutoGenCTSettings.designs.remove(design);
					viewer.setInput(AutoGenCTSettings.designs);

					viewer.refresh();
				}

				viewer.refresh();
			}
		};

		Action mergeAction = new Action("Merge") {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();

				if (selection.getFirstElement() instanceof TemplateDesign) {
					TemplateDesign primaryDesign = (TemplateDesign) selection
							.getFirstElement();
					Iterator iter = selection.iterator();
					while (iter.hasNext()) {
						TemplateDesign design = (TemplateDesign) iter.next();

						if (design != primaryDesign) {
							primaryDesign.merge(design);
							AutoGenCTSettings.designs.remove(design);
						}
					}

					viewer.setInput(AutoGenCTSettings.designs);
					viewer.refresh();
				}

			}
		};

		Action createAction = new Action("Create A New Design") {
			public void run() {
				TemplateDesign design = new TemplateDesign();
				design.setName("reusable design");

				AutoGenCTSettings.designs.add(design);
				viewer.setInput(AutoGenCTSettings.designs);
				viewer.refresh();
			}
		};
		
		Action regenerateDiagramAction = new Action("Regenerate Diagram") {
			public void run() {
				//TODO need an incremental work for this function
				viewer.setInput(AutoGenCTSettings.designs);
				viewer.refresh();
			}
		};

		menuManager.add(renameAction);
		menuManager.add(deleteAction);
		menuManager.add(mergeAction);
		menuManager.add(createAction);
		menuManager.add(regenerateDiagramAction);
		// getSite().registerContextMenu(menuManager, viewer);
	}

	private Tree createTree(Composite parent) {
		Tree featureTree = new Tree(parent, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		featureTree.setHeaderVisible(true);
		featureTree.setLinesVisible(true);

		TreeColumn featureColumn = new TreeColumn(featureTree, SWT.LEFT);
		featureColumn.setAlignment(SWT.LEFT);
		featureColumn.setText("Name");
		featureColumn.setWidth(220);

		TreeColumn returnTypeColumn = new TreeColumn(featureTree, SWT.LEFT);
		returnTypeColumn.setAlignment(SWT.LEFT);
		returnTypeColumn.setText("(return) type");
		returnTypeColumn.setWidth(100);

		TreeColumn parametersColumn = new TreeColumn(featureTree, SWT.LEFT);
		parametersColumn.setAlignment(SWT.LEFT);
		parametersColumn.setText("parameters");
		parametersColumn.setWidth(100);

		return featureTree;
	}

	public TreeViewer getViewer() {
		return this.viewer;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public URI getURI(String path) {
		return URI.createPlatformResourceURI(path, false);
	}
	
	private IFolder findTargetFolder(){
		
		
		return null;
	}

	private class DesignSelectionListener implements IDoubleClickListener {

		@Override
		public void doubleClick(DoubleClickEvent event) {
			IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
			Object selectedNode = thisSelection.getFirstElement();

			if (selectedNode instanceof TemplateDesign) {
				final TemplateDesign design = (TemplateDesign) selectedNode;
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				final IProject project = root.getProject(Settings.projectName);
				try {
					if (project.isNatureEnabled(MinerProperties.javaNatureName)) {
						IFolder folder = project.getFolder(diagramDirtory);
						if (!folder.exists()) {
							try {
								folder.create(false, true, new NullProgressMonitor());
							} catch (CoreException e) {
								e.printStackTrace();
							}
						}

						IPath diagramFilePath = folder.getFullPath().append(design.getID() + ".template_model_diagram");
						IPath modelFilePath = folder.getFullPath().append(design.getID() + ".template_model");

						if (!folder.getRawLocation().append(design.getID() + ".template_model_diagram").toFile().exists()) {
							diagram = template_model.diagram.part.Template_generationDiagramEditorUtil
									.createDiagram(getURI(diagramFilePath.toString()), getURI(modelFilePath.toString()),
											new NullProgressMonitor(), design);
							template_model.diagram.part.Template_generationDiagramEditorUtil.openDiagram(diagram);
						} else {
							TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
									.createEditingDomain();
							Resource diagramResource = editingDomain.getResourceSet().createResource(getURI(diagramFilePath.toString()));
							template_model.diagram.part.Template_generationDiagramEditorUtil.openDiagram(diagramResource);
						}
					}
				} catch (JavaModelException e) {
					e.printStackTrace();
				} catch (CoreException e) {
					e.printStackTrace();
				}
				
				
			}
		}
	}

	public class DesignContentProvider implements ITreeContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof ArrayList) {
				ArrayList list = (ArrayList) parentElement;
				if (list.size() != 0 && list.get(0) instanceof TemplateDesign) {
					return list.toArray(new TemplateDesign[0]);
				}
			} else if (parentElement instanceof TemplateDesign) {
				TemplateDesign design = (TemplateDesign) parentElement;
				return design.getDesign().toArray(new Multiset[0]);
			} else if (parentElement instanceof Multiset) {
				Multiset set = (Multiset) parentElement;
				if (set.getSubMultisetList() != null
						&& set.getSubMultisetList().size() != 0) {
					return set.getSubMultisetList().toArray(new Multiset[0]);
				}
			}

			return new ArrayList<>().toArray();
		}

		@Override
		public Object getParent(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof ArrayList) {
				return true;
			} else if (element instanceof TemplateDesign) {
				return true;
			} else if (element instanceof Multiset) {
				return ((Multiset) element).getSubMultisetList().size() != 0;
			}

			return false;
		}

	}

	public class DesignLabelProvider implements ITableLabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {

		}

		@Override
		public void dispose() {

		}

		@Override
		public boolean isLabelProperty(Object element, String property) {

			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {

		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {

			switch (columnIndex) {
			case 0:
				if (element instanceof TemplateDesign) {
					TemplateDesign design = (TemplateDesign) element;
					return (design.getName() == null) ? "reusable design"
							: design.getName();
				} else if (element instanceof Multiset) {
					Multiset set = (Multiset) element;
					return set.getAbstractElement().getName();
				}
			case 1:
				if (element instanceof Multiset) {
					Multiset set = (Multiset) element;
					if (set.isFieldSet()) {
						AbstractField field = (AbstractField) set
								.getAbstractElement();
						return field.getType();
					} else if (set.isMethodSet()) {
						AbstractMethod method = (AbstractMethod) set
								.getAbstractElement();
						return method.getReturnType();
					}
				}
			case 2:
				if (element instanceof Multiset) {
					Multiset set = (Multiset) element;
					if (set.isMethodSet()) {
						AbstractMethod method = (AbstractMethod) set
								.getAbstractElement();
						return method.getParameters().toString();
					}
				}
			}
			return null;
		}

	}

	public class DragListener implements DragSourceListener {

		private final TreeViewer viewer;

		public DragListener(TreeViewer viewer) {
			this.viewer = viewer;
		}

		@Override
		public void dragStart(DragSourceEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void dragSetData(DragSourceEvent event) {
			IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
			if(selection.getFirstElement() instanceof Multiset){
				Multiset ms = (Multiset)selection.getFirstElement();
				if(ms.isTypeSet()){
					
				}
			}
		}

		@Override
		public void dragFinished(DragSourceEvent event) {
			// TODO Auto-generated method stub

		}

	}

	public class DropListener extends ViewerDropAdapter {

		private final Viewer viewer;

		protected DropListener(Viewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		public void drop(DropTargetEvent event) {
			int location = this.determineLocation(event);
			Object target = determineTarget(event);
			
			if(target instanceof TemplateDesign){
				TemplateDesign design = (TemplateDesign)target;
				
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				if(selection.getFirstElement() instanceof Multiset){
					Multiset ms = (Multiset)selection.getFirstElement();
					if(ms.isTypeSet()){
						design.getDesign().add(ms);
						
						TemplateDesign oldParent = AutoGenCTSettings.designs.findDesignContainingTypeMultiset(ms.getId());
						oldParent.getDesign().remove(ms);
					}
				}
				
				ReusableDesignView.this.viewer.setInput(AutoGenCTSettings.designs);
				ReusableDesignView.this.viewer.refresh();
			}
			
			//super.drop(event);
		}

		// This method performs the actual drop
		// We simply add the String we receive to the model and trigger a
		// refresh of the
		// viewer by calling its setInput method.
		@Override
		public boolean performDrop(Object data) {
			
			return false;
		}

		@Override
		public boolean validateDrop(Object target, int operation,
				TransferData transferType) {
			return true;

		}
	}
}
