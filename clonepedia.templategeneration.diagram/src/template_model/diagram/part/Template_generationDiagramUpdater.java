package template_model.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

/**
 * @generated
 */
public class Template_generationDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationNodeDescriptor> getSemanticChildren(
			View view) {
		switch (template_model.diagram.part.Template_generationVisualIDRegistry
				.getVisualID(view)) {
		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
			return getTemplateGraph_1000SemanticChildren(view);
		case template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart.VISUAL_ID:
			return getInterfaceInterfaceDeclaredMethodCompartment_7003SemanticChildren(view);
		case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID:
			return getClassClassDeclaredMethodComparment_7002SemanticChildren(view);
		case template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart.VISUAL_ID:
			return getClassClassDeclaredMethodComparment_7004SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationNodeDescriptor> getTemplateGraph_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		template_model.TemplateGraph modelElement = (template_model.TemplateGraph) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationNodeDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationNodeDescriptor>();
		for (Iterator<?> it = modelElement.getElements().iterator(); it
				.hasNext();) {
			template_model.Element childElement = (template_model.Element) it
					.next();
			int visualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
			if (visualID == template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
			if (visualID == template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
			if (visualID == template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationNodeDescriptor> getInterfaceInterfaceDeclaredMethodCompartment_7003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		template_model.Interface modelElement = (template_model.Interface) containerView
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationNodeDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationNodeDescriptor>();
		for (Iterator<?> it = modelElement.getMethods().iterator(); it
				.hasNext();) {
			template_model.Method childElement = (template_model.Method) it
					.next();
			int visualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationNodeDescriptor> getClassClassDeclaredMethodComparment_7002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		template_model.Class modelElement = (template_model.Class) containerView
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationNodeDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationNodeDescriptor>();
		for (Iterator<?> it = modelElement.getMethods().iterator(); it
				.hasNext();) {
			template_model.Method childElement = (template_model.Method) it
					.next();
			int visualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getFields().iterator(); it.hasNext();) {
			template_model.Field childElement = (template_model.Field) it
					.next();
			int visualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getInnerClasses().iterator(); it
				.hasNext();) {
			template_model.Class childElement = (template_model.Class) it
					.next();
			int visualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationNodeDescriptor> getClassClassDeclaredMethodComparment_7004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		template_model.Class modelElement = (template_model.Class) containerView
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationNodeDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationNodeDescriptor>();
		for (Iterator<?> it = modelElement.getMethods().iterator(); it
				.hasNext();) {
			template_model.Method childElement = (template_model.Method) it
					.next();
			int visualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getFields().iterator(); it.hasNext();) {
			template_model.Field childElement = (template_model.Field) it
					.next();
			int visualID = template_model.diagram.part.Template_generationVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID) {
				result.add(new template_model.diagram.part.Template_generationNodeDescriptor(
						childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getContainedLinks(
			View view) {
		switch (template_model.diagram.part.Template_generationVisualIDRegistry
				.getVisualID(view)) {
		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
			return getTemplateGraph_1000ContainedLinks(view);
		case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
			return getField_2006ContainedLinks(view);
		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
			return getMethod_2003ContainedLinks(view);
		case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID:
			return getInterface_2001ContainedLinks(view);
		case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getClass_2005ContainedLinks(view);
		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
			return getMethod_3002ContainedLinks(view);
		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
			return getMethod_3003ContainedLinks(view);
		case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
			return getField_3006ContainedLinks(view);
		case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID:
			return getClass_3004ContainedLinks(view);
		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
			return getMethod_3005ContainedLinks(view);
		case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
			return getField_3007ContainedLinks(view);
		case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID:
			return getExtendInterface_4006ContainedLinks(view);
		case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID:
			return getCall_4007ContainedLinks(view);
		case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID:
			return getImplement_4008ContainedLinks(view);
		case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID:
			return getExtendClass_4010ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getIncomingLinks(
			View view) {
		switch (template_model.diagram.part.Template_generationVisualIDRegistry
				.getVisualID(view)) {
		case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
			return getField_2006IncomingLinks(view);
		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
			return getMethod_2003IncomingLinks(view);
		case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID:
			return getInterface_2001IncomingLinks(view);
		case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getClass_2005IncomingLinks(view);
		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
			return getMethod_3002IncomingLinks(view);
		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
			return getMethod_3003IncomingLinks(view);
		case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
			return getField_3006IncomingLinks(view);
		case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID:
			return getClass_3004IncomingLinks(view);
		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
			return getMethod_3005IncomingLinks(view);
		case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
			return getField_3007IncomingLinks(view);
		case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID:
			return getExtendInterface_4006IncomingLinks(view);
		case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID:
			return getCall_4007IncomingLinks(view);
		case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID:
			return getImplement_4008IncomingLinks(view);
		case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID:
			return getExtendClass_4010IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getOutgoingLinks(
			View view) {
		switch (template_model.diagram.part.Template_generationVisualIDRegistry
				.getVisualID(view)) {
		case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
			return getField_2006OutgoingLinks(view);
		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
			return getMethod_2003OutgoingLinks(view);
		case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID:
			return getInterface_2001OutgoingLinks(view);
		case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getClass_2005OutgoingLinks(view);
		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
			return getMethod_3002OutgoingLinks(view);
		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
			return getMethod_3003OutgoingLinks(view);
		case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
			return getField_3006OutgoingLinks(view);
		case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID:
			return getClass_3004OutgoingLinks(view);
		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
			return getMethod_3005OutgoingLinks(view);
		case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
			return getField_3007OutgoingLinks(view);
		case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID:
			return getExtendInterface_4006OutgoingLinks(view);
		case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID:
			return getCall_4007OutgoingLinks(view);
		case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID:
			return getImplement_4008OutgoingLinks(view);
		case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID:
			return getExtendClass_4010OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getTemplateGraph_1000ContainedLinks(
			View view) {
		template_model.TemplateGraph modelElement = (template_model.TemplateGraph) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_ExtendInterface_4006(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Call_4007(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Implement_4008(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_ExtendClass_4010(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_2006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_2003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getInterface_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getClass_2005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_3006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getClass_3004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_3007ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getExtendInterface_4006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getCall_4007ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getImplement_4008ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getExtendClass_4010ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_2006IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_2003IncomingLinks(
			View view) {
		template_model.Method modelElement = (template_model.Method) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Call_4007(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getInterface_2001IncomingLinks(
			View view) {
		template_model.Interface modelElement = (template_model.Interface) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_ExtendInterface_4006(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Implement_4008(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getClass_2005IncomingLinks(
			View view) {
		template_model.Class modelElement = (template_model.Class) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_ExtendClass_4010(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3002IncomingLinks(
			View view) {
		template_model.Method modelElement = (template_model.Method) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Call_4007(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3003IncomingLinks(
			View view) {
		template_model.Method modelElement = (template_model.Method) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Call_4007(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_3006IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getClass_3004IncomingLinks(
			View view) {
		template_model.Class modelElement = (template_model.Class) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_ExtendClass_4010(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3005IncomingLinks(
			View view) {
		template_model.Method modelElement = (template_model.Method) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Call_4007(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_3007IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getExtendInterface_4006IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getCall_4007IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getImplement_4008IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getExtendClass_4010IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_2006OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_2003OutgoingLinks(
			View view) {
		template_model.Method modelElement = (template_model.Method) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Call_4007(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getInterface_2001OutgoingLinks(
			View view) {
		template_model.Interface modelElement = (template_model.Interface) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_ExtendInterface_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getClass_2005OutgoingLinks(
			View view) {
		template_model.Class modelElement = (template_model.Class) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Implement_4008(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_ExtendClass_4010(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3002OutgoingLinks(
			View view) {
		template_model.Method modelElement = (template_model.Method) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Call_4007(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3003OutgoingLinks(
			View view) {
		template_model.Method modelElement = (template_model.Method) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Call_4007(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_3006OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getClass_3004OutgoingLinks(
			View view) {
		template_model.Class modelElement = (template_model.Class) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Implement_4008(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_ExtendClass_4010(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getMethod_3005OutgoingLinks(
			View view) {
		template_model.Method modelElement = (template_model.Method) view
				.getElement();
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Call_4007(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getField_3007OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getExtendInterface_4006OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getCall_4007OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getImplement_4008OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<template_model.diagram.part.Template_generationLinkDescriptor> getExtendClass_4010OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getContainedTypeModelFacetLinks_ExtendInterface_4006(
			template_model.TemplateGraph container) {
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		for (Iterator<?> links = container.getLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof template_model.ExtendInterface) {
				continue;
			}
			template_model.ExtendInterface link = (template_model.ExtendInterface) linkObject;
			if (template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			List targets = link.getSuperInterface();
			Object theTarget = targets.size() == 1 ? targets.get(0) : null;
			if (false == theTarget instanceof template_model.Interface) {
				continue;
			}
			template_model.Interface dst = (template_model.Interface) theTarget;
			template_model.Interface src = link.getSubInterface();
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					dst,
					link,
					template_model.diagram.providers.Template_generationElementTypes.ExtendInterface_4006,
					template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getContainedTypeModelFacetLinks_Call_4007(
			template_model.TemplateGraph container) {
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		for (Iterator<?> links = container.getLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof template_model.Call) {
				continue;
			}
			template_model.Call link = (template_model.Call) linkObject;
			if (template_model.diagram.edit.parts.CallEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			template_model.Method dst = link.getCalleeMethod();
			template_model.Method src = link.getCallerMethod();
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					dst,
					link,
					template_model.diagram.providers.Template_generationElementTypes.Call_4007,
					template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getContainedTypeModelFacetLinks_Implement_4008(
			template_model.TemplateGraph container) {
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		for (Iterator<?> links = container.getLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof template_model.Implement) {
				continue;
			}
			template_model.Implement link = (template_model.Implement) linkObject;
			if (template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			List targets = link.getInterface();
			Object theTarget = targets.size() == 1 ? targets.get(0) : null;
			if (false == theTarget instanceof template_model.Interface) {
				continue;
			}
			template_model.Interface dst = (template_model.Interface) theTarget;
			template_model.Class src = link.getClass_();
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					dst,
					link,
					template_model.diagram.providers.Template_generationElementTypes.Implement_4008,
					template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getContainedTypeModelFacetLinks_ExtendClass_4010(
			template_model.TemplateGraph container) {
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		for (Iterator<?> links = container.getLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof template_model.ExtendClass) {
				continue;
			}
			template_model.ExtendClass link = (template_model.ExtendClass) linkObject;
			if (template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			template_model.Class dst = link.getSuperClass();
			template_model.Class src = link.getSubClass();
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					dst,
					link,
					template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010,
					template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getIncomingTypeModelFacetLinks_ExtendInterface_4006(
			template_model.Interface target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != template_model.Template_modelPackage.eINSTANCE
					.getExtendInterface_SuperInterface()
					|| false == setting.getEObject() instanceof template_model.ExtendInterface) {
				continue;
			}
			template_model.ExtendInterface link = (template_model.ExtendInterface) setting
					.getEObject();
			if (template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			template_model.Interface src = link.getSubInterface();
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					target,
					link,
					template_model.diagram.providers.Template_generationElementTypes.ExtendInterface_4006,
					template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getIncomingTypeModelFacetLinks_Call_4007(
			template_model.Method target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != template_model.Template_modelPackage.eINSTANCE
					.getCall_CalleeMethod()
					|| false == setting.getEObject() instanceof template_model.Call) {
				continue;
			}
			template_model.Call link = (template_model.Call) setting
					.getEObject();
			if (template_model.diagram.edit.parts.CallEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			template_model.Method src = link.getCallerMethod();
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					target,
					link,
					template_model.diagram.providers.Template_generationElementTypes.Call_4007,
					template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getIncomingTypeModelFacetLinks_Implement_4008(
			template_model.Interface target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != template_model.Template_modelPackage.eINSTANCE
					.getImplement_Interface()
					|| false == setting.getEObject() instanceof template_model.Implement) {
				continue;
			}
			template_model.Implement link = (template_model.Implement) setting
					.getEObject();
			if (template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			template_model.Class src = link.getClass_();
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					target,
					link,
					template_model.diagram.providers.Template_generationElementTypes.Implement_4008,
					template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getIncomingTypeModelFacetLinks_ExtendClass_4010(
			template_model.Class target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != template_model.Template_modelPackage.eINSTANCE
					.getExtendClass_SuperClass()
					|| false == setting.getEObject() instanceof template_model.ExtendClass) {
				continue;
			}
			template_model.ExtendClass link = (template_model.ExtendClass) setting
					.getEObject();
			if (template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			template_model.Class src = link.getSubClass();
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					target,
					link,
					template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010,
					template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getOutgoingTypeModelFacetLinks_ExtendInterface_4006(
			template_model.Interface source) {
		template_model.TemplateGraph container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof template_model.TemplateGraph) {
				container = (template_model.TemplateGraph) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		for (Iterator<?> links = container.getLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof template_model.ExtendInterface) {
				continue;
			}
			template_model.ExtendInterface link = (template_model.ExtendInterface) linkObject;
			if (template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			List targets = link.getSuperInterface();
			Object theTarget = targets.size() == 1 ? targets.get(0) : null;
			if (false == theTarget instanceof template_model.Interface) {
				continue;
			}
			template_model.Interface dst = (template_model.Interface) theTarget;
			template_model.Interface src = link.getSubInterface();
			if (src != source) {
				continue;
			}
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					dst,
					link,
					template_model.diagram.providers.Template_generationElementTypes.ExtendInterface_4006,
					template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getOutgoingTypeModelFacetLinks_Call_4007(
			template_model.Method source) {
		template_model.TemplateGraph container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof template_model.TemplateGraph) {
				container = (template_model.TemplateGraph) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		for (Iterator<?> links = container.getLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof template_model.Call) {
				continue;
			}
			template_model.Call link = (template_model.Call) linkObject;
			if (template_model.diagram.edit.parts.CallEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			template_model.Method dst = link.getCalleeMethod();
			template_model.Method src = link.getCallerMethod();
			if (src != source) {
				continue;
			}
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					dst,
					link,
					template_model.diagram.providers.Template_generationElementTypes.Call_4007,
					template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getOutgoingTypeModelFacetLinks_Implement_4008(
			template_model.Class source) {
		template_model.TemplateGraph container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof template_model.TemplateGraph) {
				container = (template_model.TemplateGraph) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		for (Iterator<?> links = container.getLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof template_model.Implement) {
				continue;
			}
			template_model.Implement link = (template_model.Implement) linkObject;
			if (template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			List targets = link.getInterface();
			Object theTarget = targets.size() == 1 ? targets.get(0) : null;
			if (false == theTarget instanceof template_model.Interface) {
				continue;
			}
			template_model.Interface dst = (template_model.Interface) theTarget;
			template_model.Class src = link.getClass_();
			if (src != source) {
				continue;
			}
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					dst,
					link,
					template_model.diagram.providers.Template_generationElementTypes.Implement_4008,
					template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<template_model.diagram.part.Template_generationLinkDescriptor> getOutgoingTypeModelFacetLinks_ExtendClass_4010(
			template_model.Class source) {
		template_model.TemplateGraph container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof template_model.TemplateGraph) {
				container = (template_model.TemplateGraph) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<template_model.diagram.part.Template_generationLinkDescriptor> result = new LinkedList<template_model.diagram.part.Template_generationLinkDescriptor>();
		for (Iterator<?> links = container.getLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof template_model.ExtendClass) {
				continue;
			}
			template_model.ExtendClass link = (template_model.ExtendClass) linkObject;
			if (template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID != template_model.diagram.part.Template_generationVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			template_model.Class dst = link.getSuperClass();
			template_model.Class src = link.getSubClass();
			if (src != source) {
				continue;
			}
			result.add(new template_model.diagram.part.Template_generationLinkDescriptor(
					src,
					dst,
					link,
					template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010,
					template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<template_model.diagram.part.Template_generationNodeDescriptor> getSemanticChildren(
				View view) {
			return Template_generationDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<template_model.diagram.part.Template_generationLinkDescriptor> getContainedLinks(
				View view) {
			return Template_generationDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<template_model.diagram.part.Template_generationLinkDescriptor> getIncomingLinks(
				View view) {
			return Template_generationDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<template_model.diagram.part.Template_generationLinkDescriptor> getOutgoingLinks(
				View view) {
			return Template_generationDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
