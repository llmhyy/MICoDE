package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class AbstractType extends AbstractProgramElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9204915399883407896L;
	private boolean isClass;
	private String superClass;
	private ArrayList<String> interfaces = new ArrayList<>();
	private String packageName;
	
	public String getSimpleName(){
		return name.substring(name.lastIndexOf(".")+1, name.length());
	}
	
	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the isClass
	 */
	public boolean isClass() {
		return isClass;
	}
	/**
	 * @param isClass the isClass to set
	 */
	public void setClass(boolean isClass) {
		this.isClass = isClass;
	}
	/**
	 * @return the superClass
	 */
	public String getSuperClass() {
		return superClass;
	}
	/**
	 * @param superClass the superClass to set
	 */
	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}
	/**
	 * @return the interfaces
	 */
	public ArrayList<String> getInterfaces() {
		return interfaces;
	}
	/**
	 * @param interfaces the interfaces to set
	 */
	public void setInterfaces(ArrayList<String> interfaces) {
		this.interfaces = interfaces;
	}
	
	
}
