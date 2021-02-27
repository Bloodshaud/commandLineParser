package dk.eSoftware.commandLineParser;

import java.util.Map;

public class MultiParser<T extends Configuration> extends CommandLineParser<T> {
    private final Map<String, ConfigBuilder<? extends T>> mapping;


    /**
     * Constructor for a {@link CommandLineParser}
     *
     * @param mapping a map between modes and configbuilders.
     */
    public MultiParser(Map<String, ConfigBuilder<? extends T>> mapping) {
        this.mapping = mapping;
    }

    @Override
    protected ConfigBuilder<? extends T> getConfigBuilder(String firstParam) throws NoSuchBuilderException {
        ConfigBuilder<? extends T> configBuilder = mapping.get(firstParam);
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
