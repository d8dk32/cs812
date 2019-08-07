; source file: superConstructorCall.t
; T version: 1.0
; compiled: Tue Aug 06 21:05:49 EDT 2019

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

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %b = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %b
  ; class instance creation
  %temp1 = getelementptr %class$B*, %class$B** null, i32 2
  %temp2 = ptrtoint %class$B** %temp1 to i64
  %temp3 = call i8* @t_rt_alloc(i64 %temp2, i32 12)
  %temp4 = bitcast i8* %temp3 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp4
  %temp0 = bitcast i8* %temp3 to %class$B*
  ; store assigned value
  store %class$B* %temp0, %class$B** %b
  ; load value from variable
  %temp5 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp6 = icmp eq %class$B* %temp5, null
  br i1 %temp6, label %label0, label %label1
  label0:
  call void @t_rt_print_null_ref_error(i32 13)
  br label %label1
  label1:
  %temp8 = getelementptr %class$B, %class$B* %temp5, i32 0, i32 1
  %temp10 = load i32, i32* %temp8
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp10)
  ret void
}
