#
# Makefile for the T runtime library
#

CC = gcc

CFLAGS = -g -c -Wall

runtime.a: alloc.o print.o
	ar crs runtime.a alloc.o print.o

alloc.o: alloc.c
	$(CC) $(CFLAGS) alloc.c

print.o: print.c
	$(CC) $(CFLAGS) print.c

clean:
	rm -f alloc.o print.o runtime.a

