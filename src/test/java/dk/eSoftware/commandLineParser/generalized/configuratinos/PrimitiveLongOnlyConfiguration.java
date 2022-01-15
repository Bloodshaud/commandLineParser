package dk.eSoftware.commandLineParser.generalized.configuratinos;

import dk.eSoftware.commandLineParser.Configuration;

@SuppressWarnings("unused")
public class PrimitiveLongOnlyConfiguration implements Configuration {
    private long longValue;

    public PrimitiveLongOnlyConfiguration() {
    }

    public long getLongValue() {
        return longValue;
    }
}
