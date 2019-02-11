package dk.esoftware.commandLineParser;

/**
 * Thrown when a commandline argument is formatted incorrectly
 */
class WrongFormatException extends RuntimeException {

    WrongFormatException(String message) {
        super(message);
    }
}
