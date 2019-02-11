package dk.esoftware.commandLineParser;

/**
 * Thrown when a commandline argument is formatted incorrectly
 */
class WrongFormatException extends Exception {

    WrongFormatException(String message) {
        super(message);
    }
}
