package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;

public class AbstractProgramElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7217953604434573527L;
	protected String name;

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
	 
	public String toString(){
		return name;
	}
}
