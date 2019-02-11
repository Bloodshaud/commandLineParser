package dk.esoftware.commandLineParser;

/**
 * Thrown when {@link CommandLineParser} were unable to find the specified {@link CommandLineParser.ConfigBuilder}
 */
class NoSuchBuilderException extends Exception {

    NoSuchBuilderException(String message) {
        super(message);
    }
}
