/**
 */
package template_model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Is Inner Class Of</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.IsInnerClassOf#getInnerClass <em>Inner Class</em>}</li>
 *   <li>{@link template_model.IsInnerClassOf#getOuterCLass <em>Outer CLass</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getIsInnerClassOf()
 * @model
 * @generated
 */
public interface IsInnerClassOf extends Link {
	/**
	 * Returns the value of the '<em><b>Inner Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inner Class</em>' reference.
	 * @see #setInnerClass(template_model.Class)
	 * @see template_model.Template_modelPackage#getIsInnerClassOf_InnerClass()
	 * @model required="true"
	 * @generated
	 */
	template_model.Class getInnerClass();

	/**
	 * Sets the value of the '{@link template_model.IsInnerClassOf#getInnerClass <em>Inner Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inner Class</em>' reference.
	 * @see #getInnerClass()
	 * @generated
	 */
	void setInnerClass(template_model.Class value);

	/**
	 * Returns the value of the '<em><b>Outer CLass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outer CLass</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outer CLass</em>' reference.
	 * @see #setOuterCLass(template_model.Class)
	 * @see template_model.Template_modelPackage#getIsInnerClassOf_OuterCLass()
	 * @model required="true"
	 * @generated
	 */
	template_model.Class getOuterCLass();

	/**
	 * Sets the value of the '{@link template_model.IsInnerClassOf#getOuterCLass <em>Outer CLass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outer CLass</em>' reference.
	 * @see #getOuterCLass()
	 * @generated
	 */
	void setOuterCLass(template_model.Class value);

} // IsInnerClassOf
