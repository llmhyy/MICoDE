package clonepedia.codegeneration.diagram.bean;

import java.util.ArrayList;

public class DesignList extends ArrayList<TemplateDesign> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8162820508482976526L;
	
	public TemplateDesign findDesignContainingTypeMultiset(String setId){
		for(TemplateDesign design: this){
			for(Multiset set: design.getDesign()){
				if(set.getId().equals(setId)){
					return design;
				}
			}
		}
		
		return null;
	}
	
	public Multiset findMultiset(String setId){
		for(TemplateDesign design: this){
			for(Multiset set: design.getDesign()){
				Multiset ms = find(set, setId);
				if(ms != null){
					return ms;
				}
			}
		}
		
		return null;
	}
	
	private Multiset find(Multiset parentSet, String id){
		if(parentSet.getId().equals(id)){
			return parentSet;
		}
		else{
			for(Multiset subSet: parentSet.getSubMultisetList()){
				if(!subSet.isTypeSet()){
					if(subSet.getId().equals(id)){
						return subSet;
					}
				}
				else{
					Multiset ms = find(subSet, id);
					if(ms != null){
						return ms;
					}
				}
			}
		}
		
		return null;
	}

	public ArrayList<TemplateDesign> findContainingDesigns(
			TemplateDesign independentDesign) {
		ArrayList<TemplateDesign> superDesigns = new ArrayList<>();
		for(TemplateDesign design: this){
			if(design.contains(independentDesign)){
				superDesigns.add(design);
			}
		}
		return superDesigns;
	}
}
