package clonepedia.templategeneration.diagram.codegeneration;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.rewrite.ImportRewrite;
import org.eclipse.jdt.core.dom.rewrite.ImportRewrite.ImportRewriteContext;
import org.eclipse.jdt.internal.corext.codemanipulation.ContextSensitiveImportRewriteContext;
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.text.edits.TextEdit;

/**
 * Class used in stub creation routines to add needed imports to a
 * compilation unit.
 */
public class ImportsManager {

	private final CompilationUnit fAstRoot;
	private final ImportRewrite fImportsRewrite;

	public ImportsManager(CompilationUnit astRoot) {
		fAstRoot = astRoot;
		fImportsRewrite = StubUtility.createImportRewrite(astRoot, true);
	}
	
	/*ImportsManager(CompilationUnit astRoot, ImportRewrite importRewrite) {
		fAstRoot = astRoot;
		fImportsRewrite = importRewrite;
	}*/

	public ICompilationUnit getCompilationUnit() {
		return fImportsRewrite.getCompilationUnit();
	}

	/**
	 * Adds a new import declaration that is sorted in the existing imports.
	 * If an import already exists or the import would conflict with an
	 * import of an other type with the same simple name, the import is not
	 * added.
	 * 
	 * @param qualifiedTypeName
	 *            The fully qualified name of the type to import (dot
	 *            separated).
	 * @return Returns the simple type name that can be used in the code or
	 *         the fully qualified type name if an import conflict prevented
	 *         the import.
	 */
	public String addImport(String qualifiedTypeName) {
		return fImportsRewrite.addImport(qualifiedTypeName);
	}

	/**
	 * Adds a new import declaration that is sorted in the existing imports.
	 * If an import already exists or the import would conflict with an
	 * import of an other type with the same simple name, the import is not
	 * added.
	 * 
	 * @param qualifiedTypeName
	 *            The fully qualified name of the type to import (dot
	 *            separated).
	 * @param insertPosition
	 *            the offset where the import will be used
	 * @return Returns the simple type name that can be used in the code or
	 *         the fully qualified type name if an import conflict prevented
	 *         the import.
	 * 
	 * @since 3.8
	 */
	public String addImport(String qualifiedTypeName, int insertPosition) {
		ImportRewriteContext context = new ContextSensitiveImportRewriteContext(fAstRoot, insertPosition, fImportsRewrite);
		return fImportsRewrite.addImport(qualifiedTypeName, context);
	}

	/**
	 * Adds a new import declaration that is sorted in the existing imports.
	 * If an import already exists or the import would conflict with an
	 * import of an other type with the same simple name, the import is not
	 * added.
	 * 
	 * @param typeBinding
	 *            the binding of the type to import
	 * 
	 * @return Returns the simple type name that can be used in the code or
	 *         the fully qualified type name if an import conflict prevented
	 *         the import.
	 */
	public String addImport(ITypeBinding typeBinding) {
		return fImportsRewrite.addImport(typeBinding);
	}

	/**
	 * Adds a new import declaration that is sorted in the existing imports.
	 * If an import already exists or the import would conflict with an
	 * import of an other type with the same simple name, the import is not
	 * added.
	 * 
	 * @param typeBinding
	 *            the binding of the type to import
	 * @param insertPosition
	 *            the offset where the import will be used
	 * 
	 * @return Returns the simple type name that can be used in the code or
	 *         the fully qualified type name if an import conflict prevented
	 *         the import.
	 * 
	 * @since 3.8
	 */
	public String addImport(ITypeBinding typeBinding, int insertPosition) {
		ImportRewriteContext context = new ContextSensitiveImportRewriteContext(
				fAstRoot, insertPosition, fImportsRewrite);
		return fImportsRewrite.addImport(typeBinding, context);
	}

	/**
	 * Adds a new import declaration for a static type that is sorted in the
	 * existing imports. If an import already exists or the import would
	 * conflict with an import of an other static import with the same
	 * simple name, the import is not added.
	 * 
	 * @param declaringTypeName
	 *            The qualified name of the static's member declaring type
	 * @param simpleName
	 *            the simple name of the member; either a field or a method
	 *            name.
	 * @param isField
	 *            <code>true</code> specifies that the member is a field,
	 *            <code>false</code> if it is a method.
	 * @return returns either the simple member name if the import was
	 *         successful or else the qualified name if an import conflict
	 *         prevented the import.
	 * 
	 * @since 3.2
	 */
	public String addStaticImport(String declaringTypeName,
			String simpleName, boolean isField) {
		return fImportsRewrite.addStaticImport(declaringTypeName,
				simpleName, isField);
	}

	public void create(boolean needsSave, IProgressMonitor monitor)
			throws CoreException {
		TextEdit edit = fImportsRewrite.rewriteImports(monitor);
		JavaModelUtil.applyEdit(fImportsRewrite.getCompilationUnit(), edit, needsSave, null);
	}

	public void removeImport(String qualifiedName) {
		fImportsRewrite.removeImport(qualifiedName);
	}

	public void removeStaticImport(String qualifiedName) {
		fImportsRewrite.removeStaticImport(qualifiedName);
	}
}