package clonepedia.templategeneration.routine;

import java.util.ArrayList;

import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.codegeneration.diagram.bean.Application;
import clonepedia.codegeneration.diagram.bean.IElement;
import clonepedia.codegeneration.diagram.bean.IElementContainer;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.ProgramElementWrapper;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;
import clonepedia.templategeneration.diagram.util.AutoGenCTUtil;

public class Corresponder {
	/**
	 * build the internal relation of a specific multiset, return the list of its direct children multiset
	 * @param parentSet
	 * @return
	 */
	public ArrayList<Multiset> match(Multiset parentSet){
		ArrayList<Multiset> setList = matchElement(parentSet);
		for(Multiset set: setList){
			if(set.isContainerMultiset()){
				ArrayList<Multiset> subMultisetList = match(set);
				set.setSubMultisetList(subMultisetList);
				for(Multiset subMultiset: subMultisetList){
					subMultiset.setSuperMultiset(set);
				}
			}
		}
		
		parentSet.setSubMultisetList(setList);
		for(Multiset set: setList){
			set.setSuperMultiset(parentSet);
		}
		
		return setList;
	}
	
	/**
	 * the super multiset should be a container multiset
	 * @param superMultiset
	 * @return
	 */
	public ArrayList<Multiset> matchElement(Multiset superMultiset){
		ArrayList<Multiset> multisets = new ArrayList<>();
		for(IElement ele: superMultiset.getCorrespondingSet()){
			IElementContainer container = (IElementContainer)ele;
			for(IElement element: container.getContainedElements()){
				if(!element.isMarked()){
					ArrayList<IElementContainer> otherContainers = getOtherContainers(element, superMultiset);
					
					Multiset multiset = new Multiset();
					multiset.addElement(element);
					
					for(IElementContainer otherContainer: otherContainers){
						
						IElement mostSimilarElement = findMostSimilarElement(multiset, otherContainer);
						
						if(mostSimilarElement != null){
							multiset.addElement(mostSimilarElement);
							
						}
						
					}
					
					for(IElement checkedElement: multiset.getCorrespondingSet()){
						checkedElement.setMarked(true);
					}
					
					if(multiset.size() >= 2){
						multisets.add(multiset);
						for(IElement e: multiset.getCorrespondingSet()){
							e.setBelongingMultiset(multiset);
						}
					}
				}
			}
		}
		
		return multisets;
	}
	
	/**
	 * return null if there is no type which is similar enough to match multiset.
	 * @return
	 */
	private IElement findMostSimilarElement(Multiset multiset,
			IElementContainer otherContainer) {
		IElement mostSimilarElement = null;
		double bestSimilarity = 0d;
		for(IElement element: otherContainer.getContainedElements()){
			
			if(element.isMarked()){
				continue;
			}
			
			double similarity = multiset.computeSimilarity(element);
			
			if(similarity >= AutoGenCTUtil.getThreashold(element)
					&& similarity > bestSimilarity){
				mostSimilarElement = element;
				bestSimilarity = similarity;
			}
		}
		
		return mostSimilarElement;
	}

	private ArrayList<IElementContainer> getOtherContainers(IElement element, Multiset multiset){
		ArrayList<IElementContainer> otherContainers = new ArrayList<>();
		for(IElement ele: multiset.getCorrespondingSet()){
			IElementContainer container = (IElementContainer)ele;
			
			
			if(!isContains(container, element)){
				otherContainers.add(container);
			}
		}
		return otherContainers;
	}
	
	private boolean isContains(IElementContainer container, IElement element){
		for(IElement ele: container.getContainedElements()){
			if(element.equals(ele)){
				return true;
			}
		}
		
		return false;
	}
}
