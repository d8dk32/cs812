; source file: simpleMultiDArray.t
; T version: 1.0
; compiled: Sun Jun 23 14:08:13 EDT 2019

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
  ; array declaration statement
  %y  = alloca %array$int*
  store %array$int* null, %array$int** %y
  ; array creation
  %temp1 = bitcast [1 x i8*]* @intVMT to i8*
  %temp2 = call i8* (i32, i8*, i32, i32) @t_rt_new_refarray( i32 5, i8* %temp1, i32 2, i32 5)
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
  %temp8 = call i8* (i32, i8*, i32, i32) @t_rt_new_intarray( i32 6, i8* %temp7, i32 1, i32 3)
  %temp6 = bitcast i8* %temp8 to %array$int*
  ; manually store len/dims/type because the new_array func isn't doing it like I expected
  %temp9 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 3
  %temp10 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 2
  %temp11 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 1
  store i32 3, i32* %temp9
  store i32 1, i32* %temp10
  store i8* %temp7, i8** %temp11
  ; store assigned value
  store %array$int* %temp6, %array$int** %y
  ; load value from variable
  %temp12 = load %array$int*, %array$int** %y
  ; array access
  ; check for null reference
  %temp15 = icmp eq %array$int* %temp12, null
  br i1 %temp15, label %label0, label %label1
  label0:
  call void @t_rt_print_null_ref_error(i32 7)
  br label %label1
  ; check that index is greater than or equal to 0
  label1:
  %temp16 = getelementptr %array$int, %array$int* %temp12, i32 0, i32 3
  %temp17 = load i32, i32* %temp16
  %temp18 = icmp sge i32 0, 0
  br i1 %temp18, label %label2, label %label4
  ; check index is less than array length
  label2:
  %temp19 = icmp slt i32 0, %temp17
  br i1 %temp19, label %label3, label %label4
  label4:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 7)
  br label %label3
  ; perform actual array access
  label3:
  %temp13 = getelementptr %array$int, %array$int* %temp12, i32 0, i32 4
  %temp14 = getelementptr [0 x i32], [0 x i32]* %temp13, i32 0, i32 0
  ; store assigned value
  store i32 42, i32* %temp14
  ; load value from variable
  %temp21 = load %array$int*, %array$int** %y
  ; array access
  ; check for null reference
  %temp24 = icmp eq %array$int* %temp21, null
  br i1 %temp24, label %label5, label %label6
  label5:
  call void @t_rt_print_null_ref_error(i32 8)
  br label %label6
  ; check that index is greater than or equal to 0
  label6:
  %temp25 = getelementptr %array$int, %array$int* %temp21, i32 0, i32 3
  %temp26 = load i32, i32* %temp25
  %temp27 = icmp sge i32 0, 0
  br i1 %temp27, label %label7, label %label9
  ; check index is less than array length
  label7:
  %temp28 = icmp slt i32 0, %temp26
  br i1 %temp28, label %label8, label %label9
  label9:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 8)
  br label %label8
  ; perform actual array access
  label8:
  %temp22 = getelementptr %array$int, %array$int* %temp21, i32 0, i32 4
  %temp23 = getelementptr [0 x i32], [0 x i32]* %temp22, i32 0, i32 0
  %temp29 = load i32, i32* %temp23
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp29)
  ; load value from variable
  %temp30 = load %array$int*, %array$int** %y
  ; load value from variable
  %temp31 = load %array$ref*, %array$ref** %x
  ; array access
  ; check for null reference
  %temp34 = icmp eq %array$ref* %temp31, null
  br i1 %temp34, label %label10, label %label11
  label10:
  call void @t_rt_print_null_ref_error(i32 9)
  br label %label11
  ; check that index is greater than or equal to 0
  label11:
  %temp35 = getelementptr %array$ref, %array$ref* %temp31, i32 0, i32 3
  %temp36 = load i32, i32* %temp35
  %temp37 = icmp sge i32 4, 0
  br i1 %temp37, label %label12, label %label14
  ; check index is less than array length
  label12:
  %temp38 = icmp slt i32 4, %temp36
  br i1 %temp38, label %label13, label %label14
  label14:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 9)
  br label %label13
  ; perform actual array access
  label13:
  %temp32 = getelementptr %array$ref, %array$ref* %temp31, i32 0, i32 4
  %temp33 = getelementptr [0 x i8*], [0 x i8*]* %temp32, i32 0, i32 4
  %temp40 = bitcast i8** %temp33 to %array$int**
  ; store assigned value
  store %array$int* %temp30, %array$int** %temp40
  ; load value from variable
  %temp41 = load %array$int*, %array$int** %y
  ; array access
  ; check for null reference
  %temp44 = icmp eq %array$int* %temp41, null
  br i1 %temp44, label %label15, label %label16
  label15:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label16
  ; check that index is greater than or equal to 0
  label16:
  %temp45 = getelementptr %array$int, %array$int* %temp41, i32 0, i32 3
  %temp46 = load i32, i32* %temp45
  %temp47 = icmp sge i32 0, 0
  br i1 %temp47, label %label17, label %label19
  ; check index is less than array length
  label17:
  %temp48 = icmp slt i32 0, %temp46
  br i1 %temp48, label %label18, label %label19
  label19:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 10)
  br label %label18
  ; perform actual array access
  label18:
  %temp42 = getelementptr %array$int, %array$int* %temp41, i32 0, i32 4
  %temp43 = getelementptr [0 x i32], [0 x i32]* %temp42, i32 0, i32 0
  %temp49 = load i32, i32* %temp43
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp49)
  ; load value from variable
  %temp50 = load %array$int*, %array$int** %y
  ; array access
  ; check for null reference
  %temp53 = icmp eq %array$int* %temp50, null
  br i1 %temp53, label %label20, label %label21
  label20:
  call void @t_rt_print_null_ref_error(i32 11)
  br label %label21
  ; check that index is greater than or equal to 0
  label21:
  %temp54 = getelementptr %array$int, %array$int* %temp50, i32 0, i32 3
  %temp55 = load i32, i32* %temp54
  %temp56 = icmp sge i32 1, 0
  br i1 %temp56, label %label22, label %label24
  ; check index is less than array length
  label22:
  %temp57 = icmp slt i32 1, %temp55
  br i1 %temp57, label %label23, label %label24
  label24:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 11)
  br label %label23
  ; perform actual array access
  label23:
  %temp51 = getelementptr %array$int, %array$int* %temp50, i32 0, i32 4
  %temp52 = getelementptr [0 x i32], [0 x i32]* %temp51, i32 0, i32 1
  ; store assigned value
  store i32 42, i32* %temp52
  ; load value from variable
  %temp59 = load %array$ref*, %array$ref** %x
  ; array access
  ; check for null reference
  %temp62 = icmp eq %array$ref* %temp59, null
  br i1 %temp62, label %label25, label %label26
  label25:
  call void @t_rt_print_null_ref_error(i32 12)
  br label %label26
  ; check that index is greater than or equal to 0
  label26:
  %temp63 = getelementptr %array$ref, %array$ref* %temp59, i32 0, i32 3
  %temp64 = load i32, i32* %temp63
  %temp65 = icmp sge i32 4, 0
  br i1 %temp65, label %label27, label %label29
  ; check index is less than array length
  label27:
  %temp66 = icmp slt i32 4, %temp64
  br i1 %temp66, label %label28, label %label29
  label29:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 12)
  br label %label28
  ; perform actual array access
  label28:
  %temp60 = getelementptr %array$ref, %array$ref* %temp59, i32 0, i32 4
  %temp61 = getelementptr [0 x i8*], [0 x i8*]* %temp60, i32 0, i32 4
  %temp67 = load i8*, i8** %temp61
  %temp68 = bitcast i8* %temp67 to %array$ref*
  ; array access
  ; check for null reference
  %temp71 = icmp eq %array$ref* %temp68, null
  br i1 %temp71, label %label30, label %label31
  label30:
  call void @t_rt_print_null_ref_error(i32 12)
  br label %label31
  ; check that index is greater than or equal to 0
  label31:
  %temp72 = getelementptr %array$ref, %array$ref* %temp68, i32 0, i32 3
  %temp73 = load i32, i32* %temp72
  %temp74 = icmp sge i32 2, 0
  br i1 %temp74, label %label32, label %label34
  ; check index is less than array length
  label32:
  %temp75 = icmp slt i32 2, %temp73
  br i1 %temp75, label %label33, label %label34
  label34:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 12)
  br label %label33
  ; perform actual array access
  label33:
  %temp69 = getelementptr %array$ref, %array$ref* %temp68, i32 0, i32 4
  %temp70 = getelementptr [0 x i8*], [0 x i8*]* %temp69, i32 0, i32 2
  %temp77 = bitcast i8** %temp70 to i32*
  ; store assigned value
  store i32 42, i32* %temp77
  ; load value from variable
  %temp78 = load %array$ref*, %array$ref** %x
  ; array access
  ; check for null reference
  %temp81 = icmp eq %array$ref* %temp78, null
  br i1 %temp81, label %label35, label %label36
  label35:
  call void @t_rt_print_null_ref_error(i32 13)
  br label %label36
  ; check that index is greater than or equal to 0
  label36:
  %temp82 = getelementptr %array$ref, %array$ref* %temp78, i32 0, i32 3
  %temp83 = load i32, i32* %temp82
  %temp84 = icmp sge i32 3, 0
  br i1 %temp84, label %label37, label %label39
  ; check index is less than array length
  label37:
  %temp85 = icmp slt i32 3, %temp83
  br i1 %temp85, label %label38, label %label39
  label39:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 13)
  br label %label38
  ; perform actual array access
  label38:
  %temp79 = getelementptr %array$ref, %array$ref* %temp78, i32 0, i32 4
  %temp80 = getelementptr [0 x i8*], [0 x i8*]* %temp79, i32 0, i32 3
  %temp86 = load i8*, i8** %temp80
  %temp87 = bitcast i8* %temp86 to %array$ref*
  ; array access
  ; check for null reference
  %temp90 = icmp eq %array$ref* %temp87, null
  br i1 %temp90, label %label40, label %label41
  label40:
  call void @t_rt_print_null_ref_error(i32 13)
  br label %label41
  ; check that index is greater than or equal to 0
  label41:
  %temp91 = getelementptr %array$ref, %array$ref* %temp87, i32 0, i32 3
  %temp92 = load i32, i32* %temp91
  %temp93 = icmp sge i32 2, 0
  br i1 %temp93, label %label42, label %label44
  ; check index is less than array length
  label42:
  %temp94 = icmp slt i32 2, %temp92
  br i1 %temp94, label %label43, label %label44
  label44:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 13)
  br label %label43
  ; perform actual array access
  label43:
  %temp88 = getelementptr %array$ref, %array$ref* %temp87, i32 0, i32 4
  %temp89 = getelementptr [0 x i8*], [0 x i8*]* %temp88, i32 0, i32 2
  %temp95 = load i8*, i8** %temp89
  %temp97 = bitcast i8* %temp95 to i32*
  %temp96 = load i32, i32*%temp97
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp96)
  ret void
}
