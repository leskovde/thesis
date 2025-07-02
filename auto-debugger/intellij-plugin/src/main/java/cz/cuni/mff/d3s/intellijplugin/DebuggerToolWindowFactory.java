package cz.cuni.mff.d3s.intellijplugin;

import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.search.searches.AllClassesSearch;
import com.intellij.util.Query;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.ui.JBUI;
import com.intellij.icons.AllIcons;
import com.intellij.util.textCompletion.TextFieldWithCompletion;
import com.intellij.util.TextFieldCompletionProvider;
import com.intellij.util.ui.FormBuilder;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

final class DebuggerToolWindowFactory implements ToolWindowFactory, DumbAware {
    private static final Logger LOG = Logger.getInstance(DebuggerToolWindowFactory.class);

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        DebuggerToolWindowContent toolWindowContent = new DebuggerToolWindowContent(project, toolWindow);
        Content content = ContentFactory.getInstance().createContent(toolWindowContent.getContentPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private static class DebuggerToolWindowContent {
        private final Project project;
        private final ToolWindow toolWindow;
        private final JPanel contentPanel = new JPanel();

        // UI Components
        private final ComboBox<RunConfiguration> runConfigComboBox = new ComboBox<>();
        private final JButton reloadConfigsButton = new JButton("Reload");
        private final JButton editConfigsButton = new JButton("Edit");
        private final TextFieldWithCompletion targetMethodField;
        private final JBLabel methodValidationLabel = new JBLabel();
        private final JButton runAnalysisButton = new JButton("Run Analysis");
        private final JTextArea outputArea = new JTextArea(10, 40);

        // Additional configuration panels
        private final JPanel fieldsPanel = new JPanel();
        private final JPanel parametersPanel = new JPanel();
        private final JPanel returnValuePanel = new JPanel();
        private final JPanel additionalConfigPanel = new JPanel();

        // Selected method information
        private PsiMethod selectedMethod = null;
        private String selectedMethodSignature = null;

        public DebuggerToolWindowContent(Project project, ToolWindow toolWindow) {
            this.project = project;
            this.toolWindow = toolWindow;

            // Initialize method completion field
            this.targetMethodField = new TextFieldWithCompletion(
                project,
                new MethodCompletionProvider(project),
                "",
                true,
                true,
                true
            );

            initializeUI();
            loadRunConfigurations();
        }

        private void initializeUI() {
            contentPanel.setLayout(new BorderLayout());

            // Create the main form
            JPanel formPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("Run Configuration:", createRunConfigPanel())
                .addLabeledComponent("Target Method:", createMethodSelectionPanel())
                .addComponent(additionalConfigPanel)
                .addComponent(runAnalysisButton)
                .addLabeledComponentFillVertically("Output:", new JBScrollPane(outputArea))
                .getPanel();

            contentPanel.add(formPanel, BorderLayout.CENTER);

            // Initialize additional configuration panels
            initializeAdditionalConfigPanels();

            // Configure components
            outputArea.setEditable(false);
            outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            // Add event listeners
            reloadConfigsButton.addActionListener(this::onReloadConfigurations);
            editConfigsButton.addActionListener(this::onEditConfigurations);
            runAnalysisButton.addActionListener(this::onRunAnalysis);

            // Add combo box listener to update run button state
            runConfigComboBox.addActionListener(e -> updateRunButtonState());

            // Add focus listener to method field for validation
            targetMethodField.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    validateAndUpdateMethod();
                }
            });

            // Initially disable run button
            runAnalysisButton.setEnabled(false);
        }

        private JPanel createRunConfigPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(runConfigComboBox, BorderLayout.CENTER);

            // Create button panel for Reload and Edit buttons
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
            buttonPanel.add(reloadConfigsButton);
            buttonPanel.add(editConfigsButton);
            panel.add(buttonPanel, BorderLayout.EAST);

            return panel;
        }

        private JPanel createMethodSelectionPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(targetMethodField, BorderLayout.CENTER);
            panel.add(methodValidationLabel, BorderLayout.EAST);

            // Configure validation label
            methodValidationLabel.setBorder(JBUI.Borders.emptyLeft(5));

            return panel;
        }

        private void initializeAdditionalConfigPanels() {
            // Set up the main additional config panel
            additionalConfigPanel.setLayout(new BoxLayout(additionalConfigPanel, BoxLayout.Y_AXIS));
            additionalConfigPanel.setBorder(JBUI.Borders.empty(10, 0));

            // Initialize sub-panels
            initializeFieldsPanel();
            initializeParametersPanel();
            initializeReturnValuePanel();

            // Initially hide all additional config panels
            additionalConfigPanel.setVisible(false);
        }

        private void initializeFieldsPanel() {
            fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
            fieldsPanel.setBorder(JBUI.Borders.compound(
                JBUI.Borders.emptyTop(10),
                JBUI.Borders.customLine(JBUI.CurrentTheme.CustomFrameDecorations.separatorForeground(), 1, 0, 0, 0)
            ));

            JBLabel fieldsLabel = new JBLabel("Target Fields:");
            fieldsLabel.setFont(fieldsLabel.getFont().deriveFont(Font.BOLD));
            fieldsPanel.add(fieldsLabel);
            fieldsPanel.add(Box.createVerticalStrut(5));
        }

        private void initializeParametersPanel() {
            parametersPanel.setLayout(new BoxLayout(parametersPanel, BoxLayout.Y_AXIS));
            parametersPanel.setBorder(JBUI.Borders.compound(
                JBUI.Borders.emptyTop(10),
                JBUI.Borders.customLine(JBUI.CurrentTheme.CustomFrameDecorations.separatorForeground(), 1, 0, 0, 0)
            ));

            JBLabel parametersLabel = new JBLabel("Target Parameters:");
            parametersLabel.setFont(parametersLabel.getFont().deriveFont(Font.BOLD));
            parametersPanel.add(parametersLabel);
            parametersPanel.add(Box.createVerticalStrut(5));
        }

        private void initializeReturnValuePanel() {
            returnValuePanel.setLayout(new BoxLayout(returnValuePanel, BoxLayout.Y_AXIS));
            returnValuePanel.setBorder(JBUI.Borders.compound(
                JBUI.Borders.emptyTop(10),
                JBUI.Borders.customLine(JBUI.CurrentTheme.CustomFrameDecorations.separatorForeground(), 1, 0, 0, 0)
            ));

            JBLabel returnLabel = new JBLabel("Return Value:");
            returnLabel.setFont(returnLabel.getFont().deriveFont(Font.BOLD));
            returnValuePanel.add(returnLabel);
            returnValuePanel.add(Box.createVerticalStrut(5));
        }

        private void loadRunConfigurations() {
            RunManager runManager = RunManager.getInstance(project);
            List<RunConfiguration> configurations = runManager.getAllConfigurationsList();

            runConfigComboBox.removeAllItems();
            for (RunConfiguration config : configurations) {
                runConfigComboBox.addItem(config);
            }

            // Enable run button if we have both configuration and method
            updateRunButtonState();
        }

        private void onReloadConfigurations(ActionEvent e) {
            loadRunConfigurations();
        }

        private void onEditConfigurations(ActionEvent e) {
            // Open the IDE's run configuration settings using the standard settings dialog
            ShowSettingsUtil.getInstance().showSettingsDialog(project, "Run/Debug Configurations");

            // Reload configurations after the dialog is closed in case user made changes
            loadRunConfigurations();
        }

        private void validateAndUpdateMethod() {
            String methodText = targetMethodField.getText();
            if (methodText.trim().isEmpty()) {
                clearMethodValidation();
                return;
            }

            // Parse and validate the method signature
            PsiMethod method = findMethodBySignature(methodText.trim());
            if (method != null) {
                setMethodValid(method);
            } else {
                setMethodInvalid();
            }

            updateRunButtonState();
        }

        private void clearMethodValidation() {
            selectedMethod = null;
            methodValidationLabel.setIcon(null);
            methodValidationLabel.setText("");
            hideAdditionalConfig();
        }

        private void setMethodValid(PsiMethod method) {
            selectedMethod = method;
            methodValidationLabel.setIcon(AllIcons.General.InspectionsOK);
            methodValidationLabel.setText("");
            showAdditionalConfig(method);
        }

        private void setMethodInvalid() {
            selectedMethod = null;
            methodValidationLabel.setIcon(AllIcons.General.Error);
            methodValidationLabel.setText("");
            hideAdditionalConfig();
        }

        private void updateRunButtonState() {
            boolean hasConfiguration = runConfigComboBox.getSelectedItem() != null;
            boolean hasValidMethod = selectedMethod != null;
            runAnalysisButton.setEnabled(hasConfiguration && hasValidMethod);
        }

        private PsiMethod findMethodBySignature(String signature) {
            try {
                // Parse the method signature to extract class and method information
                if (!signature.contains(".")) {
                    return null; // Invalid format
                }

                int lastDot = signature.lastIndexOf('.');
                int openParen = signature.indexOf('(', lastDot);

                if (openParen == -1) {
                    return null; // No parameters specified
                }

                String className = signature.substring(0, lastDot);
                String methodName = signature.substring(lastDot + 1, openParen);
                String parametersStr = signature.substring(openParen + 1, signature.lastIndexOf(')'));

                // Find the class using PSI
                JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
                GlobalSearchScope scope = GlobalSearchScope.allScope(project);
                PsiClass psiClass = facade.findClass(className, scope);

                if (psiClass == null) {
                    return null;
                }

                // Find the method with matching name and parameters
                for (PsiMethod method : psiClass.getAllMethods()) {
                    if (method.getName().equals(methodName)) {
                        String methodSignature = buildMethodSignature(method);
                        if (methodSignature.equals(signature)) {
                            return method;
                        }
                    }
                }

                return null;
            } catch (Exception e) {
                LOG.debug("Error parsing method signature: " + signature, e);
                return null;
            }
        }

        private void showAdditionalConfig(PsiMethod method) {
            // Clear existing content
            additionalConfigPanel.removeAll();

            // Add fields configuration if the class has fields
            PsiClass containingClass = method.getContainingClass();
            if (containingClass != null) {
                PsiField[] fields = containingClass.getAllFields();
                if (fields.length > 0) {
                    populateFieldsPanel(fields);
                    additionalConfigPanel.add(fieldsPanel);
                }
            }

            // Add parameters configuration if the method has parameters
            PsiParameter[] parameters = method.getParameterList().getParameters();
            if (parameters.length > 0) {
                populateParametersPanel(parameters);
                additionalConfigPanel.add(parametersPanel);
            }

            // Add return value configuration if the method has a return type
            PsiType returnType = method.getReturnType();
            if (returnType != null && !returnType.equals(PsiType.VOID)) {
                populateReturnValuePanel(returnType);
                additionalConfigPanel.add(returnValuePanel);
            }

            // Show the panel and refresh the UI
            additionalConfigPanel.setVisible(true);
            contentPanel.revalidate();
            contentPanel.repaint();
        }

        private void hideAdditionalConfig() {
            additionalConfigPanel.setVisible(false);
            contentPanel.revalidate();
            contentPanel.repaint();
        }

        private void populateFieldsPanel(PsiField[] fields) {
            // Clear existing content except the label
            fieldsPanel.removeAll();

            JBLabel fieldsLabel = new JBLabel("Target Fields:");
            fieldsLabel.setFont(fieldsLabel.getFont().deriveFont(Font.BOLD));
            fieldsPanel.add(fieldsLabel);
            fieldsPanel.add(Box.createVerticalStrut(5));

            // Create checkboxes for each field
            for (PsiField field : fields) {
                if (shouldShowField(field)) {
                    JBCheckBox fieldCheckBox = new JBCheckBox(getFieldDisplayName(field));
                    fieldCheckBox.setSelected(false); // Default to unselected
                    fieldsPanel.add(fieldCheckBox);
                }
            }
        }

        private void populateParametersPanel(PsiParameter[] parameters) {
            // Clear existing content except the label
            parametersPanel.removeAll();

            JBLabel parametersLabel = new JBLabel("Target Parameters:");
            parametersLabel.setFont(parametersLabel.getFont().deriveFont(Font.BOLD));
            parametersPanel.add(parametersLabel);
            parametersPanel.add(Box.createVerticalStrut(5));

            // Create checkboxes for each parameter
            for (int i = 0; i < parameters.length; i++) {
                PsiParameter parameter = parameters[i];
                String displayName = getParameterDisplayName(parameter, i);
                JBCheckBox paramCheckBox = new JBCheckBox(displayName);
                paramCheckBox.setSelected(true); // Default to selected for parameters
                parametersPanel.add(paramCheckBox);
            }
        }

        private void populateReturnValuePanel(PsiType returnType) {
            // Clear existing content except the label
            returnValuePanel.removeAll();

            JBLabel returnLabel = new JBLabel("Return Value:");
            returnLabel.setFont(returnLabel.getFont().deriveFont(Font.BOLD));
            returnValuePanel.add(returnLabel);
            returnValuePanel.add(Box.createVerticalStrut(5));

            // Create checkbox for return value
            String displayName = "Track return value (" + returnType.getPresentableText() + ")";
            JBCheckBox returnCheckBox = new JBCheckBox(displayName);
            returnCheckBox.setSelected(true); // Default to selected
            returnValuePanel.add(returnCheckBox);
        }

        private boolean shouldShowField(PsiField field) {
            // Show all fields except synthetic ones
            return !field.getName().startsWith("$");
        }

        private String getFieldDisplayName(PsiField field) {
            StringBuilder display = new StringBuilder();

            // Add modifiers
            if (field.hasModifierProperty(PsiModifier.STATIC)) {
                display.append("static ");
            }
            if (field.hasModifierProperty(PsiModifier.FINAL)) {
                display.append("final ");
            }

            // Add type and name
            display.append(field.getType().getPresentableText())
                   .append(" ")
                   .append(field.getName());

            return display.toString();
        }

        private String getParameterDisplayName(PsiParameter parameter, int index) {
            String paramName = parameter.getName();
            if (paramName == null || paramName.isEmpty()) {
                paramName = "param" + index;
            }

            return parameter.getType().getPresentableText() + " " + paramName;
        }

        private String buildMethodSignature(PsiMethod method) {
            StringBuilder signature = new StringBuilder();

            // Add class name
            PsiClass containingClass = method.getContainingClass();
            if (containingClass != null) {
                signature.append(containingClass.getQualifiedName()).append(".");
            }

            // Add method name
            signature.append(method.getName());

            // Add parameters
            signature.append("(");
            PsiParameter[] parameters = method.getParameterList().getParameters();
            for (int i = 0; i < parameters.length; i++) {
                if (i > 0) signature.append(", ");
                signature.append(parameters[i].getType().getPresentableText());
            }
            signature.append(")");

            return signature.toString();
        }

        private void onRunAnalysis(ActionEvent e) {
            RunConfiguration selectedConfig = (RunConfiguration) runConfigComboBox.getSelectedItem();
            String methodSignature = targetMethodField.getText();

            if (selectedConfig == null || methodSignature.trim().isEmpty()) {
                Messages.showErrorDialog(project, "Please select both a run configuration and a target method.", "Missing Selection");
                return;
            }

            // Create auto-debugger run configuration
            cz.cuni.mff.d3s.intellijplugin.RunConfiguration autoDebuggerConfig =
                createAutoDebuggerConfiguration(selectedConfig, methodSignature.trim());

            // Run the analysis
            runAnalysis(autoDebuggerConfig);
        }

        private cz.cuni.mff.d3s.intellijplugin.RunConfiguration createAutoDebuggerConfiguration(
                RunConfiguration intellijConfig, String targetMethodSignature) {

            String configName = "Auto-Debug: " + intellijConfig.getName();
            cz.cuni.mff.d3s.intellijplugin.RunConfiguration config =
                new cz.cuni.mff.d3s.intellijplugin.RunConfiguration(configName);

            config.setDescription("Auto-generated configuration for " + targetMethodSignature);
            config.setTargetMethodReference(targetMethodSignature);

            // TODO: Extract more information from IntelliJ run configuration
            // This would need to be enhanced based on the specific run configuration type

            return config;
        }

        private void runAnalysis(cz.cuni.mff.d3s.intellijplugin.RunConfiguration config) {
            outputArea.setText("Starting analysis...\n");
            runAnalysisButton.setEnabled(false);

            ApplicationManager.getApplication().executeOnPooledThread(() -> {
                try {
                    InstrumentationService service = InstrumentationService.getInstance(project);
                    service.runInstrumentation(config, this::appendOutput)
                        .whenComplete((result, throwable) -> {
                            SwingUtilities.invokeLater(() -> {
                                runAnalysisButton.setEnabled(true);
                                if (throwable != null) {
                                    appendOutput("Analysis failed: " + throwable.getMessage());
                                    LOG.error("Analysis failed", throwable);
                                } else {
                                    appendOutput("Analysis completed successfully!");
                                    if (result != null && !result.isEmpty()) {
                                        appendOutput("Generated files: " + result.stream()
                                            .map(path -> path.toString())
                                            .collect(Collectors.joining(", ")));
                                    }
                                }
                            });
                        });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        runAnalysisButton.setEnabled(true);
                        appendOutput("Failed to start analysis: " + ex.getMessage());
                        LOG.error("Failed to start analysis", ex);
                    });
                }
            });
        }

        private void appendOutput(String text) {
            SwingUtilities.invokeLater(() -> {
                outputArea.append(text + "\n");
                outputArea.setCaretPosition(outputArea.getDocument().getLength());
            });
        }

        public JPanel getContentPanel() {
            return contentPanel;
        }
    }

    /**
     * PSI-based completion provider for method signatures with three-stage completion:
     * 1. Package name completion
     * 2. Class name completion (with package)
     * 3. Method name completion from all matching classes
     */
    static class MethodCompletionProvider extends TextFieldCompletionProvider {
        private final Project project;

        public MethodCompletionProvider(Project project) {
            this.project = project;
        }

        @Override
        protected void addCompletionVariants(@NotNull String text, int offset, @NotNull String prefix,
                                           @NotNull CompletionResultSet result) {

            if (prefix.isEmpty()) {
                // Show examples for empty input
                addExamplePatterns(result);
                return;
            }

            // TODO: looking for a method after typing a package name does not work properly
            // Determine completion type based on input pattern
            if (prefix.contains(".")) {
                if (isFullyQualifiedClassName(prefix)) {
                    // Case 3: Method name completion - suggest methods from the specified class
                    addMethodCompletions(prefix, result);
                } else {
                    // Case 1: Package name completion or Case 2: Class completion within package
                    addPackageAndClassCompletions(prefix, result);
                }
            } else {
                // Case 2: Class name completion (without package) - suggest full class names
                // Case 3: Method name completion - suggest methods from all classes with matching method names
                addClassNameCompletions(prefix, result);
                addMethodNameCompletions(prefix, result);
            }
        }

        private boolean isFullyQualifiedClassName(@NotNull String prefix) {
            // Check if this looks like a fully qualified class name (has dots and ends with a class-like name)
            if (!prefix.contains(".")) return false;

            int lastDot = prefix.lastIndexOf('.');
            String lastPart = prefix.substring(lastDot + 1);

            // If the last part starts with uppercase, it's likely a class name
            return !lastPart.isEmpty() && Character.isUpperCase(lastPart.charAt(0));
        }

        // TODO: Remove this
        private void addExamplePatterns(@NotNull CompletionResultSet result) {
            result.addElement(LookupElementBuilder.create("com.example.MyClass.myMethod()")
                .withTypeText("Example pattern"));
            result.addElement(LookupElementBuilder.create("java.lang.String.toString()")
                .withTypeText("Example pattern"));
            result.addElement(LookupElementBuilder.create("MyClass")
                .withTypeText("Type class name"));
            result.addElement(LookupElementBuilder.create("myMethod")
                .withTypeText("Type method name"));
        }

        // Case 1: Package name completion and Case 2: Class completion within package
        private void addPackageAndClassCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
            try {
                GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
                Query<PsiClass> query = AllClassesSearch.search(scope, project);

                query.forEach(psiClass -> {
                    String qualifiedName = psiClass.getQualifiedName();
                    if (qualifiedName != null && qualifiedName.toLowerCase().startsWith(prefix.toLowerCase())) {
                        result.addElement(LookupElementBuilder.create(qualifiedName)
                            .withIcon(psiClass.getIcon(0))
                            .withTypeText("Class"));
                    }
                    return true;
                });
            } catch (Exception e) {
                LOG.debug("Failed to access PSI for package/class completion", e);
            }
        }

        // Case 2: Class name completion (without package) - suggest full class names
        private void addClassNameCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
            try {
                PsiShortNamesCache cache = PsiShortNamesCache.getInstance(project);
                GlobalSearchScope scope = GlobalSearchScope.projectScope(project);

                String[] allClassNames = cache.getAllClassNames();
                for (String className : allClassNames) {
                    if (className.toLowerCase().contains(prefix.toLowerCase())) {
                        PsiClass[] classes = cache.getClassesByName(className, scope);
                        for (PsiClass psiClass : classes) {
                            String qualifiedName = psiClass.getQualifiedName();
                            if (qualifiedName != null) {
                                result.addElement(LookupElementBuilder.create(qualifiedName)
                                    .withIcon(psiClass.getIcon(0))
                                    .withTypeText("Class")
                                    .withLookupString(className)); // Allow matching by short name
                            }
                        }
                    }
                }
            } catch (Exception e) {
                LOG.debug("Failed to access PSI for class name completion", e);
            }
        }

        // Case 3: Method name completion - suggest methods from all classes with matching method names
        private void addMethodNameCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
            try {
                GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
                Query<PsiClass> query = AllClassesSearch.search(scope, project);

                query.forEach(psiClass -> {
                    PsiMethod[] methods = psiClass.getAllMethods();
                    for (PsiMethod method : methods) {
                        if (method.getName().toLowerCase().contains(prefix.toLowerCase())) {
                            String methodSignature = buildMethodSignature(method);
                            result.addElement(LookupElementBuilder.create(methodSignature)
                                .withIcon(method.getIcon(0))
                                .withTypeText(method.getReturnType() != null ?
                                    method.getReturnType().getPresentableText() : "void")
                                .withTailText(" (" + (method.getContainingClass() != null ?
                                    method.getContainingClass().getName() : "Unknown") + ")")
                                .withLookupString(method.getName())); // Allow matching by method name
                        }
                    }
                    return true;
                });
            } catch (Exception e) {
                LOG.debug("Failed to access PSI for method name completion", e);
            }
        }

        // Case 3: Method completion for fully qualified class names
        private void addMethodCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
            try {
                int lastDot = prefix.lastIndexOf('.');
                String className = prefix.substring(0, lastDot);
                String methodPrefix = prefix.substring(lastDot + 1);

                // Find the class using PSI
                JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
                GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
                PsiClass psiClass = facade.findClass(className, scope);

                if (psiClass != null) {
                    // Get all methods from the class
                    PsiMethod[] methods = psiClass.getAllMethods();
                    for (PsiMethod method : methods) {
                        if (method.getName().toLowerCase().startsWith(methodPrefix.toLowerCase())) {
                            String methodSignature = buildMethodSignature(method);
                            result.addElement(LookupElementBuilder.create(methodSignature)
                                .withIcon(method.getIcon(0))
                                .withTypeText(method.getReturnType() != null ?
                                    method.getReturnType().getPresentableText() : "void")
                                .withTailText(" (" + (method.getContainingClass() != null ?
                                    method.getContainingClass().getName() : "Unknown") + ")"));
                        }
                    }
                }
            } catch (Exception e) {
                LOG.debug("Failed to access PSI for method completion", e);
            }
        }

        private String buildMethodSignature(PsiMethod method) {
            StringBuilder signature = new StringBuilder();

            // Add class name
            PsiClass containingClass = method.getContainingClass();
            if (containingClass != null) {
                signature.append(containingClass.getQualifiedName()).append(".");
            }

            // Add method name
            signature.append(method.getName());

            // Add parameters
            signature.append("(");
            PsiParameter[] parameters = method.getParameterList().getParameters();
            for (int i = 0; i < parameters.length; i++) {
                if (i > 0) signature.append(", ");
                signature.append(parameters[i].getType().getPresentableText());
            }
            signature.append(")");

            return signature.toString();
        }
    }
}
