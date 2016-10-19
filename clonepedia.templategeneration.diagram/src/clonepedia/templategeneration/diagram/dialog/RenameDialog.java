package clonepedia.templategeneration.diagram.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class RenameDialog extends TitleAreaDialog {
	
	private Text templateNameText;
	private Text templateDescriptionText;
	
	private String templateName;
	private String templateDescription;

	public RenameDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	public void create() {
		setHelpAvailable(false);
		super.create();
		// Set the title
		setTitle("Rename Template");
		// Set the message
		setMessage("Please input template name and description.", 
				IMessageProvider.INFORMATION);
		
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		//parent.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		Composite workArea = new Composite(parent, SWT.NONE);
		workArea.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		GridLayout workAreaLayout = new GridLayout();
		workAreaLayout.numColumns = /*1*/1;
		workAreaLayout.horizontalSpacing = 10;
		workAreaLayout.marginHeight = 10;
		workAreaLayout.marginBottom = 10;
		workAreaLayout.marginLeft = 10;
		workAreaLayout.marginRight = 10;
		workAreaLayout.verticalSpacing = 10;
		workArea.setLayout(workAreaLayout);
		
		Group group = new Group(workArea, SWT.NONE);
		group.setText("Control Corresponding Clone Set Number");
		group.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		GridLayout groupLayout = new GridLayout();
		groupLayout.numColumns = 2;
		groupLayout.verticalSpacing = 10;
		group.setLayout(groupLayout);

		// The text fields will grow with the size of the dialog

		Label minimunLabel = new Label(group, SWT.NONE);
		minimunLabel.setText("name");
		minimunLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));

		templateNameText = new Text(group, SWT.BORDER);
		templateNameText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		
		Label maximumLabel = new Label(group, SWT.NONE);
		maximumLabel.setText("description");
		maximumLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		// You should not re-use GridData
		templateDescriptionText = new Text(group, SWT.BORDER);
		templateDescriptionText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		
		return workArea;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		GridData parentGridData = new GridData();
		parentGridData.grabExcessHorizontalSpace = true;
		parentGridData.grabExcessVerticalSpace = true;
		parentGridData.horizontalAlignment = GridData.FILL;
		parentGridData.verticalAlignment = GridData.FILL;
		parent.setData(parentGridData);

		parent.setLayoutData(parentGridData);
		// Create Add button
		// Own method as we need to overview the SelectionAdapter
		createOkButton(parent, OK, "OK", true);
		// Add a SelectionListener

		// Create Cancel button
		Button cancelButton = 
				createButton(parent, CANCEL, "Cancel", false);
		// Add a SelectionListener
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setReturnCode(CANCEL);
				close();
			}
		});
	}

	protected Button createOkButton(Composite parent, int id, String label, boolean defaultButton) {
		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (isValidInput()) {
					okPressed();
				}
			}
		});
		if (defaultButton) {
			Shell shell = parent.getShell();
			if (shell != null) {
				shell.setDefaultButton(button);
			}
		}
		setButtonLayoutData(button);
		return button;
	}

	private boolean isValidInput() {
		return true;
	}

	
	@Override
	protected boolean isResizable() {
		return true;
	}

	// Coyy textFields because the UI gets disposed
	// and the Text Fields are not accessible any more.
	private void saveInput() {
		templateName = templateNameText.getText();
		templateDescription = templateDescriptionText.getText();
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getTemplateDescription() {
		return templateDescription;
	}
	
	
}
