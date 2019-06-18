#!/bin/bash

#
# tar up the tc files
#
# it places the created tar file in the directory that contains the
# tc directory (the root of the distribution)
#
# this script needs to be run from the directory where it is stored,
# which is tc/bin
#

# delete any class files that were left in the compiler directory tree
rm -f ../compiler/*.class ../compiler/parse/*.class ../compiler/tree/*.class ../compiler/tree/visit/*.class ../compiler/tree/type/*.class

cd ../..

/bin/rm -f tc.tar
pushd tc/compiler/parse >&/dev/null
make -silent clean
popd >&/dev/null
pushd tc/runtime >&/dev/null
make -silent clean
popd >&/dev/null
tar cvf tc.tar ./tc/bin ./tc/README ./tc/scripts ./tc/compiler ./tc/runtime ./tc/tests

cd tc/bin

