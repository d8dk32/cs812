; source file: constructorDecl.t
; T version: 1.0
; compiled: Sun Aug 11 17:32:27 EDT 2019

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

; constructor declaration for class A
define void @A$constructor$( i8* %context ) {
  ; copy constructor params
  %temp0 = bitcast i8* %context to %class$A*
  %this = alloca %class$A*
  store %class$A* %temp0, %class$A** %this
  ; constructor body
  ret void
}

; constructor declaration for class B
define void @B$constructor$int$A$( i8* %context, i32 %param0, %class$A* %param1 ) {
  ; copy constructor params
  %temp1 = bitcast i8* %context to %class$B*
  %this = alloca %class$B*
  store %class$B* %temp1, %class$B** %this
  %a = alloca i32
  store i32 %param0, i32* %a
  %c = alloca %class$A*
  store %class$A* %param1, %class$A** %c
  ; constructor body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 42)
  ret void
}

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 42)
  ret void
}
