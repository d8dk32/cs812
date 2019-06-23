; source file: simpleMultiDArray.t
; T version: 1.0
; compiled: Sun Jun 23 14:36:52 EDT 2019

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
  %x  = alloca %array$ref*
  store %array$ref* null, %array$ref** %x
  ; array creation
  %temp1 = bitcast [1 x i8*]* @intVMT to i8*
  %temp2 = call i8* (i32, i8*, i32, i32) @t_rt_new_refarray( i32 4, i8* %temp1, i32 2, i32 5)
  %temp0 = bitcast i8* %temp2 to %array$ref*
  ; manually store len/dims/type because the new_array func isn't doing it like I expected
  %temp3 = getelementptr %array$ref, %array$ref* %temp0, i32 0, i32 3
  %temp4 = getelementptr %array$ref, %array$ref* %temp0, i32 0, i32 2
  %temp5 = getelementptr %array$ref, %array$ref* %temp0, i32 0, i32 1
  store i32 5, i32* %temp3
  store i32 2, i32* %temp4
  store i8* %temp1, i8** %temp5
  ; store assigned value
  store %array$ref* %temp0, %array$ref** %x
  ; array creation
  %temp7 = bitcast [1 x i8*]* @intVMT to i8*
  %temp8 = call i8* (i32, i8*, i32, i32) @t_rt_new_intarray( i32 5, i8* %temp7, i32 1, i32 3)
  %temp6 = bitcast i8* %temp8 to %array$int*
  ; manually store len/dims/type because the new_array func isn't doing it like I expected
  %temp9 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 3
  %temp10 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 2
  %temp11 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 1
  store i32 3, i32* %temp9
  store i32 1, i32* %temp10
  store i8* %temp7, i8** %temp11
  ; load value from variable
  %temp12 = load %array$ref*, %array$ref** %x
  ; array access
  ; check for null reference
  %temp15 = icmp eq %array$ref* %temp12, null
  br i1 %temp15, label %label0, label %label1
  label0:
  call void @t_rt_print_null_ref_error(i32 5)
  br label %label1
  ; check that index is greater than or equal to 0
  label1:
  %temp16 = getelementptr %array$ref, %array$ref* %temp12, i32 0, i32 3
  %temp17 = load i32, i32* %temp16
  %temp18 = icmp sge i32 4, 0
  br i1 %temp18, label %label2, label %label4
  ; check index is less than array length
  label2:
  %temp19 = icmp slt i32 4, %temp17
  br i1 %temp19, label %label3, label %label4
  label4:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 5)
  br label %label3
  ; perform actual array access
  label3:
  %temp13 = getelementptr %array$ref, %array$ref* %temp12, i32 0, i32 4
  %temp14 = getelementptr [0 x i8*], [0 x i8*]* %temp13, i32 0, i32 4
  %temp21 = bitcast i8** %temp14 to %array$int**
  ; store assigned value
  store %array$int* %temp6, %array$int** %temp21
  ; load value from variable
  %temp22 = load %array$ref*, %array$ref** %x
  ; array access
  ; check for null reference
  %temp25 = icmp eq %array$ref* %temp22, null
  br i1 %temp25, label %label5, label %label6
  label5:
  call void @t_rt_print_null_ref_error(i32 6)
  br label %label6
  ; check that index is greater than or equal to 0
  label6:
  %temp26 = getelementptr %array$ref, %array$ref* %temp22, i32 0, i32 3
  %temp27 = load i32, i32* %temp26
  %temp28 = icmp sge i32 4, 0
  br i1 %temp28, label %label7, label %label9
  ; check index is less than array length
  label7:
  %temp29 = icmp slt i32 4, %temp27
  br i1 %temp29, label %label8, label %label9
  label9:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 6)
  br label %label8
  ; perform actual array access
  label8:
  %temp23 = getelementptr %array$ref, %array$ref* %temp22, i32 0, i32 4
  %temp24 = getelementptr [0 x i8*], [0 x i8*]* %temp23, i32 0, i32 4
  %temp30 = load i8*, i8** %temp24
  %temp31 = bitcast i8* %temp30 to %array$ref*
  ; array access
  ; check for null reference
  %temp34 = icmp eq %array$ref* %temp31, null
  br i1 %temp34, label %label10, label %label11
  label10:
  call void @t_rt_print_null_ref_error(i32 6)
  br label %label11
  ; check that index is greater than or equal to 0
  label11:
  %temp35 = getelementptr %array$ref, %array$ref* %temp31, i32 0, i32 3
  %temp36 = load i32, i32* %temp35
  %temp37 = icmp sge i32 2, 0
  br i1 %temp37, label %label12, label %label14
  ; check index is less than array length
  label12:
  %temp38 = icmp slt i32 2, %temp36
  br i1 %temp38, label %label13, label %label14
  label14:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 6)
  br label %label13
  ; perform actual array access
  label13:
  %temp32 = getelementptr %array$ref, %array$ref* %temp31, i32 0, i32 4
  %temp33 = getelementptr [0 x i8*], [0 x i8*]* %temp32, i32 0, i32 2
  %temp40 = bitcast i8** %temp33 to i32*
  ; store assigned value
  store i32 42, i32* %temp40
  ; load value from variable
  %temp41 = load %array$ref*, %array$ref** %x
  ; array access
  ; check for null reference
  %temp44 = icmp eq %array$ref* %temp41, null
  br i1 %temp44, label %label15, label %label16
  label15:
  call void @t_rt_print_null_ref_error(i32 7)
  br label %label16
  ; check that index is greater than or equal to 0
  label16:
  %temp45 = getelementptr %array$ref, %array$ref* %temp41, i32 0, i32 3
  %temp46 = load i32, i32* %temp45
  %temp47 = icmp sge i32 4, 0
  br i1 %temp47, label %label17, label %label19
  ; check index is less than array length
  label17:
  %temp48 = icmp slt i32 4, %temp46
  br i1 %temp48, label %label18, label %label19
  label19:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 7)
  br label %label18
  ; perform actual array access
  label18:
  %temp42 = getelementptr %array$ref, %array$ref* %temp41, i32 0, i32 4
  %temp43 = getelementptr [0 x i8*], [0 x i8*]* %temp42, i32 0, i32 4
  %temp49 = load i8*, i8** %temp43
  %temp50 = bitcast i8* %temp49 to %array$ref*
  ; array access
  ; check for null reference
  %temp53 = icmp eq %array$ref* %temp50, null
  br i1 %temp53, label %label20, label %label21
  label20:
  call void @t_rt_print_null_ref_error(i32 7)
  br label %label21
  ; check that index is greater than or equal to 0
  label21:
  %temp54 = getelementptr %array$ref, %array$ref* %temp50, i32 0, i32 3
  %temp55 = load i32, i32* %temp54
  %temp56 = icmp sge i32 2, 0
  br i1 %temp56, label %label22, label %label24
  ; check index is less than array length
  label22:
  %temp57 = icmp slt i32 2, %temp55
  br i1 %temp57, label %label23, label %label24
  label24:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 7)
  br label %label23
  ; perform actual array access
  label23:
  %temp51 = getelementptr %array$ref, %array$ref* %temp50, i32 0, i32 4
  %temp52 = getelementptr [0 x i8*], [0 x i8*]* %temp51, i32 0, i32 2
  %temp58 = load i8*, i8** %temp52
  %temp60 = bitcast i8* %temp58 to i32*
  %temp59 = load i32, i32*%temp60
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp59)
  ret void
}
