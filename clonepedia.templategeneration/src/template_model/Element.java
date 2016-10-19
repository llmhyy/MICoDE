/**
 */
package template_model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link template_model.Element#getName <em>Name</em>}</li>
 *   <li>{@link template_model.Element#getGraph <em>Graph</em>}</li>
 *   <li>{@link template_model.Element#getFullName <em>Full Name</em>}</li>
 *   <li>{@link template_model.Element#isIsComplete <em>Is Complete</em>}</li>
 *   <li>{@link template_model.Element#getSupportingElements <em>Supporting Elements</em>}</li>
 *   <li>{@link template_model.Element#getDescription <em>Description</em>}</li>
 *   <li>{@link template_model.Element#getSrcFolder <em>Src Folder</em>}</li>
 *   <li>{@link template_model.Element#getVariationType <em>Variation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see template_model.Template_modelPackage#getElement()
 * @model
 * @generated
 */
public interface Element extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see template_model.Template_modelPackage#getElement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link template_model.Element#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see template_model.Template_modelPackage#getElement_Graph()
	 * @model required="true"
	 * @generated
	 */
	TemplateGraph getGraph();

	/**
	 * Sets the value of the '{@link template_model.Element#getGraph <em>Graph</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graph</em>' reference.
	 * @see #getGraph()
	 * @generated
	 */
	void setGraph(TemplateGraph value);

	/**
	 * Returns the value of the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Full Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Full Name</em>' attribute.
	 * @see #setFullName(String)
	 * @see template_model.Template_modelPackage#getElement_FullName()
	 * @model required="true"
	 * @generated
	 */
	String getFullName();

	/**
	 * Sets the value of the '{@link template_model.Element#getFullName <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Full Name</em>' attribute.
	 * @see #getFullName()
	 * @generated
	 */
	void setFullName(String value);

	/**
	 * Returns the value of the '<em><b>Is Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Complete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Complete</em>' attribute.
	 * @see #setIsComplete(boolean)
	 * @see template_model.Template_modelPackage#getElement_IsComplete()
	 * @model
	 * @generated
	 */
	boolean isIsComplete();

	/**
	 * Sets the value of the '{@link template_model.Element#isIsComplete <em>Is Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Complete</em>' attribute.
	 * @see #isIsComplete()
	 * @generated
	 */
	void setIsComplete(boolean value);

	/**
	 * Returns the value of the '<em><b>Supporting Elements</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supporting Elements</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supporting Elements</em>' attribute list.
	 * @see template_model.Template_modelPackage#getElement_SupportingElements()
	 * @model
	 * @generated
	 */
	EList<String> getSupportingElements();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see template_model.Template_modelPackage#getElement_Description()
	 * @model required="true"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link template_model.Element#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Src Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Src Folder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Src Folder</em>' attribute.
	 * @see #setSrcFolder(String)
	 * @see template_model.Template_modelPackage#getElement_SrcFolder()
	 * @model required="true"
	 * @generated
	 */
	String getSrcFolder();

	/**
	 * Sets the value of the '{@link template_model.Element#getSrcFolder <em>Src Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Src Folder</em>' attribute.
	 * @see #getSrcFolder()
	 * @generated
	 */
	void setSrcFolder(String value);

	/**
	 * Returns the value of the '<em><b>Variation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variation Type</em>' attribute.
	 * @see #setVariationType(String)
	 * @see template_model.Template_modelPackage#getElement_VariationType()
	 * @model required="true"
	 * @generated
	 */
	String getVariationType();

	/**
	 * Sets the value of the '{@link template_model.Element#getVariationType <em>Variation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variation Type</em>' attribute.
	 * @see #getVariationType()
	 * @generated
	 */
	void setVariationType(String value);

} // Element
