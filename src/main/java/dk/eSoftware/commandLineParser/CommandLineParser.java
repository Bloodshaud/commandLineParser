package dk.eSoftware.commandLineParser;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandLineParser<T extends Configuration> {

    /**
     * Utility for parsing commandline parameters into configuration objects
     * <p>
     * All commands parsable by the commandLineParser is of the form:
     * <code>
     * --mode --param1 -arg1 -arg2 --param2 --param3 -arg1
     * </code>
     * <i>mode</i> is what determines the {@link ConfigBuilder} used
     * <br>
     * Note that the first argument that the {@link ConfigBuilder} receives is always the mode argument.
     * The mode is also able to receive get arguments, however they wont affect choice of {@link ConfigBuilder}
     * <br>
     * Both commands and arguments are stripped of their <i>"-"</i> or <i>"--"</i> prefix
     *
     * @param input the string array to be parsed
     * @return null if input is null or empty - otherwise a {@link Configuration} created by the assigned {@link ConfigBuilder}
     */
    public T parse(String[] input) throws NoSuchBuilderException, WrongFormatException {
        String firstParam;
        if (input == null || input.length == 0) {
            firstParam = null;
        } else {
            firstParam = input[0];
        }

        ConfigBuilder<? extends T> configBuilder = getConfigBuilder(firstParam);

        doParse(input, configBuilder);

        return configBuilder.build();
    }

    /**
     * Method that must be implemented retrieving the correct configBuilder given first argument
     *
     * @param firstParam first argument from commandline
     * @return {@link ConfigBuilder} to be used in parsing
     * @throws NoSuchBuilderException when no {@link ConfigBuilder} is found
     */
    protected abstract ConfigBuilder<? extends T> getConfigBuilder(String firstParam) throws NoSuchBuilderException;

    private void doParse(String[] s, ConfigBuilder<?> configBuilder) throws WrongFormatException {
        if (s == null || s.length == 0) {
            return;
        }

        if (!s[0].contains("--")) {
            throw new WrongFormatException("First parameter must start with have \"--\" ");
        }

        String currCommand = s[0].replaceFirst("--", "");
        List<String> currCommands = new ArrayList<>();


        for (int i = 1; i < s.length; i++) {
            String curr = s[i];
            if (curr.startsWith("--")) {
                configBuilder.applyCommand(new Command(currCommand, currCommands));
                currCommand = curr.replaceFirst("--", "");
                currCommands = new ArrayList<>();
            } else if (curr.startsWith("-")) {
                currCommands.add(curr.replaceFirst("-", ""));
            } else {
                throw new WrongFormatException("Unable to parse argument: " + curr);
            }
        }
        configBuilder.applyCommand(new Command(currCommand, currCommands));
    }

    /**
     * @return terminal-formatted string describing configuration options
     */
    public abstract String help();

    public interface ConfigBuilder<T extends Configuration> {

        /**
         * Applies the given command to the builder
         *
         * @param command command to apply
         */
        void applyCommand(Command command);

        /**
         * Builds configuration from current state
         *
         * @return the configuration object
         */
        T build();

        /**
         * @return a proper terminal-formatted help message describing arguments
         * and their parameters for this configuration
         */
        String help();
    }

    public static class Command {
        private final String command;
        private final List<String> params;

        Command(String command, List<String> params) {
            this.command = command;
            this.params = params;
        }

        public String getCommand() {
            return command;
        }

        public List<String> getParams() {
            return params;
        }
    }


}
