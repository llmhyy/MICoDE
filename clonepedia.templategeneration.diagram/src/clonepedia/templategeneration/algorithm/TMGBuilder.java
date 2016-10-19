package clonepedia.templategeneration.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import org.eclipse.core.runtime.IProgressMonitor;

import clonepedia.businessdata.OntologicalDataFetcher;
import clonepedia.java.CloneInformationExtractor;
import clonepedia.java.CompilationUnitPool;
import clonepedia.java.model.CloneSetWrapper;
import clonepedia.model.ontology.CloneInstance;
import clonepedia.model.ontology.CloneSet;
import clonepedia.model.ontology.CloneSets;
import clonepedia.model.ontology.CounterRelationGroup;
import clonepedia.model.ontology.InstanceElementRelation;
import clonepedia.model.ontology.Method;
import clonepedia.model.ontology.OntologicalElement;
import clonepedia.model.template.TemplateMethodGroup;
import clonepedia.util.Settings;
import datamining.cluster.IClusterable;
import datamining.cluster.NormalCluster;
import datamining.cluster.hierarchy.HierarchyClusterAlgorithm;

/**
 * This class do three things 
 * 1) splitting template method group from clone set, 
 * 2) deriving template method group from counter difference and 
 * 3) building calling relation between template method groups.
 * 
 * @author linyun
 * 
 */
public class TMGBuilder {

	private CloneSets sets;
	private HashSet<TemplateMethodGroup> methodGroupList = new HashSet<TemplateMethodGroup>();

	public TMGBuilder(CloneSets sets) {
		this.sets = sets;
	}

	public void build(IProgressMonitor monitor) {
		
		init();
		
		ArrayList<TemplateMethodGroup> list = new ArrayList<TemplateMethodGroup>();
		for(TemplateMethodGroup tmg: methodGroupList){
			
			/*if(tmg.toString().contains("main") || tmg.toString().contains("ApplicationModel")){
				System.out.println();
			}*/
			
			list.add(tmg);
		}
		
		increaseTMGByCounterDiff();
		
		list = new ArrayList<TemplateMethodGroup>();
		for(TemplateMethodGroup tmg: methodGroupList){
			list.add(tmg);
			
			/*if(tmg.toString().contains("main") || tmg.toString().contains("ApplicationModel")){
				System.out.println();
			}*/
		}
		
		sanitize();
		
		list = new ArrayList<TemplateMethodGroup>();
		for(TemplateMethodGroup tmg: methodGroupList){
			list.add(tmg);
		}
		
		buildCallingGraph();
	}
	
	private void buildCallingGraph(){
		for(TemplateMethodGroup group: this.methodGroupList){
			for(Method m: group.getMethods()){
				for(Method calleeMethod: m.getCalleeMethod()){
					for(TemplateMethodGroup calleeGroup: calleeMethod.getTemplateGroupList()){
						
						if(existValidatedCallingRelations(group, calleeGroup)){
							
							group.addCalleeGroup(calleeGroup);
							calleeGroup.addCallerGroup(group);
						}
					}					
				}
			}
		}
	}
	
	private boolean existValidatedCallingRelations(TemplateMethodGroup callerGroup, TemplateMethodGroup calleeGroup){
		
		int count = 0;
		
		TreeSet<Method> examinedMethods = new TreeSet<Method>();
		for(Method methodInCallerGroup: callerGroup.getMethods()){
			if(examinedMethods.contains(methodInCallerGroup)) continue;
			
			for(Method calleeMethod: methodInCallerGroup.getCalleeMethod()){
				
				if(examinedMethods.contains(calleeMethod)) continue;
				
				if(calleeGroup.getMethods().contains(calleeMethod)){
					examinedMethods.add(methodInCallerGroup);
					examinedMethods.add(calleeMethod);
					
					count++;
					break;
				}
			}
		}
		
		return count >= Settings.templateMethodGroupCallingStrength;
	}
	
	private void increaseTMGByCounterDiff(){
		//ArrayList<TemplateMethodGroup> list = new ArrayList<TemplateMethodGroup>();
		
		int count = 0;
		
		TemplateMethodGroup[] tmgList = this.methodGroupList.toArray(new TemplateMethodGroup[0]);
		
		for(TemplateMethodGroup tmg: tmgList){
			
			if(tmg.toString().contains("main")){
				System.out.print("");
			}
			
			//System.out.print("");
			
			CloneSet set = tmg.findSmallestCloneSet();
			ArrayList<CounterRelationGroup> counterDifferences = identifyCounterDifferences(set, tmg, count);
			
			for(CounterRelationGroup group: counterDifferences){
				TemplateMethodGroup methodGroup = new TemplateMethodGroup();
				
				for(InstanceElementRelation pair: group.getRelationList()){
					OntologicalElement element = pair.getElement();
					if(element instanceof Method){
						Method candiateMethod = (Method)element;
						methodGroup.addMethod(candiateMethod);
						//candiateMethod.addTemplateGroup(methodGroup);
					}
				}
				
				if(methodGroup.getMethods().size() <= 1) continue;
				
				TemplateMethodGroup identifiedTMG = findExistingTempalteMethodGroupOrCreateOne(methodGroup);

				/**
				 * the TMG of each methods are decided only after the uniqueness of a TMG is ensured
				 */
				for(InstanceElementRelation pair: group.getRelationList()){
					OntologicalElement element = pair.getElement();
					if(element instanceof Method){
						Method candiateMethod = (Method)element;
						//methodGroup.addMethod(candiateMethod);
						candiateMethod.addTemplateGroup(identifiedTMG);
					}
				}
				
				//list.add(identifiedTMG);
				if(tmg.toString().contains("main")){
					System.out.print("");
				}
				
				//tmg.addCalleeGroup(identifiedTMG);
				//identifiedTMG.addCallerGroup(tmg);
			}
		}
		
		//sanitize();
		/*for(TemplateMethodGroup tmg: list){
			
			if(tmg.toString().contains("construct")){
				System.out.println();
			}
			//System.out.print("");
			this.methodGroupList.add(tmg);
		}*/
	}
	
	private ArrayList<CounterRelationGroup> identifyCounterDifferences(CloneSet set, TemplateMethodGroup tmg, int count){
		
		ArrayList<CounterRelationGroup> groups = new ArrayList<CounterRelationGroup>();
		if(set.size() == tmg.getMethods().size()){
			groups = set.getCounterRelationGroups();
		}
		else{
			/**
			 * If the size is shrunk, the length of clone instance may increase.
			 */
			CloneSet suitableSet = findMoreSuitableCloneSet(tmg);
			
			if(null != suitableSet){
				groups = suitableSet.getCounterRelationGroups();
			}
			else{
				
				CloneSet copiedSet = copyCloneSetWithoutIrrelevantCloneInstance(tmg, set);
				copiedSet.setId(copiedSet.getId() + "_" + count);
				copiedSet.clearCounterRelationGroups();
				
				CloneSetWrapper setWrapper = new CloneSetWrapper(copiedSet, new CompilationUnitPool());
				OntologicalDataFetcher fetcher = copiedSet.getCloneSets().getDataFetcher();
				
				//setWrapper = new CloneInformationExtractor().extractCounterRelationalDifferencesOfCloneSet(setWrapper);	
				try {
					setWrapper = new CloneInformationExtractor().extractCounterRelationalDifferencesWithinSyntacticBoundary(setWrapper);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				CloneSet newSet;
				try {
					newSet = fetcher.storeDiffRelation(setWrapper);
					groups = newSet.getCounterRelationGroups();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return groups;
	}
	
	/**
	 * If the size is shrunk, the length of clone instance may increase.
	 */
	private CloneSet findMoreSuitableCloneSet(TemplateMethodGroup tmg){
		for(CloneSet set: this.sets.getCloneList()){
			if(isCloneSetMatchTMG(set, tmg)){
				return set;
			}
		}
		
		return null;
	}
	
	private boolean isCloneSetMatchTMG(CloneSet set, TemplateMethodGroup tmg){
		
		if(set.size() != tmg.getMethods().size()) return false;
		
		for(Method m: tmg.getMethods()){
			if(!set.containsResidingMethod(m)){
				return false;
			}
		}
		
		return true;
	}
	
	private CloneSet copyCloneSetWithoutIrrelevantCloneInstance(TemplateMethodGroup tmg, CloneSet set){
		CloneSet newSet = (CloneSet) set.clone();
		
		Iterator<CloneInstance> iterator = newSet.iterator();
		while(iterator.hasNext()){
			CloneInstance instance = iterator.next();
			if(!tmg.getMethods().contains(instance.getResidingMethod())){
				iterator.remove();
			}
		}
		
		return newSet;
	}

	private void init() {
		for (CloneSet set : this.sets.getCloneList()) {

			/*if (set.getId().equals("480386")) {
				System.out.print("");
			}*/

			TemplateMethodGroup methodGroup = new TemplateMethodGroup();
			for (CloneInstance instance : set) {
				/*if(instance.getResidingMethod().toString().contains("void") && instance.getResidingMethod().toString().contains("ZoomAction")){
					System.out.println();
				}*/
				
				methodGroup.addMethod(instance.getResidingMethod());
			}

			if (methodGroup.getMethods().size() > 1) {
				verifyTemplateMethodGroup(methodGroup, set);
			}
		}

		//sanitize();
	}

	/**
	 * Case I: The cloned code ranges of a small-size template method group
	 * (TMG) will be overlapped, or even proper contained in a larger-size
	 * template method.
	 * 
	 * Solution: In this case, I remove small-size TMG and keep large-size TMG but the removed
	 * small TMGs are kept in the large TMG containing them.
	 * 
	 */
	private void sanitize() {
		TemplateMethodGroup[] groupArray = this.methodGroupList.toArray(new TemplateMethodGroup[0]);
		
		Arrays.sort(groupArray, new TMGSizeComparator());
		
		ArrayList<Integer> toBeSanitizedTMGIndex = new ArrayList<Integer>();
		
		for(int i=0; i<groupArray.length; i++){
			if(toBeSanitizedTMGIndex.contains(new Integer(i))) continue;
			
			for(int j=i+1; j<groupArray.length; j++){
				if(toBeSanitizedTMGIndex.contains(new Integer(j))) continue;
				if(groupArray[i].getMethods().size() ==  groupArray[j].getMethods().size()) continue;
				
				if(groupArray[i].contains(groupArray[j])){
					toBeSanitizedTMGIndex.add(j);
					groupArray[i].addContainedTMG(groupArray[j]);
				}
			}
		}
		
		for(Integer i: toBeSanitizedTMGIndex){
			TemplateMethodGroup tmg = groupArray[i];
			if(this.methodGroupList.contains(tmg)){
				this.methodGroupList.remove(tmg);
			}
		}
	}

	/**
	 * The methods in template method group should be similar in four
	 * perspectives:
	 * 
	 * 1. package location 2. class location 3. return type 4. parameter list
	 * 
	 * only if the above perspectives are similar, the method are regarded as a
	 * candidate to form template.
	 * 
	 * @param methodGroup
	 */
	private void verifyTemplateMethodGroup(TemplateMethodGroup methodGroup, CloneSet set) {
		IClusterable[] elements = new IClusterable[methodGroup.getMethods().size()];
		int count = 0;
		for (Method m : methodGroup.getMethods()) {
			elements[count++] = m;
		}

		HierarchyClusterAlgorithm algorithm = new HierarchyClusterAlgorithm(elements, 
				Settings.thresholdDistanceForTMGFilteringAndSplitting, HierarchyClusterAlgorithm.SingleLinkage);
		try {
			ArrayList<NormalCluster> clusters = algorithm.doClustering();

			for (NormalCluster cluster : clusters) {
				if (cluster.size() > 1) {
					TemplateMethodGroup newGroup = new TemplateMethodGroup();
					for (IClusterable ele : cluster) {
						Method tMethod = (Method) ele;
						newGroup.addMethod(tMethod);
						//tMethod.addTemplateGroup(newGroup);
					}
					TemplateMethodGroup tmg = findExistingTempalteMethodGroupOrCreateOne(newGroup);
					/**
					 * the TMG of each methods are decided only after the uniqueness of a TMG is ensured
					 */
					for (IClusterable ele : cluster) {
						Method tMethod = (Method) ele;
						tMethod.addTemplateGroup(tmg);
					}
					
					tmg.addRelatedCloneSet(set);
					//newGroup.addRelatedCloneSet(set);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private TemplateMethodGroup findExistingTempalteMethodGroupOrCreateOne(TemplateMethodGroup newGroup){
		if(!this.methodGroupList.contains(newGroup)){
			this.methodGroupList.add(newGroup);
			//System.out.print("");
			return newGroup;
		}
		else{
			TemplateMethodGroup group = achieveSameTMGInSet(newGroup);
			return group;
		}
	}
	
	/**
	 * This method should be inovked after ensuring that tmg has already been contained in methodGroupList
	 * @param tmg
	 * @return
	 */
	private TemplateMethodGroup achieveSameTMGInSet(TemplateMethodGroup tmg){
		for(TemplateMethodGroup group: this.methodGroupList){
			if(group.equals(tmg))
				return group;
		}
		
		return null;
	}

	public HashSet<TemplateMethodGroup> getMethodGroupList() {
		return this.methodGroupList;
	}
}
