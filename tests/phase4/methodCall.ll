; source file: methodCall.t
; T version: 1.0
; compiled: Mon Aug 12 16:18:30 EDT 2019

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
%class$A =  type { i8* }
%class$B =  type { i8* }
%class$Object =  type { i8* }

; method declaration meth1
define i32 @A$meth1$( i8* %context ) {
  ; copy method params
  %temp0 = bitcast i8* %context to %class$A* 
  %temp1 = load %class$A, %class$A* %temp0
  %this = alloca %class$A
  store %class$A %temp1, %class$A* %this
  ; copy method params
  ; method body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 1)
  %temp2 = load %class$A, %class$A* %this
  store %class$A %temp2, %class$A* %temp0
  ret i32 0
}

; method declaration meth2
define i32 @A$meth2$( i8* %context ) {
  ; copy method params
  %temp3 = bitcast i8* %context to %class$A* 
  %temp4 = load %class$A, %class$A* %temp3
  %this = alloca %class$A
  store %class$A %temp4, %class$A* %this
  ; copy method params
  ; method body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 2)
  %temp5 = load %class$A, %class$A* %this
  store %class$A %temp5, %class$A* %temp3
  ret i32 0
}

; method declaration meth3
define i32 @A$meth3$( i8* %context ) {
  ; copy method params
  %temp6 = bitcast i8* %context to %class$A* 
  %temp7 = load %class$A, %class$A* %temp6
  %this = alloca %class$A
  store %class$A %temp7, %class$A* %this
  ; copy method params
  ; method body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 3)
  %temp8 = load %class$A, %class$A* %this
  store %class$A %temp8, %class$A* %temp6
  ret i32 0
}

; method declaration meth2
define i32 @B$meth2$( i8* %context ) {
  ; copy method params
  %temp9 = bitcast i8* %context to %class$B* 
  %temp10 = load %class$B, %class$B* %temp9
  %this = alloca %class$B
  store %class$B %temp10, %class$B* %this
  ; copy method params
  ; method body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 22)
  %temp11 = load %class$B, %class$B* %this
  store %class$B %temp11, %class$B* %temp9
  ret i32 0
}

; method declaration meth4
define i32 @B$meth4$( i8* %context ) {
  ; copy method params
  %temp12 = bitcast i8* %context to %class$B* 
  %temp13 = load %class$B, %class$B* %temp12
  %this = alloca %class$B
  store %class$B %temp13, %class$B* %this
  ; copy method params
  ; method body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 44)
  %temp14 = load %class$B, %class$B* %this
  store %class$B %temp14, %class$B* %temp12
  ret i32 0
}

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %a = alloca %class$A*
  ; store empty value on declaration
  store %class$A* null, %class$A** %a
  ; class instance creation
  %temp16 = getelementptr %class$A*, %class$A** null, i32 1
  %temp17 = ptrtoint %class$A** %temp16 to i64
  %temp18 = call i8* @t_rt_alloc(i64 %temp17, i32 9)
  %temp19 = bitcast i8* %temp18 to [1 x i8*]**
  store [1 x i8*]* @A$VMT, [1 x i8*]** %temp19
  %temp15 = bitcast i8* %temp18 to %class$A*
  ; store assigned value
  store %class$A* %temp15, %class$A** %a
  ; load value from variable
  %temp20 = load %class$A*, %class$A** %a
  %temp21 = bitcast %class$A* %temp20 to i8*
  call i32 @A$meth1$(i8* %temp21)
  ; load value from variable
  %temp22 = load %class$A*, %class$A** %a
  %temp23 = bitcast %class$A* %temp22 to i8*
  call i32 @A$meth2$(i8* %temp23)
  ; load value from variable
  %temp24 = load %class$A*, %class$A** %a
  %temp25 = bitcast %class$A* %temp24 to i8*
  call i32 @A$meth3$(i8* %temp25)
  ; class instance creation
  %temp27 = getelementptr %class$B*, %class$B** null, i32 1
  %temp28 = ptrtoint %class$B** %temp27 to i64
  %temp29 = call i8* @t_rt_alloc(i64 %temp28, i32 13)
  %temp30 = bitcast i8* %temp29 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp30
  %temp26 = bitcast i8* %temp29 to %class$B*
  %temp31 = bitcast %class$B* %temp26 to %class$A*
  ; store assigned value
  store %class$A* %temp31, %class$A** %a
  ; load value from variable
  %temp32 = load %class$A*, %class$A** %a
  %temp33 = bitcast %class$A* %temp32 to i8*
  call i32 @A$meth1$(i8* %temp33)
  ; load value from variable
  %temp34 = load %class$A*, %class$A** %a
  %temp35 = bitcast %class$A* %temp34 to i8*
  call i32 @A$meth2$(i8* %temp35)
  ; load value from variable
  %temp36 = load %class$A*, %class$A** %a
  %temp37 = bitcast %class$A* %temp36 to i8*
  call i32 @A$meth3$(i8* %temp37)
  ; declaration statement
  %b = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %b
  ; class instance creation
  %temp39 = getelementptr %class$B*, %class$B** null, i32 1
  %temp40 = ptrtoint %class$B** %temp39 to i64
  %temp41 = call i8* @t_rt_alloc(i64 %temp40, i32 18)
  %temp42 = bitcast i8* %temp41 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp42
  %temp38 = bitcast i8* %temp41 to %class$B*
  ; store assigned value
  store %class$B* %temp38, %class$B** %b
  ; load value from variable
  %temp43 = load %class$B*, %class$B** %b
  %temp44 = bitcast %class$B* %temp43 to i8*
  call i32 @A$meth1$(i8* %temp44)
  ; load value from variable
  %temp45 = load %class$B*, %class$B** %b
  %temp46 = bitcast %class$B* %temp45 to i8*
  call i32 @B$meth2$(i8* %temp46)
  ; load value from variable
  %temp47 = load %class$B*, %class$B** %b
  %temp48 = bitcast %class$B* %temp47 to i8*
  call i32 @A$meth3$(i8* %temp48)
  ; load value from variable
  %temp49 = load %class$B*, %class$B** %b
  %temp50 = bitcast %class$B* %temp49 to i8*
  call i32 @B$meth4$(i8* %temp50)
  ret void
}
