package clonepedia.codegeneration.diagram.bean;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ITypeBinding;

import clonepedia.templategeneration.diagram.util.AutoGenCTUtil;
import clonepedia.util.MinerUtil;

public abstract class MemberWrapper extends ProgramElementWrapper{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4120183723626364579L;
	protected ArrayList<MemberWrapper> callerMembers = new ArrayList<>();
	protected ArrayList<MemberWrapper> calleeMembers = new ArrayList<>();
	
	public void addCalleeMember(MemberWrapper calleeMember){
		if(!calleeMembers.contains(calleeMember)){
			calleeMembers.add(calleeMember);
		}
	}
	
	public void addCallerMember(MemberWrapper callerMember){
		if(!callerMembers.contains(callerMember)){
			callerMembers.add(callerMember);
		}
	}
	
	
	
	/**
	 * @return the callerMembers
	 */
	public ArrayList<MemberWrapper> getCallerMembers() {
		return callerMembers;
	}

	/**
	 * @return the calleeMembers
	 */
	public ArrayList<MemberWrapper> getCalleeMembers() {
		return calleeMembers;
	}

	protected double computeTypeSimilarity(ITypeBinding thisTypeBinding,
			ITypeBinding thatTypeBinding) {
		if(thisTypeBinding == null && thatTypeBinding == null){
			return 1;
		}
		else if(thisTypeBinding == null && thatTypeBinding != null){
			return 0;
		}
		else if(thisTypeBinding != null && thatTypeBinding == null){
			return 0;
		}
		
		if(thisTypeBinding.getQualifiedName().equals(thatTypeBinding.getQualifiedName())){
			return 1;
		}
		
		ArrayList<String> list1 = getAllSuperTypesAndInterfacesOfType(thisTypeBinding);
		ArrayList<String> list2 = getAllSuperTypesAndInterfacesOfType(thatTypeBinding);
		
		return AutoGenCTUtil.computeStringSimilarity(list1, list2);
	}
	
	private ArrayList<String> getAllSuperTypesAndInterfacesOfType(ITypeBinding typeBinding){
		ArrayList<String> superTypes = new ArrayList<>();
		getAllSuperTypes(typeBinding, superTypes);
		getAllInterfaces(typeBinding, superTypes);
		
		return superTypes;
	}
	
	private ArrayList<String> getAllSuperTypes(ITypeBinding typeBinding, ArrayList<String> superTypes){
		ITypeBinding superType = typeBinding.getSuperclass();
		while(superType != null){
			superTypes.add(superType.getQualifiedName());
			superType = superType.getSuperclass();
		}
		
		return superTypes;
	}
	
	private ArrayList<String> getAllInterfaces(ITypeBinding typeBinding, ArrayList<String> superTypes) {
		for(ITypeBinding interf: typeBinding.getInterfaces()){
			collectInterfaces(interf, superTypes);
		}
		
		return superTypes;
	}
	
	private void collectInterfaces(ITypeBinding interf, ArrayList<String> superTypes){
		superTypes.add(interf.getQualifiedName());
		for(ITypeBinding superInterf: interf.getInterfaces()){
			collectInterfaces(superInterf, superTypes);
		}
	}
}
