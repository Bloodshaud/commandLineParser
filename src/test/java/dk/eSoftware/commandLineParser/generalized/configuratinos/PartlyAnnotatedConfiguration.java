package dk.eSoftware.commandLineParser.generalized.configuratinos;

import dk.eSoftware.commandLineParser.Configuration;
import dk.eSoftware.commandLineParser.generalized.annotations.Help;

@SuppressWarnings("unused")
public class PartlyAnnotatedConfiguration implements Configuration {

    @Help(helpString = "boolean 1 help")
    private boolean booleanValue1;
    private boolean booleanValue2;
    @Help(helpString = "integer 1 help")
    private int integerValue1;
    private int integerValue2;

    public PartlyAnnotatedConfiguration() {
    }

    public boolean isBooleanValue1() {
        return booleanValue1;
    }

    public boolean isBooleanValue2() {
        return booleanValue2;
    }

    public int getIntegerValue1() {
        return integerValue1;
    }

    public int getIntegerValue2() {
        return integerValue2;
    }
}
