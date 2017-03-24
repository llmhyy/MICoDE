package clonepedia.codegeneration.diagram.bean;

import java.util.ArrayList;
import java.util.List;

public class TemplateInstance {
	private List<TypeWrapper> topTypeWrapperList;

	public TemplateInstance(){
		topTypeWrapperList = new ArrayList<TypeWrapper>();
	}
	
	public TemplateInstance(List<TypeWrapper> tws){
		this.topTypeWrapperList = tws;
	}
	
	public List<TypeWrapper> getTopTypeWrapperList() {
		return topTypeWrapperList;
	}

	public void setTopTypeWrapperList(List<TypeWrapper> topTypeWrapperList) {
		this.topTypeWrapperList = topTypeWrapperList;
	}
	
	public void addTopType(TypeWrapper type){
		this.topTypeWrapperList.add(type);
	}
}
