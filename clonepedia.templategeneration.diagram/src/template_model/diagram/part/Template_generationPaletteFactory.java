package template_model.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class Template_generationPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createTemplate_model1Group());
	}

	/**
	 * Creates "template_model" palette tool group
	 * @generated
	 */
	private PaletteContainer createTemplate_model1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				template_model.diagram.part.Messages.Template_model1Group_title);
		paletteContainer.setId("createTemplate_model1Group"); //$NON-NLS-1$
		paletteContainer.add(createMethod1CreationTool());
		paletteContainer.add(createClass2CreationTool());
		paletteContainer.add(createInterface3CreationTool());
		paletteContainer.add(createField4CreationTool());
		paletteContainer.add(createCall5CreationTool());
		paletteContainer.add(createImplement6CreationTool());
		paletteContainer.add(createExtendclass7CreationTool());
		paletteContainer.add(createExtendinterface8CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createMethod1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(4);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Method_2003);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Method_3002);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Method_3003);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Method_3005);
		NodeToolEntry entry = new NodeToolEntry(
				template_model.diagram.part.Messages.Method1CreationTool_title,
				template_model.diagram.part.Messages.Method1CreationTool_desc,
				types);
		entry.setId("createMethod1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(template_model.diagram.part.Template_generationDiagramEditorPlugin
				.findImageDescriptor("icons/tool/method.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createClass2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Class_2005);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Class_3004);
		NodeToolEntry entry = new NodeToolEntry(
				template_model.diagram.part.Messages.Class2CreationTool_title,
				template_model.diagram.part.Messages.Class2CreationTool_desc,
				types);
		entry.setId("createClass2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(template_model.diagram.part.Template_generationDiagramEditorPlugin
				.findImageDescriptor("icons/tool/class.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInterface3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				template_model.diagram.part.Messages.Interface3CreationTool_title,
				template_model.diagram.part.Messages.Interface3CreationTool_desc,
				Collections
						.singletonList(template_model.diagram.providers.Template_generationElementTypes.Interface_2001));
		entry.setId("createInterface3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(template_model.diagram.part.Template_generationDiagramEditorPlugin
				.findImageDescriptor("icons/tool/interface.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createField4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Field_2006);
		types.add(template_model.diagram.providers.Template_generationElementTypes.Field_3007);
		NodeToolEntry entry = new NodeToolEntry(
				template_model.diagram.part.Messages.Field4CreationTool_title,
				template_model.diagram.part.Messages.Field4CreationTool_desc,
				types);
		entry.setId("createField4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(template_model.diagram.part.Template_generationDiagramEditorPlugin
				.findImageDescriptor("icons/tool/field.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCall5CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				template_model.diagram.part.Messages.Call5CreationTool_title,
				template_model.diagram.part.Messages.Call5CreationTool_desc,
				Collections
						.singletonList(template_model.diagram.providers.Template_generationElementTypes.Call_4007));
		entry.setId("createCall5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(template_model.diagram.part.Template_generationDiagramEditorPlugin
				.findImageDescriptor("icons/tool/call.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createImplement6CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				template_model.diagram.part.Messages.Implement6CreationTool_title,
				template_model.diagram.part.Messages.Implement6CreationTool_desc,
				Collections
						.singletonList(template_model.diagram.providers.Template_generationElementTypes.Implement_4008));
		entry.setId("createImplement6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(template_model.diagram.part.Template_generationDiagramEditorPlugin
				.findImageDescriptor("icons/tool/implement.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createExtendclass7CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				template_model.diagram.part.Messages.Extendclass7CreationTool_title,
				template_model.diagram.part.Messages.Extendclass7CreationTool_desc,
				Collections
						.singletonList(template_model.diagram.providers.Template_generationElementTypes.ExtendClass_4010));
		entry.setId("createExtendclass7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(template_model.diagram.part.Template_generationDiagramEditorPlugin
				.findImageDescriptor("icons/tool/extend_class.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createExtendinterface8CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				template_model.diagram.part.Messages.Extendinterface8CreationTool_title,
				template_model.diagram.part.Messages.Extendinterface8CreationTool_desc,
				Collections
						.singletonList(template_model.diagram.providers.Template_generationElementTypes.Field_3006));
		entry.setId("createExtendinterface8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(template_model.diagram.part.Template_generationDiagramEditorPlugin
				.findImageDescriptor("icons/tool/extend_interface.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
