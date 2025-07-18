package cz.cuni.mff.d3s.autodebugger.intellijplugin;

import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.JBUI;
import com.intellij.icons.AllIcons;
import com.intellij.util.textCompletion.TextFieldWithCompletion;
import com.intellij.util.ui.FormBuilder;
import com.intellij.psi.*;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.factories.DebuggerToolWindowFactory;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.model.ApplicationRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.model.MethodValidationResult;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.services.InstrumentationService;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.services.OutputService;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.utils.MethodCompletionProvider;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.utils.MethodValidator;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategy;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategyName;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategyProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main content panel for the auto-debugger IntelliJ plugin tool window.
 * Provides UI components for configuring analysis parameters, selecting target methods,
 * and executing the auto-debugger workflow with real-time feedback and validation.
 */
public class DebuggerToolWindowContent {
    private static final Logger LOG = Logger.getInstance(DebuggerToolWindowFactory.class);

    private final Project project;
    private final JPanel contentPanel = new JPanel();

    // UI Components
    private final ComboBox<RunConfiguration> runConfigComboBox = new ComboBox<>();
    private final JButton reloadConfigsButton = new JButton(AllIcons.Actions.Refresh);
    private final JButton editConfigsButton = new JButton(AllIcons.Actions.Edit);
    private final TextFieldWithCompletion targetMethodField;
    private final JBLabel methodValidationLabel = new JBLabel();
    private final ComboBox<TestGenerationStrategy> testGenerationStrategyComboBox = new ComboBox<>();
    private final JButton runAnalysisButton = new JButton("Run Analysis");

    // Additional configuration panels
    private final JPanel fieldsPanel = new JPanel();
    private final JPanel parametersPanel = new JPanel();
    private final JPanel returnValuePanel = new JPanel();
    private final JPanel additionalConfigPanel = new JPanel();

    // Selected method information
    private PsiMethod selectedMethod = null;

    // State management for checkboxes
    private final Map<String, Boolean> fieldSelectionState = new HashMap<>();
    private final Map<String, Boolean> parameterSelectionState = new HashMap<>();
    private boolean returnValueSelected = true;

    // Method validation logic
    private final MethodValidator methodValidator;

    public DebuggerToolWindowContent(Project project) {
        this.project = project;
        this.methodValidator = new MethodValidator(project);

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
        setupEventHandlers();
        loadRunConfigurations();
        loadTestGenerationStrategies();
    }

    private void initializeUI() {
        contentPanel.setLayout(new java.awt.BorderLayout());

        // Create the main form
        JPanel formPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("Run configuration:", createRunConfigPanel())
                .addLabeledComponent("Target method:", createMethodSelectionPanel())
                .addLabeledComponent("Test generation strategy:", testGenerationStrategyComboBox)
                .addComponent(additionalConfigPanel)
                .addComponent(runAnalysisButton)
                .getPanel();

        // Wrap the form panel to ensure proper spacing and alignment
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(formPanel, BorderLayout.NORTH);

        // Add some padding around the form
        wrapperPanel.setBorder(JBUI.Borders.empty(10));

        // Add the wrapper panel to the top (NORTH) instead of CENTER to prevent vertical centering
        contentPanel.add(wrapperPanel, BorderLayout.NORTH);

        // Initialize additional configuration panels
        initializeAdditionalConfigPanels();
    }

    private JPanel createRunConfigPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(runConfigComboBox, BorderLayout.CENTER);

        // Create a button panel for Reload and Edit buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 5));
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
        additionalConfigPanel.setBorder(JBUI.Borders.empty(10));

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

        JBLabel fieldsLabel = new JBLabel("Target fields:");
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

        JBLabel parametersLabel = new JBLabel("Target parameters:");
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

        JBLabel returnLabel = new JBLabel("Return value:");
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

        // Enable the run button if we have both configuration and method
        updateRunButtonState();
    }

    private void loadTestGenerationStrategies() {
        LOG.info("Loading test generation strategies from runner module");

        testGenerationStrategyComboBox.removeAllItems();

        try {
            List<TestGenerationStrategy> strategies = TestGenerationStrategyProvider.getAvailableStrategies();
            LOG.info("Found " + strategies.size() + " test generation strategies");

            for (TestGenerationStrategy strategy : strategies) {
                testGenerationStrategyComboBox.addItem(strategy);
                LOG.debug("Added strategy: " + strategy.getDisplayName());
            }

            // Select the default strategy
            TestGenerationStrategy defaultStrategy = TestGenerationStrategyProvider.getDefaultStrategy();
            if (defaultStrategy != null) {
                testGenerationStrategyComboBox.setSelectedItem(defaultStrategy);
                LOG.info("Selected default strategy: " + defaultStrategy.getDisplayName());
            }

        } catch (Exception e) {
            LOG.error("Failed to load test generation strategies", e);
            // Add a fallback strategy in case of error
            TestGenerationStrategy fallback = new TestGenerationStrategy(
                    TestGenerationStrategyName.TRACE_BASED_NAIVE, true
            );
            testGenerationStrategyComboBox.addItem(fallback);
        }
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
        LOG.info("Validating method: '" + methodText + "'");

        if (methodText.trim().isEmpty()) {
            LOG.info("Method text is empty, clearing validation");
            clearMethodValidation();
            return;
        }

        // Parse and validate the method signature in background thread
        String signature = methodText.trim();
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            ReadAction.run(() -> {
                MethodValidationResult result = methodValidator.validateMethodSignature(signature);

                // Update UI on EDT
                ApplicationManager.getApplication().invokeLater(() -> {
                    if (result.isValid) {
                        LOG.info("Method validation successful: " + result.method.getName() + " in class " +
                                (result.method.getContainingClass() != null ? result.method.getContainingClass().getQualifiedName() : "unknown"));
                        setMethodValid(result.method);
                    } else {
                        LOG.info("Method validation failed: " + result.message);
                        setMethodInvalid();
                    }
                    updateRunButtonState();
                });
            });
        });
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

    private void showAdditionalConfig(PsiMethod method) {
        LOG.info("Showing additional configuration for method: " + method.getName());

        // Clear existing content
        additionalConfigPanel.removeAll();

        // Add fields configuration if the class has fields
        PsiClass containingClass = method.getContainingClass();
        if (containingClass != null) {
            PsiField[] fields = containingClass.getAllFields();
            LOG.info("Found " + fields.length + " fields in class " + containingClass.getQualifiedName());
            if (fields.length > 0) {
                populateFieldsPanel(fields);
                additionalConfigPanel.add(fieldsPanel);
            }
        }

        // Add parameters configuration if the method has parameters
        PsiParameter[] parameters = method.getParameterList().getParameters();
        LOG.info("Found " + parameters.length + " parameters in method " + method.getName());
        if (parameters.length > 0) {
            populateParametersPanel(parameters);
            additionalConfigPanel.add(parametersPanel);
        }

        // Add return value configuration if the method has a return type
        PsiType returnType = method.getReturnType();
        if (returnType != null && !returnType.equals(PsiTypes.voidType())) {
            LOG.info("Method has return type: " + returnType.getPresentableText());
            populateReturnValuePanel(returnType);
            additionalConfigPanel.add(returnValuePanel);
        } else {
            LOG.info("Method has void return type, not showing return value panel");
        }

        // Show the panel and refresh the UI
        additionalConfigPanel.setVisible(true);
        contentPanel.revalidate();
        contentPanel.repaint();
        LOG.info("Additional configuration panel shown and UI refreshed");
    }

    private void hideAdditionalConfig() {
        additionalConfigPanel.setVisible(false);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void populateFieldsPanel(PsiField[] fields) {
        LOG.debug("Populating fields panel with " + fields.length + " fields");

        // Clear existing content except the label
        fieldsPanel.removeAll();

        JBLabel fieldsLabel = new JBLabel("Target fields:");
        fieldsLabel.setFont(fieldsLabel.getFont().deriveFont(Font.BOLD));
        fieldsPanel.add(fieldsLabel);
        fieldsPanel.add(Box.createVerticalStrut(5));

        // Create checkboxes for each field
        for (PsiField field : fields) {
            if (shouldShowField(field)) {
                String fieldKey = getFieldKey(field);
                String displayName = getFieldDisplayName(field);
                JBCheckBox fieldCheckBox = new JBCheckBox(displayName);

                // Restore previous state or use default
                boolean isSelected = fieldSelectionState.getOrDefault(fieldKey, false);
                fieldCheckBox.setSelected(isSelected);
                LOG.debug("Field '" + fieldKey + "' checkbox state: " + isSelected);

                // Add listener to track state changes
                fieldCheckBox.addActionListener(e -> {
                    boolean selected = fieldCheckBox.isSelected();
                    fieldSelectionState.put(fieldKey, selected);
                    LOG.debug("Field '" + fieldKey + "' state changed to: " + selected);
                });

                fieldsPanel.add(fieldCheckBox);
            }
        }
    }

    public void populateParametersPanel(PsiParameter[] parameters) {
        LOG.debug("Populating parameters panel with " + parameters.length + " parameters");

        // Clear existing content except the label
        parametersPanel.removeAll();

        JBLabel parametersLabel = new JBLabel("Target parameters:");
        parametersLabel.setFont(parametersLabel.getFont().deriveFont(Font.BOLD));
        parametersPanel.add(parametersLabel);
        parametersPanel.add(Box.createVerticalStrut(5));

        // Create checkboxes for each parameter
        for (int i = 0; i < parameters.length; i++) {
            PsiParameter parameter = parameters[i];
            String paramKey = getParameterKey(parameter, i);
            String displayName = getParameterDisplayName(parameter, i);
            JBCheckBox paramCheckBox = new JBCheckBox(displayName);

            // Restore previous state or use default (true for parameters)
            boolean isSelected = parameterSelectionState.getOrDefault(paramKey, true);
            paramCheckBox.setSelected(isSelected);
            LOG.debug("Parameter '" + paramKey + "' checkbox state: " + isSelected);

            // Add listener to track state changes
            paramCheckBox.addActionListener(e -> {
                boolean selected = paramCheckBox.isSelected();
                parameterSelectionState.put(paramKey, selected);
                LOG.debug("Parameter '" + paramKey + "' state changed to: " + selected);
            });

            parametersPanel.add(paramCheckBox);
        }
    }

    public void populateReturnValuePanel(PsiType returnType) {
        LOG.debug("Populating return value panel for type: " + returnType.getPresentableText());

        // Clear existing content except the label
        returnValuePanel.removeAll();

        JBLabel returnLabel = new JBLabel("Return value:");
        returnLabel.setFont(returnLabel.getFont().deriveFont(Font.BOLD));
        returnValuePanel.add(returnLabel);
        returnValuePanel.add(Box.createVerticalStrut(5));

        // Create checkbox for return value
        String displayName = "Track return value (" + returnType.getPresentableText() + ")";
        JBCheckBox returnCheckBox = new JBCheckBox(displayName);

        // Restore previous state or use default (true)
        returnCheckBox.setSelected(returnValueSelected);
        LOG.debug("Return value checkbox state: " + returnValueSelected);

        // Add listener to track state changes
        returnCheckBox.addActionListener(e -> {
            returnValueSelected = returnCheckBox.isSelected();
            LOG.debug("Return value state changed to: " + returnValueSelected);
        });

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
        if (paramName.isEmpty()) {
            paramName = "param" + index;
        }

        return parameter.getType().getPresentableText() + " " + paramName;
    }

    private String getFieldKey(PsiField field) {
        PsiClass containingClass = field.getContainingClass();
        String className = containingClass != null ? containingClass.getQualifiedName() : "unknown";
        return className + "." + field.getName();
    }

    private String getParameterKey(PsiParameter parameter, int index) {
        String paramName = parameter.getName();
        if (paramName.isEmpty()) {
            paramName = "param" + index;
        }
        return "param_" + index + "_" + paramName + "_" + parameter.getType().getPresentableText();
    }

    private void setupEventHandlers() {
        // Configure button tooltips and styling
        configureToolbarButton(reloadConfigsButton, "Reload run configurations");
        configureToolbarButton(editConfigsButton, "Edit run configurations");

        // Add button event handlers
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

    /**
     * Configures a button to look like an IntelliJ toolbar button.
     */
    private void configureToolbarButton(JButton button, String tooltip) {
        button.setToolTipText(tooltip);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        // Set preferred size to make it compact
        Dimension size = new Dimension(22, 22);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setContentAreaFilled(true);
                button.setBorderPainted(true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    private void onRunAnalysis(ActionEvent e) {
        RunConfiguration selectedConfig = (RunConfiguration) runConfigComboBox.getSelectedItem();
        String methodSignature = targetMethodField.getText();
        TestGenerationStrategy selectedStrategy = (TestGenerationStrategy) testGenerationStrategyComboBox.getSelectedItem();

        if (selectedConfig == null || methodSignature.trim().isEmpty()) {
            Messages.showErrorDialog(project, "Please select both a run configuration and a target method.", "Missing Selection");
            return;
        }

        if (selectedStrategy == null) {
            Messages.showErrorDialog(project, "Please select a test generation strategy.", "Missing Selection");
            return;
        }

        LOG.info("Starting analysis with strategy: " + selectedStrategy.getDisplayName());

        // Create auto-debugger run configuration
        ApplicationRunConfiguration autoDebuggerConfig =
                createAutoDebuggerConfiguration(selectedConfig, methodSignature.trim());

        // Run the analysis with the selected strategy
        runAnalysis(autoDebuggerConfig, selectedStrategy);
    }

    private ApplicationRunConfiguration createAutoDebuggerConfiguration(
            RunConfiguration intellijConfig, String targetMethodSignature) {

        String configName = "Auto-Debug: " + intellijConfig.getName();
        ApplicationRunConfiguration config =
                new ApplicationRunConfiguration(configName);

        config.setDescription("Auto-generated configuration for " + targetMethodSignature);
        config.setTargetMethodReference(targetMethodSignature);

        // Set required fields with mock/default values for now
        // TODO: Extract actual information from IntelliJ run configuration
        config.setApplicationJarPath(java.nio.file.Path.of("target/application.jar"));
        config.setMainClass("com.example.MainClass");

        // Set optional fields
        config.setWorkingDirectory(java.nio.file.Path.of(project.getBasePath() != null ? project.getBasePath() : "."));

        // Extract class name from method signature for main class if possible
        if (targetMethodSignature.contains(".")) {
            String className = targetMethodSignature.substring(0, targetMethodSignature.lastIndexOf('.'));
            if (className.contains(".")) {
                // If it contains package, use it as is
                config.setMainClass(className.substring(0, className.lastIndexOf('.')));
            } else {
                // Simple class name
                config.setMainClass(className);
            }
        }

        return config;
    }

    private void runAnalysis(ApplicationRunConfiguration config, TestGenerationStrategy strategy) {
        OutputService outputService = OutputService.getInstance(project);

        // Clear previous output and start new analysis
        outputService.clear(OutputService.OutputType.TOOL_OUTPUT);
        outputService.print(OutputService.OutputType.TOOL_OUTPUT, "Starting analysis...");
        outputService.print(OutputService.OutputType.TOOL_OUTPUT, "Selected test generation strategy: " + strategy.getDisplayName());
        outputService.print(OutputService.OutputType.TOOL_OUTPUT, "Strategy description: " + strategy.getDescription());

        runAnalysisButton.setEnabled(false);

        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            try {
                // Start instrumentation phase
                outputService.print(OutputService.OutputType.TOOL_OUTPUT, "Phase 1: Running instrumentation...");

                // TODO: Arguments arguments = new Arguments();
                // TODO: Runner.run();

                InstrumentationService service = InstrumentationService.getInstance(project);
                service.runInstrumentation(config, line ->
                    outputService.print(OutputService.OutputType.TOOL_OUTPUT, line))
                        .whenComplete((result, throwable) -> {
                            SwingUtilities.invokeLater(() -> {
                                runAnalysisButton.setEnabled(true);
                                if (throwable != null) {
                                    outputService.printError(OutputService.OutputType.TOOL_OUTPUT,
                                        "Analysis failed: " + throwable.getMessage());
                                    LOG.error("Analysis failed", throwable);
                                } else {
                                    outputService.print(OutputService.OutputType.TOOL_OUTPUT,
                                        "Instrumentation completed successfully!");

                                    if (result != null && !result.isEmpty()) {
                                        outputService.print(OutputService.OutputType.TOOL_OUTPUT,
                                            "Generated files: " + result.stream()
                                                .map(Path::toString)
                                                .collect(Collectors.joining(", ")));
                                    }

                                    // Start application run phase
                                    runApplicationAnalysis(config, outputService);

                                    // Start test generation and execution phase
                                    runTestGeneration(config, strategy, outputService);
                                }
                            });
                        });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    runAnalysisButton.setEnabled(true);
                    outputService.printError(OutputService.OutputType.TOOL_OUTPUT,
                        "Failed to start analysis: " + ex.getMessage());
                    LOG.error("Failed to start analysis", ex);
                });
            }
        });
    }

    /**
     * Simulates running the instrumented application and collecting runtime data.
     */
    private void runApplicationAnalysis(ApplicationRunConfiguration config, OutputService outputService) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            try {
                outputService.clear(OutputService.OutputType.ANALYSIS_RUN);
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Phase 2: Running instrumented application...");
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Command: java -javaagent:instrumentation.jar " + config.getMainClass());
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "");

                // Simulate application startup
                Thread.sleep(1000);
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Application started successfully");
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Collecting runtime data for method: " + config.getTargetMethodReference());

                // Simulate method execution and data collection
                Thread.sleep(2000);
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Method invoked with parameters: [arg1=42, arg2=\"test\"]");
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Field values captured: [field1=100, field2=\"initialized\"]");
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Return value: \"success\"");

                Thread.sleep(1000);
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Runtime data collection completed");
                outputService.print(OutputService.OutputType.ANALYSIS_RUN, "Trace data saved to: /tmp/auto-debugger/trace-" + System.currentTimeMillis() + ".json");

            } catch (InterruptedException e) {
                outputService.printError(OutputService.OutputType.ANALYSIS_RUN, "Application analysis interrupted");
                Thread.currentThread().interrupt();
            }
        });
    }

    /**
     * Simulates generating and running unit tests based on collected data.
     */
    private void runTestGeneration(ApplicationRunConfiguration config, TestGenerationStrategy strategy, OutputService outputService) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            try {
                // Wait a bit for application analysis to start
                Thread.sleep(3000);

                outputService.clear(OutputService.OutputType.TESTS);
                outputService.print(OutputService.OutputType.TESTS, "Phase 3: Generating and running unit tests...");
                outputService.print(OutputService.OutputType.TESTS, "Using strategy: " + strategy.getDisplayName());
                outputService.print(OutputService.OutputType.TESTS, "");

                // Simulate test generation based on strategy
                Thread.sleep(1500);
                outputService.print(OutputService.OutputType.TESTS, "Generating test cases using " + strategy.getDisplayName() + "...");

                // Generate strategy-specific test examples
                generateStrategySpecificTests(strategy, outputService);
                outputService.print(OutputService.OutputType.TESTS, "");

                // Simulate test compilation
                Thread.sleep(1000);
                outputService.print(OutputService.OutputType.TESTS, "Compiling generated tests...");
                outputService.print(OutputService.OutputType.TESTS, "Compilation successful");
                outputService.print(OutputService.OutputType.TESTS, "");

                // Simulate test execution
                Thread.sleep(2000);
                outputService.print(OutputService.OutputType.TESTS, "Running generated tests...");
                outputService.print(OutputService.OutputType.TESTS, "");
                outputService.print(OutputService.OutputType.TESTS, "testMethod_WithArgs42AndTest_ReturnsSuccess: PASSED");
                outputService.print(OutputService.OutputType.TESTS, "testMethod_WithNullArgs_ThrowsException: PASSED");
                outputService.print(OutputService.OutputType.TESTS, "testMethod_WithEmptyString_ReturnsEmpty: FAILED");
                outputService.print(OutputService.OutputType.TESTS, "  Expected: \"\" but was: \"default\"");
                outputService.print(OutputService.OutputType.TESTS, "");
                outputService.print(OutputService.OutputType.TESTS, "Test Results: 2 passed, 1 failed, 3 total");
                outputService.print(OutputService.OutputType.TESTS, "Generated test files saved to: src/test/java/generated/");

            } catch (InterruptedException e) {
                outputService.printError(OutputService.OutputType.TESTS, "Test generation interrupted");
                Thread.currentThread().interrupt();
            }
        });
    }

    /**
     * Generates strategy-specific test examples for demonstration purposes.
     */
    private void generateStrategySpecificTests(TestGenerationStrategy strategy, OutputService outputService) {
        switch (strategy.getId()) {
            case "trace-based-basic":
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_WithArgs42AndTest_ReturnsSuccess()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_WithNullArgs_ThrowsException()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_WithEmptyString_ReturnsEmpty()");
                break;

            case "trace-based-advanced":
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_EdgeCase_BoundaryValue()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_MLDetected_UnusualPattern()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_AdvancedScenario_ComplexFlow()");
                break;

            case "property-based":
                outputService.print(OutputService.OutputType.TESTS, "Generated property: forAll_inputRange_outputInvariantHolds()");
                outputService.print(OutputService.OutputType.TESTS, "Generated property: forAll_validInputs_noExceptionThrown()");
                outputService.print(OutputService.OutputType.TESTS, "Generated property: forAll_operations_idempotencyHolds()");
                break;

            case "mutation-based":
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_MutatedInput_1_DetectsBehaviorChange()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_MutatedInput_2_VerifiesRobustness()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_BoundaryMutation_HandlesEdgeCases()");
                break;

            case "symbolic-execution":
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_Path1_ConditionTrue()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_Path2_ConditionFalse()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_Path3_ExceptionPath()");
                break;

            case "ai-assisted":
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_AIGenerated_ReadableScenario1()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_AIGenerated_BusinessLogicTest()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_AIGenerated_DocumentedBehavior()");
                break;

            default:
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_DefaultStrategy_BasicTest()");
                outputService.print(OutputService.OutputType.TESTS, "Generated test: testMethod_DefaultStrategy_SimpleCase()");
                break;
        }
    }
}
