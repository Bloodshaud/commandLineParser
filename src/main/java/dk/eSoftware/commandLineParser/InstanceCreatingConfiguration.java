package dk.eSoftware.commandLineParser;

public interface InstanceCreatingConfiguration<T> extends Configuration {

    /**
     * Produces a newly instantiated instance of the configured class, defined by <code>T</code>
     *
     * @return a newly created instance of the class this configures
     */
    T produceInstance();

}
