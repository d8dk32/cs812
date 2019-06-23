//
// T runtime library
//

#include <inttypes.h>

// memory allocation
void t_rt_alloc_init(void);
void *t_rt_alloc(int64_t n, int32_t lineNumber);
void t_rt_dealloc(void*);
void *t_rt_new_intarray(int32_t lineNumber, void* runtimeType, int32_t numDims, int32_t lengthSpecified);
void *t_rt_new_refarray(int32_t lineNumber, void* runtimeType, int32_t numDims, int32_t lengthSpecified);

// print
void t_rt_print_int(int32_t value);
void t_rt_print_divide_by_zero_error(int32_t line);
void t_rt_print_array_index_out_of_bounds_error(int32_t line);
void t_rt_print_null_ref_error(int32_t line);

//array structs
typedef struct IntArray {
    void *notSure;
    void *runtimeType;
    int32_t numDims;
    int32_t length;
    int32_t array[0];

} intArray;

typedef struct RefArray {
    void *notSure;
    void *runtimeType;
    int32_t numDims;
    int32_t length;
    int8_t *array[0];

} refArray;
