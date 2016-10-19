package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import datamining.frequentitem.IItem;

public class Multiset implements Serializable, IItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7295085460901640994L;
	
	private String id = UUID.randomUUID().toString();

	private ArrayList<IElement> correspondingSet = new ArrayList<>();
	private ArrayList<Multiset> subMultisetList = new ArrayList<>();
	private Multiset superMultiset;
	
	private ArrayList<Multiset> callerSets = new ArrayList<>();
	private ArrayList<Multiset> calleeSets = new ArrayList<>();
	
	private boolean marked;
	private AbstractProgramElement abstractElement;
	/**
	 * @return the abstractElement
	 */
	public AbstractProgramElement getAbstractElement() {
		return abstractElement;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @param abstractElement the abstractElement to set
	 */
	public void setAbstractElement(AbstractProgramElement abstractElement) {
		this.abstractElement = abstractElement;
	}

	/**

	 * @return the marked
	 */
	public boolean isMarked() {
		return marked;
	}

	public boolean isTypeSet(){
		if(correspondingSet.size() == 0){
			return false;
		}
		
		return correspondingSet.get(0) instanceof TypeWrapper;
	}
	
	public boolean isMethodSet(){
		if(correspondingSet.size() == 0){
			return false;
		}
		
		return correspondingSet.get(0) instanceof MethodWrapper;
	}
	
	public boolean isFieldSet(){
		if(correspondingSet.size() == 0){
			return false;
		}
		
		return correspondingSet.get(0) instanceof FieldWrapper;
	}

	/**
	 * @param marked the marked to set
	 */
	public void setMarked(boolean marked) {
		this.marked = marked;
	}


	/**
	 * @return the superMultiset
	 */
	public Multiset getSuperMultiset() {
		return superMultiset;
	}


	/**
	 * @return the callerSet
	 */
	public ArrayList<Multiset> getCallerSets() {
		return callerSets;
	}


	/**
	 * @return the calleeSet
	 */
	public ArrayList<Multiset> getCalleeSets() {
		return calleeSets;
	}
	
	public void addCaller(Multiset caller){
		if(!callerSets.contains(caller)){
			callerSets.add(caller);
		}
	}
	
	public void addCallee(Multiset callee){
		if(!calleeSets.contains(callee)){
			calleeSets.add(callee);
		}
	}


	/**
	 * @param superMultiset the superMultiset to set
	 */
	public void setSuperMultiset(Multiset superMultiset) {
		this.superMultiset = superMultiset;
	}


	/**
	 * this method indicate whether the elements contained in this multiset
	 * is container(e.g., application, type) or ordinary element (e.g., method,
	 * field). Such information help algorithm to make recursive decision.
	 * @return
	 */
	public boolean isContainerMultiset(){
		if(correspondingSet.size() == 0){
			return false;
		}
		else{
			IElement element = correspondingSet.get(0);
			return (element instanceof IElementContainer);
		}
	}
	
	
	/**
	 * @param subMultisetList the subMultisetList to set
	 */
	public void setSubMultisetList(ArrayList<Multiset> subMultisetList) {
		this.subMultisetList = subMultisetList;
	}


	/**
	 * @return the subMultiset
	 */
	public ArrayList<Multiset> getSubMultisetList() {
		return subMultisetList;
	}
	
	public void addSubMultiset(Multiset subMultiset){
		subMultisetList.add(subMultiset);
	}

	/**
	 * @return the correspondingSet
	 */
	public ArrayList<IElement> getCorrespondingSet() {
		return correspondingSet;
	}
	
	public void addElement(IElement element){
		correspondingSet.add(element);
	}
	
	public int size(){
		return correspondingSet.size();
	}
	
	public double computeSimilarity(IElement element){
		double all = 0;
		
		for(IElement ele: correspondingSet){
			//long t1 = System.currentTimeMillis();
			all += ele.computeSimilarity(element);
			//long t2 = System.currentTimeMillis();
			//System.out.println(t2-t1);
			
		}
		
		return all/((double)correspondingSet.size());
	}
	
	public String toString(){
		return correspondingSet.toString();
	}

	@Override
	public boolean equalItem(IItem item) {
		return equals(item);
	}
	
	@Override 
	public boolean equals(Object obj){
		//TODO simplified implementation here
		return this == obj;
	}
}
