package dk.eSoftware.commandLineParser.generalized;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class CommandValuePairTest {

    @Test
    public void toStringShouldContainValues() {
        // Arrange
        final String commandString = UUID.randomUUID().toString();
        final String value = UUID.randomUUID().toString();

        final CommandValuePair command = new CommandValuePair(commandString, value);

        // Act
        final String toString = command.toString();

        // Assert
        assertTrue(toString.contains(commandString));
        assertTrue(toString.contains(value));
    }
}
