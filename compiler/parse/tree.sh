#!/bin/bash

# script for invoking parser so that parse tree is dumped to stdout
#
# file to be parsed should be provided as stdin

# disable the JAVA_TOOL_OPTIONS environment variable that exists on agate
unset JAVA_TOOL_OPTIONS

make -s TParser.class
../../build/bin/grun T compilationUnit -tree $@

