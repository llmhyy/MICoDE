package clonepedia.templategeneration.routine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

public class DesignXMLReader  implements DTDSchema{

	private static Map<String, Multiset> multisetMap = 
			new HashMap<String, Multiset>();
	
	private static void cacheMultiset(String id, Multiset m){
		//System.out.println(id+":"+(m==null));
		multisetMap.put(id, m);
	}
	
	private static void clearMultisetCache(){
		multisetMap.clear();
	}
	
	public static List<CallRelationCommission> callRelationRegister = 
			new ArrayList<CallRelationCommission>();
	
	private static void registCallRelationCommission(ArrayList<Multiset> callers,
			String id) {
		callRelationRegister.add(new CallRelationCommission(callers, id));
	}
	
	private static void completeCallRelationCommission(){
		int n=0;
		for(CallRelationCommission crc:callRelationRegister){
			Multiset m = multisetMap.get(crc.multisetId);
			if(m==null){
				n++;
				System.err.println("Miss:"+crc.multisetId);
				continue;
			}
			
			crc.list.add(m);
		}
		if(n>0){
			System.err.println("Miss number:"+n);
		}
		callRelationRegister.clear();
	}
	
	public static DesignList xml2design(String filePath){
		if(!checkFile(filePath))
			return null;
		
		DesignList ret = null;
		
		Document doc = getDoc(filePath);
		
		if(doc==null)
			return null;
		
		ret = readDesignFromXML(doc);
		
		completeCallRelationCommission();
		
		ASTRecover.cleanCache();
		clearMultisetCache();
		
		return ret;
	}
	
	private static ArrayList<Node> getNodes(Element e, String name){
		ArrayList<Node> nl = new ArrayList<Node>();
		NodeList designs = e.getElementsByTagName(name);
		for(int i=0;i<designs.getLength();i++){
			Node n = designs.item(i);
			if(n.getNodeType()==Node.ELEMENT_NODE){
				Element ele = (Element)n;
				if(ele.getParentNode()==e)
					nl.add(n);
			}
		}
		
		return nl;
	}
	
	private static DesignList readDesignFromXML(Document doc) {
		Element root = doc.getDocumentElement();
		
		if(root==null || !TEMPLATE.equals(root.getNodeName()))
				return null;
		
		DesignList ret = new DesignList();
		
		ArrayList<Node> designs = getNodes(root,DESIGN);
		
		for(int i=0;i<designs.size();i++){
			Node n = designs.get(i);
			if(n.getNodeType()==Node.ELEMENT_NODE){
				Element designElement = (Element)n;
				
				TemplateDesign td = new TemplateDesign();
				td.setName(designElement.getAttribute(DESIGN_NAME));
				td.setID(designElement.getAttribute(DESIGN_ID));
				readDesign(td, designElement);
				
				ret.add(td);
			}
		}
		
		return ret;
	}

	private static void readDesign(TemplateDesign td, Element designElement) {
		//add description
		ArrayList<Node> descriptionNodes = getNodes(designElement,
				DESCRIPTION);
		if(descriptionNodes.size()!=1){
			System.err.println("Description Error: size:"+
					descriptionNodes.size()+", design:"+
					td.getName());
			return;
		}else {
			Node  descriptionNode = descriptionNodes.get(0);
			if(descriptionNode.getNodeType()==Node.ELEMENT_NODE){
				Element descriptionElement = (Element)descriptionNode;
				td.setDescription(descriptionElement.getTextContent());
			}
		}
		
		//add multiset
		ArrayList<Node> multisetNodes = getNodes(designElement,
				MULTISET);
		for(int i=0;i<multisetNodes.size();i++){
			
			Node  multisetNode = multisetNodes.get(i);
			if(multisetNode.getNodeType()==Node.ELEMENT_NODE){
				Element multisetElement = (Element)multisetNode;
				Multiset m = new Multiset();
				m.setId(multisetElement.getAttribute(MULTISET_ID));
				
				readMultiset(m, multisetElement);
				
				cacheMultiset(m.getId(), m);
				
				td.add(m);
			}
		}
	}
	
	private static void readMultiset(Multiset m, Element multisetElement) {
		//add correspondingSet
		ArrayList<Node> nodes = getNodes(multisetElement,
				CORRESPONDINGSET);
		for(int i=0;i<nodes.size();i++){
			Node node = nodes.get(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				readCorrespondingSet(m.getCorrespondingSet(), element);
			}else{
				System.err.println("correspondingSet Error: not element, multiset:"+
						m.getId());
				return;
			}
		}
		
		//add AbstractProgramElement
		nodes = getNodes(multisetElement,
				ABSTRACTELEMENT);
//		if(nodes.getLength()!=1){
//			System.err.println("ABSTRACTELEMENT Error: size:"+
//					nodes.getLength()+", multiset:"+
//					m.getId());
//			return;
//		}else {
		for(int i=0;i<nodes.size();i++){
			Node node = nodes.get(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				readAbstractElement(m, element);
			}else{
				System.err.println("correspondingSet Error: not element, multiset:"+
						m.getId());
				return;
			}
		}
		
		//add callerSets
		nodes = getNodes(multisetElement,
				CALLERSETS);
		ArrayList<Multiset> callers = m.getCallerSets();
//		if(nodes.getLength()!=1){
//			System.err.println("callerSets Error: size:"+
//					nodes.getLength()+", multiset:"+
//					m.getId());
//			return;
//		}else {
		for(int j=0;j<nodes.size();j++){
			Node node = nodes.get(j);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				ArrayList<Node> callerIds = getNodes(element,
						CALLERID);
				for(int i=0;i<callerIds.size();i++){
					Node cn = callerIds.get(i);
					Element callerId = (Element)cn;
					
					registCallRelationCommission(callers, callerId.getAttribute(CALLERID_ID));
				}
			}else{
				System.err.println("callerSets Error: not element, multiset:"+
						m.getId());
				return;
			}
		}
		
		//add calleeSets
		nodes = getNodes(multisetElement,
				CALLEESETS);
		ArrayList<Multiset> callees = m.getCalleeSets();
//		if(nodes.getLength()!=1){
//			System.err.println("calleeSets Error: size:"+
//					nodes.getLength()+", multiset:"+
//					m.getId());
//			return;
//		}else {
		for(int j=0;j<nodes.size();j++){
			Node node = nodes.get(j);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				ArrayList<Node> calleeIds = getNodes(element,
						CALLEEID);
				for(int i=0;i<calleeIds.size();i++){
					Node cn = calleeIds.get(i);
					Element calleeId = (Element)cn;
					
					registCallRelationCommission(callees, 
							calleeId.getAttribute(CALLEEID_ID));
				}
			}else{
				System.err.println("calleeSets Error: not element, multiset:"+
						m.getId());
				return;
			}
		}
		
		//add multiset
		nodes = getNodes(multisetElement,
				MULTISET);
		ArrayList<Multiset> subMultisetList = m.getSubMultisetList();
		for(int i=0;i<nodes.size();i++){
			Node node = nodes.get(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				Multiset subM = new Multiset();
				subM.setId(element.getAttribute(MULTISET_ID));
				
				readMultiset(subM, element);
				
				cacheMultiset(subM.getId(), subM);
				
				subMultisetList.add(subM);
			}else{
				System.err.println("subMultiset Error: not element, multiset:"+
						m.getId());
				return;
			}
		}
	}

	private static void readAbstractElement(Multiset m, Element parent) {
		//add abstractElement
		ArrayList<Node> nodes = null;
		if((nodes = getNodes(parent,
				ABSTRACTFIELD))!=null && nodes.size()>0){
			Node node = nodes.get(0);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				
				AbstractField af = new AbstractField();
				af.setName(element.getAttribute(ABSTRACTFIELD_NAME));
				af.setType(element.getAttribute(ABSTRACTFIELD_TYPE));
				m.setAbstractElement(af);
				return;
			}else{
				System.err.println("AbstractElement Error: not element, multiset:"+
						m.getId());
				return;
			}
		}else if((nodes = getNodes(parent,
					ABSTRACTMETHOD))!=null && nodes.size()>0){
			Node node = nodes.get(0);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				
				AbstractMethod am = new AbstractMethod();
				am.setName(element.getAttribute(ABSTRACTMETHOD_NAME));
				am.setReturnType(element.getAttribute(ABSTRACTMETHOD_RETURNTYPE));
				
				readParameters(am, element);
				
				m.setAbstractElement(am);
				return;
			}else{
				System.err.println("AbstractElement Error: not element, multiset:"+
						m.getId());
				return;
			}
		}else if((nodes = getNodes(parent,
				ABSTRACTTYPE))!=null && nodes.size()>0){
			Node node = nodes.get(0);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				
				AbstractType am = new AbstractType();
				am.setPackageName(element.getAttribute(ABSTRACTTYPE_PACKAGENAME));
				am.setName(element.getAttribute(ABSTRACTMETHOD_NAME));
				am.setClass("true".equals(element.getAttribute(ABSTRACTTYPE_ISCLASS)));
				am.setSuperClass(element.getAttribute(ABSTRACTTYPE_SUPERCLASS));
				
				readInterface(am, element);
				
				m.setAbstractElement(am);
				return;
			}else{
				System.err.println("AbstractElement Error: not element, multiset:"+
						m.getId());
				return;
			}
		}else{
			System.err.println("AbstractElement Error: no available abstract element, multiset:"+
					m.getId());
			return;
		}
		
	}

	private static void readInterface(AbstractType am, Element parent) {
		ArrayList<Node> nodes = getNodes(parent,
				INTERFACE);
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<nodes.size();i++){
			Node node = nodes.get(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				list.add(element.getAttribute(INTERFACE_NAME));
				am.setInterfaces(list);
			}
		}
	}

	private static void readParameters(AbstractMethod am, Element parent) {
		ArrayList<Node> nodes = getNodes(parent,
				PARAMETER);
		ArrayList<Parameter> list = am.getParameters();
		for(int i=0;i<nodes.size();i++){
			Node node = nodes.get(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element)node;
				Parameter param = new Parameter(element.getAttribute(PARAMETER_TYPE), 
						element.getAttribute(PARAMETER_NAME));
				
				list.add(param);
				am.setParameters(list);
			}
		}
	}

	private static void readCorrespondingSet(
			ArrayList<IElement> correspondingSet, Element element) {
		//add correspondingSet
		ArrayList<Node> nodes = getNodes(element,
				PROGRAMELEMENT);
		
		for(int i=0;i<nodes.size();i++){
			Node node = nodes.get(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element e = (Element)node;
				
				String programElementType = e.getAttribute(PROGRAMELEMENT_TYPE);
				if(PROGRAMELEMENT_TYPE_TYPE.equals(programElementType)){
					try {
						ASTNode n = ASTRecover.recover(e.getAttribute(PROGRAMELEMENT_HANDLE), e.getAttribute(PROGRAMELEMENT_KEY));
						TypeWrapper tw = new TypeWrapper((TypeDeclaration)n);
						correspondingSet.add(tw);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(PROGRAMELEMENT_TYPE_METHOD.equals(programElementType)){
					try {
						ASTNode n = ASTRecover.recover(e.getAttribute(PROGRAMELEMENT_HANDLE), e.getAttribute(PROGRAMELEMENT_KEY));
						MethodWrapper mw = new MethodWrapper(
								(MethodDeclaration) n);
						correspondingSet.add(mw);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(PROGRAMELEMENT_TYPE_FIELD.equals(programElementType)){
					try {
						ASTNode n = ASTRecover.recover(e.getAttribute(PROGRAMELEMENT_HANDLE), e.getAttribute(PROGRAMELEMENT_KEY));
						if(n instanceof VariableDeclaration){
							n = n.getParent();
						}
						FieldDeclaration fd = (FieldDeclaration)n;
						FieldWrapper fw = new FieldWrapper(fd);
						correspondingSet.add(fw);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					System.err.println("PROGRAMELEMENT Error: not any kind element.");
					return;
				}
				
			}else{
				System.err.println("ProgramElement Error: not element");
				return;
			}
		}
		
	}

	private static Document getDoc(String filePath) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(filePath);
			
			return doc;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private static boolean checkFile(String filePath){
		File f = new File(filePath);
		if(f.exists())
			return true;
		else
			return false;
	}
}



class CallRelationCommission{
	ArrayList<Multiset> list = null;
	String multisetId = null;
	public CallRelationCommission(ArrayList<Multiset> l,
			String id){
		list = l;
		multisetId = id;
	}
}
