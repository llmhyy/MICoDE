package template_model.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class Template_generationNavigatorContentProvider implements
		ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private Viewer myViewer;

	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;

	/**
	 * @generated
	 */
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public Template_generationNavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
				.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			public Object get(Object key) {
				if (!containsKey(key)) {
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable() {
			public void run() {
				if (myViewer != null) {
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain,
				new WorkspaceSynchronizer.Delegate() {
					public void dispose() {
					}

					public boolean handleResourceChanged(final Resource resource) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}

					public boolean handleResourceDeleted(Resource resource) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}

					public boolean handleResourceMoved(Resource resource,
							final URI newURI) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}
				});
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		myViewer = null;
		unloadAllResources();
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myViewer = viewer;
	}

	/**
	 * @generated
	 */
	void unloadAllResources() {
		for (Resource nextResource : myEditingDomain.getResourceSet()
				.getResources()) {
			nextResource.unload();
		}
	}

	/**
	 * @generated
	 */
	void asyncRefresh() {
		if (myViewer != null && !myViewer.getControl().isDisposed()) {
			myViewer.getControl().getDisplay()
					.asyncExec(myViewerRefreshRunnable);
		}
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
					.toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(
					fileURI, true);
			ArrayList<template_model.diagram.navigator.Template_generationNavigatorItem> result = new ArrayList<template_model.diagram.navigator.Template_generationNavigatorItem>();
			ArrayList<View> topViews = new ArrayList<View>(resource
					.getContents().size());
			for (EObject o : resource.getContents()) {
				if (o instanceof View) {
					topViews.add((View) o);
				}
			}
			result.addAll(createNavigatorItems(
					selectViewsByType(
							topViews,
							template_model.diagram.edit.parts.TemplateGraphEditPart.MODEL_ID),
					file, false));
			return result.toArray();
		}

		if (parentElement instanceof template_model.diagram.navigator.Template_generationNavigatorGroup) {
			template_model.diagram.navigator.Template_generationNavigatorGroup group = (template_model.diagram.navigator.Template_generationNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof template_model.diagram.navigator.Template_generationNavigatorItem) {
			template_model.diagram.navigator.Template_generationNavigatorItem navigatorItem = (template_model.diagram.navigator.Template_generationNavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (template_model.diagram.part.Template_generationVisualIDRegistry
				.getVisualID(view)) {

		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Node sv = (Node) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup incominglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Method_3002_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup outgoinglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Method_3002_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Node sv = (Node) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup outgoinglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Class_2005_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup incominglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Class_2005_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Node sv = (Node) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup incominglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Interface_2001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup outgoinglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Interface_2001_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Node sv = (Node) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup incominglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Method_2003_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup outgoinglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Method_2003_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Diagram sv = (Diagram) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup links = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_TemplateGraph_1000_links,
					"icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			if (!links.isEmpty()) {
				result.add(links);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup target = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_ExtendInterface_4006_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup source = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_ExtendInterface_4006_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Node sv = (Node) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup incominglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Method_3003_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup outgoinglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Method_3003_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup target = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Implement_4008_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup source = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Implement_4008_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Node sv = (Node) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup outgoinglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Class_3004_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup incominglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Class_3004_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Node sv = (Node) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup incominglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Method_3005_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup outgoinglinks = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Method_3005_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.CallEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup target = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_ExtendClass_4010_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup source = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_ExtendClass_4010_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID: {
			LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem> result = new LinkedList<template_model.diagram.navigator.Template_generationAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			template_model.diagram.navigator.Template_generationNavigatorGroup target = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Call_4007_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			template_model.diagram.navigator.Template_generationNavigatorGroup source = new template_model.diagram.navigator.Template_generationNavigatorGroup(
					template_model.diagram.part.Messages.NavigatorGroupName_Call_4007_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(sv),
					template_model.diagram.part.Template_generationVisualIDRegistry
							.getType(template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksSourceByType(Collection<Edge> edges,
			String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType())
					&& isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksTargetByType(Collection<Edge> edges,
			String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType())
					&& isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getOutgoingLinksByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getIncomingLinksByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getChildrenByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getDiagramLinksByType(
			Collection<Diagram> diagrams, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (Diagram nextDiagram : diagrams) {
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	// TODO refactor as static method
	/**
	 * @generated
	 */
	private Collection<View> selectViewsByType(Collection<View> views,
			String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (View nextView : views) {
			if (type.equals(nextView.getType()) && isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return template_model.diagram.edit.parts.TemplateGraphEditPart.MODEL_ID
				.equals(template_model.diagram.part.Template_generationVisualIDRegistry
						.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection<template_model.diagram.navigator.Template_generationNavigatorItem> createNavigatorItems(
			Collection<View> views, Object parent, boolean isLeafs) {
		ArrayList<template_model.diagram.navigator.Template_generationNavigatorItem> result = new ArrayList<template_model.diagram.navigator.Template_generationNavigatorItem>(
				views.size());
		for (View nextView : views) {
			result.add(new template_model.diagram.navigator.Template_generationNavigatorItem(
					nextView, parent, isLeafs));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		if (element instanceof template_model.diagram.navigator.Template_generationAbstractNavigatorItem) {
			template_model.diagram.navigator.Template_generationAbstractNavigatorItem abstractNavigatorItem = (template_model.diagram.navigator.Template_generationAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

}
