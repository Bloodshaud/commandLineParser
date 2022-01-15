package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.Configuration;

@SuppressWarnings("unused")
public class ComplexConfiguration implements Configuration {
    private String outerValue;
    private SimpleConfigurationClassPrimitiveTypes inner;

    public ComplexConfiguration() {
    }

    public String getOuterValue() {
        return outerValue;
    }

    public SimpleConfigurationClassPrimitiveTypes getInner() {
        return inner;
    }
}
