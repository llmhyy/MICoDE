package clonepedia.templategeneration.diagram.evaluation;

import java.util.List;

import clonepedia.codegeneration.diagram.bean.DesignList;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import clonepedia.codegeneration.diagram.bean.TemplateInstance;
import template_model.diagram.util.AutoGenCTSettings;

public class TemplateEvaluator {
	
	class Accuracy{
		double precision;
		double recall;
	}
	
	public void evaluate(){
		DesignList designList = AutoGenCTSettings.designs;
		for(TemplateDesign design: designList){
			List<TemplateInstance> instanceList = design.resolveTemplateInstance();
			
			for(TemplateInstance instance: instanceList){
				List<TemplateInstance> otherInstanceList = findOtherInstance(instanceList, instance);
				
				TemplateDesign newDesign = summarize(otherInstanceList);
				
				Accuracy accuracy = compare(newDesign, instance);
				
				System.out.println(accuracy);
			}
		}
	}

	private Accuracy compare(TemplateDesign newDesign, TemplateInstance instance) {
		// TODO Auto-generated method stub
		return null;
	}

	private TemplateDesign summarize(List<TemplateInstance> otherInstanceList) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<TemplateInstance> findOtherInstance(List<TemplateInstance> instanceList, TemplateInstance instance) {
		// TODO Auto-generated method stub
		return null;
	}
}
