package template_model.diagram.part;

import java.util.HashSet;

import clonepedia.model.ontology.ComplexType;

public class TypeNode{
	private HashSet<TypeNode> parents = new HashSet<TypeNode>();
	private HashSet<TypeNode> children = new HashSet<TypeNode>();
	private ComplexType type;
	public TypeNode(ComplexType type) {
		super();
		this.type = type;
	}
	
	@Override
	public int hashCode(){
		return type.getFullName().hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof TypeNode){
			TypeNode node = (TypeNode)obj;
			return node.getType().getFullName().equals(this.type.getFullName());
		}
		return false;
	}
	
	@Override
	public String toString(){
		return this.type.getFullName();
	}
	
	public HashSet<TypeNode> getParents() {
		return parents;
	}
	public HashSet<TypeNode> getChildren() {
		return children;
	}
	
	public void addParent(TypeNode node){
		this.parents.add(node);
	}
	
	public void addChild(TypeNode node){
		this.children.add(node);
	}
	
	public ComplexType getType(){
		return this.type;
	}
}