package clonepedia.templategeneration.routine;

import java.util.ArrayList;

import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;
import datamining.cluster.IClusterable;
import datamining.cluster.NormalCluster;
import datamining.cluster.hierarchy.HierarchyClusterAlgorithm;


public class TypeCluster {
	
	public ArrayList<Multiset> clusterTypes(ArrayList<TypeWrapper> typeList){
		
		TypeWrapper[] array = typeList.toArray(new TypeWrapper[0]);
		//threshold is for distance, that means the similarity must higher than 0.5 if distance is 2
		double distanceThreshold = AutoGenCTSettings.retrieveClusteringDistanceThreshold();
		HierarchyClusterAlgorithm algo = new HierarchyClusterAlgorithm(array, distanceThreshold, HierarchyClusterAlgorithm.CompleteLinkage);
		try {
			long t1 = System.currentTimeMillis();
			ArrayList<NormalCluster> clusters = algo.doClustering();
			long t2 = System.currentTimeMillis();
			System.out.println("the clusternig for " + typeList.size() + 
					" java classes takes " + (t2-t1)/1000 + " seconds; parameters: distanceThres=" + distanceThreshold);
			
			ArrayList<Multiset> sets = new ArrayList<>();
			for(NormalCluster cluster: clusters){
				Multiset multiset = new Multiset();
				if(cluster.size() > 1){
					for(IClusterable cl: cluster){
						TypeWrapper type = (TypeWrapper)cl;
						multiset.addElement(type);
					}					
					sets.add(multiset);
				}
				
			}
			
			//System.currentTimeMillis();
			return sets;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
