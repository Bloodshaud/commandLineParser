package dk.eSoftware.commandLineParser.generalized.annotations;

import java.lang.annotation.*;
import java.util.Map;

/**
 * Provides the system with the information necessary to write data into maps
 */
@Documented()
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MapConfiguration {

    /**
     * Determines the type of keys in the map
     */
    Class<?> keyClass();

    /**
     * Determines the type of values in the map
     */
    Class<?> valueClass();

    /**
     * Determines the {@link Map} implementation used
     */
    @SuppressWarnings("rawtypes") Class<? extends Map> mapClass();

}
