; source file: methRet.t
; T version: 1.0
; compiled: Mon Aug 12 18:10:25 EDT 2019

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
define i32 @A$meth1$( i8* %context ) {
  ; copy method params
  %temp0 = bitcast i8* %context to %class$A* 
  %temp1 = load %class$A, %class$A* %temp0
  %this = alloca %class$A
  store %class$A %temp1, %class$A* %this
  ; copy method params
  ; method body
  ret i32 42
  %temp2 = load %class$A, %class$A* %this
  store %class$A %temp2, %class$A* %temp0
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
  %temp4 = getelementptr %class$A*, %class$A** null, i32 1
  %temp5 = ptrtoint %class$A** %temp4 to i64
  %temp6 = call i8* @t_rt_alloc(i64 %temp5, i32 7)
  %temp7 = bitcast i8* %temp6 to [1 x i8*]**
  store [1 x i8*]* @A$VMT, [1 x i8*]** %temp7
  %temp3 = bitcast i8* %temp6 to %class$A*
  ; store assigned value
  store %class$A* %temp3, %class$A** %a
  ; load value from variable
  %temp8 = load %class$A*, %class$A** %a
  %temp9 = bitcast %class$A* %temp8 to i8*
  %temp10 = call i32 @A$meth1$(i8* %temp9)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp10)
  ret void
}
