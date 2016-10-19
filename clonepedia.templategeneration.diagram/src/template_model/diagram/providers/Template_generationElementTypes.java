package template_model.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class Template_generationElementTypes {

	/**
	 * @generated
	 */
	private Template_generationElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType TemplateGraph_1000 = getElementType("clonepedia.templategeneration.diagram.TemplateGraph_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Field_2006 = getElementType("clonepedia.templategeneration.diagram.Field_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Method_2003 = getElementType("clonepedia.templategeneration.diagram.Method_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Interface_2001 = getElementType("clonepedia.templategeneration.diagram.Interface_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Class_2005 = getElementType("clonepedia.templategeneration.diagram.Class_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Method_3002 = getElementType("clonepedia.templategeneration.diagram.Method_3002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Method_3003 = getElementType("clonepedia.templategeneration.diagram.Method_3003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Field_3006 = getElementType("clonepedia.templategeneration.diagram.Field_3006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Class_3004 = getElementType("clonepedia.templategeneration.diagram.Class_3004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Method_3005 = getElementType("clonepedia.templategeneration.diagram.Method_3005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Field_3007 = getElementType("clonepedia.templategeneration.diagram.Field_3007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ExtendInterface_4006 = getElementType("clonepedia.templategeneration.diagram.ExtendInterface_4006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Call_4007 = getElementType("clonepedia.templategeneration.diagram.Call_4007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Implement_4008 = getElementType("clonepedia.templategeneration.diagram.Implement_4008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ExtendClass_4010 = getElementType("clonepedia.templategeneration.diagram.ExtendClass_4010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return template_model.diagram.part.Template_generationDiagramEditorPlugin
						.getInstance().getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(TemplateGraph_1000,
					template_model.Template_modelPackage.eINSTANCE
							.getTemplateGraph());

			elements.put(Field_2006,
					template_model.Template_modelPackage.eINSTANCE.getField());

			elements.put(Method_2003,
					template_model.Template_modelPackage.eINSTANCE.getMethod());

			elements.put(Interface_2001,
					template_model.Template_modelPackage.eINSTANCE
							.getInterface());

			elements.put(Class_2005,
					template_model.Template_modelPackage.eINSTANCE.getClass_());

			elements.put(Method_3002,
					template_model.Template_modelPackage.eINSTANCE.getMethod());

			elements.put(Method_3003,
					template_model.Template_modelPackage.eINSTANCE.getMethod());

			elements.put(Field_3006,
					template_model.Template_modelPackage.eINSTANCE.getField());

			elements.put(Class_3004,
					template_model.Template_modelPackage.eINSTANCE.getClass_());

			elements.put(Method_3005,
					template_model.Template_modelPackage.eINSTANCE.getMethod());

			elements.put(Field_3007,
					template_model.Template_modelPackage.eINSTANCE.getField());

			elements.put(ExtendInterface_4006,
					template_model.Template_modelPackage.eINSTANCE
							.getExtendInterface());

			elements.put(Call_4007,
					template_model.Template_modelPackage.eINSTANCE.getCall());

			elements.put(Implement_4008,
					template_model.Template_modelPackage.eINSTANCE
							.getImplement());

			elements.put(ExtendClass_4010,
					template_model.Template_modelPackage.eINSTANCE
							.getExtendClass());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(TemplateGraph_1000);
			KNOWN_ELEMENT_TYPES.add(Field_2006);
			KNOWN_ELEMENT_TYPES.add(Method_2003);
			KNOWN_ELEMENT_TYPES.add(Interface_2001);
			KNOWN_ELEMENT_TYPES.add(Class_2005);
			KNOWN_ELEMENT_TYPES.add(Method_3002);
			KNOWN_ELEMENT_TYPES.add(Method_3003);
			KNOWN_ELEMENT_TYPES.add(Field_3006);
			KNOWN_ELEMENT_TYPES.add(Class_3004);
			KNOWN_ELEMENT_TYPES.add(Method_3005);
			KNOWN_ELEMENT_TYPES.add(Field_3007);
			KNOWN_ELEMENT_TYPES.add(ExtendInterface_4006);
			KNOWN_ELEMENT_TYPES.add(Call_4007);
			KNOWN_ELEMENT_TYPES.add(Implement_4008);
			KNOWN_ELEMENT_TYPES.add(ExtendClass_4010);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
			return TemplateGraph_1000;
		case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
			return Field_2006;
		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
			return Method_2003;
		case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID:
			return Interface_2001;
		case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return Class_2005;
		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
			return Method_3002;
		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
			return Method_3003;
		case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
			return Field_3006;
		case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID:
			return Class_3004;
		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
			return Method_3005;
		case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
			return Field_3007;
		case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID:
			return ExtendInterface_4006;
		case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID:
			return Call_4007;
		case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID:
			return Implement_4008;
		case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID:
			return ExtendClass_4010;
		}
		return null;
	}

}
