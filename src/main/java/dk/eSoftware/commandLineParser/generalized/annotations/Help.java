package dk.eSoftware.commandLineParser.generalized.annotations;

import java.lang.annotation.*;

/**
 * Provides the parser with information used in generating a help-string to display to users
 */
@Documented()
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Help {

    String helpString() default "";

}
