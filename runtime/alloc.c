//
// functions for allocating memory
//

#include <stdlib.h>
#include <stdio.h>
#include "runtime.h"

void t_rt_alloc_init(void)
{
  // using calloc for now so nothing to be done here
}

// allocate n bytes and zero them out
// second parameter gives location in the source code
void *t_rt_alloc(int64_t n, int32_t lineNumber)
{
  // just use calloc for now
  void *ret = calloc(n, 1);
  if (ret == NULL)
  {
    fprintf(stderr, "ERROR: Out of memory (line %d).\n", lineNumber);
    exit(-1);
  }
  return ret;
}

//allocate memory for array
void *t_rt_new_intarray(int32_t lineNumber, void* runtimeType, int32_t numDims, int32_t lengthSpecified)
{
  if(lengthSpecified < 0)
  {
    fprintf(stderr, "ERROR: negative array length (line %d).\n", lineNumber);
    exit(-1);
  }
  intArray *ret = t_rt_alloc(sizeof(intArray) + sizeof(int32_t) * lengthSpecified, lineNumber);
  
  ret->runtimeType = runtimeType;
  ret->numDims = numDims;
  ret->length = lengthSpecified;

  return ret;
}

void *t_rt_new_refarray(int32_t lineNumber, void* runtimeType, int32_t numDims, int32_t lengthSpecified)
{
  if(lengthSpecified < 0)
  {
    fprintf(stderr, "ERROR: negative array length (line %d).\n", lineNumber);
    exit(-1);
  }

  refArray *ret = (refArray *) t_rt_alloc(sizeof(refArray) + sizeof(int8_t *) * lengthSpecified, lineNumber);

  ret->runtimeType = runtimeType;
  ret->numDims = numDims;
  ret->length = lengthSpecified;

  return ret;
}


// deallocate space for an object or an array
void t_rt_dealloc(void *ref)
{
  free(ref);
}

