package clonepedia.templategeneration.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import clonepedia.model.ontology.Class;
import clonepedia.model.ontology.ComplexType;
import clonepedia.model.ontology.Interface;
import clonepedia.model.ontology.Method;
import clonepedia.model.ontology.ProgrammingElement;
import clonepedia.model.ontology.VarType;
import clonepedia.model.ontology.Variable;
import clonepedia.model.template.CandidateTemplate;
import clonepedia.model.template.SubCandidateTemplate;
import clonepedia.model.template.Template;
import clonepedia.model.template.TemplateMethodGroup;
import clonepedia.util.MinerUtil;
import clonepedia.util.Settings;
import datamining.cluster.IClusterable;
import datamining.cluster.NormalCluster;
import datamining.cluster.hierarchy.HierarchyClusterAlgorithm;

public class TemplateBuilder {
	private CandidateTemplate tfgList;
	
	private ArrayList<Class> abstractClassList = new ArrayList<Class>();
	
	private ArrayList<Method> abstractMethodList = new ArrayList<Method>();
	
	private ArrayList<TemplateMethodGroup> additionalTMGList = new ArrayList<TemplateMethodGroup>();
	
	public TemplateBuilder(CandidateTemplate tfgList){
		this.tfgList = tfgList;
	}
	
	public Template buildTemplate(){
		
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		abstractClasses();
		
		buildAbstractInnerOuterClassRelation();
		
		buildAbstractCallRelation();
		
		sanitize();
		
		return new Template(abstractClassList, abstractMethodList);
	}
	
	private void sanitize(){
		Iterator<Class> iterator = abstractClassList.iterator();
		while(iterator.hasNext()){
			Class clazz = iterator.next();
			if(clazz.getMethods().size() == 0 && clazz.getInnerClassList().size() == 0){
				iterator.remove();
			}
		}
	}
	
	/**
	 * list all the classes declaring methods in TMGs and cluster them to make sure
	 * that only similar classes can take part in abstraction.
	 */
	private void init() throws Exception{
		HashSet<Class> declaringClassSet = new HashSet<Class>();
		for(SubCandidateTemplate tfg: tfgList){
			for(TemplateMethodGroup tmg: tfg.getTemplateMethodGroupList()){
				for(Method method: tmg.getMethods()){
					ComplexType owner = method.getOwner();
					if(owner.isClass()){
						Class declaringClass = (Class)owner;
						declaringClassSet.add(declaringClass);
						
						/*if(declaringClass.getFullName().contains("ExitAction.Ano")){
							System.out.println();
						}*/
						
						if(declaringClass.isInnerClass()){
							declaringClassSet.add(declaringClass.getOuterClass());
						}
					}
				}
			}
		}
		
		Class[] declaringClassArray = declaringClassSet.toArray(new Class[0]);
		HierarchyClusterAlgorithm algorithm = new HierarchyClusterAlgorithm(declaringClassArray, 
				/*0.7*/Settings.thresholdDistanceForDeclaringClassClustering, HierarchyClusterAlgorithm.CompleteLinkage);
		
		ArrayList<NormalCluster> clusterList;
		try {
			clusterList = algorithm.doClustering();
			for(NormalCluster cluster: clusterList){
				Class abstractedClass = new Class("", null, "");
				if(cluster.size() > 1){
					abstractedClass.setMerge(true);
					for(IClusterable clusterable: cluster){
						Class declaringClass = (Class)clusterable;
						
						abstractedClass.setProject(declaringClass.getProject());
						abstractedClass.getSupportingElements().add(declaringClass);
					}					
				}
				else{
					Class instanceClass = (Class)cluster.get(0); 
					abstractedClass.setFullName(instanceClass.getFullName());
					abstractedClass.getSupportingElements().add(instanceClass);
					abstractedClass.setProject(instanceClass.getProject());
					//abstractedClass.setOuterClass(instanceClass.getOuterClass());
					//abstractedClass.setSuperClass(instanceClass.getSuperClass());
					//abstractedClass.setImplementedInterfaces(instanceClass.getImplementedInterfaces());
				}
				this.abstractClassList.add(abstractedClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	private void abstractClasses(){
		for(Class abstractedClass: this.abstractClassList){
			abstractClass(abstractedClass);
		}
	}
	
	private void abstractClass(Class abstractedClass){
		if(abstractedClass.getSupportingElements().size() > 1){
			
			/*if(abstractedClass.getSupportingElements().toString().contains("MinimizeAction")){
				System.out.println();
			}*/
			
			mergeName(abstractedClass);
			
			mergeSuperType(abstractedClass);
			
			tryFindingMoreAbstractMethods(abstractedClass);
			
			//mergeInterfaces(abstractedClass);
			mergeMethods(abstractedClass);			
		}
		else{
			mergeMethods(abstractedClass);	
		}
	}

	private void tryFindingMoreAbstractMethods(Class abstractedClass) {
		ArrayList<Method> methodList = new ArrayList<Method>();
		for(ProgrammingElement element: abstractedClass.getSupportingElements()){
			Class clazz = (Class)element;
			for(Method m: clazz.getMethods()){
				if(m.getCloneInstances().size() == 0){
					methodList.add(m);
				}
			}
		}
		
		Method[] methodArray = methodList.toArray(new Method[0]);
		
		HierarchyClusterAlgorithm algorithm = new HierarchyClusterAlgorithm(methodArray, 
				Settings.thresholdDistanceForSimilarLocationedMethodsClusterng, HierarchyClusterAlgorithm.CompleteLinkage);
		
		try {
			ArrayList<NormalCluster> list = algorithm.doClustering();
			for(NormalCluster cluster: list){
				TemplateMethodGroup tmg = new TemplateMethodGroup();
				for(IClusterable clusterable: cluster){
					Method method = (Method)clusterable;
					tmg.addMethod(method);
				}
				this.additionalTMGList.add(tmg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildAbstractInnerOuterClassRelation() {

		Class[] classList = abstractClassList.toArray(new Class[0]);
		for(int i=0; i<classList.length; i++){
			if(isAbstractClassContainingInnerClass(classList[i])){
				for(int j=0; j<classList.length; j++){
					if(i != j){
						if(couldAnAbstractClassBeConsideredAsInnerClassOfAnother(classList[i], classList[j])){
							classList[i].setOuterClass(classList[j]);
							classList[j].getInnerClassList().add(classList[i]);
						}
					}
				}
			}
		}
		
	}
	
	private boolean couldAnAbstractClassBeConsideredAsInnerClassOfAnother(Class innerAbClass, Class outerAbClass){
		
		double count = 0;
		for(ProgrammingElement innerElement: innerAbClass.getSupportingElements()){
			Class innerClass = (Class)innerElement;
			Class outerClass = innerClass.getOuterClass();
			
			if(outerAbClass.getSupportingElements().contains(outerClass)){
				count++;
			}
		}
		
		if(innerAbClass.getSupportingElements().size() == 0){
			return false;
		}
		else{
			double ratio = count/innerAbClass.getSupportingElements().size();
			return ratio >= Settings.innerOuterClassRelationAbstractionRatio;
			
		}
	}
	
	private boolean isAbstractClassContainingInnerClass(Class abstractClass){
		for(ProgrammingElement element: abstractClass.getSupportingElements()){
			Class clazz = (Class)element;
			if(clazz.isInnerClass())return true;
		}
		return false;
	}

	private void buildAbstractCallRelation() {
		for(int i=0; i<abstractMethodList.size(); i++){
			for(int j=0; j<abstractMethodList.size(); j++){
				if(i != j && existValidatedCallingRelations(abstractMethodList.get(i), abstractMethodList.get(j))){
					abstractMethodList.get(i).addCalleeMethod(abstractMethodList.get(j));
					abstractMethodList.get(j).addCallerMethod(abstractMethodList.get(i));
				}
			}
		}
		
	}
	
	/**
	 * this method is a clone of TMGBuidler#existValidatedCallingRelations, ready to be refactored
	 * @param callerMethod
	 * @param calleeMethod
	 * @return
	 */
	private boolean existValidatedCallingRelations(Method callerAbstractMethod, Method calleeAbstractMethod){
		
		int count = 0;
		
		TreeSet<Method> examinedMethods = new TreeSet<Method>();
		for(ProgrammingElement callerEle: callerAbstractMethod.getSupportingElements()){
			Method methodInCallerGroup = (Method)callerEle;
			if(examinedMethods.contains(methodInCallerGroup)) continue;
			
			for(ProgrammingElement calleeEle: methodInCallerGroup.getCalleeMethod()){
				Method calleeMethod = (Method)calleeEle;
				if(examinedMethods.contains(calleeMethod)) continue;
				
				if(calleeAbstractMethod.getSupportingElements().contains(calleeMethod)){
					examinedMethods.add(methodInCallerGroup);
					examinedMethods.add(calleeMethod);
					
					count++;
					break;
				}
			}
		}
		
		return count >= Settings.abstractMethodGroupCallingStrength;
	}

	private void mergeMethods(Class abstractedClass) {
		
		for(SubCandidateTemplate tfg: tfgList){
			for(TemplateMethodGroup tmg: tfg.getTemplateMethodGroupList()){
				mergeSingleTMG(tmg, abstractedClass);
			}
		}
		
		for(TemplateMethodGroup tmg: this.additionalTMGList){
			mergeSingleTMG(tmg, abstractedClass);
		}
	}
	
	private void mergeSingleTMG(TemplateMethodGroup tmg, Class abstractedClass){
		ArrayList<Method> relatedMethodList = getRelatedMethodFromTMGToAbstractClass(tmg, abstractedClass);
		if(relatedMethodList.size() > 1){
			Method abstractedMethod = abstractMethod(relatedMethodList);
			
			abstractedClass.getMethods().add(abstractedMethod);
			abstractedMethod.setOwner(abstractedClass);
			
			this.abstractMethodList.add(abstractedMethod);
		}
	}
	
	private Method abstractMethod(ArrayList<Method> relatedMethodList) {
		Method abstractedMethod = new Method("");
		for(Method m: relatedMethodList){
			abstractedMethod.getSupportingElements().add(m);
		}
		
		mergeName(abstractedMethod);
		
		abstractReturnTypeForMethod(abstractedMethod);
		
		abstractParametersForMethod(abstractedMethod);
		
		return abstractedMethod;
	}
	
	/**
	 * compute intersection (deal with it in simplified way)
	 * @param abstractedMethod
	 */
	private void abstractParametersForMethod(Method abstractedMethod) {
		
		ProgrammingElement firstElement = abstractedMethod.getSupportingElements().get(0);
		Method firstMethod = (Method)firstElement;
		
		ArrayList<Variable> paramList = new ArrayList<Variable>();
		paramList.addAll(firstMethod.getParameters());
		
		for(int i=1; i<abstractedMethod.getSupportingElements().size(); i++){
			Method m = (Method)(abstractedMethod.getSupportingElements().get(i)); 
			
			Iterator<Variable> iterator = paramList.iterator();
			while(iterator.hasNext()){
				Variable variable = iterator.next();
				if(!m.getParameterTypes().contains(variable.getVariableType())){
					iterator.remove();
				}
			}
		}
		
		abstractedMethod.setParameters(paramList);
	}

	/**
	 * take the most frequent return type as the abstracted return type
	 * @param abstractedMethod
	 */
	private void abstractReturnTypeForMethod(Method abstractedMethod){
		HashMap<VarType, Integer> returnTypeMap = new HashMap<VarType, Integer>();
		for(ProgrammingElement element: abstractedMethod.getSupportingElements()){
			Method m = (Method)element; 
			
			VarType varType = m.getReturnType();
			if(varType != null){
				Integer frequency = returnTypeMap.get(varType);
				if(null == frequency){
					returnTypeMap.put(varType, 1);
				}
				else{
					returnTypeMap.put(varType, ++frequency);
				}
			}
		}
		
		VarType abstractedReturnType = null;
		int highestFrequency = 0;
		for(VarType type: returnTypeMap.keySet()){
			Integer frequency = returnTypeMap.get(type);
			if(frequency > highestFrequency){
				highestFrequency = frequency;
				abstractedReturnType = type;
			}
		}
		
		abstractedMethod.setReturnType(abstractedReturnType);
	}

	private ArrayList<Method> getRelatedMethodFromTMGToAbstractClass(TemplateMethodGroup tmg, Class abstractedClass){
		ArrayList<Method> relatedMethodList = new ArrayList<Method>();
		
		for(Method m: tmg.getMethods()){
			ComplexType type = m.getOwner();
			if(abstractedClass.getSupportingElements().contains(type)){
				relatedMethodList.add(m);
			}
		}
		
		return relatedMethodList;
	}

	/**
	 * compute the weight of each possible super type and find the largest one
	 * @param abstractedClass
	 */
	private void mergeSuperType(Class abstractedClass) {
		
		
		HashMap<ComplexType, Double> superTypeFrequencyMap = new HashMap<ComplexType, Double>();
		
		for(ProgrammingElement element: abstractedClass.getSupportingElements()){
			Class supportingClass = (Class)element;
			double weight = 1.0;
			
			assignWeightsForParents(weight, superTypeFrequencyMap, supportingClass);
		}
		
		/**
		 * find the class with largest weight over threshold
		 * find the interfaces over threshold
		 */
		double largestWeight = 0;
		Class candidateSuperClass = null;
		for(ComplexType type: superTypeFrequencyMap.keySet()){
			Double weight = superTypeFrequencyMap.get(type);
			if(type instanceof Class && weight > largestWeight){
				largestWeight = weight;
				candidateSuperClass = (Class)type;
			}
			else if(type instanceof Interface && weight/abstractedClass.getSupportingElements().size() >= Settings.thresholdSimilarityForDecidingAbstractedInterface){
				abstractedClass.addInterface((Interface)type);
			}
		}
		
		if(largestWeight/abstractedClass.getSupportingElements().size() >= Settings.thresholdSimilarityForDecidingAbstractedClass){
			abstractedClass.setSuperClass(candidateSuperClass);			
		}
	}
	
	private void assignWeightsForParents(double weight, HashMap<ComplexType, Double> map, ComplexType type){
		if(type.getDirectParents().size() != 0){
			for(ComplexType superType: type.getDirectParents()){
				Double frequency = map.get(superType);
				if(frequency == null){
					frequency = new Double(weight);
					map.put(superType, frequency);
				}
				else{
					frequency += weight;
					map.put(superType, frequency);
				}
				
				assignWeightsForParents(weight/2, map, superType);
			}
		}
	}
	
	private void mergeName(ProgrammingElement element){
		ArrayList<ProgrammingElement> elementList = element.getSupportingElements();
		
		ArrayList<String> packageStringList = new ArrayList<String>();
		ArrayList<String> nameStringList = new ArrayList<String>();
		for(ProgrammingElement ele: elementList){
			if(ele instanceof Class){
				String fullName = ((Class)ele).getFullName();
				String packageString = ((Class)ele).getPackageName();
				
				if(packageString == null){
					packageString = fullName.substring(0, fullName.lastIndexOf("."));
				}
				
				String classNameString = fullName.substring(packageString.length()+1, fullName.length()) ;
				
				packageStringList.add(packageString);
				nameStringList.add(classNameString);
			}
			else{
				nameStringList.add(ele.getSimpleElementName());				
			}
		}
		
		String packageString = "";
		if(packageStringList.size() > 0){
			packageString = MinerUtil.generateAbstractString(packageStringList, MinerUtil.DotSplitting);
		}
		
		String abstractName = MinerUtil.generateAbstractString(nameStringList, MinerUtil.CamelSplitting);
		
		if(packageString.length() != 0){
			Class clazz = (Class)element;
			clazz.setPackageName(packageString);
			clazz.setFullName( packageString + "." + abstractName);
		}
		else{
			Method m = (Method)element;
			m.setMethodName(abstractName);
		}
		/*	return packageString + "." + abstractName;
		else
			return abstractName;*/
	}
	
	
}
