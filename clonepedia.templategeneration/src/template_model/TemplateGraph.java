/**
 */
package template_model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Graph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.TemplateGraph#getTemplateMethodGroups <em>Template Method Groups</em>}</li>
 *   <li>{@link template_model.TemplateGraph#getElements <em>Elements</em>}</li>
 *   <li>{@link template_model.TemplateGraph#getTemplateFeatureGroups <em>Template Feature Groups</em>}</li>
 *   <li>{@link template_model.TemplateGraph#getLinks <em>Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getTemplateGraph()
 * @model
 * @generated
 */
public interface TemplateGraph extends EObject {
	/**
	 * Returns the value of the '<em><b>Template Method Groups</b></em>' containment reference list.
	 * The list contents are of type {@link template_model.TMG}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template Method Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template Method Groups</em>' containment reference list.
	 * @see template_model.Template_modelPackage#getTemplateGraph_TemplateMethodGroups()
	 * @model containment="true"
	 * @generated
	 */
	EList<TMG> getTemplateMethodGroups();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link template_model.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see template_model.Template_modelPackage#getTemplateGraph_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Element> getElements();

	/**
	 * Returns the value of the '<em><b>Template Feature Groups</b></em>' containment reference list.
	 * The list contents are of type {@link template_model.TFG}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template Feature Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template Feature Groups</em>' containment reference list.
	 * @see template_model.Template_modelPackage#getTemplateGraph_TemplateFeatureGroups()
	 * @model containment="true"
	 * @generated
	 */
	EList<TFG> getTemplateFeatureGroups();

	/**
	 * Returns the value of the '<em><b>Links</b></em>' containment reference list.
	 * The list contents are of type {@link template_model.Link}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links</em>' containment reference list.
	 * @see template_model.Template_modelPackage#getTemplateGraph_Links()
	 * @model containment="true"
	 * @generated
	 */
	EList<Link> getLinks();

} // TemplateGraph
