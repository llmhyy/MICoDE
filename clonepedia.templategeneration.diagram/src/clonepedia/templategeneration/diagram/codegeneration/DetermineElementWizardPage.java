package clonepedia.templategeneration.diagram.codegeneration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.jdt.internal.corext.refactoring.StubTypeContext;
import org.eclipse.jdt.internal.corext.refactoring.TypeContextChecker;
import org.eclipse.jdt.internal.corext.util.JavaConventionsUtil;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.corext.util.Messages;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.internal.ui.dialogs.TableTextCellEditor;
import org.eclipse.jdt.internal.ui.dialogs.TextFieldNavigationHandler;
import org.eclipse.jdt.internal.ui.javaeditor.ASTProvider;
import org.eclipse.jdt.internal.ui.preferences.CodeTemplatePreferencePage;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.CompletionContextRequestor;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.JavaPackageCompletionProcessor;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.JavaTypeCompletionProcessor;
import org.eclipse.jdt.internal.ui.util.SWTUtil;
import org.eclipse.jdt.internal.ui.viewsupport.BasicElementLabels;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.internal.ui.wizards.SuperInterfaceSelectionDialog;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IListAdapter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.ListDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.SelectionButtonDialogFieldGroup;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.Separator;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.StringButtonStatusDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.eclipse.jdt.ui.wizards.NewContainerWizardPage;
import org.eclipse.jdt.ui.wizards.NewInterfaceWizardPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * @author linyun
 * 
 * Copy from the class <code>NewTypeWizardPage</code>, (^_^) containing controls and
 * validation routines for a 'New Type WizardPage'. Implementors decide which
 * components to add and to enable. Implementors can also customize the
 * validation code. <code>NewTypeWizardPage</code> is intended to serve as base
 * class of all wizards that create types like applets, servlets, classes,
 * interfaces, etc.
 * <p>
 * See {@link NewClassWizardPage} or {@link NewInterfaceWizardPage} for an
 * example usage of the <code>NewTypeWizardPage</code>.
 * </p>
 * 
 * @see org.eclipse.jdt.ui.wizards.NewClassWizardPage
 * @see org.eclipse.jdt.ui.wizards.NewInterfaceWizardPage
 * @see org.eclipse.jdt.ui.wizards.NewEnumWizardPage
 * @see org.eclipse.jdt.ui.wizards.NewAnnotationWizardPage
 * 
 * @since 2.0
 */
@SuppressWarnings({ "deprecation", "restriction" })
public abstract class DetermineElementWizardPage extends NewContainerWizardPage {

	public final static String method_return_type = "Return type: ";
	public final static String method_name = "Name: ";
	public final static String method_parameters = "Parameters: ";
	
	private final String[] primitiveTypes = {"int", "float", "double", "long", "short", "char", "boolean"};

	/**
	 * Public access flag. See The Java Virtual Machine Specification for more
	 * details.
	 */
	public int F_PUBLIC = Flags.AccPublic;
	/**
	 * Private access flag. See The Java Virtual Machine Specification for more
	 * details.
	 */
	public int F_PRIVATE = Flags.AccPrivate;
	/**
	 * Protected access flag. See The Java Virtual Machine Specification for
	 * more details.
	 */
	public int F_PROTECTED = Flags.AccProtected;
	/**
	 * Static access flag. See The Java Virtual Machine Specification for more
	 * details.
	 */
	public int F_STATIC = Flags.AccStatic;
	/**
	 * Final access flag. See The Java Virtual Machine Specification for more
	 * details.
	 */
	public int F_FINAL = Flags.AccFinal;
	/**
	 * Abstract property flag. See The Java Virtual Machine Specification for
	 * more details.
	 */
	public int F_ABSTRACT = Flags.AccAbstract;

	private final static String PAGE_NAME = "NewTypeWizardPage"; //$NON-NLS-1$

	/** Field ID of the package input field. */
	protected final static String PACKAGE = PAGE_NAME + ".package"; //$NON-NLS-1$
	/** Field ID of the enclosing type input field. */
	protected final static String ENCLOSING = PAGE_NAME + ".enclosing"; //$NON-NLS-1$
	/** Field ID of the enclosing type checkbox. */
	protected final static String ENCLOSINGSELECTION = ENCLOSING + ".selection"; //$NON-NLS-1$
	/** Field ID of the type name input field. */
	protected final static String TYPENAME = PAGE_NAME + ".typename"; //$NON-NLS-1$
	/** Field ID of the super type input field. */
	protected final static String SUPER = PAGE_NAME + ".superclass"; //$NON-NLS-1$
	/** Field ID of the super interfaces input field. */
	protected final static String INTERFACES = PAGE_NAME + ".interfaces"; //$NON-NLS-1$
	/** Field ID of the modifier check boxes. */
	protected final static String MODIFIERS = PAGE_NAME + ".modifiers"; //$NON-NLS-1$
	/** Field ID of the method stubs check boxes. */
	protected final static String METHODS = PAGE_NAME + ".methods"; //$NON-NLS-1$
	protected final static String FIELDS = PAGE_NAME + ".fields"; //$NON-NLS-1$

	private static class ElementWrapper {

	}

	private static class InterfaceWrapper extends ElementWrapper {
		public String interfaceName;

		public InterfaceWrapper(String interfaceName) {
			this.interfaceName = interfaceName;
		}

		@Override
		public int hashCode() {
			return interfaceName.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return obj != null
					&& getClass().equals(obj.getClass())
					&& ((InterfaceWrapper) obj).interfaceName
							.equals(interfaceName);
		}
	}

	private static class InterfacesListLabelProvider extends LabelProvider {
		private Image fInterfaceImage;

		public InterfacesListLabelProvider() {
			fInterfaceImage = JavaPluginImages
					.get(JavaPluginImages.IMG_OBJS_INTERFACE);
		}

		@Override
		public String getText(Object element) {
			return BasicElementLabels
					.getJavaElementName(((InterfaceWrapper) element).interfaceName);
		}

		@Override
		public Image getImage(Object element) {
			return fInterfaceImage;
		}
	}

	public class MethodParameterWrapper extends ElementWrapper {
		public String parameterType = "";
		public String parameterName = "";

		public MethodParameterWrapper(String parameterType, String parameterName) {
			this.parameterType = parameterType;
			this.parameterName = parameterName;
		}

		@Override
		public int hashCode() {
			return toString().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return obj != null
					&& getClass().equals(obj.getClass())
					&& ((MethodParameterWrapper) obj).parameterType
							.equals(parameterType)
					&& ((MethodParameterWrapper) obj).parameterName
							.equals(parameterName);
		}

		public String toString() {
			return parameterType + ":" + parameterName;
		}
	}

	private static class MethodParameterListLabelProvider extends LabelProvider {
		private Image fparameterImage;

		public MethodParameterListLabelProvider() {
			fparameterImage = JavaPluginImages
					.get(JavaPluginImages.IMG_OBJS_INFO_ALT);
		}

		@Override
		public String getText(Object element) {
			return ((MethodParameterWrapper) element).toString();
		}

		@Override
		public Image getImage(Object element) {
			return fparameterImage;
		}
	}

	protected StringButtonStatusDialogField fPackageDialogField;

	protected SelectionButtonDialogField fEnclosingTypeSelection;
	protected StringButtonDialogField fEnclosingTypeDialogField;

	private boolean fCanModifyPackage;
	private boolean fCanModifyEnclosingType;

	private IPackageFragment fCurrPackage;

	private IType fCurrEnclosingType;
	/**
	 * a handle to the type to be created (does usually not exist, can be null)
	 */
	private IType fCurrType;
	protected StringButtonDialogField fTypeNameDialogField;

	private StringButtonDialogField fSuperClassDialogField;
	private ListDialogField<InterfaceWrapper> fSuperInterfacesDialogField;

	private SelectionButtonDialogFieldGroup fAccMdfButtons;
	private SelectionButtonDialogFieldGroup fOtherMdfButtons;

	private SelectionButtonDialogField fAddCommentButton;
	private boolean fUseAddCommentButtonValue; // used for compatibility:
												// Wizards that don't show the
												// comment button control
	// will use the preferences settings

	/**
	 * @author linyun 
	 * The following three fields are added for creating new method
	 */
	private StringButtonDialogField fMethodReturnTypeField;
	private StringDialogField fMethodNameField;
	private ListDialogField<MethodParameterWrapper> fMethodParametersDialogField;

	private StringButtonDialogField fFieldTypeField;
	private StringDialogField fFieldNameField;
	
	private IType fCreatedType;

	private JavaPackageCompletionProcessor fCurrPackageCompletionProcessor;
	private JavaTypeCompletionProcessor fEnclosingTypeCompletionProcessor;
	private StubTypeContext fSuperClassStubTypeContext;
	private StubTypeContext fSuperInterfaceStubTypeContext;

	protected IStatus fEnclosingTypeStatus;
	protected IStatus fPackageStatus;
	protected IStatus fTypeNameStatus;
	protected IStatus fSuperClassStatus;
	protected IStatus fModifierStatus;
	protected IStatus fSuperInterfacesStatus;

	protected IStatus fMethodReturnTypeStatus;
	protected IStatus fMethodNameStatus;
	protected IStatus fMethodParametersStatus;
	
	protected IStatus fFieldTypeStatus;
	protected IStatus fFieldNameStatus;

	private final int PUBLIC_INDEX = 0, DEFAULT_INDEX = 1, PRIVATE_INDEX = 2,
			PROTECTED_INDEX = 3;
	private final int ABSTRACT_INDEX = 0, FINAL_INDEX = 1, STATIC_INDEX = 2,
			ENUM_ANNOT_STATIC_INDEX = 1;

	private int fTypeKind;

	/**
	 * Constant to signal that the created type is a class.
	 * 
	 * @since 3.1
	 */
	public static final int CLASS_TYPE = 1;

	/**
	 * Constant to signal that the created type is a interface.
	 * 
	 * @since 3.1
	 */
	public static final int INTERFACE_TYPE = 2;

	/**
	 * Constant to signal that the created type is an enum.
	 * 
	 * @since 3.1
	 */
	public static final int ENUM_TYPE = 3;

	/**
	 * Constant to signal that the created type is an annotation.
	 * 
	 * @since 3.1
	 */
	public static final int ANNOTATION_TYPE = 4;

	/**
	 * Creates a new <code>NewTypeWizardPage</code>.
	 * 
	 * @param isClass
	 *            <code>true</code> if a new class is to be created; otherwise
	 *            an interface is to be created
	 * @param pageName
	 *            the wizard page's name
	 */
	public DetermineElementWizardPage(boolean isClass, String pageName) {
		this(isClass ? CLASS_TYPE : INTERFACE_TYPE, pageName);
	}

	/**
	 * Creates a new <code>NewTypeWizardPage</code>.
	 * 
	 * @param typeKind
	 *            Signals the kind of the type to be created. Valid kinds are
	 *            {@link #CLASS_TYPE}, {@link #INTERFACE_TYPE},
	 *            {@link #ENUM_TYPE} and {@link #ANNOTATION_TYPE}
	 * @param pageName
	 *            the wizard page's name
	 * @since 3.1
	 */
	public DetermineElementWizardPage(int typeKind, String pageName) {
		super(pageName);
		fTypeKind = typeKind;

		fCreatedType = null;

		TypeFieldsAdapter adapter = new TypeFieldsAdapter();
		TypeFieldsAdapter2 adapterForParameter = new TypeFieldsAdapter2();

		fPackageDialogField = new StringButtonStatusDialogField(adapter);
		fPackageDialogField.setDialogFieldListener(adapter);
		fPackageDialogField.setLabelText(getPackageLabel());
		fPackageDialogField
				.setButtonLabel(NewWizardMessages.NewTypeWizardPage_package_button);
		fPackageDialogField
				.setStatusWidthHint(NewWizardMessages.NewTypeWizardPage_default);

		fEnclosingTypeSelection = new SelectionButtonDialogField(SWT.CHECK);
		fEnclosingTypeSelection.setDialogFieldListener(adapter);
		fEnclosingTypeSelection.setLabelText(getEnclosingTypeLabel());

		fEnclosingTypeDialogField = new StringButtonDialogField(adapter);
		fEnclosingTypeDialogField.setDialogFieldListener(adapter);
		fEnclosingTypeDialogField
				.setButtonLabel(NewWizardMessages.NewTypeWizardPage_enclosing_button);

		fTypeNameDialogField = new StringButtonDialogField(adapter);
		fTypeNameDialogField.setDialogFieldListener(adapter);
		fTypeNameDialogField.setLabelText(getTypeNameLabel());
		fTypeNameDialogField
				.setButtonLabel(NewWizardMessages.NewTypeWizardPage_package_button);

		fSuperClassDialogField = new StringButtonDialogField(adapter);
		fSuperClassDialogField.setDialogFieldListener(adapter);
		fSuperClassDialogField.setLabelText(getSuperClassLabel());
		fSuperClassDialogField
				.setButtonLabel(NewWizardMessages.NewTypeWizardPage_superclass_button);

		String[] addButtons = new String[] {
				NewWizardMessages.NewTypeWizardPage_interfaces_add,
				/* 1 */null,
				NewWizardMessages.NewTypeWizardPage_interfaces_remove };
		fSuperInterfacesDialogField = new ListDialogField<InterfaceWrapper>(
				adapter, addButtons, new InterfacesListLabelProvider());
		fSuperInterfacesDialogField.setDialogFieldListener(adapter);
		fSuperInterfacesDialogField
				.setTableColumns(new ListDialogField.ColumnsDescription(1,
						false));
		fSuperInterfacesDialogField.setLabelText(getSuperInterfacesLabel());
		fSuperInterfacesDialogField.setRemoveButtonIndex(2);

		fMethodReturnTypeField = new StringButtonDialogField(adapter);
		fMethodReturnTypeField.setDialogFieldListener(adapter);
		fMethodReturnTypeField.setLabelText(getMethodReturnTypeLabel());
		fMethodReturnTypeField.setButtonLabel("Browse");

		fMethodNameField = new StringDialogField();
		fMethodNameField.setDialogFieldListener(adapter);
		fMethodNameField.setLabelText(getMethodNameLabel());

		fMethodParametersDialogField = new ListDialogField<MethodParameterWrapper>(
				adapterForParameter, addButtons, new MethodParameterListLabelProvider());
		fMethodParametersDialogField.setDialogFieldListener(adapterForParameter);
		fMethodParametersDialogField.setTableColumns(new ListDialogField.ColumnsDescription(1, false));
		fMethodParametersDialogField.setLabelText(getMethodParametersLabel());
		fMethodParametersDialogField.setRemoveButtonIndex(2);
		
		fFieldTypeField = new StringButtonDialogField(adapter);
		fFieldTypeField.setDialogFieldListener(adapter);
		fFieldTypeField.setLabelText(getMethodReturnTypeLabel());
		fFieldTypeField.setButtonLabel("Browse");

		fFieldNameField = new StringDialogField();
		fFieldNameField.setDialogFieldListener(adapter);
		fFieldNameField.setLabelText(getMethodNameLabel());

		String[] buttonNames1 = new String[] {
				NewWizardMessages.NewTypeWizardPage_modifiers_public,
				NewWizardMessages.NewTypeWizardPage_modifiers_default,
				NewWizardMessages.NewTypeWizardPage_modifiers_private,
				NewWizardMessages.NewTypeWizardPage_modifiers_protected };
		fAccMdfButtons = new SelectionButtonDialogFieldGroup(SWT.RADIO,
				buttonNames1, 4);
		fAccMdfButtons.setDialogFieldListener(adapter);
		fAccMdfButtons.setLabelText(getModifiersLabel());
		fAccMdfButtons.setSelection(0, true);

		String[] buttonNames2;
		if (fTypeKind == CLASS_TYPE) {
			buttonNames2 = new String[] {
					NewWizardMessages.NewTypeWizardPage_modifiers_abstract,
					NewWizardMessages.NewTypeWizardPage_modifiers_final,
					NewWizardMessages.NewTypeWizardPage_modifiers_static };
		} else {
			if (fTypeKind == ENUM_TYPE || fTypeKind == ANNOTATION_TYPE) {
				buttonNames2 = new String[] {
						NewWizardMessages.NewTypeWizardPage_modifiers_abstract,
						NewWizardMessages.NewTypeWizardPage_modifiers_static };
			} else
				buttonNames2 = new String[] {};
		}

		fOtherMdfButtons = new SelectionButtonDialogFieldGroup(SWT.CHECK,
				buttonNames2, 4);
		fOtherMdfButtons.setDialogFieldListener(adapter);

		fAccMdfButtons.enableSelectionButton(PRIVATE_INDEX, false);
		fAccMdfButtons.enableSelectionButton(PROTECTED_INDEX, false);
		fOtherMdfButtons.enableSelectionButton(STATIC_INDEX, false);

		if (fTypeKind == ENUM_TYPE || fTypeKind == ANNOTATION_TYPE) {
			fOtherMdfButtons.enableSelectionButton(ABSTRACT_INDEX, false);
			fOtherMdfButtons.enableSelectionButton(ENUM_ANNOT_STATIC_INDEX,
					false);
		}

		fAddCommentButton = new SelectionButtonDialogField(SWT.CHECK);
		fAddCommentButton
				.setLabelText(NewWizardMessages.NewTypeWizardPage_addcomment_label);

		fUseAddCommentButtonValue = false; // only used when enabled

		fCurrPackageCompletionProcessor = new JavaPackageCompletionProcessor();
		fEnclosingTypeCompletionProcessor = new JavaTypeCompletionProcessor(
				false, false, true);

		fPackageStatus = new StatusInfo();
		fEnclosingTypeStatus = new StatusInfo();

		fCanModifyPackage = true;
		fCanModifyEnclosingType = true;
		updateEnableState();

		fTypeNameStatus = new StatusInfo();
		fSuperClassStatus = new StatusInfo();
		fSuperInterfacesStatus = new StatusInfo();
		fModifierStatus = new StatusInfo();
		
		fMethodNameStatus = new StatusInfo();
		fMethodReturnTypeStatus = new StatusInfo();
		fMethodParametersStatus = new StatusInfo();
		
		fFieldNameStatus = new StatusInfo();
		fFieldTypeStatus = new StatusInfo();
	}

	/**
	 * Initializes all fields provided by the page with a given selection.
	 * 
	 * @param elem
	 *            the selection used to initialize this page or <code>
	 * null</code> if no selection was available
	 */
	protected void initTypePage(IJavaElement elem) {
		String initSuperclass = "java.lang.Object"; //$NON-NLS-1$
		ArrayList<String> initSuperinterfaces = new ArrayList<String>(5);

		IJavaProject project = null;
		IPackageFragment pack = null;
		IType enclosingType = null;

		if (elem != null) {
			// evaluate the enclosing type
			project = elem.getJavaProject();
			pack = (IPackageFragment) elem
					.getAncestor(IJavaElement.PACKAGE_FRAGMENT);
			IType typeInCU = (IType) elem.getAncestor(IJavaElement.TYPE);
			if (typeInCU != null) {
				if (typeInCU.getCompilationUnit() != null) {
					enclosingType = typeInCU;
				}
			} else {
				ICompilationUnit cu = (ICompilationUnit) elem
						.getAncestor(IJavaElement.COMPILATION_UNIT);
				if (cu != null) {
					enclosingType = cu.findPrimaryType();
				}
			}

			try {
				IType type = null;
				if (elem.getElementType() == IJavaElement.TYPE) {
					type = (IType) elem;
					if (type.exists()) {
						String superName = SuperInterfaceSelectionDialog
								.getNameWithTypeParameters(type);
						if (type.isInterface()) {
							initSuperinterfaces.add(superName);
						} else {
							initSuperclass = superName;
						}
					}
				}
			} catch (JavaModelException e) {
				JavaPlugin.log(e);
				// ignore this exception now
			}
		}

		String typeName = ""; //$NON-NLS-1$

		ITextSelection selection = getCurrentTextSelection();
		if (selection != null) {
			String text = selection.getText();
			if (text != null && validateJavaTypeName(text, project).isOK()) {
				typeName = text;
			}
		}

		setPackageFragment(pack, true);
		setEnclosingType(enclosingType, true);
		setEnclosingTypeSelection(false, true);

		setTypeName(typeName, true);
		setSuperClass(initSuperclass, true);
		setSuperInterfaces(initSuperinterfaces, true);

		setAddComments(StubUtility.doAddComments(project), true); // from
																	// project
																	// or
																	// workspace
	}

	private static IStatus validateJavaTypeName(String text,
			IJavaProject project) {
		if (project == null || !project.exists()) {
			return JavaConventions.validateJavaTypeName(text,
					JavaCore.VERSION_1_3, JavaCore.VERSION_1_3);
		}
		return JavaConventionsUtil.validateJavaTypeName(text, project);
	}

	private static IStatus validatePackageName(String text, IJavaProject project) {
		if (project == null || !project.exists()) {
			return JavaConventions.validatePackageName(text,
					JavaCore.VERSION_1_3, JavaCore.VERSION_1_3);
		}
		return JavaConventionsUtil.validatePackageName(text, project);
	}
	
	private static IStatus validateMethodName(String text, IJavaProject project){
		if (project == null || !project.exists()) {
			return JavaConventions.validateMethodName(text,
					JavaCore.VERSION_1_3, JavaCore.VERSION_1_3);
		}
		return JavaConventionsUtil.validateMethodName(text, project);
	}

	// -------- UI Creation ---------

	/**
	 * Returns the label that is used for the package input field.
	 * 
	 * @return the label that is used for the package input field.
	 * @since 3.2
	 */
	protected String getPackageLabel() {
		return NewWizardMessages.NewTypeWizardPage_package_label;
	}

	/**
	 * Returns the label that is used for the enclosing type input field.
	 * 
	 * @return the label that is used for the enclosing type input field.
	 * @since 3.2
	 */
	protected String getEnclosingTypeLabel() {
		return NewWizardMessages.NewTypeWizardPage_enclosing_selection_label;
	}

	/**
	 * Returns the label that is used for the type name input field.
	 * 
	 * @return the label that is used for the type name input field.
	 * @since 3.2
	 */
	protected String getTypeNameLabel() {
		return NewWizardMessages.NewTypeWizardPage_typename_label;
	}

	protected String getMethodNameLabel() {
		return method_name;
	}

	/**
	 * Returns the label that is used for the modifiers input field.
	 * 
	 * @return the label that is used for the modifiers input field
	 * @since 3.2
	 */
	protected String getModifiersLabel() {
		return NewWizardMessages.NewTypeWizardPage_modifiers_acc_label;
	}

	/**
	 * Returns the label that is used for the super class input field.
	 * 
	 * @return the label that is used for the super class input field.
	 * @since 3.2
	 */
	protected String getSuperClassLabel() {
		return NewWizardMessages.NewTypeWizardPage_superclass_label;
	}

	protected String getMethodReturnTypeLabel() {
		return method_return_type;
	}

	/**
	 * Returns the label that is used for the super interfaces input field.
	 * 
	 * @return the label that is used for the super interfaces input field.
	 * @since 3.2
	 */
	protected String getSuperInterfacesLabel() {
		if (fTypeKind != INTERFACE_TYPE)
			return NewWizardMessages.NewTypeWizardPage_interfaces_class_label;
		return NewWizardMessages.NewTypeWizardPage_interfaces_ifc_label;
	}
	
	protected String getMethodParametersLabel(){
		return method_parameters;
	}

	/**
	 * Creates a separator line. Expects a <code>GridLayout</code> with at least
	 * 1 column.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 */
	protected void createSeparator(Composite composite, int nColumns) {
		(new Separator(SWT.SEPARATOR | SWT.HORIZONTAL)).doFillIntoGrid(
				composite, nColumns, convertHeightInCharsToPixels(1));
	}

	/**
	 * Creates the controls for the package name field. Expects a
	 * <code>GridLayout</code> with at least 4 columns.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 */
	protected void createPackageControls(Composite composite, int nColumns) {
		fPackageDialogField.doFillIntoGrid(composite, nColumns);
		Text text = fPackageDialogField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		LayoutUtil.setHorizontalGrabbing(text);
		// ControlContentAssistHelper.createTextContentAssistant(text,
		// fCurrPackageCompletionProcessor);
		TextFieldNavigationHandler.install(text);
	}

	/**
	 * Creates the controls for the enclosing type name field. Expects a
	 * <code>GridLayout</code> with at least 4 columns.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 */
	protected void createEnclosingTypeControls(Composite composite, int nColumns) {
		// #6891
		Composite tabGroup = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		tabGroup.setLayout(layout);

		fEnclosingTypeSelection.doFillIntoGrid(tabGroup, 1);

		Text text = fEnclosingTypeDialogField.getTextControl(composite);
		SWTUtil.setAccessibilityText(text,
				NewWizardMessages.NewTypeWizardPage_enclosing_field_description);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = getMaxFieldWidth();
		gd.horizontalSpan = 2;
		text.setLayoutData(gd);

		Button button = fEnclosingTypeDialogField.getChangeControl(composite);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.widthHint = SWTUtil.getButtonWidthHint(button);
		button.setLayoutData(gd);
		// ControlContentAssistHelper.createTextContentAssistant(text,
		// fEnclosingTypeCompletionProcessor);
		TextFieldNavigationHandler.install(text);
	}

	/**
	 * Creates the controls for the type name field. Expects a
	 * <code>GridLayout</code> with at least 2 columns.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 */
	protected void createTypeNameControls(Composite composite, int nColumns) {

		fTypeNameDialogField.doFillIntoGrid(composite, nColumns);
		// DialogField.createEmptySpace(composite);

		Text text = fTypeNameDialogField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		TextFieldNavigationHandler.install(text);

		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				if (fCanModifyPackage && !fEnclosingTypeSelection.isSelected()
						&& e.start == 0
						&& e.end == ((Text) e.widget).getCharCount()) {
					String typeNameWithoutParameters = getTypeNameWithoutParameters(e.text);
					int lastDot = typeNameWithoutParameters.lastIndexOf('.');
					if (lastDot == -1
							|| lastDot == typeNameWithoutParameters.length() - 1)
						return;

					String pack = typeNameWithoutParameters.substring(0,
							lastDot);
					if (validatePackageName(pack, null).getSeverity() == IStatus.ERROR)
						return;

					fPackageDialogField.setText(pack);
					e.text = e.text.substring(lastDot + 1);
				}
			}
		});

		JavaTypeCompletionProcessor superClassCompletionProcessor = new JavaTypeCompletionProcessor(
				false, false, true);
		superClassCompletionProcessor
				.setCompletionContextRequestor(new CompletionContextRequestor() {
					@Override
					public StubTypeContext getStubTypeContext() {
						return getSuperClassStubTypeContext();
					}
				});

		// ControlContentAssistHelper.createTextContentAssistant(text,
		// superClassCompletionProcessor);
		// TextFieldNavigationHandler.install(text);
	}

	/**
	 * Creates the controls for the superclass name field. Expects a
	 * <code>GridLayout</code> with at least 3 columns.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 */
	protected void createSuperClassControls(Composite composite, int nColumns) {
		fSuperClassDialogField.doFillIntoGrid(composite, nColumns);
		Text text = fSuperClassDialogField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());

		JavaTypeCompletionProcessor superClassCompletionProcessor = new JavaTypeCompletionProcessor(
				false, false, true);
		superClassCompletionProcessor
				.setCompletionContextRequestor(new CompletionContextRequestor() {
					@Override
					public StubTypeContext getStubTypeContext() {
						return getSuperClassStubTypeContext();
					}
				});

		// ControlContentAssistHelper.createTextContentAssistant(text,
		// superClassCompletionProcessor);
		TextFieldNavigationHandler.install(text);
	}
	
	protected void createMethodReturnTypeControls(Composite composite, int nColumns) {
		
		fMethodReturnTypeField.doFillIntoGrid(composite, nColumns);
		Text text = fMethodReturnTypeField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		LayoutUtil.setHorizontalGrabbing(text);

		JavaTypeCompletionProcessor superClassCompletionProcessor = new JavaTypeCompletionProcessor(
				false, false, true);
		superClassCompletionProcessor
				.setCompletionContextRequestor(new CompletionContextRequestor() {
					@Override
					public StubTypeContext getStubTypeContext() {
						return getSuperClassStubTypeContext();
					}
				});

		// ControlContentAssistHelper.createTextContentAssistant(text,
		// superClassCompletionProcessor);
		TextFieldNavigationHandler.install(text);
	}
	
	protected void createMethodNameControls(Composite composite, int nColumns) {
		fMethodNameField.doFillIntoGrid(composite, nColumns - 1);
		DialogField.createEmptySpace(composite);

		Text text= fMethodNameField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		TextFieldNavigationHandler.install(text);
	}
	
	protected void createMethodParametersControls(Composite composite, int nColumns){
		final String PARAMETER = "parameter"; //$NON-NLS-1$
		fMethodParametersDialogField.doFillIntoGrid(composite, nColumns);
		final TableViewer tableViewer = fMethodParametersDialogField
				.getTableViewer();
		tableViewer.setColumnProperties(new String[] { PARAMETER });

		TableTextCellEditor cellEditor = new TableTextCellEditor(tableViewer, 0) {
			@Override
			protected void doSetFocus() {
				if (text != null) {
					text.setFocus();
					text.setSelection(text.getText().length());
					checkSelection();
					checkDeleteable();
					checkSelectable();
				}
			}
		};
		JavaTypeCompletionProcessor superInterfaceCompletionProcessor = new JavaTypeCompletionProcessor(
				false, false, true);
		superInterfaceCompletionProcessor
				.setCompletionContextRequestor(new CompletionContextRequestor() {
					@Override
					public StubTypeContext getStubTypeContext() {
						return getSuperInterfacesStubTypeContext();
					}
				});
		// SubjectControlContentAssistant contentAssistant=
		// ControlContentAssistHelper.createJavaContentAssistant(superInterfaceCompletionProcessor);
		Text cellEditorText = cellEditor.getText();
		// ContentAssistHandler.createHandlerForText(cellEditorText,
		// contentAssistant);
		TextFieldNavigationHandler.install(cellEditorText);
		// cellEditor.setContentAssistant(contentAssistant);

		tableViewer.setCellEditors(new CellEditor[] { cellEditor });
		tableViewer.setCellModifier(new ICellModifier() {
			public void modify(Object element, String property, Object value) {
				if (element instanceof Item)
					element = ((Item) element).getData();

				((MethodParameterWrapper) element).parameterType = (String) value;
				fMethodParametersDialogField
						.elementChanged((MethodParameterWrapper) element);
			}

			public Object getValue(Object element, String property) {
				return ((MethodParameterWrapper) element).parameterType;
			}

			public boolean canModify(Object element, String property) {
				return true;
			}
		});
		tableViewer.getTable().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.keyCode == SWT.F2 && event.stateMask == 0) {
					ISelection selection = tableViewer.getSelection();
					if (!(selection instanceof IStructuredSelection))
						return;
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					tableViewer.editElement(
							structuredSelection.getFirstElement(), 0);
				}
			}
		});
		GridData gd = (GridData) fMethodParametersDialogField.getListControl(
				null).getLayoutData();
		if (fTypeKind == CLASS_TYPE) {
			gd.heightHint = convertHeightInCharsToPixels(3);
		} else {
			gd.heightHint = convertHeightInCharsToPixels(6);
		}
		gd.grabExcessVerticalSpace = false;
		gd.widthHint = getMaxFieldWidth();
	}
	
	protected void createFieldTypeControls(Composite composite, int nColumns) {
		
		fFieldTypeField.doFillIntoGrid(composite, nColumns);
		Text text = fFieldTypeField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		LayoutUtil.setHorizontalGrabbing(text);

		JavaTypeCompletionProcessor superClassCompletionProcessor = new JavaTypeCompletionProcessor(
				false, false, true);
		superClassCompletionProcessor
				.setCompletionContextRequestor(new CompletionContextRequestor() {
					@Override
					public StubTypeContext getStubTypeContext() {
						return getSuperClassStubTypeContext();
					}
				});

		// ControlContentAssistHelper.createTextContentAssistant(text,
		// superClassCompletionProcessor);
		TextFieldNavigationHandler.install(text);
	}
	
	protected void createFieldNameControls(Composite composite, int nColumns) {
		fFieldNameField.doFillIntoGrid(composite, nColumns - 1);
		DialogField.createEmptySpace(composite);

		Text text= fFieldNameField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		TextFieldNavigationHandler.install(text);
	}

	/**
	 * Creates the controls for the modifiers radio/checkbox buttons. Expects a
	 * <code>GridLayout</code> with at least 3 columns.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 */
	protected void createModifierControls(Composite composite, int nColumns) {
		LayoutUtil.setHorizontalSpan(fAccMdfButtons.getLabelControl(composite),
				1);

		Control control = fAccMdfButtons.getSelectionButtonsGroup(composite);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = nColumns - 2;
		control.setLayoutData(gd);

		DialogField.createEmptySpace(composite);

		if (fTypeKind == CLASS_TYPE) {
			DialogField.createEmptySpace(composite);

			control = fOtherMdfButtons.getSelectionButtonsGroup(composite);
			gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gd.horizontalSpan = nColumns - 2;
			control.setLayoutData(gd);

			DialogField.createEmptySpace(composite);
		}
	}

	/**
	 * Creates the controls for the superclass name field. Expects a
	 * <code>GridLayout</code> with at least 3 columns.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 */
	protected void createSuperInterfacesControls(Composite composite,
			int nColumns) {
		final String INTERFACE = "interface"; //$NON-NLS-1$
		fSuperInterfacesDialogField.doFillIntoGrid(composite, nColumns);
		final TableViewer tableViewer = fSuperInterfacesDialogField
				.getTableViewer();
		tableViewer.setColumnProperties(new String[] { INTERFACE });

		TableTextCellEditor cellEditor = new TableTextCellEditor(tableViewer, 0) {
			@Override
			protected void doSetFocus() {
				if (text != null) {
					text.setFocus();
					text.setSelection(text.getText().length());
					checkSelection();
					checkDeleteable();
					checkSelectable();
				}
			}
		};
		JavaTypeCompletionProcessor superInterfaceCompletionProcessor = new JavaTypeCompletionProcessor(
				false, false, true);
		superInterfaceCompletionProcessor
				.setCompletionContextRequestor(new CompletionContextRequestor() {
					@Override
					public StubTypeContext getStubTypeContext() {
						return getSuperInterfacesStubTypeContext();
					}
				});
		// SubjectControlContentAssistant contentAssistant=
		// ControlContentAssistHelper.createJavaContentAssistant(superInterfaceCompletionProcessor);
		Text cellEditorText = cellEditor.getText();
		// ContentAssistHandler.createHandlerForText(cellEditorText,
		// contentAssistant);
		TextFieldNavigationHandler.install(cellEditorText);
		// cellEditor.setContentAssistant(contentAssistant);

		tableViewer.setCellEditors(new CellEditor[] { cellEditor });
		tableViewer.setCellModifier(new ICellModifier() {
			public void modify(Object element, String property, Object value) {
				if (element instanceof Item)
					element = ((Item) element).getData();

				((InterfaceWrapper) element).interfaceName = (String) value;
				fSuperInterfacesDialogField
						.elementChanged((InterfaceWrapper) element);
			}

			public Object getValue(Object element, String property) {
				return ((InterfaceWrapper) element).interfaceName;
			}

			public boolean canModify(Object element, String property) {
				return true;
			}
		});
		tableViewer.getTable().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.keyCode == SWT.F2 && event.stateMask == 0) {
					ISelection selection = tableViewer.getSelection();
					if (!(selection instanceof IStructuredSelection))
						return;
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					tableViewer.editElement(
							structuredSelection.getFirstElement(), 0);
				}
			}
		});
		GridData gd = (GridData) fSuperInterfacesDialogField.getListControl(
				null).getLayoutData();
		if (fTypeKind == CLASS_TYPE) {
			gd.heightHint = convertHeightInCharsToPixels(3);
		} else {
			gd.heightHint = convertHeightInCharsToPixels(6);
		}
		gd.grabExcessVerticalSpace = false;
		gd.widthHint = getMaxFieldWidth();
	}

	/**
	 * Creates the controls for the preference page links. Expects a
	 * <code>GridLayout</code> with at least 3 columns.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 * 
	 * @since 3.1
	 */
	protected void createCommentControls(Composite composite, int nColumns) {
		Link link = new Link(composite, SWT.NONE);
		link.setText(NewWizardMessages.NewTypeWizardPage_addcomment_description);
		link.addSelectionListener(new TypeFieldsAdapter());
		link.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false,
				false, nColumns, 1));
		DialogField.createEmptySpace(composite);
		fAddCommentButton.doFillIntoGrid(composite, nColumns - 1);
	}

	/**
	 * Sets the focus on the type name input field.
	 */
	protected void setFocus() {
		if (fTypeNameDialogField.isEnabled()) {
			fTypeNameDialogField.setFocus();
		} else {
			setFocusOnContainer();
		}
	}

	// -------- TypeFieldsAdapter --------

	private class TypeFieldsAdapter2 implements IStringButtonAdapter,
			IDialogFieldListener, IListAdapter<MethodParameterWrapper>,
			SelectionListener {
		// -------- IStringButtonAdapter
		public void changeControlPressed(DialogField field) {
			typePageChangeControlPressed(field);
		}

		// -------- IListAdapter
		public void customButtonPressed(
				ListDialogField<MethodParameterWrapper> field, int index) {
			typePageCustomButtonPressed(field, index);
		}

		public void selectionChanged(
				ListDialogField<MethodParameterWrapper> field) {
			
			System.out.println();
		}

		public void doubleClicked(ListDialogField<MethodParameterWrapper> field) {
			System.out.println();
		}

		// -------- IDialogFieldListener
		public void dialogFieldChanged(DialogField field) {
			typePageDialogFieldChanged(field);
		}

		public void widgetSelected(SelectionEvent e) {
			typePageLinkActivated();
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			typePageLinkActivated();
		}
	}

	private class TypeFieldsAdapter implements IStringButtonAdapter,
			IDialogFieldListener, IListAdapter<InterfaceWrapper>,
			SelectionListener {

		// -------- IStringButtonAdapter
		public void changeControlPressed(DialogField field) {
			typePageChangeControlPressed(field);
		}

		// -------- IListAdapter
		public void customButtonPressed(
				ListDialogField<InterfaceWrapper> field, int index) {
			typePageCustomButtonPressed(field, index);
		}

		public void selectionChanged(ListDialogField<InterfaceWrapper> field) {
		}

		public void doubleClicked(ListDialogField<InterfaceWrapper> field) {
		}

		// -------- IDialogFieldListener
		public void dialogFieldChanged(DialogField field) {
			typePageDialogFieldChanged(field);
		}

		public void widgetSelected(SelectionEvent e) {
			typePageLinkActivated();
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			typePageLinkActivated();
		}

	}

	private void typePageLinkActivated() {
		IJavaProject project = getJavaProject();
		if (project != null) {
			PreferenceDialog dialog = PreferencesUtil.createPropertyDialogOn(
					getShell(), project.getProject(),
					CodeTemplatePreferencePage.PROP_ID, null, null);
			dialog.open();
		} else {
			String title = NewWizardMessages.NewTypeWizardPage_configure_templates_title;
			String message = NewWizardMessages.NewTypeWizardPage_configure_templates_message;
			MessageDialog.openInformation(getShell(), title, message);
		}
	}

	private void typePageChangeControlPressed(DialogField field) {
		if (field == fPackageDialogField) {
			IPackageFragment pack = choosePackage();
			if (pack != null) {
				fPackageDialogField.setText(pack.getElementName());
			}
		} else if (field == fEnclosingTypeDialogField) {
			IType type = chooseEnclosingType();
			if (type != null) {
				fEnclosingTypeDialogField.setText(type
						.getFullyQualifiedName('.'));
			}
		} else if (field == fSuperClassDialogField) {
			IType type = chooseSuperClass();
			if (type != null) {
				fSuperClassDialogField.setText(SuperInterfaceSelectionDialog
						.getNameWithTypeParameters(type));
			}
		} else if(field == fMethodReturnTypeField){
			IType type = chooseSuperClass();
			if (type != null) {
				fMethodReturnTypeField.setText(SuperInterfaceSelectionDialog
						.getNameWithTypeParameters(type));
			}
		} else if (field == fFieldTypeField){
			IType type = chooseSuperClass();
			if (type != null) {
				fFieldTypeField.setText(SuperInterfaceSelectionDialog
						.getNameWithTypeParameters(type));
			}
		}
	}

	private void typePageCustomButtonPressed(DialogField field, int index) {
		if (field == fSuperInterfacesDialogField && index == 0) {
			chooseSuperInterfaces();
			List<InterfaceWrapper> interfaces= fSuperInterfacesDialogField.getElements();
			if (!interfaces.isEmpty()) {
				Object element= interfaces.get(interfaces.size() - 1);
				fSuperInterfacesDialogField.editElement(element);
			}
		}
		else if(field == fMethodParametersDialogField && index == 0){
			chooseParameterType();
			List<MethodParameterWrapper> parameters= fMethodParametersDialogField.getElements();
			if (!parameters.isEmpty()) {
				Object element= parameters.get(parameters.size() - 1);
				fMethodParametersDialogField.editElement(element);
			}
		}
	}

	/*
	 * A field on the type has changed. The fields' status and all dependent
	 * status are updated.
	 */
	private void typePageDialogFieldChanged(DialogField field) {
		String fieldName = null;
		if (field == fPackageDialogField) {
			fPackageStatus = packageChanged();
			updatePackageStatusLabel();
			fTypeNameStatus = typeNameChanged();
			fSuperClassStatus = superClassChanged();
			fieldName = PACKAGE;
		} else if (field == fEnclosingTypeDialogField) {
			fEnclosingTypeStatus = enclosingTypeChanged();
			fTypeNameStatus = typeNameChanged();
			fSuperClassStatus = superClassChanged();
			fieldName = ENCLOSING;
		} else if (field == fEnclosingTypeSelection) {
			updateEnableState();
			boolean isEnclosedType = isEnclosingTypeSelected();
			if (!isEnclosedType) {
				if (fAccMdfButtons.isSelected(PRIVATE_INDEX)
						|| fAccMdfButtons.isSelected(PROTECTED_INDEX)) {
					fAccMdfButtons.setSelection(PRIVATE_INDEX, false);
					fAccMdfButtons.setSelection(PROTECTED_INDEX, false);
					fAccMdfButtons.setSelection(PUBLIC_INDEX, true);
				}
				if (fOtherMdfButtons.isSelected(STATIC_INDEX)) {
					fOtherMdfButtons.setSelection(STATIC_INDEX, false);
				}
			}
			fAccMdfButtons.enableSelectionButton(PRIVATE_INDEX, isEnclosedType);
			fAccMdfButtons.enableSelectionButton(PROTECTED_INDEX,
					isEnclosedType);
			fOtherMdfButtons
					.enableSelectionButton(STATIC_INDEX, isEnclosedType);
			fTypeNameStatus = typeNameChanged();
			fSuperClassStatus = superClassChanged();
			fieldName = ENCLOSINGSELECTION;
		} else if (field == fTypeNameDialogField) {
			fTypeNameStatus = typeNameChanged();
			fieldName = TYPENAME;
		} else if (field == fSuperClassDialogField) {
			fSuperClassStatus = superClassChanged();
			fieldName = SUPER;
		} else if (field == fSuperInterfacesDialogField) {
			fSuperInterfacesStatus = superInterfacesChanged();
			fieldName = INTERFACES;
		} else if (field == fOtherMdfButtons || field == fAccMdfButtons) {
			fModifierStatus = modifiersChanged();
			fieldName = MODIFIERS;
		} else if (field == fMethodReturnTypeField || field == fMethodNameField || field == fMethodParametersDialogField) {
			fieldName = METHODS;
		} else if (field == fFieldNameField || field == fFieldTypeField){
			fieldName = FIELDS;
		}
		// tell all others
		handleFieldChanged(fieldName);
	}

	// -------- update message ----------------

	/*
	 * @see
	 * org.eclipse.jdt.ui.wizards.NewContainerWizardPage#handleFieldChanged(
	 * String)
	 */
	@Override
	protected void handleFieldChanged(String fieldName) {
		super.handleFieldChanged(fieldName);
		if (fieldName == CONTAINER) {
			fPackageStatus = packageChanged();
			fEnclosingTypeStatus = enclosingTypeChanged();
			fTypeNameStatus = typeNameChanged();
			fSuperClassStatus = superClassChanged();
			fSuperInterfacesStatus = superInterfacesChanged();
			fMethodReturnTypeStatus = methodReturnTypeChanged();
			fMethodNameStatus = methodNameChanged();
			fMethodParametersStatus = methodParametersChanged();
		}
	}

	// ---- set / get ----------------
	/**
	 * Returns the text of the package input field.
	 * 
	 * @return the text of the package input field
	 */
	public String getPackageText() {
		return fPackageDialogField.getText();
	}

	/**
	 * Returns the text of the enclosing type input field.
	 * 
	 * @return the text of the enclosing type input field
	 */
	public String getEnclosingTypeText() {
		return fEnclosingTypeDialogField.getText();
	}

	/**
	 * Returns the package fragment corresponding to the current input.
	 * 
	 * @return a package fragment or <code>null</code> if the input could not be
	 *         resolved.
	 */
	public IPackageFragment getPackageFragment() {
		if (!isEnclosingTypeSelected()) {
			return fCurrPackage;
		} else {
			if (fCurrEnclosingType != null) {
				return fCurrEnclosingType.getPackageFragment();
			}
		}
		return null;
	}

	/**
	 * Sets the package fragment to the given value. The method updates the
	 * model and the text of the control.
	 * 
	 * @param pack
	 *            the package fragment to be set
	 * @param canBeModified
	 *            if <code>true</code> the package fragment is editable;
	 *            otherwise it is read-only.
	 */
	public void setPackageFragment(IPackageFragment pack, boolean canBeModified) {
		fCurrPackage = pack;
		fCanModifyPackage = canBeModified;
		String str = (pack == null) ? "" : pack.getElementName(); //$NON-NLS-1$
		fPackageDialogField.setText(str);
		updateEnableState();
	}

	/**
	 * Returns the enclosing type corresponding to the current input.
	 * 
	 * @return the enclosing type or <code>null</code> if the enclosing type is
	 *         not selected or the input could not be resolved
	 */
	public IType getEnclosingType() {
		if (isEnclosingTypeSelected()) {
			return fCurrEnclosingType;
		}
		return null;
	}

	/**
	 * Sets the enclosing type. The method updates the underlying model and the
	 * text of the control.
	 * 
	 * @param type
	 *            the enclosing type
	 * @param canBeModified
	 *            if <code>true</code> the enclosing type field is editable;
	 *            otherwise it is read-only.
	 */
	public void setEnclosingType(IType type, boolean canBeModified) {
		fCurrEnclosingType = type;
		fCanModifyEnclosingType = canBeModified;
		String str = (type == null) ? "" : type.getFullyQualifiedName('.'); //$NON-NLS-1$
		fEnclosingTypeDialogField.setText(str);
		updateEnableState();
	}

	/**
	 * Returns the selection state of the enclosing type checkbox.
	 * 
	 * @return the selection state of the enclosing type checkbox
	 */
	public boolean isEnclosingTypeSelected() {
		return fEnclosingTypeSelection.isSelected();
	}

	/**
	 * Sets the enclosing type checkbox's selection state.
	 * 
	 * @param isSelected
	 *            the checkbox's selection state
	 * @param canBeModified
	 *            if <code>true</code> the enclosing type checkbox is
	 *            modifiable; otherwise it is read-only.
	 */
	public void setEnclosingTypeSelection(boolean isSelected,
			boolean canBeModified) {
		fEnclosingTypeSelection.setSelection(isSelected);
		fEnclosingTypeSelection.setEnabled(canBeModified);
		updateEnableState();
	}

	/**
	 * Returns the type name entered into the type input field.
	 * 
	 * @return the type name
	 */
	public String getTypeName() {
		return fTypeNameDialogField.getText();
	}

	/**
	 * Sets the type name input field's text to the given value. Method doesn't
	 * update the model.
	 * 
	 * @param name
	 *            the new type name
	 * @param canBeModified
	 *            if <code>true</code> the type name field is editable;
	 *            otherwise it is read-only.
	 */
	public void setTypeName(String name, boolean canBeModified) {
		fTypeNameDialogField.setText(name);
		fTypeNameDialogField.setEnabled(canBeModified);
	}
	
	public String getMethodName(){
		return fMethodNameField.getText();
	}

	public void setMethodName(String name, boolean canBeModified){
		fMethodNameField.setText(name);
		fMethodNameField.setEnabled(canBeModified);
	}
	/**
	 * Returns the selected modifiers.
	 * 
	 * @return the selected modifiers
	 * @see Flags
	 */
	public int getModifiers() {
		int mdf = 0;
		if (fAccMdfButtons.isSelected(PUBLIC_INDEX)) {
			mdf += F_PUBLIC;
		} else if (fAccMdfButtons.isSelected(PRIVATE_INDEX)) {
			mdf += F_PRIVATE;
		} else if (fAccMdfButtons.isSelected(PROTECTED_INDEX)) {
			mdf += F_PROTECTED;
		}
		if (fOtherMdfButtons.isSelected(ABSTRACT_INDEX)) {
			mdf += F_ABSTRACT;
		}
		if (fOtherMdfButtons.isSelected(FINAL_INDEX)) {
			mdf += F_FINAL;
		}
		if (fOtherMdfButtons.isSelected(STATIC_INDEX)) {
			mdf += F_STATIC;
		}
		return mdf;
	}

	/**
	 * Sets the modifiers.
	 * 
	 * @param modifiers
	 *            <code>F_PUBLIC</code>, <code>F_PRIVATE</code>,
	 *            <code>F_PROTECTED</code>, <code>F_ABSTRACT</code>,
	 *            <code>F_FINAL</code> or <code>F_STATIC</code> or a valid
	 *            combination.
	 * @param canBeModified
	 *            if <code>true</code> the modifier fields are editable;
	 *            otherwise they are read-only
	 * @see Flags
	 */
	public void setModifiers(int modifiers, boolean canBeModified) {
		if (Flags.isPublic(modifiers)) {
			fAccMdfButtons.setSelection(PUBLIC_INDEX, true);
		} else if (Flags.isPrivate(modifiers)) {
			fAccMdfButtons.setSelection(PRIVATE_INDEX, true);
		} else if (Flags.isProtected(modifiers)) {
			fAccMdfButtons.setSelection(PROTECTED_INDEX, true);
		} else {
			fAccMdfButtons.setSelection(DEFAULT_INDEX, true);
		}
		if (Flags.isAbstract(modifiers)) {
			fOtherMdfButtons.setSelection(ABSTRACT_INDEX, true);
		}
		if (Flags.isFinal(modifiers)) {
			fOtherMdfButtons.setSelection(FINAL_INDEX, true);
		}
		if (Flags.isStatic(modifiers)) {
			fOtherMdfButtons.setSelection(STATIC_INDEX, true);
		}

		fAccMdfButtons.setEnabled(canBeModified);
		fOtherMdfButtons.setEnabled(canBeModified);
	}

	/**
	 * Returns the content of the superclass input field.
	 * 
	 * @return the superclass name
	 */
	public String getSuperClass() {
		return fSuperClassDialogField.getText();
	}

	/**
	 * Sets the super class name.
	 * 
	 * @param name
	 *            the new superclass name
	 * @param canBeModified
	 *            if <code>true</code> the superclass name field is editable;
	 *            otherwise it is read-only.
	 */
	public void setSuperClass(String name, boolean canBeModified) {
		fSuperClassDialogField.setText(name);
		fSuperClassDialogField.setEnabled(canBeModified);
	}
	
	public String getMethodReturnType(){
		return fMethodReturnTypeField.getText();
	}
	
	public void setMethodReturnType(String name, boolean canBeModified){
		fMethodReturnTypeField.setText(name);
		fMethodReturnTypeField.setEnabled(canBeModified);
	}
	
	public String getFieldType(){
		return fFieldTypeField.getText();
	}
	
	public void setFieldType(String name, boolean canBeModified){
		fFieldTypeField.setText(name);
		fFieldTypeField.setEnabled(canBeModified);
	}
	
	public String getFieldName(){
		return fFieldNameField.getText();
	}
	
	public void setFieldName(String name, boolean canBeModified){
		fFieldNameField.setText(name);
		fFieldNameField.setEnabled(canBeModified);
	}

	/**
	 * Returns the chosen super interfaces.
	 * 
	 * @return a list of chosen super interfaces. The list's elements are of
	 *         type <code>String</code>
	 */
	public List<String> getSuperInterfaces() {
		List<InterfaceWrapper> interfaces = fSuperInterfacesDialogField
				.getElements();
		ArrayList<String> result = new ArrayList<String>(interfaces.size());
		for (Iterator<InterfaceWrapper> iter = interfaces.iterator(); iter
				.hasNext();) {
			InterfaceWrapper wrapper = iter.next();
			result.add(wrapper.interfaceName);
		}
		return result;
	}

	/**
	 * Sets the super interfaces.
	 * 
	 * @param interfacesNames
	 *            a list of super interface. The method requires that the list's
	 *            elements are of type <code>String</code>
	 * @param canBeModified
	 *            if <code>true</code> the super interface field is editable;
	 *            otherwise it is read-only.
	 */
	public void setSuperInterfaces(List<String> interfacesNames,
			boolean canBeModified) {
		ArrayList<InterfaceWrapper> interfaces = new ArrayList<InterfaceWrapper>(
				interfacesNames.size());
		for (Iterator<String> iter = interfacesNames.iterator(); iter.hasNext();) {
			interfaces.add(new InterfaceWrapper(iter.next()));
		}
		fSuperInterfacesDialogField.setElements(interfaces);
		fSuperInterfacesDialogField.setEnabled(canBeModified);
	}
	
	public List<MethodParameterWrapper> getMethodParameters(){
		List<MethodParameterWrapper> parameters = fMethodParametersDialogField.getElements();
		return parameters;
		/*ArrayList<String> result = new ArrayList<String>(parameters.size());
		for (Iterator<MethodParameterWrapper> iter = parameters.iterator(); iter
				.hasNext();) {
			MethodParameterWrapper wrapper = iter.next();
			result.add(wrapper.toString());
		}
		return result;*/
	}
	
	public void setMethodParameters(List<MethodParameterWrapper> parameters,
			boolean canBeModified){
		/*ArrayList<String> parameterTypes = new ArrayList<String>(parameters.size());
		for (MethodParameterWrapper parameter: parameters) {
			//String[] pair = str.split(":");
			parameterTypes.add(parameter.parameterType);
		}*/
		fMethodParametersDialogField.setElements(parameters);
		fMethodParametersDialogField.setEnabled(canBeModified);
	}

	/**
	 * Adds a super interface to the end of the list and selects it if it is not
	 * in the list yet.
	 * 
	 * @param superInterface
	 *            the fully qualified type name of the interface.
	 * @return returns <code>true</code>if the interfaces has been added,
	 *         <code>false</code> if the interface already is in the list.
	 * @since 3.2
	 */
	public boolean addSuperInterface(String superInterface) {
		return fSuperInterfacesDialogField.addElement(new InterfaceWrapper(
				superInterface));
	}
	
	public boolean addMethodParameter(String parameterType, String parameterName) {
		return fMethodParametersDialogField.addElement(new MethodParameterWrapper(parameterType, parameterName));
	}

	/**
	 * Sets 'Add comment' checkbox. The value set will only be used when
	 * creating source when the comment control is enabled (see
	 * {@link #enableCommentControl(boolean)}
	 * 
	 * @param doAddComments
	 *            if <code>true</code>, comments are added.
	 * @param canBeModified
	 *            if <code>true</code> check box is editable; otherwise it is
	 *            read-only.
	 * @since 3.1
	 */
	public void setAddComments(boolean doAddComments, boolean canBeModified) {
		fAddCommentButton.setSelection(doAddComments);
		fAddCommentButton.setEnabled(canBeModified);
	}

	/**
	 * Sets to use the 'Add comment' checkbox value. Clients that use the 'Add
	 * comment' checkbox additionally have to enable the control. This has been
	 * added for backwards compatibility.
	 * 
	 * @param useAddCommentValue
	 *            if <code>true</code>,
	 * @since 3.1
	 */
	public void enableCommentControl(boolean useAddCommentValue) {
		fUseAddCommentButtonValue = useAddCommentValue;
	}

	/**
	 * Returns if comments are added. This method can be overridden by clients.
	 * The selection of the comment control is taken if enabled (see
	 * {@link #enableCommentControl(boolean)}, otherwise the settings as
	 * specified in the preferences is used.
	 * 
	 * @return Returns <code>true</code> if comments can be added
	 * @since 3.1
	 */
	public boolean isAddComments() {
		if (fUseAddCommentButtonValue) {
			return fAddCommentButton.isSelected();
		}
		return StubUtility.doAddComments(getJavaProject());
	}

	/**
	 * Returns the resource handle that corresponds to the compilation unit to
	 * was or will be created or modified.
	 * 
	 * @return A resource or null if the page contains illegal values.
	 * @since 3.0
	 */
	public IResource getModifiedResource() {
		IType enclosing = getEnclosingType();
		if (enclosing != null) {
			return enclosing.getResource();
		}
		IPackageFragment pack = getPackageFragment();
		if (pack != null) {
			String cuName = getCompilationUnitName(getTypeNameWithoutParameters());
			return pack.getCompilationUnit(cuName).getResource();
		}
		return null;
	}

	// ----------- validation ----------

	/*
	 * @see org.eclipse.jdt.ui.wizards.NewContainerWizardPage#containerChanged()
	 */
	@Override
	protected IStatus containerChanged() {
		IStatus status = super.containerChanged();
		IPackageFragmentRoot root = getPackageFragmentRoot();
		if ((fTypeKind == ANNOTATION_TYPE || fTypeKind == ENUM_TYPE)
				&& !status.matches(IStatus.ERROR)) {
			if (root != null
					&& !JavaModelUtil.is50OrHigher(root.getJavaProject())) {
				// error as createType will fail otherwise (bug 96928)
				return new StatusInfo(
						IStatus.ERROR,
						Messages.format(
								NewWizardMessages.NewTypeWizardPage_warning_NotJDKCompliant,
								BasicElementLabels.getJavaElementName(root
										.getJavaProject().getElementName())));
			}
			if (fTypeKind == ENUM_TYPE) {
				try {
					// if findType(...) == null then Enum is unavailable
					if (findType(root.getJavaProject(), "java.lang.Enum") == null) //$NON-NLS-1$
						return new StatusInfo(
								IStatus.WARNING,
								NewWizardMessages.NewTypeWizardPage_warning_EnumClassNotFound);
				} catch (JavaModelException e) {
					JavaPlugin.log(e);
				}
			}
		}

		fCurrPackageCompletionProcessor.setPackageFragmentRoot(root);
		if (root != null) {
			fEnclosingTypeCompletionProcessor.setPackageFragment(root
					.getPackageFragment("")); //$NON-NLS-1$
		}
		return status;
	}

	/**
	 * A hook method that gets called when the package field has changed. The
	 * method validates the package name and returns the status of the
	 * validation. The validation also updates the package fragment model.
	 * <p>
	 * Subclasses may extend this method to perform their own validation.
	 * </p>
	 * 
	 * @return the status of the validation
	 */
	protected IStatus packageChanged() {
		StatusInfo status = new StatusInfo();
		IPackageFragmentRoot root = getPackageFragmentRoot();
		fPackageDialogField.enableButton(root != null);

		IJavaProject project = root != null ? root.getJavaProject() : null;

		String packName = getPackageText();
		if (packName.length() > 0) {
			IStatus val = validatePackageName(packName, project);
			if (val.getSeverity() == IStatus.ERROR) {
				status.setError(Messages
						.format(NewWizardMessages.NewTypeWizardPage_error_InvalidPackageName,
								val.getMessage()));
				return status;
			} else if (val.getSeverity() == IStatus.WARNING) {
				status.setWarning(Messages
						.format(NewWizardMessages.NewTypeWizardPage_warning_DiscouragedPackageName,
								val.getMessage()));
				// continue
			}
		} else {
			status.setWarning(NewWizardMessages.NewTypeWizardPage_warning_DefaultPackageDiscouraged);
		}

		if (project != null) {
			if (project.exists() && packName.length() > 0) {
				try {
					IPath rootPath = root.getPath();
					IPath outputPath = project.getOutputLocation();
					if (rootPath.isPrefixOf(outputPath)
							&& !rootPath.equals(outputPath)) {
						// if the bin folder is inside of our root, don't allow
						// to name a package
						// like the bin folder
						IPath packagePath = rootPath.append(packName.replace(
								'.', '/'));
						if (outputPath.isPrefixOf(packagePath)) {
							status.setError(NewWizardMessages.NewTypeWizardPage_error_ClashOutputLocation);
							return status;
						}
					}
				} catch (JavaModelException e) {
					JavaPlugin.log(e);
					// let pass
				}
			}

			fCurrPackage = root.getPackageFragment(packName);
			IResource resource = fCurrPackage.getResource();
			if (resource != null) {
				if (resource.isVirtual()) {
					status.setError(NewWizardMessages.NewTypeWizardPage_error_PackageIsVirtual);
					return status;
				}
				if (!ResourcesPlugin.getWorkspace().validateFiltered(resource)
						.isOK()) {
					status.setError(NewWizardMessages.NewTypeWizardPage_error_PackageNameFiltered);
					return status;
				}
			}
		} else {
			status.setError(""); //$NON-NLS-1$
		}
		return status;
	}

	/*
	 * Updates the 'default' label next to the package field.
	 */
	private void updatePackageStatusLabel() {
		String packName = getPackageText();

		if (packName.length() == 0) {
			fPackageDialogField
					.setStatus(NewWizardMessages.NewTypeWizardPage_default);
		} else {
			fPackageDialogField.setStatus(""); //$NON-NLS-1$
		}
	}

	/*
	 * Updates the enable state of buttons related to the enclosing type
	 * selection checkbox.
	 */
	private void updateEnableState() {
		boolean enclosing = isEnclosingTypeSelected();
		fPackageDialogField.setEnabled(fCanModifyPackage && !enclosing);
		fEnclosingTypeDialogField.setEnabled(fCanModifyEnclosingType
				&& enclosing);
		if (fTypeKind == ENUM_TYPE || fTypeKind == ANNOTATION_TYPE) {
			fOtherMdfButtons.enableSelectionButton(ABSTRACT_INDEX, enclosing);
			fOtherMdfButtons.enableSelectionButton(ENUM_ANNOT_STATIC_INDEX,
					enclosing);
		}
	}

	/**
	 * Hook method that gets called when the enclosing type name has changed.
	 * The method validates the enclosing type and returns the status of the
	 * validation. It also updates the enclosing type model.
	 * <p>
	 * Subclasses may extend this method to perform their own validation.
	 * </p>
	 * 
	 * @return the status of the validation
	 */
	protected IStatus enclosingTypeChanged() {
		StatusInfo status = new StatusInfo();
		fCurrEnclosingType = null;

		IPackageFragmentRoot root = getPackageFragmentRoot();

		fEnclosingTypeDialogField.enableButton(root != null);
		if (root == null) {
			status.setError(""); //$NON-NLS-1$
			return status;
		}

		String enclName = getEnclosingTypeText();
		if (enclName.length() == 0) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_EnclosingTypeEnterName);
			return status;
		}
		try {
			IType type = findType(root.getJavaProject(), enclName);
			if (type == null) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_EnclosingTypeNotExists);
				return status;
			}

			if (type.getCompilationUnit() == null) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_EnclosingNotInCU);
				return status;
			}
			if (!JavaModelUtil.isEditable(type.getCompilationUnit())) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_EnclosingNotEditable);
				return status;
			}

			fCurrEnclosingType = type;
			IPackageFragmentRoot enclosingRoot = JavaModelUtil
					.getPackageFragmentRoot(type);
			if (!enclosingRoot.equals(root)) {
				status.setWarning(NewWizardMessages.NewTypeWizardPage_warning_EnclosingNotInSourceFolder);
			}
			return status;
		} catch (JavaModelException e) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_EnclosingTypeNotExists);
			JavaPlugin.log(e);
			return status;
		}
	}

	private IType findType(IJavaProject project, String typeName)
			throws JavaModelException {
		if (project.exists()) {
			return project.findType(typeName);
		}
		return null;
	}

	private String getTypeNameWithoutParameters() {
		return getTypeNameWithoutParameters(getTypeName());
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

	/**
	 * Hook method that is called when evaluating the name of the compilation
	 * unit to create. By default, a file extension <code>java</code> is added
	 * to the given type name, but implementors can override this behavior.
	 * 
	 * @param typeName
	 *            the name of the type to create the compilation unit for.
	 * @return the name of the compilation unit to be created for the given name
	 * 
	 * @since 3.2
	 */
	protected String getCompilationUnitName(String typeName) {
		return typeName + JavaModelUtil.DEFAULT_CU_SUFFIX;
	}

	/**
	 * Hook method that gets called when the type name has changed. The method
	 * validates the type name and returns the status of the validation.
	 * <p>
	 * Subclasses may extend this method to perform their own validation.
	 * </p>
	 * 
	 * @return the status of the validation
	 */
	protected IStatus typeNameChanged() {
		StatusInfo status = new StatusInfo();
		fCurrType = null;
		String typeNameWithParameters = getTypeName();
		// must not be empty
		if (typeNameWithParameters.length() == 0) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_EnterTypeName);
			return status;
		}

		String typeName = getTypeNameWithoutParameters();
		if (typeName.indexOf('.') != -1) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_QualifiedName);
			return status;
		}

		IJavaProject project = getJavaProject();
		IStatus val = validateJavaTypeName(typeName, project);
		if (val.getSeverity() == IStatus.ERROR) {
			status.setError(Messages.format(
					NewWizardMessages.NewTypeWizardPage_error_InvalidTypeName,
					val.getMessage()));
			return status;
		} else if (val.getSeverity() == IStatus.WARNING) {
			status.setWarning(Messages
					.format(NewWizardMessages.NewTypeWizardPage_warning_TypeNameDiscouraged,
							val.getMessage()));
			// continue checking
		}

		// must not exist
		/*
		 * if (!isEnclosingTypeSelected()) { IPackageFragment pack=
		 * getPackageFragment(); if (pack != null) { ICompilationUnit cu=
		 * pack.getCompilationUnit(getCompilationUnitName(typeName)); fCurrType=
		 * cu.getType(typeName); IResource resource= cu.getResource();
		 * 
		 * if (resource.exists()) {
		 * status.setError(NewWizardMessages.NewTypeWizardPage_error_TypeNameExists
		 * ); return status; } if
		 * (!ResourcesPlugin.getWorkspace().validateFiltered(resource).isOK()) {
		 * status
		 * .setError(NewWizardMessages.NewTypeWizardPage_error_TypeNameFiltered
		 * ); return status; } URI location= resource.getLocationURI(); if
		 * (location != null) { try { IFileStore store= EFS.getStore(location);
		 * if (store.fetchInfo().exists()) { status.setError(NewWizardMessages.
		 * NewTypeWizardPage_error_TypeNameExistsDifferentCase); return status;
		 * } } catch (CoreException e) { status.setError(Messages.format(
		 * NewWizardMessages.NewTypeWizardPage_error_uri_location_unkown,
		 * BasicElementLabels
		 * .getURLPart(Resources.getLocationString(resource)))); } } } } else {
		 * IType type= getEnclosingType(); if (type != null) { fCurrType=
		 * type.getType(typeName); if (fCurrType.exists()) {
		 * status.setError(NewWizardMessages
		 * .NewTypeWizardPage_error_TypeNameExists); return status; } } }
		 */

		if (!typeNameWithParameters.equals(typeName) && project != null) {
			if (!JavaModelUtil.is50OrHigher(project)) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_TypeParameters);
				return status;
			}
			String typeDeclaration = "class " + typeNameWithParameters + " {}"; //$NON-NLS-1$//$NON-NLS-2$
			ASTParser parser = ASTParser
					.newParser(ASTProvider.SHARED_AST_LEVEL);
			parser.setSource(typeDeclaration.toCharArray());
			parser.setProject(project);
			CompilationUnit compilationUnit = (CompilationUnit) parser
					.createAST(null);
			IProblem[] problems = compilationUnit.getProblems();
			if (problems.length > 0) {
				status.setError(Messages
						.format(NewWizardMessages.NewTypeWizardPage_error_InvalidTypeName,
								problems[0].getMessage()));
				return status;
			}
		}
		return status;
	}

	protected IStatus methodNameChanged() {
		StatusInfo status = new StatusInfo();
		fCurrType = null;
		String typeNameWithParameters = getMethodName();
		// must not be empty
		if (typeNameWithParameters.length() == 0) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_EnterTypeName);
			return status;
		}

		String typeName = getMethodName();
		if (typeName.indexOf('.') != -1) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_QualifiedName);
			return status;
		}

		IJavaProject project = getJavaProject();
		IStatus val = validateMethodName(typeName, project);
		if (val.getSeverity() == IStatus.ERROR) {
			status.setError(Messages.format(
					NewWizardMessages.NewTypeWizardPage_error_InvalidTypeName,
					val.getMessage()));
			return status;
		} else if (val.getSeverity() == IStatus.WARNING) {
			status.setWarning(Messages
					.format(NewWizardMessages.NewTypeWizardPage_warning_TypeNameDiscouraged,
							val.getMessage()));
			// continue checking
		}

		// must not exist
		/*
		 * if (!isEnclosingTypeSelected()) { IPackageFragment pack=
		 * getPackageFragment(); if (pack != null) { ICompilationUnit cu=
		 * pack.getCompilationUnit(getCompilationUnitName(typeName)); fCurrType=
		 * cu.getType(typeName); IResource resource= cu.getResource();
		 * 
		 * if (resource.exists()) {
		 * status.setError(NewWizardMessages.NewTypeWizardPage_error_TypeNameExists
		 * ); return status; } if
		 * (!ResourcesPlugin.getWorkspace().validateFiltered(resource).isOK()) {
		 * status
		 * .setError(NewWizardMessages.NewTypeWizardPage_error_TypeNameFiltered
		 * ); return status; } URI location= resource.getLocationURI(); if
		 * (location != null) { try { IFileStore store= EFS.getStore(location);
		 * if (store.fetchInfo().exists()) { status.setError(NewWizardMessages.
		 * NewTypeWizardPage_error_TypeNameExistsDifferentCase); return status;
		 * } } catch (CoreException e) { status.setError(Messages.format(
		 * NewWizardMessages.NewTypeWizardPage_error_uri_location_unkown,
		 * BasicElementLabels
		 * .getURLPart(Resources.getLocationString(resource)))); } } } } else {
		 * IType type= getEnclosingType(); if (type != null) { fCurrType=
		 * type.getType(typeName); if (fCurrType.exists()) {
		 * status.setError(NewWizardMessages
		 * .NewTypeWizardPage_error_TypeNameExists); return status; } } }
		 */

		if (!typeNameWithParameters.equals(typeName) && project != null) {
			if (!JavaModelUtil.is50OrHigher(project)) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_TypeParameters);
				return status;
			}
			String typeDeclaration = "class " + typeNameWithParameters + " {}"; //$NON-NLS-1$//$NON-NLS-2$
			ASTParser parser = ASTParser
					.newParser(ASTProvider.SHARED_AST_LEVEL);
			parser.setSource(typeDeclaration.toCharArray());
			parser.setProject(project);
			CompilationUnit compilationUnit = (CompilationUnit) parser
					.createAST(null);
			IProblem[] problems = compilationUnit.getProblems();
			if (problems.length > 0) {
				status.setError(Messages
						.format(NewWizardMessages.NewTypeWizardPage_error_InvalidTypeName,
								problems[0].getMessage()));
				return status;
			}
		}
		return status;
	}
	
	protected IStatus fieldNameChanged() {
		StatusInfo status = new StatusInfo();
		fCurrType = null;
		String typeNameWithParameters = getFieldName();
		// must not be empty
		if (typeNameWithParameters.length() == 0) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_EnterTypeName);
			return status;
		}

		String typeName = getFieldName();
		if (typeName.indexOf('.') != -1) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_QualifiedName);
			return status;
		}

		IJavaProject project = getJavaProject();
		IStatus val = validateMethodName(typeName, project);
		if (val.getSeverity() == IStatus.ERROR) {
			status.setError(Messages.format(
					NewWizardMessages.NewTypeWizardPage_error_InvalidTypeName,
					val.getMessage()));
			return status;
		} else if (val.getSeverity() == IStatus.WARNING) {
			status.setWarning(Messages
					.format(NewWizardMessages.NewTypeWizardPage_warning_TypeNameDiscouraged,
							val.getMessage()));
			// continue checking
		}

		// must not exist
		/*
		 * if (!isEnclosingTypeSelected()) { IPackageFragment pack=
		 * getPackageFragment(); if (pack != null) { ICompilationUnit cu=
		 * pack.getCompilationUnit(getCompilationUnitName(typeName)); fCurrType=
		 * cu.getType(typeName); IResource resource= cu.getResource();
		 * 
		 * if (resource.exists()) {
		 * status.setError(NewWizardMessages.NewTypeWizardPage_error_TypeNameExists
		 * ); return status; } if
		 * (!ResourcesPlugin.getWorkspace().validateFiltered(resource).isOK()) {
		 * status
		 * .setError(NewWizardMessages.NewTypeWizardPage_error_TypeNameFiltered
		 * ); return status; } URI location= resource.getLocationURI(); if
		 * (location != null) { try { IFileStore store= EFS.getStore(location);
		 * if (store.fetchInfo().exists()) { status.setError(NewWizardMessages.
		 * NewTypeWizardPage_error_TypeNameExistsDifferentCase); return status;
		 * } } catch (CoreException e) { status.setError(Messages.format(
		 * NewWizardMessages.NewTypeWizardPage_error_uri_location_unkown,
		 * BasicElementLabels
		 * .getURLPart(Resources.getLocationString(resource)))); } } } } else {
		 * IType type= getEnclosingType(); if (type != null) { fCurrType=
		 * type.getType(typeName); if (fCurrType.exists()) {
		 * status.setError(NewWizardMessages
		 * .NewTypeWizardPage_error_TypeNameExists); return status; } } }
		 */

		if (!typeNameWithParameters.equals(typeName) && project != null) {
			if (!JavaModelUtil.is50OrHigher(project)) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_TypeParameters);
				return status;
			}
			String typeDeclaration = "class " + typeNameWithParameters + " {}"; //$NON-NLS-1$//$NON-NLS-2$
			ASTParser parser = ASTParser
					.newParser(ASTProvider.SHARED_AST_LEVEL);
			parser.setSource(typeDeclaration.toCharArray());
			parser.setProject(project);
			CompilationUnit compilationUnit = (CompilationUnit) parser
					.createAST(null);
			IProblem[] problems = compilationUnit.getProblems();
			if (problems.length > 0) {
				status.setError(Messages
						.format(NewWizardMessages.NewTypeWizardPage_error_InvalidTypeName,
								problems[0].getMessage()));
				return status;
			}
		}
		return status;
	}

	protected IStatus methodReturnTypeChanged() {
		StatusInfo status = new StatusInfo();
		IPackageFragmentRoot root = getPackageFragmentRoot();
		fMethodReturnTypeField.enableButton(root != null);

		fSuperClassStubTypeContext = null;

		String returnTypeName = getMethodReturnType();
		if (returnTypeName.length() == 0 || returnTypeName.equals("void")) {
			// accept the empty field (stands for java.lang.Object)
			return status;
		}

		if (root != null) {
			Type type = TypeContextChecker.parseSuperClass(returnTypeName);
			if (type == null) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_InvalidSuperClassName);
				return status;
			}
			if (type instanceof ParameterizedType
					&& !JavaModelUtil.is50OrHigher(root.getJavaProject())) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_SuperClassNotParameterized);
				return status;
			}
		} else {
			status.setError(""); //$NON-NLS-1$
		}
		return status;
	}
	
	protected IStatus fieldTypeChanged() {
		StatusInfo status = new StatusInfo();
		IPackageFragmentRoot root = getPackageFragmentRoot();
		fFieldTypeField.enableButton(root != null);

		fSuperClassStubTypeContext = null;

		String returnTypeName = getMethodReturnType();
		if (returnTypeName.length() == 0 || returnTypeName.equals("void")) {
			// accept the empty field (stands for java.lang.Object)
			return status;
		}

		if (root != null) {
			Type type = TypeContextChecker.parseSuperClass(returnTypeName);
			if (type == null) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_InvalidSuperClassName);
				return status;
			}
			if (type instanceof ParameterizedType
					&& !JavaModelUtil.is50OrHigher(root.getJavaProject())) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_SuperClassNotParameterized);
				return status;
			}
		} else {
			status.setError(""); //$NON-NLS-1$
		}
		return status;
	}

	/**
	 * Hook method that gets called when the superclass name has changed. The
	 * method validates the superclass name and returns the status of the
	 * validation.
	 * <p>
	 * Subclasses may extend this method to perform their own validation.
	 * </p>
	 * 
	 * @return the status of the validation
	 */
	protected IStatus superClassChanged() {
		StatusInfo status = new StatusInfo();
		IPackageFragmentRoot root = getPackageFragmentRoot();
		fSuperClassDialogField.enableButton(root != null);

		fSuperClassStubTypeContext = null;

		String sclassName = getSuperClass();
		if (sclassName.length() == 0) {
			// accept the empty field (stands for java.lang.Object)
			return status;
		}

		if (root != null) {
			Type type = TypeContextChecker.parseSuperClass(sclassName);
			if (type == null) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_InvalidSuperClassName);
				return status;
			}
			if (type instanceof ParameterizedType
					&& !JavaModelUtil.is50OrHigher(root.getJavaProject())) {
				status.setError(NewWizardMessages.NewTypeWizardPage_error_SuperClassNotParameterized);
				return status;
			}
		} else {
			status.setError(""); //$NON-NLS-1$
		}
		return status;
	}

	private StubTypeContext getSuperClassStubTypeContext() {
		if (fSuperClassStubTypeContext == null) {
			String typeName;
			if (fCurrType != null) {
				typeName = getTypeName();
			} else {
				typeName = JavaTypeCompletionProcessor.DUMMY_CLASS_NAME;
			}
			fSuperClassStubTypeContext = TypeContextChecker
					.createSuperClassStubTypeContext(typeName,
							getEnclosingType(), getPackageFragment());
		}
		return fSuperClassStubTypeContext;
	}
	
	
	
	private boolean isPrimitiveType(String type){
		for(String str: primitiveTypes){
			if(type.equals(str)){
				return true;
			}
		}
		
		return false;
	}

	protected IStatus methodParametersChanged() {
		StatusInfo status = new StatusInfo();

		IPackageFragmentRoot root = getPackageFragmentRoot();
		fMethodParametersDialogField.enableButton(0, root != null);

		if (root != null) {
			List<MethodParameterWrapper> elements = fMethodParametersDialogField.getElements();
			int nElements = elements.size();
			for (int i = 0; i < nElements; i++) {
				String paramTypeName = elements.get(i).toString();
				
				paramTypeName = paramTypeName.substring(0, paramTypeName.indexOf(":"));
				
				if(paramTypeName.contains("[]")){
					paramTypeName = paramTypeName.substring(0, paramTypeName.indexOf("["));
				}
				
				if(isPrimitiveType(paramTypeName)){
					return status;
				}
				
				Type type = TypeContextChecker.parseSuperInterface(paramTypeName);
				if (type == null && !isPrimitiveType(paramTypeName)) {
					status.setError(Messages
							.format(NewWizardMessages.NewTypeWizardPage_error_InvalidSuperInterfaceName,
									BasicElementLabels
											.getJavaElementName(paramTypeName)));
					return status;
				}
				if (type instanceof ParameterizedType
						&& !JavaModelUtil.is50OrHigher(root.getJavaProject())) {
					status.setError(Messages
							.format(NewWizardMessages.NewTypeWizardPage_error_SuperInterfaceNotParameterized,
									BasicElementLabels
											.getJavaElementName(paramTypeName)));
					return status;
				}
			}
		}
		return status;
	}

	/**
	 * Hook method that gets called when the list of super interface has
	 * changed. The method validates the super interfaces and returns the status
	 * of the validation.
	 * <p>
	 * Subclasses may extend this method to perform their own validation.
	 * </p>
	 * 
	 * @return the status of the validation
	 */
	protected IStatus superInterfacesChanged() {
		StatusInfo status = new StatusInfo();

		IPackageFragmentRoot root = getPackageFragmentRoot();
		fSuperInterfacesDialogField.enableButton(0, root != null);

		if (root != null) {
			List<InterfaceWrapper> elements = fSuperInterfacesDialogField
					.getElements();
			int nElements = elements.size();
			for (int i = 0; i < nElements; i++) {
				String intfname = elements.get(i).interfaceName;
				Type type = TypeContextChecker.parseSuperInterface(intfname);
				if (type == null) {
					status.setError(Messages
							.format(NewWizardMessages.NewTypeWizardPage_error_InvalidSuperInterfaceName,
									BasicElementLabels
											.getJavaElementName(intfname)));
					return status;
				}
				if (type instanceof ParameterizedType
						&& !JavaModelUtil.is50OrHigher(root.getJavaProject())) {
					status.setError(Messages
							.format(NewWizardMessages.NewTypeWizardPage_error_SuperInterfaceNotParameterized,
									BasicElementLabels
											.getJavaElementName(intfname)));
					return status;
				}
			}
		}
		return status;
	}

	private StubTypeContext getSuperInterfacesStubTypeContext() {
		if (fSuperInterfaceStubTypeContext == null) {
			String typeName;
			if (fCurrType != null) {
				typeName = getTypeName();
			} else {
				typeName = JavaTypeCompletionProcessor.DUMMY_CLASS_NAME;
			}
			fSuperInterfaceStubTypeContext = TypeContextChecker
					.createSuperInterfaceStubTypeContext(typeName,
							getEnclosingType(), getPackageFragment());
		}
		return fSuperInterfaceStubTypeContext;
	}

	/**
	 * Hook method that gets called when the modifiers have changed. The method
	 * validates the modifiers and returns the status of the validation.
	 * <p>
	 * Subclasses may extend this method to perform their own validation.
	 * </p>
	 * 
	 * @return the status of the validation
	 */
	protected IStatus modifiersChanged() {
		StatusInfo status = new StatusInfo();
		int modifiers = getModifiers();
		if (Flags.isFinal(modifiers) && Flags.isAbstract(modifiers)) {
			status.setError(NewWizardMessages.NewTypeWizardPage_error_ModifiersFinalAndAbstract);
		}
		return status;
	}

	// selection dialogs

	/**
	 * Opens a selection dialog that allows to select a package.
	 * 
	 * @return returns the selected package or <code>null</code> if the dialog
	 *         has been canceled. The caller typically sets the result to the
	 *         package input field.
	 *         <p>
	 *         Clients can override this method if they want to offer a
	 *         different dialog.
	 *         </p>
	 * 
	 * @since 3.2
	 */
	protected IPackageFragment choosePackage() {
		IPackageFragmentRoot froot = getPackageFragmentRoot();
		IJavaElement[] packages = null;
		try {
			if (froot != null && froot.exists()) {
				packages = froot.getChildren();
			}
		} catch (JavaModelException e) {
			JavaPlugin.log(e);
		}
		if (packages == null) {
			packages = new IJavaElement[0];
		}

		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				getShell(), new JavaElementLabelProvider(
						JavaElementLabelProvider.SHOW_DEFAULT));
		dialog.setIgnoreCase(false);
		dialog.setTitle(NewWizardMessages.NewTypeWizardPage_ChoosePackageDialog_title);
		dialog.setMessage(NewWizardMessages.NewTypeWizardPage_ChoosePackageDialog_description);
		dialog.setEmptyListMessage(NewWizardMessages.NewTypeWizardPage_ChoosePackageDialog_empty);
		dialog.setElements(packages);
		dialog.setHelpAvailable(false);

		IPackageFragment pack = getPackageFragment();
		if (pack != null) {
			dialog.setInitialSelections(new Object[] { pack });
		}

		if (dialog.open() == Window.OK) {
			return (IPackageFragment) dialog.getFirstResult();
		}
		return null;
	}

	/**
	 * Opens a selection dialog that allows to select an enclosing type.
	 * 
	 * @return returns the selected type or <code>null</code> if the dialog has
	 *         been canceled. The caller typically sets the result to the
	 *         enclosing type input field.
	 *         <p>
	 *         Clients can override this method if they want to offer a
	 *         different dialog.
	 *         </p>
	 * 
	 * @since 3.2
	 */
	protected IType chooseEnclosingType() {
		IPackageFragmentRoot root = getPackageFragmentRoot();
		if (root == null) {
			return null;
		}

		IJavaSearchScope scope = SearchEngine
				.createJavaSearchScope(new IJavaElement[] { root });

		FilteredTypesSelectionDialog dialog = new FilteredTypesSelectionDialog(
				getShell(), false, getWizard().getContainer(), scope,
				IJavaSearchConstants.TYPE);
		dialog.setTitle(NewWizardMessages.NewTypeWizardPage_ChooseEnclosingTypeDialog_title);
		dialog.setMessage(NewWizardMessages.NewTypeWizardPage_ChooseEnclosingTypeDialog_description);
		dialog.setInitialPattern(Signature
				.getSimpleName(getEnclosingTypeText()));

		if (dialog.open() == Window.OK) {
			return (IType) dialog.getFirstResult();
		}
		return null;
	}

	/**
	 * Opens a selection dialog that allows to select a super class.
	 * 
	 * @return returns the selected type or <code>null</code> if the dialog has
	 *         been canceled. The caller typically sets the result to the super
	 *         class input field.
	 *         <p>
	 *         Clients can override this method if they want to offer a
	 *         different dialog.
	 *         </p>
	 * 
	 * @since 3.2
	 */
	protected IType chooseSuperClass() {
		IJavaProject project = getJavaProject();
		if (project == null) {
			return null;
		}

		IJavaElement[] elements = new IJavaElement[] { project };
		IJavaSearchScope scope = SearchEngine.createJavaSearchScope(elements);

		FilteredTypesSelectionDialog dialog = new FilteredTypesSelectionDialog(
				getShell(), false, getWizard().getContainer(), scope,
				IJavaSearchConstants.CLASS);
		dialog.setTitle(NewWizardMessages.NewTypeWizardPage_SuperClassDialog_title);
		dialog.setMessage(NewWizardMessages.NewTypeWizardPage_SuperClassDialog_message);
		dialog.setInitialPattern(getSuperClass());

		if (dialog.open() == Window.OK) {
			return (IType) dialog.getFirstResult();
		}
		return null;
	}
	
	protected void chooseParameterType() {
		
		IJavaProject project = getJavaProject();
		if (project == null) {
			return;
		}

		SuperTypeSelectionDialog dialog = new SuperTypeSelectionDialog(
				getShell(), getWizard().getContainer(), this, project, false);
		dialog.setTitle("Choose Parmeter Type");
		dialog.setMessage("Choose Parmeter Type");
		dialog.open();
		
		/*IJavaProject project = getJavaProject();
		if (project == null) {
			return null;
		}

		IJavaElement[] elements = new IJavaElement[] { project };
		IJavaSearchScope scope = SearchEngine.createJavaSearchScope(elements);

		FilteredTypesSelectionDialog dialog = new FilteredTypesSelectionDialog(
				getShell(), false, getWizard().getContainer(), scope,
				IJavaSearchConstants.CLASS);
		dialog.setTitle(NewWizardMessages.NewTypeWizardPage_SuperClassDialog_title);
		dialog.setMessage(NewWizardMessages.NewTypeWizardPage_SuperClassDialog_message);
		dialog.setInitialPattern(getSuperClass());

		if (dialog.open() == Window.OK) {
			return (IType) dialog.getFirstResult();
		}
		return null;*/
	}

	/**
	 * Opens a selection dialog that allows to select the super interfaces. The
	 * selected interfaces are directly added to the wizard page using
	 * {@link #addSuperInterface(String)}.
	 * 
	 * <p>
	 * Clients can override this method if they want to offer a different
	 * dialog.
	 * </p>
	 * 
	 * @since 3.2
	 */
	protected void chooseSuperInterfaces() {
		IJavaProject project = getJavaProject();
		if (project == null) {
			return;
		}

		SuperTypeSelectionDialog dialog = new SuperTypeSelectionDialog(
				getShell(), getWizard().getContainer(), this, project, true);
		dialog.setTitle(getInterfaceDialogTitle());
		dialog.setMessage(NewWizardMessages.NewTypeWizardPage_InterfacesDialog_message);
		dialog.open();
	}

	private String getInterfaceDialogTitle() {
		if (fTypeKind == INTERFACE_TYPE)
			return NewWizardMessages.NewTypeWizardPage_InterfacesDialog_interface_title;
		return NewWizardMessages.NewTypeWizardPage_InterfacesDialog_class_title;
	}
}
