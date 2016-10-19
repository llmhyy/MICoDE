package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;

public class AbstractField extends AbstractProgramElement implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 8089205743455209330L;
	private String type;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
