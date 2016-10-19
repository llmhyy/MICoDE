package template_model.diagram.util;

import template_model.diagram.preferences.TemplateGenerationPreferencePage;
import clonepedia.Activator;
import clonepedia.codegeneration.diagram.bean.DesignList;
import clonepedia.model.ontology.CloneSets;

public class AutoGenCTSettings {
	//public static String templateFileLocation = "E:\\eclipse\\configurations\\jhotdraw7_2\\data.xml";
	
//	public static String[] projectLocation = new String[]{
//		//"F:\\workspace\\runtime-autogenct\\Clonepedia\\src\\clonepedia"
//		//"E:\\workspace\\runtime-micode\\JHotDraw7.0.6\\src\\org\\jhotdraw"
//		"E:\\workspace\\runtime-micode\\JFreeChart\\src\\org\\jfree"
//	};
//	
//	public static String[] excludeProjectLocation = new String[]{
//		"E:\\workspace\\runtime-micode\\JHotDraw7.0.6\\src\\org\\jhotdraw\\samples"
//	};
	
	private static String retriveValue(String key){
		return Activator.getDefault().getPreferenceStore().getString(key);	
	}
	
	private static boolean isValidate(String value){
		return value != null &&
				value.length() != 0;
	}
	
	public static double retrieveClusteringDistanceThreshold(){
		String value = retriveValue(TemplateGenerationPreferencePage.TYPE_CLUSTERING_THRESHOLD);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 5;
		}
	}
	
	public static double retrieveTypeNameSimilarity(){
		String value = retriveValue(TemplateGenerationPreferencePage.TYPE_NAME_SIMILARITY);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 35;
		}
	}
	
	public static double retrieveTypeSuperClassSimilarity(){
		String value = retriveValue(TemplateGenerationPreferencePage.TYPE_SUPER_CLASS_SIMILARITY);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 15;
		}
	}
	
	public static double retrieveTypeInterfaceSimilarity(){
		String value = retriveValue(TemplateGenerationPreferencePage.TYPE_INTERFACE_SIMILARITY);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 15;
		}
	}
	
	public static double retrieveTypeBodySimilarity(){
		String value = retriveValue(TemplateGenerationPreferencePage.TYPE_BODY_SIMILARITY);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 35;
		}
	}
	
	public static double retrieveConnectingThreshold(){
		String value = retriveValue(TemplateGenerationPreferencePage.CONNECTING_THRESHOLD);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 0.5;
		}
	}
	
	public static double retrieveMethodCorrespondingThreshold(){
		String value = retriveValue(TemplateGenerationPreferencePage.METHOD_CORRESPONDING_THRESHOLD);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 0.8;
		}
	}
	
	public static double retrieveFieldCorrespondingThreshold(){
		String value = retriveValue(TemplateGenerationPreferencePage.FIELD_CORRESPONDING_THRESHOLD);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 0.6;
		}
	}
	
	public static double retrieveInnerClassCorrespondingThreshold(){
		String value = retriveValue(TemplateGenerationPreferencePage.INNER_TYPE_CORRESPONDING_THRESHOLD);
		if(isValidate(value)){
			return Double.valueOf(value);
		}
		else{
			return 0.5;
		}
	}
	
	public static String retrieveScopePath(){
		String value = retriveValue(TemplateGenerationPreferencePage.SCOPE_PATH);
		return value;
	}
	
	public static String retrieveTargetProject(){
		String value = retriveValue(TemplateGenerationPreferencePage.TARGET_PORJECT);
		return value;
	}
	
	public static String retrieveTemplateFileLocation(){
		String value = retriveValue(TemplateGenerationPreferencePage.TEMPLATE_FILE_LOCATION);
		return value;
	}
	
	public static String retrieveExcludePath(){
		String value = retriveValue(TemplateGenerationPreferencePage.EXCLUDE_PATH);
		if(isValidate(value)){
			return value;			
		}
		else{
			return "$";
		}
	}
	
//	public static String[] projectLocation = new String[]{
//		"F:\\workspace\\runtime-autogenct\\jhotdraw7_2\\src\\org\\jhotdraw\\samples\\draw",
//		"F:\\workspace\\runtime-autogenct\\jhotdraw7_2\\src\\org\\jhotdraw\\samples\\net",
//		"F:\\workspace\\runtime-autogenct\\jhotdraw7_2\\src\\org\\jhotdraw\\samples\\odg",
//		"F:\\workspace\\runtime-autogenct\\jhotdraw7_2\\src\\org\\jhotdraw\\samples\\pert",
//		"F:\\workspace\\runtime-autogenct\\jhotdraw7_2\\src\\org\\jhotdraw\\samples\\svg",
//		"F:\\workspace\\runtime-autogenct\\jhotdraw7_2\\src\\org\\jhotdraw\\samples\\teddy",
//	};
	
	/*public static String[] projectLocation = new String[]{
		"F:\\workspace\\runtime-autogenct\\ArcheryStartActivity",
		"F:\\workspace\\runtime-autogenct\\BridgeTheWallActivity",
		"F:\\workspace\\runtime-autogenct\\BubbleArchor",
		"F:\\workspace\\runtime-autogenct\\KnockDown",
		"F:\\workspace\\runtime-autogenct\\Pandaslide"
	};*/
	
	/*public static String[] projectLocation = new String[]{
		"F:\\workspace\\runtime-EclipseApplication(1)\\JHotDraw7.0.6\\src\\org\\jhotdraw\\samples\\draw",
		"F:\\workspace\\runtime-EclipseApplication(1)\\JHotDraw7.0.6\\src\\org\\jhotdraw\\samples\\net",
		"F:\\workspace\\runtime-EclipseApplication(1)\\JHotDraw7.0.6\\src\\org\\jhotdraw\\samples\\pert",
		"F:\\workspace\\runtime-EclipseApplication(1)\\JHotDraw7.0.6\\src\\org\\jhotdraw\\samples\\svg",
	};*/
	
//	public static double typeCorrespondingThreshold = 0.2;
//	public static double fieldCorrespondingThreshold = 0.6;
//	public static double methodCorrespondingThreshold = 0.8;
	
//	public static CloneSets cloneSets = new CloneDetectionFileParser(false, "").getCloneSets();
	public static CloneSets cloneSets;
	
	/**
	 * Given two multisets, I regard they have calling relation if and only if there are at least
	 * *callingStrength* invocations between these two multisets. 
	 */
	public static double callingStrength = 2;
	
	//public static String tempDataLocation = "E:\\eclipse\\configurations\\JHotDraw7\\data.xml";
	
	
	public static DesignList designs = new DesignList();
}
