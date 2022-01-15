package dk.eSoftware.commandLineParser;

public class SingletonCommandLineParser<T extends Configuration> extends CommandLineParser<T> {
    private final ConfigBuilder<T> builder;

    /**
     * Constructor for a {@link CommandLineParser} with only one mode of operations.
     *
     * @param builder the singleton builder
     */
    public SingletonCommandLineParser(ConfigBuilder<T> builder) {
        this.builder = builder;
    }

    @Override
    public T parse(String[] input) throws NoSuchBuilderException, WrongFormatException, UnknownCommandException {
        return super.parse(input);
    }

    @Override
    protected ConfigBuilder<T> getConfigBuilder(String firstParam) {
        return builder;
    }

    @Override
    public String help() {
        return builder.help();
    }
}
