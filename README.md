[![Testsuite](https://github.com/Ernstsen/CommandLineParser/workflows/Test-suite/badge.svg)](https://github.com/Ernstsen/CommandLineParser/actions)
[![Test Coverage](https://codecov.io/gh/Ernstsen/CommandLineParser/branch/master/graph/badge.svg?token=NMG1R3WWZ1)](https://codecov.io/gh/Ernstsen/CommandLineParser)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/Ernstsen/CommandLineParser.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Ernstsen/CommandLineParser/context:java)
[![Maven Central](https://img.shields.io/maven-central/v/dk.e-software/commandLineParser)](https://mvnrepository.com/artifact/dk.e-software/commandLineParser)

# CommandLineParser

Simple parser for commandline arguments written in Java.

The parser is configured by implementing ``` ConfigBuilder ``` and mapping "modes" to ConfigBuilders. ConfigBuilders are
then to produce a ``` Configuration ``` from the given arguments, which are given.

# Using CommandLineParser

CommandLineParser can be retrieved with maven by adding the following to the POM:

```xml
<dependency>
    <groupId>dk.e-software</groupId>
    <artifactId>commandLineParser</artifactId>
    <version>2.1.0</version>
</dependency>

```

As newer versions are released they can be
found [here](https://mvnrepository.com/artifact/dk.e-software/commandLineParser/)

# Parser specifications

All commands parsable by the commandLineParser is of the form:

``` --mode --param1 -arg1 -arg2 --param2 --param3 -arg1 ```

<i>mode</i> is what determines which ConfigBuilder is used

Note that the first argument that the ConfigBuilder receives is always the mode argument. The mode is also able to
receive get arguments, however they wont affect choice of ConfigBuilder

Both commands and arguments are stripped of their <i>"-"</i> or <i>"--"</i> prefix

## Configurations

A ```CommandLineParser``` is configured by the mapping between commandline comments and ConfigBuilders.

## Generalized Parser

A generalized parser, ``GeneralConfigurationBuilder``, exists as a way to use this library in a generalized way.

To use this, create an implementation of the ``Configuration`` interface, and annotate the fields with name and help
information.

_Example_

```java
public class ExampleConfiguration implements Configuration {

    @Name(name = "val")
    @Help(helpString = "boolean value help-message")
    private boolean value;

    public boolean isValue() {
        return value;
    }
}
```

In this example the value ``booleanValue`` can be set in the following ways:

- ``--val=true``
- ``--val -true``
- ``--value=true``
- ``--value -true``

### Nesting

It is also possible to nest configurations.

_Example_

```java
public class ExampleConfiguration2 implements Configuration {

    @Name(name = "nested")
    @Help(helpString = "nested configuration")
    private ExampleConfiguration nested;

    public ExampleConfiguration2 getNested() {
        return nested;
    }

}
```

The ways to set the same boolean as in the example above is as follows:

- ``--nested.val=true``
- ``--nested.val -true``
- ``--nested.value=true``
- ``--nested.value -true``

# JavaDocs
JavaDocs can be found [here](https://ernstsen.github.io/CommandLineParser/)
