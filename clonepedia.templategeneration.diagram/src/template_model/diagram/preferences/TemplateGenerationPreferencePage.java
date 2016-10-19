package template_model.diagram.preferences;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.osgi.service.prefs.Preferences;

import clonepedia.Activator;

public class TemplateGenerationPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	class FileSelectionAdapter extends SelectionAdapter {

		private Text text;

		public FileSelectionAdapter(Text text) {
			this.text = text;
		}

		public void widgetSelected(SelectionEvent e) {
			FileDialog dialog = new FileDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), SWT.NULL);

			String path = dialog.open();
			if (path != null) {

				File file = new File(path);
				if (file.isFile()) {
					text.setText(file.toString());
				}
			}
		}
	}

	class FolderSelectionAdapter extends SelectionAdapter {

		private Text text;

		public FolderSelectionAdapter(Text text) {
			this.text = text;
		}

		public void widgetSelected(SelectionEvent e) {
			DirectoryDialog dialog = new DirectoryDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					SWT.NULL);

			String filterPath = ResourcesPlugin.getWorkspace().getRoot()
					.getLocation().toPortableString();
			filterPath += "/" + defaultTargetProject;

			dialog.setFilterPath(filterPath);

			String path = dialog.open();
			if (path != null) {

				File file = new File(path);
				if (file.isDirectory()) {
					text.setText(file.toString());
				}
			}
		}
	}

	private String defaultTargetProject;
	private String defaultTemplateFilePath;

	private Combo projectCombo;
	private Text templateFileText;
	
	private Text scopePathText;
	private String defaultScopePath;
	
	private Text excludePathText;
	private String defaultExcludePath;

	private Text typeClusteringDistanceThresholdText;
	private String defaultTypeClusteringDistanceThreshold = "5";
	
	private Text typeNameSimilarityText;
	private String typeNameSimilarity = "35";
	
	private Text typeSuperClassSimilarityText;
	private String typeSuperClassSimilarity = "15";
	
	private Text typeInterfaceSimilarityText;
	private String typeInterfaceSimilarity = "15";
	
	private Text typeBodySimilarityText;
	private String typeBodySimilarity = "35";
	
	private Text methodCorrespondingThresholdText;
	private String methodCorrespondingThreshold = "0.8";
	
	private Text fieldCorrespondingThresholdText;
	private String fieldCorrespondingThreshold = "0.6";
	
	private Text innerTypeCorrespondingThresholdText;
	private String innerTypeCorrespondingThreshold = "0.5";
	
	private Text connectingThresholdText;
	private String defaultConnectingThreshold = "0.5";

	public static final String TARGET_PORJECT = "targetProjectName";
	public static final String TEMPLATE_FILE_LOCATION = "templateFile";
	public static final String SCOPE_PATH = "scopePath";
	public static final String EXCLUDE_PATH = "exludePath";
	
	public static final String TYPE_CLUSTERING_THRESHOLD = "typeClusteringDistanceThreshold";

	public static final String TYPE_NAME_SIMILARITY = "typeNameSimilarity";
	public static final String TYPE_INTERFACE_SIMILARITY = "typeInterfaceSimilarity";
	public static final String TYPE_SUPER_CLASS_SIMILARITY = "typeSuperClassSimilarity";
	public static final String TYPE_BODY_SIMILARITY = "typeBodySimilarity";
	
	public static final String METHOD_CORRESPONDING_THRESHOLD = "methodCorrespondingThreshold";
	public static final String FIELD_CORRESPONDING_THRESHOLD = "fieldCorrespondingThreshold";
	public static final String INNER_TYPE_CORRESPONDING_THRESHOLD = "innerTypeCorrespondingThreshold";
	
	public static final String CONNECTING_THRESHOLD = "connectingThreshold";

	public TemplateGenerationPreferencePage() {
	}

	public TemplateGenerationPreferencePage(String title) {
		super(title);
	}

	public TemplateGenerationPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}
	
	private String retriveValue(String key){
		return Activator.getDefault().getPreferenceStore().getString(key);	
	}
	
	private boolean isValidate(String value){
		return value != null &&
				value.length() != 0;
	}

	@Override
	public void init(IWorkbench workbench) {

		this.defaultTargetProject = retriveValue(TARGET_PORJECT);
		this.defaultTemplateFilePath = retriveValue(TEMPLATE_FILE_LOCATION);
		this.defaultScopePath = retriveValue(SCOPE_PATH);
		this.defaultExcludePath = retriveValue(EXCLUDE_PATH);

		this.defaultTypeClusteringDistanceThreshold = isValidate(retriveValue(TYPE_CLUSTERING_THRESHOLD)) ? 
				retriveValue(TYPE_CLUSTERING_THRESHOLD) : defaultTypeClusteringDistanceThreshold;
		this.typeNameSimilarity = isValidate(retriveValue(TYPE_NAME_SIMILARITY)) ? 
				retriveValue(TYPE_NAME_SIMILARITY) : typeNameSimilarity;

		this.typeInterfaceSimilarity = isValidate(retriveValue(TYPE_INTERFACE_SIMILARITY)) ? 
				retriveValue(TYPE_INTERFACE_SIMILARITY) : typeInterfaceSimilarity;
		this.typeSuperClassSimilarity = isValidate(retriveValue(TYPE_SUPER_CLASS_SIMILARITY)) ? 
				retriveValue(TYPE_SUPER_CLASS_SIMILARITY) : typeSuperClassSimilarity;

		this.typeBodySimilarity = isValidate(retriveValue(TYPE_BODY_SIMILARITY)) ? 
				retriveValue(TYPE_BODY_SIMILARITY) : typeBodySimilarity;
				
		this.defaultConnectingThreshold = isValidate(retriveValue(CONNECTING_THRESHOLD)) ?
				retriveValue(CONNECTING_THRESHOLD) : defaultConnectingThreshold;
				
		this.innerTypeCorrespondingThreshold = isValidate(retriveValue(INNER_TYPE_CORRESPONDING_THRESHOLD))?
				retriveValue(INNER_TYPE_CORRESPONDING_THRESHOLD) : innerTypeCorrespondingThreshold;
		this.methodCorrespondingThreshold = isValidate(retriveValue(METHOD_CORRESPONDING_THRESHOLD))?
				retriveValue(METHOD_CORRESPONDING_THRESHOLD) : methodCorrespondingThreshold;
		this.fieldCorrespondingThreshold = isValidate(retriveValue(FIELD_CORRESPONDING_THRESHOLD))?
				retriveValue(FIELD_CORRESPONDING_THRESHOLD) : fieldCorrespondingThreshold;
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;

		composite.setLayout(layout);
		Label projectLabel = new Label(composite, SWT.NONE);
		projectLabel.setText("target project");

		projectCombo = new Combo(composite, SWT.BORDER);
		projectCombo.setItems(getProjectsInWorkspace());
		projectCombo.setText(this.defaultTargetProject);
		GridData comboData = new GridData(SWT.FILL, SWT.FILL, true, false);
		comboData.horizontalSpan = 2;
		projectCombo.setLayoutData(comboData);
		projectCombo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String projectName = projectCombo.getText();
				String templateFileName = getPersistentFileName(projectName,
						"data.xml");
				templateFileText.setText(templateFileName);
			}
		});

		createTemplateFileText(composite);
		createScopePathText(composite);
		createExcludePathText(composite);

		// Label projectLabel = new Label(composite, SWT.NONE);
		// projectLabel.setText("Example");

		createClusteringGroup(composite);
		createCorrespondingGroup(composite);
		
		createDesigningFormingGroup(composite);

		return composite;
	}

	private void createCorrespondingGroup(Composite composite) {
		Group templateGroup = new Group(composite, SWT.NONE);
		templateGroup.setText("parameters for corresponding methods, fields, and inner classes");

		GridData diffGroupData = new GridData(SWT.FILL, SWT.FILL, true, true);
		diffGroupData.horizontalSpan = 3;
		templateGroup.setLayoutData(diffGroupData);

		GridLayout layout = new GridLayout();
		layout.numColumns = 3;

		templateGroup.setLayout(layout);

		createMethodCorrespondingText(templateGroup);
		createFieldCorrespondingText(templateGroup);
		createInnerClassCorrespondingText(templateGroup);
	}

	private void createInnerClassCorrespondingText(Group group) {
		Label abstractMethodCallStrengthLabel = new Label(group, SWT.NONE);
		abstractMethodCallStrengthLabel
				.setText("inner class corresponding threshold (0~1)");

		innerTypeCorrespondingThresholdText = new Text(group, SWT.BORDER);
		innerTypeCorrespondingThresholdText.setText(String
				.valueOf(this.innerTypeCorrespondingThreshold));
		GridData abstractMethodData = new GridData(SWT.FILL, SWT.FILL, true,
				false);
		abstractMethodData.horizontalSpan = 2;
		innerTypeCorrespondingThresholdText.setLayoutData(abstractMethodData);
		
	}

	private void createFieldCorrespondingText(Group group) {
		Label abstractMethodCallStrengthLabel = new Label(group, SWT.NONE);
		abstractMethodCallStrengthLabel
				.setText("field corresponding threshold (0~1)");

		fieldCorrespondingThresholdText = new Text(group, SWT.BORDER);
		fieldCorrespondingThresholdText.setText(String
				.valueOf(this.fieldCorrespondingThreshold));
		GridData abstractMethodData = new GridData(SWT.FILL, SWT.FILL, true,
				false);
		abstractMethodData.horizontalSpan = 2;
		fieldCorrespondingThresholdText.setLayoutData(abstractMethodData);
	}

	private void createMethodCorrespondingText(Group group) {
		Label abstractMethodCallStrengthLabel = new Label(group, SWT.NONE);
		abstractMethodCallStrengthLabel
				.setText("method corresponding threshold (0~1)");

		methodCorrespondingThresholdText = new Text(group, SWT.BORDER);
		methodCorrespondingThresholdText.setText(String
				.valueOf(this.methodCorrespondingThreshold));
		GridData abstractMethodData = new GridData(SWT.FILL, SWT.FILL, true,
				false);
		abstractMethodData.horizontalSpan = 2;
		methodCorrespondingThresholdText.setLayoutData(abstractMethodData);
	}

	private void createTemplateFileText(Composite composite) {
		Label templateFileLabel = new Label(composite, SWT.NONE);
		templateFileLabel.setText("clone file:");
		templateFileText = new Text(composite, SWT.BORDER);
		templateFileText.setText(this.defaultTemplateFilePath);
		GridData textData = new GridData(SWT.FILL, SWT.FILL, true, false);
		templateFileText.setLayoutData(textData);
		Button templateFileButton = new Button(composite, SWT.NONE);
		templateFileButton.setText("Browse");

		templateFileButton.addSelectionListener(new FileSelectionAdapter(
				templateFileText));
	}

	private void createScopePathText(Composite composite) {
		Label scopePathLabel = new Label(composite, SWT.NONE);
		scopePathLabel.setText("scope path:");
		scopePathText = new Text(composite, SWT.BORDER);
		scopePathText.setText(this.defaultScopePath);
		GridData textData = new GridData(SWT.FILL, SWT.FILL, true, false);
		scopePathText.setLayoutData(textData);
		Button scopePathButton = new Button(composite, SWT.NONE);
		scopePathButton.setText("Browse");

		scopePathButton.addSelectionListener(new FolderSelectionAdapter(
				scopePathText));
	}
	
	private void createExcludePathText(Composite composite) {
		Label excludePathLabel = new Label(composite, SWT.NONE);
		excludePathLabel.setText("exclude path:");
		excludePathText = new Text(composite, SWT.BORDER);
		excludePathText.setText(this.defaultExcludePath);
		GridData textData = new GridData(SWT.FILL, SWT.FILL, true, false);
		excludePathText.setLayoutData(textData);
		Button excludePathButton = new Button(composite, SWT.NONE);
		excludePathButton.setText("Browse");

		excludePathButton.addSelectionListener(new FolderSelectionAdapter(
				excludePathText));
	}

	private String[] getProjectsInWorkspace() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();

		String[] projectStrings = new String[projects.length];
		for (int i = 0; i < projects.length; i++) {
			projectStrings[i] = projects[i].getName();
		}

		return projectStrings;
	}

	private String getPersistentFileName(String projectName, String fileName) {
		File ontologyFile = new File("configurations" + File.separator
				+ projectName + File.separator + fileName);
		return ontologyFile.getAbsolutePath();
	}
	
	/**
	 * 
	 * @param parent
	 */
	private void createDesigningFormingGroup(Composite parent) {
		Group formingGroup = new Group(parent, SWT.NONE);
		formingGroup.setText("parameters for forming design");

		GridData diffGroupData = new GridData(SWT.FILL, SWT.FILL, true, true);
		diffGroupData.horizontalSpan = 3;
		formingGroup.setLayoutData(diffGroupData);

		GridLayout layout = new GridLayout();
		layout.numColumns = 3;

		formingGroup.setLayout(layout);

		createConnectingThresholdText(formingGroup);
	}
	
	/**
	 * 
	 * 
	 * @param group
	 */
	private void createConnectingThresholdText(Group group) {
		Label label = new Label(group, SWT.NONE);
		label.setText("connecting threshold (0~1)");

		connectingThresholdText = new Text(group, SWT.BORDER);
		connectingThresholdText.setText(String
				.valueOf(this.defaultConnectingThreshold));
		GridData abstractMethodData = new GridData(SWT.FILL, SWT.FILL, true,
				false);
		abstractMethodData.horizontalSpan = 2;
		connectingThresholdText.setLayoutData(abstractMethodData);
	}

	/**
	 * 
	 * @param parent
	 */
	private void createClusteringGroup(Composite parent) {
		Group templateGroup = new Group(parent, SWT.NONE);
		templateGroup.setText("parameters for corresponding types");

		GridData diffGroupData = new GridData(SWT.FILL, SWT.FILL, true, true);
		diffGroupData.horizontalSpan = 3;
		templateGroup.setLayoutData(diffGroupData);

		GridLayout layout = new GridLayout();
		layout.numColumns = 3;

		templateGroup.setLayout(layout);

		createTypeClusteringDistanceThresholdText(templateGroup);
		createTypeNameSimilarityText(templateGroup);
		createTypeInterfaceSimilarityText(templateGroup);
		createTypeSuperClassSimilarityText(templateGroup);
		createTypeBodySimilarityText(templateGroup);
	}

	private void createTypeNameSimilarityText(Group group) {
		Label label = new Label(group, SWT.NONE);
		label.setText("type name threshold (1~100)");

		typeNameSimilarityText = new Text(group, SWT.BORDER);
		typeNameSimilarityText.setText(String
				.valueOf(this.typeNameSimilarity));
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
		data.horizontalSpan = 2;
		typeNameSimilarityText.setLayoutData(data);
	}

	private void createTypeSuperClassSimilarityText(Group group) {
		Label label = new Label(group, SWT.NONE);
		label.setText("type super class similarity (1~100)");

		typeSuperClassSimilarityText = new Text(group, SWT.BORDER);
		typeSuperClassSimilarityText.setText(String
				.valueOf(this.typeSuperClassSimilarity));
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
		data.horizontalSpan = 2;
		typeSuperClassSimilarityText.setLayoutData(data);
	}

	private void createTypeInterfaceSimilarityText(Group group) {
		Label label = new Label(group, SWT.NONE);
		label.setText("type interface similarity (1~100)");

		typeInterfaceSimilarityText = new Text(group, SWT.BORDER);
		typeInterfaceSimilarityText.setText(String
				.valueOf(this.typeInterfaceSimilarity));
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
		data.horizontalSpan = 2;
		typeInterfaceSimilarityText.setLayoutData(data);
	}

	/**
	 * Cloned from clonepedia.preference.ClonepediaPreferencePage by Luminosite.
	 * 
	 * @param group
	 */
	private void createTypeClusteringDistanceThresholdText(Group group) {
		Label abstractMethodCallStrengthLabel = new Label(group, SWT.NONE);
		abstractMethodCallStrengthLabel
				.setText("type clustering distance (> 1)");

		typeClusteringDistanceThresholdText = new Text(group, SWT.BORDER);
		typeClusteringDistanceThresholdText.setText(String
				.valueOf(this.defaultTypeClusteringDistanceThreshold));
		GridData abstractMethodData = new GridData(SWT.FILL, SWT.FILL, true,
				false);
		abstractMethodData.horizontalSpan = 2;
		typeClusteringDistanceThresholdText.setLayoutData(abstractMethodData);
	}

	/**
	 * Cloned from clonepedia.preference.ClonepediaPreferencePage by Luminosite.
	 * 
	 * @param group
	 */
	private void createTypeBodySimilarityText(Group group) {
		Label templateMethodCallStrengthLabel = new Label(group, SWT.NONE);
		templateMethodCallStrengthLabel.setText("type body similarity (1~100)");

		typeBodySimilarityText = new Text(group, SWT.BORDER);
		typeBodySimilarityText.setText(String
				.valueOf(this.typeBodySimilarity));
		GridData templateMethodData = new GridData(SWT.FILL, SWT.FILL, true,
				false);
		templateMethodData.horizontalSpan = 2;
		typeBodySimilarityText.setLayoutData(templateMethodData);
	}

	public boolean performOk() {
		Preferences preferences = ConfigurationScope.INSTANCE.getNode("Clonepedia");
		
		preferences.put(TARGET_PORJECT, this.defaultTargetProject);
		preferences.put(TYPE_BODY_SIMILARITY, this.typeBodySimilarityText.getText());
		preferences.put(TYPE_CLUSTERING_THRESHOLD, this.typeClusteringDistanceThresholdText.getText());
		preferences.put(TYPE_INTERFACE_SIMILARITY, this.typeInterfaceSimilarityText.getText());
		preferences.put(TYPE_SUPER_CLASS_SIMILARITY, this.typeSuperClassSimilarityText.getText());
		preferences.put(TYPE_NAME_SIMILARITY, this.typeNameSimilarityText.getText());
		preferences.put(TEMPLATE_FILE_LOCATION, this.templateFileText.getText());
		preferences.put(SCOPE_PATH, this.scopePathText.getText());
		preferences.put(EXCLUDE_PATH, this.excludePathText.getText());
		preferences.put(CONNECTING_THRESHOLD, this.connectingThresholdText.getText());
		preferences.put(METHOD_CORRESPONDING_THRESHOLD, this.methodCorrespondingThresholdText.getText());
		preferences.put(FIELD_CORRESPONDING_THRESHOLD, this.fieldCorrespondingThresholdText.getText());
		preferences.put(INNER_TYPE_CORRESPONDING_THRESHOLD, this.innerTypeCorrespondingThresholdText.getText());

		Activator.getDefault().getPreferenceStore().putValue(TARGET_PORJECT, this.projectCombo.getText());
		Activator.getDefault().getPreferenceStore().putValue(TYPE_BODY_SIMILARITY, this.typeBodySimilarityText.getText());
		Activator.getDefault().getPreferenceStore().putValue(TYPE_CLUSTERING_THRESHOLD, this.typeClusteringDistanceThresholdText.getText());
		Activator.getDefault().getPreferenceStore().putValue(TYPE_INTERFACE_SIMILARITY,	this.typeInterfaceSimilarityText.getText());
		Activator.getDefault().getPreferenceStore().putValue(TYPE_SUPER_CLASS_SIMILARITY, this.typeSuperClassSimilarityText.getText());
		Activator.getDefault().getPreferenceStore().putValue(TYPE_NAME_SIMILARITY, this.typeNameSimilarityText.getText());
		Activator.getDefault().getPreferenceStore().putValue(TEMPLATE_FILE_LOCATION, this.templateFileText.getText());
		Activator.getDefault().getPreferenceStore().putValue(SCOPE_PATH, this.scopePathText.getText());
		Activator.getDefault().getPreferenceStore().putValue(EXCLUDE_PATH, this.excludePathText.getText());
		Activator.getDefault().getPreferenceStore().putValue(CONNECTING_THRESHOLD, this.connectingThresholdText.getText());
		Activator.getDefault().getPreferenceStore().putValue(METHOD_CORRESPONDING_THRESHOLD, this.methodCorrespondingThresholdText.getText());
		Activator.getDefault().getPreferenceStore().putValue(FIELD_CORRESPONDING_THRESHOLD, this.fieldCorrespondingThresholdText.getText());
		Activator.getDefault().getPreferenceStore().putValue(INNER_TYPE_CORRESPONDING_THRESHOLD, this.innerTypeCorrespondingThresholdText.getText());

		confirmChanges();

		return true;

	}

	private void confirmChanges() {
//		Settings.templateMethodGroupCallingStrength = Integer
//				.valueOf(this.typeBodySimilarityText.getText());
//		Settings.abstractMethodGroupCallingStrength = Integer
//				.valueOf(this.typeClusteringDistanceThresholdText.getText());
//
//		Settings.thresholdDistanceForTMGFilteringAndSplitting = Double
//				.valueOf(this.typeInterfaceSimilarityText.getText());
//		Settings.thresholdDistanceForTMGLocationClustering = Double
//				.valueOf(this.typeSuperClassSimilarityText.getText());
//		Settings.thresholdDistanceForDeclaringClassClustering = Double
//				.valueOf(this.typeNameSimilarityText.getText());
		/*
		 * AutoGenCTSettings.templateFileLocation =
		 * this.templateFileText.getText();
		 */
	}

}
