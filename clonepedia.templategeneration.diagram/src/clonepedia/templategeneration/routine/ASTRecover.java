package clonepedia.templategeneration.routine;

import java.util.HashMap;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTRecover {
	
	private static HashMap<String, CompilationUnit> map = new HashMap<String, CompilationUnit>();

	public static void cleanCache(){
		map.clear();
	}
	
	public static ASTNode recover(String handle, String key) throws Exception{
		IJavaElement ije = JavaCore.create(handle);
		
		//String key = setLevel(ije);
		
		ICompilationUnit unit = getCompilationUnit(ije);
		
		ASTNode node = getASTNode(unit, key);
		
		return node;
	}
	
	private static ASTNode getASTNode(ICompilationUnit unit, String key) {
		CompilationUnit compilationUnit = null;
		
		if((compilationUnit=map.get(unit.getHandleIdentifier()))==null){
			ASTParser parser = ASTParser.newParser(AST.JLS4);
			parser.setSource(unit);
			parser.setKind(ASTParser.K_COMPILATION_UNIT); // to parse
			parser.setResolveBindings(true);
			compilationUnit = (CompilationUnit) parser.createAST(null);
			map.put(unit.getHandleIdentifier(), compilationUnit);
		}
		
		ASTNode ret = compilationUnit.findDeclaringNode(key);
		
		//System.out.println("c:"+(compilationUnit==null)+"\n\t"+(ret==null));
		return ret;
	}

	private static ICompilationUnit getCompilationUnit(IJavaElement ije) {
		IJavaElement ij = ije;
		while((ij!=null) && (!(ij instanceof ICompilationUnit))){
			ij = ij.getParent();
		}
		ICompilationUnit unit = (ICompilationUnit)ij;
		return unit;
	}
}
