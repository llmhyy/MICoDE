package template_model.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class Template_generationVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "clonepedia.templategeneration.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (template_model.diagram.edit.parts.TemplateGraphEditPart.MODEL_ID
					.equals(view.getType())) {
				return template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return template_model.diagram.part.Template_generationVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				template_model.diagram.part.Template_generationDiagramEditorPlugin
						.getInstance().logError(
								"Unable to parse view type as a visualID number: "
										+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (template_model.Template_modelPackage.eINSTANCE.getTemplateGraph()
				.isSuperTypeOf(domainElement.eClass())
				&& isDiagram((template_model.TemplateGraph) domainElement)) {
			return template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = template_model.diagram.part.Template_generationVisualIDRegistry
				.getModelID(containerView);
		if (!template_model.diagram.edit.parts.TemplateGraphEditPart.MODEL_ID
				.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (template_model.diagram.edit.parts.TemplateGraphEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
			if (template_model.Template_modelPackage.eINSTANCE.getField()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID;
			}
			if (template_model.Template_modelPackage.eINSTANCE.getMethod()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID;
			}
			if (template_model.Template_modelPackage.eINSTANCE.getInterface()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID;
			}
			if (template_model.Template_modelPackage.eINSTANCE.getClass_()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID;
			}
			break;
		case template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart.VISUAL_ID:
			if (template_model.Template_modelPackage.eINSTANCE.getMethod()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID;
			}
			break;
		case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID:
			if (template_model.Template_modelPackage.eINSTANCE.getMethod()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID;
			}
			if (template_model.Template_modelPackage.eINSTANCE.getField()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID;
			}
			if (template_model.Template_modelPackage.eINSTANCE.getClass_()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID;
			}
			break;
		case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart.VISUAL_ID:
			if (template_model.Template_modelPackage.eINSTANCE.getMethod()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID;
			}
			if (template_model.Template_modelPackage.eINSTANCE.getField()
					.isSuperTypeOf(domainElement.eClass())) {
				return template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = template_model.diagram.part.Template_generationVisualIDRegistry
				.getModelID(containerView);
		if (!template_model.diagram.edit.parts.TemplateGraphEditPart.MODEL_ID
				.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (template_model.diagram.edit.parts.TemplateGraphEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.FieldName3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.MethodNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.InterfaceNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.InterfaceFullNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.ClassNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.ClassFullNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.MethodName3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.MethodName4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.FieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.ClassName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.MethodName5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.FieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.ExtendInterfaceNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.CallNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.ImplementNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID:
			if (template_model.diagram.edit.parts.ExtendClassNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (template_model.Template_modelPackage.eINSTANCE.getExtendInterface()
				.isSuperTypeOf(domainElement.eClass())) {
			return template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID;
		}
		if (template_model.Template_modelPackage.eINSTANCE.getCall()
				.isSuperTypeOf(domainElement.eClass())) {
			return template_model.diagram.edit.parts.CallEditPart.VISUAL_ID;
		}
		if (template_model.Template_modelPackage.eINSTANCE.getImplement()
				.isSuperTypeOf(domainElement.eClass())) {
			return template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID;
		}
		if (template_model.Template_modelPackage.eINSTANCE.getExtendClass()
				.isSuperTypeOf(domainElement.eClass())) {
			return template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(template_model.TemplateGraph element) {
		return true;
	}

	/**
	 * @generated
	 */
	public static boolean checkNodeVisualID(View containerView,
			EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	 * @generated
	 */
	public static boolean isCompartmentVisualID(int visualID) {
		switch (visualID) {
		case template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart.VISUAL_ID:
		case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID:
		case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
			return false;
		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
		case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
		case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
		case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		 * @generated
		 */
		@Override
		public int getVisualID(View view) {
			return template_model.diagram.part.Template_generationVisualIDRegistry
					.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return template_model.diagram.part.Template_generationVisualIDRegistry
					.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return template_model.diagram.part.Template_generationVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView,
				EObject domainElement, int candidate) {
			return template_model.diagram.part.Template_generationVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return template_model.diagram.part.Template_generationVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return template_model.diagram.part.Template_generationVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}
