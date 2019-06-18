; source file: block.t
; T version: 1.0
; compiled: Sat Jun 01 13:47:01 EDT 2019

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
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 42)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 42)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 42)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 43)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 43)
  ret void
}
