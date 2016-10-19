package clonepedia.codegeneration.diagram.bean;

import java.util.ArrayList;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;

import clonepedia.templategeneration.diagram.util.AutoGenCTUtil;

public class MethodWrapper extends MemberWrapper{
	
	private static final long serialVersionUID = -7882508536401162475L;
	private MethodDeclaration methodDeclaration;

	public MethodWrapper(MethodDeclaration methodDeclaration){
		this.methodDeclaration = methodDeclaration;
		setLocaleInfo(methodDeclaration);
	}
	
	public String toString(){
		return this.methodDeclaration.getName().getFullyQualifiedName() + "(method)";
	}
	
	public String getReturnType(){
		if(methodDeclaration.getReturnType2() != null){
			return this.methodDeclaration.getReturnType2().toString();
		}
		else return "";
	}
	
	public String getMethodName(){
		return this.methodDeclaration.getName().getFullyQualifiedName();
	}
	
	public ArrayList<Parameter> getParameters(){
		ArrayList<Parameter> parameters = new ArrayList<>();
		for(Object obj: this.methodDeclaration.parameters()){
			SingleVariableDeclaration svd = (SingleVariableDeclaration)obj;
			Type type = svd.getType();
			SimpleName paramName = svd.getName();
			
			Parameter param = new Parameter(type.toString(), paramName.getIdentifier());
			parameters.add(param);
		}
		
		return parameters;
	}
	
	/**
	 * @return the methodDeclaration
	 */
	public MethodDeclaration getMethodDeclaration() {
		return methodDeclaration;
	}

	/**
	 * @param methodDeclaration the methodDeclaration to set
	 */
	public void setMethodDeclaration(MethodDeclaration methodDeclaration) {
		this.methodDeclaration = methodDeclaration;
	}

	/**
	 * four dimensions: return type, method name, parameters.
	 */
	@Override
	public double computeSimilarity(IElement elementWrapper) {
		if(!(elementWrapper instanceof MethodWrapper)){
			return 0;
		}
		
		MethodWrapper thatMethod = (MethodWrapper)elementWrapper;
		
		double nameWeight = 50;
		double returnTypeWeight = 25;
		double parameterWeight = 25;
		
		double nameSim = computeNameSimilarity(this.getMethodName(), thatMethod.getMethodName());
		
		ITypeBinding thisReturnTypeBinding = methodDeclaration.resolveBinding().getReturnType();
		ITypeBinding thatReturnTypeBinding = thatMethod.getMethodDeclaration().resolveBinding().getReturnType();
		double returnTypeSim = computeTypeSimilarity(thisReturnTypeBinding, thatReturnTypeBinding);
		
		ArrayList<String> params1 = getParameterTypes();
		ArrayList<String> params2 = thatMethod.getParameterTypes();
		double parameterSim = computeParameterSim(params1, params2);
		
		double numerator = nameWeight*nameSim + returnTypeWeight*returnTypeSim + parameterWeight*parameterSim;
		double denominator = nameWeight + returnTypeWeight + parameterWeight;
		
		return numerator/denominator;
	}

	private ArrayList<String> getParameterTypes() {
		ArrayList<Parameter> parameters = getParameters();
		ArrayList<String> paramTypes = new ArrayList<>();
		for(Parameter param: parameters){
			paramTypes.add(param.getParameterType());
		}
		
		return paramTypes;
	}

	private double computeParameterSim(ArrayList<String> params1,
			ArrayList<String> params2) {
		if(params1.size() == 0 && params2.size() == 0){
			return 1;
		}
		return AutoGenCTUtil.computeStringSimilarity(params1, params2);
	}
	
	@Override
	public ASTNode getCorrespondingASTNode() {
		return methodDeclaration;
	}

	@Override
	public String getASTHandle() {
		/*CompilationUnit unit = (CompilationUnit)methodDeclaration.getRoot();
		IMethod iMethod = (IMethod) methodDeclaration.resolveBinding().getJavaElement();
		ASTNode node = unit.findDeclaringNode(iMethod.getKey());*/
		
		return methodDeclaration.resolveBinding().getJavaElement().getHandleIdentifier();
	}

	@Override
	public String getKey() {
		IMethod iMethod = (IMethod) methodDeclaration.resolveBinding().getJavaElement();
		return iMethod.getKey();
	}
}
