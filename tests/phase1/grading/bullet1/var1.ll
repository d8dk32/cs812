; source file: var1.t
; T version: 1.0
; compiled: Fri May 31 07:45:32 EDT 2019

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)

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
  %xPtr = alloca i32
  ; store result
  store i32 0, i32* %xPtr
  ; load value from variable
  %temp0 = load i32, i32* %xPtr
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp0)
  ret void
}
