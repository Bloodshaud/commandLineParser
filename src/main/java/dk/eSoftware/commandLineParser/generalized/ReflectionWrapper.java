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
        writeFieldInner(fieldName, serializedValue, innerClass, object);
    }

    private void writeFieldInner(String fieldName, String serializedValue, Class<?> objectClass, Object object) throws ReflectionException {
        try {
            if (fieldName.contains(".")) {
                final String[] split = fieldName.split("\\.", 2);
                final String currentField = split[0];

                final Field field = objectClass.getDeclaredField(currentField);
                boolean originallyAccessible = field.isAccessible();
                field.setAccessible(true);

                Object fieldObject = field.get(object);
                final Class<?> fieldType = field.getType();
                if (fieldObject == null) {
                    fieldObject = fieldType.newInstance();
                    field.set(object, fieldObject);
                }

                writeFieldInner(split[1], serializedValue, fieldType, fieldObject);

                field.setAccessible(originallyAccessible);
            } else {
                final Field field = objectClass.getDeclaredField(fieldName);
                boolean originallyAccessible = field.isAccessible();

                field.setAccessible(true);

                final Class<?> type = field.getType();

                if (type.isPrimitive()) {
                    writePrimitiveType(type, object, field, serializedValue);
                } else {
                    writeComplex(type, object, field, serializedValue);
                }

                field.setAccessible(originallyAccessible);
            }
        } catch (NoSuchFieldException | IllegalAccessException | NumberFormatException e) {
            throw new ReflectionException("Failed writing field: " + fieldName + " to class: " + objectClass.getSimpleName());
        } catch (InstantiationException e) {
            throw new ReflectionException("Failed creating a new instance of field type - ensure that they all have zero-args constructors");
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private <I> void writeComplex(Class<?> type, I object, Field field, String value) throws IllegalAccessException, ReflectionException {
        if (String.class.equals(type)) {
            field.set(object, value);
        } else if (Boolean.class.equals(type)) {
            field.set(object, Boolean.parseBoolean(value));
        } else if (Integer.class.equals(type)) {
            field.set(object, Integer.parseInt(value));
        } else if (Float.class.equals(type)) {
            field.set(object, Float.parseFloat(value));
        } else if (type.isEnum()) {
            field.set(object, Enum.valueOf((Class<Enum>) type, value));
        } else {
            throw new ReflectionException("Failed writing field: " + field + " to class: " + type.getSimpleName()
                    + " type " + type + " was unsupported");
        }
    }

    private <I> void writePrimitiveType(Class<?> type, I object, Field field, String value) throws IllegalAccessException, ReflectionException {
        if (Boolean.TYPE.equals(type)) {
            field.setBoolean(object, Boolean.parseBoolean(value));
        } else if (Integer.TYPE.equals(type)) {
            field.setInt(object, Integer.parseInt(value));
        } else if (Float.TYPE.equals(type)) {
            field.setFloat(object, Float.parseFloat(value));
        } else {
            throw new ReflectionException("Failed writing field: " + value + " to class: " + type.getSimpleName() + " type " + type + " was unsupported");
        }
    }

    public T getObject() {
        return object;
    }
}
