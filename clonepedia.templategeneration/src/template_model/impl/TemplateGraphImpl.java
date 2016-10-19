/**
 */
package template_model.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import template_model.Element;
import template_model.Link;
import template_model.TFG;
import template_model.TMG;
import template_model.TemplateGraph;
import template_model.Template_modelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Graph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link template_model.impl.TemplateGraphImpl#getTemplateMethodGroups <em>Template Method Groups</em>}</li>
 *   <li>{@link template_model.impl.TemplateGraphImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link template_model.impl.TemplateGraphImpl#getTemplateFeatureGroups <em>Template Feature Groups</em>}</li>
 *   <li>{@link template_model.impl.TemplateGraphImpl#getLinks <em>Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TemplateGraphImpl extends MinimalEObjectImpl.Container implements TemplateGraph {
	/**
	 * The cached value of the '{@link #getTemplateMethodGroups() <em>Template Method Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateMethodGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<TMG> templateMethodGroups;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Element> elements;

	/**
	 * The cached value of the '{@link #getTemplateFeatureGroups() <em>Template Feature Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateFeatureGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<TFG> templateFeatureGroups;

	/**
	 * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> links;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateGraphImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Template_modelPackage.Literals.TEMPLATE_GRAPH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TMG> getTemplateMethodGroups() {
		if (templateMethodGroups == null) {
			templateMethodGroups = new EObjectContainmentEList<TMG>(TMG.class, this, Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_METHOD_GROUPS);
		}
		return templateMethodGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Element> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentEList<Element>(Element.class, this, Template_modelPackage.TEMPLATE_GRAPH__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TFG> getTemplateFeatureGroups() {
		if (templateFeatureGroups == null) {
			templateFeatureGroups = new EObjectContainmentEList<TFG>(TFG.class, this, Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_FEATURE_GROUPS);
		}
		return templateFeatureGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getLinks() {
		if (links == null) {
			links = new EObjectContainmentEList<Link>(Link.class, this, Template_modelPackage.TEMPLATE_GRAPH__LINKS);
		}
		return links;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_METHOD_GROUPS:
				return ((InternalEList<?>)getTemplateMethodGroups()).basicRemove(otherEnd, msgs);
			case Template_modelPackage.TEMPLATE_GRAPH__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_FEATURE_GROUPS:
				return ((InternalEList<?>)getTemplateFeatureGroups()).basicRemove(otherEnd, msgs);
			case Template_modelPackage.TEMPLATE_GRAPH__LINKS:
				return ((InternalEList<?>)getLinks()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_METHOD_GROUPS:
				return getTemplateMethodGroups();
			case Template_modelPackage.TEMPLATE_GRAPH__ELEMENTS:
				return getElements();
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_FEATURE_GROUPS:
				return getTemplateFeatureGroups();
			case Template_modelPackage.TEMPLATE_GRAPH__LINKS:
				return getLinks();
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
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_METHOD_GROUPS:
				getTemplateMethodGroups().clear();
				getTemplateMethodGroups().addAll((Collection<? extends TMG>)newValue);
				return;
			case Template_modelPackage.TEMPLATE_GRAPH__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends Element>)newValue);
				return;
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_FEATURE_GROUPS:
				getTemplateFeatureGroups().clear();
				getTemplateFeatureGroups().addAll((Collection<? extends TFG>)newValue);
				return;
			case Template_modelPackage.TEMPLATE_GRAPH__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection<? extends Link>)newValue);
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
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_METHOD_GROUPS:
				getTemplateMethodGroups().clear();
				return;
			case Template_modelPackage.TEMPLATE_GRAPH__ELEMENTS:
				getElements().clear();
				return;
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_FEATURE_GROUPS:
				getTemplateFeatureGroups().clear();
				return;
			case Template_modelPackage.TEMPLATE_GRAPH__LINKS:
				getLinks().clear();
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
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_METHOD_GROUPS:
				return templateMethodGroups != null && !templateMethodGroups.isEmpty();
			case Template_modelPackage.TEMPLATE_GRAPH__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case Template_modelPackage.TEMPLATE_GRAPH__TEMPLATE_FEATURE_GROUPS:
				return templateFeatureGroups != null && !templateFeatureGroups.isEmpty();
			case Template_modelPackage.TEMPLATE_GRAPH__LINKS:
				return links != null && !links.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TemplateGraphImpl
