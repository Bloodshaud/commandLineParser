# commandLineParser
Simple parser for commandline arguments written in Java.

The parser is configured by implementing ``` ConfigBuilder ``` and mapping "modes" to ConfigBuilders. 
ConfigBuilders are then to produce a ``` Configuration ``` from the given arguments, which are given.
