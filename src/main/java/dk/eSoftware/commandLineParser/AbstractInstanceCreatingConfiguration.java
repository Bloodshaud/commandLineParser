package dk.eSoftware.commandLineParser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Implementation of {@link InstanceCreatingConfiguration} with implementation of <code>produceInstance</code>
 * creating new instance using single-arg constructor, taking only a configuration, using reflection
 *
 * @param <T> the configurable class. Must have constructor with 1 arg being T
 */
public abstract class AbstractInstanceCreatingConfiguration<T> implements InstanceCreatingConfiguration<T> {

    private final Class<T> clazz;

    /**
     * @param clazz class object of <code>T</code>
     */
    public AbstractInstanceCreatingConfiguration(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T produceInstance() {
        try {
            Constructor<T> constructor = clazz.getConstructor(getClass());
            return constructor.newInstance(this);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create new instance of " + clazz, e);
        }
    }
}
