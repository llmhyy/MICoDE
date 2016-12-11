package clonepedia.templategeneration.diagram.evaluation;

import clonepedia.codegeneration.diagram.bean.DesignList;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import template_model.diagram.util.AutoGenCTSettings;

public class TemplateEvaluator {
	public void evaluate(){
		DesignList designList = AutoGenCTSettings.designs;
		for(TemplateDesign design: designList){
			System.currentTimeMillis();
		}
	}
}
