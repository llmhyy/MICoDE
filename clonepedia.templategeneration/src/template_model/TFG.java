/**
 */
package template_model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TFG</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.TFG#getTMGs <em>TM Gs</em>}</li>
 *   <li>{@link template_model.TFG#getGraph <em>Graph</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getTFG()
 * @model
 * @generated
 */
public interface TFG extends EObject {
	/**
	 * Returns the value of the '<em><b>TM Gs</b></em>' containment reference list.
	 * The list contents are of type {@link template_model.TMG}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TM Gs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TM Gs</em>' containment reference list.
	 * @see template_model.Template_modelPackage#getTFG_TMGs()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<TMG> getTMGs();

	/**
	 * Returns the value of the '<em><b>Graph</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graph</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graph</em>' reference.
	 * @see #setGraph(TemplateGraph)
	 * @see template_model.Template_modelPackage#getTFG_Graph()
	 * @model required="true"
	 * @generated
	 */
	TemplateGraph getGraph();

	/**
	 * Sets the value of the '{@link template_model.TFG#getGraph <em>Graph</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graph</em>' reference.
	 * @see #getGraph()
	 * @generated
	 */
	void setGraph(TemplateGraph value);

} // TFG
