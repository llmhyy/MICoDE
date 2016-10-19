package clonepedia.templategeneration.routine;

import java.util.ArrayList;

import template_model.Call;
import template_model.Class;
import template_model.Element;
import template_model.ExtendClass;
import template_model.ExtendInterface;
import template_model.Field;
import template_model.Implement;
import template_model.Interface;
import template_model.Method;
import template_model.TemplateGraph;
import template_model.Template_modelFactory;
import template_model.Type;
import clonepedia.codegeneration.diagram.bean.AbstractField;
import clonepedia.codegeneration.diagram.bean.AbstractMethod;
import clonepedia.codegeneration.diagram.bean.AbstractType;
import clonepedia.codegeneration.diagram.bean.IElement;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.Parameter;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;

public class DesignDiagramGenerator {

	public TemplateGraph createModelForDesign(TemplateDesign design) {
		TemplateGraph graph = Template_modelFactory.eINSTANCE.createTemplateGraph();
		
		for(Multiset set: design.getDesign()){
			if(set.isTypeSet()){
				AbstractType abType = (AbstractType) set.getAbstractElement();
				Type typeModel = createTypeModel(graph, abType, set);
				
				graph.getElements().add(typeModel);
				
				createTypeContent(graph, typeModel, set);
			}
		}
		
		buildCallRelationOnGraph(graph, design);
		
		return graph;
	}
	
	private void buildCallRelationOnGraph(TemplateGraph graph,
			TemplateDesign design) {
		
		ArrayList<Multiset> methodSets = new ArrayList<>();
		for(Multiset set: design.getDesign()){
			collectMethodSet(methodSets, set);
		}
		
		for(Multiset caller: methodSets){
			Method callerMethod = findMethodModel(graph, caller.getId());
			for(Multiset callee: caller.getCalleeSets()){
				Method calleeMethod = findMethodModel(graph, callee.getId());
				
				if(callerMethod != null && calleeMethod != null){
					createCallRelation(graph, callerMethod, calleeMethod);
				}
			}
		}
	}
	
	private void createCallRelation(TemplateGraph graph, Method callerMethod,
			Method calleeMethod) {
		Call call = Template_modelFactory.eINSTANCE.createCall();
		call.setCallerMethod(callerMethod);
		call.setCalleeMethod(calleeMethod);
		
		graph.getLinks().add(call);
	}

	private Method findMethodModel(TemplateGraph graph, String id) {
		for(Element element: graph.getElements()){
			if(element instanceof Type){
				Type type = (Type)element;
				ArrayList<Method> methodList = findAllMethodInType(type);
				for(Method method: methodList){
					if(method.getDescription().equals(id)){
						return method;					
					}					
				}
			}
		}
		return null;
	}
	
	private ArrayList<Method> findAllMethodInType(Type type){
		ArrayList<Method> methodList = new ArrayList<>();
		for(Method methodModel: type.getMethods()){
			methodList.add(methodModel);
		}
		
		if(type instanceof Class){
			Class clazz = (Class)type;
			for(Class innerClass: clazz.getInnerClasses()){
				ArrayList<Method> methods = findAllMethodInType(innerClass);
				methodList.addAll(methods);
			}
		}
		
		return methodList;
	}

	private void collectMethodSet(ArrayList<Multiset> methodSets, Multiset set) {
		if(set.isMethodSet()){
			methodSets.add(set);
		}
		else if(set.isTypeSet()){
			for(Multiset subSet: set.getSubMultisetList()){
				collectMethodSet(methodSets, subSet);
			}
		}
	}

	private void createTypeContent(TemplateGraph graph, Type type, Multiset parentSet){
		
		for(Multiset set: parentSet.getSubMultisetList()){
			if(set.isFieldSet()){
				Field fieldModel = createFieldModel(type, set);
				type.getFields().add(fieldModel);
			}
			else if(set.isMethodSet()){
				Method methodModel = createMethodModel(type, set);
				type.getMethods().add(methodModel);
			}
			else if(set.isTypeSet()){
				AbstractType abType = (AbstractType) set.getAbstractElement();
				
				if(abType.isClass()){
					Type typeModel = createTypeModel(graph, abType, set);
					
					createTypeContent(graph, typeModel, set);
					
					Class clazz = (Class)type;
					clazz.getInnerClasses().add((Class)typeModel);					
				}
			}
			
		}
	}
	
	private Method createMethodModel(Type ownerType, Multiset methodSet){
		Method methodModel = Template_modelFactory.eINSTANCE.createMethod();
		AbstractMethod abMethod = (AbstractMethod) methodSet.getAbstractElement();
		
		methodModel.setName(abMethod.getName());
		methodModel.setOwnerType(ownerType);
		methodModel.setReturnType(abMethod.getReturnType());
		for(Parameter parameter: abMethod.getParameters()){
			methodModel.getParameters().add(parameter.getParameterType() + ":" + parameter.getParameterName());
		}
		methodModel.setDescription(methodSet.getId());
		
		return methodModel;
	}
	
	private Field createFieldModel(Type ownerType, Multiset fieldSet){
		Field fieldModel = Template_modelFactory.eINSTANCE.createField();
		AbstractField abField = (AbstractField) fieldSet.getAbstractElement();
		
		fieldModel.setName(abField.getName());
		fieldModel.setType(abField.getType());
		fieldModel.setDescription(fieldSet.getId());
		fieldModel.setOwnerType(ownerType);
		
		return fieldModel;
	}

	private Type createTypeModel(TemplateGraph graph, AbstractType abType, Multiset set){
		if(abType.isClass()){
			Class classModel = Template_modelFactory.eINSTANCE.createClass();
			classModel.setName(abType.getSimpleName());
			//classModel.setFullName(abType.getName());
			classModel.setDescription(set.getId());
			classModel.setFullName(abType.getPackageName() + "." + abType.getSimpleName());
			
			if(abType.getSimpleName().contains("Main")){
				System.currentTimeMillis();
			}
			
			for(IElement e: set.getCorrespondingSet()){
				TypeWrapper wrapper = (TypeWrapper)e;
				classModel.getSupportingElements().add(wrapper.getPackageName() + wrapper.getName());
			}
			
			Class superClassModel = checkSuperClass(graph, abType);
			
			if(superClassModel != null){
				createExtendClassRelation(graph, classModel, superClassModel);
			}
			
			ArrayList<Interface> interfaceList = checkInterfaces(graph, abType);
			for(Interface interfaceModel: interfaceList){
				createImplementRelation(graph, classModel, interfaceModel);
			}
			
			return classModel;
		}
		else{
			Interface interfaceModel = Template_modelFactory.eINSTANCE.createInterface();
			interfaceModel = Template_modelFactory.eINSTANCE.createInterface();
			interfaceModel.setName(abType.getName());
			for(IElement e: set.getCorrespondingSet()){
				TypeWrapper wrapper = (TypeWrapper)e;
				interfaceModel.getSupportingElements().add(wrapper.getName());
			}
			
			ArrayList<Interface> interfaceList = checkInterfaces(graph, abType);
			for(Interface interfaceMod: interfaceList){
				createExtendInterfaceRelation(graph, interfaceModel, interfaceMod);
			}
			
			return interfaceModel;
		}
	}
	
	private ArrayList<Interface> checkInterfaces(TemplateGraph graph, AbstractType abType){
		ArrayList<Interface> interfaceList = new ArrayList<>();
		
		if(abType.getInterfaces().size() != 0){
			for(String interfName: abType.getInterfaces()){
				Type superType = findSuperType(graph, interfName);
				
				if(superType == null){
					superType = Template_modelFactory.eINSTANCE.createInterface();
					
					String superName = interfName;
					String simpleName = superName.substring(superName.lastIndexOf(".")+1, superName.length());
					superType.setName(simpleName);
					superType.setFullName(superName);
					superType.setIsComplete(true);
					superType.setDescription("-1");
					
					graph.getElements().add(superType);
				}
				
				Interface interf = (Interface)superType;
				interfaceList.add(interf);
			}
		}
		
		return interfaceList;
	}
	
	private Class checkSuperClass(TemplateGraph graph, AbstractType abType){
		if(abType.getSuperClass() != null && abType.getSuperClass().length() != 0){
			Type superType = findSuperType(graph, abType.getSuperClass());
			if(superType == null){
				superType = Template_modelFactory.eINSTANCE.createClass();
				
				String superName = abType.getSuperClass();
				String simpleName = superName.substring(superName.lastIndexOf(".")+1, superName.length());
				superType.setName(simpleName);
				superType.setFullName(superName);
				superType.setIsComplete(true);
				superType.setDescription("-1");
				
				graph.getElements().add(superType);
			}
			return (Class)superType;
		}
		return null;
	}
	
	private void createExtendInterfaceRelation(TemplateGraph graph, Interface subInterface, Interface superInterface){
		ExtendInterface extendInterface = Template_modelFactory.eINSTANCE.createExtendInterface();
		extendInterface.setSubInterface(subInterface);
		extendInterface.getSuperInterface().add(superInterface);
		
		subInterface.getSuperInterfaces().add(superInterface);
		graph.getLinks().add(extendInterface);
	}

	private void createImplementRelation(TemplateGraph graph, Class classModel, Interface interfaceModel){
		Implement implement = Template_modelFactory.eINSTANCE.createImplement();
		implement.setClass(classModel);
		implement.getInterface().add(interfaceModel);
		classModel.getInterfaces().add(interfaceModel);
		
		graph.getLinks().add(implement);
	}
	
	private void createExtendClassRelation(TemplateGraph graph, Class subClass, Class superClass){
		ExtendClass extendClass = Template_modelFactory.eINSTANCE.createExtendClass();
		extendClass.setSubClass(subClass);
		extendClass.setSuperClass(superClass);
		
		graph.getLinks().add(extendClass);
	}
	
	private Type findSuperType(TemplateGraph graph, String qualifiedName){
		for(Element element: graph.getElements()){
			if(element instanceof Type){
				Type superType = (Type)element;
				if(superType.getFullName() != null && superType.getFullName().equals(qualifiedName)){
					return superType;
				}
			}
		}
		
		return null;
	}
	
}
