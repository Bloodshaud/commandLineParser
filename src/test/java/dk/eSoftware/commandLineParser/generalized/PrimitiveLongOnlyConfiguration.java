package dk.eSoftware.commandLineParser.generalized;

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
