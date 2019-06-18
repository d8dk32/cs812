#!/bin/bash

# do regression testing of tc
#
# if nothing printed, then all tests passed
#
# this script needs to be run from the directory where it is stored,
# which is tc/bin
#

cd ../tests

# test programs with compile-time errors
for prog in badIntegerLiteral errorCascade
do
  ../build/bin/tc $prog.t >&$prog.err
  cmp -s $prog.err results/$prog.err
  if [ $? -ne 0 ]
  then echo $prog.t failed
  fi
  rm -f $prog.err
done

# test programs that execute
for prog in addConstants first integerLiteral negateConstants parens
do
  ../build/bin/tc -o $prog $prog.t
  ./$prog >&$prog.out
  cmp -s $prog.out results/$prog.out
  if [ $? -ne 0 ]
  then echo $prog.t failed
  fi
  rm -f $prog.out $prog
done

cd ../bin

