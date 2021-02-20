package dk.eSoftware.commandLineParser;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestMultiParser {

    @Test
    public void testMultiParser() {

        final HashMap<String, CommandLineParser.ConfigBuilder> map = new HashMap<>();

        map.put("A", new TestConfigBuilder("A"));
        map.put("B", new TestConfigBuilder("B"));

        final MultiParser multiParser = new MultiParser(map);
        try {
            final CommandLineParser.ConfigBuilder parserA = multiParser.getConfigBuilder("A");
            assertEquals("Wrong parser returned", "A", parserA.help());
            final CommandLineParser.ConfigBuilder parserB = multiParser.getConfigBuilder("B");
            assertEquals("Wrong parser returned", "B", parserB.help());

            assertEquals("Wrong help string", "Help information compiled by CommandLineParser:\nA\nB\n", multiParser.help());

        } catch (NoSuchBuilderException e) {
            fail("An exception was thrown");
        }

        try {
            final CommandLineParser.ConfigBuilder c = multiParser.getConfigBuilder("C");
            fail("Should have thrown exception");
        } catch (NoSuchBuilderException expected) {
        }
    }


    private static class TestConfigBuilder implements CommandLineParser.ConfigBuilder {
        private final String id;

        public TestConfigBuilder(String id) {
            this.id = id;
        }

        @Override
        public void applyCommand(CommandLineParser.Command command) {


        }

        @Override
        public Configuration build() {
            return null;
        }

        @Override
        public String help() {
            return id;
        }
    }
}