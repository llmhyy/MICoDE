package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class AbstractMethod extends AbstractProgramElement implements Serializable{
	
	private static final long serialVersionUID = 7596253159641413665L;
	private String returnType;
	private ArrayList<Parameter> parameters = new ArrayList<>();
	/**
	 * @return the returnType
	 */
	public String getReturnType() {
		return returnType;
	}
	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	/**
	 * @return the parameters
	 */
	public ArrayList<Parameter> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(ArrayList<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	
}
