package dk.eSoftware.commandLineParser.generalized;

/**
 * Simple data-pair for representing the relation between a command and its value
 */
class FieldValuePair {
    private final String command;
    private final String value;

    public FieldValuePair(String command, String value) {
        this.command = command;
        this.value = value;
    }

    public String getField() {
        return command;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CommandValuePair{" +
                "command='" + command + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
