package dk.eSoftware.commandLineParser.generalized.configuratinos;

import dk.eSoftware.commandLineParser.Configuration;
import dk.eSoftware.commandLineParser.generalized.annotations.MapConfiguration;
import dk.eSoftware.commandLineParser.generalized.annotations.Name;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationWithMapUnAnnotated implements Configuration {
    private Map<String, Integer> map;

    @Name(name = "val")
    private String otherValue;

    public ConfigurationWithMapUnAnnotated() {
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public String getOtherValue() {
        return otherValue;
    }
}
