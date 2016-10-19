package clonepedia.templategeneration.diagram.view.model;

import java.util.ArrayList;

import clonepedia.model.template.CandidateTemplate;
import clonepedia.model.template.SubCandidateTemplate;
import clonepedia.model.template.TemplateMethodGroup;
import clonepedia.model.template.CandidateTemplateList;

public class TemplateList {
	private ArrayList<TemplateWrapper> templateWrapperList = new ArrayList<TemplateWrapper>();
	private CandidateTemplateList totalTFGs;

	public TemplateList(CandidateTemplateList totalTFGs) {
		super();
		this.totalTFGs = totalTFGs;
		transformData();
	}
	
	private void transformData(){
		if(totalTFGs == null)return;
		
		for(CandidateTemplate tfgList: totalTFGs){
			TemplateWrapper templateWrapper = new TemplateWrapper(tfgList);
			for(SubCandidateTemplate tfg: tfgList){
				for(TemplateMethodGroup tmg: tfg.getTemplateMethodGroupList()){
					TMGWrapper tmgWrapper = new TMGWrapper(tmg);
					for(clonepedia.model.ontology.Method method: tmg.getMethods()){
						MethodWrapper methodWrapper = new MethodWrapper(method);
						tmgWrapper.getMethodWrapperList().add(methodWrapper);
					}
					
					templateWrapper.getTmgWrapperList().add(tmgWrapper);
				}
			}
			
			this.templateWrapperList.add(templateWrapper);
		}
	}

	public ArrayList<TemplateWrapper> getTemplateWrapperList() {
		return templateWrapperList;
	}

	public void setTemplateWrapperList(ArrayList<TemplateWrapper> templateWrapperList) {
		this.templateWrapperList = templateWrapperList;
	}

	public CandidateTemplateList getTotalTFGs() {
		return totalTFGs;
	}

	public void setTotalTFGs(CandidateTemplateList totalTFGs) {
		this.totalTFGs = totalTFGs;
	}
}
