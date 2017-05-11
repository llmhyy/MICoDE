package clonepedia.templategeneration.diagram.evaluation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class EvaluatoinAction implements IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		
		Job job = new Job("evaluating...") {
			
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				TemplateEvaluator evaluator = new TemplateEvaluator();
				evaluator.evaluate();
				
				return Status.OK_STATUS;
			}
		};
		
		job.schedule();
		
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
