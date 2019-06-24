; source file: simpleArrayCast.t
; T version: 1.0
; compiled: Sun Jun 23 20:48:18 EDT 2019

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)
declare void @t_rt_print_divide_by_zero_error(i32)
declare void @t_rt_print_array_index_out_of_bounds_error(i32)
declare void @t_rt_print_null_ref_error(i32)
declare i8* @t_rt_new_intarray(i32, i8*, i32, i32)
declare i8* @t_rt_new_refarray(i32, i8*, i32, i32)

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
  %x1  = alloca %array$int*
  store %array$int* null, %array$int** %x1
  ; array declaration statement
  %x2  = alloca %array$int*
  store %array$int* null, %array$int** %x2
  ; array creation
  %temp1 = bitcast [1 x i8*]* @intVMT to i8*
  %temp2 = call i8* (i32, i8*, i32, i32) @t_rt_new_intarray( i32 4, i8* %temp1, i32 1, i32 5)
  %temp0 = bitcast i8* %temp2 to %array$int*
  ; manually store len/dims/type because the new_array func isn't doing it like I expected
  %temp3 = getelementptr %array$int, %array$int* %temp0, i32 0, i32 3
  %temp4 = getelementptr %array$int, %array$int* %temp0, i32 0, i32 2
  %temp5 = getelementptr %array$int, %array$int* %temp0, i32 0, i32 1
  store i32 5, i32* %temp3
  store i32 1, i32* %temp4
  store i8* %temp1, i8** %temp5
  ; store assigned value
  store %array$int* %temp0, %array$int** %x2
  ; load value from variable
  %temp6 = load %array$int*, %array$int** %x2
  ; array access
  ; check for null reference
  %temp9 = icmp eq %array$int* %temp6, null
  br i1 %temp9, label %label0, label %label1
  label0:
  call void @t_rt_print_null_ref_error(i32 5)
  br label %label1
  ; check that index is greater than or equal to 0
  label1:
  %temp10 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 3
  %temp11 = load i32, i32* %temp10
  %temp12 = icmp sge i32 0, 0
  br i1 %temp12, label %label2, label %label4
  ; check index is less than array length
  label2:
  %temp13 = icmp slt i32 0, %temp11
  br i1 %temp13, label %label3, label %label4
  label4:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 5)
  br label %label3
  ; perform actual array access
  label3:
  %temp7 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 4
  %temp8 = getelementptr [0 x i32], [0 x i32]* %temp7, i32 0, i32 0
  ; store assigned value
  store i32 42, i32* %temp8
  ; load value from variable
  %temp15 = load %array$int*, %array$int** %x2
  ; store assigned value
  store %array$int* %temp15, %array$int** %x1
  ; load value from variable
  %temp16 = load %array$int*, %array$int** %x1
  ; array access
  ; check for null reference
  %temp19 = icmp eq %array$int* %temp16, null
  br i1 %temp19, label %label5, label %label6
  label5:
  call void @t_rt_print_null_ref_error(i32 7)
  br label %label6
  ; check that index is greater than or equal to 0
  label6:
  %temp20 = getelementptr %array$int, %array$int* %temp16, i32 0, i32 3
  %temp21 = load i32, i32* %temp20
  %temp22 = icmp sge i32 0, 0
  br i1 %temp22, label %label7, label %label9
  ; check index is less than array length
  label7:
  %temp23 = icmp slt i32 0, %temp21
  br i1 %temp23, label %label8, label %label9
  label9:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 7)
  br label %label8
  ; perform actual array access
  label8:
  %temp17 = getelementptr %array$int, %array$int* %temp16, i32 0, i32 4
  %temp18 = getelementptr [0 x i32], [0 x i32]* %temp17, i32 0, i32 0
  %temp24 = load i32, i32* %temp18
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp24)
  ret void
}
