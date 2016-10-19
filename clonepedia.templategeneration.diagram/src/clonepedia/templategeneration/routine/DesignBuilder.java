package clonepedia.templategeneration.routine;

import java.util.ArrayList;
import java.util.Iterator;

import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.codegeneration.diagram.bean.DesignList;
import clonepedia.codegeneration.diagram.bean.IElement;
import clonepedia.codegeneration.diagram.bean.MemberWrapper;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;
import datamining.frequentitem.IItem;
import datamining.frequentitem.ItemSet;
import datamining.frequentitem.Transaction;
import datamining.frequentitem.fpgrowth.FPGrowthAlgorithm;

public class DesignBuilder {
	
	public DesignList buildDesign(ArrayList<Multiset> multisets){
		System.currentTimeMillis();
		
		ArrayList<Multiset> subMultisetList = achieveSubMultisets(multisets);
		buildCallingRelationsForSubMultisets(subMultisetList);
		
		buildingCallingRelationsForMultisets(multisets);
		
		//DesignList designs = createDesign(multisets);
		DesignList designs = createDesignWithDirect(multisets);
		
		DesignList finalDesigns = postProcess(designs);
		//DesignList finalDesigns = designs;
		removeUnnecessaryDesigns(finalDesigns);
		
		return finalDesigns;
		
	}
	
	private void removeUnnecessaryDesigns(DesignList finalDesigns){
		Iterator<TemplateDesign> designIter = finalDesigns.iterator();
		while(designIter.hasNext()){
			TemplateDesign design = designIter.next();
			
			Iterator<Multiset> iter = design.getDesign().iterator();
			while(iter.hasNext()){
				Multiset multiset = iter.next();
				cleanUnnecessaryTypeElement(multiset);
				if(multiset.size() == 0){
					iter.remove();
				}
			}
			
			if(design.getDesign().size() == 0){
				designIter.remove();
			}
		}
		
	}
	
	private void cleanUnnecessaryTypeElement(Multiset multiset) {
		ArrayList<Multiset> subsets = multiset.getSubMultisetList();
		
		Iterator<IElement> iterator = multiset.getCorrespondingSet().iterator();
		while(iterator.hasNext()){
			TypeWrapper type = (TypeWrapper)iterator.next();
			
			if(isIrrelevantTo(type, subsets)){
				iterator.remove();
			}
		}
	}

	private boolean isIrrelevantTo(TypeWrapper type, ArrayList<Multiset> subsets) {
		for(Multiset set: subsets){
			for(IElement ele: set.getCorrespondingSet()){
				if(type.getMembers().contains(ele)){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method find the frequent item in designs, and split them as independent design.
	 * In addition, if a design after post process is no longer a connected graph, then it
	 * will be split again.
	 * @param oldDesignList
	 * @return
	 */
	private DesignList postProcess(DesignList oldDesignList) {
		ArrayList<Transaction> transList = convertToTransactions(oldDesignList);
		ArrayList<ItemSet> itemSets = new FPGrowthAlgorithm().runLargestBySupport(transList, 2);
		//transList = convertToTransactions(oldDesignList);
		
		
		
		ArrayList<TemplateDesign> independentDesignList = new ArrayList<>();
		
		System.currentTimeMillis();
		
		/**
		 * split the designs containing independent designs (i.e., frequent item set)
		 */
		for(ItemSet itemSet: itemSets){
			if(itemSet.size() > 0){
				TemplateDesign independentDesign = convertToDesign(itemSet);
				independentDesignList.add(independentDesign);
				
				ArrayList<TemplateDesign> containingDesignList = oldDesignList.findContainingDesigns(independentDesign);
				//ArrayList<Transaction> tList = findContainingTranscations(transList, itemSet);
				
				for(TemplateDesign containingDesign: containingDesignList){
					containingDesign.remove(independentDesign);
					
					if(containingDesign.getDesign().size() == 0){
						oldDesignList.remove(containingDesign);
					}
				}
				
				/**
				 * the following item sets in list should remove should diff this item set.
				 */
				updateItemSets(itemSets, itemSet);				
			}
		}
		oldDesignList.addAll(independentDesignList);
		
		/**
		 * deal with those unconnnected design
		 */
		DesignList finalDesignList = new DesignList();
		for(TemplateDesign oldDesign: oldDesignList){
			if(oldDesign.getDesign().size() > 5){
				System.currentTimeMillis();
				
			}
			
			DesignList testDesignList = createDesignWithDirect(oldDesign.getDesign());
			if(testDesignList.size() > 1 || testDesignList.size() == 0){
				System.currentTimeMillis();
				createDesignWithDirect(oldDesign.getDesign());
			}
			
			finalDesignList.addAll(testDesignList);
		}
		
		
		return finalDesignList;
	}
	
	private void updateItemSets(ArrayList<ItemSet> itemSets, ItemSet itemSet) {
		ItemSet tmpSet = new ItemSet();
		tmpSet.getItemList().addAll(itemSet.getItemList());
		for(ItemSet followingSet: itemSets){
			
			if(followingSet.contains(tmpSet)){
				Iterator<IItem> iterator = followingSet.getItemList().iterator();
				
				while(iterator.hasNext()){
					IItem item = iterator.next();
					if(tmpSet.getItemList().contains(item)){
						iterator.remove();
					}
				}
				
			}
			
		}
	}

	private ArrayList<Transaction> findContainingTranscations(ArrayList<Transaction> list, ItemSet itemSet){
		ArrayList<Transaction> newList = new ArrayList<>();
		for(Transaction t: list){
			if(t.contains(itemSet)){
				newList.add(t);
			}
		}
		return newList;
	}
	
	private TemplateDesign convertToDesign(ItemSet itemSet){
		TemplateDesign design = new TemplateDesign();
		for(IItem item: itemSet.getItemList()){
			Multiset multiset = (Multiset)item;
			design.add(multiset);
		}
		
		return design;
	}
	
	private ArrayList<Transaction> convertToTransactions(DesignList designs) {
		ArrayList<Transaction> transList = new ArrayList<>();
		
		for(TemplateDesign design: designs){
			ArrayList<IItem> list = new ArrayList<>();
			for(Multiset multiset: design.getDesign()){
				list.add(multiset);
			}
			Transaction transaction = new Transaction(list);
			transList.add(transaction);
		}
		
		return transList;
	}

	private DesignList createDesignWithDirect(ArrayList<Multiset> multisets){
		DesignList designs = new DesignList();
		clearMark(multisets);
		
		/**
		 * this variable is used to check cycle.
		 */
		ArrayList<Multiset> visitedSet = new ArrayList<>();
		ArrayList<Multiset> startList = getStartMultisets(multisets);
		for(Multiset startNode: startList){
			TemplateDesign design = new TemplateDesign();
			clearMark(multisets);
			
			increaseMultiset(startNode, design, multisets);
			designs.add(design);
			
			for(Multiset node: design.getDesign()){
				if(!visitedSet.contains(node)){
					visitedSet.add(node);
				}
			}
		}
		
		/**
		 * the above code cannot find independent loops in graph, e.g., A->B->C->A, and A, B, C 
		 * interact with no node other than A, B, and C.
		 */
		ArrayList<TemplateDesign> list = findLoopDesigns(visitedSet, multisets);
		designs.addAll(list);
		
		return designs;
	}
	
	private ArrayList<TemplateDesign> findLoopDesigns(ArrayList<Multiset> visitedSet, ArrayList<Multiset> multisets){
		ArrayList<TemplateDesign> list = new ArrayList<>();
		
		Multiset nodeInLoop = findANodeInLoop(visitedSet, multisets);
		while(nodeInLoop != null){
			clearMark(multisets);
			TemplateDesign design = new TemplateDesign();
			increaseMultiset(nodeInLoop, design, multisets);
			
			list.add(design);
			
			for(Multiset n: design.getDesign()){
				if(!visitedSet.contains(n)){
					visitedSet.add(n);
				}
			}
			
			nodeInLoop = findANodeInLoop(visitedSet, multisets);
		}
		
		return list;
	}
	
	private Multiset findANodeInLoop(ArrayList<Multiset> visitedSet, ArrayList<Multiset> multisets){
		for(Multiset set: multisets){
			if(!visitedSet.contains(set)){
				if(set.getCalleeSets().size()!=0 && set.getCallerSets().size()!= 0){
					return set;					
				}
			}
		}
		
		return null;
	}
	
	/**
	 * increase the graph under the scope of {@code scope}
	 * @param node
	 * @param design
	 * @param scope
	 */
	private void increaseMultiset(Multiset node, TemplateDesign design, ArrayList<Multiset> scope){
		if(!node.isMarked()){
			node.setMarked(true);
			if(scope.contains(node)){
				design.add(node);
				for(Multiset callee: node.getCalleeSets()){
					increaseMultiset(callee, design, scope);
				}							
			}
		}
	}
	
	private Multiset findUnmarkedNodes(ArrayList<Multiset> multisets){
		for(Multiset set: multisets){
			if(!set.isMarked()){
				return set;
			}
		}
		return null;
	}
	
	private void findForwardDirectedConnectedGraph(Multiset startNode, TemplateDesign design){
		if(!startNode.isMarked()){
			design.add(startNode);
			startNode.setMarked(true);
			
			for(Multiset callee: startNode.getCalleeSets()){
				findForwardDirectedConnectedGraph(callee, design);
			}
		}
	}
	
	private void findBackwardDirectedConnectedGraph(Multiset startNode, TemplateDesign design){
		if(!startNode.isMarked()){
			design.add(startNode);
			startNode.setMarked(true);
			
			for(Multiset caller: startNode.getCallerSets()){
				findForwardDirectedConnectedGraph(caller, design);
			}
		}
	}
	
	private void clearMark(ArrayList<Multiset> multisets){
		for(Multiset set: multisets){
			set.setMarked(false);
		}
	}
	
	private ArrayList<Multiset> getStartMultisets(ArrayList<Multiset> multisets){
		ArrayList<Multiset> startList = new ArrayList<>();
		for(Multiset set: multisets){
			if(set.getCallerSets().size() == 0){
			//if(getCallerSizeInScope(multisets, set) == 0){
				startList.add(set);
			}
		}
		return startList;
	}
	
	private int getCallerSizeInScope(ArrayList<Multiset> scope, Multiset set){
		int count = 0;
		for(Multiset caller: set.getCallerSets()){
			if(scope.contains(caller)){
				count++;
			}
		}
		return count;
	}
	
	private DesignList createDesign(ArrayList<Multiset> multisets){
		DesignList designs = new DesignList();
		for(Multiset set: multisets){
			if(!set.isMarked()){
				TemplateDesign design = new TemplateDesign();
				transverseGraph(set, design);
				
				designs.add(design);
			}
		}
		
		return designs;
	}
	
	private void transverseGraph(Multiset set, TemplateDesign design) {
		set.setMarked(true);
		design.add(set);
		
		for(Multiset callee: set.getCalleeSets()){
			if(!callee.isMarked()){
				transverseGraph(callee, design);				
			}
		}
		
		for(Multiset caller: set.getCallerSets()){
			if(!caller.isMarked()){
				transverseGraph(caller, design);				
			}
		}
	}

	private void buildingCallingRelationsForMultisets(ArrayList<Multiset> multisets){
		for(int i=0; i<multisets.size(); i++){
			for(int j=0; j<multisets.size(); j++){
				if(i != j){
					Multiset set1 = multisets.get(i);
					Multiset set2 = multisets.get(j);
					
					if(hasCallRelationInSet(set1, set2)){
						set1.addCallee(set2);
						set2.addCaller(set1);
					}
				}
			}
		}
	}
	
	private boolean hasCallRelationInSet(Multiset set1, Multiset set2){
		for(Multiset subSet1: set1.getSubMultisetList()){
			for(Multiset subSet2: set2.getSubMultisetList()){
				
				
				
				if(subSet1.getCalleeSets().contains(subSet2)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	private ArrayList<Multiset> achieveSubMultisets(ArrayList<Multiset> multisets){
		ArrayList<Multiset> subMultisetList = new ArrayList<>();
		for(Multiset parentSet: multisets){
			subMultisetList.addAll(parentSet.getSubMultisetList());
		}
		
		return subMultisetList;
	}
	
	private void buildCallingRelationsForSubMultisets(ArrayList<Multiset> subMultisetList){
		for(int i=0; i<subMultisetList.size(); i++){
			for(int j=0; j<subMultisetList.size(); j++){
				
				if(i != j){
					Multiset subSet1 = subMultisetList.get(i);
					Multiset subSet2 = subMultisetList.get(j);
					
					if(subSet1.toString().contains("init") && subSet2.toString().contains("Factory")){
						System.currentTimeMillis();
					}
					
					if(hasCallRelationInSubset(subSet1, subSet2)){
						subSet1.addCallee(subSet2);
						subSet2.addCaller(subSet1);
					}					
				}
			}
		}
	}
	
	/**
	 * check whether subSet1 call subSet2
	 * @param subSet1
	 * @param subSet2
	 * @return
	 */
	private boolean hasCallRelationInSubset(Multiset subSet1, Multiset subSet2){
		//int strength = 0;
		
		ArrayList<MemberCallingDirection> checkedDirections = new ArrayList<>();
		
		for(IElement element1: subSet1.getCorrespondingSet()){
			for(IElement element2: subSet2.getCorrespondingSet()){
				if((element1 instanceof MemberWrapper) && (element2 instanceof MemberWrapper) &&
						!checkedDirections.contains(element2)){
					MemberWrapper member1 = (MemberWrapper)element1;
					MemberWrapper member2 = (MemberWrapper)element2;
					
					if(member1.getCalleeMembers().contains(member2)){
						//strength++;
						MemberCallingDirection direction = new MemberCallingDirection(member1, member2);
						if(!checkedDirections.contains(direction)){
							checkedDirections.add(direction);							
						}
					}
				}
			}
		}
		
		int maxSize = (subSet1.size() > subSet2.size())? subSet1.size() : subSet2.size(); 
		int minSize = (subSet1.size() < subSet2.size())? subSet1.size() : subSet2.size(); 
		
		int strength = checkedDirections.size();
		int commonNum = getCommonSize(subSet1, subSet2);
		
		if(commonNum != 0){
			System.currentTimeMillis();
		}
		
		strength -= commonNum;
		
		//double delimeter = (maxSize + minSize)/2;
		double strengthRatio = ((double)strength)/minSize;
		//return strength >= AutoGenCTSettings.callingStrength;
		return strengthRatio >= AutoGenCTSettings.retrieveConnectingThreshold() && 
				strength >= 2;
	}
	
	private int getCommonSize(Multiset callerSet, Multiset calleeSet){
		int count = 0;
		for(IElement callee: calleeSet.getCorrespondingSet()){
			double existenceNum = 0;
			for(IElement caller: callerSet.getCorrespondingSet()){
				if((callee instanceof MemberWrapper) && (caller instanceof MemberWrapper)){
					MemberWrapper calleeMember = (MemberWrapper)callee;
					MemberWrapper callerMember = (MemberWrapper)caller;
					
					if(calleeMember.getCallerMembers().contains(callerMember)){
						existenceNum++;
					}
					
				}
			}
			
			double ratio = existenceNum/callerSet.size();
			if(ratio > 0.8){
				count ++;
			}
		}
		
		return count;
	}
	
	private class MemberCallingDirection{
		private MemberWrapper startPoint;
		private MemberWrapper endPoint;
		
		public MemberCallingDirection(MemberWrapper startPoint,
				MemberWrapper endPoint) {
			super();
			this.startPoint = startPoint;
			this.endPoint = endPoint;
		}
		
		public boolean equals(Object obj){
			if(obj instanceof MemberCallingDirection){
				MemberCallingDirection thatDirect = (MemberCallingDirection)obj;
				if(this.startPoint.equals(thatDirect.getStartPoint()) 
						|| this.endPoint.equals(thatDirect.getEndPoint())){
					return true;
				}
			}
			
			return false;
		}
		
		public MemberWrapper getStartPoint() {
			return startPoint;
		}
		public void setStartPoint(MemberWrapper startPoint) {
			this.startPoint = startPoint;
		}
		public MemberWrapper getEndPoint() {
			return endPoint;
		}
		public void setEndPoint(MemberWrapper endPoint) {
			this.endPoint = endPoint;
		}
		
		
	}
}
