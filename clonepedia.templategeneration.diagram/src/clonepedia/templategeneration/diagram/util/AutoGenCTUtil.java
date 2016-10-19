package clonepedia.templategeneration.diagram.util;

import java.util.ArrayList;

import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.codegeneration.diagram.bean.FieldWrapper;
import clonepedia.codegeneration.diagram.bean.IElement;
import clonepedia.codegeneration.diagram.bean.MethodWrapper;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;

public class AutoGenCTUtil {
	/**
	 * Jaccard coefficient
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static double computeStringSimilarity(ArrayList<String> list1, ArrayList<String> list2){
		
		if(list1.size() == 0 && list2.size() == 0){
			return 0;
		}
		
		if(null == list1 || null == list2)
			return 0;
		
		double count = 0.0;
		for(String str: list1){
			if(list2.contains(str))
				count++;
		}
		
		return count/(list1.size() + list2.size() - count);
	}
	
	public static double computeStringSimilarity(String[] array1, String[] array2){
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		
		for(String str: array1){
			list1.add(str);
		}
		for(String str: array2){
			list2.add(str);
		}
		
		return computeStringSimilarity(list1, list2);
	}
	
	public static double getThreashold(IElement element){
		if(element instanceof TypeWrapper){
			return AutoGenCTSettings.retrieveInnerClassCorrespondingThreshold();
		}
		else if(element instanceof MethodWrapper){
			return AutoGenCTSettings.retrieveMethodCorrespondingThreshold();
		}
		else if(element instanceof FieldWrapper){
			return AutoGenCTSettings.retrieveFieldCorrespondingThreshold();
		}
		else{
			System.err.println("No such type of element, only supporting for type, method and field");
			return -1;
		}
	}
}
