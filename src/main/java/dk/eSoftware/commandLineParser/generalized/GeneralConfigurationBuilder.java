package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.CommandLineParser;
import dk.eSoftware.commandLineParser.Configuration;
import dk.eSoftware.commandLineParser.UnknownCommandException;

import java.lang.reflect.Field;

public class GeneralConfigurationBuilder<T extends Configuration> implements CommandLineParser.ConfigBuilder<T> {

    private final Class<T> configurationClass;
    private final T configuration;
    private final String helpString;

    public GeneralConfigurationBuilder(Class<T> configurationClass, String helpString) {
        this.configurationClass = configurationClass;
        this.helpString = helpString;
        try {
            configuration = configurationClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(
                    "Failed to instantiate new instance of class: " + configurationClass.getSimpleName()
                            + " make sure it has zero-args constructor");
        }
    }

    @Override
    public void applyCommand(CommandLineParser.Command command) throws UnknownCommandException {
        CommandValuePair commandValuePair = convertToValuePair(command);

        writeToConfiguration(commandValuePair);
    }

    private void writeToConfiguration(CommandValuePair commandValuePair) throws FieldMappingException {
        try {
            final Field field = configurationClass.getDeclaredField(commandValuePair.getCommand());
            boolean originallyAccessible = field.isAccessible();

            field.setAccessible(true);

            final Class<?> type = field.getType();

            if(type.isPrimitive()){
                writePrimitiveType(type, field, commandValuePair);
            } else {
                if (String.class.equals(type)) {
                    field.set(configuration, commandValuePair.getValue());
                } else if (Boolean.class.equals(type)) {
                    field.set(configuration, Boolean.parseBoolean(commandValuePair.getValue()));
                } else if (Integer.class.equals(type)) {
                    field.set(configuration, Integer.parseInt(commandValuePair.getValue()));
                } else if (Float.class.equals(type)) {
                    field.set(configuration, Float.parseFloat(commandValuePair.getValue()));
                } else {
                    throw new FieldMappingException("Failed writing command: " + commandValuePair + " to class: " + configurationClass.getSimpleName() + " type " + type + " was unsupported");
                }
            }

            field.setAccessible(originallyAccessible);
        } catch (NoSuchFieldException | IllegalAccessException | NumberFormatException e) {
            throw new FieldMappingException("Failed writing command: " + commandValuePair + " to class: " + configurationClass.getSimpleName());
        }
    }

    private void writePrimitiveType(Class<?> type, Field field, CommandValuePair commandValuePair) throws IllegalAccessException, FieldMappingException {
        if (Boolean.TYPE.equals(type)) {
            field.setBoolean(configuration, Boolean.parseBoolean(commandValuePair.getValue()));
        } else if (Integer.TYPE.equals(type)) {
            field.setInt(configuration, Integer.parseInt(commandValuePair.getValue()));
        } else if (Float.TYPE.equals(type)) {
            field.setFloat(configuration, Float.parseFloat(commandValuePair.getValue()));
        } else {
            throw new FieldMappingException("Failed writing command: " + commandValuePair + " to class: " + configurationClass.getSimpleName() + " type " + type + " was unsupported");
        }
    }

    private CommandValuePair convertToValuePair(CommandLineParser.Command command) throws FieldMappingException {
        final String commandString = command.getCommand();

        if (commandString.contains("=") && command.getParams().isEmpty()) {
            final String[] split = commandString.split("=");

            if (split.length == 2) {
                return new CommandValuePair(split[0], split[1]);
            } else {
                throw new FieldMappingException(
                        "When using '=' there must only be a single occurrence"
                );
            }

        } else if (!commandString.contains("=") && command.getParams().size() == 1) {
            return new CommandValuePair(commandString, command.getParams().get(0));
        } else {
            throw new FieldMappingException("Failed to map command " + commandString +
                    " to a field, must have exactly one specified value");
        }
    }

    @Override
    public T build() {
        return configuration;
    }

    @Override
    public String help() {
        return helpString;
    }
}
