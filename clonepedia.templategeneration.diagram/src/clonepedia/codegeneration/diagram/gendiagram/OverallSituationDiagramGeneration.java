package clonepedia.codegeneration.diagram.gendiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.core.runtime.IProgressMonitor;

import template_model.Call;
import template_model.Class;
import template_model.Declare;
import template_model.ExtendClass;
import template_model.ExtendInterface;
import template_model.Implement;
import template_model.Interface;
import template_model.IsInnerClassOf;
import template_model.Method;
import template_model.TMG;
import template_model.TemplateGraph;
import template_model.Template_modelFactory;
import template_model.Type;
import template_model.diagram.part.TypeNode;
import clonepedia.model.ontology.ComplexType;
import clonepedia.model.template.CandidateTemplate;
import clonepedia.model.template.SubCandidateTemplate;
import clonepedia.model.template.TemplateMethodGroup;

public class OverallSituationDiagramGeneration extends DiagramGeneration{
	
	public template_model.TemplateGraph createModelAccordingToFeature(
			CandidateTemplate tfgList, IProgressMonitor monitor) {
		TemplateGraph graph = Template_modelFactory.eINSTANCE.createTemplateGraph();

		int totalWork = 0;
		for (SubCandidateTemplate group : tfgList) {
			totalWork += group.getTemplateMethodGroupList().size();
		}
	
		monitor.beginTask("drawing elements in graph", totalWork);
		for (SubCandidateTemplate locationGroup : tfgList) {
			for (TemplateMethodGroup tmg : locationGroup.getTemplateMethodGroupList()) {
				
				
				//TMG tmgModel = Template_modelFactory.eINSTANCE.createTMG();
				
				HashSet<ComplexType> declaringTypeList = new HashSet<ComplexType>();
				
				for (clonepedia.model.ontology.Method m : tmg.getMethods()) {
					ComplexType declaringType = m.getOwner();
					declaringTypeList.add(declaringType);
					
					Method methodModel = getMethodOrCreateOneFromPool(m);
					methodModel.setName(m.getSimpleElementName());
					methodModel.setFullName(m.getFullName());
					//tmgModel.getMethods().add(methodModel);

					generateDeclaringType(m, methodModel, classMap, interfaceMap, graph);
				}
				
				generateRelationsForDeclaringTypes(declaringTypeList, classMap, interfaceMap, graph);
				
				//graph.getTemplateMethodGroups().add(tmgModel);
				
				monitor.worked(1);
				if(monitor.isCanceled())return graph;
				
			}
		}

		for (SubCandidateTemplate locationGroup : tfgList) {
			for (TemplateMethodGroup tmg : locationGroup.getTemplateMethodGroupList()) {
				for (clonepedia.model.ontology.Method m : tmg.getMethods()) {
					Method callerMethodModel = getMethodOrCreateOneFromPool(m);
					generateCallRelationOfEachCaleeMethod(m, callerMethodModel, methodMap, graph);
				}
			}
		}
		return graph;
	}

	

	
	
	public template_model.TemplateGraph createTestModel(
			CandidateTemplate tfgList, IProgressMonitor monitor) {
		TemplateGraph graph = Template_modelFactory.eINSTANCE.createTemplateGraph();
		
		Class outerClass = Template_modelFactory.eINSTANCE.createClass();
		Class innerClass = Template_modelFactory.eINSTANCE.createClass();
		Method method = Template_modelFactory.eINSTANCE.createMethod();
		
		outerClass.getInnerClasses().add(innerClass);
		innerClass.getMethods().add(method);
		graph.getElements().add(outerClass);
		
		return graph;
	}
}
