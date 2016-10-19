package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;

import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.model.ontology.CloneInstance;
import clonepedia.model.ontology.CloneSet;

public class Application implements IElement, IElementContainer, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2083371710933966768L;
	private String applicationLocation;
	private ArrayList<TypeWrapper> types = new ArrayList<>();
	
	private ArrayList<MemberWrapper> memberTable = new ArrayList<>();
	
	private Multiset belongingMultiset;

	public Application(String applicationLocation){
		this.applicationLocation = applicationLocation;
	}
	
	public String toString(){
		return getApplicationName();
	}
	
	public String getApplicationName() {
		return this.applicationLocation.substring(this.applicationLocation.lastIndexOf("\\")+1, 
				this.applicationLocation.length());
	}

	
	public String getApplicationLocation() {
		return applicationLocation;
	}

	
	public void setApplicationLocation(String applicationLocation) {
		this.applicationLocation = applicationLocation;
	}
	
	public boolean equals(Application application){
		return this.applicationLocation.equals(application.getApplicationLocation());
	}

	/**
	 * @return the types
	 */
	public ArrayList<TypeWrapper> getTypes() {
		return types;
	}
	
	public void addType(TypeWrapper type){
		if(!types.contains(type)){
			types.add(type);
		}
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(ArrayList<TypeWrapper> types) {
		this.types = types;
	}

	private void collectMemberTable() {
		for(TypeWrapper typeWrapper: this.types){
			for(ProgramElementWrapper elementWrapper: typeWrapper.getMembers()){
				collectMembers(elementWrapper);
			}
		}
	}
	
	public int getFieldNumber(){
		int count = 0;
		for(MemberWrapper member: memberTable){
			if(member instanceof FieldWrapper){
				count++;
			}
		}
		
		return count;
	}
	
	public int getMethodNumber(){
		int count = 0;
		for(MemberWrapper member: memberTable){
			if(member instanceof MethodWrapper){
				count++;
			}
		}
		
		return count;
	}
	
	public int getLOC(){
		int count = 0;
		for(TypeWrapper type: types){
			char[] charList = type.getCompilationUnit().toString().toCharArray();
			for(int i=0; i<charList.length; i++){
				if(charList[i] == '\n'){
					count++;
				}
			}
		}
		return count;
	}
	
	public double getCloneRatio(){
		int count = 0;
		for(CloneSet set: AutoGenCTSettings.cloneSets.getCloneList()){
			for(CloneInstance instance: set){
				if(instance.getFileLocation().contains(applicationLocation)){
					count++;
					break;
				}
			}
			
		}
		
		return (double)count/AutoGenCTSettings.cloneSets.getCloneList().size();
	}
	
	private void collectMembers(ProgramElementWrapper elementWrapper){
		if(elementWrapper instanceof TypeWrapper){
			TypeWrapper typeWrapper = (TypeWrapper)elementWrapper;
			for(ProgramElementWrapper eleWrapper: typeWrapper.getMembers()){
				collectMembers(eleWrapper);
			}
		}
		else if(elementWrapper instanceof MemberWrapper){
			MemberWrapper memberWrapper = (MemberWrapper)elementWrapper;
			memberTable.add(memberWrapper);
		}
	}

	public void buildCallingRelation() {
		collectMemberTable();
		
		for(MemberWrapper callerMember: memberTable){
			if(callerMember instanceof MethodWrapper){
				MethodWrapper methodWrapper = (MethodWrapper)callerMember;
				
				RelationBuilder builder = new RelationBuilder();
				methodWrapper.getMethodDeclaration().accept(builder);
				
				ArrayList<MemberWrapper> callingList = builder.getCallingList();
				for(MemberWrapper calleeMember: callingList){
					callerMember.addCalleeMember(calleeMember);
					calleeMember.addCallerMember(callerMember);
				}
			}
		}
		
		System.currentTimeMillis();
	}
	
	public class RelationBuilder extends ASTVisitor{
		private ArrayList<MemberWrapper> callingList = new ArrayList<>();
		
		public boolean visit(MethodInvocation invocation){
			IMethodBinding binding = invocation.resolveMethodBinding().getMethodDeclaration();
			for(MemberWrapper member: memberTable){
				if(member instanceof MethodWrapper){
					MethodWrapper methodWrapper = (MethodWrapper)member;
					String key = methodWrapper.getMethodDeclaration().resolveBinding().getKey();
					if(key.equals(binding.getKey())){
						if(!callingList.contains(methodWrapper)){
							callingList.add(methodWrapper);							
						}
					}
				}
			}
			
			return true;
		}

		public boolean visit(ClassInstanceCreation creation){
			IMethodBinding binding = creation.resolveConstructorBinding().getMethodDeclaration();
			for(MemberWrapper member: memberTable){
				if(member instanceof MethodWrapper){
					MethodWrapper methodWrapper = (MethodWrapper)member;
					String key = methodWrapper.getMethodDeclaration().resolveBinding().getKey();
					if(key.equals(binding.getKey())){
						if(!callingList.contains(methodWrapper)){
							callingList.add(methodWrapper);							
						}
					}
				}
			}
			
			return true;
		}
		
		public boolean visit(FieldAccess access){
			IVariableBinding binding = access.resolveFieldBinding().getVariableDeclaration();
			for(MemberWrapper member: memberTable){
				if(member instanceof FieldWrapper){
					FieldWrapper fieldWrapper = (FieldWrapper)member;
					String key = fieldWrapper.getFirstFragment().resolveBinding().getKey();
					if(key.equals(binding.getKey())){
						if(!callingList.contains(fieldWrapper)){
							callingList.add(fieldWrapper);							
						}
					}
				}
			}
			
			return true;
		}
		

		/**
		 * @return the callingList
		 */
		public ArrayList<MemberWrapper> getCallingList() {
			return callingList;
		}
	}

	@Override
	public double computeSimilarity(IElement element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<? extends IElement> getContainedElements() {
		return types;
	}

	@Override
	public boolean isMarked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMarked(boolean mark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Multiset getBelongingMultiset() {
		return belongingMultiset;
	}

	@Override
	public void setBelongingMultiset(Multiset multiset) {
		this.belongingMultiset = multiset;
	}
}
