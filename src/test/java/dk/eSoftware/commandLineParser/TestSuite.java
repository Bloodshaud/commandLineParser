package dk.eSoftware.commandLineParser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestMultiParser.class,
        TestSingletonCommandLineParser.class
})
public class TestSuite {
}
