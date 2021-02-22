package dk.eSoftware.commandLineParser;

public class SingletonCommandLineParser extends CommandLineParser {
    private final ConfigBuilder builder;

    /**
     * Constructor for a {@link CommandLineParser} with only one mode of operations.
     *
     * @param builder the singleton builder
     */
    public SingletonCommandLineParser(ConfigBuilder builder) {
        this.builder = builder;
    }

    @Override
    protected ConfigBuilder getConfigBuilder(String firstParam) {
        return builder;
    }

    @Override
    public String help() {
        return builder.help();
    }
}
