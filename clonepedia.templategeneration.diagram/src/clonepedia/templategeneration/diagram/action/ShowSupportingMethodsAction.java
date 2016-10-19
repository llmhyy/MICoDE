package clonepedia.templategeneration.diagram.action;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.PlatformUI;

import template_model.Element;
import template_model.Method;
import template_model.diagram.edit.parts.Method4EditPart;
import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.java.CloneInformationExtractor;
import clonepedia.java.CompilationUnitPool;
import clonepedia.java.model.CloneSetWrapper;
import clonepedia.model.ontology.CloneSet;
import clonepedia.perspective.CloneDiffPerspective;
import clonepedia.templategeneration.diagram.util.TemplateDiagramUtil;
import clonepedia.util.Settings;
import clonepedia.views.codesnippet.CloneDiffView;

public class ShowSupportingMethodsAction extends AbstractActionDelegate implements IObjectActionDelegate{

	public ShowSupportingMethodsAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		IStructuredSelection selection = getStructuredSelection();
		Object selEditPart = selection.getFirstElement();
		
		ShapeNodeEditPart editPart = (ShapeNodeEditPart)selEditPart;
		
		Element elementModel = (Element)editPart.resolveSemanticElement();
		
		Multiset ms = AutoGenCTSettings.designs.findMultiset(elementModel.getDescription());
		
		if(ms == null) return;
		
		CloneSetWrapper setWrapper = TemplateDiagramUtil.constructCloneSetFromASTNodeList(ms);
		try {
			if(Settings.diffComparisonMode.equals("ASTNode_Based")){
				setWrapper = new CloneInformationExtractor().extractCounterRelationalDifferencesOfCloneSet(setWrapper);					
			}
			else{
				setWrapper = new CloneInformationExtractor().extractCounterRelationalDifferencesWithinSyntacticBoundary(setWrapper);
			}
			CloneDiffView viewpart = (CloneDiffView)(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(CloneDiffPerspective.CLONE_DIFF_VIEW));
			if(viewpart != null){
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(CloneDiffPerspective.CLONE_DIFF_VIEW);
				viewpart.showCodeSnippet(setWrapper, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * inherent for ontology based API.
	 */
	protected void doRun_bak(IProgressMonitor progressMonitor) {
		IStructuredSelection selection = getStructuredSelection();
		Object selEditPart = selection.getFirstElement();
		
		if(selEditPart instanceof Method4EditPart){
			Method4EditPart methodEditPart = (Method4EditPart)selEditPart;
			Method methodModel = (Method)methodEditPart.resolveSemanticElement();
			
			List<String> methodList = methodModel.getSupportingElements();
			
			if(methodList.size() == 0) return;
			
			CloneSet set = TemplateDiagramUtil.constructCloneSetFromMethodList(methodList);
			
			CloneSetWrapper setWrapper = new CloneSetWrapper(set, new CompilationUnitPool());
			try {
				if(Settings.diffComparisonMode.equals("ASTNode_Based")){
					setWrapper = new CloneInformationExtractor().extractCounterRelationalDifferencesOfCloneSet(setWrapper);					
				}
				else{
					setWrapper = new CloneInformationExtractor().extractCounterRelationalDifferencesWithinSyntacticBoundary(setWrapper);
				}
				CloneDiffView viewpart = (CloneDiffView)(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(CloneDiffPerspective.CLONE_DIFF_VIEW));
				if(viewpart != null){
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(CloneDiffPerspective.CLONE_DIFF_VIEW);
					viewpart.showCodeSnippet(setWrapper, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
