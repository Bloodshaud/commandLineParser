package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.Configuration;

public class ConfigurationWithoutDefaultConstructor implements Configuration {
    private String value;

    public ConfigurationWithoutDefaultConstructor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
