package clonepedia.templategeneration.routine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import clonepedia.codegeneration.diagram.bean.AbstractField;
import clonepedia.codegeneration.diagram.bean.AbstractMethod;
import clonepedia.codegeneration.diagram.bean.AbstractProgramElement;
import clonepedia.codegeneration.diagram.bean.AbstractType;
import clonepedia.codegeneration.diagram.bean.Application;
import clonepedia.codegeneration.diagram.bean.FieldWrapper;
import clonepedia.codegeneration.diagram.bean.IElement;
import clonepedia.codegeneration.diagram.bean.MemberWrapper;
import clonepedia.codegeneration.diagram.bean.MethodWrapper;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.Parameter;
import clonepedia.codegeneration.diagram.bean.ProgramElementWrapper;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import clonepedia.codegeneration.diagram.bean.TypeWrapper;

public class DesignXMLWriter implements DTDSchema{
	
	private static String encode = "UTF-8";
	public static boolean design2XML(ArrayList<TemplateDesign> designs, String filePath){
		
		Document doc = getDoc();
		boolean docCreated = write2doc(designs, doc);
		boolean docWritten = write2file(doc, encode, filePath);
		return docCreated & docWritten;
	}
	
	private static boolean write2file(Document doc, String encode2, String filePath) {
		
		checkFile(filePath);
		
		boolean flag = true;
		try {
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer();
		    /** encode */
//		    transformer.setOutputProperty(OutputKeys.ENCODING, encode2);
		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult(new File(filePath));
		    transformer.transform(source, result);
			
		} catch (TransformerConfigurationException e) {
			flag = false;
			e.printStackTrace();
		} catch (TransformerException e) {
			flag = false;
			e.printStackTrace();
		}
		
		return flag;
	}

	private static void checkFile(String path) {
		File f = new File(path);
		if(!f.exists()){
			String dirPath = path.substring(0,path.lastIndexOf("\\"));
			File dir = new File(dirPath);
			if(!dir.exists())
				dir.mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static boolean write2doc(ArrayList<TemplateDesign> designs,
			Document doc) {
		
		Element template = doc.createElement(TEMPLATE);
		doc.appendChild(template);
		
		for(TemplateDesign td : designs){
			Element design = doc.createElement(DESIGN);
			
			String designName = "reusable design";
			if(td.getName() != null && td.getName().length() > 0){
				designName = td.getName();
			}
			
			design.setAttribute(DESIGN_NAME, designName);
			design.setAttribute(DESIGN_ID, td.getID());
			write2design(doc, design, td);
			template.appendChild(design);
		}
		
		return true;
	}
	
	private static void write2design(Document doc, Element design, TemplateDesign td) {
		//add description and description text.
		Element description = doc.createElement(DESCRIPTION);
		Text descriptionText = doc.createTextNode(td.getDescription()+" ");
		
		description.appendChild(descriptionText);
		design.appendChild(description);
		
		//add multiset
		//There should be only one multiset.
		for(Multiset ms : td.getDesign()){
			write2multiset(doc, design, ms);
		}
	}

	private static void write2multiset(Document doc, Element parent,
			Multiset ms) {
		Element multiset = doc.createElement(MULTISET);
		multiset.setAttribute(MULTISET_ID, ms.getId());
		
		//add correspondingSet
		ArrayList<IElement> cs = ms.getCorrespondingSet();
		Element correspondingSet = doc.createElement(CORRESPONDINGSET);
		write2programElement(doc, correspondingSet, cs);
		multiset.appendChild(correspondingSet);
		
		//add abstractElement
		
		AbstractProgramElement ape = ms.getAbstractElement();
		
		Element abstractElement = doc.createElement(ABSTRACTELEMENT);
		write2abstractElement(doc, abstractElement, ape);
		multiset.appendChild(abstractElement);
		
		//add callerSets
		Element callerSets = doc.createElement(CALLERSETS);
		for(Multiset ci : ms.getCallerSets()){
			Element callerId = doc.createElement(CALLERID);
			callerId.setAttribute(CALLERID_ID, ci.getId());
			callerSets.appendChild(callerId);
		}
		multiset.appendChild(callerSets);
		//add calleeSets
		Element calleeSets = doc.createElement(CALLEESETS);
		for(Multiset ci : ms.getCalleeSets()){
			Element calleeId = doc.createElement(CALLEEID);
			calleeId.setAttribute(CALLEEID_ID, ci.getId());
			calleeSets.appendChild(calleeId);
		}
		multiset.appendChild(calleeSets);
		
		//add multiset
		List<Multiset> subMultisetList = ms.getSubMultisetList();
		if(subMultisetList!=null){
			for(Multiset cms : subMultisetList){
				write2multiset(doc, multiset, cms);
			}
		}
		
		parent.appendChild(multiset);
	}

	private static boolean write2abstractElement(Document doc,
			Element parent, AbstractProgramElement ape) {
		Element element = null;
		if(ape instanceof AbstractField){
			AbstractField af = (AbstractField)ape;
			
			element = doc.createElement(ABSTRACTFIELD);
			element.setAttribute(ABSTRACTFIELD_NAME, af.getName());
			element.setAttribute(ABSTRACTFIELD_TYPE, af.getType());
		}else if(ape instanceof AbstractMethod){
			AbstractMethod am = (AbstractMethod)ape;
			
			element = doc.createElement(ABSTRACTMETHOD);
			element.setAttribute(ABSTRACTMETHOD_NAME, am.getName());
			element.setAttribute(ABSTRACTMETHOD_RETURNTYPE, am.getReturnType());
			
			//parameters.
			List<Parameter> parameters = am.getParameters();
			if(parameters!=null){
				for(Parameter p:parameters){
					Element parameter = doc.createElement(PARAMETER);
					parameter.setAttribute(PARAMETER_TYPE, p.getParameterType());
					parameter.setAttribute(PARAMETER_NAME, p.getParameterName());
					
					element.appendChild(parameter);
				}
			}
			
		}else if(ape instanceof AbstractType){
			AbstractType af = (AbstractType)ape;
			
			element = doc.createElement(ABSTRACTTYPE);
			element.setAttribute(ABSTRACTTYPE_NAME, af.getName());
			element.setAttribute(ABSTRACTTYPE_PACKAGENAME, af.getPackageName());
			element.setAttribute(ABSTRACTTYPE_ISCLASS, (new Boolean(af.isClass())).toString());
			element.setAttribute(ABSTRACTTYPE_SUPERCLASS, af.getSuperClass());
			
			//interfaces
			List<String> interfaces = af.getInterfaces();
			if(interfaces!=null){
				for(String i:interfaces){
					Element interface_ = doc.createElement(INTERFACE);
					interface_.setAttribute(INTERFACE_NAME, i);
					
					element.appendChild(interface_);
				}
			}
		}else
			return false;
		
		parent.appendChild(element);
		return true;
	}

	private static void write2programElement(Document doc,
			Element parent, List<IElement> arrayList) {
		for(IElement ie : arrayList){
			if(ie instanceof ProgramElementWrapper){
				ProgramElementWrapper pe = (ProgramElementWrapper)ie;
				Element programElement = doc.createElement(PROGRAMELEMENT);
				if(pe instanceof TypeWrapper){
					TypeWrapper tw = (TypeWrapper)pe;
					programElement.setAttribute(PROGRAMELEMENT_NAME, tw.getName());
					programElement.setAttribute(PROGRAMELEMENT_TYPE, PROGRAMELEMENT_TYPE_TYPE);
					programElement.setAttribute(PROGRAMELEMENT_STARTLINE, (new Integer(tw.getStartLine())).toString());
					programElement.setAttribute(PROGRAMELEMENT_ENDLINE, (new Integer(tw.getEndLine())).toString());
					programElement.setAttribute(PROGRAMELEMENT_LOCATION, tw.getLocation());
					programElement.setAttribute(PROGRAMELEMENT_HANDLE, tw.getASTHandle());
					programElement.setAttribute(PROGRAMELEMENT_KEY, tw.getKey());
					
					Application app = tw.getApplication();
					if(app!=null)
						programElement.setAttribute(PROGRAMELEMENT_APPLICATIONLOCATION, 
								tw.getApplication().getApplicationLocation());
					
//					//
//					List<IElement> cs = tw.getMembers();
//					if(cs!=null){
//						write2programElement(doc, programElement, cs);
//					}
				}else if(pe instanceof MemberWrapper){
					if(pe instanceof FieldWrapper){
						FieldWrapper fw = (FieldWrapper)pe;
						programElement.setAttribute(PROGRAMELEMENT_NAME, fw.getFieldName());
						programElement.setAttribute(PROGRAMELEMENT_TYPE, PROGRAMELEMENT_TYPE_FIELD);
						programElement.setAttribute(PROGRAMELEMENT_STARTLINE, (new Integer(fw.getStartLine())).toString());
						programElement.setAttribute(PROGRAMELEMENT_ENDLINE, (new Integer(fw.getEndLine())).toString());
						programElement.setAttribute(PROGRAMELEMENT_LOCATION, fw.getLocation());
						programElement.setAttribute(PROGRAMELEMENT_HANDLE, fw.getASTHandle());
						programElement.setAttribute(PROGRAMELEMENT_KEY, fw.getKey());
						
					}else if(pe instanceof MethodWrapper){
						MethodWrapper mw = (MethodWrapper)pe;
						programElement.setAttribute(PROGRAMELEMENT_NAME, mw.getMethodName());
						programElement.setAttribute(PROGRAMELEMENT_TYPE, PROGRAMELEMENT_TYPE_METHOD);
						programElement.setAttribute(PROGRAMELEMENT_STARTLINE, (new Integer(mw.getStartLine())).toString());
						programElement.setAttribute(PROGRAMELEMENT_ENDLINE, (new Integer(mw.getEndLine())).toString());
						programElement.setAttribute(PROGRAMELEMENT_LOCATION, mw.getLocation());
						programElement.setAttribute(PROGRAMELEMENT_HANDLE, mw.getASTHandle());
						programElement.setAttribute(PROGRAMELEMENT_KEY, mw.getKey());
						
					}
				}
				parent.appendChild(programElement);
			}
		}
		
	}

	private static Document getDoc(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = builder.newDocument();
		return doc;
	}

}
