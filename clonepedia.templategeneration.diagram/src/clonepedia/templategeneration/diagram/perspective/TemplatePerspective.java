package clonepedia.templategeneration.diagram.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import clonepedia.perspective.CloneDiffPerspective;

public class TemplatePerspective implements IPerspectiveFactory {
	
	public static String PLAIN_CLONESET_VIEW = "Clonepedia.PlainCloneSetView";
	public static String FEATURE_TEMPLATE_VIEW = "clonepedia.templategeneration.diagram.feaure";
	public static String REUSABLE_DESIGN_VIEW = "clonepedia.templategeneration.diagram.resuableDesign";
	public static String CONFIGURE_TASK_VIEW = "clonepedia.templategeneration.diagram.configureTask";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.addView(IPageLayout.ID_PROJECT_EXPLORER, IPageLayout.LEFT, 0.15f, editorArea);
		
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.6f, editorArea);
		bottom.addView(IPageLayout.ID_PROP_SHEET);
		bottom.addView(CloneDiffPerspective.CLONE_DIFF_VIEW);
		bottom.addView(CONFIGURE_TASK_VIEW);
		
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.7f, editorArea);
		//right.addView(FEATURE_TEMPLATE_VIEW);
		right.addView(REUSABLE_DESIGN_VIEW);
		right.addView(IPageLayout.ID_OUTLINE);
		//layout.addView(FEATURE_TEMPLATE_VIEW, IPageLayout.RIGHT, 0.65f, editorArea);
		//layout.addView(CloneDiffPerspective.CLONE_DIFF_VIEW, IPageLayout.BOTTOM, 0.65f, editorArea);

	}

}
