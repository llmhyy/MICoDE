/**
 */
package template_model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import template_model.ExtendInterface;
import template_model.Interface;
import template_model.Template_modelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extend Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link template_model.impl.ExtendInterfaceImpl#getSubInterface <em>Sub Interface</em>}</li>
 *   <li>{@link template_model.impl.ExtendInterfaceImpl#getSuperInterface <em>Super Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendInterfaceImpl extends LinkImpl implements ExtendInterface {
	/**
	 * The cached value of the '{@link #getSubInterface() <em>Sub Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubInterface()
	 * @generated
	 * @ordered
	 */
	protected Interface subInterface;

	/**
	 * The cached value of the '{@link #getSuperInterface() <em>Super Interface</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperInterface()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> superInterface;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendInterfaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Template_modelPackage.Literals.EXTEND_INTERFACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface getSubInterface() {
		if (subInterface != null && subInterface.eIsProxy()) {
			InternalEObject oldSubInterface = (InternalEObject)subInterface;
			subInterface = (Interface)eResolveProxy(oldSubInterface);
			if (subInterface != oldSubInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Template_modelPackage.EXTEND_INTERFACE__SUB_INTERFACE, oldSubInterface, subInterface));
			}
		}
		return subInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface basicGetSubInterface() {
		return subInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubInterface(Interface newSubInterface) {
		Interface oldSubInterface = subInterface;
		subInterface = newSubInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.EXTEND_INTERFACE__SUB_INTERFACE, oldSubInterface, subInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface> getSuperInterface() {
		if (superInterface == null) {
			superInterface = new EObjectResolvingEList<Interface>(Interface.class, this, Template_modelPackage.EXTEND_INTERFACE__SUPER_INTERFACE);
		}
		return superInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Template_modelPackage.EXTEND_INTERFACE__SUB_INTERFACE:
				if (resolve) return getSubInterface();
				return basicGetSubInterface();
			case Template_modelPackage.EXTEND_INTERFACE__SUPER_INTERFACE:
				return getSuperInterface();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Template_modelPackage.EXTEND_INTERFACE__SUB_INTERFACE:
				setSubInterface((Interface)newValue);
				return;
			case Template_modelPackage.EXTEND_INTERFACE__SUPER_INTERFACE:
				getSuperInterface().clear();
				getSuperInterface().addAll((Collection<? extends Interface>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Template_modelPackage.EXTEND_INTERFACE__SUB_INTERFACE:
				setSubInterface((Interface)null);
				return;
			case Template_modelPackage.EXTEND_INTERFACE__SUPER_INTERFACE:
				getSuperInterface().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Template_modelPackage.EXTEND_INTERFACE__SUB_INTERFACE:
				return subInterface != null;
			case Template_modelPackage.EXTEND_INTERFACE__SUPER_INTERFACE:
				return superInterface != null && !superInterface.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@Override
	public String toString() {
		return "extendInterface " + this.subInterface.getFullName() + ":" + this.superInterface.get(0).getFullName();
	}
	
	@Override
	public int hashCode(){
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof ExtendInterface){
			ExtendInterface extendInterface = (ExtendInterface)obj;
			return this.toString().equals(extendInterface.toString());
		}
		
		return false;
	}

} //ExtendInterfaceImpl
