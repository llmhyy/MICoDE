package clonepedia.codegeneration.diagram.bean;

import java.util.Comparator;

public class TemplateDesignComparator implements Comparator<TemplateDesign> {

	@Override
	public int compare(TemplateDesign arg0, TemplateDesign arg1) {
		return arg1.getAbstractTypeNumber() - arg0.getAbstractTypeNumber();
	}

}
