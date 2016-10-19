package clonepedia.templategeneration.diagram.view.model;

import java.util.ArrayList;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import clonepedia.model.ontology.Method;
import clonepedia.model.ontology.Variable;

public class MethodWrapper implements IPropertySource{
	private clonepedia.model.ontology.Method method;
	
	private static final String RETURN_TYPE = "return_type";
	private static final String METHOD_NAME = "method_name";
	private static final String DECLARING_TYPE = "delcaring type";
	private static final String	PARAMETERS = "parameters";
	private static final String CALEE_METHODS = "calee methods";

	public MethodWrapper(Method method) {
		super();
		this.method = method;
	}

	public clonepedia.model.ontology.Method getMethod() {
		return method;
	}

	public void setMethod(clonepedia.model.ontology.Method method) {
		this.method = method;
	}

	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ArrayList<IPropertyDescriptor> descriptorList = new ArrayList<IPropertyDescriptor>();
		
		PropertyDescriptor returnTypeDescriptor = new PropertyDescriptor(RETURN_TYPE, "Return Type");
		PropertyDescriptor methodNameDescriptor = new PropertyDescriptor(METHOD_NAME, "Method Name");
		PropertyDescriptor declaringTypeDescriptor = new PropertyDescriptor(DECLARING_TYPE, "Declaring Type");
		PropertyDescriptor parametersDescriptor = new PropertyDescriptor(PARAMETERS, "Parameters");
		PropertyDescriptor calleeMethodDescriptor = new PropertyDescriptor(CALEE_METHODS, "Calee Methods");
		
		descriptorList.add(returnTypeDescriptor);
		descriptorList.add(methodNameDescriptor);
		descriptorList.add(declaringTypeDescriptor);
		descriptorList.add(parametersDescriptor);
		descriptorList.add(calleeMethodDescriptor);
		
		return descriptorList.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(id.equals(RETURN_TYPE)){
			return method.getReturnType().getFullName();
		}
		else if(id.equals(METHOD_NAME)){
			return method.getMethodName();
		}
		else if(id.equals(DECLARING_TYPE)){
			return method.getOwner().getFullName();
		}
		else if(id.equals(PARAMETERS)){
			StringBuffer buffer = new StringBuffer();
			for(Variable v: method.getParameters()){
				buffer.append(v.toString() + ", ");
			}
			String paramString = buffer.toString();
			if(paramString.length() != 0){
				paramString = paramString.substring(0, paramString.length()-2);
			}
			return paramString;
		}
		else if(id.equals(CALEE_METHODS)){
			StringBuffer buffer = new StringBuffer();
			for(clonepedia.model.ontology.Method m: method.getCalleeMethod()){
				buffer.append(m.getFullName() + ", ");
			}
			String methodString = buffer.toString();
			if(methodString.length() != 0){
				methodString = methodString.substring(0, methodString.length()-2);
			}
			return methodString;
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		
	}

}
