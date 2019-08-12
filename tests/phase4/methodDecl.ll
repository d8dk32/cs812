; source file: methodDecl.t
; T version: 1.0
; compiled: Sun Aug 11 21:46:32 EDT 2019

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
%class$B =  type { i8*, i32, i32 }
%class$Object =  type { i8* }

; method declaration meth1
define void @A$meth1$( %class$A %context ) {
  ; copy constructor params
  %this = alloca %class$A
  store %class$A %context, %class$A* %this
  ; constructor body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 1)
  ret void
}

; method declaration meth2
define void @A$meth2$( %class$A %context ) {
  ; copy constructor params
  %this = alloca %class$A
  store %class$A %context, %class$A* %this
  ; constructor body
  ; field access
  ; Null reference check
  %temp0 = icmp eq %class$A* %this, null
  br i1 %temp0, label %label0, label %label1
  label0:
  call void @t_rt_print_null_ref_error(i32 5)
  br label %label1
  label1:
  %temp2 = getelementptr %class$A, %class$A* %this, i32 0, i32 1
  %temp4 = load i32, i32* %temp2
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp4)
  ret void
}

; method declaration meth3
define void @A$meth3$( %class$A %context ) {
  ; copy constructor params
  %this = alloca %class$A
  store %class$A %context, %class$A* %this
  ; constructor body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 3)
  ret void
}

; method declaration meth4
define void @B$meth4$int$intarr$A$( %class$B %context, i32 %param0, %array$int %param1, %class$A* %param2 ) {
  ; copy constructor params
  %this = alloca %class$B
  store %class$B %context, %class$B* %this
  %a = alloca i32
  store i32 %param0, i32* %a
  %b = alloca %array$int
  store %array$int %param1, %array$int* %b
  %c = alloca %class$A*
  store %class$A* %param2, %class$A** %c
  ; constructor body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 1)
  ret void
}

; method declaration meth5
define void @B$meth5$( %class$B %context ) {
  ; copy constructor params
  %this = alloca %class$B
  store %class$B %context, %class$B* %this
  ; constructor body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 5)
  ret void
}

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 42)
  ret void
}
