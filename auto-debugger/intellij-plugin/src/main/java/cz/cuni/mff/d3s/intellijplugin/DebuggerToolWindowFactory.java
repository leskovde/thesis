package cz.cuni.mff.d3s.intellijplugin;

import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.impl.EditConfigurationsDialog;
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
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
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
            editConfigsButton.addActionListener(this::onEditConfigurations);
            runAnalysisButton.addActionListener(this::onRunAnalysis);

            // Add combo box listener to update run button state
            runConfigComboBox.addActionListener(e -> updateRunButtonState());

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
            EditConfigurationsDialog dialog = new EditConfigurationsDialog(project);
            dialog.show();

            // Reload configurations after the dialog is closed in case user made changes
            loadRunConfigurations();
        }

        private void updateRunButtonState() {
            boolean hasConfiguration = runConfigComboBox.getSelectedItem() != null;
            boolean hasMethod = !targetMethodField.getText().trim().isEmpty();
            runAnalysisButton.setEnabled(hasConfiguration && hasMethod);
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
     * Completion provider for method signatures with suggestions.
     */
    private static class MethodCompletionProvider extends TextFieldCompletionProvider {
        private final Project project;

        public MethodCompletionProvider(Project project) {
            this.project = project;
        }

        @Override
        protected void addCompletionVariants(@NotNull String text, int offset, @NotNull String prefix,
                                           @NotNull CompletionResultSet result) {
            // If the prefix contains a dot, try to complete method names using PSI
            if (prefix.contains(".") && !prefix.endsWith(".")) {
                addPsiMethodCompletions(prefix, result);
            } else {
                // Complete class names using PSI
                addPsiClassCompletions(prefix, result);
            }
        }

        private void addPsiClassCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
            try {
                PsiShortNamesCache cache = PsiShortNamesCache.getInstance(project);
                GlobalSearchScope scope = GlobalSearchScope.projectScope(project);

                // Get all class names that start with the prefix
                String[] allClassNames = cache.getAllClassNames();
                for (String className : allClassNames) {
                    if (className.toLowerCase().startsWith(prefix.toLowerCase())) {
                        PsiClass[] classes = cache.getClassesByName(className, scope);
                        for (PsiClass psiClass : classes) {
                            String qualifiedName = psiClass.getQualifiedName();
                            if (qualifiedName != null) {
                                result.addElement(LookupElementBuilder.create(qualifiedName)
                                        .withIcon(psiClass.getIcon(0))
                                        .withTypeText("Class from project"));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // Fallback to simple completion if PSI access fails
                LOG.debug("Failed to access PSI for class completion", e);
            }
        }

        private void addPsiMethodCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
            try {
                int lastDot = prefix.lastIndexOf('.');
                String className = prefix.substring(0, lastDot);
                String methodPrefix = prefix.substring(lastDot + 1);

                // Find the class using PSI
                JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
                GlobalSearchScope scope = GlobalSearchScope.allScope(project);
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
