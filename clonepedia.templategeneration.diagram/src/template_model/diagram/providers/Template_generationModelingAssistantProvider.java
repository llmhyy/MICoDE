package template_model.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @generated
 */
public class Template_generationModelingAssistantProvider extends
		ModelingAssistantProvider {

	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof template_model.diagram.edit.parts.TemplateGraphEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(4);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Field_2006);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Method_2003);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Interface_2001);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Class_2005);
			return types;
		}
		if (editPart instanceof template_model.diagram.edit.parts.InterfaceInterfaceDeclaredMethodCompartmentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Method_3002);
			return types;
		}
		if (editPart instanceof template_model.diagram.edit.parts.ClassClassDeclaredMethodComparmentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Method_3003);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Field_3006);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Class_3004);
			return types;
		}
		if (editPart instanceof template_model.diagram.edit.parts.ClassClassDeclaredMethodComparment2EditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(2);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Method_3005);
			types.add(template_model.diagram.providers.Template_generationElementTypes.Field_3007);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof template_model.diagram.edit.parts.MethodEditPart) {
			return ((template_model.diagram.edit.parts.MethodEditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.InterfaceEditPart) {
			return ((template_model.diagram.edit.parts.InterfaceEditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.ClassEditPart) {
			return ((template_model.diagram.edit.parts.ClassEditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method3EditPart) {
			return ((template_model.diagram.edit.parts.Method3EditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method4EditPart) {
			return ((template_model.diagram.edit.parts.Method4EditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Class2EditPart) {
			return ((template_model.diagram.edit.parts.Class2EditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method5EditPart) {
			return ((template_model.diagram.edit.parts.Method5EditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof template_model.diagram.edit.parts.MethodEditPart) {
			return ((template_model.diagram.edit.parts.MethodEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.InterfaceEditPart) {
			return ((template_model.diagram.edit.parts.InterfaceEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.ClassEditPart) {
			return ((template_model.diagram.edit.parts.ClassEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Method3EditPart) {
			return ((template_model.diagram.edit.parts.Method3EditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Method4EditPart) {
			return ((template_model.diagram.edit.parts.Method4EditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Class2EditPart) {
			return ((template_model.diagram.edit.parts.Class2EditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Method5EditPart) {
			return ((template_model.diagram.edit.parts.Method5EditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof template_model.diagram.edit.parts.MethodEditPart) {
			return ((template_model.diagram.edit.parts.MethodEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.InterfaceEditPart) {
			return ((template_model.diagram.edit.parts.InterfaceEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.ClassEditPart) {
			return ((template_model.diagram.edit.parts.ClassEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method3EditPart) {
			return ((template_model.diagram.edit.parts.Method3EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method4EditPart) {
			return ((template_model.diagram.edit.parts.Method4EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Class2EditPart) {
			return ((template_model.diagram.edit.parts.Class2EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method5EditPart) {
			return ((template_model.diagram.edit.parts.Method5EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof template_model.diagram.edit.parts.MethodEditPart) {
			return ((template_model.diagram.edit.parts.MethodEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.InterfaceEditPart) {
			return ((template_model.diagram.edit.parts.InterfaceEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.ClassEditPart) {
			return ((template_model.diagram.edit.parts.ClassEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Method3EditPart) {
			return ((template_model.diagram.edit.parts.Method3EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Method4EditPart) {
			return ((template_model.diagram.edit.parts.Method4EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Class2EditPart) {
			return ((template_model.diagram.edit.parts.Class2EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof template_model.diagram.edit.parts.Method5EditPart) {
			return ((template_model.diagram.edit.parts.Method5EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof template_model.diagram.edit.parts.MethodEditPart) {
			return ((template_model.diagram.edit.parts.MethodEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.InterfaceEditPart) {
			return ((template_model.diagram.edit.parts.InterfaceEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.ClassEditPart) {
			return ((template_model.diagram.edit.parts.ClassEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method3EditPart) {
			return ((template_model.diagram.edit.parts.Method3EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method4EditPart) {
			return ((template_model.diagram.edit.parts.Method4EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Class2EditPart) {
			return ((template_model.diagram.edit.parts.Class2EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof template_model.diagram.edit.parts.Method5EditPart) {
			return ((template_model.diagram.edit.parts.Method5EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target,
			IElementType relationshipType) {
		return selectExistingElement(target,
				getTypesForSource(target, relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source,
			IElementType relationshipType) {
		return selectExistingElement(source,
				getTypesForTarget(source, relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		HashSet<EObject> elements = new HashSet<EObject>();
		for (Iterator<EObject> it = diagram.getElement().eAllContents(); it
				.hasNext();) {
			EObject element = it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements
				.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(
				element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				template_model.diagram.part.Template_generationDiagramEditorPlugin
						.getInstance().getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, labelProvider);
		dialog.setMessage(template_model.diagram.part.Messages.Template_generationModelingAssistantProviderMessage);
		dialog.setTitle(template_model.diagram.part.Messages.Template_generationModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
