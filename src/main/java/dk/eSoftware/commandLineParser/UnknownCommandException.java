package dk.eSoftware.commandLineParser;

/**
 * Exception used to inform that a specified command is not known to the system
 */
public class UnknownCommandException extends Exception {

    public UnknownCommandException(String message) {
        super(message);
    }
}
