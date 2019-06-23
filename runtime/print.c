// routine to support the out statement

#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>

// print a 32-bit integer to stdout in decimal followed by a newline
void t_rt_print_int(int32_t value)
{
  printf("%" PRId32 "\n", value);
}

//when a divide-by-zero error occurs, print the line on which it happened and crash out
void t_rt_print_divide_by_zero_error(int32_t line)
{
  printf("divide by zero error occured at line %" PRId32 "\n", line);
  exit(-1);
}

void t_rt_print_array_index_out_of_bounds_error(int32_t line)
{
  printf("array index out of bounds error occured at line %" PRId32 "\n", line);
  exit(-1);
}

void t_rt_print_null_ref_error(int32_t line)
{
  printf("null reference error occured at line %" PRId32 "\n", line);
  exit(-1); 
}
