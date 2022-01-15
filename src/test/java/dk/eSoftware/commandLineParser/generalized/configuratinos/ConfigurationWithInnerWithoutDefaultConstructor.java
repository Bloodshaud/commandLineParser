package dk.eSoftware.commandLineParser.generalized.configuratinos;

import dk.eSoftware.commandLineParser.Configuration;

@SuppressWarnings("unused")
public class ConfigurationWithInnerWithoutDefaultConstructor implements Configuration {
    private ConfigurationWithoutDefaultConstructor inner;
    private String stringValue;

    public ConfigurationWithInnerWithoutDefaultConstructor() {
    }

    public ConfigurationWithoutDefaultConstructor getInner() {
        return inner;
    }

    public String getStringValue() {
        return stringValue;
    }
}
