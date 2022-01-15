package dk.eSoftware.commandLineParser.generalized;

import java.lang.reflect.Field;

class ReflectionWrapper<T> {
    private final Class<T> innerClass;
    private final T object;


    public ReflectionWrapper(T configuration, Class<T> innerClass) {
        object = configuration;
        this.innerClass = innerClass;
    }

    void writeField(String fieldName, String serializedValue) throws ReflectionException {
        try {
            final Field field = innerClass.getDeclaredField(fieldName);
            boolean originallyAccessible = field.isAccessible();

            field.setAccessible(true);

            final Class<?> type = field.getType();

            if(type.isPrimitive()){
                writePrimitiveType(type, field, serializedValue);
            } else {
                writeComplex(field, type, serializedValue);
            }

            field.setAccessible(originallyAccessible);
        } catch (NoSuchFieldException | IllegalAccessException | NumberFormatException e) {
            throw new ReflectionException("Failed writing field: " + fieldName + " to class: " + innerClass.getSimpleName());
        }

    }

    private void writeComplex(Field field, Class<?> type, String value) throws IllegalAccessException, ReflectionException {
        if (String.class.equals(type)) {
            field.set(object, value);
        } else if (Boolean.class.equals(type)) {
            field.set(object, Boolean.parseBoolean(value));
        } else if (Integer.class.equals(type)) {
            field.set(object, Integer.parseInt(value));
        } else if (Float.class.equals(type)) {
            field.set(object, Float.parseFloat(value));
        } else {
            throw new ReflectionException("Failed writing field: " + field + " to class: " + innerClass.getSimpleName() + " type " + type + " was unsupported");
        }
    }

    private void writePrimitiveType(Class<?> type, Field field, String value) throws IllegalAccessException, ReflectionException {
        if (Boolean.TYPE.equals(type)) {
            field.setBoolean(object, Boolean.parseBoolean(value));
        } else if (Integer.TYPE.equals(type)) {
            field.setInt(object, Integer.parseInt(value));
        } else if (Float.TYPE.equals(type)) {
            field.setFloat(object, Float.parseFloat(value));
        } else {
            throw new ReflectionException("Failed writing field: " + value + " to class: " + innerClass.getSimpleName() + " type " + type + " was unsupported");
        }
    }

    public T getObject(){
        return object;
    }
}
