package dk.esoftware.commandLineParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class CommandLineParser {
    private Map<String, ConfigBuilder> mapping;

    /**
     * Constructor for a {@link CommandLineParser}
     *
     * @param mapping a map between modes and configbuilders.
     */
    public CommandLineParser(Map<String, ConfigBuilder> mapping) {
        this.mapping = mapping;
    }

    /**
     * Utility for parsing commandline parameters into configuration objects
     * <p>
     * All commands parsable by the commandLineParser is of the form:
     * <code>
     * --mode --param1 -arg1 -arg2 --param2 --param3 -arg1
     * </code>
     * <i>mode</i> is what determines the {@link ConfigBuilder} used
     * <br/>
     * Note that the first argument that the {@link ConfigBuilder} receives is always the mode argument.
     * The mode is also able to receive get arguments, however they wont affect choice of {@link ConfigBuilder}
     * <br/>
     * Both commands and arguments are stripped of their <i>"-"</i> or <i>"--"</i> prefix
     *
     * @param input the string to be parsed
     * @return null if input is null or empty - otherwise a {@link Configuration} created by the assigned {@link ConfigBuilder}
     */
    public Configuration parse(String input) throws NoSuchBuilderException {
        if (input == null || input.isEmpty()) {
            return null;
        }

        String[] s = input.split(" ");
        ConfigBuilder configBuilder = mapping.get(s[0]);

        if (configBuilder == null) {
            throw new NoSuchBuilderException("Unable to find ConfigBuilder from mode: \"" + s[0] + "\"");
        }

        doParse(s, configBuilder);

        return configBuilder.build();
    }

    private void doParse(String[] s, ConfigBuilder configBuilder) {
        String currCommand = s[0];
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

    public static class Command {
        private String command;
        private List<String> params;

        public Command(String command, List<String> params) {
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

    public interface ConfigBuilder {

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
        Configuration build();
    }


}
