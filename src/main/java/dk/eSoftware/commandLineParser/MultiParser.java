package dk.eSoftware.commandLineParser;

import java.util.Map;

@SuppressWarnings("unused")
public class MultiParser extends CommandLineParser {
    private final Map<String, ConfigBuilder> mapping;


    /**
     * Constructor for a {@link CommandLineParser}
     *
     * @param mapping a map between modes and configbuilders.
     */
    public MultiParser(Map<String, ConfigBuilder> mapping) {
        this.mapping = mapping;
    }

    @Override
    protected ConfigBuilder getConfigBuilder(String firstParam) throws NoSuchBuilderException {
        ConfigBuilder configBuilder = mapping.get(firstParam);
        if (configBuilder == null) {
            throw new NoSuchBuilderException("Unable to find ConfigBuilder from mode: \"" + firstParam + "\"");
        }
        return configBuilder;
    }


    @Override
    public String help() {
        StringBuilder sb = new StringBuilder();
        sb.append("Help information compiled by CommandLineParser:\n");
        for (String s : mapping.keySet()) {
            sb.append(mapping.get(s).help()).append("\n");
        }
        return sb.toString();
    }
}
