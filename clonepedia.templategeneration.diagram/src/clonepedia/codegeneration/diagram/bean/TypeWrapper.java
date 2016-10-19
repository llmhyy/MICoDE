package clonepedia.codegeneration.diagram.bean;

import java.util.ArrayList;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.model.ontology.CloneInstance;
import clonepedia.model.ontology.CloneSet;
import clonepedia.templategeneration.diagram.util.AutoGenCTUtil;
import datamining.cluster.IClusterable;

public class TypeWrapper extends ProgramElementWrapper implements IElement, IElementContainer, IClusterable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300010365271399794L;
	private TypeDeclaration typeDeclaration;
	private ArrayList<ProgramElementWrapper> members = new ArrayList<>();
	private Application application;
	
	

	public TypeWrapper(TypeDeclaration typeDeclaration){
		this.typeDeclaration = typeDeclaration;
		
		this.typeDeclaration.accept(new ASTVisitor() {
			public boolean visit(TypeDeclaration td){
				
				if(!td.equals(TypeWrapper.this.typeDeclaration)){
					
					TypeWrapper innerType = new TypeWrapper(td);
					
					innerType.setOwnerType(TypeWrapper.this);
					members.add(innerType);
				}
				
				return true;
			}
			
			public boolean visit(MethodDeclaration md){
				MethodWrapper methodWrapper = new MethodWrapper(md);
				
				methodWrapper.setOwnerType(TypeWrapper.this);
				members.add(methodWrapper);
				
				return false;
			}
			
			public boolean visit(FieldDeclaration fd){
				FieldWrapper fieldWrapper = new FieldWrapper(fd);
				
				fieldWrapper.setOwnerType(TypeWrapper.this);
				members.add(fieldWrapper);
				
				return false;
			}
		});
		
		setLocaleInfo(typeDeclaration);
	}
	
	@Override
	public double computeDistanceWith(IClusterable cluster) {
		if(cluster instanceof TypeWrapper){
			TypeWrapper thatType = (TypeWrapper)cluster;
			
			if((thatType.getName().equals("TimeSeries") && this.getName().equals("Series")) ||
					thatType.getName().equals("Series") && this.getName().equals("TimeSeries")){
				System.currentTimeMillis();
			}
			
			double similarity = computeSimilarity(thatType);
			if(similarity < 0.001){
				return 1000000;
			}
			else{
				return 1/similarity;
			}
		}
		
		
		return 1000000;
	}
	
	
	/**
	 * five dimensions: name, super type, interface, clone and method signatures.
	 * The weight is 20:15:15:20:25. If there is not any super type or interface,
	 * these dimensions will be reduced and their weights will be averagely distributed
	 * to other dimensions.
	 */
	@Override
	public double computeSimilarity(IElement elementWrapper){
		
		if(!(elementWrapper instanceof TypeWrapper)){
			return 0;
		}
		
		TypeWrapper thatType = (TypeWrapper)elementWrapper;
		
		if(this.isClass() != thatType.isClass()){
			return 0;
		}
		
		if(hasSuperSubRelation(this, thatType)){
			return 0;
		}
		
		double nameWeight = AutoGenCTSettings.retrieveTypeNameSimilarity();
		double superTypeWeight = AutoGenCTSettings.retrieveTypeSuperClassSimilarity();
		double interfaceWeight = AutoGenCTSettings.retrieveTypeInterfaceSimilarity();
		double cloneWeight = AutoGenCTSettings.retrieveTypeBodySimilarity();
		
		
		//double signatureWeight = 15;
		
		double nameSim = computeNameSimilarity(this.getName(), thatType.getName());
		double superTypeSim = computeSupertypeSimilarity(this, thatType);
		double interfaceSim = computeInterfaceSimilarity(this, thatType);
		double cloneSim = computeCloneSimilarity(this, thatType);
		//double signatureSim = computeSignatureSimilarity(this, thatType);
		
		/**
		 * if two types have no superclass or interfaces, I will ignore the corresponding weights.
		 */
		superTypeWeight = (superTypeSim == -1)? 0 : superTypeWeight;
		interfaceWeight = (interfaceSim == -1)? 0 : interfaceWeight;
		
		double numerator = nameWeight*nameSim + superTypeSim*superTypeWeight
				+ interfaceWeight*interfaceSim + cloneSim*cloneWeight /*+ signatureSim*signatureWeight*/;
		double denominator = nameWeight + superTypeWeight + interfaceWeight + cloneWeight /*+ signatureWeight*/;
		
		double overallSim = numerator/denominator;
		return overallSim;
	}
	
	private boolean hasSuperSubRelation(TypeWrapper thisType, TypeWrapper thatType) {
		String thisQualifiedName = thisType.getTypeDeclaration().resolveBinding().getQualifiedName();
		String thatQualifiedName = thatType.getTypeDeclaration().resolveBinding().getQualifiedName();
		
		ArrayList<String> thisSuperTypes = thisType.getAllSuperTypes(thisType);
		thisSuperTypes.addAll(getAllInterfaces(thisType));
		
		ArrayList<String> thatSuperTypes = thisType.getAllSuperTypes(thatType);
		thatSuperTypes.addAll(getAllInterfaces(thatType));
		
		return thisSuperTypes.contains(thatQualifiedName) || thatSuperTypes.contains(thisQualifiedName);
		
		//return false;
	}

	private double computeSignatureSimilarity(TypeWrapper typeWrapper,
			TypeWrapper thatType) {
		ArrayList<String> methodSignatures1 = collectMethodSignature(typeWrapper);
		ArrayList<String> methodSignatures2 = collectMethodSignature(thatType);
		
		return AutoGenCTUtil.computeStringSimilarity(methodSignatures1, methodSignatures2);
	}
	
	private ArrayList<String> collectMethodSignature(TypeWrapper typeWrapper){
		ArrayList<String> methodNames = new ArrayList<>();
		for(ProgramElementWrapper element: typeWrapper.getMembers()){
			if(element instanceof MethodWrapper){
				MethodWrapper methodWrapper = (MethodWrapper)element;
				methodNames.add(methodWrapper.getMethodName());
			}
		}
		return methodNames;
	}

	private double computeCloneSimilarity(TypeWrapper typeWrapper,
			TypeWrapper thatType) {
		CompilationUnit unit1 = typeWrapper.getCompilationUnit();
		String location1 = unit1.getJavaElement().getResource().getLocation().toString();
		
		CompilationUnit unit2 = thatType.getCompilationUnit();
		String location2 = unit2.getJavaElement().getResource().getLocation().toString();
		
		//long t1 = System.currentTimeMillis();
		int sharedClonedLine = getSharedCloneLineNumber(location1, location2);
		//long t2 = System.currentTimeMillis();
		//System.out.println(t2-t1);
		
		double len = unit1.getLineNumber(unit1.getLength()-1) + unit2.getLineNumber(unit2.getLength()-1);
		
		return (2*(double)sharedClonedLine)/len;
	}
	
	private int getSharedCloneLineNumber(String location1, String location2){
		CloneInstance instance1 = null;
		CloneInstance instance2 = null;
		
		int totalLen = 0;
		for(CloneSet set: AutoGenCTSettings.cloneSets.getCloneList()){
			for(CloneInstance instance: set){
				String instanceLocation = instance.getFileLocation();
				
				if(instanceLocation.equals(location1)){
					instance1 = instance;
				}
				if(instanceLocation.equals(location2)){
					instance2 = instance;
				}
			}
			
			if(instance1 != null && instance2 != null){
				totalLen += instance1.getEndLine() - instance1.getStartLine() + 1;
			}
			
			instance1 = null;
			instance2 = null;
		}
		
		return totalLen;
	}

	private double computeInterfaceSimilarity(TypeWrapper typeWrapper,
			TypeWrapper thatType) {
		ArrayList<String> list1 = getAllInterfaces(typeWrapper);
		ArrayList<String> list2 = getAllInterfaces(thatType);
		
		if(list1.size() == 0 && list2.size() == 0){
			return -1;
		}
		else{
			return AutoGenCTUtil.computeStringSimilarity(list1, list2);			
		}
	}

	private ArrayList<String> getAllInterfaces(TypeWrapper typeWrapper) {
		ArrayList<String> interfaces = new ArrayList<>();
		ITypeBinding binding = typeWrapper.getTypeDeclaration().resolveBinding();
		for(ITypeBinding interf: binding.getInterfaces()){
			collectInterfaces(interf, interfaces);
		}
		
		return interfaces;
	}
	
	private void collectInterfaces(ITypeBinding interf, ArrayList<String> interfaces){
		String qualifiedName = interf.getQualifiedName();
		if(!qualifiedName.equals("java.io.Serializable")){
			interfaces.add(qualifiedName);
			
			for(ITypeBinding superInterf: interf.getInterfaces()){
				collectInterfaces(superInterf, interfaces);
			}
		}
		
	}

	private double computeSupertypeSimilarity(TypeWrapper typeWrapper,
			TypeWrapper thatType) {
		ArrayList<String> list1 = getAllSuperTypes(typeWrapper);
		ArrayList<String> list2 = getAllSuperTypes(thatType);
		
		if(list1.size() == 0 && list2.size() == 0){
			return -1;
		}
		else{
			return AutoGenCTUtil.computeStringSimilarity(list1, list2);			
		}
	}
	
	private ArrayList<String> getAllSuperTypes(TypeWrapper typeWrapper){
		ArrayList<String> superTypes = new ArrayList<>();
		ITypeBinding binding = typeWrapper.getTypeDeclaration().resolveBinding();
		ITypeBinding superType = binding.getSuperclass();
		while(superType != null){
			String qualifiedName = superType.getQualifiedName();
			//if(!qualifiedName.equals("java.lang.Object")){
				superTypes.add(qualifiedName);				
			//}
			superType = superType.getSuperclass();
		}
		
		return superTypes;
	}
	
	public String getDirectSuperType(){
		ITypeBinding typeBinding = typeDeclaration.resolveBinding();
		ITypeBinding superTypeBinding = typeBinding.getSuperclass();
		
		if(superTypeBinding != null){
			String superTypeName = superTypeBinding.getQualifiedName();
			if(!superTypeName.equals("java.lang.Object")){
				return superTypeBinding.getQualifiedName();				
			}
		}
		
		return null;
	}
	
	public ArrayList<String> getDirectInterfaces(){
		ArrayList<String> directInterfaces = new ArrayList<>();
		ITypeBinding typeBinding = typeDeclaration.resolveBinding();
		for(ITypeBinding interfBinding: typeBinding.getInterfaces()){
			directInterfaces.add(interfBinding.getQualifiedName());
		}
		
		return directInterfaces;
	}
	
	public String toString(){
		return getName();
	}
	
	public boolean isClass(){
		return !typeDeclaration.isInterface();
	}
	
	public String getName(){
		return typeDeclaration.getName().getFullyQualifiedName();
	}
	
	/**
	 * @return the project
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * @param project the project to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
	
	/**
	 * @return the members
	 */
	public ArrayList<ProgramElementWrapper> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(ArrayList<ProgramElementWrapper> members) {
		this.members = members;
	}

	/**
	 * @return the typeDeclaration
	 */
	public TypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}

	/**
	 * @param typeDeclaration the typeDeclaration to set
	 */
	public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
		this.typeDeclaration = typeDeclaration;
	}
	
	public CompilationUnit getCompilationUnit(){
		ASTNode parent = typeDeclaration.getParent();
		while(parent.getParent() != null){
			parent = parent.getParent();
		}
		
		return (CompilationUnit)parent;
	}

	@Override
	public ArrayList<? extends IElement> getContainedElements() {
		return members;
	}
	
	@Override
	public ASTNode getCorrespondingASTNode() {
		return typeDeclaration;
	}


	@Override
	public String getASTHandle() {
		return typeDeclaration.resolveBinding().getJavaElement().getHandleIdentifier();
	}
	
	public String getPackageName(){
		return typeDeclaration.resolveBinding().getPackage().getName();
	}


	@Override
	public String getKey() {
		IType iType = (IType) typeDeclaration.resolveBinding().getJavaElement();
		return iType.getKey();
	}
}
