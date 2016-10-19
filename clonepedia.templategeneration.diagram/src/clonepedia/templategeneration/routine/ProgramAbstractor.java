package clonepedia.templategeneration.routine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import clonepedia.codegeneration.diagram.bean.AbstractField;
import clonepedia.codegeneration.diagram.bean.AbstractMethod;
import clonepedia.codegeneration.diagram.bean.AbstractType;
import clonepedia.codegeneration.diagram.bean.DesignList;
import clonepedia.codegeneration.diagram.bean.FieldWrapper;
import clonepedia.codegeneration.diagram.bean.IElement;
import clonepedia.codegeneration.diagram.bean.MethodWrapper;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.Parameter;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;
import clonepedia.util.MinerUtil;

public class ProgramAbstractor {
	public DesignList abstractTemplate(DesignList designs){
		for(TemplateDesign design: designs){
			for(Multiset set: design.getDesign()){
				abstracting(set);
			}
		}
		
		return designs;
	}
	
	private void abstracting(Multiset set){
		if(set.isTypeSet()){
			abstractType(set);
			for(Multiset subSet: set.getSubMultisetList()){
				abstracting(subSet);
			}
		}
		else if(set.isFieldSet()){
			abstractField(set);
		}
		else if(set.isMethodSet()){
			abstractMethod(set);
		}
	}
	
	public Multiset abstractType(Multiset typeSet){
		AbstractType abType = new AbstractType();
		
		/**
		 * set class/interface
		 */
		IElement element = typeSet.getCorrespondingSet().get(0);
		abType.setClass(((TypeWrapper)element).isClass());
		
		/**
		 * get package name, name, supertype and interface
		 */
		ArrayList<String> packageNames = new ArrayList<>();
		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> supertypes = new ArrayList<>();
		ArrayList<ArrayList<String>> interfacesList = new ArrayList<>();
		for(IElement ele: typeSet.getCorrespondingSet()){
			TypeWrapper typeWrapper = (TypeWrapper)ele;
			
			packageNames.add(typeWrapper.getPackageName());
			names.add(typeWrapper.getName());
			supertypes.add(typeWrapper.getDirectSuperType());
			interfacesList.add(typeWrapper.getDirectInterfaces());
		}
		
		String abPackageName = MinerUtil.generateAbstractString(packageNames, MinerUtil.DotSplitting);
		String abName = MinerUtil.generateAbstractString(names, MinerUtil.CamelSplitting);
		String abSuperType = abstractSuperType(supertypes);
		ArrayList<String> abInterfaces = intersectStringSet(interfacesList);
		
		abType.setPackageName(abPackageName);
		abType.setName(abName);
		abType.setSuperClass(abSuperType);
		abType.setInterfaces(abInterfaces);
		
		typeSet.setAbstractElement(abType);
		
		return typeSet;
	}
	
	private ArrayList<String> intersectStringSet(ArrayList<ArrayList<String>> stringsList){
		ArrayList<String> strings = stringsList.get(0);
		Iterator<String> iterator = strings.iterator();
		while(iterator.hasNext()){
			String string = iterator.next();
			for(int i=1; i<stringsList.size(); i++){
				ArrayList<String> stringSet = stringsList.get(i);
				
				if(!stringSet.contains(string) && strings.contains(string)){
					iterator.remove();
				}
			}
		}
		return strings;
	}
	
	private ArrayList<Parameter> intersect(ArrayList<ArrayList<Parameter>> paramsList) {
		ArrayList<Parameter> intersectParameters = paramsList.get(0);
		Iterator<Parameter> iterator = intersectParameters.iterator();
		while(iterator.hasNext()){
			Parameter param = iterator.next();
			for(int i=1; i<paramsList.size(); i++){
				ArrayList<Parameter> paramSet = paramsList.get(i);
				
				ArrayList<String> paramTypeSet = new ArrayList<>();
				for(Parameter p: paramSet){
					paramTypeSet.add(p.getParameterType());
				}
				if(!paramTypeSet.contains(param.getParameterType()) && intersectParameters.contains(param)){
					iterator.remove();
				}
			}
		}
		return intersectParameters;
	}

	/**
	 * currently, I just find the most frequent super type. However, it should be to construct
	 * a hierarchical tree (from type to super type, and etc.), traverse all the non-leaf node
	 * to see which non-leaf node own the most non-leaf nodes.
	 * @param superTypes
	 * @return
	 */
	private String abstractSuperType(ArrayList<String> superTypes){
		HashMap<String, Integer> map = new HashMap<>();
		for(String superType: superTypes){
			Integer times = map.get(superType);
			if(times == null){
				times = 0;
			}
			else{
				times++;
			}
			map.put(superType, times);
		}
		
		int count = 0;
		String abType = null;
		for(String key: map.keySet()){
			int times = map.get(key);
			if(times > count){
				count = times;
				abType = key;
			}
		}
		
		return abType;
	}
	
	public Multiset abstractMethod(Multiset methodSet){
		AbstractMethod abMethod = new AbstractMethod();
		
		ArrayList<String> types = new ArrayList<>();
		ArrayList<String> names = new ArrayList<>();
		ArrayList<ArrayList<Parameter>> parametersList = new ArrayList<>();
		
		for(IElement element: methodSet.getCorrespondingSet()){
			MethodWrapper methodWrapper = (MethodWrapper)element;
			types.add(methodWrapper.getReturnType());
			names.add(methodWrapper.getMethodName());
			parametersList.add(methodWrapper.getParameters());
		}
		
		String abReturnType = MinerUtil.generateAbstractString(types, MinerUtil.CamelSplitting);
		String abName = MinerUtil.generateAbstractString(names, MinerUtil.CamelSplitting);
		ArrayList<Parameter> abParameters = intersect(parametersList);
		
		abMethod.setName(abName);
		abMethod.setReturnType(abReturnType);
		abMethod.setParameters(abParameters);
		
		methodSet.setAbstractElement(abMethod);
		return methodSet;
	}
	
	public Multiset abstractField(Multiset fieldSet){
		AbstractField abField = new AbstractField();
		
		ArrayList<String> types = new ArrayList<>();
		ArrayList<String> names = new ArrayList<>();

		for(IElement element: fieldSet.getCorrespondingSet()){
			FieldWrapper fieldWrapper = (FieldWrapper)element;
			types.add(fieldWrapper.getFieldType());
			names.add(fieldWrapper.getFieldName());
		}
		
		String abType = MinerUtil.generateAbstractString(types, MinerUtil.CamelSplitting);
		String abName = MinerUtil.generateAbstractString(names, MinerUtil.CamelSplitting);
		
		abField.setName(abName);
		abField.setType(abType);
		
		fieldSet.setAbstractElement(abField);
		return fieldSet;
	}
}
