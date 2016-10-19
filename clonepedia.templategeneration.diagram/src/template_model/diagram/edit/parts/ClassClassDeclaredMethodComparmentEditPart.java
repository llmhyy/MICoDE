package template_model.diagram.edit.parts;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent;

/**
 * @generated
 */
public class ClassClassDeclaredMethodComparmentEditPart extends
		ShapeCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 7002;

	/**
	 * @generated
	 */
	public ClassClassDeclaredMethodComparmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return template_model.diagram.part.Messages.ClassClassDeclaredMethodComparmentEditPart_title;
	}

	/**
	 * @not generated
	 */
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);

		/*result.getContentPane().setLayoutManager(new XYLayout(){
			public void layout(IFigure parent) {
				Iterator<IFigure> fIterator = parent.getChildren().iterator();
				int count = 1;
				int x = 10;
				int y = 10;
				while (fIterator.hasNext()){
					IFigure figure = fIterator.next();
					int height = figure.getPreferredSize().height;
					int width = figure.getPreferredSize().width;
					figure.setBounds(new Rectangle(x, y, width,height));
					if(count % 3 == 0){
						x = 10;
						y = y + height + 20;
					}else{
						x = x + width + 20;
					}
					count++;
				}
			}
			
			private void computeConstaints(IFigure parent){
				//HashMap<IFigure, Rectangle> map = new HashMap<>();
				
				Iterator children = parent.getChildren().iterator();
				Point offset = getOrigin(parent);
				IFigure f;
				
				int count = 1;
				int x = 10;
				int y = 10;
				while (children.hasNext()) {
					f = (IFigure) children.next();
					Rectangle bounds = (Rectangle) getConstraint(f);
					if (bounds == null)
						continue;

					if (bounds.width == -1 || bounds.height == -1) {
						Dimension preferredSize = f.getPreferredSize(bounds.width,
								bounds.height);
						bounds = bounds.getCopy();
						if (bounds.width == -1)
							bounds.width = preferredSize.width;
						if (bounds.height == -1)
							bounds.height = preferredSize.height;
					}
					
					int height = bounds.height;
					int width = bounds.width;
					Rectangle rec = new Rectangle(x, y, width,height);
					
					setConstraint(f, rec);
					
					if(count % 5 == 0){
						x = 10;
						y = y + height + 20;
					}else{
						x = x + width + 20;
					}
					count++;
				}
				
				//return map;
			}
		});*/
		return result;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new template_model.diagram.edit.policies.ClassClassDeclaredMethodComparmentItemSemanticEditPolicy());
		installEditPolicy(
				EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicyWithCustomReparent(
						template_model.diagram.part.Template_generationVisualIDRegistry.TYPED_INSTANCE));
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(
				EditPolicyRoles.CANONICAL_ROLE,
				new template_model.diagram.edit.policies.ClassClassDeclaredMethodComparmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

}
