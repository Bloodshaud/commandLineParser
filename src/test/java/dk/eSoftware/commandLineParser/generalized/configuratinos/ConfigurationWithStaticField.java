package dk.eSoftware.commandLineParser.generalized.configuratinos;

import dk.eSoftware.commandLineParser.Configuration;

@SuppressWarnings("unused")
public class ConfigurationWithStaticField implements Configuration {
    private static String staticString;
    private String string;

    public ConfigurationWithStaticField() {
    }

    public static String getStaticString() {
        return staticString;
    }

    public String getString() {
        return string;
    }
}
