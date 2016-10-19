package clonepedia.templategeneration.diagram.codegeneration;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EmptyStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.internal.core.dom.NaiveASTFlattener;

@SuppressWarnings("restriction")
public class ASTPrinter extends NaiveASTFlattener {

	public ASTPrinter() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("deprecation")
	private void attachComment(Statement node){
		String comment = node.getLeadingComment();
		if(comment != null){
			this.buffer.append(comment+"\n");
		}
	}
	
	public boolean visit(AssertStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(Block node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(BreakStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(ConstructorInvocation node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(ContinueStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(DoStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(EmptyStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(EnhancedForStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(ExpressionStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(ForStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(IfStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(LabeledStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(ReturnStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(SuperConstructorInvocation node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(SwitchCase node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(SwitchStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(SynchronizedStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(ThrowStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(TryStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(VariableDeclarationStatement node){
		attachComment(node);
		return super.visit(node);
	}
	
	public boolean visit(WhileStatement node){
		attachComment(node);
		return super.visit(node);
	}
}
