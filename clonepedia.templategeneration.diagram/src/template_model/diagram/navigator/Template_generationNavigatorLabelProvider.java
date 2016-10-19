package template_model.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class Template_generationNavigatorLabelProvider extends LabelProvider
		implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		template_model.diagram.part.Template_generationDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		template_model.diagram.part.Template_generationDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof template_model.diagram.navigator.Template_generationNavigatorItem
				&& !isOwnView(((template_model.diagram.navigator.Template_generationNavigatorItem) element)
						.getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof template_model.diagram.navigator.Template_generationNavigatorGroup) {
			template_model.diagram.navigator.Template_generationNavigatorGroup group = (template_model.diagram.navigator.Template_generationNavigatorGroup) element;
			return template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof template_model.diagram.navigator.Template_generationNavigatorItem) {
			template_model.diagram.navigator.Template_generationNavigatorItem navigatorItem = (template_model.diagram.navigator.Template_generationNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (template_model.diagram.part.Template_generationVisualIDRegistry
				.getVisualID(view)) {
		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.se.fudan.edu.cn/clonepedia?Method", template_model.diagram.providers.Template_generationElementTypes.Method_3002); //$NON-NLS-1$
		case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.se.fudan.edu.cn/clonepedia?Class", template_model.diagram.providers.Template_generationElementTypes.Class_2005); //$NON-NLS-1$
		case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.se.fudan.edu.cn/clonepedia?Interface", template_model.diagram.providers.Template_generationElementTypes.Interface_2001); //$NON-NLS-1$
		case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.se.fudan.edu.cn/clonepedia?Field", template_model.diagram.providers.Template_generationElementTypes.Field_3007); //$NON-NLS-1$
		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.se.fudan.edu.cn/clonepedia?Method", template_model.diagram.providers.Template_generationElementTypes.Method_2003); //$NON-NLS-1$
		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.se.fudan.edu.cn/clonepedia?TemplateGraph", template_model.diagram.providers.Template_generationElementTypes.TemplateGraph_1000); //$NON-NLS-1$
		case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.se.fudan.edu.cn/clonepedia?ExtendInterface", template_model.diagram.providers.Template_generationElementTypes.ExtendInterface_4006); //$NON-NLS-1$
		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.se.fudan.edu.cn/clonepedia?Method", template_model.diagram.providers.Template_generationElementTypes.Method_3003); //$NON-NLS-1$
		case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.se.fudan.edu.cn/clonepedia?Implement", template_model.diagram.providers.Template_generationElementTypes.Implement_4008); //$NON-NLS-1$
		case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.se.fudan.edu.cn/clonepedia?Class", template_model.diagram.providers.Template_generationElementTypes.Class_3004); //$NON-NLS-1$
		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.se.fudan.edu.cn/clonepedia?Method", template_model.diagram.providers.Template_generationElementTypes.Method_3005); //$NON-NLS-1$
		case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.se.fudan.edu.cn/clonepedia?Field", template_model.diagram.providers.Template_generationElementTypes.Field_2006); //$NON-NLS-1$
		case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.se.fudan.edu.cn/clonepedia?ExtendClass", template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010); //$NON-NLS-1$
		case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.se.fudan.edu.cn/clonepedia?Field", template_model.diagram.providers.Template_generationElementTypes.Field_3006); //$NON-NLS-1$
		case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.se.fudan.edu.cn/clonepedia?Call", template_model.diagram.providers.Template_generationElementTypes.Call_4007); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = template_model.diagram.part.Template_generationDiagramEditorPlugin
				.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null
				&& elementType != null
				&& template_model.diagram.providers.Template_generationElementTypes
						.isKnownElementType(elementType)) {
			image = template_model.diagram.providers.Template_generationElementTypes
					.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof template_model.diagram.navigator.Template_generationNavigatorGroup) {
			template_model.diagram.navigator.Template_generationNavigatorGroup group = (template_model.diagram.navigator.Template_generationNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof template_model.diagram.navigator.Template_generationNavigatorItem) {
			template_model.diagram.navigator.Template_generationNavigatorItem navigatorItem = (template_model.diagram.navigator.Template_generationNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (template_model.diagram.part.Template_generationVisualIDRegistry
				.getVisualID(view)) {
		case template_model.diagram.edit.parts.Method3EditPart.VISUAL_ID:
			return getMethod_3002Text(view);
		case template_model.diagram.edit.parts.ClassEditPart.VISUAL_ID:
			return getClass_2005Text(view);
		case template_model.diagram.edit.parts.InterfaceEditPart.VISUAL_ID:
			return getInterface_2001Text(view);
		case template_model.diagram.edit.parts.Field2EditPart.VISUAL_ID:
			return getField_3007Text(view);
		case template_model.diagram.edit.parts.MethodEditPart.VISUAL_ID:
			return getMethod_2003Text(view);
		case template_model.diagram.edit.parts.TemplateGraphEditPart.VISUAL_ID:
			return getTemplateGraph_1000Text(view);
		case template_model.diagram.edit.parts.ExtendInterfaceEditPart.VISUAL_ID:
			return getExtendInterface_4006Text(view);
		case template_model.diagram.edit.parts.Method4EditPart.VISUAL_ID:
			return getMethod_3003Text(view);
		case template_model.diagram.edit.parts.ImplementEditPart.VISUAL_ID:
			return getImplement_4008Text(view);
		case template_model.diagram.edit.parts.Class2EditPart.VISUAL_ID:
			return getClass_3004Text(view);
		case template_model.diagram.edit.parts.Method5EditPart.VISUAL_ID:
			return getMethod_3005Text(view);
		case template_model.diagram.edit.parts.Field3EditPart.VISUAL_ID:
			return getField_2006Text(view);
		case template_model.diagram.edit.parts.ExtendClassEditPart.VISUAL_ID:
			return getExtendClass_4010Text(view);
		case template_model.diagram.edit.parts.FieldEditPart.VISUAL_ID:
			return getField_3006Text(view);
		case template_model.diagram.edit.parts.CallEditPart.VISUAL_ID:
			return getCall_4007Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getTemplateGraph_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getClass_2005Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Class_2005,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.ClassNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getMethod_3003Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Method_3003,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.MethodName4EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getField_3007Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Field_3007,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.FieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5016); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getMethod_2003Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Method_2003,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.MethodNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getClass_3004Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Class_3004,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.ClassName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getExtendClass_4010Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.ExtendClassNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getCall_4007Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Call_4007,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.CallNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getMethod_3005Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Method_3005,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.MethodName5EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getExtendInterface_4006Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.ExtendInterface_4006,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.ExtendInterfaceNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getMethod_3002Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Method_3002,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.MethodName3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getField_3006Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Field_3006,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.FieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5015); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getImplement_4008Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Implement_4008,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.ImplementNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 6003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getInterface_2001Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Interface_2001,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.InterfaceNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getField_2006Text(View view) {
		IParser parser = template_model.diagram.providers.Template_generationParserProvider
				.getParser(
						template_model.diagram.providers.Template_generationElementTypes.Field_2006,
						view.getElement() != null ? view.getElement() : view,
						template_model.diagram.part.Template_generationVisualIDRegistry
								.getType(template_model.diagram.edit.parts.FieldName3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			template_model.diagram.part.Template_generationDiagramEditorPlugin
					.getInstance().logError(
							"Parser was not found for label " + 5017); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
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
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return template_model.diagram.edit.parts.TemplateGraphEditPart.MODEL_ID
				.equals(template_model.diagram.part.Template_generationVisualIDRegistry
						.getModelID(view));
	}

}
