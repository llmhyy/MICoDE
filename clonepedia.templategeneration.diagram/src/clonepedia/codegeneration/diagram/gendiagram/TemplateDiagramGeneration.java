package clonepedia.codegeneration.diagram.gendiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import template_model.Class;
import template_model.Element;
import template_model.Interface;
import template_model.Method;
import template_model.TemplateGraph;
import template_model.Template_modelFactory;
import template_model.diagram.edit.commands.ClassCreateCommand;
import template_model.diagram.part.TypeNode;
import template_model.diagram.providers.Template_generationElementTypes;
import clonepedia.model.ontology.ComplexType;
import clonepedia.model.ontology.OntologicalElement;
import clonepedia.model.ontology.ProgrammingElement;
import clonepedia.model.ontology.Variable;
import clonepedia.model.template.Template;
import clonepedia.templategeneration.Activator;


public class TemplateDiagramGeneration extends DiagramGeneration{
	
	public template_model.TemplateGraph createModelAccordingToFeature(
			Template template, IProgressMonitor monitor) {
		TemplateGraph graph = Template_modelFactory.eINSTANCE.createTemplateGraph();

		int totalWork = template.getAbstractClassList().size();
	
		monitor.beginTask("drawing elements in graph", totalWork);
		
		clonepedia.model.ontology.Class[] array = sortArray(template.getAbstractClassList());
		for (clonepedia.model.ontology.Class clazz: array) {
			
			Class classModel = getClassOrCreateOneFromPool(clazz);
			setModelValue(classModel, clazz);
			
			for(clonepedia.model.ontology.Method method: clazz.getMethods()){
				Method methodModel = getMethodOrCreateOneFromPool(method);
				setModelValue(methodModel, method);
				
				classModel.getMethods().add(methodModel);
				methodModel.setOwnerType(classModel);
				methodModel.setReturnType(method.getReturnType().getFullName());
				for(Variable v: method.getParameters()){
					String param = v.getVariableType() + ":" + v.getVariableName();
					methodModel.getParameters().add(param);
				}
			}
			
			for(clonepedia.model.ontology.Class innerClass: clazz.getInnerClassList()){
				Class innerClassModel = getClassOrCreateOneFromPool(innerClass);
				setModelValue(innerClassModel, innerClass);
				
				classModel.getInnerClasses().add(innerClassModel);
			}
			
			
			graph.getElements().add(classModel);
			
			HashSet<ComplexType> declaringTypeList = new HashSet<ComplexType>();
			declaringTypeList.add(clazz);
			
			HashMap<String, TypeNode> declaringTypeNodeMap = buildDirectTypeHierarchyTreeStructure(declaringTypeList);
			buildGraphicModelForType(declaringTypeNodeMap, classMap, interfaceMap, graph);
			
			monitor.worked(1);
			if(monitor.isCanceled())return graph;
		}

		for (clonepedia.model.ontology.Method callerMethod : template.getAbstractMethodList()) {
			Method callerMethodModel = getMethodOrCreateOneFromPool(callerMethod);
			generateCallRelationOfEachCaleeMethod(callerMethod, callerMethodModel, methodMap, graph);
		}
		
		//storeElementMappingRelation();
		
		return graph;
	}
	
	private clonepedia.model.ontology.Class[] sortArray(ArrayList<clonepedia.model.ontology.Class> list){
		int index = 0;
		clonepedia.model.ontology.Class[] array = new clonepedia.model.ontology.Class[list.size()];
		
		for(clonepedia.model.ontology.Class clazz: list){
			if(clazz.isInnerClass()){
				array[index++] = clazz;				
			}
		}
		
		for(clonepedia.model.ontology.Class clazz: list){
			if(!clazz.isInnerClass()){
				array[index++] = clazz;				
			}
		}
		
		return array;
	}
	
	/*private void storeElementMappingRelation(){
		for(clonepedia.model.ontology.Class clazz: this.classMap.keySet()){
			//Class classModel = this.classMap.get(clazz);
			Activator.classMap.put(clazz.getFullName(), clazz);
		}
		
		for(clonepedia.model.ontology.Interface interf: this.interfaceMap.keySet()){
			//Interface interfaceModel = this.interfaceMap.get(interf);
			Activator.interfaceMap.put(interf.getFullName(), interf);
		}
		
		for(clonepedia.model.ontology.Method method: this.methodMap.keySet()){
			//Method methodModel = this.methodMap.get(method);
			Activator.methodMap.put(method.getFullName(), method);
		}
	}*/
}
