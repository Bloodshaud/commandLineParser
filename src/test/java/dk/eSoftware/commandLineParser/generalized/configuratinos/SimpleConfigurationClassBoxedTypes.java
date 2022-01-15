package dk.eSoftware.commandLineParser.generalized.configuratinos;

import dk.eSoftware.commandLineParser.Configuration;
import dk.eSoftware.commandLineParser.generalized.annotations.Name;

@SuppressWarnings("unused")
public class SimpleConfigurationClassBoxedTypes implements Configuration {
    @Name(name = "sv1")
    private String stringVariable1;
    @Name(name = "sv2")
    private String stringVariable2;
    @Name(name = "sv3")
    private String stringVariable3;
    @Name(name = "bv1")
    private Boolean booleanValue1;
    @Name(name = "bv2")
    private Boolean booleanValue2;
    @Name(name = "iv1")
    private Integer integerValue1;
    @Name(name = "iv2")
    private Integer integerValue2;
    @Name(name = "fv1")
    private Float floatValue1;
    @Name(name = "fv2")
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

