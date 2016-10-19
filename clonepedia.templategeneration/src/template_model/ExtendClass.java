/**
 */
package template_model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extend Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.ExtendClass#getSubClass <em>Sub Class</em>}</li>
 *   <li>{@link template_model.ExtendClass#getSuperClass <em>Super Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getExtendClass()
 * @model
 * @generated
 */
public interface ExtendClass extends Link {
	/**
	 * Returns the value of the '<em><b>Sub Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Class</em>' reference.
	 * @see #setSubClass(template_model.Class)
	 * @see template_model.Template_modelPackage#getExtendClass_SubClass()
	 * @model required="true"
	 * @generated
	 */
	template_model.Class getSubClass();

	/**
	 * Sets the value of the '{@link template_model.ExtendClass#getSubClass <em>Sub Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Class</em>' reference.
	 * @see #getSubClass()
	 * @generated
	 */
	void setSubClass(template_model.Class value);

	/**
	 * Returns the value of the '<em><b>Super Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Class</em>' reference.
	 * @see #setSuperClass(template_model.Class)
	 * @see template_model.Template_modelPackage#getExtendClass_SuperClass()
	 * @model required="true"
	 * @generated
	 */
	template_model.Class getSuperClass();

	/**
	 * Sets the value of the '{@link template_model.ExtendClass#getSuperClass <em>Super Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Class</em>' reference.
	 * @see #getSuperClass()
	 * @generated
	 */
	void setSuperClass(template_model.Class value);
} // ExtendClass
