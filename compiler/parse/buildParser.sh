#!/bin/bash

java -ea -jar ../../build/lib/antlr.jar T.g4 -no-listener -package tc.compiler.parse

