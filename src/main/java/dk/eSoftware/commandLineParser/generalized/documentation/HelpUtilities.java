package dk.eSoftware.commandLineParser.generalized.documentation;

import dk.eSoftware.commandLineParser.Configuration;
import dk.eSoftware.commandLineParser.generalized.annotations.Help;

import java.lang.reflect.Field;

public class HelpUtilities {

    public static String generateHelpString(Class<? extends Configuration> subjectClass) {
        return subjectClass.getSimpleName() + ": \n" + generateFieldsString(1, subjectClass);
    }

    private static String generateFieldsString(int level, Class<? extends Configuration> subjectClass) {
        final StringBuilder sb = new StringBuilder();

        for (Field field : subjectClass.getDeclaredFields()) {
            for (int i = 0; i < level; i++) {
                sb.append("\t");
            }
            String helpString = getHelpString(field);
            sb.append(field.getName()).append("(").append(field.getType().getSimpleName()).append("): ").append(helpString).append("\n");

            if (Configuration.class.isAssignableFrom(field.getType())) {
                //noinspection unchecked
                sb.append(generateFieldsString(level + 1, (Class<? extends Configuration>) field.getType()));
            }

        }

        return sb.toString();
    }

    private static String getHelpString(Field field) {
        final Help annotation = field.getAnnotation(Help.class);
        return annotation != null ? annotation.helpString() : "";
    }
}
