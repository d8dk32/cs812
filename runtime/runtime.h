//
// T runtime library
//

#include <inttypes.h>

// memory allocation
void t_rt_alloc_init(void);
void *t_rt_alloc(int64_t n, int32_t lineNumber);
void t_rt_dealloc(void*);
void *t_rt_new_array(int32_t lineNumber, int8_t* runtimeType, int32_t numDims, int32_t numDimsSpec, ...);

// print
void t_rt_print_int(int32_t value);
void t_rt_print_divide_by_zero_error(int32_t line);

