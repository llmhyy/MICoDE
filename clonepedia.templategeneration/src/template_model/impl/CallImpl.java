/**
 */
package template_model.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import template_model.Call;
import template_model.Method;
import template_model.Template_modelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link template_model.impl.CallImpl#getCallerMethod <em>Caller Method</em>}</li>
 *   <li>{@link template_model.impl.CallImpl#getCalleeMethod <em>Callee Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallImpl extends LinkImpl implements Call {
	/**
	 * The cached value of the '{@link #getCallerMethod() <em>Caller Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallerMethod()
	 * @generated
	 * @ordered
	 */
	protected Method callerMethod;

	/**
	 * The cached value of the '{@link #getCalleeMethod() <em>Callee Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalleeMethod()
	 * @generated
	 * @ordered
	 */
	protected Method calleeMethod;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Template_modelPackage.Literals.CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getCallerMethod() {
		if (callerMethod != null && callerMethod.eIsProxy()) {
			InternalEObject oldCallerMethod = (InternalEObject)callerMethod;
			callerMethod = (Method)eResolveProxy(oldCallerMethod);
			if (callerMethod != oldCallerMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Template_modelPackage.CALL__CALLER_METHOD, oldCallerMethod, callerMethod));
			}
		}
		return callerMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetCallerMethod() {
		return callerMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallerMethod(Method newCallerMethod) {
		Method oldCallerMethod = callerMethod;
		callerMethod = newCallerMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.CALL__CALLER_METHOD, oldCallerMethod, callerMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getCalleeMethod() {
		if (calleeMethod != null && calleeMethod.eIsProxy()) {
			InternalEObject oldCalleeMethod = (InternalEObject)calleeMethod;
			calleeMethod = (Method)eResolveProxy(oldCalleeMethod);
			if (calleeMethod != oldCalleeMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Template_modelPackage.CALL__CALLEE_METHOD, oldCalleeMethod, calleeMethod));
			}
		}
		return calleeMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetCalleeMethod() {
		return calleeMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalleeMethod(Method newCalleeMethod) {
		Method oldCalleeMethod = calleeMethod;
		calleeMethod = newCalleeMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Template_modelPackage.CALL__CALLEE_METHOD, oldCalleeMethod, calleeMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Template_modelPackage.CALL__CALLER_METHOD:
				if (resolve) return getCallerMethod();
				return basicGetCallerMethod();
			case Template_modelPackage.CALL__CALLEE_METHOD:
				if (resolve) return getCalleeMethod();
				return basicGetCalleeMethod();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Template_modelPackage.CALL__CALLER_METHOD:
				setCallerMethod((Method)newValue);
				return;
			case Template_modelPackage.CALL__CALLEE_METHOD:
				setCalleeMethod((Method)newValue);
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
			case Template_modelPackage.CALL__CALLER_METHOD:
				setCallerMethod((Method)null);
				return;
			case Template_modelPackage.CALL__CALLEE_METHOD:
				setCalleeMethod((Method)null);
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
			case Template_modelPackage.CALL__CALLER_METHOD:
				return callerMethod != null;
			case Template_modelPackage.CALL__CALLEE_METHOD:
				return calleeMethod != null;
		}
		return super.eIsSet(featureID);
	}

} //CallImpl
