/**
 */
package template_model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.Method#getCalleeMethods <em>Callee Methods</em>}</li>
 *   <li>{@link template_model.Method#getOwnerType <em>Owner Type</em>}</li>
 *   <li>{@link template_model.Method#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link template_model.Method#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getMethod()
 * @model
 * @generated
 */
public interface Method extends Element {
	/**
	 * Returns the value of the '<em><b>Callee Methods</b></em>' reference list.
	 * The list contents are of type {@link template_model.Method}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callee Methods</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callee Methods</em>' reference list.
	 * @see template_model.Template_modelPackage#getMethod_CalleeMethods()
	 * @model
	 * @generated
	 */
	EList<Method> getCalleeMethods();

	/**
	 * Returns the value of the '<em><b>Owner Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner Type</em>' reference.
	 * @see #setOwnerType(Type)
	 * @see template_model.Template_modelPackage#getMethod_OwnerType()
	 * @model required="true"
	 * @generated
	 */
	Type getOwnerType();

	/**
	 * Sets the value of the '{@link template_model.Method#getOwnerType <em>Owner Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner Type</em>' reference.
	 * @see #getOwnerType()
	 * @generated
	 */
	void setOwnerType(Type value);

	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' attribute.
	 * @see #setReturnType(String)
	 * @see template_model.Template_modelPackage#getMethod_ReturnType()
	 * @model
	 * @generated
	 */
	String getReturnType();

	/**
	 * Sets the value of the '{@link template_model.Method#getReturnType <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' attribute.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(String value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' attribute list.
	 * @see template_model.Template_modelPackage#getMethod_Parameters()
	 * @model
	 * @generated
	 */
	EList<String> getParameters();

} // Method
