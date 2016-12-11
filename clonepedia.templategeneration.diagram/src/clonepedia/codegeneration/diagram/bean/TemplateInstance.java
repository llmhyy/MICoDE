package clonepedia.codegeneration.diagram.bean;

import java.util.List;

public class TemplateInstance {
	private List<TypeWrapper> topTypeWrapperList;

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
