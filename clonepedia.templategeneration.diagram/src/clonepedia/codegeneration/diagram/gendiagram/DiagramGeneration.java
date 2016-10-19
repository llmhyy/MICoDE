package clonepedia.codegeneration.diagram.gendiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import clonepedia.model.ontology.ComplexType;
import clonepedia.model.ontology.ProgrammingElement;

import template_model.Call;
import template_model.Class;
import template_model.Element;
import template_model.ExtendClass;
import template_model.ExtendInterface;
import template_model.Implement;
import template_model.Interface;
import template_model.IsInnerClassOf;
import template_model.Method;
import template_model.TemplateGraph;
import template_model.Template_modelFactory;
import template_model.Type;
import template_model.diagram.part.TypeNode;

public abstract class DiagramGeneration {
	protected HashMap<clonepedia.model.ontology.Method, Method> methodMap = new HashMap<clonepedia.model.ontology.Method, Method>();
	protected HashMap<clonepedia.model.ontology.Class, Class> classMap = new HashMap<clonepedia.model.ontology.Class, Class>();
	protected HashMap<clonepedia.model.ontology.Interface, Interface> interfaceMap = new HashMap<clonepedia.model.ontology.Interface, Interface>();
	
	protected HashMap<String, ExtendClass> extendClassMap = new HashMap<String, ExtendClass>();
	protected HashMap<String, ExtendInterface> extendInterfaceMap = new HashMap<String, ExtendInterface>();
	protected HashMap<String, Implement> implementMap = new HashMap<String, Implement>();
	protected HashMap<String, IsInnerClassOf> isInnerClassOfMap = new HashMap<String, IsInnerClassOf>();
	
	/**
	 * set the name, fullname and isComplete attribute
	 * @param elemetModel
	 * @param element
	 */
	protected void setModelValue(Element elemetModel, ProgrammingElement element){
		elemetModel.setName(element.getSimpleElementName());
		elemetModel.setFullName(element.getFullName());
		
		StringBuffer descrption = new StringBuffer();
		for(ProgrammingElement ele: element.getSupportingElements()){
			elemetModel.getSupportingElements().add(ele.getFullName());
			descrption.append(ele.getSimpleElementName() + ", ");
		}
		//elemetModel.setDescription(descrption.toString());
		
		if(element.getSupportingElements().size() <= 1){
			elemetModel.setIsComplete(true);
		}
	}
	
	protected void generateDeclaringType(
			clonepedia.model.ontology.Method m, Method methodModel,
			HashMap<clonepedia.model.ontology.Class, Class> classMap,
			HashMap<clonepedia.model.ontology.Interface, Interface> interfaceMap, TemplateGraph graph) {
		ComplexType type = m.getOwner();
		Type typeModel;
		if (type.isClass()) {
			clonepedia.model.ontology.Class clazz = (clonepedia.model.ontology.Class) type;
			typeModel = getClassOrCreateOneFromPool(clazz);
			
		} else {
			clonepedia.model.ontology.Interface interf = (clonepedia.model.ontology.Interface) type;
			typeModel = getInterfaceOrCreateOneFromPool(interf);
		}
	
		typeModel.setName(type.getSimpleElementName());
		typeModel.setFullName(type.getFullName());
	
		typeModel.getMethods().add(methodModel);
		
		if(type.isClass()){
			clonepedia.model.ontology.Class clazz = (clonepedia.model.ontology.Class) type;
			if(clazz.isInnerClass()){
				
				clonepedia.model.ontology.Class outerClass = clazz.getOuterClass();
				Class outerClassModel = getClassOrCreateOneFromPool(outerClass);
				Class innerClassModel = (Class)typeModel;
				
				outerClassModel.setName(outerClass.getSimpleName());
				outerClassModel.setFullName(outerClass.getFullName());
				
				outerClassModel.getInnerClasses().add(innerClassModel);
				graph.getElements().add(outerClassModel);
			}
			else{
				graph.getElements().add(typeModel);
			}
		}
		else{
			graph.getElements().add(typeModel);
		}
		//Declare declareRelation = Template_modelFactory.eINSTANCE.createDeclare();
		//declareRelation.setMethod(methodModel);
		//declareRelation.setType(typeModel);
	
		//graph.getElements().add(typeModel);
		//graph.getLinks().add(declareRelation);
	}

	protected void generateRelationsForDeclaringTypes(HashSet<ComplexType> declaringTypeList,
			HashMap<clonepedia.model.ontology.Class, Class> classMap, 
			HashMap<clonepedia.model.ontology.Interface, Interface> interfaceMap, TemplateGraph graph) {
		
		if(declaringTypeList.size() == 1) return;
		
		HashMap<String, TypeNode> declaringTypeNodeMap = buildTypeHierarchyTreeStructure(declaringTypeList);
		buildGraphicModelForType(declaringTypeNodeMap, classMap, interfaceMap, graph);
		
		//buildGraphicModelForOuterClass(declaringTypeList, graph);
		
		HashSet<ComplexType> outerClassList = retriveOuterClasses(declaringTypeList);
		if(outerClassList.size() < 2) return;
		
		HashMap<String, TypeNode> outerClassNodeMap = buildTypeHierarchyTreeStructure(outerClassList);
		buildGraphicModelForType(outerClassNodeMap, classMap, interfaceMap, graph);
		
	}
	
	protected void generateCallRelationOfEachCaleeMethod(
			clonepedia.model.ontology.Method m, Method callerMethodModel,
			HashMap<clonepedia.model.ontology.Method, Method> methodMap, TemplateGraph graph) {
		for (clonepedia.model.ontology.Method calledMethod : m
				.getCalleeMethod()) {
	
			if (methodMap.containsKey(calledMethod)) {
				Method calledMethodModel = getMethodOrCreateOneFromPool(calledMethod);
	
				if (calledMethodModel.getName() == null) {
					System.out.println();
				}
	
				Call call = Template_modelFactory.eINSTANCE.createCall();
	
				call.setCallerMethod(callerMethodModel);
				call.setCalleeMethod(calledMethodModel);
				
				callerMethodModel.getCalleeMethods().add(calledMethodModel);
	
				graph.getLinks().add(call);
	
			}
	
		}
	}

	private void buildGraphicModelForOuterClass(HashSet<ComplexType> declaringTypeList, TemplateGraph graph) {
		for(ComplexType declaringType: declaringTypeList){
			if(declaringType.isClass()){
				clonepedia.model.ontology.Class declaringClass = (clonepedia.model.ontology.Class)declaringType;
				if(declaringClass.isInnerClass()){
					clonepedia.model.ontology.Class outerClass = declaringClass.getOuterClass();
					
					Class declaringClassModel = getClassOrCreateOneFromPool(declaringClass);
					declaringClassModel.setName(declaringClass.getSimpleName());
					declaringClassModel.setFullName(declaringClass.getFullName());
					
					
					Class outerClassModel = getClassOrCreateOneFromPool(outerClass);
					outerClassModel.setName(outerClass.getSimpleName());
					outerClassModel.setFullName(outerClass.getFullName());
					
					/*IsInnerClassOf isInnerClassOf = Template_modelFactory.eINSTANCE.createIsInnerClassOf();
					isInnerClassOf.setInnerClass(declaringClassModel);
					isInnerClassOf.setOuterCLass(outerClassModel);
					isInnerClassOf = getIsInnerClassOfOrCreateOneFromPool(isInnerClassOf.toString(), isInnerClassOfMap);
					isInnerClassOf.setInnerClass(declaringClassModel);
					isInnerClassOf.setOuterCLass(outerClassModel);
					
					graph.getElements().add(declaringClassModel);
					graph.getElements().add(outerClassModel);
					graph.getLinks().add(isInnerClassOf);*/
				}
			}
		}
		
	}

	protected void buildGraphicModelForType(HashMap<String, TypeNode> typeNodeMap, HashMap<clonepedia.model.ontology.Class, Class> classMap, 
			HashMap<clonepedia.model.ontology.Interface, Interface> interfaceMap, TemplateGraph graph){
		ArrayList<TypeNode> topTypeNode = new ArrayList<TypeNode>();
		for(String key: typeNodeMap.keySet()){
			TypeNode node = typeNodeMap.get(key);
			if(node.getParents().size() == 0){
				topTypeNode.add(node);
			}
		}
		
		for(TypeNode parentNode: topTypeNode){
			recursivelyBuildTree(parentNode, classMap, interfaceMap, graph);
		}
	}
	
	private void recursivelyBuildTree(TypeNode parentNode, HashMap<clonepedia.model.ontology.Class, Class> classMap, 
			HashMap<clonepedia.model.ontology.Interface, Interface> interfaceMap, TemplateGraph graph){
		if(parentNode.getChildren().size() != 0){
			
			ComplexType parentComplexType = parentNode.getType();
			Type parentTypeModel;
			if(parentComplexType.isClass()){
				
				parentTypeModel = getClassOrCreateOneFromPool((clonepedia.model.ontology.Class)parentComplexType);
			}
			else{
				
				parentTypeModel = getInterfaceOrCreateOneFromPool((clonepedia.model.ontology.Interface)parentComplexType);
			}
			setModelValue(parentTypeModel, parentComplexType);
			/*parentTypeModel.setName(parentComplexType.getSimpleName());
			parentTypeModel.setFullName(parentComplexType.getFullName());*/
			
			if(parentComplexType.isClass() && ((clonepedia.model.ontology.Class)parentComplexType).isInnerClass()){
				clonepedia.model.ontology.Class outerClass = ((clonepedia.model.ontology.Class)parentComplexType).getOuterClass();
				Class outerClassModel = getClassOrCreateOneFromPool(outerClass);
				setModelValue(outerClassModel, outerClass);
				
				outerClassModel.getInnerClasses().add((Class)parentTypeModel);
				graph.getElements().add(outerClassModel);
			}
			else{
				graph.getElements().add(parentTypeModel);	
			}
			
			for(TypeNode childNode: parentNode.getChildren()){
				
				//boolean isChildExist = false;
				
				ComplexType childComplexType = childNode.getType();
				Type childTypeModel;
				if(childComplexType.isClass()){
					
					childTypeModel = getClassOrCreateOneFromPool((clonepedia.model.ontology.Class)childComplexType);
				}
				else{
					
					childTypeModel = getInterfaceOrCreateOneFromPool((clonepedia.model.ontology.Interface)childComplexType);
				}
				setModelValue(childTypeModel, childComplexType);
				/*childTypeModel.setName(childComplexType.getSimpleName());
				childTypeModel.setFullName(childComplexType.getFullName());*/
				
				if(childComplexType.isClass() && ((clonepedia.model.ontology.Class)childComplexType).getOuterClass() != null){
				}
				else{
					graph.getElements().add(childTypeModel);
				}
				
				
				decideRelationType(parentTypeModel, childTypeModel, graph);					
				
				recursivelyBuildTree(childNode, classMap, interfaceMap, graph);
			}
		}
	}
	
	private void decideRelationType(Type parentModel, Type childModel, TemplateGraph graph){
		if(parentModel instanceof Class && childModel instanceof Class){
			ExtendClass extendClass = Template_modelFactory.eINSTANCE.createExtendClass();
			extendClass.setSubClass((Class)childModel);
			extendClass.setSuperClass((Class)parentModel);
			
			extendClass = getExtendClassOrCreateOneFromPool(extendClass.toString(), this.extendClassMap);
			extendClass.setSubClass((Class)childModel);
			extendClass.setSuperClass((Class)parentModel);
			
			((Class)childModel).setSuperClass((Class)parentModel);
			
			graph.getLinks().add(extendClass);
		}
		else if(parentModel instanceof Interface && childModel instanceof Class){
			Implement implement = Template_modelFactory.eINSTANCE.createImplement();
			implement.setClass((Class)childModel);
			implement.getInterface().add((Interface)parentModel);
			
			implement = getImplementOrCreateOneFromPool(implement.toString(), this.implementMap);
			implement.setClass((Class)childModel);
			implement.getInterface().add((Interface)parentModel);
			
			((Class)childModel).getInterfaces().add(((Interface)parentModel));
			
			graph.getLinks().add(implement);
		}
		else if(parentModel instanceof Interface && childModel instanceof Interface){
			ExtendInterface extendInterface = Template_modelFactory.eINSTANCE.createExtendInterface();
			extendInterface.setSubInterface((Interface)childModel);
			extendInterface.getSuperInterface().add((Interface)parentModel);
			
			extendInterface = getExtendInterfaceOrCreateOneFromPool(extendInterface.toString(), this.extendInterfaceMap);
			extendInterface.setSubInterface((Interface)childModel);
			extendInterface.getSuperInterface().add((Interface)parentModel);
			
			((Interface)childModel).getSuperInterfaces().add(((Interface)parentModel));
			
			graph.getLinks().add(extendInterface);
		}
	}
	
	protected HashMap<String, TypeNode> buildDirectTypeHierarchyTreeStructure(HashSet<ComplexType> typeList){
		
		HashMap<String, TypeNode> typeNodeMap = new HashMap<String, TypeNode>();
		for(ComplexType type: typeList){
			TypeNode node = getTypeNodeOrCreateOne(type, typeNodeMap);
			for(ComplexType parentType: type.getDirectParents()){
				TypeNode parentNode = getTypeNodeOrCreateOne(parentType, typeNodeMap);
				
				parentNode.addChild(node);
				node.addParent(parentNode);
			}
		}
		
		return typeNodeMap;
	}
	
	protected HashMap<String, TypeNode> buildTypeHierarchyTreeStructure(HashSet<ComplexType> typeList){
		/**
		 * init
		 */
		Queue<ComplexType> typeQueue = new LinkedList<ComplexType>();
		HashMap<String, TypeNode> typeNodeMap = new HashMap<String, TypeNode>();
		for(ComplexType type: typeList){
			typeQueue.add(type);
			//typeNodeMap.put(type.getFullName(), new TypeNode(type));
		}
		
		/**
		 * start
		 */
		while(!typeQueue.isEmpty()){
			ComplexType type = typeQueue.poll();
			TypeNode node = getTypeNodeOrCreateOne(type, typeNodeMap);
			
			for(ComplexType parentType: type.getDirectParents()){
				TypeNode parentNode = getTypeNodeOrCreateOne(parentType, typeNodeMap);
				
				parentNode.addChild(node);
				node.addParent(parentNode);
				
				typeQueue.add(parentType);
			}
		}
		
		//filterOutParentNodesWithOnlyOneChild(typeNodeMap, typeList);
		
		return typeNodeMap;
	}
	
	/**
	 * post-process: filter out the parent nodes with only one child.
	 */
	private void filterOutParentNodesWithOnlyOneChild(HashMap<String, TypeNode> typeNodeMap, 
			HashSet<ComplexType> typeList){
		ArrayList<TypeNode> oneChildParents = new ArrayList<TypeNode>();
		for(String key: typeNodeMap.keySet()){
			TypeNode node = typeNodeMap.get(key);
			/**
			 * this means that the node is not a most foundamantal child node generating all the parents node
			 */
			if(!typeList.contains(node.getType())){
				if(node.getChildren().size() < 2){
					oneChildParents.add(node);
				}
			}
		}
		
		for(TypeNode parent: oneChildParents){
			typeNodeMap.remove(parent);
			
			TypeNode child = null;
			for(TypeNode node: parent.getChildren()){
				child = node;
			}
			
			while(child.getChildren().size() < 2){
				/**
				 * this means that the node is not a most foundamantal child node generating all the parents node
				 */
				if(!typeList.contains(child.getType())){
					typeNodeMap.remove(child);
					for(TypeNode node: child.getChildren()){
						child = node;
					}
				}				
			}
		}
	}
	
	
	private TypeNode getTypeNodeOrCreateOne(ComplexType type, HashMap<String, TypeNode> typeNodeMap){
		TypeNode typeNode = typeNodeMap.get(type.getFullName());
		if(null == typeNode){
			typeNode = new TypeNode(type);
			typeNodeMap.put(type.getFullName(), typeNode);
		}
		
		return typeNode;
	}
	
	private HashSet<ComplexType> retriveOuterClasses(HashSet<ComplexType> declaringTypeList){
		HashSet<ComplexType> outerClassList = new HashSet<ComplexType>();
		for(ComplexType type: declaringTypeList){
			if(type.isClass()){
				clonepedia.model.ontology.Class clazz = (clonepedia.model.ontology.Class)type;
				if(clazz.getOuterClass() != null){
					outerClassList.add(clazz.getOuterClass());
				}
			}
		}
		
		return outerClassList;
	} 

	protected Method getMethodOrCreateOneFromPool(clonepedia.model.ontology.Method method) {
		Method methodModel = this.methodMap.get(method);
		if (null == methodModel) {
			methodModel = Template_modelFactory.eINSTANCE.createMethod();
			this.methodMap.put(method, methodModel);
		}
		return methodModel;
	}

	protected Class getClassOrCreateOneFromPool(clonepedia.model.ontology.Class clazz) {
		
		Class classModel = this.classMap.get(clazz);
		if (null == classModel) {
			classModel = Template_modelFactory.eINSTANCE.createClass();
			classMap.put(clazz, classModel);
		}
		return classModel;
	}

	protected Interface getInterfaceOrCreateOneFromPool(clonepedia.model.ontology.Interface interf) {
		Interface interfaceModel = interfaceMap.get(interf);
		if (null == interfaceModel) {
			interfaceModel = Template_modelFactory.eINSTANCE.createInterface();
			interfaceMap.put(interf, interfaceModel);
		}
		return interfaceModel;
	}
	
	protected ExtendClass getExtendClassOrCreateOneFromPool(String name, HashMap<String, ExtendClass> map){
		ExtendClass extendClassModel = map.get(name);
		if(null == extendClassModel){
			extendClassModel = Template_modelFactory.eINSTANCE.createExtendClass();
			map.put(name, extendClassModel);
		}
		return extendClassModel;
	}
	
	protected ExtendInterface getExtendInterfaceOrCreateOneFromPool(String name, HashMap<String, ExtendInterface> map){
		ExtendInterface extendInterfaceModel = map.get(name);
		if(null == extendInterfaceModel){
			extendInterfaceModel = Template_modelFactory.eINSTANCE.createExtendInterface();
			map.put(name, extendInterfaceModel);
		}
		return extendInterfaceModel;
	}
	
	protected Implement getImplementOrCreateOneFromPool(String name, HashMap<String, Implement> map){
		Implement implement = map.get(name);
		if(null == implement){
			implement = Template_modelFactory.eINSTANCE.createImplement();
			map.put(name, implement);
		}
		return implement;
	}
	
	protected IsInnerClassOf getIsInnerClassOfOrCreateOneFromPool(String name, HashMap<String, IsInnerClassOf> map){
		IsInnerClassOf isInnerClassOf = map.get(name);
		if(null == isInnerClassOf){
			/*isInnerClassOf = Template_modelFactory.eINSTANCE.createIsInnerClassOf();
			map.put(name, isInnerClassOf);*/
		}
		return isInnerClassOf;
	}
}
