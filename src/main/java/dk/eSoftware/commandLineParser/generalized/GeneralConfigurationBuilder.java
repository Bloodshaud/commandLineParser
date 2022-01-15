package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.CommandLineParser;
import dk.eSoftware.commandLineParser.Configuration;
import dk.eSoftware.commandLineParser.UnknownCommandException;

public class GeneralConfigurationBuilder<T extends Configuration> implements CommandLineParser.ConfigBuilder<T> {

    private final Class<T> configurationClass;
    private final ReflectionWrapper<T> wrapper;
    private final String helpString;

    public GeneralConfigurationBuilder(Class<T> configurationClass, String helpString) {
        this.configurationClass = configurationClass;
        this.helpString = helpString;
        try {
            T configuration = configurationClass.newInstance();

            wrapper = new ReflectionWrapper<>(configuration, configurationClass);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(
                    "Failed to instantiate new instance of class: " + configurationClass.getSimpleName()
                            + " make sure it has zero-args constructor");
        }
    }

    @Override
    public void applyCommand(CommandLineParser.Command command) throws UnknownCommandException {
        FieldValuePair fieldValuePair = convertToValuePair(command);

        try {
            wrapper.writeField(fieldValuePair.getField(), fieldValuePair.getValue());
        } catch (ReflectionException e) {
            throw new FieldMappingException("Failed writing command: " + fieldValuePair + " to class: " + configurationClass.getSimpleName());
        }
    }

    private FieldValuePair convertToValuePair(CommandLineParser.Command command) throws FieldMappingException {
        final String commandString = command.getCommand();

        if (commandString.contains("=")) {
            if (command.getParams().isEmpty()) {
                final String[] split = commandString.split("=");

                if (split.length == 2) {
                    return new FieldValuePair(split[0], split[1]);
                } else {
                    throw new FieldMappingException(
                            "When using '=' there must only be a single occurrence"
                    );
                }

            } else {
                throw new FieldMappingException(
                        "When using '=' a value cannot be specified with '-value' as well"
                );
            }
        } else {
            if (command.getParams().size() == 1) {
                return new FieldValuePair(commandString, command.getParams().get(0));
            } else {
                throw new FieldMappingException("Failed to map command " + commandString +
                        " to a field, must have exactly one specified value");

            }
        }
    }

    @Override
    public T build() {
        return wrapper.getObject();
    }

    @Override
    public String help() {
        return helpString;
    }
}
