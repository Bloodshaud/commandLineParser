# commandLineParser
Simple parser for commandline arguments written in Java.

The parser is configured by implementing ``` ConfigBuilder ``` and mapping "modes" to ConfigBuilders. 
ConfigBuilders are then to produce a ``` Configuration ``` from the given arguments, which are given.

# Parser specifications
All commands parsable by the commandLineParser is of the form:

``` --mode --param1 -arg1 -arg2 --param2 --param3 -arg1 ```

<i>mode</i> is what determines which ConfigBuilder is used

Note that the first argument that the ConfigBuilder receives is always the mode argument.
The mode is also able to receive get arguments, however they wont affect choice of ConfigBuilder


Both commands and arguments are stripped of their <i>"-"</i> or <i>"--"</i> prefix

## Configurations
A ```CommandLineParser``` is configured by the mapping between commandline comments and ConfigBuilders. 
 