package clonepedia.templategeneration.algorithm;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;

import clonepedia.model.template.CandidateTemplate;
import clonepedia.model.template.CandidateTemplateList;
import clonepedia.model.template.SubCandidateTemplate;
import clonepedia.model.template.TemplateMethodGroup;
import clonepedia.util.Settings;
import datamining.cluster.IClusterable;
import datamining.cluster.NormalCluster;
import datamining.cluster.hierarchy.HierarchyClusterAlgorithm;
/**
 * 
 * renaming, the concept is as follows:
 * A TMG contains several corresponding methods
 * A SubCandidateTemplate contains TMGs with similar locations
 * A CandidateTemplate contains several SubCaididateTemplate with call relations
 * 
 * @author linyun
 *
 */
public class CandidateTemplateBuilder {

	private ArrayList<TemplateMethodGroup> methodGroupList;
	private CandidateTemplateList candidateTemplateList = new CandidateTemplateList();

	public CandidateTemplateBuilder(ArrayList<TemplateMethodGroup> methodGroupList) {
		this.methodGroupList = methodGroupList;
	}

	public void generateTemplateFeatures(IProgressMonitor monitor) {
		ArrayList<SubCandidateTemplate> subCandidateTemplateList = clusterTMGByLocation();
		
		monitor.worked(20);
		System.out.println("cluster TMG by location: " + subCandidateTemplateList.size());
		
		buildCallingRelationsForTemplateFeatures(subCandidateTemplateList);
		
		monitor.worked(10);
		System.out.println("build calling relation for sub canidate template");
		
		//connectSubCandidateTemplatesByCallingRelation_(subCandidateTemplateList);
		connectSubCandidateTemplatesByCallingRelation(subCandidateTemplateList);
		
		monitor.worked(10);
		System.out.println("connect sub candidate template: " + candidateTemplateList.size());
	}
	
	private ArrayList<SubCandidateTemplate> clusterTMGByLocation(){
		
		ArrayList<SubCandidateTemplate> list = new ArrayList<SubCandidateTemplate>();
		
		TemplateMethodGroup[] groupList = this.methodGroupList.toArray(new TemplateMethodGroup[0]);
		
		for(int i=0; i<groupList.length; i++){
			if(groupList[i].toString().contains("main")){
				System.out.print("");
			}
		}
		
		HierarchyClusterAlgorithm algorithm = new HierarchyClusterAlgorithm(groupList, 
				Settings.thresholdDistanceForTMGLocationClustering, HierarchyClusterAlgorithm.SingleLinkage);
		try {
			ArrayList<NormalCluster> clusters = algorithm.doClustering();
			
			//int count = 0;
			for(NormalCluster cluster: clusters){
				
				/*if(cluster.toString().contains("main")){
					System.out.print("");
				}*/
				
				SubCandidateTemplate subCandidateTemplate = new SubCandidateTemplate();
				
				for(IClusterable clusterable: cluster){
					TemplateMethodGroup tmg = (TemplateMethodGroup)clusterable;
					subCandidateTemplate.addTemplateMethodGroup(tmg);
					tmg.setSubCandidateTemplate(subCandidateTemplate);
					//tmg.mark();
				}
				
				list.add(subCandidateTemplate);
				//count += cluster.size();
			}
			
			//System.out.println(count);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	private void buildCallingRelationsForTemplateFeatures(ArrayList<SubCandidateTemplate> list){
		for(SubCandidateTemplate callerTFG: list){
			for(TemplateMethodGroup callerTMG: callerTFG.getTemplateMethodGroupList()){
				for(TemplateMethodGroup calleeTMG: callerTMG.getCalleeGroup()){
					SubCandidateTemplate calleeTFG = calleeTMG.getSubCandiateTemplate();
					
					if(calleeTFG == null){
						System.out.print("");
					}
					
					if(calleeTFG != null && !calleeTFG.equals(callerTFG)){
						callerTFG.addCalleeTFG(calleeTFG);
						calleeTFG.addCallerTFG(callerTFG);
					}
				}
			}
		}
	}
	
	/**
	 * Just think of such a problem as graph traversing. Given one vertex v in graph G, we can get a set of
	 * nodes V by DFS. Let us call such a vertex set as depth set (Not that depth set can only be achieved by
	 * traversing only one vertex in G). If graph G has n vertexes, thus, there are at most n depth sets for G. 
	 * We call a depth set independent if and only if it will no longer be a depth set when we add one other 
	 * vertex into the set. Such a method is written for compute all the independent depth set in a G.
	 * 
	 * Tempalte Feature Group (TFG) is for node.
	 * Calling Relation is for edge.
	 * @param tfgList
	 */
	private void connectSubCandidateTemplatesByCallingRelation(ArrayList<SubCandidateTemplate> list){

		for(SubCandidateTemplate sct: list){
			sct.buildReachableList();
		}
		
		for(SubCandidateTemplate sct: list){
			
			/*if(sct.toString().contains("main")){
				System.out.println();
			}*/
			
			if(!sct.isVisited() && sct.isValidateNode()){
				sct.buildDepthSet();
				
				CandidateTemplate ct = new CandidateTemplate();
				for(SubCandidateTemplate sct0: sct.getDepthSet()){
					ct.add(sct0);
				}
				this.candidateTemplateList.add(ct);
			}
		}
	}
	
	/**
	 * Old method, connect sub-candidate templates with all its caller and callee.
	 * @param list
	 */
	private void connectSubCandidateTemplatesByCallingRelation_(ArrayList<SubCandidateTemplate> list){

		SubCandidateTemplate[] groupList = list.toArray(new SubCandidateTemplate[0]);

		for (int i = 0; i < groupList.length; i++) {
			SubCandidateTemplate subTemplate = groupList[i];
			// the group has not been visited
			if (!subTemplate.isVisited()) {
				CandidateTemplate candidateTemplate = new CandidateTemplate();
				candidateTemplate.add(subTemplate);

				gatherNeighbourTMGs(candidateTemplate, subTemplate);
				//gatherCallerTMGs(features, tfg);

				this.candidateTemplateList.add(candidateTemplate);
			}
		}
	}
	
	private void gatherNeighbourTMGs(CandidateTemplate features,
			SubCandidateTemplate group) {

		if (features.size() == 0/*group.getCalleeSubCT().size() == 0 && group.getCallerSubCT().size() == 0*/) {
			return;
		} else {
			for (SubCandidateTemplate calleeGroup : group.getCalleeSubCT()) {
				calleeGroup.mark();
				if (!features.contains(calleeGroup)) {
					features.add(calleeGroup);
					gatherNeighbourTMGs(features, calleeGroup);
				}
			}
			
			for (SubCandidateTemplate callerGroup : group.getCallerSubCT()) {
				callerGroup.mark();
				if (!features.contains(callerGroup)) {
					features.add(callerGroup);
					gatherNeighbourTMGs(features, callerGroup);
				}
			}
		}
	}
	
	/*private void gatherCalleeTMGs(TFGList features, TemplateFeatureGroup group) {

		if (features.size() == 0) {
			return;
		} else {
			for (TemplateFeatureGroup callerGroup : group.getCallerTFG()) {
				callerGroup.mark();
				if (!features.contains(callerGroup)) {
					features.add(callerGroup);
					gatherCalleeTMGs(features, callerGroup);
				}
			}
		}
	}*/
	
	/*private void gatherCallerTMGs(TFGList features,
			TemplateFeatureGroup group) {

		if (features.size() == 0) {
			return;
		} else {
			for (TemplateFeatureGroup callerGroup : group.getCallerTFG()) {
				callerGroup.mark();
				if (!features.contains(callerGroup)) {
					features.add(callerGroup);
					gatherCallerTMGs(features, callerGroup);
				}
			}
		}
	}*/
	
	private void gatherCalleeGroups(SubCandidateTemplate feature,
			TemplateMethodGroup group) {

		if (group.getCalleeGroup().size() == 0) {
			return;
		} else {
			for (TemplateMethodGroup calleeGroup : group.getCalleeGroup()) {
				calleeGroup.mark();
				if (!feature.contains(calleeGroup)) {
					feature.addTemplateMethodGroup(calleeGroup);
					gatherCalleeGroups(feature, calleeGroup);
				}
			}
		}
	}

	private void gatherCallerGroups(SubCandidateTemplate feature,
			TemplateMethodGroup group) {
		if (group.getCallerGroup().size() == 0) {
			return;
		} else {
			for (TemplateMethodGroup callerGroup : group.getCallerGroup()) {
				callerGroup.mark();
				if (!feature.contains(group)) {
					feature.addTemplateMethodGroup(callerGroup);
					gatherCallerGroups(feature, callerGroup);
				}
			}
		}
	}

	private ArrayList<SubCandidateTemplate> generateTemplateFeatureByDirectlyCallingRelation() {
		// contructCallGraph();

		ArrayList<SubCandidateTemplate> templateFeatures = new ArrayList<SubCandidateTemplate>();

		TemplateMethodGroup[] groupList = methodGroupList
				.toArray(new TemplateMethodGroup[0]);

		for (int i = 0; i < groupList.length; i++) {
			TemplateMethodGroup tmg = groupList[i];
			// the group has not been visited
			if (!tmg.isVisited()) {
				SubCandidateTemplate feature = new SubCandidateTemplate();
				feature.addTemplateMethodGroup(tmg);

				gatherCalleeGroups(feature, tmg);
				gatherCallerGroups(feature, tmg);

				templateFeatures.add(feature);
			}
		}

		return templateFeatures;
	}

	

	public CandidateTemplateList getCandidateTemplateList() {
		return candidateTemplateList;
	}

	public void setCandidateTemplateList(CandidateTemplateList featureGroups) {
		this.candidateTemplateList = featureGroups;
	}
}
