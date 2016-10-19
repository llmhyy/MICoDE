package clonepedia.codegeneration.diagram.bean;

public class Parameter{
	private String parameterType;
	private String parameterName;
	
	public Parameter(String parameterType, String parameterName){
		this.parameterName = parameterName;
		this.parameterType = parameterType;
	}
	
	/**
	 * @return the parameterType
	 */
	public String getParameterType() {
		return parameterType;
	}
	/**
	 * @param parameterType the parameterType to set
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	/**
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}
	/**
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	
}
