package template_model.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import template_model.Class;
import template_model.Field;

/**
 * @generated
 */
public class Class2EditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3004;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public Class2EditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new template_model.diagram.edit.policies.Class2ItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new ClassFigure();
	}

	/**
	 * @generated
	 */
	public ClassFigure getPrimaryShape() {
		return (ClassFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof template_model.diagram.edit.parts.ClassName2EditPart) {
			((template_model.diagram.edit.parts.ClassName2EditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureClassNameFigure());
			return true;
		}
		if (childEditPart instanceof template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart) {
			IFigure pane = getPrimaryShape()
					.getFigureClassDeclaredMethodCompartment();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof template_model.diagram.edit.parts.ClassName2EditPart) {
			return true;
		}
		if (childEditPart instanceof template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart) {
			IFigure pane = getPrimaryShape()
					.getFigureClassDeclaredMethodCompartment();
			pane.remove(((template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart) childEditPart)
					.getFigure());
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
		super.addChildVisual(childEditPart, -1);
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
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		if (editPart instanceof template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart) {
			return getPrimaryShape().getFigureClassDeclaredMethodCompartment();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(70, 50);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(template_model.diagram.part.Template_generationVisualIDRegistry
				.getType(template_model.diagram.edit.parts.ClassName2EditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Implement_4008);
		types.add(template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof template_model.diagram.edit.parts.InterfaceEditPart) {
			types.add(template_model.diagram.providers.Template_generationElementTypes.Implement_4008);
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.ClassEditPart) {
			types.add(template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010);
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Class2EditPart) {
			types.add(template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == template_model.diagram.providers.Template_generationElementTypes.Implement_4008) {
			types.add(template_model.diagram.providers.Template_generationElementTypes.Interface_2001);
		} else if (relationshipType == template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010) {
			types.add(template_model.diagram.providers.Template_generationElementTypes.Class_2005);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Class_3004);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010) {
			types.add(template_model.diagram.providers.Template_generationElementTypes.Class_2005);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Class_3004);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class ClassFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureClassNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureClassFullNameFigure;
		/**
		 * @generated
		 */
		private RectangleFigure fFigureClassDeclaredMethodCompartment;

		/**
		 * @generated
		 */
		public ClassFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			layoutThis.marginWidth = 0;
			layoutThis.marginHeight = 0;
			this.setLayoutManager(layoutThis);

			this.setForegroundColor(THIS_FORE);
			this.setBackgroundColor(THIS_BACK);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(70),
					getMapMode().DPtoLP(50)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureClassNameFigure = new WrappingLabel();

			fFigureClassNameFigure.setText("<...>");

			fFigureClassNameFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(5), getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5)));

			this.add(fFigureClassNameFigure);

			fFigureClassFullNameFigure = new WrappingLabel();

			fFigureClassFullNameFigure.setText("<...>");

			fFigureClassFullNameFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(5), getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5)));

			this.add(fFigureClassFullNameFigure);

			fFigureClassDeclaredMethodCompartment = new RectangleFigure();

			GridData constraintFFigureClassDeclaredMethodCompartment = new GridData();
			constraintFFigureClassDeclaredMethodCompartment.verticalAlignment = GridData.FILL;
			constraintFFigureClassDeclaredMethodCompartment.horizontalAlignment = GridData.FILL;
			constraintFFigureClassDeclaredMethodCompartment.horizontalIndent = 0;
			constraintFFigureClassDeclaredMethodCompartment.horizontalSpan = 1;
			constraintFFigureClassDeclaredMethodCompartment.verticalSpan = 1;
			constraintFFigureClassDeclaredMethodCompartment.grabExcessHorizontalSpace = true;
			constraintFFigureClassDeclaredMethodCompartment.grabExcessVerticalSpace = true;
			this.add(fFigureClassDeclaredMethodCompartment,
					constraintFFigureClassDeclaredMethodCompartment);

			GridLayout layoutFFigureClassDeclaredMethodCompartment = new GridLayout();
			layoutFFigureClassDeclaredMethodCompartment.numColumns = 1;
			layoutFFigureClassDeclaredMethodCompartment.makeColumnsEqualWidth = true;
			fFigureClassDeclaredMethodCompartment
					.setLayoutManager(layoutFFigureClassDeclaredMethodCompartment);

		}

		@Override
		public void paintFigure(Graphics graphics) {
			super.paintFigure(graphics);

			Class classModel = (Class) resolveSemanticElement();
			if (classModel.isIsComplete()) {
				setBackgroundColor(THIS_BACK);
				setLineStyle(SWT.LINE_SOLID);
			} else {
				setBackgroundColor(UNCOMPLETE_FORE);
				if(classModel.getVariationType() != null){
					if(classModel.getVariationType().equals("optional")){
						setLineStyle(SWT.LINE_DASH);
					}				
				}
			}
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureClassNameFigure() {
			return fFigureClassNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureClassFullNameFigure() {
			return fFigureClassFullNameFigure;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureClassDeclaredMethodCompartment() {
			return fFigureClassDeclaredMethodCompartment;
		}

	}

	static final Color UNCOMPLETE_FORE = new Color(null, 203, 231, 203);

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 159, 242, 174);

}
