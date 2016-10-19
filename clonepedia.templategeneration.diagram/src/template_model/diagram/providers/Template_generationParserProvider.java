package template_model.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Template_generationParserProvider extends AbstractProvider
		implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser fieldName_5017Parser;

	/**
	 * @generated
	 */
	private IParser getFieldName_5017Parser() {
		if (fieldName_5017Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			fieldName_5017Parser = parser;
		}
		return fieldName_5017Parser;
	}

	/**
	 * @generated
	 */
	private IParser methodName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getMethodName_5003Parser() {
		if (methodName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			methodName_5003Parser = parser;
		}
		return methodName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser interfaceName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getInterfaceName_5001Parser() {
		if (interfaceName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			interfaceName_5001Parser = parser;
		}
		return interfaceName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser interfaceFullName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getInterfaceFullName_5006Parser() {
		if (interfaceFullName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_FullName() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			interfaceFullName_5006Parser = parser;
		}
		return interfaceFullName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser className_5004Parser;

	/**
	 * @generated
	 */
	private IParser getClassName_5004Parser() {
		if (className_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			className_5004Parser = parser;
		}
		return className_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser classFullName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getClassFullName_5007Parser() {
		if (classFullName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_FullName() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			classFullName_5007Parser = parser;
		}
		return classFullName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser methodName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getMethodName_5008Parser() {
		if (methodName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			methodName_5008Parser = parser;
		}
		return methodName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser methodName_5009Parser;

	/**
	 * @generated
	 */
	private IParser getMethodName_5009Parser() {
		if (methodName_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			methodName_5009Parser = parser;
		}
		return methodName_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser fieldName_5015Parser;

	/**
	 * @generated
	 */
	private IParser getFieldName_5015Parser() {
		if (fieldName_5015Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			fieldName_5015Parser = parser;
		}
		return fieldName_5015Parser;
	}

	/**
	 * @generated
	 */
	private IParser className_5010Parser;

	/**
	 * @generated
	 */
	private IParser getClassName_5010Parser() {
		if (className_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			className_5010Parser = parser;
		}
		return className_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser methodName_5011Parser;

	/**
	 * @generated
	 */
	private IParser getMethodName_5011Parser() {
		if (methodName_5011Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			methodName_5011Parser = parser;
		}
		return methodName_5011Parser;
	}

	/**
	 * @generated
	 */
	private IParser fieldName_5016Parser;

	/**
	 * @generated
	 */
	private IParser getFieldName_5016Parser() {
		if (fieldName_5016Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getElement_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			fieldName_5016Parser = parser;
		}
		return fieldName_5016Parser;
	}

	/**
	 * @generated
	 */
	private IParser extendInterfaceName_6001Parser;

	/**
	 * @generated
	 */
	private IParser getExtendInterfaceName_6001Parser() {
		if (extendInterfaceName_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getLink_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			extendInterfaceName_6001Parser = parser;
		}
		return extendInterfaceName_6001Parser;
	}

	/**
	 * @generated
	 */
	private IParser callName_6002Parser;

	/**
	 * @generated
	 */
	private IParser getCallName_6002Parser() {
		if (callName_6002Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getLink_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			callName_6002Parser = parser;
		}
		return callName_6002Parser;
	}

	/**
	 * @generated
	 */
	private IParser implementName_6003Parser;

	/**
	 * @generated
	 */
	private IParser getImplementName_6003Parser() {
		if (implementName_6003Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getLink_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			implementName_6003Parser = parser;
		}
		return implementName_6003Parser;
	}

	/**
	 * @generated
	 */
	private IParser extendClassName_6005Parser;

	/**
	 * @generated
	 */
	private IParser getExtendClassName_6005Parser() {
		if (extendClassName_6005Parser == null) {
			EAttribute[] features = new EAttribute[] { template_model.Template_modelPackage.eINSTANCE
					.getLink_Name() };
			template_model.diagram.parsers.MessageFormatParser parser = new template_model.diagram.parsers.MessageFormatParser(
					features);
			extendClassName_6005Parser = parser;
		}
		return extendClassName_6005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case template_model.diagram.edit.parts.FieldName3EditPart.VISUAL_ID:
			return getFieldName_5017Parser();
		case template_model.diagram.edit.parts.MethodNameEditPart.VISUAL_ID:
			return getMethodName_5003Parser();
		case template_model.diagram.edit.parts.InterfaceNameEditPart.VISUAL_ID:
			return getInterfaceName_5001Parser();
		case template_model.diagram.edit.parts.InterfaceFullNameEditPart.VISUAL_ID:
			return getInterfaceFullName_5006Parser();
		case template_model.diagram.edit.parts.ClassNameEditPart.VISUAL_ID:
			return getClassName_5004Parser();
		case template_model.diagram.edit.parts.ClassFullNameEditPart.VISUAL_ID:
			return getClassFullName_5007Parser();
		case template_model.diagram.edit.parts.MethodName3EditPart.VISUAL_ID:
			return getMethodName_5008Parser();
		case template_model.diagram.edit.parts.MethodName4EditPart.VISUAL_ID:
			return getMethodName_5009Parser();
		case template_model.diagram.edit.parts.FieldNameEditPart.VISUAL_ID:
			return getFieldName_5015Parser();
		case template_model.diagram.edit.parts.ClassName2EditPart.VISUAL_ID:
			return getClassName_5010Parser();
		case template_model.diagram.edit.parts.MethodName5EditPart.VISUAL_ID:
			return getMethodName_5011Parser();
		case template_model.diagram.edit.parts.FieldName2EditPart.VISUAL_ID:
			return getFieldName_5016Parser();
		case template_model.diagram.edit.parts.ExtendInterfaceNameEditPart.VISUAL_ID:
			return getExtendInterfaceName_6001Parser();
		case template_model.diagram.edit.parts.CallNameEditPart.VISUAL_ID:
			return getCallName_6002Parser();
		case template_model.diagram.edit.parts.ImplementNameEditPart.VISUAL_ID:
			return getImplementName_6003Parser();
		case template_model.diagram.edit.parts.ExtendClassNameEditPart.VISUAL_ID:
			return getExtendClassName_6005Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(template_model.diagram.part.Template_generationVisualIDRegistry
					.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(template_model.diagram.part.Template_generationVisualIDRegistry
					.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (template_model.diagram.providers.Template_generationElementTypes
					.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}