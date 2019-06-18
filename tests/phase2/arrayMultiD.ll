; source file: arrayMultiD.t
; T version: 1.0
; compiled: Wed Dec 12 17:00:36 EST 2018

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
  %x = alloca %array$ref*
  store %array$ref* null, %array$ref** %x
  ; new array
  %temp1 = bitcast [1 x i8*]* @intVMT to i8*
  %temp2 = call i8* (i32, i8*, i32, i32, ...) @t_rt_newArray(i32 4, i8* %temp1, i32 2, i32 1, i32 10)
  %temp0 = bitcast i8* %temp2 to %array$ref*
  ; assign
  store %array$ref* %temp0, %array$ref** %x
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
  %temp7 = icmp slt i32 %temp5, 10
  %temp6 = zext i1 %temp7 to i32
  %temp8 = icmp ne i32 %temp6, 0
  br i1 %temp8, label %Label1, label %Label2
  Label1:
  ; deref
  %temp10 = load %array$ref*, %array$ref** %x
  ; deref
  %temp11 = load i32, i32* %i
  ; new array
  %temp13 = bitcast [1 x i8*]* @intVMT to i8*
  %temp14 = call i8* (i32, i8*, i32, i32, ...) @t_rt_newArray(i32 9, i8* %temp13, i32 1, i32 1, i32 5)
  %temp12 = bitcast i8* %temp14 to %array$int*
  %temp15 = bitcast %array$ref* %temp10 to i8*
  %temp16 = bitcast %array$int* %temp12 to i8*
  %temp9 = call i8* @t_rt_array_assignment_ref(i32 9, i8* %temp15, i32 %temp11, i8* %temp16)
  %temp17 = bitcast i8*%temp9 to %array$int*
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
  %temp24 = load %array$ref*, %array$ref** %x
  ; deref
  %temp25 = load i32, i32* %i
  ; array access
  %temp27 = icmp eq %array$ref* %temp24, null
  br i1 %temp27, label %Label6, label %Label7
  Label6:
  call void @t_rt_null_reference_error(i32 12)
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
  call void @t_rt_index_out_of_bounds_error(i32 12)
  br label %Label9
  Label9:
  %temp32 = getelementptr %array$ref, %array$ref* %temp24, i32 0, i32 4
  %temp26 = getelementptr [0 x i8*], [0 x i8*]* %temp32, i32 0, i32 %temp25
  %temp33 = bitcast i8** %temp26 to %array$int**
  ; deref
  %temp34 = load %array$int*, %array$int** %temp33
  ; deref
  %temp35 = load i32, i32* %j
  ; deref
  %temp36 = load i32, i32* %i
  ; multiply
  %temp37 = mul i32 %temp36, 10
  ; deref
  %temp38 = load i32, i32* %j
  ; add
  %temp39 = add i32 %temp37, %temp38
  %temp40 = bitcast %array$int* %temp34 to i8*
  %temp23 = call i32 @t_rt_array_assignment_int(i32 12, i8* %temp40, i32 %temp35, i32 %temp39)
  ; deref
  %temp41 = load i32, i32* %j
  ; add
  %temp42 = add i32 %temp41, 1
  ; assign
  store i32 %temp42, i32* %j
  br label %Label3
  Label5:
  ; deref
  %temp44 = load i32, i32* %i
  ; add
  %temp45 = add i32 %temp44, 1
  ; assign
  store i32 %temp45, i32* %i
  br label %Label0
  Label2:
  %sum = alloca i32
  store i32 0, i32* %sum
  ; assign
  store i32 0, i32* %sum
  ; assign
  store i32 0, i32* %i
  ; while statement
  br label %Label11
  Label11:
  ; deref
  %temp49 = load i32, i32* %i
  ; <
  %temp51 = icmp slt i32 %temp49, 10
  %temp50 = zext i1 %temp51 to i32
  %temp52 = icmp ne i32 %temp50, 0
  br i1 %temp52, label %Label12, label %Label13
  Label12:
  ; assign
  store i32 0, i32* %j
  ; while statement
  br label %Label14
  Label14:
  ; deref
  %temp54 = load i32, i32* %j
  ; <
  %temp56 = icmp slt i32 %temp54, 5
  %temp55 = zext i1 %temp56 to i32
  %temp57 = icmp ne i32 %temp55, 0
  br i1 %temp57, label %Label15, label %Label16
  Label15:
  ; deref
  %temp58 = load i32, i32* %sum
  ; deref
  %temp59 = load %array$ref*, %array$ref** %x
  ; deref
  %temp60 = load i32, i32* %i
  ; array access
  %temp62 = icmp eq %array$ref* %temp59, null
  br i1 %temp62, label %Label17, label %Label18
  Label17:
  call void @t_rt_null_reference_error(i32 23)
  br label %Label18
  Label18:
  %temp63 = getelementptr %array$ref, %array$ref* %temp59, i32 0, i32 3
  %temp64 = load i32, i32* %temp63
  %temp65 = icmp sge i32 %temp60, 0
  br i1 %temp65, label %Label19, label %Label21
  Label19:
  %temp66 = icmp sge i32 %temp60, %temp64
  br i1 %temp66, label %Label21, label %Label20
  Label21:
  call void @t_rt_index_out_of_bounds_error(i32 23)
  br label %Label20
  Label20:
  %temp67 = getelementptr %array$ref, %array$ref* %temp59, i32 0, i32 4
  %temp61 = getelementptr [0 x i8*], [0 x i8*]* %temp67, i32 0, i32 %temp60
  %temp68 = bitcast i8** %temp61 to %array$int**
  ; deref
  %temp69 = load %array$int*, %array$int** %temp68
  ; deref
  %temp70 = load i32, i32* %j
  ; array access
  %temp72 = icmp eq %array$int* %temp69, null
  br i1 %temp72, label %Label22, label %Label23
  Label22:
  call void @t_rt_null_reference_error(i32 23)
  br label %Label23
  Label23:
  %temp73 = getelementptr %array$int, %array$int* %temp69, i32 0, i32 3
  %temp74 = load i32, i32* %temp73
  %temp75 = icmp sge i32 %temp70, 0
  br i1 %temp75, label %Label24, label %Label26
  Label24:
  %temp76 = icmp sge i32 %temp70, %temp74
  br i1 %temp76, label %Label26, label %Label25
  Label26:
  call void @t_rt_index_out_of_bounds_error(i32 23)
  br label %Label25
  Label25:
  %temp77 = getelementptr %array$int, %array$int* %temp69, i32 0, i32 4
  %temp71 = getelementptr [0 x i32], [0 x i32]* %temp77, i32 0, i32 %temp70
  ; deref
  %temp78 = load i32, i32* %temp71
  ; add
  %temp79 = add i32 %temp58, %temp78
  ; assign
  store i32 %temp79, i32* %sum
  ; deref
  %temp81 = load i32, i32* %j
  ; add
  %temp82 = add i32 %temp81, 1
  ; assign
  store i32 %temp82, i32* %j
  br label %Label14
  Label16:
  ; deref
  %temp84 = load i32, i32* %i
  ; add
  %temp85 = add i32 %temp84, 1
  ; assign
  store i32 %temp85, i32* %i
  br label %Label11
  Label13:
  ; deref
  %temp87 = load i32, i32* %sum
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp87)
  ret i32 0
}
