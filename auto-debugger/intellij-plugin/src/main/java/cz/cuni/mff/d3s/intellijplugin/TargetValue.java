package cz.cuni.mff.d3s.intellijplugin;

import java.io.Serializable;

/**
 * Represents a target value to be monitored during instrumentation.
 * This can be a field, parameter, or global variable.
 */
public class TargetValue implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The type of target value.
     */
    public enum Type {
        FIELD,
        PARAMETER,
        GLOBAL_VARIABLE
    }

    private final Type type;
    private final String name;
    private final String dataType;
    private String owner; // For fields, this is the class name

    /**
     * Creates a new target value.
     *
     * @param type The type of the target value
     * @param name The name of the target value
     * @param dataType The data type of the target value
     */
    public TargetValue(Type type, String name, String dataType) {
        this.type = type;
        this.name = name;
        this.dataType = dataType;
    }

    /**
     * Creates a new field target value.
     *
     * @param name The name of the field
     * @param dataType The data type of the field
     * @param owner The class that owns the field
     * @return A new TargetValue representing a field
     */
    public static TargetValue field(String name, String dataType, String owner) {
        TargetValue value = new TargetValue(Type.FIELD, name, dataType);
        value.owner = owner;
        return value;
    }

    /**
     * Creates a new parameter target value.
     *
     * @param name The name of the parameter
     * @param dataType The data type of the parameter
     * @return A new TargetValue representing a parameter
     */
    public static TargetValue parameter(String name, String dataType) {
        return new TargetValue(Type.PARAMETER, name, dataType);
    }

    /**
     * Creates a new global variable target value.
     *
     * @param name The name of the global variable
     * @param dataType The data type of the global variable
     * @return A new TargetValue representing a global variable
     */
    public static TargetValue globalVariable(String name, String dataType) {
        return new TargetValue(Type.GLOBAL_VARIABLE, name, dataType);
    }

    /**
     * Gets the type of this target value.
     *
     * @return The type of this target value
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the name of this target value.
     *
     * @return The name of this target value
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the data type of this target value.
     *
     * @return The data type of this target value
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Gets the owner of this target value (for fields).
     *
     * @return The owner of this target value, or null if not applicable
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of this target value (for fields).
     *
     * @param owner The owner of this target value
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Returns a string representation of this target value.
     *
     * @return A string representation of this target value
     */
    @Override
    public String toString() {
        switch (type) {
            case FIELD:
                return String.format("Field: %s %s (in %s)", dataType, name, owner);
            case PARAMETER:
                return String.format("Parameter: %s %s", dataType, name);
            case GLOBAL_VARIABLE:
                return String.format("Global: %s %s", dataType, name);
            default:
                return String.format("%s %s", dataType, name);
        }
    }
}