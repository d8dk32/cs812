; source file: arrayDecl.t
; T version: 1.0
; compiled: Wed Jun 19 20:56:01 EDT 2019

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
  %a  = alloca %array$int*
  store %array$int* null, %array$int** %a
  ; array declaration statement
  %b  = alloca %array$int*
  store %array$int* null, %array$int** %b
  ; array declaration statement
  %c  = alloca %array$int*
  store %array$int* null, %array$int** %c
  ; array declaration statement
  %a2  = alloca %array$int*
  store %array$int* null, %array$int** %a2
  ; array declaration statement
  %b2  = alloca %array$int*
  store %array$int* null, %array$int** %b2
  ; array declaration statement
  %c2  = alloca %array$int*
  store %array$int* null, %array$int** %c2
  ; array declaration statement
  %b3  = alloca %array$int*
  store %array$int* null, %array$int** %b3
  ; array declaration statement
  %c3  = alloca %array$int*
  store %array$int* null, %array$int** %c3
  ; array declaration statement
  %d3  = alloca %array$int*
  store %array$int* null, %array$int** %d3
  ret void
}
