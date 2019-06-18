#!/bin/bash

#
# build the T compiler and runtime library
#
# this script needs to be run from the directory where it is stored,
# which is tc/bin
#

# disable the JAVA_TOOL_OPTIONS environment variable that exists on agate
unset JAVA_TOOL_OPTIONS

# delete any class files that were left in the compiler directory tree
rm -f ../compiler/*.class ../compiler/parse/*.class ../compiler/tree/*.class ../compiler/tree/visit/*.class ../compiler/tree/type/*.class

# first make sure the generated parser is up to date
cd ..
cd compiler/parse
make
cd ../..

# now compile the compiler
javac -g  -cp ..:build/lib/antlr.jar -d build/packages -Xlint:unchecked compiler/Main.java

# now run javadoc
javadoc -classpath build/packages:build/lib/antlr.jar -d build/html -use -package -quiet -doctitle "tc" compiler/*.java compiler/parse/TreeBuilder.java compiler/tree/*.java compiler/tree/visit/*.java compiler/tree/type/*.java

# now build the runtime library
cd runtime
make
cp runtime.a ../build/lib
cd ..

cd bin
