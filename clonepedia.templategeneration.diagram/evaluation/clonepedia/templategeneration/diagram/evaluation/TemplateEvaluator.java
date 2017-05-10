package clonepedia.templategeneration.diagram.evaluation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import clonepedia.codegeneration.diagram.bean.DesignList;
import clonepedia.codegeneration.diagram.bean.IElement;
import clonepedia.codegeneration.diagram.bean.MemberWrapper;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import clonepedia.codegeneration.diagram.bean.TemplateInstance;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;
import clonepedia.templategeneration.diagram.util.AutoGenCTUtil;
import clonepedia.templategeneration.routine.DesignBuilder;
import clonepedia.templategeneration.routine.DesignXMLReader;
import edu.ntu.cltk.algo.HungarianAlgo;
import edu.ntu.cltk.algo.MunkresAlgo;
import edu.ntu.cltk.file.FileUtil;
import template_model.diagram.util.AutoGenCTSettings;

public class TemplateEvaluator {
	
	class Accuracy{
		double precision;
		double recall;
		
		@Override
		public String toString(){
			return String.format("%.2f %.2f", precision, recall);
		}
	}
	
	public void evaluate(){
		DesignList designList = DesignXMLReader.xml2design(AutoGenCTSettings.retrieveTemplateFileLocation());
		for(int i = 0 ; i < designList.size(); i++){
			TemplateDesign design = designList.get(i);
			List<TemplateInstance> instanceList = design.resolveTemplateInstance();
			
			if (instanceList.size() < 2){
				//System.out.println("");
				continue;
			}
			
			for(TemplateInstance instance: instanceList){
//				DesignList dl = DesignXMLReader.xml2design(AutoGenCTSettings.retrieveTemplateFileLocation());
				ArrayList<Multiset> newMaterials = designList.get(i).getMaterials();
				
				ArrayList<Multiset> remaining = filterOneInstance(newMaterials, instance, instanceList);
				
				DesignList newDesign = new DesignBuilder().buildDesign(remaining);
				
				System.out.println("=============================");
				FileUtil.writeFile("acc.txt", "=============================", "a");
				for (TemplateDesign td : newDesign){
					FileUtil.writeFile("acc.txt", td.toString() + " | " + instance.toString(), "a");
					System.out.println(td.toString() + " | " + instance.toString());
					Accuracy accuracy = compare(td, instance);
					
					System.out.println(accuracy);
					FileUtil.writeFile("acc.txt", accuracy.toString(), "a");
				}
				
			}
		}
	}

	private ArrayList<Multiset> filterOneInstance(ArrayList<Multiset> allSets, TemplateInstance ti, List<TemplateInstance> allInstances){
		
		//Get all unnecessary TypeWrapper elements
		HashSet<TypeWrapper> removed = new HashSet<TypeWrapper>(ti.getTopTypeWrapperList());
		for (TemplateInstance t : allInstances){
			if (t.equals(ti))	continue;
			removed.removeAll(t.getTopTypeWrapperList());
		}
		
		for (Multiset set : allSets){
			ArrayList<IElement> pendingElements = new ArrayList<IElement>();
			for (IElement ele : set.getCorrespondingSet()){
				if (ele instanceof TypeWrapper){
					TypeWrapper tw = (TypeWrapper) ele;
					for (TypeWrapper revWrapper : removed){
						if (revWrapper.equals(tw)){
							pendingElements.add(ele);
							break;
						}
					}
				}
			}
			set.getCorrespondingSet().removeAll(pendingElements);
		}
		return allSets;
	}
	
	private Accuracy compare(TemplateDesign newDesign, TemplateInstance instance) {
		Accuracy acc = new Accuracy();
		double commonality = countCommonality(newDesign.getMaterials(), instance.getTopTypeWrapperList());
		int numInstance = 0;
		for (TypeWrapper tw : instance.getTopTypeWrapperList()){
			numInstance += tw.getMembers().size();
		}
		int numMultiset = 0;
		for (Multiset ms : newDesign.getMaterials()){
			numMultiset += ms.getSubMultisetList().size();
		}
		if (instance.getTopTypeWrapperList().size() != 0){
			acc.precision = commonality / numInstance;
		}else{
			acc.precision = 0;
		}
		if (newDesign.getMaterials().size() != 0){
			acc.recall = commonality / numMultiset;
		}else{
			acc.recall = 0;
		}
		return acc;
	}

	private TemplateDesign summarize(List<TemplateInstance> otherInstanceList) {
		for (TemplateInstance ti : otherInstanceList){
			for (TypeWrapper tw : ti.getTopTypeWrapperList()){
				System.out.println(tw.toString());
			}
		}
		return null;
	}

	/**
	 * Calculate the similarity between materials and types
	 * @param materials
	 * @param types
	 * @return
	 */
	private double countCommonality(List<Multiset> materials, List<TypeWrapper> types){
		Integer[][] matrix = buildSimilarityMatrix(materials, types);
		double[][] dMatrix = new double[materials.size()][types.size()];
		for (int i = 0; i < materials.size(); i++){
			for (int j = 0; j < types.size(); j++){
				dMatrix[i][j] = matrix[i][j] * 1.0;
			}
		}
		MunkresAlgo ma = new MunkresAlgo();
		return ma.maximalAssignment(dMatrix);		
	}
	/**
	 * Build the similarity matrix for TemplateDesign and TemplateInstance
	 * @param materials
	 * @param types
	 * @return
	 */
	private Integer[][] buildSimilarityMatrix(List<Multiset> materials, List<TypeWrapper> types){
		Integer[][] matrix = new Integer[materials.size()][types.size()];
		for (int i = 0 ; i < materials.size(); i++){
			Multiset ms = materials.get(i);
			for (int j = 0 ; j < types.size(); j++){
				TypeWrapper tw = types.get(j);
				//First check if the top IElement is similar
				double typeSimilarity = 1/AutoGenCTSettings.retrieveClusteringDistanceThreshold();
				if (ms.computeSimilarity(tw) >= typeSimilarity){	
					matrix[i][j] = calSimilarity(ms, tw);					
				}else{
					matrix[i][j] = 0;
				}
			}
		}
		return matrix;
	}
	/**
	 * Build the similarity matrix for Multiset and TypeWrapper
	 * @param m
	 * @param tw
	 * @return
	 */
	private Integer[][] buildSimilarityMatrix(Multiset m, TypeWrapper tw){
		Integer[][] matrix = new Integer[m.getSubMultisetList().size()][tw.getMembers().size()];
		for (int i = 0 ; i < m.getSubMultisetList().size(); i++){
			Multiset sub = m.getSubMultisetList().get(i);
			for (int j = 0 ; j < tw.getMembers().size(); j++){
				IElement ie = tw.getMembers().get(j);
				if (sub.computeSimilarity(ie) >= AutoGenCTUtil.getThreashold(ie)){
					matrix[i][j] = 1;
				}else{
					matrix[i][j] = 0;
				}
			}
		}
		return matrix;
	}
	/**
	 * Calculate the commonality between one Multiset and one TypeWrapper
	 * @param m
	 * @param tw
	 * @return
	 */
	private int calSimilarity(Multiset m, TypeWrapper tw){
		Integer[][] matrix = buildSimilarityMatrix(m, tw);
		HungarianAlgo<Integer> ha = new HungarianAlgo<Integer>();
		int [] matching = ha.maximalBipartiteGraphMatching(matrix);
		int cnt = 0;
		for (int mat : matching){
			if (mat != -1)
				cnt++;
		}
		return cnt;
	}
	
}
