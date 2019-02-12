package dk.eSoftware.commandLineParser;

/**
 * Thrown when a commandline argument is formatted incorrectly
 */
public class WrongFormatException extends Exception {

    WrongFormatException(String message) {
        super(message);
    }
}
