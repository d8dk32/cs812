; source file: simpleArray.t
; T version: 1.0
; compiled: Mon Jun 24 01:42:06 EDT 2019

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)
declare void @t_rt_print_divide_by_zero_error(i32)
declare void @t_rt_print_array_index_out_of_bounds_error(i32)
declare void @t_rt_print_null_ref_error(i32)
declare void @t_rt_print_logging(i32)
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
  %x  = alloca %array$int*
  store %array$int* null, %array$int** %x
  ; array declaration statement
  %y  = alloca %array$int*
  store %array$int* null, %array$int** %y
  ; array creation
  %temp1 = bitcast [1 x i8*]* @intVMT to i8*
  %temp2 = call i8* (i32, i8*, i32, i32) @t_rt_new_intarray( i32 5, i8* %temp1, i32 1, i32 10)
  %temp0 = bitcast i8* %temp2 to %array$int*
  ; manually store len/dims/type because the new_array func isn't doing it like I expected
  %temp3 = getelementptr %array$int, %array$int* %temp0, i32 0, i32 3
  %temp4 = getelementptr %array$int, %array$int* %temp0, i32 0, i32 2
  %temp5 = getelementptr %array$int, %array$int* %temp0, i32 0, i32 1
  store i32 10, i32* %temp3
  store i32 1, i32* %temp4
  store i8* %temp1, i8** %temp5
  ; store assigned value
  store %array$int* %temp0, %array$int** %x
  ; array creation
  %temp7 = bitcast [1 x i8*]* @intVMT to i8*
  %temp8 = call i8* (i32, i8*, i32, i32) @t_rt_new_intarray( i32 6, i8* %temp7, i32 1, i32 10)
  %temp6 = bitcast i8* %temp8 to %array$int*
  ; manually store len/dims/type because the new_array func isn't doing it like I expected
  %temp9 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 3
  %temp10 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 2
  %temp11 = getelementptr %array$int, %array$int* %temp6, i32 0, i32 1
  store i32 10, i32* %temp9
  store i32 1, i32* %temp10
  store i8* %temp7, i8** %temp11
  ; store assigned value
  store %array$int* %temp6, %array$int** %y
  ; load value from variable
  %temp12 = load %array$int*, %array$int** %x
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
  %temp18 = icmp sge i32 5, 0
  br i1 %temp18, label %label2, label %label4
  ; check index is less than array length
  label2:
  %temp19 = icmp slt i32 5, %temp17
  br i1 %temp19, label %label3, label %label4
  label4:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 7)
  br label %label3
  ; perform actual array access
  label3:
  %temp13 = getelementptr %array$int, %array$int* %temp12, i32 0, i32 4
  %temp14 = getelementptr [0 x i32], [0 x i32]* %temp13, i32 0, i32 5
  ; store assigned value
  store i32 5, i32* %temp14
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
  %temp27 = icmp sge i32 4, 0
  br i1 %temp27, label %label7, label %label9
  ; check index is less than array length
  label7:
  %temp28 = icmp slt i32 4, %temp26
  br i1 %temp28, label %label8, label %label9
  label9:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 8)
  br label %label8
  ; perform actual array access
  label8:
  %temp22 = getelementptr %array$int, %array$int* %temp21, i32 0, i32 4
  %temp23 = getelementptr [0 x i32], [0 x i32]* %temp22, i32 0, i32 4
  ; store assigned value
  store i32 5, i32* %temp23
  ; load value from variable
  %temp30 = load %array$int*, %array$int** %x
  ; array access
  ; check for null reference
  %temp33 = icmp eq %array$int* %temp30, null
  br i1 %temp33, label %label10, label %label11
  label10:
  call void @t_rt_print_null_ref_error(i32 9)
  br label %label11
  ; check that index is greater than or equal to 0
  label11:
  %temp34 = getelementptr %array$int, %array$int* %temp30, i32 0, i32 3
  %temp35 = load i32, i32* %temp34
  %temp36 = icmp sge i32 0, 0
  br i1 %temp36, label %label12, label %label14
  ; check index is less than array length
  label12:
  %temp37 = icmp slt i32 0, %temp35
  br i1 %temp37, label %label13, label %label14
  label14:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 9)
  br label %label13
  ; perform actual array access
  label13:
  %temp31 = getelementptr %array$int, %array$int* %temp30, i32 0, i32 4
  %temp32 = getelementptr [0 x i32], [0 x i32]* %temp31, i32 0, i32 0
  %temp38 = load i32, i32* %temp32
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp38)
  ; load value from variable
  %temp39 = load %array$int*, %array$int** %x
  ; array access
  ; check for null reference
  %temp42 = icmp eq %array$int* %temp39, null
  br i1 %temp42, label %label15, label %label16
  label15:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label16
  ; check that index is greater than or equal to 0
  label16:
  %temp43 = getelementptr %array$int, %array$int* %temp39, i32 0, i32 3
  %temp44 = load i32, i32* %temp43
  %temp45 = icmp sge i32 5, 0
  br i1 %temp45, label %label17, label %label19
  ; check index is less than array length
  label17:
  %temp46 = icmp slt i32 5, %temp44
  br i1 %temp46, label %label18, label %label19
  label19:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 10)
  br label %label18
  ; perform actual array access
  label18:
  %temp40 = getelementptr %array$int, %array$int* %temp39, i32 0, i32 4
  %temp41 = getelementptr [0 x i32], [0 x i32]* %temp40, i32 0, i32 5
  %temp47 = load i32, i32* %temp41
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp47)
  ; load value from variable
  %temp48 = load %array$int*, %array$int** %y
  ; array access
  ; check for null reference
  %temp51 = icmp eq %array$int* %temp48, null
  br i1 %temp51, label %label20, label %label21
  label20:
  call void @t_rt_print_null_ref_error(i32 11)
  br label %label21
  ; check that index is greater than or equal to 0
  label21:
  %temp52 = getelementptr %array$int, %array$int* %temp48, i32 0, i32 3
  %temp53 = load i32, i32* %temp52
  %temp54 = icmp sge i32 4, 0
  br i1 %temp54, label %label22, label %label24
  ; check index is less than array length
  label22:
  %temp55 = icmp slt i32 4, %temp53
  br i1 %temp55, label %label23, label %label24
  label24:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 11)
  br label %label23
  ; perform actual array access
  label23:
  %temp49 = getelementptr %array$int, %array$int* %temp48, i32 0, i32 4
  %temp50 = getelementptr [0 x i32], [0 x i32]* %temp49, i32 0, i32 4
  %temp56 = load i32, i32* %temp50
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp56)
  ; load value from variable
  %temp57 = load %array$int*, %array$int** %y
  ; store assigned value
  store %array$int* %temp57, %array$int** %x
  ; load value from variable
  %temp58 = load %array$int*, %array$int** %x
  ; array access
  ; check for null reference
  %temp61 = icmp eq %array$int* %temp58, null
  br i1 %temp61, label %label25, label %label26
  label25:
  call void @t_rt_print_null_ref_error(i32 13)
  br label %label26
  ; check that index is greater than or equal to 0
  label26:
  %temp62 = getelementptr %array$int, %array$int* %temp58, i32 0, i32 3
  %temp63 = load i32, i32* %temp62
  %temp64 = icmp sge i32 4, 0
  br i1 %temp64, label %label27, label %label29
  ; check index is less than array length
  label27:
  %temp65 = icmp slt i32 4, %temp63
  br i1 %temp65, label %label28, label %label29
  label29:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 13)
  br label %label28
  ; perform actual array access
  label28:
  %temp59 = getelementptr %array$int, %array$int* %temp58, i32 0, i32 4
  %temp60 = getelementptr [0 x i32], [0 x i32]* %temp59, i32 0, i32 4
  %temp66 = load i32, i32* %temp60
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp66)
  ; array creation
  %temp68 = bitcast [1 x i8*]* @intVMT to i8*
  %temp69 = call i8* (i32, i8*, i32, i32) @t_rt_new_intarray( i32 14, i8* %temp68, i32 1, i32 5)
  %temp67 = bitcast i8* %temp69 to %array$int*
  ; manually store len/dims/type because the new_array func isn't doing it like I expected
  %temp70 = getelementptr %array$int, %array$int* %temp67, i32 0, i32 3
  %temp71 = getelementptr %array$int, %array$int* %temp67, i32 0, i32 2
  %temp72 = getelementptr %array$int, %array$int* %temp67, i32 0, i32 1
  store i32 5, i32* %temp70
  store i32 1, i32* %temp71
  store i8* %temp68, i8** %temp72
  ; store assigned value
  store %array$int* %temp67, %array$int** %x
  ; load value from variable
  %temp73 = load %array$int*, %array$int** %x
  ; array access
  ; check for null reference
  %temp76 = icmp eq %array$int* %temp73, null
  br i1 %temp76, label %label30, label %label31
  label30:
  call void @t_rt_print_null_ref_error(i32 15)
  br label %label31
  ; check that index is greater than or equal to 0
  label31:
  %temp77 = getelementptr %array$int, %array$int* %temp73, i32 0, i32 3
  %temp78 = load i32, i32* %temp77
  %temp79 = icmp sge i32 0, 0
  br i1 %temp79, label %label32, label %label34
  ; check index is less than array length
  label32:
  %temp80 = icmp slt i32 0, %temp78
  br i1 %temp80, label %label33, label %label34
  label34:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 15)
  br label %label33
  ; perform actual array access
  label33:
  %temp74 = getelementptr %array$int, %array$int* %temp73, i32 0, i32 4
  %temp75 = getelementptr [0 x i32], [0 x i32]* %temp74, i32 0, i32 0
  ; store assigned value
  store i32 42, i32* %temp75
  ; load value from variable
  %temp82 = load %array$int*, %array$int** %x
  ; array access
  ; check for null reference
  %temp85 = icmp eq %array$int* %temp82, null
  br i1 %temp85, label %label35, label %label36
  label35:
  call void @t_rt_print_null_ref_error(i32 16)
  br label %label36
  ; check that index is greater than or equal to 0
  label36:
  %temp86 = getelementptr %array$int, %array$int* %temp82, i32 0, i32 3
  %temp87 = load i32, i32* %temp86
  %temp88 = icmp sge i32 0, 0
  br i1 %temp88, label %label37, label %label39
  ; check index is less than array length
  label37:
  %temp89 = icmp slt i32 0, %temp87
  br i1 %temp89, label %label38, label %label39
  label39:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 16)
  br label %label38
  ; perform actual array access
  label38:
  %temp83 = getelementptr %array$int, %array$int* %temp82, i32 0, i32 4
  %temp84 = getelementptr [0 x i32], [0 x i32]* %temp83, i32 0, i32 0
  %temp90 = load i32, i32* %temp84
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp90)
  ; load value from variable
  %temp91 = load %array$int*, %array$int** %x
  ; array access
  ; check for null reference
  %temp94 = icmp eq %array$int* %temp91, null
  br i1 %temp94, label %label40, label %label41
  label40:
  call void @t_rt_print_null_ref_error(i32 17)
  br label %label41
  ; check that index is greater than or equal to 0
  label41:
  %temp95 = getelementptr %array$int, %array$int* %temp91, i32 0, i32 3
  %temp96 = load i32, i32* %temp95
  %temp97 = icmp sge i32 4, 0
  br i1 %temp97, label %label42, label %label44
  ; check index is less than array length
  label42:
  %temp98 = icmp slt i32 4, %temp96
  br i1 %temp98, label %label43, label %label44
  label44:
  call void @t_rt_print_array_index_out_of_bounds_error(i32 17)
  br label %label43
  ; perform actual array access
  label43:
  %temp92 = getelementptr %array$int, %array$int* %temp91, i32 0, i32 4
  %temp93 = getelementptr [0 x i32], [0 x i32]* %temp92, i32 0, i32 4
  %temp99 = load i32, i32* %temp93
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp99)
  ret void
}
