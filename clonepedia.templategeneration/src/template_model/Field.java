/**
 */
package template_model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.Field#getOwnerType <em>Owner Type</em>}</li>
 *   <li>{@link template_model.Field#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getField()
 * @model
 * @generated
 */
public interface Field extends Element {
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
	 * @see template_model.Template_modelPackage#getField_OwnerType()
	 * @model
	 * @generated
	 */
	Type getOwnerType();

	/**
	 * Sets the value of the '{@link template_model.Field#getOwnerType <em>Owner Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner Type</em>' reference.
	 * @see #getOwnerType()
	 * @generated
	 */
	void setOwnerType(Type value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see template_model.Template_modelPackage#getField_Type()
	 * @model required="true"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link template_model.Field#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // Field
