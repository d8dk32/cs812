; source file: simpleArray.t
; T version: 1.0
; compiled: Fri Jun 21 11:11:33 EDT 2019

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
  ; array declaration statement
  %x  = alloca %array$int*
  store %array$int* null, %array$int** %x
  ; array creation
  %temp1 = bitcast [1 x i8*]* @arrayVMT to i8*
  %temp2 = call i8* (i32, i8*, i32, i32) @t_rt_new_array( i32 4, i8* %temp1, i32 1, i32 10)
  %temp0 = bitcast i8* %temp2 to %array$int*
  ; store assigned value
  store %array$int* %temp0, %array$int** %x
  ; store assigned value
  store i32 5, i32* null
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 null)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 null)
  ret void
}
