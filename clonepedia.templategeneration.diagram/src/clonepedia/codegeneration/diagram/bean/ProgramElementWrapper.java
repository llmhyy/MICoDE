package clonepedia.codegeneration.diagram.bean;

import java.io.Serializable;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import clonepedia.templategeneration.diagram.util.AutoGenCTUtil;
import clonepedia.util.MinerUtil;

public abstract class ProgramElementWrapper implements IElement, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2736693905437538935L;
	private TypeWrapper ownerType;
	private boolean marked;
	private Multiset belongingMultiset;
	
	private String location;
	private int startLine;
	private int endLine;
	
	public abstract ASTNode getCorrespondingASTNode();
	
	public abstract String getASTHandle();
	
	public abstract String getKey();

	protected void setLocaleInfo(ASTNode node){
		CompilationUnit unit = getCompilationUnit(node);
		String location = unit.getJavaElement().getResource().getLocation().toString();
		int startLine = unit.getLineNumber(node.getStartPosition());
		int endLine = unit.getLineNumber(node.getStartPosition()+node.getLength());
		
		setLocation(location);
		setStartLine(startLine);
		setEndLine(endLine);
		
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the startLine
	 */
	public int getStartLine() {
		return startLine;
	}

	/**
	 * @param startLine the startLine to set
	 */
	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	/**
	 * @return the endLine
	 */
	public int getEndLine() {
		return endLine;
	}

	/**
	 * @param endLine the endLine to set
	 */
	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	protected double computeNameSimilarity(String name1, String name2){
		String[] id1 = MinerUtil.splitString(MinerUtil.CamelSplitting, name1);
		String[] id2 = MinerUtil.splitString(MinerUtil.CamelSplitting, name2);
		
		return AutoGenCTUtil.computeStringSimilarity(id1, id2);
	}
	
	/**
	 * @return the belongingMultiset
	 */
	public Multiset getBelongingMultiset() {
		return belongingMultiset;
	}

	/**
	 * @param belongingMultiset the belongingMultiset to set
	 */
	public void setBelongingMultiset(Multiset belongingMultiset) {
		this.belongingMultiset = belongingMultiset;
	}

	/**
	 * @return the marked
	 */
	public boolean isMarked() {
		return marked;
	}

	/**
	 * @param marked the marked to set
	 */
	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	/**
	 * @return the ownerType
	 */
	public TypeWrapper getOwnerType() {
		return ownerType;
	}

	/**
	 * @param ownerType the ownerType to set
	 */
	public void setOwnerType(TypeWrapper ownerType) {
		this.ownerType = ownerType;
	}
	
	public CompilationUnit getCompilationUnit(ASTNode node){
		if(node == null) return null;
		
		ASTNode parent = node.getParent();
		if(parent == null){
			return (CompilationUnit)node;
		}
		
		while(parent.getParent() != null){
			parent = parent.getParent();
		}
		
		return (CompilationUnit)parent;
	}
}
