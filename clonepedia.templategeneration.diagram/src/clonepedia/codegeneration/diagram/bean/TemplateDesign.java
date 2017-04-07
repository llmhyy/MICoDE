package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import edu.ntu.cltk.algo.HungarianAlgo;


public class TemplateDesign implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4744609248737367648L;
	
	private String id = UUID.randomUUID().toString();
	
	private String name;
	private String description;
	
	/**
	 * The materials contains the a forest (tree) like structure. The first layer of materials
	 * contains the top-level elements of containment tree, e.g., classes. The second layer represents
	 * the contained elements in the top-level elements.  
	 */
	private ArrayList<Multiset> materials = new ArrayList<>();
	
	public String toString(){
		return this.materials.toString();
	}
	
	public int getAbstractTypeNumber(){
		int count = 0;
		for(Multiset multiset: materials){
			if(multiset.isTypeSet()){
				count++;
			}
		}
		return count;
	}
	
	public int getAbstractInnerTypeNumber(){
		int count = 0;
		for(Multiset multiset: materials){
			count += getInnerClassNumber(multiset);
		}
		return count;
	}
	
	public int getInnerClassNumber(Multiset set){
		int count = 0;
		for(Multiset subSet: set.getSubMultisetList()){
			if(subSet.isTypeSet()){
				count++;
				count += getInnerClassNumber(subSet);
			}
		}
		return count;
	}
	
	public int getAbstractFieldNumber(){
		int count = 0;
		for(Multiset multiset: materials){
			count += getInnerFieldNumber(multiset);
		}
		return count;
	}
	
	private int getInnerFieldNumber(Multiset set){
		int count = 0;
		for(Multiset subSet: set.getSubMultisetList()){
			if(subSet.isFieldSet()){
				count ++;
			}
			else if(subSet.isTypeSet()){
				count += getInnerFieldNumber(subSet);
			}
		}
		return count;
	}
	
	public int getAbstractMethodNumber(){
		int count = 0;
		for(Multiset multiset: materials){
			count += getInnerMethodNumber(multiset);
		}
		return count;
	}
	
	private int getInnerMethodNumber(Multiset set){
		int count = 0;
		for(Multiset subSet: set.getSubMultisetList()){
			if(subSet.isMethodSet()){
				count ++;
			}
			else if(subSet.isTypeSet()){
				count += getInnerFieldNumber(subSet);
			}
		}
		return count;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	public String getID(){
		return id;
	}
	
	public void setID(String id){
		this.id = id;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the design
	 */
	public ArrayList<Multiset> getMaterials() {
		return materials;
	}
	
	public void add(Multiset multiset){
		if(!materials.contains(multiset)){
			materials.add(multiset);
		}
	}
	
	public void merge(TemplateDesign design){
		for(Multiset set: design.getMaterials()){
			this.materials.add(set);
		}
	}

	public boolean contains(TemplateDesign independentDesign) {
		for(Multiset set: independentDesign.getMaterials()){
			if(!this.getMaterials().contains(set)){
				return false;
			}
		}
		
		return true;
	}

	public void remove(TemplateDesign independentDesign) {
		Iterator<Multiset> iterator = this.getMaterials().iterator();
		while(iterator.hasNext()){
			Multiset set = iterator.next();
			if(independentDesign.getMaterials().contains(set)){
				iterator.remove();
			}
		}
	}

	/**
	 * Return the sequence number for the member in a class
	 * @param sup
	 * @return
	 */
	private ArrayList<int[]> buildGraph(Multiset sup){
		
		ArrayList<int[]> graph = new ArrayList<int[]>();
		
		for (Multiset sub : sup.getSubMultisetList()){
			int[] subgraph = new int[sup.size()];
			for (int i = 0 ; i < subgraph.length; i++)	subgraph[i] = -1;
			for (IElement ie : sub.getCorrespondingSet()){
				MemberWrapper mw = (MemberWrapper) ie;
				int ind = sup.getCorrespondingSet().indexOf(mw.getOwnerType());
				if (ind > -1){
					TypeWrapper cla = (TypeWrapper)sup.getCorrespondingSet().get(ind);
					int num = cla.getMembers().indexOf(mw);
					if (num > -1){
						subgraph[ind] = num;
					}			 
				}
			}
			graph.add(subgraph);
		}
		return graph;
	}
	public TemplateInstance resolveTemplateInstance2(Multiset ms) {
		
		return null;
	}
	/**
	 * Generate template instance for each multiset
	 * @param ms
	 * @return
	 */
	public TemplateInstance resolveTemplateInstance(Multiset ms) {
		ArrayList<int[]> graph = buildGraph(ms);
		
		//Remove the instance which do not shared with all instances
		for (int i = 0 ; i < graph.size();){
			boolean remove = false;
			for (int j = 0 ; j < graph.get(i).length; j++){
				if (graph.get(i)[j] == -1){
					remove = true;
					break;
				}
			}
			if (remove){
				graph.remove(i);
			}else	i++;
		}
		
		TypeWrapper tw = (TypeWrapper) ms.getCorrespondingSet().get(0);
		int len = tw.getMembers().size();
		HungarianAlgo<Integer> ha = new HungarianAlgo<Integer>();
		for (int i = 1; i < ms.size(); i++){
			TypeWrapper temp = (TypeWrapper) ms.getCorrespondingSet().get(i);
			Integer[][] matrix = new Integer[len][temp.getMembers().size()];
			for (int j = 0; j < graph.size(); j++){
				int a = graph.get(j)[0];
				int b = graph.get(j)[i];
				if (a == -1 || b == -1)	continue;
				matrix[a][b] = matrix[b][a]	= 1;
			}
			int[] match = ha.maximalBipartiteGraphMatching(matrix);
			for (int j = 0; j < len; j++){
				if(match[j] == -1){
					//If the j-th element is not in the match list, we remove the associated connection
					for (int k = 0 ; k < graph.size(); ){
						if (graph.get(k)[0] != -1){
							graph.remove(k);
						}else	k++;
					}
				}else{
					//Remove the connection if not in the match list
					for (int k = 0 ; k < graph.size(); ) {
						if (graph.get(k)[i] != match[j]){
							graph.remove(k);
						}else	k++;
					}
				}
			}
		}
		
		TemplateInstance ti = new TemplateInstance();
		for (int i = 0 ; i < ms.size(); i++){
			TypeWrapper instance = (TypeWrapper) ms.getCorrespondingSet().get(i);
			ArrayList<Integer> containedMembers = new ArrayList<Integer>();
			for (int j = 0 ; j < graph.size(); j++)		containedMembers.add(graph.get(j)[i]);
		}
		return ti;
	}
	
	/**
	 * Find if one string exists in an array
	 * 
	 * @param arr
	 * @param src
	 * @return
	 */
	private boolean findString(String[] arr, String src) {
		if (arr == null || src == null)	return false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(src)) {
				return true;
			}
		}
		return false;
	}
	
	private double jaccardIndex(String[] sar1, String[] sar2) {
		if (sar1 == null || sar2 == null)	return 0.0;
		List<String> conjunctArr = new ArrayList<String>();
		List<String> disjunctArr = new ArrayList<String>();

		for (int i = 0; i < sar1.length; i++) {
			if (!disjunctArr.contains(sar1[i])) {
				disjunctArr.add(sar1[i]);
			}

			if (!conjunctArr.contains(sar1[i]) && findString(sar2, sar1[i])) {
				conjunctArr.add(sar1[i]);
			}
		}

		for (int i = 0; i < sar2.length; i++) {
			if (!disjunctArr.contains(sar2[i])) {
				disjunctArr.add(sar2[i]);
			}
		}
		return 1.0 * conjunctArr.size() / disjunctArr.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<TemplateInstance> resolveTemplateInstance() {
		
		/**TODO 
		 * Here, we need an algorithm to distinguish template instances. The
		 * basic idea is to identify the top-level types in the same template
		 * instance. Afterwards, we use containment relation to further identify 
		 * the corresponding members.
		 */
		
		List<TemplateInstance> instances = new ArrayList<>();
		int len = this.materials.size();	
		System.out.println("#########################################################");
		
		List<TypeWrapper> clazz = new ArrayList<TypeWrapper>();
		List<MemberWrapper> members = new ArrayList<MemberWrapper>();
		for (Multiset ms : this.materials){
			for (IElement ele : ms.getCorrespondingSet()){
				if (ele instanceof TypeWrapper){
					TypeWrapper tw = (TypeWrapper)ele;
					clazz.add(tw);
					
					for (ProgramElementWrapper pew : tw.getMembers()){
						if (pew instanceof MemberWrapper)
							members.add((MemberWrapper)pew);
					}
				
				}
			}
		}
		
		
		for (Multiset ms : this.materials){
			for (IElement ele : ms.getCorrespondingSet()){
					
				HashSet<TypeWrapper> visited = new HashSet<TypeWrapper>();
					
				if (ele instanceof TypeWrapper){
					TypeWrapper tw = (TypeWrapper) ele;
					if (visited.contains(tw))	continue;
						
					List<TypeWrapper> instance = new ArrayList<TypeWrapper>();
					int pos = 0;
					for (instance.add(tw), visited.add(tw);pos < instance.size();pos++){

						for (ProgramElementWrapper pew : instance.get(pos).getMembers()){
							if (pew instanceof MethodWrapper){
								MethodWrapper mw = (MethodWrapper) pew;
								if(mw.getMethodName().equals("beginEdit")){
									System.currentTimeMillis();
								}
								
								for (MemberWrapper callee : mw.getCalleeMembers()){
									if (members.contains(callee)){
										TypeWrapper owner = callee.getOwnerType();
										if (instance.contains(owner) || visited.contains(owner))	continue;
										visited.add(owner);
										instance.add(owner);
									}
								}
								
								for (MemberWrapper caller : mw.getCallerMembers()){
									if (members.contains(caller)){
										TypeWrapper owner = caller.getOwnerType();
										if (instance.contains(owner) || visited.contains(owner))	continue;
										visited.add(owner);
										instance.add(owner);
									}
								}
							}
						}
					}
					
					if (instance.size() > 1)
						instances.add(new TemplateInstance(instance));
				}
			}
			
		
			/*for (Multiset sub : ms.getSubMultisetList()){
				HashSet<String> hm1 = new HashSet<String>();
				for (Multiset callee : sub.getCalleeSets()){
					
				}
				for (Multiset caller : sub.getCallerSets()){
					
				}
				for (IElement ele : sub.getCorrespondingSet()){
					if (ele instanceof MethodWrapper){
						MethodWrapper mw = (MethodWrapper) ele;
						hm1.add(mw.toString());

						HashSet hs = null;
						
					}
				}
			}
			for (IElement ele : ms.getCorrespondingSet()){
				TypeWrapper tw = (TypeWrapper) ele;
				
				for (ProgramElementWrapper pew : tw.getMembers()){
					if (pew instanceof MethodWrapper){
						MethodWrapper mw = (MethodWrapper)pew;
					}else if (pew instanceof FieldWrapper){
						
					}
				}
			}
			
			System.out.println();
			//TODO: re-implement containedMember method
			instances.add(resolveTemplateInstance(ms));
			int[][] similarity = new int[ms.size()][ms.size()]; 
			for (Multiset sub: ms.getSubMultisetList()){
				List<Integer> mem = new ArrayList<Integer>();//containedMember(ms, sub);
				for (int i = 0 ; i < mem.size(); i++){
					for (int j = i+1; j < mem.size(); j++){
						similarity[mem.get(i)][mem.get(j)]++;
						similarity[mem.get(j)][mem.get(i)]++;
					}
				}
			}
			
			System.out.println(ms.toString());
			for (int i = 0; i< ms.size(); i++){
				for (int j = 0 ; j < ms.size(); j++){
					System.out.print(similarity[i][j] + " ");
				}
				System.out.println();
			}*/
		}
		
		return instances;
	}
}
