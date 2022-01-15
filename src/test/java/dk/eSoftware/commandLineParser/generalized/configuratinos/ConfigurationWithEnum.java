package dk.eSoftware.commandLineParser.generalized.configuratinos;

@SuppressWarnings("unused")
public class ConfigurationWithEnum {
    private String stringValue;
    private TestingEnum enumValue;

    public ConfigurationWithEnum() {
    }

    public String getStringValue() {
        return stringValue;
    }

    public TestingEnum getEnumValue() {
        return enumValue;
    }
}
