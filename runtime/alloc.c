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
void 

// deallocate space for an object or an array
void t_rt_dealloc(void *ref)
{
  free(ref);
}

