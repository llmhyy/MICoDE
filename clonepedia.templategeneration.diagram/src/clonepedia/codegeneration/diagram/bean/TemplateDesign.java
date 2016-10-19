package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;


public class TemplateDesign implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4744609248737367648L;
	
	private String id = UUID.randomUUID().toString();
	
	private String name;
	private String description;
	private ArrayList<Multiset> design = new ArrayList<>();
	
	public String toString(){
		return this.design.toString();
	}
	
	public int getAbstractTypeNumber(){
		int count = 0;
		for(Multiset multiset: design){
			if(multiset.isTypeSet()){
				count++;
			}
		}
		return count;
	}
	
	public int getAbstractInnerTypeNumber(){
		int count = 0;
		for(Multiset multiset: design){
			count += getInnerClassNumber(multiset);
		}
		return count;
	}
	
	public int getInnerClassNumber(Multiset set){
		int count = 0;
		for(Multiset subSet: set.getSubMultisetList()){
			if(subSet.isTypeSet()){
				count++;
				count += getInnerClassNumber(subSet);
			}
		}
		return count;
	}
	
	public int getAbstractFieldNumber(){
		int count = 0;
		for(Multiset multiset: design){
			count += getInnerFieldNumber(multiset);
		}
		return count;
	}
	
	private int getInnerFieldNumber(Multiset set){
		int count = 0;
		for(Multiset subSet: set.getSubMultisetList()){
			if(subSet.isFieldSet()){
				count ++;
			}
			else if(subSet.isTypeSet()){
				count += getInnerFieldNumber(subSet);
			}
		}
		return count;
	}
	
	public int getAbstractMethodNumber(){
		int count = 0;
		for(Multiset multiset: design){
			count += getInnerMethodNumber(multiset);
		}
		return count;
	}
	
	private int getInnerMethodNumber(Multiset set){
		int count = 0;
		for(Multiset subSet: set.getSubMultisetList()){
			if(subSet.isMethodSet()){
				count ++;
			}
			else if(subSet.isTypeSet()){
				count += getInnerFieldNumber(subSet);
			}
		}
		return count;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	public String getID(){
		return id;
	}
	
	public void setID(String id){
		this.id = id;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the design
	 */
	public ArrayList<Multiset> getDesign() {
		return design;
	}
	
	public void add(Multiset multiset){
		if(!design.contains(multiset)){
			design.add(multiset);
		}
	}
	
	public void merge(TemplateDesign design){
		for(Multiset set: design.getDesign()){
			this.design.add(set);
		}
	}

	public boolean contains(TemplateDesign independentDesign) {
		for(Multiset set: independentDesign.getDesign()){
			if(!this.getDesign().contains(set)){
				return false;
			}
		}
		
		return true;
	}

	public void remove(TemplateDesign independentDesign) {
		Iterator<Multiset> iterator = this.getDesign().iterator();
		while(iterator.hasNext()){
			Multiset set = iterator.next();
			if(independentDesign.getDesign().contains(set)){
				iterator.remove();
			}
		}
	}
}
