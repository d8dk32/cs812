; source file: arrayDecl.t
; T version: 1.0
; compiled: Fri Jun 14 20:11:47 EDT 2019

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)
declare void @t_rt_print_divide_by_zero_error(i32)

; VMTs
@arrayVMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@intVMT = global [1 x i8*] [i8* null]
@Object$VMT = global [1 x i8*] [i8* null]

; class object types
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %aPtr = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %aPtr
  ; declaration statement
  %bPtr = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %bPtr
  ; declaration statement
  %cPtr = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %cPtr
  ; declaration statement
  %a2Ptr = alloca int
  ; store 0 on declaration
  store int 0, int* %a2Ptr
  ; declaration statement
  %b2Ptr = alloca int
  ; store 0 on declaration
  store int 0, int* %b2Ptr
  ; declaration statement
  %c2Ptr = alloca int
  ; store 0 on declaration
  store int 0, int* %c2Ptr
  ; declaration statement
  %b3Ptr = alloca int
  ; store 0 on declaration
  store int 0, int* %b3Ptr
  ; declaration statement
  %c3Ptr = alloca int
  ; store 0 on declaration
  store int 0, int* %c3Ptr
  ; declaration statement
  %d3Ptr = alloca int
  ; store 0 on declaration
  store int 0, int* %d3Ptr
  ret void
}
