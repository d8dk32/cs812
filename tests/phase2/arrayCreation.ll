; source file: arrayCreation.t
; T version: 1.0
; compiled: Tue Jun 18 22:30:58 EDT 2019

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
  store i32 19, i32* %i
  ; array declaration statement
  %x  = alloca %array$int*
  store %array$int* null, %array$int** %x
  ; array declaration statement
  %y  = alloca %array$int*
  store %array$int* null, %array$int** %y
  ; array declaration statement
  %z  = alloca %array$int*
  store %array$int* null, %array$int** %z
  ; load value from variable
  %temp0 = load i32, i32* %i
  ; array creation
  %temp2 = bitcast [1 x i8*]* @arrayVMT to i8*
  %temp3 = call i8* (i32, i8*, i32, i32) @t_rt_new_array( i32 11, i8* %temp2, i32 1, i32 %temp0)
  %temp1 = bitcast i8* %temp3 to %array$int*
  ; store assigned value
  store %array$int* %temp1, i32* %x
  ; load value from variable
  %temp4 = load i32, i32* %i
  ; add
  %temp5 = add i32 %temp4, 1
  ; array creation
  %temp7 = bitcast [1 x i8*]* @arrayVMT to i8*
  %temp8 = call i8* (i32, i8*, i32, i32) @t_rt_new_array( i32 12, i8* %temp7, i32 2, i32 %temp5)
  %temp6 = bitcast i8* %temp8 to %array$int*
  ; store assigned value
  store %array$int* %temp6, i32* %y
  ; load value from variable
  %temp9 = load i32, i32* %i
  ; add
  %temp10 = add i32 %temp9, 2
  ; array creation
  %temp12 = bitcast [1 x i8*]* @arrayVMT to i8*
  %temp13 = call i8* (i32, i8*, i32, i32) @t_rt_new_array( i32 13, i8* %temp12, i32 3, i32 %temp10)
  %temp11 = bitcast i8* %temp13 to %array$int*
  ; store assigned value
  store %array$int* %temp11, i32* %z
  ret void
}
