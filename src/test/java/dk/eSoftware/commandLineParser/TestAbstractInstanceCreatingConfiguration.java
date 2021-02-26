package dk.eSoftware.commandLineParser;

import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class TestAbstractInstanceCreatingConfiguration {

    @Test
    public void testCreation() {
        Configuration conf = new Configuration();

        Configurable configurable = conf.produceInstance();

        assertSame("Configuration was not passed to configurable", conf, configurable.configuration);
    }

    @Test
    public void runtimeExceptionWhenNoConstructor(){
        AbstractInstanceCreatingConfiguration<String> conf = new StringConfiguration();

        try {
            conf.produceInstance();
            fail("Should have thrown exception");
        } catch (Exception expected) {
        }
    }

    private static class Configuration extends AbstractInstanceCreatingConfiguration<Configurable> {

        public Configuration() {
            super(Configurable.class);
        }
    }

    private static class StringConfiguration extends AbstractInstanceCreatingConfiguration<String> {

        public StringConfiguration() {
            super(String.class);
        }
    }

    private static class Configurable {
        Configuration configuration;

        public Configurable(Configuration configuration) {
            this.configuration = configuration;
        }
    }

}
