package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


public class TemplateDesign implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4744609248737367648L;
	
	private String id = UUID.randomUUID().toString();
	
	private String name;
	private String description;
	
	/**
	 * The materials contains the a forest (tree) like structure. The first layer of materials
	 * contains the top-level elements of containment tree, e.g., classes. The second layer represents
	 * the contained elements in the top-level elements.  
	 */
	private ArrayList<Multiset> materials = new ArrayList<>();
	
	public String toString(){
		return this.materials.toString();
	}
	
	public int getAbstractTypeNumber(){
		int count = 0;
		for(Multiset multiset: materials){
			if(multiset.isTypeSet()){
				count++;
			}
		}
		return count;
	}
	
	public int getAbstractInnerTypeNumber(){
		int count = 0;
		for(Multiset multiset: materials){
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
		for(Multiset multiset: materials){
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
		for(Multiset multiset: materials){
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
	public ArrayList<Multiset> getMaterials() {
		return materials;
	}
	
	public void add(Multiset multiset){
		if(!materials.contains(multiset)){
			materials.add(multiset);
		}
	}
	
	public void merge(TemplateDesign design){
		for(Multiset set: design.getMaterials()){
			this.materials.add(set);
		}
	}

	public boolean contains(TemplateDesign independentDesign) {
		for(Multiset set: independentDesign.getMaterials()){
			if(!this.getMaterials().contains(set)){
				return false;
			}
		}
		
		return true;
	}

	public void remove(TemplateDesign independentDesign) {
		Iterator<Multiset> iterator = this.getMaterials().iterator();
		while(iterator.hasNext()){
			Multiset set = iterator.next();
			if(independentDesign.getMaterials().contains(set)){
				iterator.remove();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<TemplateInstance> resolveTemplateInstance() {
		
		/**TODO 
		 * Here, we need an algorithm to distinguish template instances. The
		 * basic idea is to identify the top-level types in the same template
		 * instance. Afterwards, we use containment relation to further identify 
		 * the corresponding members.
		 */
		
		List<TemplateInstance> list = new ArrayList<>();
		
		
		
		return null;
	}
}
