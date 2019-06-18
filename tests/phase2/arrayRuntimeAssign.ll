; source file: arrayRuntimeAssign.t
; T version: 1.0
; compiled: Wed Dec 12 17:00:57 EST 2018

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)
declare void @t_rt_print_divide_by_zero_error(i32)
declare void @t_rt_null_reference_error(i32)
declare void @t_rt_index_out_of_bounds_error(i32)
declare i8* @t_rt_newArray(i32, i8*, i32, i32, ...)
declare void @t_rt_check_cast(i32, i8*, i8*)
declare void @t_rt_check_array_cast(i32, i8*, i32, i8*)
declare i32 @t_rt_array_assignment_int(i32, i8*, i32, i32)
declare i8* @t_rt_array_assignment_ref(i32, i8*, i32, i8*)
declare i32 @Object$equals$Object$(%class$Object*, %class$Object*)

; array runtime object types
%array$int = type {i8*, i8*, i32, i32, [0 x i32]}
%array$ref = type {i8*, i8*, i32, i32, [0 x i8*]}

; VMTs
@arrayVMT = global [1 x i8*] [i8* bitcast ([3 x i8*]* @Object$VMT to i8*)]
@intVMT = global [1 x i8*] [i8* null]
@Object$VMT = global [3 x i8*] [i8* null, i8* bitcast ( void (%class$Object*)*  @Object$destructor$ to i8*), i8* bitcast (i32 (%class$Object*, %class$Object*)* @Object$equals$Object$ to i8*)]

; class object types
%class$Object =  type { i8* }

; default constructors and destructors
define void @Object$constructor$(%class$Object* %ths) {
  ret void
}
define void @Object$destructor$(%class$Object* %ths) {
  ret void
}

; main block
define i32 @main() {
  call void @t_rt_alloc_init()
  %x2 = alloca %array$ref*
  store %array$ref* null, %array$ref** %x2
  %x3 = alloca %array$ref*
  store %array$ref* null, %array$ref** %x3
  %o = alloca %array$ref*
  store %array$ref* null, %array$ref** %o
  ; new array
  %temp1 = bitcast [1 x i8*]* @intVMT to i8*
  %temp2 = call i8* (i32, i8*, i32, i32, ...) @t_rt_newArray(i32 6, i8* %temp1, i32 3, i32 1, i32 2)
  %temp0 = bitcast i8* %temp2 to %array$ref*
  ; assign
  store %array$ref* %temp0, %array$ref** %x3
  %i = alloca i32
  store i32 0, i32* %i
  %j = alloca i32
  store i32 0, i32* %j
  ; assign
  store i32 0, i32* %i
  ; while statement
  br label %Label0
  Label0:
  ; deref
  %temp5 = load i32, i32* %i
  ; <
  %temp7 = icmp slt i32 %temp5, 2
  %temp6 = zext i1 %temp7 to i32
  %temp8 = icmp ne i32 %temp6, 0
  br i1 %temp8, label %Label1, label %Label2
  Label1:
  ; deref
  %temp10 = load %array$ref*, %array$ref** %x3
  ; deref
  %temp11 = load i32, i32* %i
  ; new array
  %temp13 = bitcast [1 x i8*]* @intVMT to i8*
  %temp14 = call i8* (i32, i8*, i32, i32, ...) @t_rt_newArray(i32 10, i8* %temp13, i32 2, i32 1, i32 5)
  %temp12 = bitcast i8* %temp14 to %array$ref*
  %temp15 = bitcast %array$ref* %temp10 to i8*
  %temp16 = bitcast %array$ref* %temp12 to i8*
  %temp9 = call i8* @t_rt_array_assignment_ref(i32 10, i8* %temp15, i32 %temp11, i8* %temp16)
  %temp17 = bitcast i8*%temp9 to %array$ref*
  ; assign
  store i32 0, i32* %j
  ; while statement
  br label %Label3
  Label3:
  ; deref
  %temp19 = load i32, i32* %j
  ; <
  %temp21 = icmp slt i32 %temp19, 5
  %temp20 = zext i1 %temp21 to i32
  %temp22 = icmp ne i32 %temp20, 0
  br i1 %temp22, label %Label4, label %Label5
  Label4:
  ; deref
  %temp24 = load %array$ref*, %array$ref** %x3
  ; deref
  %temp25 = load i32, i32* %i
  ; array access
  %temp27 = icmp eq %array$ref* %temp24, null
  br i1 %temp27, label %Label6, label %Label7
  Label6:
  call void @t_rt_null_reference_error(i32 13)
  br label %Label7
  Label7:
  %temp28 = getelementptr %array$ref, %array$ref* %temp24, i32 0, i32 3
  %temp29 = load i32, i32* %temp28
  %temp30 = icmp sge i32 %temp25, 0
  br i1 %temp30, label %Label8, label %Label10
  Label8:
  %temp31 = icmp sge i32 %temp25, %temp29
  br i1 %temp31, label %Label10, label %Label9
  Label10:
  call void @t_rt_index_out_of_bounds_error(i32 13)
  br label %Label9
  Label9:
  %temp32 = getelementptr %array$ref, %array$ref* %temp24, i32 0, i32 4
  %temp26 = getelementptr [0 x i8*], [0 x i8*]* %temp32, i32 0, i32 %temp25
  %temp33 = bitcast i8** %temp26 to %array$ref**
  ; deref
  %temp34 = load %array$ref*, %array$ref** %temp33
  ; deref
  %temp35 = load i32, i32* %j
  ; new array
  %temp37 = bitcast [1 x i8*]* @intVMT to i8*
  %temp38 = call i8* (i32, i8*, i32, i32, ...) @t_rt_newArray(i32 13, i8* %temp37, i32 1, i32 1, i32 10)
  %temp36 = bitcast i8* %temp38 to %array$int*
  %temp39 = bitcast %array$ref* %temp34 to i8*
  %temp40 = bitcast %array$int* %temp36 to i8*
  %temp23 = call i8* @t_rt_array_assignment_ref(i32 13, i8* %temp39, i32 %temp35, i8* %temp40)
  %temp41 = bitcast i8*%temp23 to %array$int*
  ; deref
  %temp42 = load i32, i32* %j
  ; add
  %temp43 = add i32 %temp42, 1
  ; assign
  store i32 %temp43, i32* %j
  br label %Label3
  Label5:
  ; deref
  %temp45 = load i32, i32* %i
  ; add
  %temp46 = add i32 %temp45, 1
  ; assign
  store i32 %temp46, i32* %i
  br label %Label0
  Label2:
  ; deref
  %temp49 = load %array$ref*, %array$ref** %x3
  ; array access
  %temp51 = icmp eq %array$ref* %temp49, null
  br i1 %temp51, label %Label11, label %Label12
  Label11:
  call void @t_rt_null_reference_error(i32 18)
  br label %Label12
  Label12:
  %temp52 = getelementptr %array$ref, %array$ref* %temp49, i32 0, i32 3
  %temp53 = load i32, i32* %temp52
  %temp54 = icmp sge i32 1, 0
  br i1 %temp54, label %Label13, label %Label15
  Label13:
  %temp55 = icmp sge i32 1, %temp53
  br i1 %temp55, label %Label15, label %Label14
  Label15:
  call void @t_rt_index_out_of_bounds_error(i32 18)
  br label %Label14
  Label14:
  %temp56 = getelementptr %array$ref, %array$ref* %temp49, i32 0, i32 4
  %temp50 = getelementptr [0 x i8*], [0 x i8*]* %temp56, i32 0, i32 1
  %temp57 = bitcast i8** %temp50 to %array$ref**
  ; deref
  %temp58 = load %array$ref*, %array$ref** %temp57
  ; array access
  %temp60 = icmp eq %array$ref* %temp58, null
  br i1 %temp60, label %Label16, label %Label17
  Label16:
  call void @t_rt_null_reference_error(i32 18)
  br label %Label17
  Label17:
  %temp61 = getelementptr %array$ref, %array$ref* %temp58, i32 0, i32 3
  %temp62 = load i32, i32* %temp61
  %temp63 = icmp sge i32 3, 0
  br i1 %temp63, label %Label18, label %Label20
  Label18:
  %temp64 = icmp sge i32 3, %temp62
  br i1 %temp64, label %Label20, label %Label19
  Label20:
  call void @t_rt_index_out_of_bounds_error(i32 18)
  br label %Label19
  Label19:
  %temp65 = getelementptr %array$ref, %array$ref* %temp58, i32 0, i32 4
  %temp59 = getelementptr [0 x i8*], [0 x i8*]* %temp65, i32 0, i32 3
  %temp66 = bitcast i8** %temp59 to %array$int**
  ; deref
  %temp67 = load %array$int*, %array$int** %temp66
  %temp68 = bitcast %array$int* %temp67 to i8*
  %temp48 = call i32 @t_rt_array_assignment_int(i32 18, i8* %temp68, i32 5, i32 15)
  ; deref
  %temp69 = load %array$ref*, %array$ref** %x3
  ; array access
  %temp71 = icmp eq %array$ref* %temp69, null
  br i1 %temp71, label %Label21, label %Label22
  Label21:
  call void @t_rt_null_reference_error(i32 19)
  br label %Label22
  Label22:
  %temp72 = getelementptr %array$ref, %array$ref* %temp69, i32 0, i32 3
  %temp73 = load i32, i32* %temp72
  %temp74 = icmp sge i32 1, 0
  br i1 %temp74, label %Label23, label %Label25
  Label23:
  %temp75 = icmp sge i32 1, %temp73
  br i1 %temp75, label %Label25, label %Label24
  Label25:
  call void @t_rt_index_out_of_bounds_error(i32 19)
  br label %Label24
  Label24:
  %temp76 = getelementptr %array$ref, %array$ref* %temp69, i32 0, i32 4
  %temp70 = getelementptr [0 x i8*], [0 x i8*]* %temp76, i32 0, i32 1
  %temp77 = bitcast i8** %temp70 to %array$ref**
  ; deref
  %temp78 = load %array$ref*, %array$ref** %temp77
  ; array access
  %temp80 = icmp eq %array$ref* %temp78, null
  br i1 %temp80, label %Label26, label %Label27
  Label26:
  call void @t_rt_null_reference_error(i32 19)
  br label %Label27
  Label27:
  %temp81 = getelementptr %array$ref, %array$ref* %temp78, i32 0, i32 3
  %temp82 = load i32, i32* %temp81
  %temp83 = icmp sge i32 3, 0
  br i1 %temp83, label %Label28, label %Label30
  Label28:
  %temp84 = icmp sge i32 3, %temp82
  br i1 %temp84, label %Label30, label %Label29
  Label30:
  call void @t_rt_index_out_of_bounds_error(i32 19)
  br label %Label29
  Label29:
  %temp85 = getelementptr %array$ref, %array$ref* %temp78, i32 0, i32 4
  %temp79 = getelementptr [0 x i8*], [0 x i8*]* %temp85, i32 0, i32 3
  %temp86 = bitcast i8** %temp79 to %array$int**
  ; deref
  %temp87 = load %array$int*, %array$int** %temp86
  ; array access
  %temp89 = icmp eq %array$int* %temp87, null
  br i1 %temp89, label %Label31, label %Label32
  Label31:
  call void @t_rt_null_reference_error(i32 19)
  br label %Label32
  Label32:
  %temp90 = getelementptr %array$int, %array$int* %temp87, i32 0, i32 3
  %temp91 = load i32, i32* %temp90
  %temp92 = icmp sge i32 5, 0
  br i1 %temp92, label %Label33, label %Label35
  Label33:
  %temp93 = icmp sge i32 5, %temp91
  br i1 %temp93, label %Label35, label %Label34
  Label35:
  call void @t_rt_index_out_of_bounds_error(i32 19)
  br label %Label34
  Label34:
  %temp94 = getelementptr %array$int, %array$int* %temp87, i32 0, i32 4
  %temp88 = getelementptr [0 x i32], [0 x i32]* %temp94, i32 0, i32 5
  ; deref
  %temp95 = load i32, i32* %temp88
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp95)
  ; deref
  %temp96 = load %array$ref*, %array$ref** %x3
  ; assign
  %temp98 = bitcast %array$ref* %temp96 to %array$ref*
  store %array$ref* %temp98, %array$ref** %o
  ; new array
  %temp100 = bitcast [1 x i8*]* @intVMT to i8*
  %temp101 = call i8* (i32, i8*, i32, i32, ...) @t_rt_newArray(i32 21, i8* %temp100, i32 2, i32 1, i32 6)
  %temp99 = bitcast i8* %temp101 to %array$ref*
  ; assign
  store %array$ref* %temp99, %array$ref** %x2
  ; deref
  %temp104 = load %array$ref*, %array$ref** %o
  ; deref
  %temp105 = load %array$ref*, %array$ref** %x2
  %temp106 = bitcast %array$ref* %temp104 to i8*
  %temp107 = bitcast %array$ref* %temp105 to i8*
  %temp103 = call i8* @t_rt_array_assignment_ref(i32 22, i8* %temp106, i32 1, i8* %temp107)
  %temp108 = bitcast i8*%temp103 to %class$Object*
  ; assign
  store i32 0, i32* %j
  ; while statement
  br label %Label36
  Label36:
  ; deref
  %temp110 = load i32, i32* %j
  ; <
  %temp112 = icmp slt i32 %temp110, 6
  %temp111 = zext i1 %temp112 to i32
  %temp113 = icmp ne i32 %temp111, 0
  br i1 %temp113, label %Label37, label %Label38
  Label37:
  ; deref
  %temp115 = load %array$ref*, %array$ref** %x2
  ; deref
  %temp116 = load i32, i32* %j
  ; new array
  %temp118 = bitcast [1 x i8*]* @intVMT to i8*
  %temp119 = call i8* (i32, i8*, i32, i32, ...) @t_rt_newArray(i32 25, i8* %temp118, i32 1, i32 1, i32 9)
  %temp117 = bitcast i8* %temp119 to %array$int*
  %temp120 = bitcast %array$ref* %temp115 to i8*
  %temp121 = bitcast %array$int* %temp117 to i8*
  %temp114 = call i8* @t_rt_array_assignment_ref(i32 25, i8* %temp120, i32 %temp116, i8* %temp121)
  %temp122 = bitcast i8*%temp114 to %array$int*
  ; deref
  %temp123 = load i32, i32* %j
  ; add
  %temp124 = add i32 %temp123, 1
  ; assign
  store i32 %temp124, i32* %j
  br label %Label36
  Label38:
  ; deref
  %temp127 = load %array$ref*, %array$ref** %o
  ; deref
  %temp128 = load %array$ref*, %array$ref** %x2
  %temp129 = bitcast %array$ref* %temp127 to i8*
  %temp130 = bitcast %array$ref* %temp128 to i8*
  %temp126 = call i8* @t_rt_array_assignment_ref(i32 28, i8* %temp129, i32 1, i8* %temp130)
  %temp131 = bitcast i8*%temp126 to %class$Object*
  ; deref
  %temp132 = load %array$ref*, %array$ref** %x3
  ; array access
  %temp134 = icmp eq %array$ref* %temp132, null
  br i1 %temp134, label %Label39, label %Label40
  Label39:
  call void @t_rt_null_reference_error(i32 29)
  br label %Label40
  Label40:
  %temp135 = getelementptr %array$ref, %array$ref* %temp132, i32 0, i32 3
  %temp136 = load i32, i32* %temp135
  %temp137 = icmp sge i32 1, 0
  br i1 %temp137, label %Label41, label %Label43
  Label41:
  %temp138 = icmp sge i32 1, %temp136
  br i1 %temp138, label %Label43, label %Label42
  Label43:
  call void @t_rt_index_out_of_bounds_error(i32 29)
  br label %Label42
  Label42:
  %temp139 = getelementptr %array$ref, %array$ref* %temp132, i32 0, i32 4
  %temp133 = getelementptr [0 x i8*], [0 x i8*]* %temp139, i32 0, i32 1
  %temp140 = bitcast i8** %temp133 to %array$ref**
  ; deref
  %temp141 = load %array$ref*, %array$ref** %temp140
  ; array access
  %temp143 = icmp eq %array$ref* %temp141, null
  br i1 %temp143, label %Label44, label %Label45
  Label44:
  call void @t_rt_null_reference_error(i32 29)
  br label %Label45
  Label45:
  %temp144 = getelementptr %array$ref, %array$ref* %temp141, i32 0, i32 3
  %temp145 = load i32, i32* %temp144
  %temp146 = icmp sge i32 3, 0
  br i1 %temp146, label %Label46, label %Label48
  Label46:
  %temp147 = icmp sge i32 3, %temp145
  br i1 %temp147, label %Label48, label %Label47
  Label48:
  call void @t_rt_index_out_of_bounds_error(i32 29)
  br label %Label47
  Label47:
  %temp148 = getelementptr %array$ref, %array$ref* %temp141, i32 0, i32 4
  %temp142 = getelementptr [0 x i8*], [0 x i8*]* %temp148, i32 0, i32 3
  %temp149 = bitcast i8** %temp142 to %array$int**
  ; deref
  %temp150 = load %array$int*, %array$int** %temp149
  ; array access
  %temp152 = icmp eq %array$int* %temp150, null
  br i1 %temp152, label %Label49, label %Label50
  Label49:
  call void @t_rt_null_reference_error(i32 29)
  br label %Label50
  Label50:
  %temp153 = getelementptr %array$int, %array$int* %temp150, i32 0, i32 3
  %temp154 = load i32, i32* %temp153
  %temp155 = icmp sge i32 5, 0
  br i1 %temp155, label %Label51, label %Label53
  Label51:
  %temp156 = icmp sge i32 5, %temp154
  br i1 %temp156, label %Label53, label %Label52
  Label53:
  call void @t_rt_index_out_of_bounds_error(i32 29)
  br label %Label52
  Label52:
  %temp157 = getelementptr %array$int, %array$int* %temp150, i32 0, i32 4
  %temp151 = getelementptr [0 x i32], [0 x i32]* %temp157, i32 0, i32 5
  ; deref
  %temp158 = load i32, i32* %temp151
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp158)
  ; deref
  %temp160 = load %array$ref*, %array$ref** %o
  ; new array
  %temp162 = bitcast [1 x i8*]* @intVMT to i8*
  %temp163 = call i8* (i32, i8*, i32, i32, ...) @t_rt_newArray(i32 30, i8* %temp162, i32 1, i32 1, i32 3)
  %temp161 = bitcast i8* %temp163 to %array$int*
  %temp164 = bitcast %array$ref* %temp160 to i8*
  %temp165 = bitcast %array$int* %temp161 to i8*
  %temp159 = call i8* @t_rt_array_assignment_ref(i32 30, i8* %temp164, i32 1, i8* %temp165)
  %temp166 = bitcast i8*%temp159 to %class$Object*
  ret i32 0
}
