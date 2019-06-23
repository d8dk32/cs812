; source file: assign.t
; T version: 1.0
; compiled: Thu Jun 20 20:50:23 EDT 2019

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)
declare void @t_rt_print_divide_by_zero_error(i32)
declare i8* @t_rt_new_array(i32, i8*, i32, i32)

; VMTs
@arrayVMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@intVMT = global [1 x i8*] [i8* null]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %i = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %i
  ; store assigned value
  store i32 42, i32* %i
  ; load value from variable
  %temp0 = load i32, i32* %i
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp0)
  ret void
}
