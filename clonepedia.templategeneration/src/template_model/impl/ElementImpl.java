/**
 */
package template_model.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import template_model.Element;
import template_model.TemplateGraph;
import template_model.Template_modelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link template_model.impl.ElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link template_model.impl.ElementImpl#getGraph <em>Graph</em>}</li>
 *   <li>{@link template_model.impl.ElementImpl#getFullName <em>Full Name</em>}</li>
 *   <li>{@link template_model.impl.ElementImpl#isIsComplete <em>Is Complete</em>}</li>
 *   <li>{@link template_model.impl.ElementImpl#getSupportingElements <em>Supporting Elements</em>}</li>
 *   <li>{@link template_model.impl.ElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link template_model.impl.ElementImpl#getSrcFolder <em>Src Folder</em>}</li>
 *   <li>{@link template_model.impl.ElementImpl#getVariationType <em>Variation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementImpl extends MinimalEObjectImpl.Container implements Element {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGraph() <em>Graph</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGraph()
	 * @generated
	 * @ordered
	 */
	protected TemplateGraph graph;

	/**
	 * The default value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullName()
	 * @generated
	 * @ordered
	 */
	protected static final String FULL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullName()
	 * @generated
	 * @ordered
	 */
	protected String fullName = FULL_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsComplete() <em>Is Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsComplete()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_COMPLETE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsComplete() <em>Is Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsComplete()
	 * @generated
	 * @ordered
	 */
	protected boolean isComplete = IS_COMPLETE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSupportingElements() <em>Supporting Elements</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportingElements()
	 * @generated
	 * @ordered
	 */
	protected EList<String> supportingElements;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSrcFolder() <em>Src Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrcFolder()
	 * @generated
	 * @ordered
	 */
	protected static final String SRC_FOLDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSrcFolder() <em>Src Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrcFolder()
	 * @generated
	 * @ordered
	 */
	protected String srcFolder = SRC_FOLDER_EDEFAULT;

	/**
	 * The default value of the '{@link #getVariationType() <em>Variation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariationType()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIATION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariationType() <em>Variation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariationType()
	 * @generated
	 * @ordered
	 */
	protected String variationType = VARIATION_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Template_modelPackage.Literals.ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateGraph getGraph() {
		if (graph != null && graph.eIsProxy()) {
			InternalEObject oldGraph = (InternalEObject)graph;
			graph = (TemplateGraph)eResolveProxy(oldGraph);
			if (graph != oldGraph) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Template_modelPackage.ELEMENT__GRAPH, oldGraph, graph));
			}
		}
		return graph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateGraph basicGetGraph() {
		return graph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGraph(TemplateGraph newGraph) {
		TemplateGraph oldGraph = graph;
		graph = newGraph;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.ELEMENT__GRAPH, oldGraph, graph));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFullName(String newFullName) {
		String oldFullName = fullName;
		fullName = newFullName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.ELEMENT__FULL_NAME, oldFullName, fullName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsComplete() {
		return isComplete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsComplete(boolean newIsComplete) {
		boolean oldIsComplete = isComplete;
		isComplete = newIsComplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.ELEMENT__IS_COMPLETE, oldIsComplete, isComplete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSupportingElements() {
		if (supportingElements == null) {
			supportingElements = new EDataTypeUniqueEList<String>(String.class, this, Template_modelPackage.ELEMENT__SUPPORTING_ELEMENTS);
		}
		return supportingElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.ELEMENT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSrcFolder() {
		return srcFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSrcFolder(String newSrcFolder) {
		String oldSrcFolder = srcFolder;
		srcFolder = newSrcFolder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.ELEMENT__SRC_FOLDER, oldSrcFolder, srcFolder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVariationType() {
		return variationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariationType(String newVariationType) {
		String oldVariationType = variationType;
		variationType = newVariationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.ELEMENT__VARIATION_TYPE, oldVariationType, variationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Template_modelPackage.ELEMENT__NAME:
				return getName();
			case Template_modelPackage.ELEMENT__GRAPH:
				if (resolve) return getGraph();
				return basicGetGraph();
			case Template_modelPackage.ELEMENT__FULL_NAME:
				return getFullName();
			case Template_modelPackage.ELEMENT__IS_COMPLETE:
				return isIsComplete();
			case Template_modelPackage.ELEMENT__SUPPORTING_ELEMENTS:
				return getSupportingElements();
			case Template_modelPackage.ELEMENT__DESCRIPTION:
				return getDescription();
			case Template_modelPackage.ELEMENT__SRC_FOLDER:
				return getSrcFolder();
			case Template_modelPackage.ELEMENT__VARIATION_TYPE:
				return getVariationType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Template_modelPackage.ELEMENT__NAME:
				setName((String)newValue);
				return;
			case Template_modelPackage.ELEMENT__GRAPH:
				setGraph((TemplateGraph)newValue);
				return;
			case Template_modelPackage.ELEMENT__FULL_NAME:
				setFullName((String)newValue);
				return;
			case Template_modelPackage.ELEMENT__IS_COMPLETE:
				setIsComplete((Boolean)newValue);
				return;
			case Template_modelPackage.ELEMENT__SUPPORTING_ELEMENTS:
				getSupportingElements().clear();
				getSupportingElements().addAll((Collection<? extends String>)newValue);
				return;
			case Template_modelPackage.ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Template_modelPackage.ELEMENT__SRC_FOLDER:
				setSrcFolder((String)newValue);
				return;
			case Template_modelPackage.ELEMENT__VARIATION_TYPE:
				setVariationType((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Template_modelPackage.ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Template_modelPackage.ELEMENT__GRAPH:
				setGraph((TemplateGraph)null);
				return;
			case Template_modelPackage.ELEMENT__FULL_NAME:
				setFullName(FULL_NAME_EDEFAULT);
				return;
			case Template_modelPackage.ELEMENT__IS_COMPLETE:
				setIsComplete(IS_COMPLETE_EDEFAULT);
				return;
			case Template_modelPackage.ELEMENT__SUPPORTING_ELEMENTS:
				getSupportingElements().clear();
				return;
			case Template_modelPackage.ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Template_modelPackage.ELEMENT__SRC_FOLDER:
				setSrcFolder(SRC_FOLDER_EDEFAULT);
				return;
			case Template_modelPackage.ELEMENT__VARIATION_TYPE:
				setVariationType(VARIATION_TYPE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Template_modelPackage.ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Template_modelPackage.ELEMENT__GRAPH:
				return graph != null;
			case Template_modelPackage.ELEMENT__FULL_NAME:
				return FULL_NAME_EDEFAULT == null ? fullName != null : !FULL_NAME_EDEFAULT.equals(fullName);
			case Template_modelPackage.ELEMENT__IS_COMPLETE:
				return isComplete != IS_COMPLETE_EDEFAULT;
			case Template_modelPackage.ELEMENT__SUPPORTING_ELEMENTS:
				return supportingElements != null && !supportingElements.isEmpty();
			case Template_modelPackage.ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Template_modelPackage.ELEMENT__SRC_FOLDER:
				return SRC_FOLDER_EDEFAULT == null ? srcFolder != null : !SRC_FOLDER_EDEFAULT.equals(srcFolder);
			case Template_modelPackage.ELEMENT__VARIATION_TYPE:
				return VARIATION_TYPE_EDEFAULT == null ? variationType != null : !VARIATION_TYPE_EDEFAULT.equals(variationType);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", fullName: ");
		result.append(fullName);
		result.append(", isComplete: ");
		result.append(isComplete);
		result.append(", supportingElements: ");
		result.append(supportingElements);
		result.append(", description: ");
		result.append(description);
		result.append(", srcFolder: ");
		result.append(srcFolder);
		result.append(", variationType: ");
		result.append(variationType);
		result.append(')');
		return result.toString();
	}

} //ElementImpl
