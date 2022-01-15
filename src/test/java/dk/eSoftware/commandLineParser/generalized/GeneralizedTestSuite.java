package dk.eSoftware.commandLineParser.generalized;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FieldValuePairTest.class,
        GeneralConfigurationBuilderTest.class,
        ReflectionWrapperTest.class,
})
public class GeneralizedTestSuite {
}
