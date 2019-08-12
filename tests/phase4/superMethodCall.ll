; source file: superMethodCall.t
; T version: 1.0
; compiled: Mon Aug 12 18:16:02 EDT 2019

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
  call void @t_rt_print_int(i32 42)
  %temp2 = load %class$A, %class$A* %this
  store %class$A %temp2, %class$A* %temp0
  ret i32 0
}

; method declaration meth1
define i32 @B$meth1$( i8* %context ) {
  ; copy method params
  %temp3 = bitcast i8* %context to %class$B* 
  %temp4 = load %class$B, %class$B* %temp3
  %this = alloca %class$B
  store %class$B %temp4, %class$B* %this
  ; copy method params
  ; method body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 4242)
  %temp5 = load %class$B, %class$B* %this
  store %class$B %temp5, %class$B* %temp3
  ret i32 0
}

; method declaration meth2
define i32 @B$meth2$( i8* %context ) {
  ; copy method params
  %temp6 = bitcast i8* %context to %class$B* 
  %temp7 = load %class$B, %class$B* %temp6
  %this = alloca %class$B
  store %class$B %temp7, %class$B* %this
  ; copy method params
  ; method body
  %temp8 = call i32 @A$meth1$(i8* %context)
  %temp9 = load %class$B, %class$B* %this
  store %class$B %temp9, %class$B* %temp6
  ret i32 0
}

; main block
define i32 @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %b = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %b
  ; class instance creation
  %temp11 = getelementptr %class$B*, %class$B** null, i32 1
  %temp12 = ptrtoint %class$B** %temp11 to i64
  %temp13 = call i8* @t_rt_alloc(i64 %temp12, i32 12)
  %temp14 = bitcast i8* %temp13 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp14
  %temp10 = bitcast i8* %temp13 to %class$B*
  ; store assigned value
  store %class$B* %temp10, %class$B** %b
  ; load value from variable
  %temp15 = load %class$B*, %class$B** %b
  %temp16 = bitcast %class$B* %temp15 to i8*
  %temp17 = call i32 @B$meth2$(i8* %temp16)
  ret i32 0
}
