package template_model.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import template_model.Call;
import template_model.ExtendClass;

/**
 * @generated
 */
public class CallEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4007;

	/**
	 * @generated
	 */
	public CallEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new template_model.diagram.edit.policies.CallItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof template_model.diagram.edit.parts.CallNameEditPart) {
			((template_model.diagram.edit.parts.CallNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureCallNameFigure());
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
		if (childEditPart instanceof template_model.diagram.edit.parts.CallNameEditPart) {
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
		return new CallFigure();
	}

	/**
	 * @generated
	 */
	public CallFigure getPrimaryShape() {
		return (CallFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class CallFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureCallNameFigure;

		/**
		 * @generated
		 */
		public CallFigure() {
			this.setFillXOR(true);

			this.setBorder(new MarginBorder(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			createContents();
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @not generated
		 */
		private void createContents() {

			fFigureCallNameFigure = new WrappingLabel();

			//fFigureCallNameFigure.setText("call");

			this.add(fFigureCallNameFigure);

		}
		
		@Override
		public void paintFigure(Graphics graphics) {
			super.paintFigure(graphics);

			Call model = (Call) resolveSemanticElement();
			if (model.isIsComplete()) {
				setForegroundColor(DF_FORE);
			} else {
				if(model.getVariationType() != null){
					if(model.getVariationType().equals("optional")){
						setForegroundColor(OPTIONAL_FORE);
					}				
				}
				
			}
		}

		/**
		 * @not generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			df.setFillXOR(true);
			df.setLineWidth(1);
			df.setForegroundColor(DF_FORE);
			return df;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureCallNameFigure() {
			return fFigureCallNameFigure;
		}

	}
	
	static final Color OPTIONAL_FORE = new Color(null, 188, 188, 188);
	
	/**
	 * @generated
	 */
	static final Color DF_FORE = new Color(null, 0, 0, 0);

}
