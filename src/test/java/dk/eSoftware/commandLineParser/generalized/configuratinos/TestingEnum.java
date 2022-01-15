package dk.eSoftware.commandLineParser.generalized.configuratinos;

public enum TestingEnum {
    VALUE1("One", 1),
    VALUE2("TWO", 2),
    VALUE3("three", 3);

    private final String string;
    private final int integer;

    TestingEnum(String string, int integer) {
        this.string = string;
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public int getInteger() {
        return integer;
    }
}
