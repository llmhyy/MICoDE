package clonepedia.templategeneration.routine;

public interface DTDSchema {

	static String UNKNOW = "?";
	
	static String TEMPLATE = "template";
	static String DESIGN = "design";
	static String DESIGN_NAME = "name";
	static String DESIGN_ID = "id";
	static String DESCRIPTION = "description";
	static String MULTISET = "multiset";
	static String MULTISET_ID = "id";
	static String CORRESPONDINGSET = "correspondingSet";
	static String PROGRAMELEMENT = "programElement";
	static String PROGRAMELEMENT_NAME = "name";
	static String PROGRAMELEMENT_TYPE = "type";
	static String PROGRAMELEMENT_TYPE_TYPE = "1";
	static String PROGRAMELEMENT_TYPE_METHOD = "2";
	static String PROGRAMELEMENT_TYPE_FIELD = "3";
	static String PROGRAMELEMENT_LOCATION = "location";
	static String PROGRAMELEMENT_STARTLINE = "startLine";
	static String PROGRAMELEMENT_ENDLINE = "endLine";
	static String PROGRAMELEMENT_HANDLE = "handler";
	static String PROGRAMELEMENT_KEY = "key";
	static String PROGRAMELEMENT_APPLICATIONLOCATION = "applicationLocation";
	
	static String CALLERSETS = "callerSets";
	static String CALLERID = "callerId";
	static String CALLERID_ID = "id";
	static String CALLEESETS = "calleeSets";
	static String CALLEEID = "calleeId";
	static String CALLEEID_ID = "id";
	static String ABSTRACTELEMENT = "abstractElement";
	static String ABSTRACTTYPE = "abstractType";
	static String ABSTRACTTYPE_NAME = "name";
	static String ABSTRACTTYPE_ISCLASS = "isClass";
	static String ABSTRACTTYPE_SUPERCLASS = "superClass";
	static String ABSTRACTTYPE_PACKAGENAME = "packageName";
	
	static String INTERFACE = "interface";
	static String INTERFACE_NAME = "name";
	
	static String ABSTRACTMETHOD = "abstractMethod";
	static String ABSTRACTMETHOD_NAME = "name";
	static String ABSTRACTMETHOD_RETURNTYPE = "returnType";
	
	static String PARAMETER = "parameter";
	static String PARAMETER_TYPE = "paramType";
	static String PARAMETER_NAME = "paramName";
	
	static String ABSTRACTFIELD = "abstractField";
	static String ABSTRACTFIELD_NAME = "name";
	static String ABSTRACTFIELD_TYPE = "type";
	
}
