/**
 */
package template_model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extend Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.ExtendInterface#getSubInterface <em>Sub Interface</em>}</li>
 *   <li>{@link template_model.ExtendInterface#getSuperInterface <em>Super Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getExtendInterface()
 * @model
 * @generated
 */
public interface ExtendInterface extends Link {
	/**
	 * Returns the value of the '<em><b>Sub Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Interface</em>' reference.
	 * @see #setSubInterface(Interface)
	 * @see template_model.Template_modelPackage#getExtendInterface_SubInterface()
	 * @model required="true"
	 * @generated
	 */
	Interface getSubInterface();

	/**
	 * Sets the value of the '{@link template_model.ExtendInterface#getSubInterface <em>Sub Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Interface</em>' reference.
	 * @see #getSubInterface()
	 * @generated
	 */
	void setSubInterface(Interface value);

	/**
	 * Returns the value of the '<em><b>Super Interface</b></em>' reference list.
	 * The list contents are of type {@link template_model.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Interface</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Interface</em>' reference list.
	 * @see template_model.Template_modelPackage#getExtendInterface_SuperInterface()
	 * @model required="true"
	 * @generated
	 */
	EList<Interface> getSuperInterface();

} // ExtendInterface
