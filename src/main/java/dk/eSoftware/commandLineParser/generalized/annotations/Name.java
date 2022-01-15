package dk.eSoftware.commandLineParser.generalized.annotations;

import java.lang.annotation.*;

/**
 * Provides the parser with information used in generating a help-string to display to users
 */
@Documented()
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Name {

    /**
     * Determines the name used to reference this field
     * <br>
     * This binds tighter than name, meaning that having a field 'foo' named 'bar' and another defined as 'bar',
     * then 'bar' will also need a name, to be referenced
     * <br>
     * Note that multiple fields with the same name is not supported
     */
    String name() default "";

}
