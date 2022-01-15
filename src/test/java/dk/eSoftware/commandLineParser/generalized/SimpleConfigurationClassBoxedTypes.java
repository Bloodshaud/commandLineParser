package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.Configuration;

@SuppressWarnings("unused")
class SimpleConfigurationClassBoxedTypes implements Configuration {
    private String stringVariable1;
    private String stringVariable2;
    private String stringVariable3;
    private Boolean booleanValue1;
    private Boolean booleanValue2;
    private Integer integerValue1;
    private Integer integerValue2;
    private Float floatValue1;
    private Float floatValue2;

    public SimpleConfigurationClassBoxedTypes() {
    }

    public String getStringVariable1() {
        return stringVariable1;
    }

    public String getStringVariable2() {
        return stringVariable2;
    }

    public String getStringVariable3() {
        return stringVariable3;
    }

    public Boolean isBooleanValue1() {
        return booleanValue1;
    }

    public Boolean isBooleanValue2() {
        return booleanValue2;
    }

    public Integer getIntegerValue1() {
        return integerValue1;
    }

    public Integer getIntegerValue2() {
        return integerValue2;
    }

    public Float getFloatValue1() {
        return floatValue1;
    }

    public Float getFloatValue2() {
        return floatValue2;
    }
}

