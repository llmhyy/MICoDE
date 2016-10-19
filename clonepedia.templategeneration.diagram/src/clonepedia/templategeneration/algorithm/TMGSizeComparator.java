package clonepedia.templategeneration.algorithm;

import java.util.Comparator;

import clonepedia.model.template.TemplateMethodGroup;

public class TMGSizeComparator implements Comparator<TemplateMethodGroup> {

	@Override
	public int compare(TemplateMethodGroup tmg1, TemplateMethodGroup tmg2) {
		Integer i1 = new Integer(tmg1.getMethods().size());
		Integer i2 = new Integer(tmg2.getMethods().size());
		return i2.compareTo(i1);
	}

}
