/**
 */
package template_model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import template_model.Call;
import template_model.Element;
import template_model.ExtendClass;
import template_model.ExtendInterface;
import template_model.Field;
import template_model.Implement;
import template_model.Interface;
import template_model.Method;
import template_model.TFG;
import template_model.TMG;
import template_model.TemplateGraph;
import template_model.Template_modelFactory;
import template_model.Template_modelPackage;
import template_model.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Template_modelFactoryImpl extends EFactoryImpl implements Template_modelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Template_modelFactory init() {
		try {
			Template_modelFactory theTemplate_modelFactory = (Template_modelFactory)EPackage.Registry.INSTANCE.getEFactory(Template_modelPackage.eNS_URI);
			if (theTemplate_modelFactory != null) {
				return theTemplate_modelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Template_modelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template_modelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Template_modelPackage.TEMPLATE_GRAPH: return createTemplateGraph();
			case Template_modelPackage.METHOD: return createMethod();
			case Template_modelPackage.ELEMENT: return createElement();
			case Template_modelPackage.CLASS: return createClass();
			case Template_modelPackage.TYPE: return createType();
			case Template_modelPackage.INTERFACE: return createInterface();
			case Template_modelPackage.TMG: return createTMG();
			case Template_modelPackage.TFG: return createTFG();
			case Template_modelPackage.CALL: return createCall();
			case Template_modelPackage.IMPLEMENT: return createImplement();
			case Template_modelPackage.EXTEND_CLASS: return createExtendClass();
			case Template_modelPackage.EXTEND_INTERFACE: return createExtendInterface();
			case Template_modelPackage.FIELD: return createField();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateGraph createTemplateGraph() {
		TemplateGraphImpl templateGraph = new TemplateGraphImpl();
		return templateGraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method createMethod() {
		MethodImpl method = new MethodImpl();
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element createElement() {
		ElementImpl element = new ElementImpl();
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public template_model.Class createClass() {
		ClassImpl class_ = new ClassImpl();
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type createType() {
		TypeImpl type = new TypeImpl();
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface createInterface() {
		InterfaceImpl interface_ = new InterfaceImpl();
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMG createTMG() {
		TMGImpl tmg = new TMGImpl();
		return tmg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFG createTFG() {
		TFGImpl tfg = new TFGImpl();
		return tfg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Call createCall() {
		CallImpl call = new CallImpl();
		return call;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Implement createImplement() {
		ImplementImpl implement = new ImplementImpl();
		return implement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendClass createExtendClass() {
		ExtendClassImpl extendClass = new ExtendClassImpl();
		return extendClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendInterface createExtendInterface() {
		ExtendInterfaceImpl extendInterface = new ExtendInterfaceImpl();
		return extendInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field createField() {
		FieldImpl field = new FieldImpl();
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template_modelPackage getTemplate_modelPackage() {
		return (Template_modelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Template_modelPackage getPackage() {
		return Template_modelPackage.eINSTANCE;
	}

} //Template_modelFactoryImpl
