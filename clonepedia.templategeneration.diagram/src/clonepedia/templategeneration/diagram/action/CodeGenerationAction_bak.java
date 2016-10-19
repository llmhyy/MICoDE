package clonepedia.templategeneration.diagram.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.corext.codemanipulation.AddUnimplementedConstructorsOperation;
import org.eclipse.jdt.internal.corext.codemanipulation.AddUnimplementedMethodsOperation;
import org.eclipse.jdt.internal.corext.codemanipulation.CodeGenerationSettings;
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.jdt.internal.corext.dom.ASTNodes;
import org.eclipse.jdt.internal.corext.dom.TokenScanner;
import org.eclipse.jdt.internal.corext.refactoring.StubTypeContext;
import org.eclipse.jdt.internal.corext.refactoring.TypeContextChecker;
import org.eclipse.jdt.internal.corext.template.java.JavaContext;
import org.eclipse.jdt.internal.corext.util.CodeFormatterUtil;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.corext.util.Strings;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.javaeditor.ASTProvider;
import org.eclipse.jdt.internal.ui.preferences.JavaPreferencesSettings;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.JavaTypeCompletionProcessor;
import org.eclipse.jdt.ui.CodeGeneration;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.PlatformUI;

import template_model.Class;
import template_model.Element;
import template_model.Interface;
import template_model.Method;
import template_model.TemplateGraph;
import template_model.diagram.edit.parts.TemplateGraphEditPart;
import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.java.CloneInformationExtractor;
import clonepedia.java.model.CloneInstanceWrapper;
import clonepedia.java.model.CloneSetWrapper;
import clonepedia.java.util.MinerUtilforJava;
import clonepedia.java.visitor.CodeGenerationStatementVistor;
import clonepedia.java.visitor.CollectImportedTypeVisitor;
import clonepedia.java.visitor.CollectStatementInMethodVisitor;
import clonepedia.java.visitor.FindCloneDifferenceVisitor;
import clonepedia.model.ontology.CloneInstance;
import clonepedia.model.ontology.CloneSet;
import clonepedia.templategeneration.diagram.codegeneration.ASTPrinter;
import clonepedia.templategeneration.diagram.codegeneration.CodeSkeletonGenerationUtil;
import clonepedia.templategeneration.diagram.codegeneration.ImportsManager;
import clonepedia.templategeneration.diagram.util.TemplateDiagramUtil;

@SuppressWarnings("restriction")
public class CodeGenerationAction_bak extends AbstractActionDelegate implements IObjectActionDelegate{
	
	
	
	private ImportsManager imports = null;
	private Set<String> existingImports = null;

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		IStructuredSelection selection = getStructuredSelection();
		Object selEditPart = selection.getFirstElement();

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		if(new ValidateGraphAction().checkGraph(selEditPart) == null){
			if (selEditPart instanceof TemplateGraphEditPart) {
				TemplateGraphEditPart graph = (TemplateGraphEditPart)selEditPart;
				TemplateGraph graphModel = (TemplateGraph)graph.resolveSemanticElement();
				
				for(Element elementModel: graphModel.getElements()){
					
					if(elementModel instanceof Class){
						Class classModel = (Class)elementModel;
						try {
							if(classModel.getMethods().size() != 0){
								createType(progressMonitor, classModel);							
							}
						} catch (CoreException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}
		}
		else{
			MessageDialog.openError(shell, "Sorry", "The graph has not been completely configured.");
		}
	}

	// ---- creation ----------------
	/**
	 * Creates the new type using the entered field values.
	 * 
	 * @param monitor
	 *            a progress monitor to report progress.
	 * @throws CoreException
	 *             Thrown when the creation failed.
	 * @throws InterruptedException
	 *             Thrown when the operation was canceled.
	 */
	@SuppressWarnings("restriction")
	public ICompilationUnit createType(IProgressMonitor monitor, Class typeModel)
			throws CoreException, InterruptedException {

		String classFullName = typeModel.getFullName();
		String packageName = classFullName.substring(0, classFullName.lastIndexOf("."));

		this.imports = null;
		this.existingImports = null;

		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		monitor.beginTask("Create Code from Clone...", 8);

		IPackageFragmentRoot root = CodeSkeletonGenerationUtil.getPackageFragmentRoot();
		IPackageFragment pack = root.getPackageFragment(packageName);
		if (pack == null) {
			pack = root.getPackageFragment(""); //$NON-NLS-1$
		}

		if (!pack.exists()) {
			String packName = pack.getElementName();
			pack = root.createPackageFragment(packName, true, new SubProgressMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}

		boolean needsSave = true;
		ICompilationUnit connectedCU = null;

		try {
			String typeName = getTypeNameWithoutParameters(typeModel.getName());

			boolean isInnerClass = false;// isEnclosingTypeSelected();
			boolean isClassExist = true;

			IType createdType = null;
			int indent = 0;

			String lineDelimiter = null;
			if (!isInnerClass) {
				lineDelimiter = StubUtility.getLineDelimiterUsed(pack.getJavaProject());

				String cuName = getCompilationUnitName(typeName);

				ICompilationUnit parentCU = pack.getCompilationUnit(cuName);

				if (!parentCU.exists()) {
					parentCU = pack.createCompilationUnit(cuName, "", false, new SubProgressMonitor(monitor, 2)); //$NON-NLS-1$	
					isClassExist = false;
				}

				// create a working copy with a new owner

				needsSave = true;
				parentCU.becomeWorkingCopy(new SubProgressMonitor(monitor, 1)); // cu  is now a primary working copy
				connectedCU = parentCU;

				IBuffer buffer = parentCU.getBuffer();

				/*
				 * CompilationUnit astRoot = createASTForImports(parentCU);
				 * existingImports = getExistingImports(astRoot); imports = new
				 * ImportsManager(astRoot); // add an import that will be
				 * removed again. Having this import solves 14661
				 * imports.addImport
				 * (JavaModelUtil.concatenateName(pack.getElementName(),
				 * typeName));
				 */

				if (!isClassExist) {

					/**
					 * create initial class skeleton such as "public class A{}"
					 */
					String simpleTypeStub = constructSimpleTypeStub(typeModel.getName());

					/**
					 * create location packages and corresponding AST.
					 */
					String cuContent = constructCUContent(parentCU, simpleTypeStub, lineDelimiter, typeModel.getName());
					buffer.setContents(cuContent);

					CompilationUnit astRoot = initializeImports(parentCU, pack, typeName);

					String typeContent = constructTypeStub(parentCU, imports, lineDelimiter, typeModel, createdType, pack);

					int index = cuContent.lastIndexOf(simpleTypeStub);
					if (index == -1) {
						AbstractTypeDeclaration typeNode = (AbstractTypeDeclaration) astRoot.types().get(0);
						int start = ((ASTNode) typeNode.modifiers().get(0)).getStartPosition();
						int end = typeNode.getStartPosition() + typeNode.getLength();
						buffer.replace(start, end - start, typeContent);
					} else {
						buffer.replace(index, simpleTypeStub.length(), typeContent);
					}
				} else {
					initializeImports(parentCU, pack, typeName);
				}

				createdType = parentCU.getType(typeName);

			}
			/**
			 * for inner class
			 */
			/**/

			if (monitor.isCanceled()) {
				throw new InterruptedException();
			}

			// add imports for superclass/interfaces, so types can be resolved
			// correctly

			ICompilationUnit cu = createdType.getCompilationUnit();

			// cu.createPackageDeclaration(packageName, null);

			imports.create(false, new SubProgressMonitor(monitor, 1));

			JavaModelUtil.reconcile(cu);

			if (monitor.isCanceled()) {
				throw new InterruptedException();
			}

			// set up again
			CompilationUnit astRoot = createASTForImports(imports.getCompilationUnit());
			imports = new ImportsManager(astRoot);
			
			createTypeMembers(createdType, imports, new SubProgressMonitor(monitor, 1), typeModel);

			// add imports
			imports.create(false, new SubProgressMonitor(monitor, 1));

			removeUnusedImports(cu, existingImports, false);

			// JavaModelUtil.reconcile(cu);

			ISourceRange range = createdType.getSourceRange();

			IBuffer buf = cu.getBuffer();
			String originalContent = buf.getText(range.getOffset(), range.getLength());

			String formattedContent = CodeFormatterUtil.format(
					CodeFormatter.K_CLASS_BODY_DECLARATIONS, originalContent,
					indent, lineDelimiter, pack.getJavaProject());
			formattedContent = Strings .trimLeadingTabsAndSpaces(formattedContent);
			buf.replace(range.getOffset(), range.getLength(), formattedContent);
			if (!isInnerClass) {
				String fileComment = getFileComment(cu);
				if (fileComment != null && fileComment.length() > 0) {
					buf.replace(0, 0, fileComment + lineDelimiter);
				}
			}
			
			cu.getBuffer().setContents(buf.getContents());

			// fCreatedType = createdType;

			if (needsSave) {
				cu.commitWorkingCopy(true, new SubProgressMonitor(monitor, 1));
			} else {
				monitor.worked(1);
			}

		} finally {
			if (connectedCU != null) {
				connectedCU.discardWorkingCopy();
			}
			monitor.done();
		}

		return connectedCU;
	}

	@SuppressWarnings("restriction")
	private CompilationUnit initializeImports(ICompilationUnit parentCU,
			IPackageFragment pack, String typeName) {
		CompilationUnit astRoot = createASTForImports(parentCU);
		existingImports = getExistingImports(astRoot);
		imports = new ImportsManager(astRoot);
		// add an import that will be removed again. Having this import solves
		// 14661
		imports.addImport(JavaModelUtil.concatenateName(pack.getElementName(),
				typeName));

		return astRoot;
	}

	private CompilationUnit createASTForImports(ICompilationUnit cu) {
		ASTParser parser = ASTParser.newParser(ASTProvider.SHARED_AST_LEVEL);
		parser.setSource(cu);
		parser.setResolveBindings(true);
		parser.setFocalPosition(0);
		return (CompilationUnit) parser.createAST(null);
	}

	private Set<String> getExistingImports(CompilationUnit root) {
		List<ImportDeclaration> imports = root.imports();
		Set<String> res = new HashSet<String>(imports.size());
		for (int i = 0; i < imports.size(); i++) {
			res.add(ASTNodes.asString(imports.get(i)));
		}
		return res;
	}

	private void removeUnusedImports(ICompilationUnit cu,
			Set<String> existingImports, boolean needsSave)
			throws CoreException {
		ASTParser parser = ASTParser.newParser(ASTProvider.SHARED_AST_LEVEL);
		parser.setSource(cu);
		parser.setResolveBindings(true);

		CompilationUnit root = (CompilationUnit) parser.createAST(null);
		if (root.getProblems().length == 0) {
			return;
		}

		List<ImportDeclaration> importsDecls = root.imports();
		if (importsDecls.isEmpty()) {
			return;
		}
		ImportsManager imports = new ImportsManager(root);

		int importsEnd = ASTNodes.getExclusiveEnd(importsDecls.get(importsDecls
				.size() - 1));
		IProblem[] problems = root.getProblems();
		for (int i = 0; i < problems.length; i++) {
			IProblem curr = problems[i];
			if (curr.getSourceEnd() < importsEnd) {
				int id = curr.getID();
				if (id == IProblem.UnusedImport
						|| id == IProblem.NotVisibleType) { // not visible
															// problems hide
															// unused -> remove
															// both
					int pos = curr.getSourceStart();
					for (int k = 0; k < importsDecls.size(); k++) {
						ImportDeclaration decl = importsDecls.get(k);
						if (decl.getStartPosition() <= pos
								&& pos < decl.getStartPosition()
										+ decl.getLength()) {
							if (existingImports.isEmpty()
									|| !existingImports.contains(ASTNodes
											.asString(decl))) {
								String name = decl.getName()
										.getFullyQualifiedName();
								if (decl.isOnDemand()) {
									name += ".*"; //$NON-NLS-1$
								}
								if (decl.isStatic()) {
									imports.removeStaticImport(name);
								} else {
									imports.removeImport(name);
								}
							}
							break;
						}
					}
				}
			}
		}
		imports.create(needsSave, null);
	}

	/**
	 * Uses the New Java file template from the code template page to generate a
	 * compilation unit with the given type content.
	 * 
	 * @param cu
	 *            The new created compilation unit
	 * @param typeContent
	 *            The content of the type, including signature and type body.
	 * @param lineDelimiter
	 *            The line delimiter to be used.
	 * @return String Returns the result of evaluating the new file template
	 *         with the given type content.
	 * @throws CoreException
	 *             when fetching the file comment fails or fetching the content
	 *             for the new compilation unit fails
	 * @since 2.1
	 */
	protected String constructCUContent(ICompilationUnit cu,
			String typeContent, String lineDelimiter, String classSimpleName)
			throws CoreException {
		String fileComment = getFileComment(cu, lineDelimiter);
		String typeComment = getTypeComment(cu, lineDelimiter, classSimpleName);
		IPackageFragment pack = (IPackageFragment) cu.getParent();
		String content = CodeGeneration.getCompilationUnitContent(cu,
				fileComment, typeComment, typeContent, lineDelimiter);
		if (content != null) {
			ASTParser parser = ASTParser
					.newParser(ASTProvider.SHARED_AST_LEVEL);
			parser.setProject(cu.getJavaProject());
			parser.setSource(content.toCharArray());
			CompilationUnit unit = (CompilationUnit) parser.createAST(null);
			if ((pack.isDefaultPackage() || unit.getPackage() != null)
					&& !unit.types().isEmpty()) {
				return content;
			}
		}
		StringBuffer buf = new StringBuffer();
		if (!pack.isDefaultPackage()) {
			buf.append("package ").append(pack.getElementName()).append(';'); //$NON-NLS-1$
		}
		buf.append(lineDelimiter).append(lineDelimiter);
		if (typeComment != null) {
			buf.append(typeComment).append(lineDelimiter);
		}
		buf.append(typeContent);
		return buf.toString();
	}

	// ---- construct CU body----------------

	private void writeSuperClass(StringBuffer buf, ImportsManager imports,
			String classSimpleName, String superClass, IType fCurrType,
			IPackageFragment pack) {
		// String superclass = getSuperClass();
		buf.append(" extends "); //$NON-NLS-1$

		ITypeBinding binding = null;
		if (fCurrType != null) {
			binding = TypeContextChecker.resolveSuperClass(superClass,
					fCurrType,
					getSuperClassStubTypeContext(classSimpleName, fCurrType, pack));
		}
		if (binding != null) {
			buf.append(imports.addImport(binding));
		} else {
			buf.append(imports.addImport(superClass));
		}
	}

	private void writeSuperInterfaces(StringBuffer buf, ImportsManager imports,
			String classSimpleName, List<String> interfaces, IType fCurrType, IPackageFragment pack) {
		// List<String> interfaces = getSuperInterfaces();
		int last = interfaces.size() - 1;
		if (last >= 0) {
			buf.append(" implements ");
			String[] intfs = interfaces.toArray(new String[interfaces.size()]);
			ITypeBinding[] bindings;
			if (fCurrType != null) {
				bindings = TypeContextChecker.resolveSuperInterfaces(
						intfs,
						fCurrType,
						getSuperInterfacesStubTypeContext(classSimpleName, fCurrType, pack));
			} else {
				bindings = new ITypeBinding[intfs.length];
			}
			for (int i = 0; i <= last; i++) {
				ITypeBinding binding = bindings[i];
				if (binding != null) {
					buf.append(imports.addImport(binding));
				} else {
					buf.append(imports.addImport(intfs[i]));
				}
				if (i < last) {
					buf.append(',');
				}
			}
		}
	}

	private String constructSimpleTypeStub(String classSimpleName) {
		StringBuffer buf = new StringBuffer("public class "); //$NON-NLS-1$
		buf.append(classSimpleName);
		buf.append("{ }"); //$NON-NLS-1$
		return buf.toString();
	}

	/*
	 * Called from createType to construct the source for this type
	 */
	private String constructTypeStub(ICompilationUnit parentCU,
			ImportsManager imports, String lineDelimiter, Class classModel,
			IType fCurType, IPackageFragment pack) throws CoreException {
		StringBuffer buf = new StringBuffer();

		int modifiers = 1;
		buf.append(Flags.toString(modifiers));
		if (modifiers != 0) {
			buf.append(' ');
		}
		String type = ""; //$NON-NLS-1$
		String templateID = ""; //$NON-NLS-1$
		type = "class "; //$NON-NLS-1$
		templateID = CodeGeneration.CLASS_BODY_TEMPLATE_ID;
		buf.append(type);
		buf.append(classModel.getName());

		if (classModel.getSuperClass() != null) {
			writeSuperClass(buf, imports, classModel.getName(), classModel.getSuperClass().getFullName(), fCurType, pack);
		}

		List<String> interfaceList = new ArrayList<String>();
		for (Interface interfaceModel : classModel.getInterfaces()) {
			interfaceList.add(interfaceModel.getFullName());
		}

		writeSuperInterfaces(buf, imports, classModel.getName(), interfaceList, fCurType, pack);

		System.out.println();

		buf.append(" {").append(lineDelimiter); //$NON-NLS-1$
		String typeBody = CodeGeneration.getTypeBody(templateID, parentCU,
				classModel.getName(), lineDelimiter);
		if (typeBody != null) {
			buf.append(typeBody);
		} else {
			buf.append(lineDelimiter);
		}
		buf.append('}').append(lineDelimiter);
		return buf.toString();
	}

	/**
	 * Hook method that gets called from <code>createType</code> to support
	 * adding of unanticipated methods, fields, and inner types to the created
	 * type.
	 * <p>
	 * Implementers can use any methods defined on <code>IType</code> to
	 * manipulate the new type.
	 * </p>
	 * <p>
	 * The source code of the new type will be formatted using the platform's
	 * formatter. Needed imports are added by the wizard at the end of the type
	 * creation process using the given import manager.
	 * </p>
	 * 
	 * @param newType
	 *            the new type created via <code>createType</code>
	 * @param imports
	 *            an import manager which can be used to add new imports
	 * @param monitor
	 *            a progress monitor to report progress. Must not be
	 *            <code>null</code>
	 * @throws CoreException
	 *             thrown when creation of the type members failed
	 * 
	 * @see #createType(IProgressMonitor)
	 */
	protected void createTypeMembers(IType type, final ImportsManager imports, IProgressMonitor monitor, 
			Class classModel/*, HashMap<Method, PathPatternGroupWrapper> methodAndCloneMap*/) throws CoreException {

		//createInheritedMethods(type, false, true, imports, new
		// SubProgressMonitor(monitor, 1));

		for (Method methodModel : classModel.getMethods()) {

			String returnType = methodModel.getReturnType();
			String methodName = methodModel.getName();

			String[] params = methodModel.getParameters().toArray(new String[0]);
			for (int i = 0; i < params.length; i++) {
				String[] p = params[i].split(":");
				
				String typeName = p[0];
				String name = p[1];
				params[i] = typeName + " " + name + ", ";
			}

			StringBuffer buf = new StringBuffer();
			final String lineDelim = "\n"; // OK, since content is formatted afterwards //$NON-NLS-1$
			if(!returnType.equals("")){
				String comment = CodeGeneration.getMethodComment(type.getCompilationUnit(), type.getTypeQualifiedName('.'), methodName, params, new String[0],
						Signature.createTypeSignature(returnType, true), 
						null, lineDelim); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				if (comment != null) {
					buf.append(comment);
					buf.append(lineDelim);
				}
			}
			buf.append("public " + returnType + " " + methodName + "("); //$NON-NLS-1$
			for (int i = 0; i < params.length; i++) {
				if(null != params[i]){
					buf.append(params[i]); //$NON-NLS-1$
					
					if (params[i].contains(".")) {
						imports.addImport(params[i].substring(0, params[i].indexOf(" ")));
					}					
				}

				/*buf.append(" ");

				buf.append("param" + i + ", ");*/
			}

			if (params.length != 0) {

				String tempString = buf.toString();
				tempString = tempString.substring(0, tempString.length() - 2);
				buf = new StringBuffer(tempString);

			}
			buf.append("){\n //TODO ");
			buf.append(lineDelim);
			
			
			/*final String content = CodeGeneration.getMethodBodyContent(
					type.getCompilationUnit(), type.getTypeQualifiedName('.'),
					"main", false, "", lineDelim); //$NON-NLS-1$ //$NON-NLS-2$
			if (content != null && content.length() != 0)
				buf.append(content);*/
			
			String content = generateFillInCloneContent(methodModel, imports);
			buf.append(content);
			
			buf.append(lineDelim);
			buf.append("}"); //$NON-NLS-1$
			try{
				type.createMethod(buf.toString(), null, false, null);				
			}
			catch(JavaModelException exception){
				
			}
		}
		
		if (monitor != null) {
			monitor.done();
		}
	}
	
	private CloneSet getSuitableCloneSet(clonepedia.model.ontology.Method abstractMethod){
		clonepedia.model.ontology.Method supportingMethod = (clonepedia.model.ontology.Method) abstractMethod.getSupportingElements().get(0);
		
		int range = 0;
		CloneSet set = null;
		
		for(CloneInstance instance: supportingMethod.getCloneInstances()){
			int instanceRange = instance.getEndLine()-instance.getStartLine()+1;
			if(instanceRange > range){
				range = instanceRange;
				set = instance.getCloneSet();
			}
		}
		
		return set;
	}
	
	private String generateFillInCloneContent(Method methodModel, ImportsManager imports){
		
		clonepedia.model.template.Template template = TemplateDiagramUtil.getActiveTemplate();
		
		/*CloneSet set = TemplateDiagramUtil.constructCloneSetFromMethodList(methodModel.getSupportingElements());
		if(set == null) return "";
		CloneSetWrapper setWrapper = new CloneSetWrapper(set, new CompilationUnitPool());
		*/
		
		Multiset ms = AutoGenCTSettings.designs.findMultiset(methodModel.getDescription());
		CloneSetWrapper setWrapper = TemplateDiagramUtil.constructCloneSetFromASTNodeList(ms);
		
		try {
			setWrapper = new CloneInformationExtractor().
					extractCounterRelationalDifferencesWithinSyntacticBoundary(setWrapper);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		CloneInstanceWrapper insWrapper = setWrapper.findLongestCloneInstance();
		
		if(insWrapper == null){
			return "";	
		}
			
		
		MethodDeclaration md = (MethodDeclaration) insWrapper.getMinimumContainingASTNode();
		
		CompilationUnit unit = MinerUtilforJava.getCompilationUnit(md);
		
		CollectImportedTypeVisitor importTypeVisitor = new CollectImportedTypeVisitor();
		CollectStatementInMethodVisitor totalStatVisitor = new CollectStatementInMethodVisitor();
		CodeGenerationStatementVistor cloneStatVistor = 
				new CodeGenerationStatementVistor(insWrapper.getStartLine(), insWrapper.getEndLine(), unit);
		
		md.accept(importTypeVisitor);
		md.accept(totalStatVisitor);
		md.accept(cloneStatVistor);
		
		ArrayList<String> importedTypes = importTypeVisitor.getImportList();
		addImports(importedTypes, imports);
		
		ArrayList<Statement> totalStatements = totalStatVisitor.getTotalStatementList();
		ArrayList<Statement> cloneStatements = cloneStatVistor.getCloneStatementList();
		
		return generateMethodContent(totalStatements, cloneStatements, insWrapper, setWrapper, unit);
	}
	
	
	
	@SuppressWarnings("deprecation")
	private String generateMethodContent(ArrayList<Statement> totalStatements, ArrayList<Statement> cloneStatements,
			CloneInstanceWrapper insWrapper, CloneSetWrapper setWrapper, CompilationUnit unit){
		
		StringBuffer buf = new StringBuffer();
		for(Statement stat: totalStatements){
			
			if(!CodeSkeletonGenerationUtil.isCloneRelatedStatement(stat, insWrapper, unit)){
				stat.setLeadingComment("//not clone");
			}
			else{
				FindCloneDifferenceVisitor diffVisitor = new FindCloneDifferenceVisitor(setWrapper, insWrapper);
				stat.accept(diffVisitor);
				
				//ArrayList<ASTNode> counterDiffList = diffVisitor.getCounterDiffList();
				//ArrayList<ASTNode> uncounterDiffList = diffVisitor.getUncounterDiffList();
				
				HashMap<Statement, ArrayList<ASTNode>> counterDiffMap = diffVisitor.getCounterDiffMap();
				HashMap<Statement, ArrayList<ASTNode>> uncounterDiffMap = diffVisitor.getUncounterDiffMap();
				
				if(counterDiffMap.size() != 0){
					for(Statement st: counterDiffMap.keySet()){
						generateComment(st, counterDiffMap);
					}
				}
				
				if(uncounterDiffMap.size() != 0){
					for(Statement st: uncounterDiffMap.keySet()){
						generateComment(st, uncounterDiffMap);
					}
				}
			}
			
			ASTPrinter printer = new ASTPrinter();
			stat.accept(printer);
			
			buf.append(printer.getResult());
		}
		
		return buf.toString();
	}

	private void addImports(ArrayList<String> importedTypes, ImportsManager imports){
		for(String type: importedTypes){
			imports.addImport(type);
		}
	}
	
	private void generateComment(Statement stat, HashMap<Statement, ArrayList<ASTNode>> map){
		String message = "/*You may need to change ";
		ArrayList<ASTNode> nodeList = map.get(stat);
		for(ASTNode node: nodeList){
			message += node.toString() + ", ";
		}
		message = message.substring(0, message.length()-2) + "*/";
		
		stat.setLeadingComment(message);
		
	}

	/**
	 * @param parentCU
	 *            the current compilation unit
	 * @return returns the file template or <code>null</code>
	 * @deprecated Instead of file templates, the new type code template
	 *             specifies the stub for a compilation unit.
	 */
	protected String getFileComment(ICompilationUnit parentCU) {
		return null;
	}

	/**
	 * Hook method that gets called from <code>createType</code> to retrieve a
	 * file comment. This default implementation returns the content of the
	 * 'file comment' template or <code>null</code> if no comment should be
	 * created.
	 * 
	 * @param parentCU
	 *            the parent compilation unit
	 * @param lineDelimiter
	 *            the line delimiter to use
	 * @return the file comment or <code>null</code> if a file comment is not
	 *         desired
	 * @throws CoreException
	 *             when fetching the file comment fails
	 * 
	 * @since 3.1
	 */
	protected String getFileComment(ICompilationUnit parentCU,
			String lineDelimiter) throws CoreException {

		return CodeGeneration.getFileComment(parentCU, lineDelimiter);

	}

	private boolean isValidComment(String template) {
		IScanner scanner = ToolFactory.createScanner(true, false, false, false);
		scanner.setSource(template.toCharArray());
		try {
			int next = scanner.getNextToken();
			while (TokenScanner.isComment(next)) {
				next = scanner.getNextToken();
			}
			return next == ITerminalSymbols.TokenNameEOF;
		} catch (InvalidInputException e) {
		}
		return false;
	}

	/**
	 * Hook method that gets called from <code>createType</code> to retrieve a
	 * type comment. This default implementation returns the content of the
	 * 'type comment' template.
	 * 
	 * @param parentCU
	 *            the parent compilation unit
	 * @param lineDelimiter
	 *            the line delimiter to use
	 * @return the type comment or <code>null</code> if a type comment is not
	 *         desired
	 * 
	 * @since 3.0
	 */
	protected String getTypeComment(ICompilationUnit parentCU,
			String lineDelimiter, String classSimpleName) {
		try {
			StringBuffer typeName = new StringBuffer();
			/*
			 * if (isEnclosingTypeSelected()) { typeName.append(
			 * getEnclosingType().getTypeQualifiedName('.')) .append('.'); }
			 */
			typeName.append(getTypeNameWithoutParameters(classSimpleName));
			String[] typeParamNames = new String[0];
			String comment = CodeGeneration.getTypeComment(parentCU,
					typeName.toString(), typeParamNames, lineDelimiter);
			if (comment != null && isValidComment(comment)) {
				return comment;
			}
		} catch (CoreException e) {
			JavaPlugin.log(e);
		}
		return null;
	}

	/**
	 * @param parentCU
	 *            the current compilation unit
	 * @return returns the template or <code>null</code>
	 * @deprecated Use getTypeComment(ICompilationUnit, String)
	 */
	protected String getTypeComment(ICompilationUnit parentCU, Class classModel) {
		if (StubUtility.doAddComments(parentCU.getJavaProject()))
			return getTypeComment(parentCU,
					StubUtility.getLineDelimiterUsed(parentCU), classModel.getName());
		return null;
	}

	/**
	 * @param name
	 *            the name of the template
	 * @param parentCU
	 *            the current compilation unit
	 * @return returns the template or <code>null</code>
	 * @deprecated Use getTemplate(String,ICompilationUnit,int)
	 */
	protected String getTemplate(String name, ICompilationUnit parentCU) {
		return getTemplate(name, parentCU, 0);
	}

	/**
	 * Returns the string resulting from evaluation the given template in the
	 * context of the given compilation unit. This accesses the normal template
	 * page, not the code templates. To use code templates use
	 * <code>constructCUContent</code> to construct a compilation unit stub or
	 * getTypeComment for the comment of the type.
	 * 
	 * @param name
	 *            the template to be evaluated
	 * @param parentCU
	 *            the templates evaluation context
	 * @param pos
	 *            a source offset into the parent compilation unit. The template
	 *            is evaluated at the given source offset
	 * @return return the template with the given name or <code>null</code> if
	 *         the template could not be found.
	 */
	protected String getTemplate(String name, ICompilationUnit parentCU, int pos) {
		try {
			Template template = JavaPlugin.getDefault().getTemplateStore()
					.findTemplate(name);
			if (template != null) {
				return JavaContext.evaluateTemplate(template, parentCU, pos);
			}
		} catch (CoreException e) {
			JavaPlugin.log(e);
		} catch (BadLocationException e) {
			JavaPlugin.log(e);
		} catch (TemplateException e) {
			JavaPlugin.log(e);
		}
		return null;
	}

	/**
	 * Creates the bodies of all unimplemented methods and constructors and adds
	 * them to the type. Method is typically called by implementers of
	 * <code>NewTypeWizardPage</code> to add needed method and constructors.
	 * 
	 * @param type
	 *            the type for which the new methods and constructor are to be
	 *            created
	 * @param doConstructors
	 *            if <code>true</code> unimplemented constructors are created
	 * @param doUnimplementedMethods
	 *            if <code>true</code> unimplemented methods are created
	 * @param imports
	 *            an import manager to add all needed import statements
	 * @param monitor
	 *            a progress monitor to report progress
	 * @return the created methods.
	 * @throws CoreException
	 *             thrown when the creation fails.
	 */
	protected IMethod[] createInheritedMethods(IType type,
			boolean doConstructors, boolean doUnimplementedMethods,
			ImportsManager imports, IProgressMonitor monitor)
			throws CoreException {
		final ICompilationUnit cu = type.getCompilationUnit();
		JavaModelUtil.reconcile(cu);
		if (type.exists()) {
			IMethod[] typeMethods = type.getMethods();
			Set<String> handleIds = new HashSet<String>(typeMethods.length);
			for (int index = 0; index < typeMethods.length; index++)
				handleIds.add(typeMethods[index].getHandleIdentifier());
			ArrayList<IMethod> newMethods = new ArrayList<IMethod>();
			CodeGenerationSettings settings = JavaPreferencesSettings
					.getCodeGenerationSettings(type.getJavaProject());
			settings.createComments = true/* isAddComments() */;
			ASTParser parser = ASTParser
					.newParser(ASTProvider.SHARED_AST_LEVEL);
			parser.setResolveBindings(true);
			parser.setSource(cu);
			CompilationUnit unit = (CompilationUnit) parser
					.createAST(new SubProgressMonitor(monitor, 1));
			final ITypeBinding binding = ASTNodes.getTypeBinding(unit, type);
			if (binding != null) {
				if (doUnimplementedMethods) {
					AddUnimplementedMethodsOperation operation = new AddUnimplementedMethodsOperation(
							unit, binding, null, -1, false, true, false);
					operation.setCreateComments(true/* isAddComments() */);
					operation.run(monitor);
					createImports(imports, operation.getCreatedImports());
				}
				if (doConstructors) {
					AddUnimplementedConstructorsOperation operation = new AddUnimplementedConstructorsOperation(
							unit, binding, null, -1, false, true, false);
					operation.setOmitSuper(true);
					operation.setCreateComments(true/* isAddComments() */);
					operation.run(monitor);
					createImports(imports, operation.getCreatedImports());
				}
			}
			JavaModelUtil.reconcile(cu);
			typeMethods = type.getMethods();
			for (int index = 0; index < typeMethods.length; index++)
				if (!handleIds.contains(typeMethods[index]
						.getHandleIdentifier()))
					newMethods.add(typeMethods[index]);
			IMethod[] methods = new IMethod[newMethods.size()];
			newMethods.toArray(methods);
			return methods;

		}

		return null;
	}

	private void createImports(ImportsManager imports, String[] createdImports) {
		for (int index = 0; index < createdImports.length; index++)
			imports.addImport(createdImports[index]);
	}

	// ---- creation ----------------

	/**
	 * Returns the runnable that creates the type using the current settings.
	 * The returned runnable must be executed in the UI thread.
	 * 
	 * @return the runnable to create the new type
	 */
	public IRunnableWithProgress getRunnable(final Class clazz) {
		return new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				try {
					if (monitor == null) {
						monitor = new NullProgressMonitor();
					}
					createType(monitor, clazz);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				}
			}
		};
	}

	// The following is code clones
	private String getTypeNameWithoutParameters(Class classModel) {
		return getTypeNameWithoutParameters(classModel.getName());
	}

	private static String getTypeNameWithoutParameters(
			String typeNameWithParameters) {
		int angleBracketOffset = typeNameWithParameters.indexOf('<');
		if (angleBracketOffset == -1) {
			return typeNameWithParameters;
		} else {
			return typeNameWithParameters.substring(0, angleBracketOffset);
		}
	}

	protected String getCompilationUnitName(String typeName) {
		return typeName + JavaModelUtil.DEFAULT_CU_SUFFIX;
	}

	private StubTypeContext getSuperClassStubTypeContext(String classSimpleName,
			IType fCurrType, IPackageFragment pack) {
		String typeName;
		if (fCurrType != null) {
			typeName = classSimpleName;
		} else {
			typeName = JavaTypeCompletionProcessor.DUMMY_CLASS_NAME;
		}
		StubTypeContext fSuperClassStubTypeContext = TypeContextChecker
				.createSuperClassStubTypeContext(typeName, null/*
																 * getEnclosingType(
																 * )
																 */, pack);
		return fSuperClassStubTypeContext;
	}

	private StubTypeContext getSuperInterfacesStubTypeContext(String classSimpleName,
			IType fCurrType, IPackageFragment pack) {
		String typeName;
		if (fCurrType != null) {
			typeName = classSimpleName;
		} else {
			typeName = JavaTypeCompletionProcessor.DUMMY_CLASS_NAME;
		}
		StubTypeContext fSuperInterfaceStubTypeContext = TypeContextChecker
				.createSuperInterfaceStubTypeContext(typeName, null/*
																	 * getEnclosingType
																	 * ()
																	 */, pack);
		return fSuperInterfaceStubTypeContext;
	}
}
