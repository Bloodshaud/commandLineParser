package dk.eSoftware.commandLineParser.generalized;

import dk.eSoftware.commandLineParser.generalized.annotations.Name;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

class ReflectionWrapper<T> {
    private final Class<T> innerClass;
    private final T object;
    private final Map<Class<?>, Map<String, Field>> fieldMaps;


    public ReflectionWrapper(T configuration, Class<T> innerClass) {
        object = configuration;
        this.innerClass = innerClass;

        fieldMaps = new HashMap<>();

        fieldMaps.put(innerClass, buildNameMap(innerClass));
    }

    private Map<String, Field> buildNameMap(Class<?> innerClass) {
        final HashMap<String, Field> result = new HashMap<>();
        for (Field field : innerClass.getDeclaredFields()) {

            result.putIfAbsent(field.getName(), field);

            final Name nameAnnotation = field.getAnnotation(Name.class);
            if (nameAnnotation != null) {
                result.put(nameAnnotation.name(), field);
            }

        }
        return result;
    }

    void writeField(String fieldName, String serializedValue) throws ReflectionException {
        writeFieldInner(fieldName, serializedValue, innerClass, object);
    }

    private void writeFieldInner(String fieldName, String serializedValue, Class<?> objectClass, Object object) throws ReflectionException {
        try {
            if (fieldName.contains(".")) {
                final String[] split = fieldName.split("\\.", 2);
                final String currentFieldName = split[0];

                final Field field = getField(objectClass, currentFieldName);
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
                final Field field = getField(objectClass, fieldName);
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

    private Field getField(Class<?> objectClass, String currentFieldName) throws NoSuchFieldException {
        final Field field = fieldMaps.computeIfAbsent(objectClass, this::buildNameMap).get(currentFieldName);

        if (field == null) {
            throw new NoSuchFieldException("Found no field with name: '" + currentFieldName + "' in object: '" + objectClass.getSimpleName() + "'");
        }

        return field;
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
