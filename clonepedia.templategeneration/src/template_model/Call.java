/**
 */
package template_model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.Call#getCallerMethod <em>Caller Method</em>}</li>
 *   <li>{@link template_model.Call#getCalleeMethod <em>Callee Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getCall()
 * @model
 * @generated
 */
public interface Call extends Link {
	/**
	 * Returns the value of the '<em><b>Caller Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caller Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caller Method</em>' reference.
	 * @see #setCallerMethod(Method)
	 * @see template_model.Template_modelPackage#getCall_CallerMethod()
	 * @model required="true"
	 * @generated
	 */
	Method getCallerMethod();

	/**
	 * Sets the value of the '{@link template_model.Call#getCallerMethod <em>Caller Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller Method</em>' reference.
	 * @see #getCallerMethod()
	 * @generated
	 */
	void setCallerMethod(Method value);

	/**
	 * Returns the value of the '<em><b>Callee Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callee Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callee Method</em>' reference.
	 * @see #setCalleeMethod(Method)
	 * @see template_model.Template_modelPackage#getCall_CalleeMethod()
	 * @model required="true"
	 * @generated
	 */
	Method getCalleeMethod();

	/**
	 * Sets the value of the '{@link template_model.Call#getCalleeMethod <em>Callee Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callee Method</em>' reference.
	 * @see #getCalleeMethod()
	 * @generated
	 */
	void setCalleeMethod(Method value);

} // Call
