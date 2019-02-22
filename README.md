# CommandLineParser
Simple parser for commandline arguments written in Java.

The parser is configured by implementing ``` ConfigBuilder ``` and mapping "modes" to ConfigBuilders. 
ConfigBuilders are then to produce a ``` Configuration ``` from the given arguments, which are given.

# Using CommandLineParser
CommandLineParser can be retrieved with maven by adding a maven m2Compatible repository with the url 
`http://e-software.dk:8088/repository/internal/`
And adding the following to the POM:
```
<dependency>
  <groupId>dk.eSoftware</groupId>
  <artifactId>commandLineParser</artifactId>
  <version>1.1.1</version>
</dependency>
```
As newer versions are released they can be found [here](http://e-software.dk:8088/#artifact/dk.eSoftware/commandLineParser)

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
Javadocs for the library can be found here [JavaDocs](https://bloodshaud.github.io/CommandLineParser/)
