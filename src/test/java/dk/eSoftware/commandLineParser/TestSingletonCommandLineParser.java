package dk.eSoftware.commandLineParser;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSingletonCommandLineParser {
    private Builder builder;
    private SingletonCommandLineParser<TestConfiguration> parser;

    @Before
    public void setUp() {
        builder = new Builder();
        parser = new SingletonCommandLineParser<>(builder);
    }

    @Test
    public void parse() {
        try {
            TestConfiguration parse = parser.parse("--name -param1 -param2".split(" "));

            String val = parse.getVals();

            assertEquals("Unexpected configuration", "nameparam1param2", val);

        } catch (NoSuchBuilderException | WrongFormatException | UnknownCommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getConfigBuilder() {
        assertSame("Did not return same builder as it was given", builder, parser.getConfigBuilder(""));

    }

    @Test
    public void help() {
        assertEquals("Wrong help string", builder.help(), parser.help());
    }

    @Test
    public void noExceptionOnNull() {
        try {
            parser.parse(null);
            parser.parse(new String[0]);
        } catch (NoSuchBuilderException | WrongFormatException | UnknownCommandException e) {
            fail("No exception should have been thrown");
        }
    }

    @Test
    public void wrongFormatExceptionOnWrongFormat() {
        try {
            parser.parse(new String[]{"wrongFormat"});
            fail("should have thrown exception");
        } catch (WrongFormatException expected) {
        } catch (NoSuchBuilderException | UnknownCommandException e) {
            fail("Did not expect unknownCommandException!");
        }
    }

    @Test
    public void wrongFormatExceptionOnWrongFormatSecondArg() {
        try {
            parser.parse("--self wrongFormat".split(" "));
            fail("should have thrown exception");
        } catch (WrongFormatException expected) {
        } catch (NoSuchBuilderException | UnknownCommandException e) {
            fail("Did not expect unknownCommandException!");
        }
    }

    @Test
    public void correctlyHandlesTwoComplexArgs() {
        try {
            TestConfiguration parse = parser.parse("--name -param1 -param2 --name2 -param3 -param4".split(" "));

            String val = parse.getVals();

            assertEquals("Unexpected configuration", "nameparam1param2,name2param3param4", val);
        } catch (NoSuchBuilderException | WrongFormatException | UnknownCommandException e) {
            fail("No exception should have been thrown");
        }
    }


    private static class Builder implements CommandLineParser.ConfigBuilder<TestConfiguration> {
        private final List<String> vals = new ArrayList<>();

        @Override
        public void applyCommand(CommandLineParser.Command command) {
            vals.add(command.getCommand() + String.join("", command.getParams()));
        }

        @Override
        public TestConfiguration build() {
            return new TestConfiguration(String.join(",", vals));
        }

        @Override
        public String help() {
            return "HELP!";
        }
    }

    private static class TestConfiguration implements Configuration {
        private final String val;

        public TestConfiguration(String val) {
            this.val = val;
        }

        public String getVals() {
            return val;
        }
    }
}
