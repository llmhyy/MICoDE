/**
 */
package template_model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.Implement#getClass_ <em>Class</em>}</li>
 *   <li>{@link template_model.Implement#getInterface <em>Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getImplement()
 * @model
 * @generated
 */
public interface Implement extends Link {
	/**
	 * Returns the value of the '<em><b>Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' reference.
	 * @see #setClass(template_model.Class)
	 * @see template_model.Template_modelPackage#getImplement_Class()
	 * @model required="true"
	 * @generated
	 */
	template_model.Class getClass_();

	/**
	 * Sets the value of the '{@link template_model.Implement#getClass_ <em>Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' reference.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(template_model.Class value);

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' reference list.
	 * The list contents are of type {@link template_model.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' reference list.
	 * @see template_model.Template_modelPackage#getImplement_Interface()
	 * @model required="true"
	 * @generated
	 */
	EList<Interface> getInterface();

} // Implement
