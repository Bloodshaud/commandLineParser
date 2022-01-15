package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.generalized.documentation.HelpUtilitiesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HelpUtilitiesTest.class,
        FieldValuePairTest.class,
        GeneralConfigurationBuilderTest.class,
        ReflectionWrapperTest.class,
})
public class GeneralizedTestSuite {
}
