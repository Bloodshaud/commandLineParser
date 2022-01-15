package dk.eSoftware.commandLineParser.generalized.configuratinos;

import dk.eSoftware.commandLineParser.Configuration;

@SuppressWarnings("unused")
public class TwoLayerComplexConfiguration implements Configuration {
    private String stringValue;
    private ComplexConfiguration complexConfiguration;

    public TwoLayerComplexConfiguration() {
    }

    public String getStringValue() {
        return stringValue;
    }

    public ComplexConfiguration getComplexConfiguration() {
        return complexConfiguration;
    }
}
