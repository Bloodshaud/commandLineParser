package dk.eSoftware.commandLineParser;

import dk.eSoftware.commandLineParser.generalized.GeneralizedTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GeneralizedTestSuite.class,
        TestAbstractInstanceCreatingConfiguration.class,
        TestMultiParser.class,
        TestSingletonCommandLineParser.class
})
public class TestSuite {
}
