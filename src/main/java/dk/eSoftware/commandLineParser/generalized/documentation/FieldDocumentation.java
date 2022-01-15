package dk.eSoftware.commandLineParser.generalized.documentation;

public class FieldDocumentation {
    private final Class<?> dataType;
    private final String helpString;

    public FieldDocumentation(Class<?> dataType, String helpString) {
        this.dataType = dataType;
        this.helpString = helpString;
    }

    @Override
    public String toString() {
        return "" + "type=" + dataType + ", " + helpString;
    }
}
