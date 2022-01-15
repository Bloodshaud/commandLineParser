package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.Configuration;

@SuppressWarnings("unused")
class SimpleConfigurationClassPrimitiveTypes implements Configuration {
    private boolean booleanValue1;
    private boolean booleanValue2;
    private int integerValue1;
    private int integerValue2;
    private float floatValue1;
    private float floatValue2;

    public SimpleConfigurationClassPrimitiveTypes() {
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

    public float getFloatValue1() {
        return floatValue1;
    }

    public float getFloatValue2() {
        return floatValue2;
    }
}
