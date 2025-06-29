package cz.cuni.mff.d3s.intellijplugin;

import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.textCompletion.TextFieldWithCompletion;
import com.intellij.util.TextFieldCompletionProvider;
import com.intellij.util.ui.FormBuilder;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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
        private final TextFieldWithCompletion targetMethodField;
        private final JButton runAnalysisButton = new JButton("Run Analysis");
        private final JTextArea outputArea = new JTextArea(10, 40);

        // Selected method information
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
                .addLabeledComponent("Target Method:", targetMethodField)
                .addComponent(runAnalysisButton)
                .addLabeledComponentFillVertically("Output:", new JBScrollPane(outputArea))
                .getPanel();

            contentPanel.add(formPanel, BorderLayout.CENTER);

            // Configure components
            outputArea.setEditable(false);
            outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            // Add event listeners
            reloadConfigsButton.addActionListener(this::onReloadConfigurations);
            runAnalysisButton.addActionListener(this::onRunAnalysis);

            // Add combo box listener to update run button state
            runConfigComboBox.addActionListener(e -> updateRunButtonState());

            // Initially disable run button
            runAnalysisButton.setEnabled(false);
        }

        private JPanel createRunConfigPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(runConfigComboBox, BorderLayout.CENTER);
            panel.add(reloadConfigsButton, BorderLayout.EAST);
            return panel;
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



        private void updateRunButtonState() {
            boolean hasConfiguration = runConfigComboBox.getSelectedItem() != null;
            boolean hasMethod = targetMethodField.getText() != null && !targetMethodField.getText().trim().isEmpty();
            runAnalysisButton.setEnabled(hasConfiguration && hasMethod);
        }

        private void onRunAnalysis(ActionEvent e) {
            RunConfiguration selectedConfig = (RunConfiguration) runConfigComboBox.getSelectedItem();
            String methodSignature = targetMethodField.getText();

            if (selectedConfig == null || methodSignature == null || methodSignature.trim().isEmpty()) {
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
     * Completion provider for method signatures with common Java method examples.
     */
    private static class MethodCompletionProvider extends TextFieldCompletionProvider {
        private final Project project;

        // Common method signature examples
        private static final String[] COMMON_METHODS = {
            "java.lang.String.toString()",
            "java.lang.String.equals(Object)",
            "java.lang.String.length()",
            "java.lang.Object.hashCode()",
            "java.util.List.add(Object)",
            "java.util.List.get(int)",
            "java.util.List.size()",
            "java.util.Map.put(Object, Object)",
            "java.util.Map.get(Object)",
            "java.io.PrintStream.println(String)",
            "java.lang.System.currentTimeMillis()",
            "java.lang.Math.max(int, int)",
            "java.lang.Math.min(int, int)"
        };

        public MethodCompletionProvider(Project project) {
            this.project = project;
        }

        @Override
        protected void addCompletionVariants(@NotNull String text, int offset, @NotNull String prefix,
                                           @NotNull CompletionResultSet result) {
            // Add common method signatures that match the prefix
            for (String method : COMMON_METHODS) {
                if (method.toLowerCase().contains(prefix.toLowerCase())) {
                    result.addElement(LookupElementBuilder.create(method)
                        .withTypeText("Common Java method"));
                }
            }

            // Add some example patterns
            if (prefix.isEmpty() || "com".startsWith(prefix.toLowerCase())) {
                result.addElement(LookupElementBuilder.create("com.example.MyClass.myMethod()")
                    .withTypeText("Example pattern"));
            }

            if (prefix.isEmpty() || "main".startsWith(prefix.toLowerCase())) {
                result.addElement(LookupElementBuilder.create("com.example.Main.main(String[])")
                    .withTypeText("Main method pattern"));
            }

            // If the prefix looks like a class name, suggest method completion
            if (prefix.contains(".") && !prefix.endsWith(".")) {
                int lastDot = prefix.lastIndexOf('.');
                String className = prefix.substring(0, lastDot);
                String methodPrefix = prefix.substring(lastDot + 1);

                // Suggest common method names
                String[] commonMethodNames = {"toString", "equals", "hashCode", "get", "set", "add", "remove", "size", "isEmpty"};
                for (String methodName : commonMethodNames) {
                    if (methodName.toLowerCase().startsWith(methodPrefix.toLowerCase())) {
                        String suggestion = className + "." + methodName + "()";
                        result.addElement(LookupElementBuilder.create(suggestion)
                            .withTypeText("Common method"));
                    }
                }
            }
        }
    }
}
