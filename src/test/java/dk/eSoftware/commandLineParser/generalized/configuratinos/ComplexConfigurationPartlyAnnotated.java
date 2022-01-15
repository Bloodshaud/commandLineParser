package dk.eSoftware.commandLineParser.generalized.configuratinos;

import dk.eSoftware.commandLineParser.Configuration;
import dk.eSoftware.commandLineParser.generalized.annotations.Help;

@SuppressWarnings("unused")
public class ComplexConfigurationPartlyAnnotated implements Configuration {

    @Help(helpString = "Inner configuration")
    private PartlyAnnotatedConfiguration inner;
    @Help(helpString = "Some value")
    private String val;

    public ComplexConfigurationPartlyAnnotated() {
    }

    public PartlyAnnotatedConfiguration getInner() {
        return inner;
    }

    public String getVal() {
        return val;
    }
}
