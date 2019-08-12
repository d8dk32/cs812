; source file: methodCall2.t
; T version: 1.0
; compiled: Mon Aug 12 16:19:23 EDT 2019

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
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$A =  type { i8* }
%class$Object =  type { i8* }

; method declaration meth1
define i32 @A$meth1$int$int$( i8* %context, i32 %param0, i32 %param1 ) {
  ; copy method params
  %temp0 = bitcast i8* %context to %class$A* 
  %temp1 = load %class$A, %class$A* %temp0
  %this = alloca %class$A
  store %class$A %temp1, %class$A* %this
  ; copy method params
  %a = alloca i32
  store i32 %param0, i32* %a
  %b = alloca i32
  store i32 %param1, i32* %b
  ; method body
  ; load value from variable
  %temp2 = load i32, i32* %a
  ; load value from variable
  %temp3 = load i32, i32* %b
  ; add
  %temp4 = add i32 %temp2, %temp3
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp4)
  %temp5 = load %class$A, %class$A* %this
  store %class$A %temp5, %class$A* %temp0
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
  %temp7 = getelementptr %class$A*, %class$A** null, i32 1
  %temp8 = ptrtoint %class$A** %temp7 to i64
  %temp9 = call i8* @t_rt_alloc(i64 %temp8, i32 9)
  %temp10 = bitcast i8* %temp9 to [1 x i8*]**
  store [1 x i8*]* @A$VMT, [1 x i8*]** %temp10
  %temp6 = bitcast i8* %temp9 to %class$A*
  ; store assigned value
  store %class$A* %temp6, %class$A** %a
  ; load value from variable
  %temp11 = load %class$A*, %class$A** %a
  %temp12 = bitcast %class$A* %temp11 to i8*
  call i32 @A$meth1$int$int$(i8* %temp12, i32 20, i32 22)
  ret void
}
