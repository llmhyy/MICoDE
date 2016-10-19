package clonepedia.templategeneration.diagram.view;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

import clonepedia.Activator;
import clonepedia.util.ImageUI;

public class PropertyView extends ViewPart {

	String codeContent = "        super.init();\n"+
	        "\n" +
	        "        initComponents();\n"+
	        "\n" +
	        "        JPanel zoomButtonPanel = new JPanel(new BorderLayout());\n"+
	        "        scrollPane.setLayout(new PlacardScrollPaneLayout());\n"+
	        "        scrollPane.setBorder(new EmptyBorder(0,0,0,0));\n"+
	        "\n" +
	        "        setEditor(new DefaultDrawingEditor());\n"+
	        "        //TODO\n"+
	        "		view.setDOMFactory(new *Factory());\n"+
	        "        undo = new UndoRedoManager();\n"+
	        "        //TODO\n"+
	        "		view.setDrawing(new *Drawing());\n"+
	        "        view.getDrawing().addUndoableEditListener(undo);\n"+
	        "        initActions();\n"+
	        "        undo.addPropertyChangeListener(new PropertyChangeListener() {\n"+
	        "            public void propertyChange(PropertyChangeEvent evt) {\n"+
	        "                setHasUnsavedChanges(undo.hasSignificantEdits());\n"+
	        "            }\n"+
	        "        });\n"+
	        "\n" +
	        "        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle(\"org.jhotdraw.draw.Labels\");\n"+
	        "\n" +
	        "        JPanel placardPanel = new JPanel(new BorderLayout());\n"+
	        "        javax.swing.AbstractButton pButton;\n"+
	        "        pButton = ToolBarButtonFactory.createZoomButton(view);\n"+
	        "        pButton.putClientProperty(\"Quaqua.Button.style\",\"placard\");\n"+
	        "        pButton.putClientProperty(\"Quaqua.Component.visualMargin\",new Insets(0,0,0,0));\n"+
	        "        pButton.setFont(UIManager.getFont(\"SmallSystemFont\"));\n"+
	        "        placardPanel.add(pButton, BorderLayout.WEST);\n"+
	        "        toggleGridButton = pButton = ToolBarButtonFactory.createToggleGridButton(view);\n"+
	        "        pButton.putClientProperty(\"Quaqua.Button.style\",\"placard\");\n"+
	        "        pButton.putClientProperty(\"Quaqua.Component.visualMargin\",new Insets(0,0,0,0));\n"+
	        "        pButton.setFont(UIManager.getFont(\"SmallSystemFont\"));\n"+
	        "        labels.configureToolBarButton(pButton, \"alignGridSmall\");\n"+
	        "        placardPanel.add(pButton, BorderLayout.EAST);\n"+
	        "        scrollPane.add(placardPanel, JScrollPane.LOWER_LEFT_CORNER);\n"+
	        "\n" +
	        "        prefs = Preferences.userNodeForPackage(getClass());\n"+
	        "        toggleGridButton.setSelected(prefs.getBoolean(\"project.gridVisible\", false));\n"+
	        "        setScaleFactor(prefs.getDouble(\"project.scaleFactor\", 1d));\n"+
	        "\n" +
	        "        view.addPropertyChangeListener(new PropertyChangeListener() {\n"+
	        "            public void propertyChange(PropertyChangeEvent evt) {\n"+
	        "                String name = evt.getPropertyName();\n"+
	        "                if (name.equals(\"constrainer\")) {\n"+
	        "                    prefs.putBoolean(\"project.gridVisible\", ((Constrainer) evt.getNewValue()).isVisible());\n"+
	        "                    firePropertyChange(\"gridVisible\", ((Constrainer) evt.getOldValue()).isVisible(), ((Constrainer) evt.getNewValue()).isVisible());\n"+
	        "                } else if (name.equals(\"scaleFactor\")) {\n"+
	        "                    prefs.putDouble(\"project.scaleFactor\", (Double) evt.getNewValue());\n"+
	        "                    firePropertyChange(\"scaleFactor\", evt.getOldValue(), evt.getNewValue());\n"+
	        "                }\n"+
	        "            }\n"+
	        "        });\n";
	
	private void generateCommentStyle(StyledText text){
		String keyword = "//TODO";
		Color color = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
		int style = SWT.NORMAL;
		
		String codeText = text.getText();
		
		int startPosition = 0;
		int length = 0;
		
		startPosition = codeText.indexOf(keyword);
		
		while(startPosition != -1){
			length = codeText.indexOf("\n", startPosition) - startPosition;
			
			StyleRange styleRange = new StyleRange();
			styleRange.start = startPosition;
			styleRange.length = length;
			styleRange.foreground = color;
			styleRange.fontStyle = style;	
			
			text.setStyleRange(styleRange);

			startPosition = codeText.indexOf(keyword, startPosition+length);
		}
	}
	
	private void generatePlaceholderStyle(StyledText text){
		String keyword = "*";
		Color color = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED);
		int style = SWT.BOLD;
		
		String codeText = text.getText();
		
		int startPosition = 0;
		int length = 0;
		
		startPosition = codeText.indexOf(keyword);
		
		while(startPosition != -1){
			length = 8;
			
			StyleRange styleRange = new StyleRange();
			styleRange.start = startPosition;
			styleRange.length = length;
			styleRange.foreground = color;
			styleRange.fontStyle = style;
			styleRange.underline = true;
			styleRange.underlineStyle = SWT.UNDERLINE_LINK;
			
			text.setStyleRange(styleRange);

			startPosition = codeText.indexOf(keyword, startPosition+length);
		}
	}
	
	private void generateKeywordsStyle(StyledText text){
		String[] keywords = {"package ", "import ", "private ", "public ", "protected ", "class ", "interface ", "new ", 
				"final ", "static ", "int ", "double ", "short ", "long ", "char ", "boolean ", "void ", "instanceof ", 
				"switch", "case", "for(", "for ", "if(", "if ", "else", "while(", "while ", "try{", "try ", "catch(", "catch ", "finally",
				"return", "throw", "throws", "null"};
		Color color = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		int style = SWT.BOLD;
		
		String codeText = text.getText();
		
		int startPosition = 0;
		int length = 0;
		
		for(String keyword: keywords){
			
			startPosition = codeText.indexOf(keyword);
			
			while(startPosition != -1){
				length = keyword.length();
				
				StyleRange styleRange = new StyleRange();
				styleRange.start = startPosition;
				styleRange.length = length;
				styleRange.foreground = color;
				styleRange.fontStyle = style;	
				
				text.setStyleRange(styleRange);

				startPosition = codeText.indexOf(keyword, startPosition+length);
			}
			
		}
	}
	
	public PropertyView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));
		
		Label methodNameLabel = new Label(parent, SWT.NONE);
		methodNameLabel.setText("Name:");
		methodNameLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
				
		Text methodNameText = new Text(parent, SWT.BORDER);
		methodNameText.setText("init");
		methodNameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		
		Label returnTypeLabel = new Label(parent, SWT.NONE);
		returnTypeLabel.setText("Return Type:");
		
		Text returnTypeText = new Text(parent, SWT.BORDER);
		returnTypeText.setText("void");
		returnTypeText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Button returnTypeButton = new Button(parent, SWT.NONE);
		returnTypeButton.setText("Browse");
		
		Label paramLabel = new Label(parent, SWT.NONE);
		paramLabel.setText("Parameters:");
		paramLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1));
		
		Table paramTable = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		tableData.heightHint = 100;
		paramTable.setLayoutData(tableData);
		
		Composite operationsComposite = new Composite(parent, SWT.NONE);
		operationsComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1));
		operationsComposite.setLayout(new GridLayout(1, false));
		
		Button addParamButton = new Button(operationsComposite, SWT.NONE);
		addParamButton.setText("Add");
		addParamButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true, 1, 1));
		Button removeParamButton = new Button(operationsComposite, SWT.NONE);
		removeParamButton.setText("Remove");
		removeParamButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true, 1, 1));
		
		/*Group group = new Group(parent, SWT.NONE);
		group.setText("Concrete Examples");
		group.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 3, 1));
		group.setLayout(new GridLayout(1, false));
		
		Link link1 = new Link(group, SWT.NONE);
	    link1.setText("<a href=\"www.google.com\">DrawView.init(...)</a>");
	    link1.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
		
	    Link link2 = new Link(group, SWT.NONE);
	    link2.setText("<a href=\"www.google.com\">NetView.init(...)</a>");
	    link2.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
		
	    Link link3 = new Link(group, SWT.NONE);
	    link3.setText("<a href=\"www.google.com\">PertView.init(...)</a>");
	    link3.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
		
	    Link link4 = new Link(group, SWT.NONE);
	    link4.setText("<a href=\"www.google.com\">SVGView.init(...)</a>");
	    link4.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
	    
	    Link link5 = new Link(group, SWT.NONE);
	    link5.setText("<a href=\"www.google.com\">ODGView.init(...)</a>");
	    link5.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));*/
		
	    
		Composite methodBodyComposite = new Composite(parent, SWT.NONE);
		methodBodyComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		methodBodyComposite.setLayout(new GridLayout(1, false));
		Label methodBodyLabel = new Label(methodBodyComposite, SWT.NONE);
		methodBodyLabel.setText("Template Method Body:");
		
		GridData scrollCodeLayoutData = new GridData(GridData.FILL_BOTH);
		scrollCodeLayoutData.widthHint = 50;
		scrollCodeLayoutData.heightHint = 50;
		final StyledText text = new StyledText(methodBodyComposite, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		text.setLayoutData(scrollCodeLayoutData);
		text.setText(codeContent);
		generateKeywordsStyle(text);
		generateCommentStyle(text);
		generatePlaceholderStyle(text);
		
		text.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// It is up to the application to determine when and how a link should be activated.
				// In this snippet links are activated on mouse down when the control key is held down 
				//if ((event.stateMask & SWT.MOD1) != 0) {
					try {
						int offset = text.getOffsetAtLocation(new Point (event.x, event.y));
						StyleRange style = text.getStyleRangeAtOffset(offset);
						if (style != null && style.underline && style.underlineStyle == SWT.UNDERLINE_LINK) {
							System.out.println("Click on a Link");
						}
					} catch (IllegalArgumentException e) {
						// no character under event.x, event.y
					}
					
				//}
			}
		});
		
		hookActionsOnToolBar();
	}
	
	private void hookActionsOnToolBar(){
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		
		Action saveAction = new Action("Save"){
			public void run(){
				
			}
		};
		saveAction.setImageDescriptor(Activator.getDefault().getImageRegistry().getDescriptor(ImageUI.DOWN_ARROW));
		toolBar.add(saveAction);
	}

	@Override
	public void setFocus() {
		
	}

}
