package clonepedia.templategeneration.diagram.view.model;

import java.util.ArrayList;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import clonepedia.model.template.TemplateMethodGroup;

public class TMGWrapper implements IPropertySource{
	private ArrayList<MethodWrapper> methodWrapperList = new ArrayList<MethodWrapper>();
	private TemplateMethodGroup tmg;

	private final static String NAME = "name";
	private final static String CALLEE_TMG = "caller tmg";
	private final static String CALLER_TMG = "callee tmg";
	
	public TMGWrapper(TemplateMethodGroup tmg) {
		super();
		this.tmg = tmg;
	}

	public ArrayList<MethodWrapper> getMethodWrapperList() {
		return methodWrapperList;
	}

	public void setMethodWrapperList(ArrayList<MethodWrapper> methodWrapperList) {
		this.methodWrapperList = methodWrapperList;
	}

	public TemplateMethodGroup getTmg() {
		return tmg;
	}

	public void setTmg(TemplateMethodGroup tmg) {
		this.tmg = tmg;
	}

	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ArrayList<IPropertyDescriptor> descriptorList = new ArrayList<IPropertyDescriptor>();
		
		PropertyDescriptor returnTypeDescriptor = new PropertyDescriptor(NAME, "Name");
		PropertyDescriptor methodNameDescriptor = new PropertyDescriptor(CALLEE_TMG, "Callee TMG");
		PropertyDescriptor declaringTypeDescriptor = new PropertyDescriptor(CALLER_TMG, "Caller TMG");

		descriptorList.add(returnTypeDescriptor);
		descriptorList.add(methodNameDescriptor);
		descriptorList.add(declaringTypeDescriptor);
		
		return descriptorList.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(id.equals(NAME)){
			return this.tmg.getName();
		}
		else if(id.equals(CALLEE_TMG)){
			StringBuffer buffer = new StringBuffer();
			for(TemplateMethodGroup group: tmg.getCalleeGroup()){
				buffer.append(group.getName() + ", ");
			}
			String calleeString = buffer.toString();
			if(calleeString.length() != 0){
				calleeString = calleeString.substring(0, calleeString.length()-2);
			}
			return calleeString;
		}
		else if(id.equals(CALLER_TMG)){
			StringBuffer buffer = new StringBuffer();
			for(TemplateMethodGroup group: tmg.getCallerGroup()){
				buffer.append(group.getName() + ", ");
			}
			String callerString = buffer.toString();
			if(callerString.length() != 0){
				callerString = callerString.substring(0, callerString.length()-2);
			}
			return callerString;
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
