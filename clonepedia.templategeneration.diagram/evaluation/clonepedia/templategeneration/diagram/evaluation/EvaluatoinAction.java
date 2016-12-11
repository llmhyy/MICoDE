package clonepedia.templategeneration.diagram.evaluation;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class EvaluatoinAction implements IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		System.out.println("evaluate");

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow window) {

	}

}
