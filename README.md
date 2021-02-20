[![Testsuite](https://github.com/Ernstsen/CommandLineParser/workflows/Test-suite/badge.svg)](https://github.com/Ernstsen/CommandLineParser/actions) 
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/Ernstsen/CommandLineParser.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Ernstsen/CommandLineParser/context:java)
[![Coverage Status](https://coveralls.io/repos/github/Ernstsen/CommandLineParser/badge.svg?branch=master)](https://coveralls.io/github/Ernstsen/CommandLineParser?branch=master)
# CommandLineParser
Simple parser for commandline arguments written in Java.

The parser is configured by implementing ``` ConfigBuilder ``` and mapping "modes" to ConfigBuilders. 
ConfigBuilders are then to produce a ``` Configuration ``` from the given arguments, which are given.

# Using CommandLineParser
CommandLineParser can be retrieved with maven by adding the following to the POM:
```
<dependency>
    <groupId>dk.e-software</groupId>
    <artifactId>commandLineParser</artifactId>
    <version>1.2.0</version>
</dependency>

```
As newer versions are released they can be found [here](https://mvnrepository.com/artifact/dk.e-software/commandLineParser/1.2.0)

# Parser specifications
All commands parsable by the commandLineParser is of the form:

``` --mode --param1 -arg1 -arg2 --param2 --param3 -arg1 ```

<i>mode</i> is what determines which ConfigBuilder is used

Note that the first argument that the ConfigBuilder receives is always the mode argument.
The mode is also able to receive get arguments, however they wont affect choice of ConfigBuilder


Both commands and arguments are stripped of their <i>"-"</i> or <i>"--"</i> prefix

## Configurations
A ```CommandLineParser``` is configured by the mapping between commandline comments and ConfigBuilders. 
 
## JavaDocs
Javadocs for the library can be found here [JavaDocs](https://ernstsen.github.io/CommandLineParser/)
