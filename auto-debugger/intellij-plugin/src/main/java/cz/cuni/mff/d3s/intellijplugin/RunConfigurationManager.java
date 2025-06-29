package cz.cuni.mff.d3s.intellijplugin;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Manages run configurations for the auto-debugger.
 * This service is responsible for storing and retrieving run configurations.
 */
@Service(Service.Level.PROJECT)
@State(
    name = "AutoDebuggerRunConfigurations",
    storages = @Storage("autoDebuggerRunConfigurations.xml")
)
public final class RunConfigurationManager implements PersistentStateComponent<RunConfigurationManager.State> {
    private final Project project;
    private State state = new State();

    /**
     * Creates a new run configuration manager for the specified project.
     *
     * @param project The project
     */
    public RunConfigurationManager(Project project) {
        this.project = project;
    }

    /**
     * Gets the run configuration manager for the specified project.
     *
     * @param project The project
     * @return The run configuration manager
     */
    public static RunConfigurationManager getInstance(@NotNull Project project) {
        return project.getService(RunConfigurationManager.class);
    }

    /**
     * Gets all run configurations.
     *
     * @return The list of run configurations
     */
    public List<RunConfiguration> getConfigurations() {
        return state.configurations;
    }

    /**
     * Gets a run configuration by name.
     *
     * @param name The name of the configuration
     * @return The run configuration, or null if not found
     */
    public RunConfiguration getConfiguration(String name) {
        return state.configurations.stream()
            .filter(config -> config.getName().equals(name))
            .findFirst()
            .orElse(null);
    }

    /**
     * Adds a run configuration.
     *
     * @param configuration The configuration to add
     */
    public void addConfiguration(RunConfiguration configuration) {
        // Remove any existing configuration with the same name
        state.configurations.removeIf(config -> config.getName().equals(configuration.getName()));
        state.configurations.add(configuration);
    }

    /**
     * Removes a run configuration.
     *
     * @param name The name of the configuration to remove
     * @return true if the configuration was removed, false otherwise
     */
    public boolean removeConfiguration(String name) {
        return state.configurations.removeIf(config -> config.getName().equals(name));
    }

    @Nullable
    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        XmlSerializerUtil.copyBean(state, this.state);
    }

    /**
     * State class for persisting run configurations.
     */
    public static class State {
        public List<RunConfiguration> configurations = new ArrayList<>();
    }
}