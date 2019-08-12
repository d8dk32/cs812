; source file: superConstructorCall.t
; T version: 1.0
; compiled: Mon Aug 12 15:45:44 EDT 2019

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
declare void @t_rt_runtime_cast_check(i32, i8*, i8*)

; VMTs
@arrayVMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@intVMT = global [1 x i8*] [i8* null]
@A$VMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@B$VMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @A$VMT to i8*)]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$A =  type { i8*, i32 }
%class$B =  type { i8*, i32 }
%class$Object =  type { i8* }

; constructor declaration for class A
define void @A$constructor$int$int$( i8* %context, i32 %param0, i32 %param1 ) {
  ; copy constructor params
  %i = alloca i32
  store i32 %param0, i32* %i
  %j = alloca i32
  store i32 %param1, i32* %j
  %temp0 = bitcast i8* %context to %class$A* 
  %temp1 = load %class$A, %class$A* %temp0
  %this = alloca %class$A
  store %class$A %temp1, %class$A* %this
  ; rest of constructor body
  ; load value from variable
  %temp2 = load i32, i32* %i
  ; load value from variable
  %temp3 = load i32, i32* %j
  ; add
  %temp4 = add i32 %temp2, %temp3
  ; field access
  ; Null reference check
  %temp5 = icmp eq %class$A* %this, null
  br i1 %temp5, label %label0, label %label1
  label0:
  call void @t_rt_print_null_ref_error(i32 3)
  br label %label1
  label1:
  %temp7 = getelementptr %class$A, %class$A* %this, i32 0, i32 1
  ; store assigned value
  store i32 %temp4, i32* %temp7
  ; field access
  ; Null reference check
  %temp9 = icmp eq %class$A* %this, null
  br i1 %temp9, label %label2, label %label3
  label2:
  call void @t_rt_print_null_ref_error(i32 4)
  br label %label3
  label3:
  %temp11 = getelementptr %class$A, %class$A* %this, i32 0, i32 1
  %temp13 = load i32, i32* %temp11
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp13)
  %temp14 = load %class$A, %class$A* %this
  store %class$A %temp14, %class$A* %temp0
  ret void
}

; constructor declaration for class B
define void @B$constructor$int$( i8* %context, i32 %param0 ) {
  ; copy constructor params
  %i = alloca i32
  store i32 %param0, i32* %i
  ; load value from variable
  %temp15 = load i32, i32* %i
  ; load value from variable
  %temp16 = load i32, i32* %i
  ; add
  %temp17 = add i32 %temp16, 5
  call void @A$constructor$int$int$(i8* %context, i32 %temp15, i32 %temp17)
  %temp18 = bitcast i8* %context to %class$B* 
  %temp19 = load %class$B, %class$B* %temp18
  %this = alloca %class$B
  store %class$B %temp19, %class$B* %this
  ; rest of constructor body
  ; field access
  ; Null reference check
  %temp20 = icmp eq %class$B* %this, null
  br i1 %temp20, label %label4, label %label5
  label4:
  call void @t_rt_print_null_ref_error(i32 13)
  br label %label5
  label5:
  %temp22 = getelementptr %class$B, %class$B* %this, i32 0, i32 1
  %temp24 = load i32, i32* %temp22
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp24)
  %temp25 = load %class$B, %class$B* %this
  store %class$B %temp25, %class$B* %temp18
  ret void
}

; constructor declaration for class B
define void @B$constructor$int$int$( i8* %context, i32 %param0, i32 %param1 ) {
  ; copy constructor params
  %i = alloca i32
  store i32 %param0, i32* %i
  %j = alloca i32
  store i32 %param1, i32* %j
  %temp26 = bitcast i8* %context to %class$B* 
  %temp27 = load %class$B, %class$B* %temp26
  %this = alloca %class$B
  store %class$B %temp27, %class$B* %this
  ; rest of constructor body
  ; load value from variable
  %temp28 = load i32, i32* %i
  ; load value from variable
  %temp29 = load i32, i32* %j
  ; add
  %temp30 = add i32 %temp28, %temp29
  ; add
  %temp31 = add i32 %temp30, 1
  ; field access
  ; Null reference check
  %temp32 = icmp eq %class$B* %this, null
  br i1 %temp32, label %label6, label %label7
  label6:
  call void @t_rt_print_null_ref_error(i32 16)
  br label %label7
  label7:
  %temp34 = getelementptr %class$B, %class$B* %this, i32 0, i32 1
  ; store assigned value
  store i32 %temp31, i32* %temp34
  ; field access
  ; Null reference check
  %temp36 = icmp eq %class$B* %this, null
  br i1 %temp36, label %label8, label %label9
  label8:
  call void @t_rt_print_null_ref_error(i32 17)
  br label %label9
  label9:
  %temp38 = getelementptr %class$B, %class$B* %this, i32 0, i32 1
  %temp40 = load i32, i32* %temp38
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp40)
  %temp41 = load %class$B, %class$B* %this
  store %class$B %temp41, %class$B* %temp26
  ret void
}

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %b = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %b
  ; class instance creation
  %temp43 = getelementptr %class$B*, %class$B** null, i32 2
  %temp44 = ptrtoint %class$B** %temp43 to i64
  %temp45 = call i8* @t_rt_alloc(i64 %temp44, i32 23)
  %temp46 = bitcast i8* %temp45 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp46
  %temp42 = bitcast i8* %temp45 to %class$B*
  call void @B$constructor$int$(i8* %temp45, i32 13)
  ; store assigned value
  store %class$B* %temp42, %class$B** %b
  ; load value from variable
  %temp47 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp48 = icmp eq %class$B* %temp47, null
  br i1 %temp48, label %label10, label %label11
  label10:
  call void @t_rt_print_null_ref_error(i32 25)
  br label %label11
  label11:
  %temp50 = getelementptr %class$B, %class$B* %temp47, i32 0, i32 1
  %temp52 = load i32, i32* %temp50
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp52)
  ret void
}
