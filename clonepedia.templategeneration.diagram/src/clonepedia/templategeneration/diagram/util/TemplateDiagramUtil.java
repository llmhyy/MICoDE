package clonepedia.templategeneration.diagram.util;

//import org.eclipse.gef.EditPart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.ui.PlatformUI;

import clonepedia.businessdata.OntologicalModelDataFetcher;
import clonepedia.codegeneration.diagram.bean.IElement;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.ProgramElementWrapper;
import clonepedia.java.CompilationUnitPool;
import clonepedia.java.model.CloneSetWrapper;
import clonepedia.java.util.MinerUtilforJava;
import clonepedia.java.visitor.FindMethodVisitor;
import clonepedia.model.ontology.CloneInstance;
import clonepedia.model.ontology.CloneSet;
import clonepedia.model.ontology.Project;
import clonepedia.model.template.CandidateTemplateList;
import clonepedia.model.template.Template;
import clonepedia.templategeneration.diagram.view.FeatureView;
import clonepedia.templategeneration.diagram.view.model.TemplateList;
import clonepedia.views.util.ViewUtil;

public class TemplateDiagramUtil {
	public static Template getActiveTemplate() {
		String fileName = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActiveEditor().getTitle();
		String templateID = fileName.substring(0, fileName.indexOf("."));

		FeatureView view = (FeatureView) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.findView("clonepedia.templategeneration.diagram.feaure");
		if (view != null) {
			CandidateTemplateList totalTFGs = ((TemplateList) view.getViewer()
					.getInput()).getTotalTFGs();
			Template template = totalTFGs.findTemplate(templateID);
			return template;
		}

		return null;
	}

	public static CloneSet constructCloneSetFromMethodList(List<String> methodList) {

		CloneSet set = new CloneSet("0");

		for (String methodFullName : methodList) {
			String classFullName = methodFullName.substring(0,
					methodFullName.indexOf("$"));
			String returnType = methodFullName.substring(
					methodFullName.indexOf("$") + 1,
					methodFullName.indexOf(" "));
			String methodName = methodFullName.substring(
					methodFullName.indexOf(" ") + 1,
					methodFullName.indexOf("("));
			String paramString = methodFullName.substring(
					methodFullName.indexOf("(") + 1,
					methodFullName.length() - 1);
			String params[];
			if (paramString.length() == 0) {
				params = new String[0];
			} else {
				params = paramString.split(" ");
			}

			try {
				ICompilationUnit iunit = ViewUtil
						.getCorrespondingCompliationUnit(classFullName);
				String javaFile = iunit.getElementName();
				String path = "\\src\\";
				String packString = iunit.getPackageDeclarations()[0]
						.getElementName();
				packString = packString.replace('.', '\\');
				path += packString + "\\" + javaFile;

				if (iunit != null) {
					ASTParser parser = ASTParser.newParser(AST.JLS3);
					parser.setKind(ASTParser.K_COMPILATION_UNIT);
					parser.setSource(iunit);
					parser.setResolveBindings(true);
					CompilationUnit cu = (CompilationUnit) parser
							.createAST(null);

					FindMethodVisitor visitor = new FindMethodVisitor(
							returnType, methodName, params);
					cu.accept(visitor);
					MethodDeclaration md = visitor.getMethodDeclaration();

					int startLine = cu.getLineNumber(md.getStartPosition());
					int endLine = cu.getLineNumber(md.getStartPosition()
							+ md.getLength());

					CloneInstance ci = new CloneInstance(set, path, startLine,
							endLine);

					try {
						clonepedia.model.ontology.Method m = MinerUtilforJava
								.getMethodfromASTNode(md, new Project(null,
										null, null),
										new OntologicalModelDataFetcher());
						ci.setResidingMethod(m);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					set.add(ci);
				}

				// System.out.println();
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return set;
	}

	public static CloneSetWrapper constructCloneSetFromASTNodeList(Multiset ms) {
		CloneSet set = new CloneSet("0");
		
		ArrayList<ASTNode> nodeList = new ArrayList<>();
		
		HashMap<CloneInstance, ASTNode> map = new HashMap<>();
		for(IElement element: ms.getCorrespondingSet()){
			ProgramElementWrapper elementWrapper = (ProgramElementWrapper)element;
			CloneInstance instance = new CloneInstance(set, elementWrapper.getLocation(), 
					elementWrapper.getStartLine(), elementWrapper.getEndLine());	
			
			map.put(instance, elementWrapper.getCorrespondingASTNode());
			
			set.add(instance);
		}
		
		for(CloneInstance instance: set){
			ASTNode node = map.get(instance);
			nodeList.add(node);
		}
		
		CloneSetWrapper setWrapper = new CloneSetWrapper(set, new CompilationUnitPool(), false, nodeList);
		
		return setWrapper;
	}
}
