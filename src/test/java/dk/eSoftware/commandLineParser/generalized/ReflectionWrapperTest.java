package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.generalized.configuratinos.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReflectionWrapperTest {

    @Test
    public void shouldWriteValuesToConfigurationObject() {
        // Arrange
        final ReflectionWrapper<SimpleConfigurationClassBoxedTypes> wrapper = new ReflectionWrapper<>(
                new SimpleConfigurationClassBoxedTypes(),
                SimpleConfigurationClassBoxedTypes.class
        );

        // Act
        try {
            wrapper.writeField("stringVariable1", "SV1");
            wrapper.writeField("stringVariable2", "SV2");
            wrapper.writeField("booleanValue1", "TRUE");
            wrapper.writeField("booleanValue2", "false");
            wrapper.writeField("integerValue1", "1234");
            wrapper.writeField("integerValue2", "2345");
            wrapper.writeField("floatValue1", "2.4");
            wrapper.writeField("floatValue2", "5.4");
        } catch (ReflectionException e) {
            fail("Should not have thrown exception");
        }

        final SimpleConfigurationClassBoxedTypes object = wrapper.getObject();

        // Assert
        assertEquals("SV1", object.getStringVariable1());
        assertEquals("SV2", object.getStringVariable2());
        assertTrue(object.isBooleanValue1());
        assertFalse(object.isBooleanValue2());
        assertEquals(1234, object.getIntegerValue1().intValue());
        assertEquals(2345, object.getIntegerValue2().intValue());
        assertEquals(2.4, object.getFloatValue1(), .001f);
        assertEquals(5.4, object.getFloatValue2(), .001f);
    }

    @Test
    public void shouldWritePrimitiveValuesToConfigurationObject() {
        // Arrange
        final ReflectionWrapper<SimpleConfigurationClassPrimitiveTypes> wrapper = new ReflectionWrapper<>(
                new SimpleConfigurationClassPrimitiveTypes(),
                SimpleConfigurationClassPrimitiveTypes.class
        );

        // Act
        try {
            wrapper.writeField("booleanValue1", "TRUE");
            wrapper.writeField("booleanValue2", "false");
            wrapper.writeField("integerValue1", "1234");
            wrapper.writeField("integerValue2", "2345");
            wrapper.writeField("floatValue1", "2.4");
            wrapper.writeField("floatValue2", "5.4");
        } catch (ReflectionException e) {
            fail("Should not have thrown exception");
        }

        final SimpleConfigurationClassPrimitiveTypes object = wrapper.getObject();

        // Assert
        assertTrue(object.isBooleanValue1());
        assertFalse(object.isBooleanValue2());
        assertEquals(1234, object.getIntegerValue1());
        assertEquals(2345, object.getIntegerValue2());
        assertEquals(2.4, object.getFloatValue1(), .001f);
        assertEquals(5.4, object.getFloatValue2(), .001f);
    }

    @Test
    public void shouldThrowExceptionAsWritingToNonExistentField() {
        // Arrange
        final ReflectionWrapper<SimpleConfigurationClassPrimitiveTypes> wrapper = new ReflectionWrapper<>(
                new SimpleConfigurationClassPrimitiveTypes(),
                SimpleConfigurationClassPrimitiveTypes.class
        );

        // Act
        try {
            wrapper.writeField("nonExistent", "something");
            fail();
        } catch (ReflectionException expected) {
        }
    }

    @Test
    public void shouldThrowExceptionAsWritingUnsupportedPrimitive() {
        // Arrange
        final ReflectionWrapper<PrimitiveLongOnlyConfiguration> wrapper = new ReflectionWrapper<>(
                new PrimitiveLongOnlyConfiguration(),
                PrimitiveLongOnlyConfiguration.class
        );

        // Act
        try {
            wrapper.writeField("longValue", "12312412134");
            fail("Expected exception");
        } catch (ReflectionException expected) {
        }
    }

    @Test
    public void shouldThrowExceptionAsWritingUnsupportedComplex() {
        // Arrange
        final ReflectionWrapper<ComplexConfiguration> wrapper = new ReflectionWrapper<>(
                new ComplexConfiguration(),
                ComplexConfiguration.class
        );

        // Act
        try {
            wrapper.writeField("inner", "{\"booleanValue1\": true }");
            fail("Expected exception");
        } catch (ReflectionException expected) {
        }
    }

    @Test
    public void shouldLoadConfigurationWithEnum() {
        // Arrange
        final ReflectionWrapper<ConfigurationWithEnum> wrapper = new ReflectionWrapper<>(
                new ConfigurationWithEnum(),
                ConfigurationWithEnum.class
        );

        // Act
        try {
            wrapper.writeField("stringValue", "someValue");
            wrapper.writeField("enumValue", "VALUE2");
        } catch (ReflectionException e) {
            e.printStackTrace();
            fail("Should not throw exception");
        }

        final ConfigurationWithEnum object = wrapper.getObject();

        // Assert
        assertEquals("someValue", object.getStringValue());
        assertEquals(TestingEnum.VALUE2, object.getEnumValue());
    }
}
