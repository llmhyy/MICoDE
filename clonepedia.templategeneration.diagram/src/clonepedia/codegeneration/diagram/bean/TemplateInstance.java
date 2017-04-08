package clonepedia.codegeneration.diagram.bean;

import java.util.ArrayList;
import java.util.HashSet;
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
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof TemplateInstance))
			return false;
		HashSet<TypeWrapper> set1 = new HashSet<TypeWrapper>(topTypeWrapperList);
		HashSet<TypeWrapper> set2 = new HashSet<TypeWrapper>(((TemplateInstance)obj).getTopTypeWrapperList());
		return set1.equals(set2);
	}
	
}
