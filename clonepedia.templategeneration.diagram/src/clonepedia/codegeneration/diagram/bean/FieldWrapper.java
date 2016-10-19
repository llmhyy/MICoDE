package clonepedia.codegeneration.diagram.bean;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FieldWrapper extends MemberWrapper{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5204321553499734006L;
	private FieldDeclaration fieldDeclaration;

	public FieldWrapper(FieldDeclaration fieldDeclaration){
		this.fieldDeclaration = fieldDeclaration;
		setLocaleInfo(fieldDeclaration);
	}
	
	public String toString(){
		return getFieldName() + "(field)";
	}
	
	public VariableDeclarationFragment getFirstFragment(){
		return (VariableDeclarationFragment) this.fieldDeclaration.fragments().get(0);
	}
	
	public String getFieldName(){
		VariableDeclarationFragment fragment = getFirstFragment();
		return fragment.getName().getFullyQualifiedName();
	}
	
	public String getFieldType(){
		VariableDeclarationFragment fragment = getFirstFragment();
		return fragment.resolveBinding().getType().getQualifiedName();
	}
	
	/**
	 * @return the fieldDeclaration
	 */
	public FieldDeclaration getFieldDeclaration() {
		return fieldDeclaration;
	}

	/**
	 * @param fieldDeclaration the fieldDeclaration to set
	 */
	public void setFieldDeclaration(FieldDeclaration fieldDeclaration) {
		this.fieldDeclaration = fieldDeclaration;
	}

	
	/**
	 * two dimensions, type and name, whose weight distribution is 50:50.
	 */
	@Override
	public double computeSimilarity(IElement element) {
		if(!(element instanceof FieldWrapper)){
			return 0;
		}
		
		//TODO
		FieldWrapper thatField = (FieldWrapper)element;
		double nameWeight = 50;
		double typeWeight = 50;

		double nameSim = computeNameSimilarity(this.getFieldName(), thatField.getFieldName());
		
		ITypeBinding thisTypeBinding = this.fieldDeclaration.getType().resolveBinding();
		ITypeBinding thatTypeBinding = thatField.getFieldDeclaration().getType().resolveBinding();
		double typeSim = computeTypeSimilarity(thisTypeBinding, thatTypeBinding);
		
		double overallSim = (nameWeight*nameSim + typeWeight*typeSim)/(nameWeight+typeWeight);
		return overallSim;
	}

	@Override
	public ASTNode getCorrespondingASTNode() {
		return fieldDeclaration;
	}

	@Override
	public String getASTHandle() {
		
		/*CompilationUnit unit = (CompilationUnit)fieldDeclaration.getRoot();
		IField iField = (IField) getFirstFragment().resolveBinding().getJavaElement();
		ASTNode node = unit.findDeclaringNode(iField.getKey());*/
		
		return getFirstFragment().resolveBinding().getJavaElement().getHandleIdentifier();
	}

	@Override
	public String getKey() {
		IField iField = (IField) getFirstFragment().resolveBinding().getJavaElement();
		return iField.getKey();
	}
	
}
