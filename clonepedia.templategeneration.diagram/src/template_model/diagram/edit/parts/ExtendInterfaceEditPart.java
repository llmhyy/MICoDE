package template_model.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import template_model.ExtendClass;
import template_model.ExtendInterface;

/**
 * @generated
 */
public class ExtendInterfaceEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4006;

	/**
	 * @generated
	 */
	public ExtendInterfaceEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new template_model.diagram.edit.policies.ExtendInterfaceItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof template_model.diagram.edit.parts.ExtendInterfaceNameEditPart) {
			((template_model.diagram.edit.parts.ExtendInterfaceNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureExtendInterfaceNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, index);
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof template_model.diagram.edit.parts.ExtendInterfaceNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	protected Connection createConnectionFigure() {
		return new ExtendInterfaceFigure();
	}

	/**
	 * @generated
	 */
	public ExtendInterfaceFigure getPrimaryShape() {
		return (ExtendInterfaceFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class ExtendInterfaceFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureExtendInterfaceNameFigure;

		/**
		 * @generated
		 */
		public ExtendInterfaceFigure() {
			this.setLineWidth(2);
			this.setForegroundColor(THIS_FORE);

			createContents();
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureExtendInterfaceNameFigure = new WrappingLabel();

			fFigureExtendInterfaceNameFigure.setText("");

			this.add(fFigureExtendInterfaceNameFigure);

		}
		
		@Override
		public void paintFigure(Graphics graphics) {
			super.paintFigure(graphics);

			ExtendInterface model = (ExtendInterface) resolveSemanticElement();
			if (model.isIsComplete()) {
				setForegroundColor(THIS_FORE);
			} else {
				if(model.getVariationType() != null){
					if(model.getVariationType().equals("optional")){
						setForegroundColor(OPTIONAL_FORE);
					}				
				}
				
			}
		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolygonDecoration df = new PolygonDecoration();
			df.setFill(true);
			df.setLineWidth(2);
			df.setForegroundColor(ColorConstants.black);
			df.setBackgroundColor(ColorConstants.white);
			PointList pl = new PointList();
			pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(2));
			pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(-2));
			pl.addPoint(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0));
			df.setTemplate(pl);
			df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(3));
			return df;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureExtendInterfaceNameFigure() {
			return fFigureExtendInterfaceNameFigure;
		}

	}

	static final Color OPTIONAL_FORE = new Color(null, 188, 188, 188);
	
	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

}
